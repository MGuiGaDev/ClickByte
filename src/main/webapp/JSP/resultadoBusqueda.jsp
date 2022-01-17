<%-- 
    Document   : resultadoBusqueda
    Created on : 11-ene-2022, 13:17:43
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
        <style><%@include file="../CSS/vistaBusqueda.css"%></</style>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="shortcut icon" href="logo_clico.ico" type="image/x-icon" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    </head>
</head>
<body id="body">
    <%@include file="../INCLUDES/header.inc"%>
    <main>
        <div class="container__menu">
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
                            <input name="nombreCategoria" type="button" hidden value="${categoria.nombre}"/>
                            <button type="submit" id="${categoria.idCategoria}" name="categoria" value="${categoria.idCategoria}"><c:out value="${categoria.nombre}"/></button>   
                        </li>
                    </c:forEach>
                </ul>
            </form>
            <!-- FIN MODAL -->
        </div>
        <c:choose>
            <c:when test="${requestScope.numeroResultado!=null}">
                <section class="titulo__vista__busqueda">
                    <c:choose>
                        <c:when test="${numeroResultado>1}">
                            <h2>Para la búsqueda "<c:out value="${valorBuscado}"/>" se han encontrado <c:out value="${requestScope.numeroResultado}"/> resultados.</h2>
                        </c:when>
                        <c:when test="${numeroResultado==1}">
                            <h2>Para la búsqueda "<c:out value="${valorBuscado}"/>" se ha encontrado <c:out value="${requestScope.numeroResultado}"/> resultado.</h2>
                        </c:when>
                    </c:choose>
                </section>
                <section class="container__vista__busqueda">
                    <div class="container__parametros__busqueda">
                        <div class="filtro__marcas">
                            <h3>Filtro por marca</h3>
                            <c:forEach var="marca" items="${marcas}">
                                <label class="marca"><input type="checkbox" checked="true" data-marca="${marca}"><c:out value="${marca}"/></label>
                                </c:forEach>
                        </div>
                        <div class="filtro__precios">
                            <h3>Filtro por precio</h3>
                            <div class="container__range">
                                <input type="range" min="0" max="${precioMasAlto}" value="${precioMasAlto}" class="range" id="range" step="20">
                                <span class="slider__value" id="slider__value"><c:out value="${precioMasAlto}"/>0 €</span>
                            </div>
                        </div>
                    </div>
                    <div class="container__resultado__busqueda">
                        <form class="productos__body" action="ProductoController" method ="POST">
                            <c:forEach var="producto" items="${requestScope.listaProductosBuscados}">
                                <div class="producto" data-precio="${producto.precio}" data-marca="${producto.marca}">
                                    <div class="container__productos__img">
                                        <img src="IMAGENES/APP/productos/${producto.direccionImagen}.jpg" class="productos__img">
                                    </div>
                                    <div class="container__productos__desc">
                                        <p value="${producto.marca}">
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
                    </div>
                </section>
                <div class="container__productos"></div>
            </c:when>


            <c:when test="${requestScope.numeroResultado==null}">
                <section class="titulo__vista__busqueda">
                    <h2>No se han encontrado resultados</h2>
                </section>
                <section class="container__vista__busqueda">
                    <div class="container__parametros__busqueda">

                    </div>
                    <div class="container__resultado__busqueda">
                        <div class="container__productos"></div>
                    </div>
                </section>
            </c:when>
        </c:choose>
    </main>
    <%@include file="../INCLUDES/footer.inc"%>
    <script><%@include file="../JAVASCRIPT/header.js"%></script>
    <script><%@include file="../JAVASCRIPT/iniciarSesion.js"%></script>
    <script><%@include file="../JAVASCRIPT/gestionarCarrito.js"%></script>
    <script><%@include file="../JAVASCRIPT/resultadoBusquedaFiltros.js"%></script>
    <!-- AJAX -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</body>
</html>
