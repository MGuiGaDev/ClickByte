/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.LineaCesta;
import es.albarregas.beans.Producto;
import java.util.ArrayList;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public interface IProductoDAO {

    /**
     * <p>
     * Este método nos permite cargar los datos de los productos elegidos por el
     * usuario en la cesta.</p>
     * <p>
     * Es utilizado en el Servlet FrontController.</p> 
     * <p>
     * También es utilizado 
     * @param listaProductosCesta
     * @return
     */
    public ArrayList<LineaCesta> cargarProductosCarrito(ArrayList<LineaCesta> listaProductosCesta);

    /**
     *<p>Este método nos permite cargar los datos de un producto seleccionado por el usuario (logueado, o no) para su visualización en búsqueda, detalle o en cesta.</p>
     *<p>Es utilizado en los Servlet: AjaxGestionarCarritoController, BusquedaController, FrontController y ProductoController.</p>
     * @param producto
     * @return
     */
    public Producto cargarProducto(Producto producto);

    /**
     *<p>Método para cargar los datos de los productos devueltos a una búsqueda realizada por el usuario.</p>
     * <p>Es utilizado en el Servlet BusquedaController.</p>
     * @param buscado
     * @return
     */
    public ArrayList<Producto> cargarProductosBuscados(String buscado);

    /**
     *<p>Método que nos permite devolver los productos relacionados con una determinada categoría.</p>
     *<p>Es usado en el Servlet CategoriaController.</p>
     * @param producto
     * @return
     */
    public ArrayList<Producto> obtenerProductosPorCategoria(Producto producto);

    /**
     * 
     */
    public void closeConnection();
}
