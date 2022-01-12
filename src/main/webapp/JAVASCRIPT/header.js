/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author [Manuel Guillén Gallardo]
 * @email [mguigadev@gmail.com]
 * @create date 23-12-2021 12:42:49
 * @modify date 23-12-2021 16:37:01
 * @desc [pixer.com]
 */

/**
 * Botón de usuario logueado.
 */
let usuarioLogueado = `
<li class="nav__item">
    <a href="#" class="nav__item__button" title="Mi Cuenta" id="open__cuenta">
        <img src="IMAGENES/perfil.jpg" class="imagen__perfil" id="imagen__perfil"">
        <span class="nav__item__text" id="usuario__nombre">mguilleng02@</span>
    </a>
</li>
`;

/**
 * Variables para el tratamiento de eventos relacionados con el carrito y su ventana modal.
 */
let modal__carrito;
let open__carrito;
let countCarrito;
let carrito;
let close__carrito;
let span__exception;

/**
 * Variables para el tratamiento de eventos relacionados con el menú de categorías.
 */
let modal__menu;
let btn__menu;

/**
 * Variables para el tratamiento de eventos relacionados con el inicio de sesión del usuario.
 */
let modal__login;
let login;
let open__modal__login;
let close__login;
let btn__login;

/**
 * Variables para el tratamiento de eventos relacionados con la búsqueda
 */
let buscar;
let modal__busqueda;
let form__busqueda;
let cerrar__modal__busqueda;


document.addEventListener("DOMContentLoaded", asignarEventos);

function asignarEventos() {

    nav = document.querySelector(".nav");

    modal__carrito = document.getElementById("modal__carrito");
    open__carrito = document.getElementById("open__carrito");
    open__carrito.addEventListener("click", visualizarCarrito);
    close__carrito = document.getElementById("close__carrito");
    carrito = document.getElementById("carrito");
    span__exception = Array.from(document.querySelectorAll(".span__exception"));

    modal__login = document.getElementById("modal__login");
    open__modal__login = document.getElementById("open__modal__login");
    login = document.getElementById("login");
    close__login = document.getElementById("close__login");

    btn__login = document.getElementById("btn__login");
    open__modal__login.addEventListener("click", visualizarLogin);
    
    modal__menu = document.getElementById("modal__menu");
    btn__menu = document.getElementById("btn__menu");
    btn__menu.addEventListener("click", visualizarMenu);
    
    buscar = document.getElementById("buscar");
    modal__busqueda = document.getElementById("modal__busqueda");
    form__busqueda = document.getElementById("form__busqueda");
    cerrar__modal__busqueda = document.getElementById("cerrar__modal__busqueda");
    buscar.addEventListener("click", visualizarBusqueda);

    let body = document.getElementById("body");
    body.addEventListener("click", function (evento) {
        cerrarModalesGlobal(evento.target);
    });
}

function visualizarBusqueda() {
    modal__busqueda.classList.add("modal__busqueda-activo");
}


function visualizarCarrito() {
    modal__carrito.classList.add("modal__carrito-activo");
}

function visualizarLogin() {
    if (!open__modal__login.querySelector("img")) {
        modal__login.classList.add("modal__login-activo");
        open__modal__login.style = "background: linear-gradient(to right top, #7B65FF 0%, #251374 100%);  color: white";
    }
}

function visualizarMenu() {
    if (!modal__menu.classList.contains("modal__menu-activo")) {
        btn__menu.childNodes[1].innerText = "close";
        modal__menu.classList.add("modal__menu-activo");
    } else {
        btn__menu.childNodes[1].innerText = "menu";
        modal__menu.classList.remove("modal__menu-activo");
        let btns = Array.from(document.querySelectorAll(".menu__list button"));
        btns.forEach(e => e.style = "backgroun: none;");
    }

}

function cerrarModalesGlobal(evento) {
    switch (true) {
        case ((evento === close__carrito || !carrito.contains(evento)) && !open__carrito.contains(evento) && modal__carrito.classList.contains("modal__carrito-activo")):
            if (span__exception.some(element => element === evento)) {
                modal__carrito.classList.add("modal__carrito-activo");
            } else {
                modal__carrito.classList.remove("modal__carrito-activo");
            }
            break;
        case ((evento === close__login || !login.contains(evento)) && !open__modal__login.contains(evento) && modal__login.classList.contains("modal__login-activo")):
            open__modal__login.style = "background: #1882DA;";
            open__modal__login.style = ":hover {background: linear-gradient(to right top, #1882DA 0%, #251374 100%);}";
            modal__login.classList.remove("modal__login-activo");
            break;
        case ((evento === cerrar__modal__busqueda || !form__busqueda.contains(evento)) && !buscar.contains(evento) && modal__busqueda.classList.contains("modal__busqueda-activo")):
            modal__busqueda.classList.remove("modal__busqueda-activo");
        break;
        case (!btn__menu.contains(evento) && !modal__menu.contains(evento) && modal__menu.classList.contains("modal__menu-activo")):
            btn__menu.childNodes[1].innerText = "menu";
            modal__menu.classList.remove("modal__menu-activo");
            let btns = Array.from(document.querySelectorAll(".menu__list button"));
            btns.forEach(e => e.style = "backgroun: none;");
            break;
    }
}