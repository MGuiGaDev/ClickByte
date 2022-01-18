/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.ILineaPedidoDAO;
import es.albarregas.DAO.IPedidoDAO;
import es.albarregas.DAO.IProductoDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.LineaCesta;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Producto;
import es.albarregas.beans.Usuario;
import es.albarregas.models.UtilidadesCookie;
import es.albarregas.models.UtilidadesLineaCesta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
/**
 * <h1>Función</h1>
 * <p>
 * Mediante este Servlet gestionaremos la cesta de un usuario dinámicamente.
 * A ete se accede desde cualquier lugar de la aplicación.</p>
 * <h2>Flujo</h2>
 * <ol>
 * <li>Recibimos una petición AJAX que representa una acción realizada por el usuario.</li>
 * <li>Cargamos un atributo de sesión (de existir, lo actualizamos), con los datos recibidos: añadir producto a cesta vacía, añadir producto a cesta no vacía, eliminar producto de cesta, eliminar cesta.</li>
 * <li>Cargamos el objeto JSON que vamos a devolver a la petición AJAX.</li>
 * <li>Dependiendo de si se trata de una acción realizada por un usuario logueado o anónimo cargamos un atributo de sesión usuario, o una cookie.</li>
 * <h2>Variable "tipoAccion"</h2>
 * <p>
 * Esta variable nos sirve para determinar en caso de que exista atributo de
 * sesión "usuario" qué tipo de acción vamos a realizar en la BBDD:</p>
 * <ol>
 * <li>"insertar"</li>
 * <li>"actualizar"</li>
 * <li>"eliminarLinea"</li>
 * <li>"eliminarPedido"</li>
 * </ol>
 * <h2>Observaciones</h2>
 * <p>
 * Dado que se ha cometido el error de no dividir la gestión de la cesta de los
 * usuarios dependiendo de si estamos ante un usuario logueado o anónimo,
 * tenemos este archivo exvesivamente extenso.</p>
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
        IPedidoDAO iped = daof.getPedidoDAO();
        ILineaPedidoDAO ilpd = daof.getLineaPedidoDAO();
        String accion = request.getParameter("accion");
        JSONObject objeto = null;

        String tipoAccion = "";
        boolean remove = false;

        Cookie[] co = request.getCookies();
        Cookie cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");

        ArrayList<LineaCesta> listaProductosCesta = new ArrayList<>();
        Pedido pedido = new Pedido();
        LineaPedido lineaPedido = new LineaPedido();
        LineaCesta lineaCesta = new LineaCesta();
        Producto producto = new Producto();
        Usuario usuario = new Usuario();

        if (request.getSession().getAttribute("listaProductosCesta") != null) {
            listaProductosCesta = (ArrayList<LineaCesta>) request.getSession().getAttribute("listaProductosCesta");
            Collections.sort(listaProductosCesta);
        }

        int cantidadProductosCesta = 0;
        double total = 0;

        if (request.getParameter("accion") != null) {
            if (request.getParameter("id") != null) {
                producto.setIdProducto(Short.parseShort(request.getParameter("id")));
            }
            switch (accion) {
                case "nuevoProducto":
                    if (request.getParameter("id") != null) {
                        if (!listaProductosCesta.isEmpty()) {
                            boolean esta = false;
                            for (LineaCesta lC : listaProductosCesta) {
                                if (lC.getIdProducto() == producto.getIdProducto()) {
                                    lineaCesta.setCantidad((short) ((short) lC.getCantidad() + 1));
                                    lineaCesta = lC;
                                    esta = true;
                                    tipoAccion = "actualizar";
                                }
                            }
                            if (esta == false) {
                                producto = ipd.cargarProducto(producto);
                                lineaCesta = UtilidadesLineaCesta.cargarListaCestaConProducto(producto);
                                lineaCesta.setOrden((short) ((short) listaProductosCesta.size() + 1));
                                listaProductosCesta.add(lineaCesta);
                                tipoAccion = "insertar";
                            }
                        } else {
                            producto = ipd.cargarProducto(producto);
                            lineaCesta = UtilidadesLineaCesta.cargarListaCestaConProducto(producto);
                            lineaCesta.setOrden((short) 1);
                            lineaCesta.setCantidad((short) 1);
                            listaProductosCesta.add(lineaCesta);
                            tipoAccion = "crearPedido";
                        }

                    }
                    break;
                case "add":
                    if (!listaProductosCesta.isEmpty()) {
                        for (LineaCesta lC : listaProductosCesta) {
                            if (lC.getIdProducto() == producto.getIdProducto()) {
                                lC.setCantidad((short) (lC.getCantidad() + 1));
                                lineaCesta = lC;
                                tipoAccion = "actualizar";
                            }
                        }
                    }

                    break;
                case "remove":
                    if (!listaProductosCesta.isEmpty()) {
                        for (LineaCesta lC : listaProductosCesta) {
                            if (lC.getIdProducto() == producto.getIdProducto()) {
                                lC.setCantidad((short) (lC.getCantidad() - 1));
                                lineaCesta = lC;
                                tipoAccion = "actualizar";
                            }
                        }
                    }

                    break;

                case "clear":
                    lineaCesta = new LineaCesta();
                    if (!listaProductosCesta.isEmpty()) {
                        Iterator<LineaCesta> it = listaProductosCesta.listIterator();
                        while (it.hasNext()) {
                            LineaCesta lc = it.next();
                            if (lc.getIdProducto() == producto.getIdProducto()) {
                                it.remove();
                                remove = true;
                            }
                            if (remove == true) {
                                lc.setOrden((short) (lc.getOrden() - 1));
                            }
                        }
                        tipoAccion = listaProductosCesta.isEmpty() ? "eliminarPedido" : "eliminarLinea";
                    }
                    break;
                case "delete":
                    if (!listaProductosCesta.isEmpty()) {
                        listaProductosCesta.clear();
                        tipoAccion = "eliminarPedido";
                    }
                    break;
            }
        }
        if (!listaProductosCesta.isEmpty()) {
            Collections.sort(listaProductosCesta);
            cantidadProductosCesta = UtilidadesLineaCesta.cantidadTotalProductosCesta(listaProductosCesta);
            total = UtilidadesLineaCesta.calcularTotal(listaProductosCesta);
            request.getSession().setAttribute("listaProductosCesta", listaProductosCesta);
            request.getSession().setAttribute("cantidadProductosCesta", cantidadProductosCesta);
            request.getSession().setAttribute("totalCesta", total);
            objeto = new JSONObject();
            objeto.put("tipo", "success");
            objeto.put("idProducto", lineaCesta.getIdProducto());
            objeto.put("imagen", lineaCesta.getNombreImagen());
            objeto.put("nombre", lineaCesta.getNombre());
            objeto.put("cantidad", lineaCesta.getCantidad());
            objeto.put("precio", lineaCesta.getPrecioUnitario());
            objeto.put("cantidadTotal", cantidadProductosCesta);
            objeto.put("total", total);
        } else {
            request.getSession().removeAttribute("listaProductosCesta");
            request.getSession().removeAttribute("cantidadProductosCesta");
            request.getSession().removeAttribute("totalCesta");
            objeto = new JSONObject();
            objeto.put("tipo", "vacio");
        }
        if (!listaProductosCesta.isEmpty()) {
            if (request.getSession().getAttribute("usuario") != null) {
                usuario = (Usuario) request.getSession().getAttribute("usuario");
                if (tipoAccion.length() > 0) {
                    pedido.setIdUsuario((short) usuario.getIdUsuario());
                    pedido.setImporte(total);
                    pedido.setIva(total * 0.21);
                    if (tipoAccion.equalsIgnoreCase("crearPedido")) {
                        boolean creado = iped.crearPedido(pedido);
                        if (creado) {
                            lineaPedido.setIdProducto(lineaCesta.getIdProducto());
                            Pedido p = new Pedido();
                            p = iped.obtenerPedidoNoFinalizado(pedido);
                            lineaPedido.setIdPedido(p.getIdPedido());
                            lineaPedido.setCantidad((short) 1);
                            lineaPedido.setOrden((short) 1);
                            ilpd.insertarLineaPedido(lineaPedido);
                            lineaCesta.setIdPedido(p.getIdPedido());
                            lineaPedido = ilpd.getIdLineaPedido(lineaCesta);
                            lineaCesta.setIdLinea(lineaPedido.getIdLinea());
                            listaProductosCesta = UtilidadesLineaCesta.cargarIdsListaProductosCestaUsuario(listaProductosCesta, lineaCesta);
                        }
                    } else {
                        Pedido p = new Pedido();
                        p = iped.obtenerPedidoNoFinalizado(pedido);
                        pedido.setIdPedido(p.getIdPedido());
                        lineaPedido.setIdPedido(pedido.getIdPedido());
                        lineaPedido.setIdProducto(lineaCesta.getIdProducto());
                        LineaPedido nuevaL = new LineaPedido();
                        switch (tipoAccion) {
                            case "insertar":
                                lineaPedido.setCantidad((short) 1);
                                lineaPedido.setOrden((short) 1);
                                ilpd.insertarLineaPedido(lineaPedido);
                                break;
                            case "actualizar":
                                nuevaL = ilpd.getIdLineaPedido(lineaCesta);
                                lineaPedido.setIdLinea(nuevaL.getIdLinea());
                                lineaPedido.setCantidad(lineaCesta.getCantidad());
                                ilpd.actualizarCantidadLineaPedido(lineaPedido);
                                break;
                            case "eliminarLinea":
                                nuevaL = ilpd.getIdLineaPedido(lineaCesta);
                                lineaPedido.setIdLinea(nuevaL.getIdLinea());
                                lineaPedido = ilpd.getIdLineaPedido(lineaCesta);
                                ilpd.eliminarUnaLineaPedido(lineaPedido);
                                break;
                            case "eliminarPedido":
                                ilpd.eliminarTodasLasLineas(pedido);
                                iped.eliminarPedido(pedido);
                                break;
                        }
                        if (!tipoAccion.equalsIgnoreCase("eliminarPedido")) {
                            lineaCesta.setIdLinea(lineaPedido.getIdLinea());
                            iped.actualizarPedido(pedido);
                            listaProductosCesta = UtilidadesLineaCesta.cargarIdsListaProductosCestaUsuario(listaProductosCesta, lineaCesta);
                        }
                    }
                }
            } else {

                if (cookieAnonimo == null) {
                    cookieAnonimo = new Cookie("cookieAnonimo", "");
                }
                cookieAnonimo = UtilidadesCookie.cargarCookie(listaProductosCesta);
                cookieAnonimo.setMaxAge(60 * 60 * 24 * 2);
                response.addCookie(cookieAnonimo);
            }

        }

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
