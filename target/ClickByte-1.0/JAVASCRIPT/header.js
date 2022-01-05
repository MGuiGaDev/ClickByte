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
 * Variable que será utilizada para la delegación de eventos. 
 * Cuando el usuario inicia sesión, será creado dinámicamente un nuevo botón, para poder
 * trabajar con él, será necesario enfocarlo mediante este elemento.
 */
let nav;
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
 * Variables para el tratamiento de eventos relacionados con el usuario logueado y su ventana modal.
 */
let modal__cuentaUsuario;
let cuentaUsuario;
let close__cuentaUsuario;
let logout;



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
    btn__login.addEventListener("click", function (evento) {
        gestionarLogin(evento);
    });
    nav.addEventListener("click", function (evento) {
        gestionarUsuario(evento);
    });

    modal__cuentaUsuario = document.getElementById("modal__cuentaUsuario");
    cuentaUsuario = document.getElementById("cuentaUsuario");
    close__cuentaUsuario = document.getElementById("close__cuentaUsuario");
    logout = document.getElementById("logout");

    modal__menu = document.getElementById("modal__menu");
    btn__menu = document.getElementById("btn__menu");
    btn__menu.addEventListener("click", visualizarMenu);

    let body = document.getElementById("body");
    body.addEventListener("click", function (evento) {
        cerrarModalesGlobal(evento.target);
    });
}

function gestionarUsuario(evento) {
    if (evento.target.id === "open__cuenta" || evento.target.id === "imagen__perfil" || evento.target.id === "usuario__nombre") {
        visualizarModalCuentaUsuario();
    }
}

function visualizarModalCuentaUsuario() {
    let open__ModalCuentaUsuario = document.querySelector(".nav li:nth-child(3)");
    if (!modal__cuentaUsuario.classList.contains("modal__cuentaUsuario-activo")) {
        modal__cuentaUsuario.classList.add("modal__cuentaUsuario-activo");
        open__ModalCuentaUsuario.style = "background: linear-gradient(to right top, #7B65FF 0%, #251374 100%); border-radius: 5px;";
        usuario__nombre.style = "color: white;";
    } else {
        modal__cuentaUsuario.classList.remove("modal__cuentaUsuario-activo");
        open__ModalCuentaUsuario.style = "background: #7B65FF;";
        open__ModalCuentaUsuario.style = ":hover {background: linear-gradient(to right top, #7B65FF 0%, #251374 100%); color: white;}";
        usuario__nombre.style = "color: #7B65FF;";
        usuario__nombre.style = ":hover{color: white;}";
    }

}

function gestionarLogin(evento) {
    evento.preventDefault();
    /*
     COSAS QUE TIENE QUE HACER ESTE MÉTODO
     - [x] Validar si se han introducido bien los campos, existe correo...
     - [x] Abrir sesión de usuario
     - [x] Preguntar si hay imagen en servidor, sino por defecto
     - [x] Recuperar correo usuario y hacer slice() para nombre = xxxxx@ 
     - [v] Eliminar nav__item existente e incrustar el nuevo con los
     elementos del usuario
     - [v] Cerrar modal
     */
    // [v] Eliminar nav__item existente e incrustar el nuevo con los elementos del usuario
    document.querySelector(".nav li:nth-child(3)").style = "display: none;";
    document.querySelector(".nav li:nth-child(2)").insertAdjacentHTML("afterend", usuarioLogueado);
    //[v] Cerrar modal
    modal__login.classList.remove("modal__login-activo");
}

function visualizarCarrito() {
    modal__carrito.classList.add("modal__carrito-activo");
}

function visualizarLogin() {
    modal__login.classList.add("modal__login-activo");
    open__modal__login.style = "background: linear-gradient(to right top, #7B65FF 0%, #251374 100%);  color: white";
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
            if(span__exception.some(element => element === evento)) {
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
        case ((evento === close__cuentaUsuario || logout.contains(evento) || !cuentaUsuario.contains(evento)) && !nav.querySelector("li:nth-child(3)").contains(evento) && modal__cuentaUsuario.classList.contains("modal__cuentaUsuario-activo")):
            nav.querySelector("li:nth-child(3)").style = "background: #1882DA;";
            nav.querySelector("li:nth-child(3) span").style = "color:#1882DAF;";
            nav.querySelector("li:nth-child(3)").style = ":hover {background: linear-gradient(to right top, #1882DAF 0%, #251374 100%);}";
            modal__cuentaUsuario.classList.remove("modal__cuentaUsuario-activo");
            break;
        case (!btn__menu.contains(evento) && !modal__menu.contains(evento) && modal__menu.classList.contains("modal__menu-activo")):
            btn__menu.childNodes[1].innerText = "menu";
            modal__menu.classList.remove("modal__menu-activo");
            console.log(evento);
            let btns = Array.from(document.querySelectorAll(".menu__list button"));
            btns.forEach(e => e.style = "backgroun: none;");
            break;
    }
}