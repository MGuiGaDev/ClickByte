/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class LineaPedido implements Serializable{

    private short idLinea;
    private short idProducto;
    private short idPedido;
    private short cantidad;
    private short orden;

    public short getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(short idLinea) {
        this.idLinea = idLinea;
    }

    public short getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(short idProducto) {
        this.idProducto = idProducto;
    }

    public short getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(short idPedido) {
        this.idPedido = idPedido;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    public short getOrden() {
        return orden;
    }

    public void setOrden(short orden) {
        this.orden = orden;
    }

}
