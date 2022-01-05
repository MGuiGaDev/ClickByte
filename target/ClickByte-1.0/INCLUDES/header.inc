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
            <div class="carrito__body">
                <c:choose>
                    <c:when test="${requestScope.carrito!=null}">
                        
                    </c:when>
                    <c:when test="${requestScope.carrito==null}">
                        <h2>El carrito está vacío</h2>
                    </c:when>
                </c:choose>
            </div>
            <div class="carrito__footer">

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
    <a href="FrontController" class="container__logo">
        <img src="IMAGENES/APP/logo_clico.png" alt="Pixer" class="halfclick__logo">
        <h1 class="pixer__title">ClickByte.es</h1>
    </a>
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
                    <span class="count__products">0</span>
                </a>
            </li>
        </ul>
    </div>
</header>
