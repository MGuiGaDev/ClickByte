/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Usuario;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public interface IUsuarioDAO {

    /**
     * <p>
     * Método utilizado para comprobar el Email introducido por un usuario.</p>
     * <p>
     * Es utilizado en el Servlet AjaxValidarCuentaController.</p>
     *
     * @param usuario
     * @return
     */
    public Usuario comprobarEmail(Usuario usuario);

    /**
     * <p>
     * Método utilizado para crear una cuenta de usuario.</p>
     * <p>
     * Invocado en CrearCuentaController.</p>
     *
     * @param sql
     * @return
     */
    public boolean crearUsuario(String sql);

    /**
     * <p>
     * Método utilizado para obtener el id de un usuario que acaba de crear su
     * cuenta.</p>
     * <p>
     * Invocado en el Servlet CrearCuentaController.</p>
     *
     * @return
     */
    public short obtenerIdUsuario();

    /**
     * <p>
     * Método utilizado para regitrar el avatar de un usuario en caso de haber sido introducido.</p>
     * <p>
     * Invocado en el Servlet CrearCuentaController.</p>
     * @param usuario
     * @return
     */
    public boolean actualizarAvatar(Usuario usuario);

    /**
     * <p>
     * Método utilizado para comprobar la contraseña de un usuario que acaba de crear su
     * cuenta.</p>
     * <p>
     * Invocado en el Servlet AjaxValidarCuentaController.</p>
     * @param usuario
     * @return
     */
    public Usuario comprobarPassword(Usuario usuario);

    /**
     * <p>
     * Método utilizado para recoger todos datos que existen en la base de datos de un usuario concreto.</p>
     * <p>
     * Invocado en el Servlet IniciarSesiónController.</p>
     * @param usuario
     * @return
     */
    public Usuario obtenerUsuario(Usuario usuario);

    /**
     *
     */
    public void closeConnection();
}
