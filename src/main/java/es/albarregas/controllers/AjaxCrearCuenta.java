/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IUsuarioDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Manuel Guillén Gallardo
 */
@WebServlet(name = "AjaxCrearCuenta", urlPatterns = {"/AjaxCrearCuenta"})
public class AjaxCrearCuenta extends HttpServlet {

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

        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuarioDAO iud = daof.getUsuarioDAO();

        switch (accion) {
            case "datosUsuario":
                objeto = new JSONObject();
                if (request.getParameter("name") != null) {
                    String email = request.getParameter("name");
                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario = iud.comprobarEmail(email);
                    if (nuevoUsuario == null) {
                        objeto.put("tipo", "success");
                        objeto.put("email", "El email no existe.");
                    } else {
                        objeto.put("tipo", "error");
                        objeto.put("email", "El email ya existe.");
                    }
                }

                response.setContentType("application/json");
                response.getWriter().print(objeto);
                break;
        }
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
