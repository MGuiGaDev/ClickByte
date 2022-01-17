/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IPedidoDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Usuario;
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
@WebServlet(name = "OpcionesCuentaUsuarioController", urlPatterns = {"/OpcionesCuentaUsuarioController"})
public class OpcionesCuentaUsuarioController extends HttpServlet {

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
        String url = "JSP/usuario.jsp";
        String accion = "";
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        Usuario nuevoU = new Usuario();
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IPedidoDAO iped = daof.getPedidoDAO();

        if (request.getParameter("accion") != null) {
            accion = request.getParameter("accion");
            switch (accion) {
                case "volver":
                    url = "FrontController";
                case "verPerfil":
                    request.setAttribute("verPerfil", "verPerfil");
                    break;
                case "verPedidos":
                    request.setAttribute("verPedidos", "verPedidos");
                    nuevoU = (Usuario) request.getSession().getAttribute("usuario");
                    listaPedidos = iped.obtenerTodosLosPedidos(nuevoU);
                    if(!listaPedidos.isEmpty()) {
                        request.setAttribute("listaPedidos", listaPedidos);
                    }
                    
                    break;
                case "cerrarSesion":
                    url = "FrontController";
                    request.setAttribute("volver", "volver");
                    request.getSession().removeAttribute("usuario");
                    request.getSession().removeAttribute("listaProductosCesta");
                    request.getSession().removeAttribute("cantidadProductosCesta");
                    request.getSession().removeAttribute("totalCesta");
                    break;
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
