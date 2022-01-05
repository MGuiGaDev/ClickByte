/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.IUsuarioDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Usuario;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Manuel Guillén Gallardo
 */
@MultipartConfig
@WebServlet(name = "CrearCuenta", urlPatterns = {"/CrearCuenta"})

public class CrearCuenta extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "FrontController";

        Usuario usuario = new Usuario();
        //DAOFactory daof = DAOFactory.getDAOFactory(1);
        //IUsuarioDAO udao = daof.getUsuarioDAO();
        Boolean error = false;

        // Obtenemos el directorio donde se almacenarán las imágenes
        //String dirImagen = "C:\\Users\\manue\\Desktop\\DAW2_APUNTES\\WORKSPACE_NETBEANS\\GGMTiendaVirtual\\src\\main\\webapp\\IMAGENES\\USER_AVATAR\\";
        String dirImagen = request.getServletContext().getRealPath("/IMAGENES/AVATARES/");
        // Declaramos otra variable booleana que pondremos a true en el caso de que el usuario haya pulsado Cancelar
        Boolean cancelar = false;
        // Declaramos la variable donde se almacenará el nombre del fichero
        StringBuilder nombreFichero = new StringBuilder();
        // Declaramos la variable necesaria para crear un objeto File
        String filePath = null;
        // Declaramos la factoría

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        // Declaramos la lista de objetos FileItem
        List<FileItem> items = null;

        try {

            items = upload.parseRequest(request);
            // Recorremos la lista con ayuda de iterator
            Iterator<FileItem> it = items.iterator();
            while (it.hasNext()) {
                FileItem uploaded = it.next();
                if (uploaded.isFormField()) {
                    String key = uploaded.getFieldName();
                    String valor = "";
                    valor = uploaded.getString("UTF-8");
                    switch (key) {
                        case "boton":
                            if (valor.equals("Cancelar")) {
                                cancelar = true;
                            }
                            break;
                        case "nombre":

                        case "apellidos":

                        case "dni":
                        
                        case "telefono":
                        
                        case "direccion":
                            
                        case "cp":
                        
                        case "localidad":
                        
                        case "provincia":
                        
                        case "email":
                        
                        case "password":
                            if (valor.equals("")) {
                                error = true;
                            }
                    }
                } else if (uploaded.getName().equals("")) {
                    error = true;
                }
            }
            if (!cancelar) {
                // En el caso de que no se haya pulsado Cancelar comprobamos que no haya algún campo vacío
                if (!error) {
                    
                    Iterator<FileItem> itDos = items.iterator();

                    while (itDos.hasNext()) {
                        // Obtenemos un objeto FileItem
                        FileItem uploaded = itDos.next();

                        // En el caso de que no se un control normal
                        if (!uploaded.isFormField()) {
                            // Comprobamos que haya fichero
                            // Comprobamos que el fichero tenga la extensión permitida
                            if (uploaded.getContentType().equals("image/png") || uploaded.getContentType().equals("image/jpeg")) {
                                System.out.println("tipo img.");
                                // Comprobamos que el fichero no sea mayor de lo permitido
                                if (uploaded.getSize() < 102400) {
                                    System.out.println("tamaño img");
                                    // Obtenemos la extensión
                                    String extension = ".jpg";
                                    if (uploaded.getContentType().equals("image/png")) {
                                        extension = ".png";
                                    }
                                    // Obtenemos el nombre del fichero como AvatarN + identificativo del usuario
                                    nombreFichero.append("AvatarN").append(extension);
                                    filePath = dirImagen + nombreFichero.toString();
                                    System.out.println(dirImagen);
                                    System.out.println(filePath);
                                    // Obtenemos el objeto File a partir de la variable anterior
                                    File fichero = new File(filePath);
                                    request.setAttribute("error", "guarda");
                                    try {
                                        // Escribimos el fichero en el servidor
                                        uploaded.write(fichero);
                                    } catch (Exception ex) {
                                        request.setAttribute("error", "No se ha podido almacenar la imagen en el servidor");
                                        error = true;
                                    }
                                } else {
                                    request.setAttribute("error", "La imagen sobrepasa el tamaño permitido");
                                    error = true;
                                }
                            } else {
                                request.setAttribute("error", "La imagen no tiene el formato adecuado");
                                error = true;
                            }
                        }
                    }
                }//fin error
                
                
                /*if (!error) {
                DateConverter converter = new DateConverter();
                converter.setPattern("yyyy-MM-dd");
                ConvertUtils.register(converter, Date.class);
                
                try {
                BeanUtils.populate(usuario, request.getParameterMap());
                boolean a = usuario.getImagenAvatar()!=null? true: false;
                request.setAttribute("error", a + " " + usuario.getDireccion());
                
                
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                StringBuilder nombreFichero = new StringBuilder();
                File fileR = (File) upload.parseRequest(request);
                nombreFichero.append("AvatarN").append(String.valueOf(usuario.getNombre())).append(".jpg");
                String filePath = dirImagen + nombreFichero.toString();
                File fichero = new File(filePath);
                try {
                // Escribimos el fichero en el servidor
                fileR.write(fichero);
                } catch (Exception ex) {

                }
                } catch (IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
                error = true;
                }
                } else {
                request.setAttribute("error", "Todos los campos son obligatorios");
                
                
                }*/
            } //FIN CANCELAR
        } catch (FileUploadException ex) {
            Logger.getLogger(CrearCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
