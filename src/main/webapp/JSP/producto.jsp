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
        <header>
            <!-- 
                MODALES 
            -->
            <div id="modal__login" class="modal__login">
                <form class="login" id="login">
                    <div class="login__header">
                        <p>Iniciar sesi&oacute;n</p>
                        <span class="material-icons" id="close__login">close</span>
                    </div>
                    <div class="login__body" id="form__count">
                        <div class="form__group">
                            <input type="email" class="inputLogin" id="email" name="email" required>
                            <label for="email" class="label">@Email*</label>
                        </div>
                        <div class="form__group user__account">
                            <input type="password" class="inputLogin" id="password" name="password" required>
                            <label for="password" class="label">Contrase&ntilde;a*</label>
                            <span class="material-icons" id="pass__visibility"
                                  style="font-size: 16px; color: #dadada;">visibility_off</span>
                        </div>
                    </div>
                    <div class="login__footer">
                        <a href="${contexto}/JSP/crearCuenta.jsp" class="btn__login" id="btn__createAccount">
                            <span class="text">Crear cuenta</span>
                            <span class="material-icons">person_add</span>
                        </a>
                        <button class="btn__login" id="btn__login">
                            <span class="text">Iniciar sesi&oacute;n</span>
                            <span class="material-icons">login</span>
                        </button>
                    </div>
                </form>
            </div>
            <div id="modal__carrito" class="modal__carrito">
                <div class="carrito" id="carrito">
                    <div class="carrito__header">
                        <p class="p-title">Mi carrito</p>
                        <span class="material-icons" id="close__carrito">close</span>
                    </div>
                    <div class="carrito__body" id="carrito__body">
                        <c:choose>
                            <c:when test="${sessionScope.listaProductosCarrito!=null}">
                                <c:forEach var="productoCarrito" items="${listaProductosCarrito}">
                                    <div class="container__producto__carrito" data-idProducto="${productoCarrito.idProducto}">
                                        <div class="container__producto__carrito__img">
                                            <img src="IMAGENES/APP/productos/${productoCarrito.direccionImagen}.jpg" class="producto__carrito__img">
                                        </div>
                                        <div class="descripcion__producto__carrito">
                                            <p class="producto__carrito__nombre"><c:out value="${productoCarrito.nombre}" /></p>
                                            <div class="container__producto__carrito__unidades">
                                                <p>Unidades:</p>
                                                <div class="producto__carrito__unidades">
                                                    <span class="material-icons">remove</span>
                                                    <span class="unidades"><c:out value="${productoCarrito.cantidad}" /></span>
                                                    <span class="material-icons">add</span>
                                                </div>
                                            </div>
                                            <p class="producto__carrito__precio"><fmt:formatNumber type="currency" minFractionDigits="2" value="${productoCarrito.precio}" /></p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:when test="${sessionScope.listaProductosCarrito==null}">
                                <h2>El carrito está vacío</h2>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="carrito__footer" id="carrito__footer">
                        <c:if test="${sessionScope.cantidadProductosCarrito!=null && sessionScope.totalCarrito!=null}">
                            <div class="container__total__unidades">
                                <p>Unidades:</p>
                                <p id="total__productos"><c:out value="${cantidadProductosCarrito}"/></p>
                            </div>
                            <div class="container__total">
                                <p>Total: </p>
                                <p id="total__carrito"><fmt:formatNumber type="currency" minFractionDigits="2"  value="${totalCarrito}"/></p>
                            </div>
                            <form action="FinalizarCarrito" method="POST" class="acciones__carrito">
                                <button name="accionCarrito" value="eliminar" class="accion__carrito"><span class="material-icons ">delete</span></button>
                                <button name="accionCarrito" value="pagar" class="accion__carrito">REALIZAR COMPRA</button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
            <div id="modal__cuentaUsuario" class="modal__cuentaUsuario">
                <div class="cuentaUsuario" id="cuentaUsuario">
                    <div class="cuentaUsuario__header">
                        <p class="p-title">Mi cuenta</p>
                        <span class="material-icons" id="close__cuentaUsuario">close</span>
                    </div>
                    <div class="cuentaUsuario__body">

                    </div>
                    <div class="cuentaUsuario__footer">
                        <!-- CERRAR SESIÓN -->
                        <button class="btn__login" id="logout">
                            <span class="text">Cerrar sesi&oacute;n</span>
                            <span class="material-icons">logout</span>
                        </button>
                    </div>
                </div>
            </div>
            <!-- 
                FIN MODALES 
            -->
            <form action="FrontController" method="POST" class="container__logo">
                <button name="accion" value="producto.jsp">
                    <img src="IMAGENES/APP/logo_clico.png" alt="Clickbyte" class="halfclick__logo">
                    <h1 class="clickbyte__title">ClickByte.es</h1>
                </button>
            </form>
            <div class="container__nav">
                <ul class="nav">
                    <li class="nav__item">
                        <a class="nav__item__button" title="Buscar">
                            <span class="material-icons">search</span>
                        </a>
                    </li>
                    <li class="nav__item">
                        <a class="nav__item__button" title="Mi Cuenta" id="open__modal__login">
                            <span class="material-icons">perm_identity</span>
                        </a>
                    </li>

                    <li class="nav__item">
                        <a class="nav__item__button" title="Mi carrito" id="open__carrito">
                            <span class="material-icons">shopping_cart</span>
                            <span class="count__products" id="count__producto">
                                <c:choose>
                                    <c:when test="${cantidadProductosCarrito!=null}">
                                        <c:out value="${cantidadProductosCarrito}"/>
                                    </c:when>
                                    <c:when test="${cantidadProductosCarrito==null}">
                                        <c:out value="0"/>
                                    </c:when>
                                </c:choose>
                            </span>
                        </a>
                    </li>
                </ul>
            </div>
        </header>
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
        <script><%@include file="../JAVASCRIPT/gestionarCarrito.js"%></script>
    </body>
</html>
