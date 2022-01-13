/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.models;

import es.albarregas.beans.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class UtilidadesCookie implements Serializable {

    public static Cookie comprobarCookieAnonimo(Cookie[] co, String nombreCookie) {

        Cookie cookieEncontrada = null;

        if (co != null) {
            for (Cookie c : co) {
                if (c.getName().equals(nombreCookie)) {
                    cookieEncontrada = c;
                    break;
                }
            }
        }

        return cookieEncontrada;
    }

    public static Cookie cargarCookie(List<Producto> listaProductosCarrito) {
        StringBuilder listado = new StringBuilder();
        Cookie cookie = new Cookie("cookieAnonimo", "");
        int count = 0;
        for (Producto pro : listaProductosCarrito) {
            count++;
            listado.append(pro.getIdProducto()).append("-").append(pro.getCantidad());
            if (count < listaProductosCarrito.size()) {
                listado.append("#");
            }
        }
        cookie.setValue(listado.toString());
        return cookie;
    }

    public static ArrayList<Producto> cargarListaProductos(Cookie coo) {
        Producto productoCarrito = null;
        ArrayList<Producto> listaProductosCarrito = new ArrayList<>();
        String[] datosCookie = coo.getValue().split("#");
        for (String i : datosCookie) {
            String[] productoCookie = i.split("-");
            productoCarrito = new Producto();
            productoCarrito.setIdProducto(Short.parseShort(productoCookie[0]));
            productoCarrito.setCantidad(Short.parseShort(productoCookie[1]));
            listaProductosCarrito.add(productoCarrito);
        }
        return listaProductosCarrito;
    }
    public static String obtenerIdProductosCarrito(Cookie coo) {
        String[] datosCookie = coo.getValue().split("#");
        StringBuilder listaIdProductos = new StringBuilder();
        int count = 0;
        for(String i : datosCookie) {
            count++;
            String[] productoCookie = i.split("-");
            listaIdProductos.append(productoCookie[0]);
            if (count < datosCookie.length) {
                listaIdProductos.append(",");
            }
        }
        return listaIdProductos.toString();
    }
}
