<%-- 
    Document   : usuario.jsp
    Created on : 08-ene-2022, 0:19:26
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
        <title>ClickByte</title>
        <style><%@include file="../CSS/index.css"%></</style>
        <style><%@include file="../CSS/modales.css"%></</style>
        <style><%@include file="../CSS/datosUsuario.css"%></</style>
        <style><%@include file="../CSS/pedidoFinalizado.css"%></</style>
        <style><%@include file="../CSS/listaPedidos.css"%></</style>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="shortcut icon" href="logo_clico.ico" type="image/x-icon" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body id="body">
        <%@include file="../INCLUDES/header.inc"%>
        <main>
            <%@include file="../INCLUDES/menuCategorias.inc"%>
            <c:choose>
                <c:when test="${requestScope.verPerfil!=null}">
                    <c:set var="usuario" value="${sessionScope.usuario}"/> 
                    <div class="presentacion__vista__usuario">
                        <div class="tilte__vista">
                            <h1><c:out  value="${usuario.email}"/></h1>
                            <br>
                            <h3>Último acceso: <fmt:formatDate pattern="dd/MM/yyyy"  value="${usuario.ultimoAcceso}"/></h3>
                        </div>
                        <div class="container__datos__usuario">
                            <h1 class="title__apartado">Mis datos Personales</h1>
                            <div class="datos__personales">
                                <div class="form__group">
                                    <label for="nombre">Nombre</label>
                                    <input type="text" value="${usuario.nombre}" id="nombre"/> 
                                </div>
                                <div class="form__group">
                                    <label for="apellidos">Apellidos</label>
                                    <input type="text" value="${usuario.apellidos}" id="apellidos"/>
                                </div>
                                <div class="form__group">
                                    <label for="nif">DNI</label>
                                    <p class="nif"><c:out value="${usuario.nif}"/></p>
                                </div>
                                <div class="form__group">
                                    <label for="telefono">Telefóno</label>
                                    <input type="text" value="${usuario.telefono}" id="telefono"/>
                                </div>
                            </div>
                            <button class="btn__actualizar">ACTUALIZAR</button>
                            <hr>
                            <br>
                            <h1 class="title__apartado">Mis datos de compra</h1>
                            <div class="datos__shipping">
                                <div class="form__group">
                                    <label for="direccion">Dirección</label>
                                    <input type="text" value="${usuario.direccion}" id="direccion"/>  
                                </div>
                                <div class="form__group">
                                    <label for="cp">Código Postal</label>
                                    <input type="text" value="${usuario.codigoPostal}" id="cp"/>
                                </div>
                                <div class="form__group">
                                    <label for="localidad">Localidad</label>
                                    <input type="text" value="${usuario.localidad}" id="localidad"/>
                                </div>
                                <div class="form__group">
                                    <label for="provincia">Provincia</label>
                                    <input type="text" value="${usuario.provincia}" id="provincia"/>
                                </div>

                            </div>
                            <button class="btn__actualizar">ACTUALIZAR</button>
                            <hr>
                            <br>
                            <h1 class="title__apartado">Mis datos de  cuenta</h1>
                            <div class="datos__cuenta">
                                <h2>Correo electrónico</h2>
                                <div class="form__group">
                                    <p><c:out value="${usuario.email}"/></p>
                                </div>
                                <br>
                                <h2>Contraseña</h2>
                                <div class="container__pass">
                                    <div class="form__group">
                                        <label for="password">Antigua contraseña</label>
                                        <input type="password" value="password" id="password"/>  
                                    </div>
                                    <div class="form__group">
                                        <label for="password">Nueva contraseña</label>
                                        <input type="password" value="password" id="password"/>
                                    </div>
                                    <div class="form__group">
                                        <label for="password">Repetir contraseña</label>
                                        <input type="password" value="password" id="password"/> 
                                    </div>
                                </div>
                                <div>
                                    <label class="mostrar"><input type="checkbox"> Mostrar contraseñas</label>
                                </div>
                                <br>
                                <h2>Avatar de usuario</h2>
                                <div class="container__imagen">
                                    <div class="form__group">
                                        <input type="file" />
                                    </div>
                                </div>
                            </div>
                            <button class="btn__actualizar">ACTUALIZAR</button>
                        </div>
                    </div>
                </c:when>
                <c:when test="${requestScope.pedidoFinalizado!=null}">
                    <c:set var="usuarioPF" value="${sessionScope.usuario}"/> 
                    <c:set var="pedidoF" value="${requestScope.pedidoFinalizado}"/> 
                    <c:set var="listaP" value="${requestScope.listaProductosPedidoFinalizado}"/> 
                    <div class="pedido__finalizado">
                        <div class="finalizado__header">
                            <h1>¡Enhorabuena, <c:out value="${usuarioPF.nombre}"/> <c:out value="${usuarioPF.apellidos}"/>!</h1>
                            <h2>Su pedido ha sido realizado con éxito</h2>
                        </div>
                        <div class="finalizado__body">
                            <h1>Detalle pedido</h1>
                            <h2>Código de pedido: <c:out value="${pedidoF.idPedido}"/></h2>
                            <h2>Fecha: <c:out value="${pedidoF.fecha}"/></h2>
                            <table>
                                <tr>
                                    <th>Orden</th>
                                    <th>Nombre</th>
                                    <th>Marca</th>
                                    <th>Cantidad</th>
                                    <th>Precio</th>
                                    <th>Total</th>
                                </tr>
                                <c:forEach var="lp" items="${listaP}">
                                    <tr  class="tr__dato">
                                        <td><c:out value="${lp.orden}"/></td>
                                        <td><c:out value="${lp.nombre}"/></td>
                                        <td><c:out value="${lp.marca}"/></td>
                                        <td><c:out value="${lp.cantidad}"/></td>
                                        <td><fmt:formatNumber type="currency" minFractionDigits="2" value="${lp.precioUnitario}" /></td>
                                        <td><fmt:formatNumber type="currency" minFractionDigits="2" value="${lp.cantidad*lp.precioUnitario}" /></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="4"><span>Importe:</span></td>
                                    <td colspan="2"><fmt:formatNumber type="currency" minFractionDigits="2" value="${pedidoF.importe}" /></td>
                                </tr>
                                <tr>
                                    <td colspan="4"><span>IVA 21%:</span></td>
                                    <td colspan="6"><fmt:formatNumber type="currency" minFractionDigits="2" value="${pedidoF.iva}" /></td>
                                </tr>
                                <tr>
                                    <td colspan="4"><span style="font-size: 20px">Importe total:</span></td>
                                    <td colspan="6"><span style="font-size: 20px"><fmt:formatNumber type="currency" minFractionDigits="2" value="${pedidoF.iva + pedidoF.importe}" /></span></td>
                                </tr>
                            </table>
                        </div>
                        <div class="finalizado__footer">
                            <form action="OpcionesCuentaUsuarioController" method="POST">
                                <button name="accion" value="volver" class="btn__pedidoF">VOLVER A INICIO</button>
                                <button name="accion" value="verPerfil" class="btn__pedidoF">VER MI PERFIL</button>
                                <button name="accion" value="verPedidos" class="btn__pedidoF">VER MIS PEDIDOS</button>
                            </form>
                        </div>
                    </div>
                </c:when>
                <c:when test="${requestScope.verPedidos!=null}">
                    <c:set var="us" value="${sessionScope.usuario}"/>
                    <c:choose>
                        <c:when test="${requestScope.listaPedidos==null}">
                            <div class="lista__pedidos">
                                <div>
                                    <h1>
                                        <c:out value="${us.nombre}"/>, no tienes ningún pedido todavía. 
                                    </h1>
                                </div>
                            </div>
                            <div class="listaPedidos__footer">
                                <form action="OpcionesCuentaUsuarioController" method="POST">
                                    <button name="accion" value="volver" class="btn__pedidoF">VOLVER A INICIO</button>
                                    <button name="accion" value="verPerfil" class="btn__pedidoF">VER MI PERFIL</button>
                                </form>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.listaPedidos!=null}">
                            <c:set var="listaPedidos" value="${requestScope.listaPedidos}"/>
                            <div class="lista__pedidos">
                                <div>
                                    <h1><c:out value="${us.nombre}"/>, tienes un total de <c:out value="${listaPedidos.size()}"/> 
                                        <c:choose>
                                            <c:when test="${listaPedidos.size()==1}"> pedido</c:when>
                                            <c:when test="${listaPedidos.size()>1}"> pedidos</c:when>
                                        </c:choose>
                                    </h1>
                                </div>
                                <table>
                                    <tr>
                                        <th>Código</th>
                                        <th>Fecha</th>
                                        <th>Importe</th>
                                        <th>Acciones</th>
                                    </tr>
                                    <c:forEach var="pedido" items="${listaPedidos}">
                                        <tr class="tr__dato">
                                            <td><c:out value="${pedido.idPedido}"/></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${pedido.fecha}"/></td>
                                            <td><fmt:formatNumber type="currency" minFractionDigits="2" value="${pedido.importe}" /></td>
                                            <td><button class="btn__verDetalle">Ver detalle</button></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <div class="listaPedidos__footer">
                                    <form action="OpcionesCuentaUsuarioController" method="POST">
                                        <button name="accion" value="volver" class="btn__pedidoF">VOLVER A INICIO</button>
                                        <button name="accion" value="verPerfil" class="btn__pedidoF">VER MI PERFIL</button>
                                    </form>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
            <div class="container__productos"></div>
            <div class="container__resultado__busqueda"></div>
        </main>
        <%@include file="../INCLUDES/footer.inc"%>
        <script><%@include file="../JAVASCRIPT/header.js"%></script>
        <script><%@include file="../JAVASCRIPT/gestionarCarrito.js"%></script>
    </body>
</html>

