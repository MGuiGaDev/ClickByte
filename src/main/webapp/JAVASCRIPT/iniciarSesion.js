/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let login__body;
let email;
let password;
let show;
document.addEventListener("DOMContentLoaded", asignarEventos);

function asignarEventos() {
    login__body = document.getElementById("login__body");
    email = document.getElementById("email");
    password = document.getElementById("password");
    show = document.getElementById("show");

    email.addEventListener(("blur"), comprobarEmail);
    password.addEventListener(("blur"), comprobarPassword);


    login__body.addEventListener("focusout", function (evento) {
        validarCampos(evento);
    });
    show.addEventListener("click", mostrarPassword);
}

function validarCampos(evento) {
    if (evento.target.id === "password") {
        if (email.value !== "" && password.value !== "") {
            document.getElementById("btn__login").removeAttribute("disabled");
        }
    } else {

    }
}
function comprobarEmail() {
    let value = email.value;
    let url = "AjaxValidarCuentaController";
    $.ajax({
        type: 'POST',
        url: url,
        data: {
            accion: "validarEmailLogin",
            name: value
        },
        success: function (data) {

            if (data.tipo === 'error') {
                email.style = "border-bottom: 1px solid red;";
                email.focus();
            } else {
                email.style = "border-bottom: 1px solid black;";
            }

        },
        error: function () {
            console.log("error");
        }
    });
}
function comprobarPassword() {
    let valuePassword = password.value;
    let url = "AjaxValidarCuentaController";
    if (email.value === "") {
        email.focus();
    } else {
        let valueEmail = email.value;
        $.ajax({
            type: 'POST',
            url: url,
            data: {
                accion: "validarPasswordLogin",
                password: valuePassword,
                email: valueEmail
            },
            success: function (data) {

                if (data.tipo === 'error') {
                    password.style = "border-bottom: 1px solid red;";
                    password.focus();
                } else {
                    password.style = "border-bottom: 1px solid black;";
                }

            },
            error: function () {
                console.log("error");
            }
        });
    }

}

function mostrarPassword() {
    if (show.checked) {
        password.setAttribute("type", "text");
    } else {
        password.setAttribute("type", "password");
    }
}