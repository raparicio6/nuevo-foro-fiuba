
<%@ page import="nuevo_foro_fiuba.Usuario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main2">
		<g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>

		<a href="#list-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<content tag="nav">
	      <a><li>${usuarioInstance.nombre}</li></a>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="Volver al inicio"/></a></li>
			</content>
		</div>

		<h1>Filtrar usuarios</h1>
		<g:form controller="usuario" action="listaUsuarios" id="${usuarioInstance.id}" params="[max:'10']">
			<div>Puntaje minimo: <g:field type="number" min="0" max="5" name="puntajeMin"/></div>
			<div>Puntaje maximo: <g:field type="number" min="0" max="5" name="puntajeMax"/></div>
			<div>Materia: <g:select  name="idMateria" from="${materias}" optionValue ="nombre" optionKey = "id" /></div>
		<div><g:submitButton name="Filtrar" value="Filtrar"/></div>
		</g:form>

		<div>
			<table>
				<thead>
					<tr>
            <g:sortableColumn property="nombre" title="${message(code: 'usuario.nombre.label', default: 'Nombre')}" />
            <g:sortableColumn property="puntajeActual" title="${message(code: 'usuario.puntaje.label', default: 'Puntaje')}" />
            <g:sortableColumn property="cursadas.size()" title="${message(code: 'usuario.cursadas.size().label', default: 'Materias cursadas')}"/>
					</tr>
				</thead>
				<tbody>
				<g:each in="${usuarioInstanceList}" status="i" var="usuarioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: usuarioInstance, field: "nombre")}</td>
            <td>${fieldValue(bean: usuarioInstance, field: "puntajeActual")}</td>
            <td><g:link action="verMaterias" id="${usuarioInstance.id}">${"Ver"}</g:link></td>
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
