/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Manuel Guillén Gallardo
 */
public class Usuario implements Serializable{
    
    //private byte idUsuario;
    
    private String nombre;
    private String apellidos;
    private String nif;
    private String email;
    private String telefono;
    private String direccion;
    private char codigoPostal; //perfecto para codigo postal => 65535
    private String localidad;
    private String provincia;
    private String password;
    private Date ultimoAcceso;
    private String avatar;
    //private byte[] imagenAvatar;
    /*
    public byte[] getImagenAvatar() {
        return imagenAvatar;
    }

    public void setImagenAvatar(byte[] imagenAvatar) {
        this.imagenAvatar = imagenAvatar;
    }*/
/*
    public byte getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(byte idUsuario) {
        this.idUsuario = idUsuario;
    }
*/
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public char getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(char codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    
}
