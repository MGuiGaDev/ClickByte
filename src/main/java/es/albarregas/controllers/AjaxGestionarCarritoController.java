/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IProductoDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.ListaCesta;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Producto;
import es.albarregas.models.UtilidadesCookie;
import es.albarregas.models.UtilidadesListaCesta;
import es.albarregas.models.UtilidadesProducto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Manuel Guillén Gallardo
 */
@WebServlet(name = "AjaxGestionarCarritoController", urlPatterns = {"/AjaxGestionarCarritoController"})
public class AjaxGestionarCarritoController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IProductoDAO ipd = daof.getProductoDAO();
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        JSONObject objeto = null;

        Cookie[] co = request.getCookies();
        Cookie cookieAnonimo = null;

        ArrayList<ListaCesta> listaProductosCesta = new ArrayList<>();
        Pedido pedido = new Pedido();
        ArrayList<LineaPedido> lineaPedido = new ArrayList<>();
        ListaCesta listaCesta = new ListaCesta();
        Producto producto = new Producto();

        if (request.getSession().getAttribute("listaProductosCesta") != null) {
            listaProductosCesta = (ArrayList<ListaCesta>) request.getSession().getAttribute("listaProductosCesta");
        }

        int cantidadProductosCesta = 0;
        double total = 0;

        if (request.getParameter("accion") != null) {
            switch (accion) {
                case "nuevoProducto":
                    if (request.getParameter("id") != null) {
                        producto.setIdProducto(Short.parseShort(request.getParameter("id")));
                        if (!listaProductosCesta.isEmpty()) {
                            boolean esta = false;
                            for (ListaCesta lC : listaProductosCesta) {
                                if (lC.getIdProducto() == producto.getIdProducto()) {
                                    //pro.setCantidad((short) (pro.getCantidad() + 1));
                                    listaCesta = new ListaCesta();
                                    listaCesta = lC;
                                    esta = true;
                                }
                            }
                            if (esta == false) {
                                producto = ipd.cargarProducto(producto);
                                listaCesta = UtilidadesListaCesta.cargarListaCestaConProducto(producto);
                                //productoCarrito.setOrden((short) ((short) listaProductosCarrito.size() + 1));
                                listaProductosCesta.add(listaCesta);
                            }
                        } else {
                            producto = ipd.cargarProducto(producto);
                            listaCesta = UtilidadesListaCesta.cargarListaCestaConProducto(producto);
                            //producto.setOrden((short) 1);
                            listaProductosCesta.add(listaCesta);
                        }

                    }
                    break;
                case "add":
                    if (request.getParameter("id") != null) {
                        String idProducto = request.getParameter("id");
                        if (!listaProductosCesta.isEmpty()) {
                            for (ListaCesta lC : listaProductosCesta) {
                                if (lC.getIdProducto() == Short.parseShort(idProducto)) {
                                    lC.setCantidad((short) (lC.getCantidad() + 1));
                                    listaCesta = new ListaCesta();
                                    listaCesta = lC;
                                }
                            }
                        }
                    }
                    break;
                case "remove":
                    if (request.getParameter("id") != null) {
                        String idProducto = request.getParameter("id");
                        if (!listaProductosCesta.isEmpty()) {
                            for (ListaCesta lC : listaProductosCesta) {
                                if (lC.getIdProducto() == Short.parseShort(idProducto)) {
                                    lC.setCantidad((short) (lC.getCantidad() - 1));
                                    listaCesta = new ListaCesta();
                                    listaCesta = lC;
                                }
                            }
                        }
                    }
                    break;

                case "clear":
                    listaCesta = new ListaCesta();
                    if (request.getParameter("id") != null) {
                        String idProducto = request.getParameter("id");

                        if (!listaProductosCesta.isEmpty()) {
                            Iterator<ListaCesta> it = listaProductosCesta.listIterator();
                            while (it.hasNext()) {
                                if (it.next().getIdProducto() == Short.parseShort(idProducto)) {
                                    it.remove();
                                }
                            }
                        }
                    }
                    break;
                case "delete":
                    if (!listaProductosCesta.isEmpty()) {
                        listaProductosCesta.clear();
                    }
                    break;
            }
        }

        if (!listaProductosCesta.isEmpty()) {
            //Preguntar por atributo de usuario
            cantidadProductosCesta = UtilidadesListaCesta.cantidadTotalProductosCesta(listaProductosCesta);
            total = UtilidadesListaCesta.calcularTotal(listaProductosCesta);
            request.getSession().setAttribute("listaProductosCesta", listaProductosCesta);
            request.getSession().setAttribute("cantidadProductosCesta", cantidadProductosCesta);
            request.getSession().setAttribute("totalCesta", total);
            //cambiarCookie a listaCesta
            cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");
            if (cookieAnonimo == null) {
                cookieAnonimo = UtilidadesCookie.cargarCookie(listaProductosCesta);
                cookieAnonimo.setMaxAge(60 * 60 * 24 * 2);
            } else {
                cookieAnonimo = UtilidadesCookie.cargarCookie(listaProductosCesta);
                cookieAnonimo.setMaxAge(60 * 60 * 24 * 2);
            }

            objeto = new JSONObject();
            objeto.put("tipo", "success");
            objeto.put("idProducto", listaCesta.getIdProducto());
            objeto.put("imagen", listaCesta.getNombreImagen());
            objeto.put("nombre", listaCesta.getNombre());
            objeto.put("cantidad", listaCesta.getCantidad());
            objeto.put("precio", listaCesta.getPrecioUnitario());
            objeto.put("cantidadTotal", cantidadProductosCesta);
            objeto.put("total", total);

        } else {

            request.getSession().removeAttribute("listaProductosCesta");
            request.getSession().removeAttribute("cantidadProductosCesta");
            request.getSession().removeAttribute("totalCesta");

            cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");
            if (cookieAnonimo != null) {
                cookieAnonimo.setValue("");
                cookieAnonimo.setMaxAge(0);
            }
            objeto = new JSONObject();
            objeto.put("tipo", "vacio");

        }
        response.addCookie(cookieAnonimo);
        response.setContentType("application/json");
        response.getWriter().print(objeto);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
