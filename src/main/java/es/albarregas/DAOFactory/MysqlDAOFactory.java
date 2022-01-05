/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAOFactory;

import es.albarregas.DAO.CategoriaDAO;
import es.albarregas.DAO.ICategoriaDAO;
import es.albarregas.DAO.IProductoDAO;
import es.albarregas.DAO.ProductoDAO;
import es.albarregas.DAO.IUsuarioDAO;
import es.albarregas.DAO.UsuarioDAO;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class MysqlDAOFactory extends DAOFactory{

    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO();
    }

    @Override
    public IProductoDAO getProductoDAO() {
        return new ProductoDAO();
    }

    @Override
    public ICategoriaDAO getCategoriaDAO() {
        return new CategoriaDAO();
    }
}
