/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class Pedido {
    
    private short idPedido;
    private Date fecha;
    private enum estado {C, F};
    private double importe;
    private double iva;
    private ArrayList<LineaPedido> lineaPedido;

    public short getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(short idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public ArrayList<LineaPedido> getLineaPedido() {
        return lineaPedido;
    }

    public void setLineaPedido(ArrayList<LineaPedido> lineaPedido) {
        this.lineaPedido = lineaPedido;
    }
    
    
}
