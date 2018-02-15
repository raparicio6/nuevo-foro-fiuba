
<%@ page import="nuevo_foro_fiuba.Usuario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="mainPantallas">
		<g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>

		<div class="row">
			<div class="col-md-12">
				<div class="navbar navbar-inverse">
					<div class="container-fluid">
						<ul class="nav navbar-nav">
							<li><a class="home navbar-brand" href="${createLink(uri: '/')}">
								Volver al inicio
							</a></li>
							<li><a>
								${usuarioInstance.nombre}
							</a></li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Sitios<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li> <g:link controller="publicacion" action="listaPublicaciones" max="10" params="[idUsuario:"${usuarioInstance.id}"]">${"Ver publicaciones"}</g:link> </li>
									<li> <g:link controller="${"crearMensaje"}" id="${usuarioInstance.id}">${"Crear mensaje privado"}</g:link> </li>
									<li> <g:link controller="${"usuario"}" action="listaUsuarios" params="[idUsuario:"${usuarioInstance.id}"]">${"Buscar usuarios"}</g:link> </li>
									<li> <g:link controller="${"usuario"}">${"Cerrar sesion"}</g:link> </li>
								</ul>
							</li>
						</ul>
					</div>
				</div>

		<a href="#list-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<content tag="nav">
	      <a><li>${usuarioInstance.nombre}</li></a>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="Volver al inicio"/></a></li>
			</content>
		</div>

		<h1>Filtrar usuarios</h1>
		<g:form controller="usuario" action="listaUsuarios" params="[max:'10', idUsuario:"${usuarioInstance.id}"]">
			<!-- hacer que acepten floats -->
			<div>Promedio de calificaciones minimo: <g:field type="float" min="0" max="5" name="promedioMin"/></div>
			<div>Promedio de calificaciones maximo: <g:field type="float" min="0" max="5" name="promedioMax"/></div>
			<div>Materia cursada: <g:select  name="idMateria" from="${materias}" optionValue ="nombre" optionKey = "id" noSelection="${['null':'Elegir materia...']}"/></div>
		<div><g:submitButton name="Filtrar" value="Filtrar"/></div>
		</g:form>

		<div>
			<table>
				<thead>
					<tr>
            <g:sortableColumn property="nombre" title="${message(code: 'usuario.nombre.label', default: 'Nombre')}" />
            <g:sortableColumn property="promedioCalificaciones" title="${message(code: 'usuario.promedioCalificaciones.label', default: 'Promedio de calificaciones')}" />
            <g:sortableColumn property="cursadas.size()" title="${message(code: 'usuario.cursadas.size().label', default: 'Materias cursadas')}"/>
					</tr>
				</thead>
				<tbody>
				<g:each in="${usuarioInstanceList}" status="i" var="usuarioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: usuarioInstance, field: "nombre")}</td>
            <td>${usuarioInstance.promedioCalificaciones.round(2)}</td>
            <td><g:link action="verMateriasCursadas" params="[idUsuario:"${usuarioInstance.id}"]">${"Ver"}</g:link></td>
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
