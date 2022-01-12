/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Usuario;
import java.sql.Connection;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public interface IUsuarioDAO {
    
    public Usuario comprobarEmail(Usuario usuario);
    public void closeConnection(Connection conexion);
    public boolean crearUsuario(String sql);
    public short obtenerIdUsuario();
    public boolean actualizarAvatar(Usuario usuario);
    public Usuario comprobarPassword(Usuario usuario);
    public Usuario obtenerUsuario(Usuario usuario);
}
