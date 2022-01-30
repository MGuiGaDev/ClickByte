/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.LineaCesta;
import es.albarregas.beans.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class ProductoDAO implements IProductoDAO {

    private static Connection conexion = null;
   

    @Override
    public Producto cargarProducto (Producto producto) {
        Producto nuevoProducto = null;
        PreparedStatement productoPT = null;
        ResultSet productoRS = null;
        String consulta = "SELECT idProducto, idCategoria, nombre, descripcion, precio, marca, imagen from productos where idProducto =?;";
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            productoPT = conexion.prepareStatement(consulta);
            productoPT.setShort(1, producto.getIdProducto());
            productoRS = productoPT.executeQuery();
            while (productoRS.next()) {
                nuevoProducto = new Producto();
                nuevoProducto.setIdProducto(productoRS.getShort(1));
                nuevoProducto.setIdCategoria(productoRS.getShort(2));
                nuevoProducto.setNombre(productoRS.getString(3));
                nuevoProducto.setDescripcion(productoRS.getString(4));
                nuevoProducto.setPrecio(productoRS.getDouble(5));
                nuevoProducto.setMarca(productoRS.getString(6));
                nuevoProducto.setDireccionImagen(productoRS.getString(7));
            }
            nuevoProducto.setCantidad((short) 1);
        } catch (SQLException ex) {

            System.out.println("Fallo en la conexión.");

        } finally {
            try {
                productoPT.close();
                productoRS.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return nuevoProducto;
    }
    

    @Override
    public ArrayList<Producto> cargarProductosBuscados(String buscado) {
        ArrayList<Producto> productosBuscados = new ArrayList<>();
        Producto producto = null;
        String busquedaPreparada = "'%"+buscado+"%'";
        Statement productosPS = null;
        ResultSet productoRS = null;
        String consulta = "SELECT idProducto, IdCategoria, nombre, descripcion, precio, marca, imagen from productos where descripcion like "+busquedaPreparada+" or nombre like "+busquedaPreparada+";";
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            productosPS = conexion.createStatement();
            productoRS = productosPS.executeQuery(consulta);
            while (productoRS.next()) {
                producto = new Producto();
                producto.setIdProducto(productoRS.getShort(1));
                producto.setIdCategoria(productoRS.getShort(2));
                producto.setNombre(productoRS.getString(3));
                producto.setDescripcion(productoRS.getString(4));
                producto.setPrecio(productoRS.getDouble(5));
                producto.setMarca(productoRS.getString(6));
                producto.setDireccionImagen(productoRS.getString(7));
                productosBuscados.add(producto);
            }
        } catch (SQLException ex) {

            System.out.println("Fallo en la conexión.");

        } finally {
            try {
                productosPS.close();
                productoRS.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productosBuscados;
    }
    @Override
    public ArrayList<Producto> obtenerProductosPorCategoria(Producto productoBuscado) {
        ArrayList<Producto> listaProductosPorCategoria = new ArrayList<>();
        Producto producto = null;
        PreparedStatement productosPS = null;
        ResultSet productoRS = null;
        String consulta = "SELECT idProducto, IdCategoria, nombre, descripcion, precio, marca, imagen from productos where idCategoria = ?;";
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            productosPS = conexion.prepareStatement(consulta);
            productosPS.setShort(1, productoBuscado.getIdCategoria());
            productoRS = productosPS.executeQuery();
            while (productoRS.next()) {
                producto = new Producto();
                producto.setIdProducto(productoRS.getShort(1));
                producto.setIdCategoria(productoRS.getShort(2));
                producto.setNombre(productoRS.getString(3));
                producto.setDescripcion(productoRS.getString(4));
                producto.setPrecio(productoRS.getDouble(5));
                producto.setMarca(productoRS.getString(6));
                producto.setDireccionImagen(productoRS.getString(7));
                listaProductosPorCategoria.add(producto);
            }
        } catch (SQLException ex) {
            System.out.println("Fallo en la conexión.");

        } finally {
            try {
                productosPS.close();
                productoRS.close();
                closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listaProductosPorCategoria;
    }

    @Override
    public ArrayList<LineaCesta> cargarProductosCarrito(ArrayList<LineaCesta> listaProductosCesta) {
        LineaCesta lineaCesta = null;
        StringBuilder listaIdProductos = new StringBuilder();
        ArrayList<LineaCesta> nuevaListaProductosCesta = new ArrayList<>();
        int count = 0;
        for (LineaCesta lC : listaProductosCesta) {
            count++;
            listaIdProductos.append(lC.getIdProducto());
            if (count < listaProductosCesta.size()) {
                listaIdProductos.append(",");
            }
        }
        
        Statement productosST = null;
        ResultSet productoRS = null;
        String consulta = "SELECT idProducto, nombre, precio, marca, imagen from productos where idProducto in ("+listaIdProductos.toString()+");";
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            productosST = conexion.createStatement();
            productoRS = productosST.executeQuery(consulta);
            while (productoRS.next()) {
                lineaCesta = new LineaCesta();
                lineaCesta.setIdProducto(productoRS.getShort(1));
                lineaCesta.setNombre(productoRS.getString(2));
                lineaCesta.setPrecioUnitario(productoRS.getDouble(3));
                lineaCesta.setMarca(productoRS.getString(4));
                lineaCesta.setNombreImagen(productoRS.getString(5));
                for(LineaCesta lC : listaProductosCesta) {
                    if(lineaCesta.getIdProducto() == lC.getIdProducto()){
                        lineaCesta.setCantidad(lC.getCantidad());
                    }
                }
                nuevaListaProductosCesta.add(lineaCesta);
            }
        } catch (SQLException ex) {

            System.out.println("Fallo en la conexión.");

        } finally {
            try {
                productosST.close();
                productoRS.close();
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return nuevaListaProductosCesta;
    }

    
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
