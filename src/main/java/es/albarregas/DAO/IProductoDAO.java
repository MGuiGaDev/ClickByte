/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.ListaCesta;
import es.albarregas.beans.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public interface IProductoDAO {
    public ArrayList <Producto> listarProductos();
    public ArrayList <ListaCesta> cargarProductosCarrito(ArrayList<ListaCesta> listaProductosCesta);
    public void closeConnection();
    public Producto cargarProducto (Producto producto);
    public ArrayList<Producto> cargarProductosBuscados(String buscado);
    public ArrayList<Producto> obtenerProductosPorCategoria(Producto producto); 
}
