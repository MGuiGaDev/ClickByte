/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IUsuarioDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Usuario;
import es.albarregas.models.UtilidadesCookie;
import java.io.IOException;
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
@WebServlet(name = "IniciarSesionController", urlPatterns = {"/IniciarSesionController"})
public class IniciarSesionController extends HttpServlet {

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
        String url = "index.jsp";
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuarioDAO udao = daof.getUsuarioDAO();
        Usuario usuario = null;
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "cerrar":
                    request.removeAttribute("mensajeCrearCuenta");
                    break;
                case "iniciarSesion":
                    //aquí, si el usuario tiene una cookie de anónimo nos da igual => reventarla
                    //debo comprobar última compra realizada y en caso de que exista la paso a sesión

                    if (request.getParameter("email") != null) {
                        usuario = new Usuario();
                        usuario.setEmail(request.getParameter("email"));
                        usuario = udao.obtenerUsuario(usuario);
                        if (usuario != null) {
                            request.getSession().setAttribute("usuario", usuario);
                        }
                    }
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
