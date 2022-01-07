/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let count__producto;

let carrito__body;

let carrito__footer;

let container__productos;

document.addEventListener("DOMContentLoaded", asignarEventos);
function asignarEventos() {

    count__producto = document.getElementById("count__producto");

    carrito__body = document.getElementById("carrito__body");
    carrito__body.addEventListener("click", function (evento) {
        operarUnidadesProducto(evento);
    });

    container__productos = document.querySelector(".container__productos");
    carrito__footer = document.querySelector(".carrito__footer");
    container__productos.addEventListener("click", function (evento) {
        cargarCarritoConProducto(evento);
    });

}

function operarUnidadesProducto(evento) {
    console.log(carrito__body.childElementCount);
    if (evento.target.tagName === "SPAN") {
        let span = evento.target;
        let accion = span.innerText;
        let idProducto = span.dataset.idproducto;
        let url = "GestionarCarrito";
        if (accion === "remove") {
            if (span.parentElement.querySelector(".unidades").innerText === "1") {
                accion = "delete";
            }
        }


        $.ajax({
            type: 'POST',
            url: url,
            data: {
                accion: accion,
                id: idProducto
            },
            success: function (data) {

                if (data.tipo === 'success') {
                    let infoProducto = {
                        cantidad: data.cantidad,
                        precio: data.precio,
                        cantidadTotal: data.cantidadTotal,
                        total: data.total
                    };
                    let nuevoFooterCarrito = "<div class='container__total__unidades'>\n\
                                            <p>Unidades:</p>\n\
                                            <p>" + infoProducto.cantidadTotal + "</p>\n\
                                            </div>\n\
                                            <div class='container__total'>\n\
                                            <p>Total: </p>\n\
                                            <p>" + infoProducto.total + ",00 &euro;</p>\n\
                                            </div>\n\
                                            <form action='FinalizarCarrito' method='POST' class='acciones__carrito'>\n\
                                            <button name='accionCarrito' value='eliminar' class='accion__carrito'>\n\
                                            <span class='material-icons'>delete</span>\n\
                                            </button>\n\
                                            <button name='accionCarrito' value='pagar' class='accion__carrito'>REALIZAR COMPRA</button>\n\
                                            </form>\n\
                                        </div>";
                    switch (accion) {
                        case "add":
                        case "remove":
                            span.parentElement.querySelector(".unidades").innerText = infoProducto.cantidad;
                            span.parentElement.parentElement.parentElement.parentElement.querySelectorAll(".producto__carrito__precio")[1].innerText = "Importe total: " + infoProducto.precio * infoProducto.cantidad + ",00 \u20AC";
                            break;
                        case "delete":
                            
                            if (carrito__body.childElementCount > 1) {
                                span.parentElement.parentElement.parentElement.parentElement.parentElement.remove();
                            }
                            break;
                    }

                    count__producto.innerText = infoProducto.cantidadTotal;
                    carrito__footer.innerHTML = nuevoFooterCarrito;
                } else if (data.tipo === 'vacio') {
                    carrito__body.innerHTML = "<h2>El carrito est&aacute; vac&iacute;o</h2>";
                    count__producto.innerText = "0";
                    carrito__footer.innerHTML = "";
                } else {
                    console.log("error succes");
                }

            },
            error: function () {
                console.log("error post");
            }
        });
        ;
    }
}
function cargarCarritoConProducto(evento) {
    if (evento.target.tagName === "SPAN" && evento.target.id.length > 0) {
        let idProducto = evento.target.id;
        let modal__carrito = document.getElementById("modal__carrito");
        modal__carrito.classList.add("modal__carrito-activo");
        nuevoProductoCarrito(idProducto);
    }
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
                    precio: data.precio,
                    cantidadTotal: data.cantidadTotal,
                    total: data.total
                };
                let nuevoProducto = "<div class='container__producto__carrito' data-idProducto='" + infoProducto.idProducto + "'>\n\
                                                <div class='container__producto__carrito__img'>\n\
                                                    <img src='IMAGENES/APP/productos/" + infoProducto.imagen + ".jpg' class='producto__carrito__img'>\n\
                                                </div>\n\
                                                <div class='descripcion__producto__carrito'>\n\
                                                    <p class='producto__carrito__nombre'>" + infoProducto.nombre + "</p>\n\
                                                    <br>\n\
                                                    <div class='gestion__unidad'>    \n\
                                                        <div class='container__producto__carrito__unidades'>\n\
                                                            <p> Unidades: </p>\n\
                                                            <div class='producto__carrito__unidades'>\n\
                                                                <span class='material-icons' data-idProducto='" + infoProducto.idProducto + "'>remove</span>\n\
                                                                <span class='unidades'>" + infoProducto.cantidad + "</span>\n\
                                                                <span class='material-icons' data-idProducto='" + infoProducto.idProducto + "'>add</span>\n\
                                                            </div>\n\
                                                        </div>\n\
                                                        <div class='container__producto__carrito__unidades delete__unidad'>\n\
                                                            <span class='material-icons' data-idProducto='" + infoProducto.idProducto + "'>delete</span>\n\
                                                        </div>\n\
                                                    </div>\n\
                                                <br>\n\
                                                <p class='producto__carrito__precio'>Importe unidad: " + infoProducto.precio + ",00 &euro;</p>\n\
                                                <p class='producto__carrito__precio'>Importe total: " + infoProducto.precio * infoProducto.cantidad + ",00 &euro;</p>\n\
                                                </div>\n\
                                            </div>";
                let nuevoFooterCarrito = "<div class='container__total__unidades'>\n\
                                            <p>Unidades:</p>\n\
                                            <p>" + infoProducto.cantidadTotal + "</p>\n\
                                            </div>\n\
                                            <div class='container__total'>\n\
                                            <p>Total: </p>\n\
                                            <p>" + infoProducto.total + ",00 &euro;</p>\n\
                                            </div>\n\
                                            <form action='FinalizarCarrito' method='POST' class='acciones__carrito'>\n\
                                            <button name='accionCarrito' value='eliminar' class='accion__carrito'>\n\
                                            <span class='material-icons'>delete</span>\n\
                                            </button>\n\
                                            <button name='accionCarrito' value='pagar' class='accion__carrito'>REALIZAR COMPRA</button>\n\
                                            </form>\n\
                                        </div>";
                if (!carrito__body.querySelector("h2")) {
                    let productosCarrito = Array.from(carrito__body.children);
                    let productoActualizado = productosCarrito.find(element => element.dataset.idproducto === idProducto);
                    if (productoActualizado) {
                        productoActualizado.querySelector(".unidades").innerText = parseInt(productoActualizado.querySelector(".unidades").innerText) + 1;
                        count__producto.innerText = parseInt(count__producto.innerText) + 1;
                        span.parentElement.parentElement.parentElement.parentElement.querySelectorAll(".producto__carrito__precio")[1].innerText = "Importe total: " + infoProducto.precio * infoProducto.cantidad + ",00 \u20AC";
                    } else {
                        carrito__body.innerHTML += nuevoProducto;
                        count__producto.innerText = parseInt(count__producto.innerText) + 1;
                    }
                } else {
                    carrito__body.innerHTML = nuevoProducto;
                    count__producto.innerText = parseInt(count__producto.innerText) + 1;
                }
                carrito__footer.innerHTML = nuevoFooterCarrito;
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