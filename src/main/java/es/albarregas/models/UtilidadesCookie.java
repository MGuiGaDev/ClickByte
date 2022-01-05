/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.models;
import es.albarregas.beans.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.http.Cookie;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class UtilidadesCookie implements Serializable{

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

    public static Cookie cargarCookie(ArrayList<Producto>listaProductosCarrito, Cookie cookie) {
        StringBuilder listado = new StringBuilder();
        int count = 0;
        for (Producto pro : listaProductosCarrito) {
            count++;
            listado.append(pro.getIdProducto()).append("-").append(pro.getCantidad());
            if(count < listaProductosCarrito.size()){
                listado.append("#");
            }
        }
        cookie.setValue(listado.toString());
        return cookie;
    }
}
