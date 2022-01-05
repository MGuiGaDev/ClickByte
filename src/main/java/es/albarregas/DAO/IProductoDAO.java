/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Producto;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public interface IProductoDAO {
    public ArrayList <Producto> listarProductos();
    public void closeConnection(Connection conexion);
}
