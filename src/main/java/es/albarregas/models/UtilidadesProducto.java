/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.models;

import es.albarregas.beans.Producto;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class UtilidadesProducto implements Serializable {

    public static ArrayList<Producto> filtrarPortatiles(ArrayList<Producto> listaProductos) {
        ArrayList<Producto> listaPortatiles = new ArrayList<>();
        if (!listaProductos.isEmpty()) {
            for (Producto p : listaProductos) {
                if (p.getDireccionImagen().contains("portatiles")) {
                    listaPortatiles.add(p);
                }
            }
        }
        return listaPortatiles;
    }

    public static ArrayList<Producto> filtrarProductosPorIdCategoria(ArrayList<Producto> listaProductos, String idCategoria) {
        ArrayList<Producto> listaProductosPorCategoria = new ArrayList<>();
        if (!listaProductos.isEmpty()) {
            for (Producto p : listaProductos) {
                if (idCategoria.equals(String.valueOf(p.getIdCategoria()))) {
                    listaProductosPorCategoria.add(p);
                }
            }
        }
        return listaProductosPorCategoria;
    }

    public static Producto verDetalleProducto(ArrayList<Producto> listadoProductos, String idProducto) {
        Producto producto = new Producto();
        if (!listadoProductos.isEmpty() && idProducto != null) {
            for (Producto p : listadoProductos) {
                if (idProducto.equals(String.valueOf(p.getIdProducto()))) {
                    producto = p;
                }
            }
        }
        return producto;
    }

    public static ArrayList<Producto> filtrarProductosEnCarrito(ArrayList<Producto> listaProductos, ArrayList<Producto> listaProductosCarrito) {
        ArrayList<Producto> nuevaListaProductosCarrito = new ArrayList<>();
        ArrayList<Short> idProductosCarrito = new ArrayList<>();
        short orden = 1;
        if (!listaProductos.isEmpty() && !listaProductosCarrito.isEmpty()) {
            for (Producto p : listaProductosCarrito) {
                idProductosCarrito.add(p.getIdProducto());
            }
            for (Producto p : listaProductos) {
                if (idProductosCarrito.contains(p.getIdProducto())) {
                    int indexPro = idProductosCarrito.indexOf(p.getIdProducto());
                    p.setCantidad(listaProductosCarrito.get(indexPro).getCantidad());
                    p.setOrden(orden);
                    nuevaListaProductosCarrito.add(p);
                    orden++;
                }
            }
        }
        return nuevaListaProductosCarrito;
    }

    public static int cantidadTotalProductosCarrito(ArrayList<Producto> listaProductosCarrito) {
        int cantidadTotal = 0;
        for (Producto p : listaProductosCarrito) {
            cantidadTotal += p.getCantidad();
        }
        return cantidadTotal;
    }

    public static Producto obtenerProductoParaCarrito(ArrayList<Producto> listaProductos, short idProducto) {
        Producto producto = new Producto();
        if (!listaProductos.isEmpty()) {
            for (Producto p : listaProductos) {
                if (p.getIdProducto() == idProducto) {
                    p.setCantidad((short) 1);
                    producto = p;
                }
            }
        }
        return producto;
    }

    public static double calcularTotal(ArrayList<Producto> listaProductosCarrito) {
        double total = 0;
        if (!listaProductosCarrito.isEmpty()) {
            for (Producto p : listaProductosCarrito) {
                total += p.getCantidad() * p.getPrecio();
            }
        }
        return total;
    }
}
