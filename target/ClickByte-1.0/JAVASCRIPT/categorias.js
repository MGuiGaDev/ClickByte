/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let listCategorias;

document.addEventListener("DOMContentLoaded", asignarEventos);

function asignarEventos(){
    listCategorias = document.getElementById("menu__list");
    listCategorias.addEventListener("click", function (evento) {
        evento.preventDefault();
        mostrarProductosCategoria(evento);
    });
    
}

function mostrarProductosCategoria(evento) {
        let btn__categoria = evento.target;
        let btns = Array.from(document.querySelectorAll(".menu__list button"));
        window.scrollTo(0, 0);
        btns.forEach(e => e.style="backgroun: none;");
        btn__categoria.style = "background: #F68E5F; color:white;";
}
