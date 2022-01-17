<%-- 
    Document   : error404
    Created on : 17-ene-2022, 10:34:47
    Author     : Manuel Guillén Gallardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ClickByte</title>
        <style><%@include file="/CSS/estiloPaginaError.css"%></</style>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="shortcut icon" href="logo_clico.ico" type="image/x-icon" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <main>
            <div class="msg">
                <h1>error 404</h1>
                <h2>Página no encontrada</h2>
                <form action="FrontController" method="post">
                    <button class="btn" name="volver" value="volver">Ir a inicio</button>
                </form>
            </div>
        </main>
    </body>
</html>
