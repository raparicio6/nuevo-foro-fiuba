
<%@ page import="nuevo_foro_fiuba.Publicacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main2">
		<g:set var="entityName" value="${message(code: 'publicacion.label', default: 'Publicacion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">

		</div>
		<div id="list-usuario" class="content scaffold-list" role="main">
			<h1><g:message code="Seleccione un usuario" args="[entityName]" /></h1>
			<table>
				<thead>
					<tr>
            <g:sortableColumn property="nombre" title="${message(code: 'usuario.nombre.label', default: 'Nombre')}" />
            <g:sortableColumn property="apellido" title="${message(code: 'usuario.apellido.label', default: 'Apellido')}" />
            <g:sortableColumn property="nombreUsuario" title="${message(code: 'usuario.nombreUsuario.label', default: 'Nick')}" />
            <g:sortableColumn property=" " title="${message(code: ' ', default: ' ')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${usuarioInstanceList}" status="i" var="usuarioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: usuarioInstance, field: "nombre")}</td>
            <td>${fieldValue(bean: usuarioInstance, field: "apellido")}</td>
            <td>${fieldValue(bean: usuarioInstance, field: "nombreUsuario")}</td>
            <td><g:link action="inicioUsuario" id="${usuarioInstance.id}">${"Iniciar sesion"}</g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${usuarioInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
