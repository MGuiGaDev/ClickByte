/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let filtro__marcas;
let marca__producto;

let filtro__precios;
let precio__producto;

let range;
let slider__value;

let productos;
let marcas;

document.addEventListener("DOMContentLoaded", asignarEventos);

function asignarEventos() {

    filtro__marcas = document.querySelector(".filtro__marcas");
    marca__producto = document.getElementById("marca__producto");
    filtro__marcas.addEventListener("change", function (evento) {
        filtrarPorMarca(evento);
    });

    filtro__precios = document.querySelector(".filtro__precios");
    precio__producto = document.getElementById("precio__producto");

    range = document.getElementById("range");
    slider__value = document.getElementById("slider__value");
    range.addEventListener("input", establecerValor);

    productos = Array.from(document.querySelectorAll(".producto"));
    marcas = Array.from(document.querySelectorAll(".marca"));

}
function filtrarPorMarca(evento) {
    if (evento.target.tagName === "INPUT") {
        let input = evento.target;
        if (!evento.target.checked) {
            for (let p of productos) {
                if (p.dataset.marca === input.dataset.marca) {
                    p.style = "display: none;";
                }
            }
        } else {
            for (let p of productos) {
                if (p.dataset.marca === input.dataset.marca) {
                    p.style = "display: block;";
                }
            }
        }
        let nuevaListaProductos = Array.from(document.querySelectorAll(".producto"));
        let valores = nuevaListaProductos.filter(element => element.style.display!=="none").map(element => element.dataset.precio);
        let valorMaximo = Math.max(...valores);
        range.value = valorMaximo;
        slider__value.innerHTML = valorMaximo + ",00 \u20AC";
    }
}
function establecerValor() {
    slider__value.innerHTML = range.value + ",00 \u20AC";
    let marcaEvento;
    let input;
    for (let p of productos) {
        if (parseFloat(p.dataset.precio) > parseFloat(range.value)) {
            p.style = "display: none;";
            marcaEvento = marcas.find(element => element.innerText.includes(p.dataset.marca));
            marcaEvento.style = "display: none;";
        } else {
            p.style = "display: block;";
            marcaEvento = marcas.find(element => element.innerText.includes(p.dataset.marca));
            marcaEvento.style = "display: block;";
            input = marcaEvento.querySelector("input").checked=true;
            //input.setAttribute("checked", true);
        }
    }
}
