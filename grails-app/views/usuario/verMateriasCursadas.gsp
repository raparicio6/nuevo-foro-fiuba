<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="mainPantallas">
		<title>Ver materias cursadas</title>
	</head>
	<body>

		<div class="container">

			<div class="row">
        <div class="col-md-12">
          <div class="navbar navbar-inverse">
            <div class="container-fluid">
              <ul class="nav navbar-nav">
								<li>
									<a class="home navbar-brand" href="${createLink(action : 'inicioUsuario', params: [idUsuario : usuario.id])}">
									Volver al inicio
									</a>
								</li>
								<li> <g:link action="listaUsuarios" params="[idUsuario:"${usuario.id}"]">Atr&aacute;s</g:link> </li>
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
							Materias cursadas
					</h1>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th class="text-center">C&aacute;tedra</th>
								<th class="text-center">Materia</th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${cursadas.sort { it.catedra.materia.nombre  }}" var="cursada">
								<tr>
									<td class="text-center">${cursada.catedra.profesor.nombre}</td>
									<td class="text-center">${cursada.catedra.materia.nombre}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
			</div>

		</div>

	</body>
</html>
