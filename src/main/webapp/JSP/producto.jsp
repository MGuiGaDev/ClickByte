<%-- 
    Document   : producto
    Created on : 28-dic-2021, 10:23:19
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
                        <c:forEach var="categoria" items="${sessionScope.listaCategorias}">
                            <li>
                                <button type="submit" id="${categoria.idCategoria}" name="categoria" value="${categoria.idCategoria}">${categoria.nombre}</button>   
                            </li>
                        </c:forEach>
                    </ul>
                </form>
                <!-- FIN MODAL -->
            </div>


            <section class="container__productos container__vista__producto">
                <div class="container__vista__producto__img">
                    <img src="IMAGENES/APP/productos/${producto.direccionImagen}.jpg" class="regalo__img">
                </div>
                <div class="container__vista__producto__descripcion">
                    <h2 class="vista__producto__descripcion__titulo">
                        <c:out value="${producto.nombre}" />
                    </h2>
                    <h3 class="vista__producto__descripcion__precio">
                        <fmt:formatNumber type="currency" minFractionDigits="2" value="${producto.precio}" />
                    </h3>
                    <p>Vendido y enviado por <strong>ClickByte</strong></p>
                    <table>
                        <tr>
                            <td>Envío:</td>
                            <td>¡ENVÍO GRATIS!</td>
                        </tr>
                        <tr>
                            <td>Marca:</td>
                            <td><c:out value="${producto.marca}" /> | Cod. Artículo: <c:out value="${producto.idProducto}" /></td>
                        </tr>
                        <tr>
                            <td>Cantidad:</td>
                            <td>1</td>
                        </tr>
                        <tr>
                            <td>Descripción:</td>
                            <td><c:out value="${producto.descripcion}" /></td>
                        </tr>
                    </table>
                    <span class="material-icons producto__btn span__exception btn__vista__producto" id="${producto.idProducto}" style="font-size: 30px;">add_shopping_cart</span>
                </div>
            </section>
        </main>
        <%@include file="../INCLUDES/footer.inc"%>
        <script><%@include file="../JAVASCRIPT/header.js"%></script>
        <script><%@include file="../JAVASCRIPT/iniciarSesion.js"%></script>
        <script><%@include file="../JAVASCRIPT/gestionarCarrito.js"%></script>
    </body>
</html>
