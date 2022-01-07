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
                                        <div class="gestion__unidad">
                                            <div class="container__producto__carrito__unidades">
                                                <p>Unidades:</p>
                                                <div class="producto__carrito__unidades">
                                                    <span class="material-icons" data-idProducto="${productoCarrito.idProducto}">remove</span>
                                                    <span class="unidades"><c:out value="${productoCarrito.cantidad}" /></span>
                                                    <span class="material-icons" data-idProducto="${productoCarrito.idProducto}">add</span>
                                                </div>
                                            </div>
                                            <div class="container__producto__carrito__unidades delete__unidad">
                                                <span class="material-icons" data-idProducto="${productoCarrito.idProducto}">delete</span>
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


        <section class="container__productos">
            <div class="productos__header">
                <input type="button" hidden id="nombreCategoria" value="${listaProductosIdCategoria[0].nombreCategoria}"/>
                <h2><c:out value="${listaProductosIdCategoria[0].nombreCategoria}" /></h2>
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
    </main>
    <script><%@include file="../JAVASCRIPT/header.js"%></script>
    <script><%@include file="../JAVASCRIPT/gestionarCarrito.js"%></script>
</body>
</html>
