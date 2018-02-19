<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="mainPantallas">
		<title>Listado de publicaciones</title>
	</head>
	<body>

		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="navbar navbar-inverse">
						<div class="container-fluid">
							<ul class="nav navbar-nav">
								<li>
                  <a class="home navbar-brand" href="${createLink(controller : 'usuario' ,action : 'inicioUsuario', params: [idUsuario : usuarioInstance.id])}">
                    Volver al inicio
                  </a>
                </li>
								<li> <g:link controller="publicacion" action="crearPublicacion" params="[idUsuario:"${usuarioInstance.id}"]">Crear publicaci&oacute;n</g:link></li>
								<li> <g:link controller="${"mensajePrivado"}" params="[idUsuario:"${usuarioInstance.id}"]">${"Mensajes privados"}</g:link> </li>
								<li> <g:link controller="${"usuario"}" action="listaUsuarios" params="[idUsuario:"${usuarioInstance.id}"]">${"Buscar usuarios"}</g:link> </li>
								<li> <g:link controller="${"usuario"}">Cerrar sesi&oacute;n</g:link> </li>
							</ul>
						</div>
					</div>
				</div>
			</div>

		<h1>Buscar publicaciones:</h1>
		<g:form action="listaPublicaciones" params="[idUsuario:"${usuarioInstance.id}"]">
			<g:select name="idCatedra" from="${catedras}" optionValue="${{it.materia.nombre + ", " + "catedra " + it.profesor.nombre}}" optionKey="id" />
			<g:submitButton name="Buscar"/>
		</g:form>

		<div>
			<h1><g:message code="Ver publicaciones" args="[entityName]" /></h1>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="id" title="${message(code: 'publicacion.usuarioCreador.label', default: 'Numero publicacion')}" />
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

						<td>${fieldValue(bean: publicacionInstance, field: "id")}</td>
						<td>${fieldValue(bean: publicacionInstance, field: "usuarioCreador.nombre")}</td>
						<td>${fieldValue(bean: publicacionInstance, field: "catedraRelacionada.materia.nombre")}</td>
            <td>${fieldValue(bean: publicacionInstance, field: "catedraRelacionada.profesor.nombre")}</td>
            <td>${fieldValue(bean: publicacionInstance, field: "estado")}</td>
            <td><g:link action="verPublicacion" params="[idUsuario:"${usuarioInstance.id}", idPublicacion:"${publicacionInstance.id}"]">${"Ver"}</g:link></td>

					</tr>
				</g:each>
				</tbody>
			</table>
		</div>
	</div>
	</body>
</html>
