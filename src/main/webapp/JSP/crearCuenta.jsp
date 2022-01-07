<%-- 
    Document   : crearCuenta
    Created on : 23-dic-2021, 17:01:47
    Author     : Manuel Guillén Gallardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/INCLUDES/meta.inc" %>
        <title>Pixer</title>
        <style><%@include file="../CSS/crearCuenta.css"%></</style>
        <link rel="shortcut icon" href="../logo_clico.ico" type="image/x-icon" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <form action="${contexto}/CrearCuenta" method="post" class="form" enctype="multipart/form-data">
            <div class="form__header">
                <h1>CREAR CUENTA</h1>
            </div>
            <div class="form__body">
                <div class="data activo" id="personal">
                    <div class="data__header">
                        <h2>Datos personales</h2>
                    </div>
                    <div class="data__body">
                        <div class="form__group">
                            <label for="nombre" class="label">Nombre*</label>
                            <input type="text" class="inputText" minlength="3" id="nombre" name="nombre"
                                   placeholder="Ej: Joaquín">

                            <p class="input__msg">Debe contener un mínimo de 3 caracteres y solo letras</p>
                        </div>

                        <div class="form__group">
                            <label for="apellidos" class="label">Apellidos*</label>
                            <input type="text" class="inputText" minlength="3" id="apellidos" name="apellidos"
                                   placeholder="Ej: Hurtado Temprano">

                            <p class="input__msg">Debe contener un mínimo de 3 caracteres y solo letras</p>
                        </div>
                        <div class="form__group">
                            <label for="dni" class="label">DNI*</label><input type="number" class="inputText" id="dni"
                                                                              name="nif" placeholder="Ej: 12312312">
                            <p class="input__msg">Debe contener 8 caracteres numéricos</p>
                        </div>
                        <div class="form__group">
                            <label for="telefono" class="label">Teléfono*</label>
                            <input type="number" class="inputText" id="telefono" name="telefono"
                                   placeholder="Ej: 678574557">
                            <p class="input__msg">Debe contener 9 caracteres numéricos</p>
                        </div>
                    </div>
                </div>
                <div class="data" id="shipping">
                    <div class="data__header">
                        <h2>Datos de compra</h2>
                    </div>
                    <div class="data__body">
                        <div class="form__group">
                            <label for="direccion" class="label">Dirección*</label>
                            <input type="text" class="inputText" id="direccion" name="direccion" minlength="3"
                                   placeholder="Ej: Avd. Castro, nº 23, piso 3ºB">
                            <p class="input__msg">Debe contener un mínimo de 3 caracteres</p>
                        </div>
                        <div class="form__group">
                            <label for="cp" class="label">Código Postal*</label>
                            <input type="number" class="inputText" id="cp" name="codigoPostal" placeholder="Ej: 06300">
                            <p class="input__msg">Debe contener un mínimo de 5 caracteres</p>
                        </div>
                        <div class="form__group">
                            <label for="localidad" class="label">Localidad*</label>
                            <input type="text" class="inputText" id="localidad" name="localidad"
                                   placeholder="Ej: Villafranca de los Barros">
                            <p class="input__msg">Debe contener un mínimo de 3 caracteres y solo letras.</p>
                        </div>
                        <div class="form__group">
                            <label for="provincia" class="label">Provincia*</label>
                            <input type="text" class="inputText" id="provincia" name="provincia" placeholder="Ej: Badajoz">
                            <p class="input__msg">Debe contener un mínimo de 4 caracteres y solo letras.</p>
                        </div>
                    </div>
                </div>
                <div class="data" id="profile">
                    <div class="data__header">
                        <h2>Perfil</h2>
                    </div>
                    <div class="data__body">
                        <div class="form__group">
                            <label for="email" class="label">@Email*</label>
                            <input type="email" class="inputText" id="email" name="email"
                                   placeholder="Ej: joaquin08@gmail.com">
                            <p class="input__msg">Debe contener un mínimo de 8 caracteres y el signo @.</p>
                        </div>
                        <div class="form__group">
                            <label for="password" class="label">Contraseña*</label>
                            <input type="password" class="inputText" id="password" name="password"
                                   placeholder="Ej: 123asd$=">
                            <p class="input__msg">*Utilice ocho catacteres como mínimo con una combinación de letras,
                                números y
                                símbolos.
                            </p>
                        </div>
                        <div class="form__group">
                            <label for="confirm" class="label">Confirmación*</label>
                            <input type="password" class="inputText" id="confirm" placeholder="Ej: 123asd$=">
                            <p class="input__msg">*La contraseña y la confirmación deben ser iguales.
                            </p>
                        </div>
                        <div class="form__group show">
                            <input type="checkbox" id="show">
                            <label for="show">Mostrar contraseña</label>
                        </div>
                    </div>
                </div>
                <div class="data" id="avatar">
                    <div class="data__header">
                        <h2>Avatar</h2>
                    </div>
                    <div class="data__body">
                        <div class="form__group group__avatar">
                            <input type="file" class="inputText file__avatar" id="file__avatar" name="avatar">
                            <div class="avatar__previous">
                                <img src="../IMAGENES/AVATARES/default.png" alt="Avatar usuario" class="avatar__img" id="avatar__img">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form__footer">
                <button name="boton" value="cancelar">CANCELAR</button>
                <button id="anterior" hidden>
                    <span class="material-icons">arrow_back</span>
                </button>
                <button id="siguiente">
                    <span class="material-icons">arrow_forward</span>
                </button>
                <button name="boton" value="crear" id="crear" class="crear" hidden>CREAR</button>
            </div>
        </form>
        <script><%@include file="../JAVASCRIPT/crearCuenta.js"%></script>
    </body>
</html>
