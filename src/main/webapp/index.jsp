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
        <main class="main__index">
            <%@include file="INCLUDES/menuCategorias.inc"%>
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
            <%@include file="INCLUDES/footer.inc"%>
        <script><%@include file="/JAVASCRIPT/header.js"%></script>
        <script><%@include file="/JAVASCRIPT/iniciarSesion.js"%></script>
        <script><%@include file="/JAVASCRIPT/gestionarCarrito.js"%></script>
    </body>
</html>
