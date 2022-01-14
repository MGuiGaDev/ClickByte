/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.ICategoriaDAO;
import es.albarregas.DAO.IProductoDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Categoria;
import es.albarregas.beans.Producto;
import es.albarregas.models.UtilidadesCookie;
import es.albarregas.models.UtilidadesProducto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "index.jsp";
        List<Producto> listaProductosCarrito = new ArrayList<>();
        List<Categoria> listaCategorias = new ArrayList<>();
        Cookie[] co = request.getCookies();
        Cookie cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");

        if (request.getSession().getAttribute("listaCategorias") == null && request.getParameter("volver") == null) {
            DAOFactory daof = DAOFactory.getDAOFactory(1);
            ICategoriaDAO icd = daof.getCategoriaDAO();
            listaCategorias = icd.listarCategorias();
            request.getSession().setAttribute("listaCategorias", listaCategorias);
            request.getSession().setAttribute("dirImagen", "/IMAGENES/AVATARES/");
        }

        if (request.getSession().getAttribute("listaProductosCarrito") == null && cookieAnonimo != null) {
            if (!cookieAnonimo.getValue().equals("")) {
                listaProductosCarrito = UtilidadesCookie.cargarListaProductos(cookieAnonimo);
                if (!listaProductosCarrito.isEmpty()) {
                    DAOFactory daof = DAOFactory.getDAOFactory(1);
                    IProductoDAO ipd = daof.getProductoDAO();
                    listaProductosCarrito = ipd.cargarProductosCarrito(listaProductosCarrito);
                    int cantidadProductosCarrito = UtilidadesProducto.cantidadTotalProductosCarrito(listaProductosCarrito);
                    double totalCarrito = UtilidadesProducto.calcularTotal(listaProductosCarrito);
                    request.getSession().setAttribute("listaProductosCarrito", listaProductosCarrito);
                    request.getSession().setAttribute("cantidadProductosCarrito", cantidadProductosCarrito);
                    request.getSession().setAttribute("totalCarrito", totalCarrito);
                }
                cookieAnonimo.setMaxAge(60 * 60 * 24 * 2);
                response.addCookie(cookieAnonimo);
            }
        }
        request.getRequestDispatcher(url).forward(request, response);

    }

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
        processRequest(request, response);
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

        processRequest(request, response);
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
