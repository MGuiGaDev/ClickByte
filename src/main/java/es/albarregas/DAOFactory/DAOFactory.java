/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAOFactory;

import es.albarregas.DAO.ICategoriaDAO;
import es.albarregas.DAO.IProductoDAO;
import es.albarregas.DAO.IUsuarioDAO;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public abstract class DAOFactory {
    
    public static final int MYSQL = 1;
    //public static final int DERBY = 2; SE SUPONE QUE AQUI TRABAJAREMOS CON FILE
    public abstract IUsuarioDAO getUsuarioDAO();
    public abstract IProductoDAO getProductoDAO();
    public abstract ICategoriaDAO getCategoriaDAO();
    
    public static DAOFactory getDAOFactory (int tipo) {
        DAOFactory daof =null;
        //De momento esto lo trampeo porque no se si buscare files por aqui
        tipo = 1;
        switch(tipo) {
            case 1:
                daof = new MysqlDAOFactory();
                break;
            case 2:
                break;
        }
        return daof;
    }
}
