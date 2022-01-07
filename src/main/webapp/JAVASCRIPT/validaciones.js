/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let form;
let imagenAvatar;
let user__account;
let cuadroInformativo;
let open__form__info;
let close__form__info;
let createAccount;
let email;
let error__element = document.createElement("div");
error__element.classList.add("container__errmsg");
let error__msg =
        `
        <span class="material-icons report">report_problem</span>
        <p class="errmsg"></p>
    `;
error__element.innerHTML = error__msg;
//document.getElementById("password").parentElement.insertAdjacentElement("afterend", error__element);
//http://creandobits.blogspot.com/2016/09/obtener-provincia-traves-del-codigo.html
let cp_provincias = {
      01: "\u00C1lava", 02: "Albacete", 03: "Alicante", 04: "Almer\u00EDa", 05: "\u00C1vila",
      06: "Badajoz", 07: "Baleares", 08: "Barcelona", 09: "Burgos", 10: "C\u00E1ceres",
      11: "C\u00E1diz", 12: "Castell\u00F3n", 13: "Ciudad Real", 14: "C\u00F3rdoba", 15: "Coruña",
      16: "Cuenca", 17: "Gerona", 18: "Granada", 19: "Guadalajara", 20: "Guip\u00FAzcoa",
      21: "Huelva", 22: "Huesca", 23: "Ja\u00E9n", 24: "Le\u00F3n", 25: "L\u00E9rida",
      26: "La Rioja", 27: "Lugo", 28: "Madrid", 29: "M\u00E1laga", 30: "Murcia",
      31: "Navarra", 32: "Orense", 33: "Asturias", 34: "Palencia", 35: "Las Palmas",
      36: "Pontevedra", 37: "Salamanca", 38: "Santa Cruz de Tenerife", 39: "Cantabria", 40: "Segovia",
      41: "Sevilla", 42: "Soria", 43: "Tarragona", 44: "Teruel", 45: "Toledo",
      46: "Valencia", 47: "Valladolid", 48: "Vizcaya", 49: "Zamora", 50: "Zaragoza",
      51: "Ceuta", 52: "Melilla"
    };

let password;
let confirm__password;

document.addEventListener("DOMContentLoaded", asignarEventos);

function asignarEventos() {
    
    /*
     * REALIZAR:
     * [o] Validación nombre
     * [o] Validación apellido
     * [o] Validación DNI
     * [o] Validación email
     * [o] Validación teléfono
     * [x] Validación dirección
     * [x] Validación código postal
     * [x] Validación Localidad
     * [x] Validación Provincia
     * [o] Validación password
     * [o] Validación confirm
     * 
     */

    form = document.getElementById("form__account");

    form.addEventListener("focusin", function (evento) {
        colorFocusInFieldSet(evento);
    });

    form.addEventListener("focusout", function (evento) {
        colorFocusOutFieldSet(evento);
    });

    //controlar autocompletado
    /*
     form.addEventListener("input", function (evento) {
     globalColorFocus(evento);
     });*/
    email = document.querySelector("#email");
    email.addEventListener(("blur"), comprobarEmail);
            

    user__account = document.querySelector(".user__account");
    user__account.addEventListener("click", function (evento) {
        cambiarVisibilidad(evento);
    });

    modal__info = document.getElementById("info__ventana");
    open__form__info = document.getElementById("form__info");

    open__form__info.addEventListener("click", mostrarInformacion);
    close__form__info = document.getElementById("close");
    close__form__info.addEventListener("click", cerrarInformacion);

    password = document.getElementById("password");
    password.addEventListener("blur", validarPassword);
    confirm__password = document.getElementById("confirm");
    confirm__password.addEventListener("blur", validarConfirm);

    imagenAvatar = document.getElementById("imagenAvatar");
    imagenAvatar.onchange = (function () {
        readURL(this);
    });
    //avatar.addEventListener("change", previsualizarImagen); => no me funciona
     let c = document.getElementById("avatar");
     c.addEventListener("change", () => {
         let se = c.files[0];
         console.log(se)
     });
     
}

function validarPassword(){
    /*
     * VALIDACIÓN:
     * - Que contenga: /^[..]$/
     * - Al menos un número: (?=.*[0-9])
     * - Al menos una letra mayúscula o minúscula: (?=.*[a-zA-Z])
     * - Al menos un caracter especial: (?=.*[@#$%^&+=])
     *  + No puede contener ningún espacio: (?=\S+$)
     * - Un mínimo de 8 caracteres: {8,}
     */
    let regex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/;
    if(!regex.test(password.value)){
        console.log("error");
    } 
}
function validarConfirm(){
    if(password.value === ""){
        password.focus();
    } else if(password.value !== confirm__password.value){
        console.log("error");
    } else {
        console.log("las contraseñas son iguales");
    }
}
function comprobarEmail() {
    let value = email.value;
    console.log(value);
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
                console.log(data.email);
            } else {
                console.log(data.email);
            }

        },
        error: function () {
            console.log("error");
        }
    });
}

function mostrarInformacion() {
    modal__info.showModal();
}

function cerrarInformacion() {
    modal__info.close();
}

function cambiarVisibilidad(evento) {
    if (evento.target.tagName === "SPAN") {
        let icon = evento.target;
        let field = icon.parentElement.firstElementChild;
        field.parentElement.style = "border: 2px solid var(--color-title);";
        field.nextElementSibling.classList.add("label__floating");
        field.classList.add("input");
        field.focus();
        if (icon.innerText === "visibility_off") {
            icon.innerText = "visibility";
            field.setAttribute("type", "text");

        } else {
            icon.innerText = "visibility_off";
            field.setAttribute("type", "password");

        }
    }
}

function colorFocusInFieldSet(evento) {
    if (evento.target.tagName === "INPUT" && !evento.target.files) {
        evento.target.classList.add("input");
        evento.target.parentNode.style = "border: 2px solid var(--c-primary);";
        evento.target.nextElementSibling.classList.add("label__floating");
    }
}

function colorFocusOutFieldSet(evento) {
    if (evento.target.tagName === "INPUT" && !evento.target.files) {
        let input = evento.target;
        if (input.value.length === 0) {
            input.classList.remove("input");
            input.parentElement.style = "border: 2px solid var(--c-primary);";
            input.nextElementSibling.classList.remove("label__floating");
        } else {
            input.classList.add("input");
            input.nextElementSibling.classList.add("label__floating");
        }
    }
}

function readURL(input) {
    /*
 let avatar = document.getElementById("avatar");
     imagenAvatar.addEventListener("change", () =>{
         avatar.value = imagenAvatar.file[0].name;
         console.log(avatar);
     });*/
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById("avatar__img").setAttribute("src", e.target.result);
            let avatar = document.getElementById("avatar");
            avatar.value = input.files[0].name;
        }
        reader.readAsDataURL(input.files[0]); // convert to base64 string
    }
}

