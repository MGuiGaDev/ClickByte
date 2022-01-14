<%-- 
    Document   : usuario.jsp
    Created on : 08-ene-2022, 0:19:26
    Author     : Manuel Guillén Gallardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ClickByte</title>
        <style><%@include file="../CSS/index.css"%></</style>
        <style><%@include file="../CSS/modales.css"%></</style>
        <style><%@include file="../CSS/vistaProducto.css"%></</style>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="shortcut icon" href="logo_clico.ico" type="image/x-icon" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body id="body">
        <%@include file="../INCLUDES/header.inc"%>
        <main>
            <div class="container__menu">
                <a id="btn__menu" class="btn__menu">
                    <span class="material-icons">menu</span>
                    <span>Todas las categor&iacute;as</span>
                </a>
                <!-- MODAL SIMULADO -->
                <form id="modal__menu" class="modal__menu" action="CategoriaController" method="POST">
                    <ul class="menu__list" id="menu__list">
                        <c:forEach var="categoria" items="${listaCategorias}">
                            <li>
                                <button type="submit" name="categoria" value="${categoria.idCategoria}">${categoria.nombre}</button>   
                            </li>
                        </c:forEach>
                    </ul>
                </form>
                <!-- FIN MODAL -->
            </div>
        </main>
        <%@include file="../INCLUDES/footer.inc"%>
        <script><%@include file="../JAVASCRIPT/header.js"%></script>
        <script><%@include file="../JAVASCRIPT/gestionarCarrito.js"%></script>
    </body>
</html>

