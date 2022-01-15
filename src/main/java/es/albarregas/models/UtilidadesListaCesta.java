/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.models;

import es.albarregas.beans.ListaCesta;
import es.albarregas.beans.Producto;
import java.util.List;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class UtilidadesListaCesta {
    public static ListaCesta cargarListaCestaConProducto(Producto producto){
        ListaCesta lC = null;
        if(producto!=null) {
            lC = new ListaCesta();
            lC.setIdProducto(producto.getIdProducto());
            lC.setNombre(producto.getNombre());
            lC.setMarca(producto.getMarca());
            lC.setNombreImagen(producto.getDireccionImagen());
            lC.setPrecioUnitario(producto.getPrecio());
            lC.setCantidad(producto.getCantidad());
        }
        return lC;
    }
    public static int cantidadTotalProductosCesta(List<ListaCesta> listaProductosCesta) {
        int cantidadTotal = 0;
        if (!listaProductosCesta.isEmpty()) {
            for (ListaCesta lC : listaProductosCesta) {
                cantidadTotal += lC.getCantidad();
            }
        }
        return cantidadTotal;
    }
    
    public static double calcularTotal(List<ListaCesta> listaProductosCesta) {
        double total = 0;
        if (!listaProductosCesta.isEmpty()) {
            for (ListaCesta lC : listaProductosCesta) {
                total += lC.getCantidad() * lC.getPrecioUnitario();
            }
        }
        return total;
    }
}
