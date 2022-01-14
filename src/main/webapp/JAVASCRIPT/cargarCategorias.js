/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let btn__menu;
let modal__menu;
document.addEventListener("DOMContentLoaded", asignarEventos);

function asignarEventos() {
    btn__menu = document.querySelector(".btn__menu");
    modal__menu = document.querySelector(".modal__menu");
    btn__menu.addEventListener("click", visualizarCategorias);

}
function visualizarCategorias() {
    if (modal__menu.classList.contains("modal__menu-activo")) {
        let url = "AjaxListarCategorias";
        $.ajax({
            type: 'POST',
            url: url,
            data: {
                accion: "listarCategorias"
            },
            success: function (data) {

                if (data.tipo === 'success') {
                    let infoCategorias = {
                        cantidad: data.cantidad,
                        precio: data.precio,
                        cantidadTotal: data.cantidadTotal,
                        total: data.total
                    };
                } else if (data.tipo === 'vacio') {

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
