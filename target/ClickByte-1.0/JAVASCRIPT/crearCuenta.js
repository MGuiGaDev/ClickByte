/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let personal;
let dni;

let shipping;

let profile;
let email;
let avatar;
let password;
let confirm;

let siguiente;
let anterior;
let show;
let crear;


const regexSoloLetras = /^[A-Z]+$/i;

document.addEventListener("DOMContentLoaded", asignarEventos);

function asignarEventos() {

    personal = document.getElementById("personal");
    personal.addEventListener("input", function (evento) {
        validarPersonal(evento);
    });
    dni = document.getElementById("dni");
    dni.addEventListener("blur", validarDNI);

    shipping = document.getElementById("shipping");
    shipping.addEventListener("input", function (evento) {
        validarShipping(evento);
    });

    profile = document.getElementById("profile");
    email = document.querySelector("#email");
    email.addEventListener(("blur"), comprobarEmail);
    password = document.getElementById("password");
    confirm = document.getElementById("confirm");
    profile.addEventListener("input", function (evento) {
        validarProfile(evento);
    });

    show = document.getElementById("show");
    show.addEventListener("click", mostrarPassword);


    avatar = document.getElementById("avatar");
    crear = document.getElementById("crear");

    siguiente = document.getElementById("siguiente");
    siguiente.addEventListener("click", function (evento) {
        avanzar(evento);
    });
    anterior = document.getElementById("anterior");
    anterior.addEventListener("click", function (evento) {
        retroceder(evento);
    });
}

function mostrarPassword() {
    if (show.checked) {
        password.setAttribute("type", "text");
        confirm.setAttribute("type", "text");
    } else {
        password.setAttribute("type", "password");
        confirm.setAttribute("type", "password");
    }
}

function validarProfile(evento) {
    if (evento.target.tagName === "INPUT") {
        let inputFocus = evento.target;
        switch (inputFocus.id) {
            case "email":

                if (inputFocus.value.length <= 8 && !inputFocus.value.includes('@')) {
                    inputFocus.nextElementSibling.style = "color: red;";
                    inputFocus.focus();
                } else {
                    inputFocus.nextElementSibling.style = "color: black;";
                }
                break;
            case "password":
                if (!validarPassword(inputFocus)) {
                    inputFocus.nextElementSibling.style = "color: red;";
                    inputFocus.focus();
                } else {
                    inputFocus.nextElementSibling.style = "color: black;";
                }
                break;
            case "confirm":
                if (!validarConfirm(inputFocus)) {
                    inputFocus.nextElementSibling.style = "color: red;";
                    inputFocus.focus();
                } else {
                    inputFocus.nextElementSibling.style = "color: black;";
                }
                break;
        }
    }
}
function comprobarEmail() {
    let value = email.value;
    let url = "../ValidarCuenta";
    $.ajax({
        type: 'POST',
        url: url,
        data: {
            accion: "validarEmail",
            name: value
        },
        success: function (data) {

            if (data.tipo === 'error') {
                email.nextElementSibling.style = "color: red;";
                email.focus();
            } else {
                email.nextElementSibling.style = "color: black;";
            }

        },
        error: function () {
            console.log("error");
        }
    });
}

function validarPassword(password) {
    /*
     * VALIDACIÓN:
     * - Que contenga: /^[..]$/
     * - Al menos un número: (?=.*[0-9])
     * - Al menos una letra mayúscula o minúscula: (?=.*[a-zA-Z])
     * - Al menos un caracter especial: (?=.*[@#$%^&+=])
     *  + No puede contener ningún espacio: (?=\S+$)
     * - Un mínimo de 8 caracteres: {8,}
     */
    let resultado;
    let regex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/;
    if (!regex.test(password.value))
        resultado = false;
    else
        resultado = true;
    return resultado;
}

function validarConfirm(confirm) {
    let resultado;
    if (password.value === "") {
        resultado = false;
        password.focus();
    } else if (password.value !== confirm.value) {
        resultado = false;
    } else {
        resultado = true;
    }
    return resultado;
}

