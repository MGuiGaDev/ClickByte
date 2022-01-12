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
        String consulta = "SELECT email FROM usuarios WHERE email=? AND password=MD5('"+ password +"');";

        try {
            ConnectionFactory.openConnectionMysql();
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
            closeConnection(conexion);
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
            ConnectionFactory.openConnectionMysql();
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
            closeConnection(conexion);
        }
        return nuevoUsuario;
    }

    @Override
    public boolean crearUsuario(String sql) {
        boolean creado = false;
        Connection conexion = null;
        Statement crearUsuario = null;
        try {
            ConnectionFactory.openConnectionMysql();
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
            closeConnection(conexion);
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
            ConnectionFactory.openConnectionMysql();
            conexion = ConnectionFactory.openConnectionMysql();
            sT = conexion.createStatement();
            res = sT.executeQuery(consulta);
            while (res.next()) {
                idUsuario = res.getShort(1);
            }
        } catch (SQLException ex) {

            System.out.println("Fallo en la conexión.");

        } finally {
            closeConnection(conexion);
        }
        return idUsuario;
    }

    @Override
    public boolean actualizarAvatar(Usuario usuario) {
        boolean actualizado = false;
        Connection conexion = null;
        PreparedStatement usuarioAvatar = null;
        try {
            ConnectionFactory.openConnectionMysql();
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
            closeConnection(conexion);
        }
        return actualizado;
    }

    @Override
    public Usuario obtenerUsuario(Usuario usuario) {
        Connection conexion = null;
        PreparedStatement busquedaUsuario = null;
        ResultSet res = null;
        String consulta = "SELECT Password, Nombre, Apellidos, NIF, Telefono, Direccion, CodigoPostal, Localidad, Provincia, UltimoAcceso, avatar from usuarios WHERE email=?;";
        
        if (usuario.getEmail() != null) {
            try {
                ConnectionFactory.openConnectionMysql();
                conexion = ConnectionFactory.openConnectionMysql();
                busquedaUsuario = conexion.prepareStatement(consulta);
                busquedaUsuario.setString(1, usuario.getEmail());
                res = busquedaUsuario.executeQuery(consulta);
                while (res.next()) {
                usuario.setPassword(res.getString(1));
                usuario.setNombre(res.getString(2));
                usuario.setApellidos(res.getString(3));
                usuario.setNif(res.getString(4));
                usuario.setTelefono(res.getString(5));
                usuario.setDireccion(res.getString(6));
                usuario.setCodigoPostal(res.getString(7));
                usuario.setLocalidad(res.getString(8));
                usuario.setProvincia(res.getString(9));
                usuario.setUltimoAcceso(res.getDate(10));
                usuario.setAvatar(res.getString(11));
            }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeConnection(conexion);
            }
        }
        return usuario;
    }
    

    @Override
    public void closeConnection(Connection conexion) {
        ConnectionFactory.closeConnetion(conexion);
    }

    
}
