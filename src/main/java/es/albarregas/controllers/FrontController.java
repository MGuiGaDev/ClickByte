/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IProductoDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.LineaCesta;
import es.albarregas.models.UtilidadesCookie;
import es.albarregas.models.UtilidadesLineaCesta;
import java.io.IOException;
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
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "index.jsp";
        ArrayList<LineaCesta> listaProductosCesta = new ArrayList<>();
        Cookie[] co = request.getCookies();
        Cookie cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");

        if (request.getParameter("volver") == null) {
            if (cookieAnonimo != null) {
                if (!listaProductosCesta.isEmpty()) {
                    DAOFactory daof = DAOFactory.getDAOFactory(1);
                    IProductoDAO ipd = daof.getProductoDAO();
                    listaProductosCesta = ipd.cargarProductosCarrito(listaProductosCesta);
                    int cantidadProductosCesta = UtilidadesLineaCesta.cantidadTotalProductosCesta(listaProductosCesta);
                    double total = UtilidadesLineaCesta.calcularTotal(listaProductosCesta);
                    request.getSession().setAttribute("listaProductosCesta", listaProductosCesta);
                    request.getSession().setAttribute("cantidadProductosCesta", cantidadProductosCesta);
                    request.getSession().setAttribute("totalCarrito", total);
                }
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
