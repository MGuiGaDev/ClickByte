/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Producto;
import es.albarregas.models.UtilidadesCookie;
import es.albarregas.models.UtilidadesProducto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "GestionarCarrito", urlPatterns = {"/GestionarCarrito"})
public class GestionarCarrito extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        JSONObject objeto = null;

        Cookie[] co = request.getCookies();
        Cookie cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");
        Cookie cookieRegistrado = null;

        ArrayList<Producto> listaProductosCarrito = new ArrayList<>();
        ArrayList<Producto> listaProductos = (ArrayList<Producto>) request.getSession().getAttribute("listaProductos");

        if (request.getSession().getAttribute("listaProductosCarrito") != null) {
            listaProductosCarrito = (ArrayList<Producto>) request.getSession().getAttribute("listaProductosCarrito");
            listaProductosCarrito = UtilidadesProducto.filtrarProductosEnCarrito(listaProductos, listaProductosCarrito);
        }

        Producto productoCarrito = null;

        int cantidadProductosCarrito = 0;
        double total = 0;

        switch (accion) {
            case "nuevoProducto":
                objeto = new JSONObject();
                productoCarrito = new Producto();
                if (request.getParameter("id") != null) {
                    String idProducto = request.getParameter("id");
                    if (!listaProductosCarrito.isEmpty()) {
                        boolean esta = false;
                        for (Producto pro : listaProductosCarrito) {
                            if (pro.getIdProducto() == Short.parseShort(idProducto)) {
                                pro.setCantidad((short) (pro.getCantidad() + 1));
                                productoCarrito = new Producto();
                                productoCarrito = pro;
                                esta = true;
                            }
                        }
                        if (esta == false) {
                            if (!listaProductos.isEmpty()) {
                                productoCarrito = new Producto();
                                productoCarrito = UtilidadesProducto.obtenerProductoParaCarrito(listaProductos, Short.parseShort(idProducto));
                                listaProductosCarrito.add(productoCarrito);
                            }
                        }
                    } else {
                        if (!listaProductos.isEmpty()) {
                            productoCarrito = new Producto();
                            productoCarrito = UtilidadesProducto.obtenerProductoParaCarrito(listaProductos, Short.parseShort(idProducto));
                            listaProductosCarrito.add(productoCarrito);
                        }
                    }

                    cantidadProductosCarrito = UtilidadesProducto.cantidadTotalProductosCarrito(listaProductosCarrito);
                    listaProductosCarrito = UtilidadesProducto.filtrarProductosEnCarrito(listaProductos, listaProductosCarrito);
                    total = UtilidadesProducto.calcularTotal(listaProductosCarrito);
                    request.getSession().setAttribute("listaProductosCarrito", listaProductosCarrito);
                    request.getSession().setAttribute("cantidadProductosCarrito", cantidadProductosCarrito);
                    request.getSession().setAttribute("totalCarrito", total);
                    cookieAnonimo = UtilidadesCookie.cargarCookie(listaProductosCarrito, cookieAnonimo);
                    cookieAnonimo.setMaxAge(60 * 60 * 24 * 2);
                    response.addCookie(cookieAnonimo);

                    if (!cookieAnonimo.getValue().equals("")) {

                        objeto.put("tipo", "success");
                        objeto.put("idProducto", productoCarrito.getIdProducto());
                        objeto.put("imagen", productoCarrito.getDireccionImagen());
                        objeto.put("nombre", productoCarrito.getNombre());
                        objeto.put("cantidad", productoCarrito.getCantidad());
                        objeto.put("precio", productoCarrito.getPrecio());
                        objeto.put("cantidadTotal", cantidadProductosCarrito);
                        objeto.put("total", total);

                    } else {
                        objeto.put("tipo", "error");
                        objeto.put("email", "La cookie no ha sido creada.");
                    }

                }
                break;
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
