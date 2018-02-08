<%@ page import="nuevo_foro_fiuba.Publicacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main2">
		<g:set var="entityName" value="${message(code: 'publicacion.label', default: 'Publicacion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>

		<a href="#list-publicacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">

			<content tag="nav">
	      <a><li>${usuarioInstance.nombre}</li></a>
	    </content>

			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="Volver al inicio"/></a></li>
			</ul>
		</div>

		<h1>Buscar publicaciones:</h1>
		<g:form action="listaPublicaciones" id="${usuarioInstance.id}">
			<g:select name="idCatedra" from="${catedras}" optionValue="${{it.materia.nombre + ", " + "catedra " + it.profesor.nombre}}" optionKey="id" />
			<g:submitButton name="Buscar"/>
		</g:form>

		<div>
			<h1><g:message code="Ver publicaciones" args="[entityName]" /></h1>
			<table>
				<thead>
					<tr>

            <g:sortableColumn property="usuarioCreador" title="${message(code: 'publicacion.usuarioCreador.label', default: 'Autor')}" />
            <g:sortableColumn property="materiaRelacionada" title="${message(code: 'publicacion.catedraRelacionada.label', default: 'Materia')}" />
						<g:sortableColumn property="materiaRelacionada" title="${message(code: 'publicacion.catedraRelacionada.label', default: 'Catedra')}" />
						<g:sortableColumn property="estado" title="${message(code: 'publicacion.estado.label', default: 'Estado')}" />
            <g:sortableColumn property=" " title="${message(code: ' ', default: ' ')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${publicacionInstanceList}" status="i" var="publicacionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>${fieldValue(bean: publicacionInstance, field: "usuarioCreador.nombre")}</td>
						<td>${fieldValue(bean: publicacionInstance, field: "catedraRelacionada.materia.nombre")}</td>
            <td>${fieldValue(bean: publicacionInstance, field: "catedraRelacionada.profesor.nombre")}</td>
            <td>${fieldValue(bean: publicacionInstance, field: "estado")}</td>
            <td><g:link action="verPublicacion" id="${publicacionInstance.id}" params="[idUsuario:"${usuarioInstance.id}"]">${"Ver"}</g:link></td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${publicacionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
