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

			<br/>

			<div class="row">
				<div class="col-md-12">
					<h1>
						<span>
							Buscar publicaciones
						</span>
					</h1>
				</div>
			</div>


			<g:form action="listaPublicaciones" params="[idUsuario:"${usuarioInstance.id}"]">
				<div class="row">
					<div class="col-md-12">
						<g:select name="idCatedra" from="${catedras}" optionValue="${{it.materia.nombre + ", " + "catedra " + it.profesor.nombre}}" optionKey="id" noSelection="${['null':'Elegir materia...']}"/>
							<!-- <g:submitButton name="Buscar"/> -->
								<button type="submit" title="Buscar" style="background-color: Transparent;border: none;font-size: 20px;">
									<span class="glyphicon glyphicon-search" ></span>
								</button>
						</div>
					</div>
				</g:form>

				<br/>

				<div class="row">
					<div class="col-md-12">
						<h3>
							<span class="label label-default">
								Publicaciones
							</span>
						</h3>
					</div>
				</div>

				<br/>


				<div class="row">
					<div class="col-md-10 col-md-offset-1	">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Autor</th>
						<th>Materia</th>
						<th>Catedra</th>
						<th>Fecha creacion</th>
						<th>Estado</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${publicacionInstanceList}" var="publicacionInstance">
					<tr>
						<td>${publicacionInstance.usuarioCreador.nombreUsuario} </td>
						<g:if test="${publicacionInstance.catedraRelacionada}">
							<td>${publicacionInstance.catedraRelacionada.materia.nombre} </td>
							<td>${publicacionInstance.catedraRelacionada.profesor.nombre} </td>
						</g:if>
						<g:else>
							<td>Ninguna</td>
							<td>Ninguna</td>
						</g:else>
						<td>${publicacionInstance.fechaHoraCreacion} </td>
						<td>${publicacionInstance.estado} </td>
            <td>
							<g:form action="verPublicacion" params="[idUsuario:"${usuarioInstance.id}", idPublicacion:"${publicacionInstance.id}"]">
								<button type="submit" title="Ver" style="background-color: Transparent;border: none;font-size: 20px;">
									<span class="glyphicon glyphicon-zoom-in" ></span>
								</button>
							</g:form>
						</td>
					</tr>
				</g:each>
				</tbody>
			</table>
		</div>
	</div>


	</body>
</html>
