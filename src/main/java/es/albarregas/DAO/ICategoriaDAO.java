/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Categoria;
import java.util.ArrayList;

/**
 *
 * @author Manuel Guillén Gallardo
 */
/**
 * Esta interfaz es responsable de la aportación de los datos acerca de las categorías, presentes en todas las vistas.
 * @author Manuel Guillén Gallardo
 */
public interface ICategoriaDAO {
    /**
     * <p>Método para obtener el listado completo de categorías de productos</p>
     * <p>Es invocado en el Servlet ClickByteListener</p>
     * @return 
     */
    public ArrayList <Categoria> listarCategorias();
    public void closeConnection();
}
