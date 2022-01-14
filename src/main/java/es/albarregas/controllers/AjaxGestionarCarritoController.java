/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IProductoDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Producto;
import es.albarregas.models.UtilidadesCookie;
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
        Cookie cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");

        List<Producto> listaProductosCarrito = new ArrayList<>();
        Pedido pedido = new Pedido();
        List<LineaPedido> lineaPedido = new ArrayList<>();

        if (request.getSession().getAttribute("listaProductosCarrito") != null) {
            listaProductosCarrito = (ArrayList<Producto>) request.getSession().getAttribute("listaProductosCarrito");
        }

        Producto productoCarrito = null;
        int cantidadProductosCarrito = 0;
        double total = 0;

        if (request.getParameter("accion") != null) {
            productoCarrito = new Producto();
            switch (accion) {
                case "nuevoProducto":
                    if (request.getParameter("id") != null) {
                        productoCarrito.setIdProducto(Short.parseShort(request.getParameter("id")));
                        if (!listaProductosCarrito.isEmpty()) {
                            boolean esta = false;
                            for (Producto pro : listaProductosCarrito) {
                                if (pro.getIdProducto() == productoCarrito.getIdProducto()) {
                                    //pro.setCantidad((short) (pro.getCantidad() + 1));
                                    productoCarrito = new Producto();
                                    productoCarrito = pro;
                                    esta = true;
                                }
                            }
                            if (esta == false) {
                                productoCarrito = ipd.cargarProducto(productoCarrito);
                                //productoCarrito.setOrden((short) ((short) listaProductosCarrito.size() + 1));
                                listaProductosCarrito.add(productoCarrito);
                            }
                        } else {
                            productoCarrito = ipd.cargarProducto(productoCarrito);
                            productoCarrito.setOrden((short) 1);
                            listaProductosCarrito.add(productoCarrito);
                        }

                    }
                    break;
                case "add":
                    if (request.getParameter("id") != null) {
                        String idProducto = request.getParameter("id");
                        if (!listaProductosCarrito.isEmpty()) {
                            for (Producto pro : listaProductosCarrito) {
                                if (pro.getIdProducto() == Short.parseShort(idProducto)) {
                                    pro.setCantidad((short) (pro.getCantidad() + 1));
                                    productoCarrito = new Producto();
                                    productoCarrito = pro;
                                }
                            }
                        }
                    }
                    break;
                case "remove":
                    if (request.getParameter("id") != null) {
                        String idProducto = request.getParameter("id");
                        if (!listaProductosCarrito.isEmpty()) {
                            for (Producto pro : listaProductosCarrito) {
                                if (pro.getIdProducto() == Short.parseShort(idProducto)) {
                                    pro.setCantidad((short) (pro.getCantidad() - 1));
                                    productoCarrito = new Producto();
                                    productoCarrito = pro;
                                }
                            }
                        }
                    }
                    break;

                case "clear":
                    productoCarrito = new Producto();
                    if (request.getParameter("id") != null) {
                        String idProducto = request.getParameter("id");

                        if (!listaProductosCarrito.isEmpty()) {
                            Iterator<Producto> it = listaProductosCarrito.listIterator();
                            while (it.hasNext()) {
                                if (it.next().getIdProducto() == Short.parseShort(idProducto)) {
                                    it.remove();
                                }
                            }
                        }
                    }
                    break;
                case "delete":
                    if (!listaProductosCarrito.isEmpty()) {
                        listaProductosCarrito.clear();
                    }
                    break;
            }
        }

        if (!listaProductosCarrito.isEmpty()) {
            cantidadProductosCarrito = UtilidadesProducto.cantidadTotalProductosCarrito(listaProductosCarrito);
            total = UtilidadesProducto.calcularTotal(listaProductosCarrito);
            request.getSession().setAttribute("listaProductosCarrito", listaProductosCarrito);
            request.getSession().setAttribute("cantidadProductosCarrito", cantidadProductosCarrito);
            request.getSession().setAttribute("totalCarrito", total);
            cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");
            cookieAnonimo = UtilidadesCookie.cargarCookie(listaProductosCarrito);
            cookieAnonimo.setMaxAge(60 * 60 * 24 * 2);
            objeto = new JSONObject();
            objeto.put("tipo", "success");
            objeto.put("idProducto", productoCarrito.getIdProducto());
            objeto.put("imagen", productoCarrito.getDireccionImagen());
            objeto.put("nombre", productoCarrito.getNombre());
            objeto.put("cantidad", productoCarrito.getCantidad());
            objeto.put("precio", productoCarrito.getPrecio());
            objeto.put("cantidadTotal", cantidadProductosCarrito);
            objeto.put("total", total);

        } else {
            
            request.getSession().removeAttribute("listaProductosCarrito");
            request.getSession().removeAttribute("cantidadProductosCarrito");
            request.getSession().removeAttribute("totalCarrito");
            if (!cookieAnonimo.getValue().equals("")) {
                cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");
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
