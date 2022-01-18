/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import es.albarregas.DAO.IUsuarioDAO;

/**
 *
 * @author Manuel Guillén Gallardo
 */
/**
 * 
 * @author Manuel Guillén Gallardo
 */
@WebServlet(name = "AjaxValidarCuentaController", urlPatterns = {"/AjaxValidarCuentaController"})
public class AjaxValidarCuentaController extends HttpServlet {

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

        
        Usuario usuario = null;

        if (request.getParameter("accion") != null) {
            switch (accion) {
                case "validarEmail":
                    objeto = new JSONObject();
                    usuario = new Usuario();
                    usuario.setEmail(request.getParameter("name"));
                    usuario = iud.comprobarEmail(usuario);
                    if (usuario == null) {
                        objeto.put("tipo", "success");
                        objeto.put("email", "El email no existe.");
                    } else {
                        objeto.put("tipo", "error");
                        objeto.put("email", "El email ya existe.");
                    }
                    break;
                case "validarEmailLogin":
                    objeto = new JSONObject();
                    usuario = new Usuario();
                    usuario.setEmail(request.getParameter("name"));
                    usuario = iud.comprobarEmail(usuario);
                    if (usuario == null) {
                        objeto.put("tipo", "error");
                        objeto.put("email", "El email no existe.");
                    } else {
                        objeto.put("tipo", "success");
                        objeto.put("email", "El email existe.");
                    }
                    break;
                case "validarPasswordLogin":
                    objeto = new JSONObject();
                    usuario = new Usuario();
                    usuario.setEmail(request.getParameter("email"));
                    usuario.setPassword(request.getParameter("password"));
                    Usuario usuarioComprobado = null;
                    usuarioComprobado = iud.comprobarPassword(usuario);
                    if (usuarioComprobado == null) {
                        objeto.put("tipo", "error");
                        objeto.put("email", "El email y la contraseña no coinciden.");
                    } else {
                        objeto.put("tipo", "success");
                        objeto.put("email", "El email y la contraseña coinciden.");
                    }
                    break;
                case "validarDni":
                    objeto = new JSONObject();
                    int numero = Integer.parseInt(request.getParameter("dni"));
                    String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
                    if (numero != 0) {
                        int indice = numero % 23;
                        objeto.put("tipo", "success");
                        objeto.put("letra", letras.substring(indice, indice + 1));
                    } else {
                        objeto.put("tipo", "error");
                        objeto.put("letra", "El DNI no puede ser 0");
                    }
                    break;
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
