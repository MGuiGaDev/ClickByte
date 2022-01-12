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
import es.albarregas.models.UtilidadesCategoria;
import es.albarregas.models.UtilidadesCookie;
import es.albarregas.models.UtilidadesProducto;
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
        ArrayList<Producto> listaProductos = new ArrayList<>();
        ArrayList<Producto> listaPortatiles = new ArrayList<>();
        Cookie[] co = request.getCookies();
        Cookie cookieAnonimo = null;
        Cookie cookieUsuario = null;
        ArrayList<Producto> listaProductosCarrito = new ArrayList<>();
        ArrayList<Categoria> listaCategorias = new ArrayList<>();
        ArrayList<Categoria> listaCategoriasRelevantes = new ArrayList<>();
        Producto productoCarrito;
        request.getSession().setAttribute("dirImagen", "/IMAGENES/AVATARES/");
        if (request.getParameter("accion") != null) {
            cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");
            cookieUsuario = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieUsuario");
            if (cookieAnonimo != null) {
                if (!cookieAnonimo.getValue().equals("")) {
                    String[] datosCookie = cookieAnonimo.getValue().split("#");
                    for (String i : datosCookie) {
                        String[] productoCookie = i.split("-");
                        productoCarrito = new Producto();
                        productoCarrito.setIdProducto(Short.parseShort(productoCookie[0]));
                        productoCarrito.setCantidad(Short.parseShort(productoCookie[1]));
                        listaProductosCarrito.add(productoCarrito);
                    }
                    if (!listaProductosCarrito.isEmpty() && request.getSession().getAttribute("listaProductos") != null) {
                        ArrayList<Producto> nuevaLista = (ArrayList<Producto>) request.getSession().getAttribute("listaProductos");
                        listaProductosCarrito = UtilidadesProducto.filtrarProductosEnCarrito(nuevaLista, listaProductosCarrito);
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
            if (cookieUsuario != null) {
                if (!cookieUsuario.getValue().equals("")) {
                    String[] datosCookie = cookieAnonimo.getValue().split("#");
                    for (String i : datosCookie) {
                        String[] productoCookie = i.split("-");
                        productoCarrito = new Producto();
                        productoCarrito.setIdProducto(Short.parseShort(productoCookie[0]));
                        productoCarrito.setCantidad(Short.parseShort(productoCookie[1]));
                        listaProductosCarrito.add(productoCarrito);
                    }
                    if (!listaProductosCarrito.isEmpty() && request.getSession().getAttribute("listaProductos") != null) {
                        ArrayList<Producto> nuevaLista = (ArrayList<Producto>) request.getSession().getAttribute("listaProductos");
                        listaProductosCarrito = UtilidadesProducto.filtrarProductosEnCarrito(nuevaLista, listaProductosCarrito);
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
        } else {
            DAOFactory daof = DAOFactory.getDAOFactory(1);
            IProductoDAO ipd = daof.getProductoDAO();

            listaProductos = ipd.listarProductos();
            if (!listaProductos.isEmpty()) {
                request.getSession().setAttribute("listaProductos", listaProductos);
            }

            listaPortatiles = UtilidadesProducto.filtrarPortatiles(listaProductos);
            if (!listaPortatiles.isEmpty()) {
                request.getSession().setAttribute("listaPortatiles", listaPortatiles);
            }

            cookieAnonimo = UtilidadesCookie.comprobarCookieAnonimo(co, "cookieAnonimo");
            if (cookieAnonimo == null) {
                cookieAnonimo = new Cookie("cookieAnonimo", "");
                cookieAnonimo.setMaxAge(60 * 60 * 24 * 2);
                response.addCookie(cookieAnonimo);
            } else {
                if (!cookieAnonimo.getValue().equals("")) {
                    String[] datosCookie = cookieAnonimo.getValue().split("#");
                    for (String i : datosCookie) {
                        String[] productoCookie = i.split("-");
                        productoCarrito = new Producto();
                        productoCarrito.setIdProducto(Short.parseShort(productoCookie[0]));
                        productoCarrito.setCantidad(Short.parseShort(productoCookie[1]));
                        listaProductosCarrito.add(productoCarrito);
                    }
                    if (!listaProductosCarrito.isEmpty()) {
                        listaProductosCarrito = UtilidadesProducto.filtrarProductosEnCarrito(listaProductos, listaProductosCarrito);
                        int cantidadProductosCarrito = UtilidadesProducto.cantidadTotalProductosCarrito(listaProductosCarrito);
                        double totalCarrito = UtilidadesProducto.calcularTotal(listaProductosCarrito);
                        request.getSession().setAttribute("listaProductosCarrito", listaProductosCarrito);
                        request.getSession().setAttribute("cantidadProductosCarrito", cantidadProductosCarrito);
                        request.getSession().setAttribute("totalCarrito", totalCarrito);
                    }
                }

                cookieAnonimo.setMaxAge(60 * 60 * 24 * 2);
                response.addCookie(cookieAnonimo);
            }

            ICategoriaDAO icd = daof.getCategoriaDAO();
            listaCategorias = icd.listarCategorias();
            if (!listaCategorias.isEmpty()) {
                request.getSession().setAttribute("listaCategorias", listaCategorias);
            }

            listaCategoriasRelevantes = UtilidadesCategoria.filtrarCategoriasRelevantes(listaCategorias);
            if (!listaCategoriasRelevantes.isEmpty()) {
                request.getSession().setAttribute("listaCategoriasRelevantes", listaCategoriasRelevantes);
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
