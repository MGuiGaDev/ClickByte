/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let count__producto;

let carrito__body;

let container__productos;


document.addEventListener("DOMContentLoaded", asignarEventos);
function asignarEventos() {

    count__producto = document.getElementById("count__producto");

    carrito__body = document.getElementById("carrito__body");

    container__productos = document.querySelector(".container__productos");

    container__productos.addEventListener("click", function (evento) {
        cargarCarritoConProducto(evento);
    });

}
function cargarCarritoConProducto(evento) {
    if (evento.target.tagName === "SPAN" && evento.target.id.length > 0) {
        let idProducto = evento.target.id;
        let modal__carrito = document.getElementById("modal__carrito");
        modal__carrito.classList.add("modal__carrito-activo");
        nuevoProductoCarrito(idProducto);
    }
}
function actualizarCarrito() {

}
function nuevoProductoCarrito(idProducto) {
    let value = idProducto;
    let url = "GestionarCarrito";
    $.ajax({
        type: 'POST',
        url: url,
        data: {
            accion: "nuevoProducto",
            id: value
        },
        success: function (data) {

            if (data.tipo === 'success') {
                let infoProducto = {
                    idProducto: data.idProducto,
                    imagen: data.imagen,
                    nombre: data.nombre,
                    cantidad: data.cantidad,
                    precio: data.precio
                };
                let nuevoProducto = "<div class='container__producto__carrito' data-idProducto='" + infoProducto.idProducto + "'>\n\
                                                <div class='container__producto__carrito__img'>\n\
                                                    <img src='IMAGENES/APP/productos/" + infoProducto.imagen + ".jpg' class='producto__carrito__img'>\n\
                                                </div>\n\
                                                <div class='descripcion__producto__carrito'>\n\
                                                    <p class='producto__carrito__nombre'>" + infoProducto.nombre + "</p>\n\
                                                <div class='container__producto__carrito__unidades'>\n\
                                                    <p> Unidades: </p>\n\
                                                    <div class='producto__carrito__unidades'>\n\
                                                        <span class='material-icons'>remove</span>\n\
                                                        <span class='unidades'>" + infoProducto.cantidad + "</span>\n\
                                                        <span class='material-icons'>add</span>\n\
                                                    </div>\n\
                                                </div>\n\
                                                <p class='producto__carrito__precio'>" + infoProducto.precio  +",00 €</p>\n\
                                                </div>\n\
                                            </div>";
                if (carrito__body.innerHTML !== "<h2>El carrito está vacío</h2>") {
                    let productosCarrito = Array.from(carrito__body.children);
                    let productoActualizado = productosCarrito.find(element => element.dataset.idproducto === idProducto);
                    if (productoActualizado) {
                        productoActualizado.querySelector(".unidades").innerText = parseInt(productoActualizado.querySelector(".unidades").innerText) + 1;
                        count__producto.innerText = parseInt(count__producto.innerText) + 1;
                    } else {
                        carrito__body.innerHTML += nuevoProducto;
                        count__producto.innerText = parseInt(count__producto.innerText) + 1;
                    }
                } else {
                    carrito__body.innerHTML = nuevoProducto;
                    count__producto.innerText = parseInt(count__producto.innerText) + 1;
                }
            } else {
                console.log("error");
            }

        },
        error: function () {
            console.log("error");
        }
    });
    ;
}