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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class UsuarioDAO implements IUsuarioDAO {
    
    @Override
    public Usuario comprobarPassword(Usuario usuario) {
        Usuario nuevoUsuario = null;
        Connection conexion = null;
        PreparedStatement usuarioPs = null;
        ResultSet res = null;
        String password = usuario.getPassword();
        String consulta = "SELECT email FROM usuarios WHERE email=? AND password=MD5('" + password + "');";
        
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            usuarioPs = conexion.prepareStatement(consulta);
            usuarioPs.setString(1, usuario.getEmail());
            res = usuarioPs.executeQuery();
            while (res.next()) {
                nuevoUsuario = new Usuario();
                nuevoUsuario.setEmail(res.getString(1));
            }
        } catch (SQLException ex) {
            
            System.out.println("Fallo en la conexión.");
            
        } finally {
            closeConnection();
        }
        return nuevoUsuario;
    }
    
    @Override
    public Usuario comprobarEmail(Usuario usuario) {
        
        Usuario nuevoUsuario = null;
        Connection conexion = null;
        PreparedStatement usuarioPs = null;
        ResultSet res = null;
        String consulta = "SELECT email FROM usuarios WHERE email=?";
        
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            usuarioPs = conexion.prepareStatement(consulta);
            usuarioPs.setString(1, usuario.getEmail());
            res = usuarioPs.executeQuery();
            while (res.next()) {
                nuevoUsuario = new Usuario();
                nuevoUsuario.setEmail(res.getString(1));
            }
        } catch (SQLException ex) {
            
            System.out.println("Fallo en la conexión.");
            
        } finally {
            closeConnection();
        }
        return nuevoUsuario;
    }
    
    @Override
    public boolean crearUsuario(String sql) {
        boolean creado = false;
        Connection conexion = null;
        Statement crearUsuario = null;
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            conexion.setAutoCommit(false);
            crearUsuario = conexion.createStatement();
            crearUsuario.executeUpdate(sql);
            conexion.commit();
            creado = true;
        } catch (SQLException ex) {
            try {
                System.out.println("Fallo en la conexión.");
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        } finally {
            closeConnection();
        }
        return creado;
    }
    
    @Override
    public short obtenerIdUsuario() {
        short idUsuario = 0;
        Connection conexion = null;
        Statement sT = null;
        ResultSet res = null;
        String consulta = "SELECT MAX(idUsuario) FROM usuarios;";
        
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            sT = conexion.createStatement();
            res = sT.executeQuery(consulta);
            while (res.next()) {
                idUsuario = res.getShort(1);
            }
        } catch (SQLException ex) {
            
            System.out.println("Fallo en la conexión.");
            
        } finally {
            closeConnection();
        }
        return idUsuario;
    }
    
    @Override
    public boolean actualizarAvatar(Usuario usuario) {
        boolean actualizado = false;
        Connection conexion = null;
        PreparedStatement usuarioAvatar = null;
        try {
            conexion = ConnectionFactory.openConnectionMysql();
            conexion.setAutoCommit(false);
            usuarioAvatar = conexion.prepareStatement("UPDATE usuarios SET avatar=? WHERE idUsuario=?;");
            usuarioAvatar.setString(1, usuario.getAvatar());
            usuarioAvatar.setShort(2, usuario.getIdUsuario());
            usuarioAvatar.executeUpdate();
            conexion.commit();
            actualizado = true;
        } catch (SQLException ex) {
            try {
                System.out.println("Fallo en la conexión.");
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        } finally {
            closeConnection();
        }
        return actualizado;
    }
    
    @Override
    public Usuario obtenerUsuario(Usuario usuario) {
        Connection conexion = null;
        PreparedStatement busquedaUsuario = null;
        ResultSet res = null;
        String consulta = "SELECT IdUsuario, Password, Nombre, Apellidos, NIF, Telefono, Direccion, CodigoPostal, Localidad, Provincia, UltimoAcceso, avatar from usuarios WHERE email = ?;";
        
        if (usuario.getEmail() != null) {
            try {
                conexion = ConnectionFactory.openConnectionMysql();
                busquedaUsuario = conexion.prepareStatement(consulta);
                busquedaUsuario.setString(1, usuario.getEmail());
                res = busquedaUsuario.executeQuery();
                while (res.next()) {
                    usuario.setIdUsuario(res.getShort(1));
                    usuario.setPassword(res.getString(2));
                    usuario.setNombre(res.getString(3));
                    usuario.setApellidos(res.getString(4));
                    usuario.setNif(res.getString(5));
                    usuario.setTelefono(res.getString(6));
                    usuario.setDireccion(res.getString(7));
                    usuario.setCodigoPostal(res.getString(8));
                    usuario.setLocalidad(res.getString(9));
                    usuario.setProvincia(res.getString(10));
                    usuario.setUltimoAcceso(res.getDate(11));
                    usuario.setAvatar(res.getString(12));
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeConnection();
            }
        }
        return usuario;
    }
    
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
