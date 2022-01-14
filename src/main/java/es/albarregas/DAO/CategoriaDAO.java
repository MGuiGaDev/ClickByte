/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Categoria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class CategoriaDAO implements ICategoriaDAO{
    private static Connection conexion = null;
    @Override
    public ArrayList<Categoria> listarCategorias() {
        ArrayList<Categoria> listadoCategorias = new ArrayList<>();
        Categoria categoria = null;
        Statement productosST = null;
        ResultSet productoRS = null;
        String consulta = "SELECT IdCategoria, c.nombre, c.imagen  from categorias c inner join productos using (IdCategoria) group by IdCategoria order by nombre;";

        try {
            conexion = ConnectionFactory.openConnectionMysql();
            productosST = conexion.createStatement();
            productoRS = productosST.executeQuery(consulta);
            while (productoRS.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(productoRS.getShort(1));
                categoria.setNombre(productoRS.getString(2));
                categoria.setDireccionImagen(productoRS.getString(3));
                listadoCategorias.add(categoria);
            }
        } catch (SQLException ex) {

            System.out.println("Fallo en la conexión.");

        } finally {
            closeConnection();
        }
        return listadoCategorias;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
