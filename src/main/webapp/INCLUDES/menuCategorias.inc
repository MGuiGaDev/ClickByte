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
