/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.eventos;

import es.albarregas.DAO.ICategoriaDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Categoria;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
/**
 *
 * @author Manuel Guillén Gallardo
 */
public class ClickByteListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext contexto = sce.getServletContext();
        ArrayList<Categoria> listaCategorias = new ArrayList<>();
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        ICategoriaDAO icd = daof.getCategoriaDAO();
        listaCategorias = icd.listarCategorias();
        synchronized (contexto) {
            contexto.setAttribute("listaCategorias", listaCategorias);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext contexto = sce.getServletContext();
        contexto.removeAttribute("listaCategorias");
    }

}
