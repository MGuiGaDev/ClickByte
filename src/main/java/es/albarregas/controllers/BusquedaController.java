/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Producto;
import es.albarregas.models.UtilidadesProducto;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "BusquedaController", urlPatterns = {"/BusquedaController"})
public class BusquedaController extends HttpServlet {

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
        String url = "/JSP/resultadoBusqueda.jsp";
        ArrayList<Producto> listaProductos = (ArrayList<Producto>) request.getSession().getAttribute("listaProductos");
        ArrayList<Producto> listaProductosBuscados = new ArrayList<>();
        ArrayList<String>marcas = new ArrayList<>();
        double precioMasAlto = 0;
        String accion = "";
        String valorBuscado = "";
        if (request.getParameter("accion") != null && !listaProductos.isEmpty()) {
            accion = request.getParameter("accion");
            if (accion.equals("buscar")) {
                valorBuscado = request.getParameter("busqueda__input");
            } else {
                valorBuscado = accion;
            }
            listaProductosBuscados = UtilidadesProducto.buscarProductos(listaProductos, valorBuscado);
        }
        
        if(listaProductosBuscados.isEmpty()){
            request.setAttribute("listaProductosBuscados", listaProductos);
            request.setAttribute("numeroResultado", listaProductos.size());
            marcas = UtilidadesProducto.filtroPorMarca(listaProductos);
            request.setAttribute("marcas", marcas);
            precioMasAlto = UtilidadesProducto.filtroPrecioMasAlto(listaProductos);
            request.setAttribute("precioMasAlto", precioMasAlto);
        } else {
            request.setAttribute("listaProductosBuscados", listaProductosBuscados);
            request.setAttribute("numeroResultado", listaProductosBuscados.size());
            marcas = UtilidadesProducto.filtroPorMarca(listaProductosBuscados);
            request.setAttribute("marcas", marcas);
            precioMasAlto = UtilidadesProducto.filtroPrecioMasAlto(listaProductosBuscados);
            request.setAttribute("precioMasAlto", precioMasAlto);
        }
        request.setAttribute("valorBuscado", valorBuscado);
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