function validarShipping(evento) {
    if (evento.target.tagName === "INPUT") {
        let inputFocus = evento.target;
        switch (inputFocus.id) {
            case "direccion":
                if (inputFocus.value.length <= 2) {
                    inputFocus.nextElementSibling.style = "color: red;";
                    inputFocus.focus();
                } else {
                    inputFocus.nextElementSibling.style = "color: black;";
                }
                break;
            case "localidad":
                if (inputFocus.value.length <= 2 || !regexSoloLetras.test(inputFocus.value)) {
                    inputFocus.nextElementSibling.style = "color: red;";
                    inputFocus.focus();
                } else {
                    inputFocus.nextElementSibling.style = "color: black;";
                }
                break;
            case "cp":
                if (inputFocus.value.length !== 5 || inputFocus.value < 1000 || inputFocus.value > 52999) {
                    inputFocus.nextElementSibling.style = "color: red;";
                    inputFocus.focus();
                } else {
                    inputFocus.nextElementSibling.style = "color: black;";
                }
                break;
            case "provincia":
                if (inputFocus.value.length <= 3 || !regexSoloLetras.test(inputFocus.value)) {
                    inputFocus.nextElementSibling.style = "color: red;";
                    inputFocus.focus();
                } else {
                    inputFocus.nextElementSibling.style = "color: black;";
                }
                break;
        }
    }
}

function validarPersonal(evento) {
    if (evento.target.tagName === "INPUT") {
        let inputFocus = evento.target;
        switch (inputFocus.id) {
            case "nombre":
            case "apellidos":
                if (inputFocus.value.length <= 2) {
                    inputFocus.nextElementSibling.style = "color: red;";
                    inputFocus.focus();
                } else {
                    inputFocus.nextElementSibling.style = "color: black;";
                }
                break;
            case "dni":
                if(dni.type==="text"){
                    dni.type="number";
                }
                if (inputFocus.value.length !== 8) {
                    inputFocus.nextElementSibling.style = "color: red;";
                    inputFocus.focus();
                } else {
                    inputFocus.nextElementSibling.style = "color: black;";
                }
                break;
            case "telefono":
                if (inputFocus.value.length !== 9) {
                    inputFocus.nextElementSibling.style = "color: red;";
                    inputFocus.focus();
                } else {
                    inputFocus.nextElementSibling.style = "color: black;";
                }
                break;
        }
    }

}
function validarDNI() {
    let url = "../ValidarCuenta";
    let value = dni.value;
    $.ajax({
        type: "POST",
        url: url,
        data: {
            accion: "validarDni",
            dni: value
        },
        success: function (data) {

            if (data.tipo === 'error') {

                $("#dni").val('');
                $("#dni").focus();
                //$.notify(data.letra, {className: data.tipo});
            } else {
                dni.type = "text";
                dni.value = value + data.letra;
            }

        },
        error: function () {
            //$.notify('Error en servidor. No se pudo comprobar el DNI.', {className: 'error'});
            $("#dni").val('');
            $("#dni").focus();
        }
    });
}
function validarSiguiente(paso) {
    let coleccionMensajes = Array.from(paso.querySelectorAll("p"));
    let coleccionInputs = Array.from(paso.querySelectorAll("input"));

    if (coleccionMensajes.some(elemento => elemento.style.color === "red") || coleccionInputs.some(elemento => elemento.value === "")) {
        return false;
    } else {
        return true;
    }

}

function avanzar(evento) {
    evento.preventDefault();
    switch (true) {
        case personal.classList.contains("activo"):
            if (!validarSiguiente(personal)) {
                console.log(validarSiguiente(personal));
            } else {
                personal.classList.remove("activo");
                shipping.classList.add("activo");
                anterior.removeAttribute("hidden");
            }

            break;
        case shipping.classList.contains("activo"):
            if (!validarSiguiente(shipping)) {
                console.log(validarSiguiente(shipping));
            } else {
                shipping.classList.remove("activo");
                profile.classList.add("activo");
            }
            break;
        case profile.classList.contains("activo"):
            if (!validarSiguiente(profile)) {
                console.log(validarSiguiente(profile));
            } else {
                profile.classList.remove("activo");
                avatar.classList.add("activo");
                crear.removeAttribute("hidden");
                siguiente.setAttribute("hidden", true);
            }
            break;
    }
}

function retroceder(evento) {
    evento.preventDefault();
    switch (true) {
        case shipping.classList.contains("activo"):
            anterior.setAttribute("hidden", true);
            shipping.classList.remove("activo");
            personal.classList.add("activo");
            break;
        case profile.classList.contains("activo"):
            profile.classList.remove("activo");
            shipping.classList.add("activo");
            break;
        case avatar.classList.contains("activo"):
            crear.setAttribute("hidden", true);
            avatar.classList.remove("activo");
            profile.classList.add("activo");
            siguiente.removeAttribute("hidden");
            break;
    }
}