<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="mainPantallas">
	<title>Iniciar sesi&oacute;n</title>
</head>

<body>

	<div class="container">

		<br/>

		<div class="row">
			<div class="col-md-12">
				<h1>
						Seleccione un usuario
				</h1>
			</div>
		</div>

		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Nombre usuario</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${usuarioInstanceList}" var="usuarioInstance">
							<tr>
								<td>${usuarioInstance.nombre}</td>
								<td>${usuarioInstance.apellido}</td>
								<td>${usuarioInstance.nombreUsuario}</td>
								<td>
									<g:link action="inicioUsuario" params="[idUsuario:"${usuarioInstance.id}"]">
										Iniciar sesi&oacute;n
									</g:link>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
		</div>

	</div>
</body>

</html>
