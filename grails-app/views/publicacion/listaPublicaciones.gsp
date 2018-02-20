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
								<li> <g:link controller="${"usuario"}">Cerrar sesi&oacute;n</g:link> </li>
							</ul>
						</div>
					</div>
				</div>
			</div>

		<h1>Buscar publicaciones:</h1>
		<g:form action="listaPublicaciones" params="[idUsuario:"${usuarioInstance.id}"]">
			<g:select name="idCatedra" from="${catedras}" optionValue="${{it.materia.nombre + ", " + "catedra " + it.profesor.nombre}}" optionKey="id" noSelection="${['null':'Elegir materia...']}"/>
			<g:submitButton name="Buscar"/>
		</g:form>

		<div>
			<h1><g:message code="Ver publicaciones" args="[entityName]" /></h1>
			<table>
				<thead>
					<tr>
						<th>Fecha creacion</th>
						<th>Autor</th>
						<th>Materia</th>
						<th>Catedra</th>
						<th>Estado</th>
						<th>Acciones<th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${publicacionInstanceList}" status="i" var="publicacionInstance">
					<tr>

						<td>${fieldValue(bean: publicacionInstance, field: "fechaHoraCreacion")}</td>
						<td>${fieldValue(bean: publicacionInstance, field: "usuarioCreador.nombre")}</td>
						<g:if test="${publicacionInstance.catedraRelacionada}">
							<td>${fieldValue(bean: publicacionInstance, field: "catedraRelacionada.materia.nombre")}</td>
							<td>${fieldValue(bean: publicacionInstance, field: "catedraRelacionada.profesor.nombre")}</td>
						</g:if>
						<g:else>
							<td>Ninguna</td>
							<td>Ninguna</td>
						</g:else>
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
