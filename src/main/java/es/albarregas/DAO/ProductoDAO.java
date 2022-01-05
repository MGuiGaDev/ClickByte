/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class ProductoDAO implements IProductoDAO {

    @Override
    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> listadoProductos = new ArrayList<>();
        Producto producto = null;
        Connection conexion = null;
        Statement productosST = null;
        ResultSet productoRS = null;
        String consulta = "SELECT p.idProducto, p.IdCategoria, p.nombre, c.nombre, p.descripcion, p.precio, p.marca, p.imagen from productos p inner join categorias c using (IdCategoria);";

        try {
            ConnectionFactory.openConnectionMysql();
            conexion = ConnectionFactory.openConnectionMysql();
            productosST = conexion.createStatement();
            productoRS = productosST.executeQuery(consulta);
            while (productoRS.next()) {
                producto = new Producto();
                producto.setIdProducto(productoRS.getShort(1));
                producto.setIdCategoria(productoRS.getShort(2));
                producto.setNombre(productoRS.getString(3));
                producto.setNombreCategoria(productoRS.getString(4));
                producto.setDescripcion(productoRS.getString(5));
                producto.setPrecio(productoRS.getDouble(6));
                producto.setMarca(productoRS.getString(7));
                producto.setDireccionImagen(productoRS.getString(8));
                listadoProductos.add(producto);
            }
        } catch (SQLException ex) {

            System.out.println("Fallo en la conexión.");

        } finally {
            closeConnection(conexion);
        }
        return listadoProductos;
    }

    @Override
    public void closeConnection(Connection conexion) {
        ConnectionFactory.closeConnetion(conexion);
    }

}
