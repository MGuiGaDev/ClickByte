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
                        <button name="accion" value="mac" class="btn__mb">Mac</button> <button name="accion" value="placa" class="btn__mb">Placa</button>
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
                    <label for="password" class="label">Contrase&ncaron;a</label>
                    <input type="password" class="input__login" id="password" name="password"
                           placeholder="Ej: 123asd$=">
                </div>
                <div class="form__group show">
                    <input type="checkbox" id="show">
                    <label for="show">Mostrar contrase&ncaron;a</label>
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
                    <c:when test="${sessionScope.listaProductosCesta!=null}">
                        <c:forEach var="productoCarrito" items="${listaProductosCesta}">
                            <div class="container__producto__carrito" data-idProducto="${productoCarrito.idProducto}">
                                <div class="container__producto__carrito__img">
                                    <img src="IMAGENES/APP/productos/${productoCarrito.nombreImagen}.jpg" class="producto__carrito__img">
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
                                    <p class="producto__carrito__precio">Importe unidad: <fmt:formatNumber type="currency" minFractionDigits="2" value="${productoCarrito.precioUnitario}" /></p>
                                    <p class="producto__carrito__precio">Importe total: <fmt:formatNumber type="currency" minFractionDigits="2" value="${productoCarrito.precioUnitario*productoCarrito.cantidad}" /></p>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:when test="${sessionScope.listaProductosCesta==null}">
                        <h2>El carrito est&aacute; vac&iacute;o</h2>
                    </c:when>
                </c:choose>
            </div>
            <div class="carrito__footer" id="carrito__footer">
                <c:if test="${sessionScope.cantidadProductosCesta!=null && sessionScope.totalCesta!=null}">
                    <div class="container__total__unidades">
                        <p>Unidades:</p>
                        <p id="total__productos"><c:out value="${cantidadProductosCesta}"/></p>
                    </div>
                    <div class="container__total">
                        <p>Total: </p>
                        <p id="total__carrito"><fmt:formatNumber type="currency" minFractionDigits="2"  value="${totalCesta}"/></p>
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
            <li class="nav__item" id="cuenta">
            <c:choose>
                <c:when test="${sessionScope.usuario!=null}">
                    <form action="OpcionesCuentaUsuarioController" method="POST" class="form__usuario">
                        <button name="accion" value="verPerfil" class="nav__item__button btn__usuario" title="Mi Cuenta" id="open__modal__login">
                            <img src="${contexto}/IMAGENES/AVATARES/${usuario.avatar}" class="avatar__usuario"/>
                            <p><c:out value="${usuario.nombre}"/></p>
                        </button>  
                        <ul class="opcionesUsuario">
                            <li><button name="accion" value="cerrarSesion" class="btn__opcion__usuario">Cerrar sesi&oacute;n</button></li>
                            <li><button name="accion" value="verPerfil" class="btn__opcion__usuario">Editar perfil</button></li>
                            <li><button name="accion" value="verPedidos" class="btn__opcion__usuario">Ver pedidos</button></li>
                        </ul>
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
                            <c:when test="${cantidadProductosCesta!=null}">
                                <c:out value="${cantidadProductosCesta}"/>
                            </c:when>
                            <c:when test="${cantidadProductosCesta==null}">
                                <c:out value="0"/>
                            </c:when>
                        </c:choose>
                    </span>
                </a>
            </li>
        </ul>
    </div>
</header>