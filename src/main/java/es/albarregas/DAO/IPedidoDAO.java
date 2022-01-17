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
    public boolean crearPedido(Pedido pedido);
    public Pedido obtenerPedidoNoFinalizado(Pedido pedido);
    public void eliminarPedido(Pedido pedido);
    public void actualizarPedido(Pedido pedido);
    public void cambiarEstadoPedido(Pedido pedido);
    public ArrayList<Pedido> obtenerTodosLosPedidos(Usuario usuario);
    public void closeConnection();
}
