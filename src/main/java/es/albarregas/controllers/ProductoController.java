/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Producto;
import es.albarregas.models.UtilidadesProducto;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manuel Guillén Gallardo
 */
@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

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
        String url = "/JSP/producto.jsp";
        String idProducto = null;
        ArrayList<Producto> listaProductos = new ArrayList<>();
        Producto producto = null;

        if (request.getParameter("idProducto") != null) {
            idProducto = request.getParameter("idProducto");
            if (request.getSession().getAttribute("listaProductos") != null) {
                listaProductos = (ArrayList<Producto>) request.getSession().getAttribute("listaProductos");
                producto = UtilidadesProducto.verDetalleProducto(listaProductos, idProducto);
            }
            if (producto != null) {
                request.setAttribute("producto", producto);
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
