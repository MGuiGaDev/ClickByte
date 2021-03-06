/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IProductoDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Categoria;
import es.albarregas.beans.Producto;
import es.albarregas.models.UtilidadesCategoria;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        List<Producto> listaProductosIdCategoria = new ArrayList<>();
        ArrayList<Categoria> listaCategorias = null;
        Producto producto = null;

        if (request.getParameter("categoria") != null) {
            producto = new Producto();
            DAOFactory daof = DAOFactory.getDAOFactory(1);
            IProductoDAO ipd = daof.getProductoDAO();
            producto.setIdCategoria(Short.parseShort(request.getParameter("categoria")));
            listaProductosIdCategoria = ipd.obtenerProductosPorCategoria(producto);
            ServletContext contexto = getServletContext();
            listaCategorias = (ArrayList<Categoria>) contexto.getAttribute("listaCategorias");
            if (!listaCategorias.isEmpty() && !listaProductosIdCategoria.isEmpty()) {
                String nombreCategoria = UtilidadesCategoria.obtenerNombreCategoria(producto.getIdCategoria(), listaCategorias);
                request.setAttribute("listaProductosIdCategoria", listaProductosIdCategoria);
                request.setAttribute("nombreCategoria", nombreCategoria);
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
