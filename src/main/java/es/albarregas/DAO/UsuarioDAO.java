/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class UsuarioDAO implements IUsuarioDAO{


    @Override
    public Usuario comprobarEmail(String email) {
        
        Usuario usuario = null;
        Connection conexion = null;
        PreparedStatement usuarioPs = null;
        ResultSet res = null;
        String consulta = "SELECT email FROM usuarios WHERE email=?";
        
        try {
            ConnectionFactory.openConnectionMysql();
            conexion = ConnectionFactory.openConnectionMysql();
            usuarioPs = conexion.prepareStatement(consulta);
            usuarioPs.setString(1, email);
            res = usuarioPs.executeQuery();
            while (res.next()) {
                usuario = new Usuario();
                usuario.setEmail(res.getString(1));
            }
        } catch (SQLException ex) {

            System.out.println("Fallo en la conexión.");

        } finally {
            closeConnection(conexion);
        }
        return usuario;
    }
    
    /*public Usuario crearUsuario(Usuario nuevoUsuario) {
        
    }*/

    @Override
    public void closeConnection(Connection conexion) {
         ConnectionFactory.closeConnetion(conexion);
    }
    
}
