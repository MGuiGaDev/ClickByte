<header>
    <!-- 
        MODALES 
    -->
    <c:if test="${requestScope.mensajeCrearCuenta != null}">
        <form class="modal__aviso__crearCuenta" action="IniciarSesionController" method="POST">
            <div class="aviso__crearCuenta"">
                <div class="cerrar__aviso">
                    <button name="accion" value="cerrar"><span class="material-icons">clear</span></button>
                </div>
                <div class="logo__aviso">
                    <h1 class="clickbyte__title">ClickByte.es</h1>
                </div>
                <div class="info__aviso">
                    <h2>Para realizar una compra debe iniciar sesión o crear una cuenta de usuario.</h2>
                </div>
            </div>
        </form>
    </c:if>
    <div id="modal__busqueda" class="modal__busqueda">
        <form  action="BusquedaController" method="POST" class="form__busqueda" id="form__busqueda">
            <div class="busqueda__body">
                <div class="busqueda__data">
                    <input type="search" autofocus class="busqueda__input" name="busqueda__input"><button name="accion" value="buscar"><span class="material-icons" style="font-size: 30px;">search</span></button>
                </div>
                <div class="mas__buscados">
                    <h2>LO M&Aacute;S BUSCADO</h2>
                    <div>
                        <button name="accion" value="portátil" class="btn__mb">Port&aacute;til</button> <button name="accion" value="tablet" class="btn__mb">Tablet</button> <button name="accion" value="mac" class="btn__mb">Mac</button> <button name="accion" value="placa" class="btn__mb">Placa</button>
                    </div>
                </div>
            </div>
            <div class="busqueda__footer">
                <a id="cerrar__modal__busqueda">CERRAR</a>
                <button name="accion" value="buscar">BUSCAR</button>
            </div>
        </form>
    </div>
    <div id="modal__login" class="modal__login">
        <form class="login" id="login" action="IniciarSesionController" method="POST">
            <div class="login__header">
                <p>Iniciar sesi&oacute;n</p>
                <span class="material-icons" id="close__login">close</span>
            </div>
            <div class="login__body" id="login__body">
                <div class="form__group">
                    <label for="email" class="label">@Email</label>
                    <input type="email" class="input__login" id="email" name="email"
                           placeholder="Ej: joaquin08@gmail.com">
                </div>
                <div class="form__group">
                    <label for="password" class="label">Contraseña</label>
                    <input type="password" class="input__login" id="password" name="password"
                           placeholder="Ej: 123asd$=">
                </div>
                <div class="form__group show">
                    <input type="checkbox" id="show">
                    <label for="show">Mostrar contraseña</label>
                </div>
            </div>
            <div class="login__footer">
                <a href="${contexto}/JSP/crearCuenta.jsp" class="btn__login" id="btn__createAccount">
                    <span class="text">Crear cuenta</span>
                    <span class="material-icons">person_add</span>
                </a>
                <button class="btn__login" id="btn__login" name="accion" value="iniciarSesion" disabled>
                    <span class="text">Iniciar sesi&oacute;n</span>
                    <span class="material-icons">login</span>
                </button>
            </div>
        </form>
    </div>
    <div id="modal__carrito" class="modal__carrito">
        <div action="FinalizarCarritoController" method="POST" class="carrito" id="carrito">
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
                                    <br>
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
                                            <div>
                                                <span class="material-icons" data-idProducto="${productoCarrito.idProducto}">clear</span>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <p class="producto__carrito__precio">Importe unidad: <fmt:formatNumber type="currency" minFractionDigits="2" value="${productoCarrito.precio}" /></p>
                                    <p class="producto__carrito__precio">Importe total: <fmt:formatNumber type="currency" minFractionDigits="2" value="${productoCarrito.precio*productoCarrito.cantidad}" /></p>
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
                    <form action="FinalizarCarritoController" method="POST" class="acciones__carrito">
                        <span class="material-icons accion__carrito" id="eliminar__carrito">delete</span>
                        <button name="accionCarrito" value="pagar" class="accion__carrito">REALIZAR COMPRA</button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
    <!-- 
        FIN MODALES 
    -->
    <form action="FrontController" method="POST" class="container__logo">
        <button name="volver" value="volver">
            <img src="IMAGENES/APP/logo_clico.png" alt="Clickbyte" class="halfclick__logo">
            <h1 class="clickbyte__title">ClickByte.es</h1>
        </button>
    </form>
    <div class="container__nav">
        <ul class="nav">
            <li class="nav__item">
                <a class="nav__item__button" title="Buscar" id="buscar">
                    <span class="material-icons">search</span>
                </a>
            </li>
            <li class="nav__item">
            <c:choose>
                <c:when test="${sessionScope.usuario!=null}">
                    <form action="GestionarCuentaController" method="POST">
                        <button class="nav__item__button" title="Mi Cuenta" id="open__modal__login">
                            <img src="${contexto}/IMAGENES/AVATARES/${usuario.avatar}" class="avatar__usuario"/>
                        </button>  
                    </form>
                </c:when>
                <c:when test="${sessionScope.usuario==null}">
                    <a class="nav__item__button" title="Mi Cuenta" id="open__modal__login">
                        <span class="material-icons">perm_identity</span>
                    </a>
                </c:when>
            </c:choose>
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