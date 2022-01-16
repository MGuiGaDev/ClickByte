/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.LineaCesta;
import es.albarregas.beans.Pedido;
import java.util.ArrayList;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public interface ILineaPedidoDAO {
//insertarlinea    
//actualizarlinea
    //eliminarlinea
    public void insertarLineaPedido(LineaPedido lp);
    public LineaPedido getIdLineaPedido(LineaCesta lc);
    public void actualizarCantidadLineaPedido(LineaPedido lp);
    public void eliminarUnaLineaPedido(LineaPedido lp);
    public void eliminarTodasLasLineas(Pedido pedido);
    public ArrayList<LineaCesta> obtenerTodasLasLineas(Pedido pedido);
    public void closeConnection();
}
