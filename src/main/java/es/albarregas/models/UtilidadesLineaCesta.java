/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.models;

import es.albarregas.beans.LineaCesta;
import es.albarregas.beans.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class UtilidadesLineaCesta{
    public static LineaCesta cargarListaCestaConProducto(Producto producto){
        LineaCesta lC = null;
        if(producto!=null) {
            lC = new LineaCesta();
            lC.setIdProducto(producto.getIdProducto());
            lC.setNombre(producto.getNombre());
            lC.setMarca(producto.getMarca());
            lC.setNombreImagen(producto.getDireccionImagen());
            lC.setPrecioUnitario(producto.getPrecio());
            lC.setCantidad(producto.getCantidad());
        }
        return lC;
    }
    public static int cantidadTotalProductosCesta(List<LineaCesta> listaProductosCesta) {
        int cantidadTotal = 0;
        if (!listaProductosCesta.isEmpty()) {
            for (LineaCesta lC : listaProductosCesta) {
                cantidadTotal += lC.getCantidad();
            }
        }
        return cantidadTotal;
    }
    
    public static double calcularTotal(List<LineaCesta> listaProductosCesta) {
        double total = 0;
        if (!listaProductosCesta.isEmpty()) {
            for (LineaCesta lC : listaProductosCesta) {
                total += lC.getCantidad() * lC.getPrecioUnitario();
            }
        }
        return total;
    }
    public static ArrayList<LineaCesta> cargarIdsListaProductosCestaUsuario(ArrayList<LineaCesta> listaCestaProductos,LineaCesta listaCesta) {
        if(!listaCestaProductos.isEmpty()){
            for(LineaCesta lc : listaCestaProductos) {
                if(lc.getIdProducto()== listaCesta.getIdProducto()){
                    lc.setIdLinea(listaCesta.getIdLinea());
                    lc.setIdPedido(listaCesta.getIdPedido());
                }
            }
        }
        return listaCestaProductos;
    }
    public static String configurarConsultaInsertarVariasLineas(ArrayList<LineaCesta> listaProductos){
        StringBuilder consulta = new StringBuilder();
        int count = 0;
        for(LineaCesta lc : listaProductos) {
            count++;
            consulta.append("(").append(lc.getIdProducto()).append(",").append(lc.getIdPedido()).append(",").append(lc.getCantidad()).append(",").append(lc.getOrden()).append(")");
            if (count < listaProductos.size()) {
                    consulta.append(",");
            } 
            
        }
        
        return consulta.toString();
    }
}
