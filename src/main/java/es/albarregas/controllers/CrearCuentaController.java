/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IUsuarioDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Producto;
import es.albarregas.beans.Usuario;
import es.albarregas.models.UtilidadesCookie;
import es.albarregas.models.UtilidadesUsuario;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Manuel Guillén Gallardo
 */
@MultipartConfig
@WebServlet(name = "CrearCuentaController", urlPatterns = {"/CrearCuentaController"})

public class CrearCuentaController extends HttpServlet {

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

        String url = "FrontController";
        Producto productoCarrito = null;
        ArrayList<Producto> listaProductosCarrito = new ArrayList<>();
        Cookie[] co = request.getCookies();
        Cookie cookieAnonimo = null;
        Cookie cookieUsuario = null;
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuarioDAO udao = daof.getUsuarioDAO();
        Usuario usuario = new Usuario();
        String dirImagen = request.getServletContext().getRealPath("/IMAGENES/AVATARES/");
        String accion = request.getParameter("accion");

        if (request.getParameter("accion") != null) {
            switch (accion) {
                case "cancelar":
                    url = "index.jsp";
                    break;
                case "crear":
                    try {
                    BeanUtils.populate(usuario, request.getParameterMap());
                    String sql = UtilidadesUsuario.crearSentenciaSQL(usuario);
                    boolean creado = udao.crearUsuario(sql);
                    if (creado) {
                        usuario.setIdUsuario(udao.obtenerIdUsuario());
                        Part filePart = request.getPart("avatar");
                        usuario.setAvatar(UtilidadesUsuario.subirAvatar(filePart, dirImagen, usuario));
                        if (usuario.getAvatar().length() > 1) {
                            boolean actualizado = udao.actualizarAvatar(usuario);
                            if (actualizado) {
                                url = "JSP/usuario.jsp";
                                request.getSession().setAttribute("usuario", usuario);
                                cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");
                                if (cookieAnonimo != null) {
                                    if (!cookieAnonimo.getValue().equals("")) {
                                        listaProductosCarrito = UtilidadesCookie.cargarListaProductos(cookieAnonimo);
                                        cookieAnonimo.setMaxAge(0);
                                        response.addCookie(cookieAnonimo);
                                    }
                                } else {
                                    cookieUsuario = new Cookie("cookieUsuario", "");
                                    cookieUsuario.setMaxAge(60 * 60 * 24 * 2);
                                    response.addCookie(cookieUsuario);
                                }
                            }
                        }
                    }
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(CrearCuentaController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(CrearCuentaController.class.getName()).log(Level.SEVERE, null, ex);
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
