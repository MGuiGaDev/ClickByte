/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Producto;
import es.albarregas.models.UtilidadesCategoria;
import es.albarregas.models.UtilidadesCookie;
import es.albarregas.models.UtilidadesProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manuel Guillén Gallardo
 */
@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

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
        String url = "JSP/categoria.jsp";
        ArrayList<Producto> listaProductosCarrito = new ArrayList<>();
        ArrayList<Producto> listaProductos = new ArrayList<>();
        ArrayList<Producto> listaProductosIdCategoria = new ArrayList<>();
        Producto productoCarrito;
        Cookie[] co = request.getCookies();
        Cookie cookieAnonimo = null;
        if (request.getParameter("categoria") != null) {
            String idCategoria = request.getParameter("categoria");
            if (request.getParameter("nombreCategoria") != null) {
                String nombreCategoria = request.getParameter("nombreCategoria");
                request.setAttribute("nombreCategoria", nombreCategoria);
            }
            listaProductos = (ArrayList<Producto>) request.getSession().getAttribute("listaProductos");
            listaProductosIdCategoria = UtilidadesProducto.filtrarProductosPorIdCategoria(listaProductos, idCategoria);
            if (!listaProductosIdCategoria.isEmpty()) {
                request.setAttribute("listaProductosIdCategoria", listaProductosIdCategoria);
            }
         
            cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");
            if (!cookieAnonimo.getValue().equals("")) {
                String[] datosCookie = cookieAnonimo.getValue().split("#");
                for (String i : datosCookie) {
                    String[] productoCookie = i.split("-");
                    productoCarrito = new Producto();
                    productoCarrito.setIdProducto(Short.parseShort(productoCookie[0]));
                    productoCarrito.setCantidad(Short.parseShort(productoCookie[1]));
                    listaProductosCarrito.add(productoCarrito);
                }
                if (!listaProductosCarrito.isEmpty()) {
                    listaProductosCarrito = UtilidadesProducto.filtrarProductosEnCarrito(listaProductos, listaProductosCarrito);
                    int cantidadProductosCarrito = UtilidadesProducto.cantidadTotalProductosCarrito(listaProductosCarrito);
                    request.getSession().setAttribute("listaProductosCarrito", listaProductosCarrito);
                    request.getSession().setAttribute("cantidadProductosCarrito", cantidadProductosCarrito);
                }
            }
            
        }
        request.getRequestDispatcher(url).forward(request, response);
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
