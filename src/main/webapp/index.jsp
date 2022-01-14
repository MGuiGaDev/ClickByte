<%-- 
    Document   : index
    Created on : 28-dic-2021, 11:54:32
    Author     : Manuel Guillén Gallardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="dirImagen" value="${sessionScope.dirImagen}" scope="application"/>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ClickByte</title>
        <style><%@include file="/CSS/index.css"%></</style>
        <style><%@include file="/CSS/modales.css"%></</style>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="shortcut icon" href="logo_clico.ico" type="image/x-icon" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body id="body">
        <%@include file="INCLUDES/header.inc"%>
        <main>
            <section class="container__menu">
                <a id="btn__menu" class="btn__menu">
                    <span class="material-icons">menu</span>
                    <span>Todas las categor&iacute;as</span>
                </a>
                <ul class="nav__social">
                    <li><a><i class="fab fa-facebook"></i></a></li>
                    <li><a><i class="fab fa-twitter"></i></a></li>
                    <li><a><i class="fab fa-whatsapp"></i></a></li>
                    <li><a><i class="fab fa-instagram"></i></a></li>
                    <li><a><i class="fab fa-twitch"></i></a></li>
                    <li><a><i class="fab fa-youtube-square"></i></a></li>
                </ul>
                <!-- MODAL SIMULADO -->
                <form id="modal__menu" class="modal__menu" action="CategoriaController" method="POST">
                    <ul class="menu__list" id="menu__list">
                        <c:forEach var="categoria" items="${applicationScope.listaCategorias}">
                            <li>
                                <button type="submit" name="categoria" value="${categoria.idCategoria}"><c:out value="${categoria.nombre}"/></button>   
                            </li>
                        </c:forEach>
                    </ul>
                </form>
                <!-- FIN MODAL -->
            </section>
            <section class="container__video__card">
                <div class="video__card">
                    <p>Infórmate sobre nuestro SET-UP para GAMERS.</p>
                    <a class="video__card__btn" href="#"><span>+ INFO</span></a>
                </div>
            </section>
            <video autoplay muted loop class="container__video">
                <source src="IMAGENES/APP/Video Games - 89894.mp4">
            </video>
            <div class="container__productos"></div>
            <div class="container__resultado__busqueda"></div>
        </main>
        <script><%@include file="/JAVASCRIPT/header.js"%></script>
        <script><%@include file="/JAVASCRIPT/iniciarSesion.js"%></script>
        <script><%@include file="/JAVASCRIPT/gestionarCarrito.js"%></script>
    </body>
</html>
