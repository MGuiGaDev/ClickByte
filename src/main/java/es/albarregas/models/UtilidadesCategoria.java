/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.models;

import es.albarregas.beans.Categoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class UtilidadesCategoria implements Serializable{

    public static ArrayList<Categoria> filtrarCategoriasRelevantes(List<Categoria> listaCategorias) {
        ArrayList<Categoria> listaCategoriasRelevantes = new ArrayList<>();
        if (!listaCategorias.isEmpty()) {
            for (Categoria c : listaCategorias) {
                if (!c.getDireccionImagen().equals("default.jpg")) {
                    listaCategoriasRelevantes.add(c);
                }
            }
        }
        return listaCategoriasRelevantes;
    }
    public static String obtenerNombreCategoria(short idCategoria, List<Categoria>listaCategorias) {
        String nombreCategoria = null;
        for(Categoria cat : listaCategorias) {
            if(cat.getIdCategoria()==idCategoria) {
                nombreCategoria = cat.getNombre();
            }
        }
        return nombreCategoria;
    }

}
