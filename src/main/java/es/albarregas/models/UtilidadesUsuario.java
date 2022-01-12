/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.models;

import es.albarregas.beans.Usuario;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.Part;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class UtilidadesUsuario {

    public static String subirAvatar(Part filePart, String dirImagen, Usuario usuario) throws IOException {
        String resultado = "";
        StringBuilder nombreFichero = new StringBuilder();
        String filePath = null;
        if (filePart.getName().length() != 0) {
            // Comprobamos que sea del formato adecuado
            if (filePart.getContentType().equals("image/png") || filePart.getContentType().equals("image/jpeg")) {
                // Comprobamos que tenga el tamaño permitido
                if (filePart.getSize() < 102400) {
                    // Obtenemos la extensión
                    String extension = ".jpg";
                    if (filePart.getContentType().equals("image/png")) {
                        extension = ".png";
                    }
                    nombreFichero.append("AvatarUsuario").append(usuario.getIdUsuario()).append(extension);
                    filePath = dirImagen + nombreFichero.toString();
                    filePart.write(filePath);
                    resultado = nombreFichero.toString();

                } else {
                    resultado = "La imagen sobrepasa el tamaño permitido";
                }
            } else {
                resultado = "La imagen no tiene el formato adecuado";
            }
        } else {
            resultado = "El campo avatar es obligatorio";
        }
        return resultado;
    }

    public static String crearSentenciaSQL(Usuario usuario) {
        String sql = "";
        StringBuilder datosUsuario = new StringBuilder();
        if (usuario != null) {
            sql = "INSERT INTO usuarios (Nombre, Apellidos, Nif, Telefono, Direccion, CodigoPostal, Localidad, Provincia, Email, Password, UltimoAcceso) VALUES ";
            datosUsuario.append("INSERT INTO usuarios (Nombre, Apellidos, Nif, Telefono, Direccion, CodigoPostal, Localidad, Provincia, Email, Password, UltimoAcceso) VALUES (\"")
                    .append(usuario.getNombre())
                    .append("\", \"").append(usuario.getApellidos())
                    .append("\", \"").append(usuario.getNif())
                    .append("\", \"").append(usuario.getTelefono())
                    .append("\", \"").append(usuario.getDireccion())
                    .append("\", \"").append(usuario.getCodigoPostal())
                    .append("\", \"").append(usuario.getLocalidad())
                    .append("\", \"").append(usuario.getProvincia())
                    .append("\", \"").append(usuario.getEmail())
                    .append("\", MD5(\"").append(usuario.getPassword())
                    .append("\"), now());");
            sql = datosUsuario.toString();
        }
        return sql;
    }

}
