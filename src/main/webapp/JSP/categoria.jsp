<%-- 
    Document   : categoria
    Created on : 27-dic-2021, 20:24:01
    Author     : Manuel Guillén Gallardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ClickByte</title>
        <style><%@include file="../CSS/index.css"%></</style>
        <style><%@include file="../CSS/modales.css"%></</style>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="shortcut icon" href="logo_clico.ico" type="image/x-icon" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
</head>
<body id="body">
    <%@include file="../INCLUDES/header.inc"%>
    <main>
        <%@include file="../INCLUDES/menuCategorias.inc"%>
        <section class="container__productos">
            <div class="productos__header">
                <h2><c:out value="${requestScope.nombreCategoria}"/></h2>
            </div>
            <form class="productos__body" action="ProductoController" method ="POST">
                <c:forEach var="producto" items="${listaProductosIdCategoria}">
                    <div class="producto">
                        <div class="container__productos__img">
                            <img src="IMAGENES/APP/productos/${producto.direccionImagen}.jpg" class="productos__img">
                        </div>
                        <div class="container__productos__desc">
                            <p>
                                <c:out value="${producto.nombre}" />
                            </p>
                            <p>
                                <fmt:formatNumber type="currency" minFractionDigits="2" value="${producto.precio}" />
                            </p>
                        </div>
                        <div class="container__productos__btn">
                            <span class="material-icons producto__btn span__exception" id="${producto.idProducto}">add_shopping_cart</span>
                            <button class="producto__btn" name="idProducto" value="${producto.idProducto}">VER DETALLE</button>
                        </div>
                    </div>
                </c:forEach>
            </form>
        </section>
        <div class="container__resultado__busqueda"></div>
    </main>
    <script><%@include file="../JAVASCRIPT/header.js"%></script>
    <script><%@include file="../JAVASCRIPT/iniciarSesion.js"%></script>
    <script><%@include file="../JAVASCRIPT/gestionarCarrito.js"%></script>
</body>
</html>
