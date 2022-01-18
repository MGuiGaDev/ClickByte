/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Pedido;
import es.albarregas.beans.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public interface IPedidoDAO {

    /**
     * <p>
     * Mediante este método creamos un pedido no finalizado.</p>
     * <p>
     * Es invocado en los Servlet AjaxGestionarCarritoController y
     * CrearCuentaController.</p>
     * <p>
     * En el primero de los Servlet crea un pedido no finalizado para un usuario
     * logueado y, en el segundo, crea un pedido no finalizado para un usuario
     * que acaba de crear una cuenta y contaba con una cesta</p>
     *
     * @param pedido
     * @return
     */
    public boolean crearPedido(Pedido pedido);

    /**
     * <p>
     * Método para obtener un pedido no finalizado</p>
     * <p>
     * Es invocado en los Servlets (1)AjaxGestionarCarritoController,
     * (2)FinalizarCarritoController e (3)IniciarSesiónController</p>
     * <ol>
     * <li>Actualizamos el carrito no finalizado cada vez que el usuario realiza
     * un cambio.</li>
     * <li>Obtenemos los datos del pedido no finalizado antes de actualizar su
     * estado a finalizado.</li>
     * <li>Recuperamos, si existe, un pedido no finalizado por el usuario cuando
     * inicia sesión.</li>
     * </ol>
     *
     * @param pedido
     * @return
     */
    public Pedido obtenerPedidoNoFinalizado(Pedido pedido);

    /**
     * <p>
     * Método que nos permite eliminar un pedido no finalizado cuando el usuario
     * así lo precisa.</p>
     * <p>
     * Es invocado en el Servlets AjaxGestionarCarritoController.</p>
     *
     * @param pedido
     */
    public void eliminarPedido(Pedido pedido);

    /**
     * <p>
     * Método que nos permite actualizar un pedido no finalizado cuando el
     * usuario así lo precisa.</p>
     * <p>
     * Es invocado en el Servlets AjaxGestionarCarritoController.</p>
     *
     * @param pedido
     */
    public void actualizarPedido(Pedido pedido);

    /**
     * <p>
     * Método que nos permite actualizar el estado de un pedido, pasando de no finalizado a finalizado.</p>
     * <p>
     * Es invocado en el Servlets FinalizarCarritoController.</p>
     * @param pedido
     */
    public void cambiarEstadoPedido(Pedido pedido);

    /**
     * <p>Método que nos permite recuperar la información de todos los pedidos finalizados realizados por un usuario concreto.</p>
     * <p>Es utilizado en el Servlet OpcionesCuentaUsuarioController.</p>
     * @param usuario
     * @return 
     */
    public ArrayList<Pedido> obtenerTodosLosPedidos(Usuario usuario);

    public void closeConnection();
}
