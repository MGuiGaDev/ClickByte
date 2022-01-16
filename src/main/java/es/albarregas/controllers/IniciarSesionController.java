/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.ILineaPedidoDAO;
import es.albarregas.DAO.IPedidoDAO;
import es.albarregas.DAO.IUsuarioDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.LineaCesta;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Usuario;
import es.albarregas.models.UtilidadesCookie;
import es.albarregas.models.UtilidadesLineaCesta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
        IPedidoDAO iped = daof.getPedidoDAO();
        ILineaPedidoDAO ild = daof.getLineaPedidoDAO();
        IUsuarioDAO udao = daof.getUsuarioDAO();
        Usuario usuario = null;
        Pedido pedido = new Pedido();
        ArrayList<LineaCesta> listaProductosCesta = new ArrayList<>();
        Cookie[] co = request.getCookies();
        Cookie cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");

        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "cerrar":
                    request.removeAttribute("mensajeCrearCuenta");
                    break;
                case "iniciarSesion":
                    if (request.getParameter("email") != null) {
                        usuario = new Usuario();
                        usuario.setEmail(request.getParameter("email"));
                        usuario = udao.obtenerUsuario(usuario);
                    }
                    break;
            }

            if (usuario != null) {
                request.getSession().setAttribute("usuario", usuario);
                pedido.setIdUsuario(usuario.getIdUsuario());
                pedido = iped.obtenerPedidoNoFinalizado(pedido);
                if (pedido != null) {
                    listaProductosCesta = ild.obtenerTodasLasLineas(pedido);
                    Collections.sort(listaProductosCesta);
                    int cantidadProductosCesta = UtilidadesLineaCesta.cantidadTotalProductosCesta(listaProductosCesta);
                    request.getSession().setAttribute("listaProductosCesta", listaProductosCesta);
                    request.getSession().setAttribute("cantidadProductosCesta", cantidadProductosCesta);
                    request.getSession().setAttribute("totalCesta", pedido.getImporte());
                }

                if (cookieAnonimo != null) {
                    cookieAnonimo.setMaxAge(0);
                    response.addCookie(cookieAnonimo);
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
