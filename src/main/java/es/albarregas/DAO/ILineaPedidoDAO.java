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
 *//**
  * <p>Esta interfaz es operativa cuando un usuario está registrado y logueado.
  * La mayoría de sus métodos son invocados en el Servlet AjaxGestionarCarritoController, debido a que la existencia dinámica de los pedidos (creación, actualización y eliminación)
  * así lo requiere.<p>
  * @author Manuel Guillén Gallardo
  */
public interface ILineaPedidoDAO {
    
    /**
     * <p>Mediante este método insertamos en nuestra base de datos un solo registro en la tabla LineasPedidos.</p>
     * <p>Es invocado en el Servlet AjaxGestionarCarritoController (encargado de la gestión dinámica de la cesta tanto para usuario no registrado, como registrado).</p>
     * @param lp 
     */
    public void insertarLineaPedido(LineaPedido lp);
    /**
     * <p>Mediante este método insertamos varias líneas de pedido en la tabla LineasPedidos.</p>
     * <p>Es invocado en el Servlet CrearCuentaController. Recibe los datos de la cesta de productos (atributo de sesión "listaProductosCesta") configurada como String.</p>
     * <p>Hace posible el paso de los datos de nuestra Cookiea al pedido no finalizado del usuario.</p>
     * @param consulta 
     */
    public void insertarVariasLineasPedido(String consulta);
    /**
     * <p>Mediante este método obtenemos el id de una línea de pedido concreta.</p>
     * <p>Es invocado en el Servlet AjaxGestionarCarritoController.</p>
     * <p>Al insertar una nueva línea de pedido, lo hacemos mediante el paso de los atributos de un objeto LineaCesta a un objeto LineaPedido. 
     * Para hacer posible la recuperación de estos datos en el futuro, cargamos el objeto LineaCesta con el idLineaPedido.</p>
     * @param lc
     * @return 
     */
    public LineaPedido getIdLineaPedido(LineaCesta lc);
    /**
     * <p>Mediante este método hacemos posible la actualización dinámica de la tabla LineaPedido.</p>
     * <p>Es invocado en el Servlet AjaxGestionarCarritoController.</p>
     * @param lp 
     */
    public void actualizarCantidadLineaPedido(LineaPedido lp);
    /**
     * <p>Mediante este método hacemos posible la actualización dinámica de la tabla LineaPedido.</p>
     * <p>Es invocado en el Servlet AjaxGestionarCarritoController.</p>
     * @param lp 
     */
    public void eliminarUnaLineaPedido(LineaPedido lp);
    /**
     * <p>Mediante este método hacemos posible la actualización dinámica de la tabla LineaPedido.</p>
     * <p>Es invocado en el Servlet AjaxGestionarCarritoController.</p>
     * @param pedido 
     */
    public void eliminarTodasLasLineas(Pedido pedido);
    /**
     * <p>Mediante este método hacemos posible la actualización dinámica de la tabla LineaPedido.</p>
     * <p>Es invocado en el Servlet AjaxGestionarCarritoController.</p>
     * @param pedido
     * @return 
     */
    public ArrayList<LineaCesta> obtenerTodasLasLineas(Pedido pedido);
    /**
     * <p>Método para cerrar la conexión con la base de datos.</p>
     */
    public void closeConnection();
}
