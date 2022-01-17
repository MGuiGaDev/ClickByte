/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IPedidoDAO;
import es.albarregas.DAO.IProductoDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.LineaCesta;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Usuario;
import es.albarregas.models.UtilidadesCookie;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "FinalizarCarritoController", urlPatterns = {"/FinalizarCarritoController"})
public class FinalizarCarritoController extends HttpServlet {

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
        ArrayList<LineaCesta> listaProductosCesta = new ArrayList<>();
        Pedido pedido = null;
        Usuario usuario = null;
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IPedidoDAO iped = daof.getPedidoDAO();

        if (request.getSession().getAttribute("usuario") == null) {
            url = "FrontController";
            request.setAttribute("mensajeCrearCuenta", "crear");
        } else {
            if (request.getSession().getAttribute("listaProductosCesta")!=null) {
                usuario = (Usuario) request.getSession().getAttribute("usuario");
                listaProductosCesta = (ArrayList<LineaCesta>) request.getSession().getAttribute("listaProductosCesta");
                pedido = new Pedido();
                pedido.setIdUsuario((short) usuario.getIdUsuario());
                Pedido pedidoFinalizado = new Pedido();
                pedidoFinalizado = iped.obtenerPedidoNoFinalizado(pedido);
                iped.cambiarEstadoPedido(pedidoFinalizado);
                request.setAttribute("listaProductosPedidoFinalizado", listaProductosCesta);
                request.setAttribute("pedidoFinalizado", pedidoFinalizado);
                request.getSession().removeAttribute("listaProductosCesta");
                request.getSession().removeAttribute("cantidadProductosCesta");
                request.getSession().removeAttribute("totalCesta");
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
