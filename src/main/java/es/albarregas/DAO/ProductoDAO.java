/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String consulta = "SELECT p.idProducto, p.IdCategoria, p.nombre, p.descripcion, p.precio, p.marca, p.imagen from productos p inner join categorias c using (IdCategoria);";

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
                producto.setDescripcion(productoRS.getString(4));
                producto.setPrecio(productoRS.getDouble(5));
                producto.setMarca(productoRS.getString(6));
                producto.setDireccionImagen(productoRS.getString(7));
                listadoProductos.add(producto);
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
        return listadoProductos;
    }

    @Override
    public Producto cargarProducto (Producto producto) {
        Producto nuevoProducto = null;
        Connection conexion = null;
        PreparedStatement productoPT = null;
        ResultSet productoRS = null;
        String consulta = "SELECT idProducto, idCategoria, nombre, descripcion, precio, marca, imagen from productos where idProducto =?;";
        try {
            ConnectionFactory.openConnectionMysql();
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
                nuevoProducto.setCantidad((short) 1);
            }
            producto.setCantidad((short) 1);
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
    public ArrayList<Producto> cargarProductosCarrito(List<Producto> listaProductosCarrito) {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        Producto producto = null;
        StringBuilder listaIdProductos = new StringBuilder();
        int count = 0;
        for (Producto p : listaProductosCarrito) {
            count++;
            listaIdProductos.append(p.getIdProducto());
            if (count < listaProductosCarrito.size()) {
                listaIdProductos.append(",");
            }
        }
        Connection conexion = null;
        Statement productosST = null;
        ResultSet productoRS = null;
        String consulta = "SELECT idProducto, IdCategoria, nombre, descripcion, precio, marca, imagen from productos where idProducto in ("+listaIdProductos.toString()+");";
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
                producto.setDescripcion(productoRS.getString(4));
                producto.setPrecio(productoRS.getDouble(5));
                producto.setMarca(productoRS.getString(6));
                producto.setDireccionImagen(productoRS.getString(7));
                //contador + arraylist get index
                for(Producto p : listaProductosCarrito) {
                    if(producto.getIdProducto() == p.getIdProducto()){
                        producto.setCantidad(p.getCantidad());
                    }
                }
                listaProductos.add(producto);
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
        return listaProductos;
    }

    @Override
    public ArrayList<Producto> cargarProductosBuscados(String buscado) {
        ArrayList<Producto> productosBuscados = new ArrayList<>();
        Producto producto = null;
        Connection conexion = null;
        String busquedaPreparada = "'%"+buscado+"%'";
        Statement productosPS = null;
        ResultSet productoRS = null;
        String consulta = "SELECT idProducto, IdCategoria, nombre, descripcion, precio, marca, imagen from productos where descripcion like "+busquedaPreparada+" or nombre like "+busquedaPreparada+";";
        try {
            ConnectionFactory.openConnectionMysql();
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
        Connection conexion = null;
        PreparedStatement productosPS = null;
        ResultSet productoRS = null;
        String consulta = "SELECT idProducto, IdCategoria, nombre, descripcion, precio, marca, imagen from productos where idCategoria = ?;";
        try {
            ConnectionFactory.openConnectionMysql();
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
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
