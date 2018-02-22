<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="mainPantallas">
		<title>Buscar usuarios</title>
	</head>
	<body>

		<div class="container">

			<div class="row">
        <div class="col-md-12">
          <div class="navbar navbar-inverse">
            <div class="container-fluid">
              <ul class="nav navbar-nav">
								<li>
									<a class="home navbar-brand" href="${createLink(action : 'inicioUsuario', params: [idUsuario : usuarioInstance.id])}">
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
							Buscar usuarios
					</h1>
				</div>
			</div>


			<g:form controller="usuario" action="listaUsuarios" params="[idUsuario:"${usuarioInstance.id}"]">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-3" style="padding-right:0px">
							Promedio de calificaciones m&iacute;nimo:
						</div>
						<div class="col-md-1" style="margin-left:-40px">
							<g:field type="number" step="0.01" min="0" max="5" name="promedioMin" class="form-control" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="col-md-3" style="padding-right:0px">
							Promedio de calificaciones m&aacute;ximo:
						</div>
						<div class="col-md-1" style="margin-left:-40px">
							<g:field type="number" step="0.01" min="0" max="5" name="promedioMax" class="form-control" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="col-md-2" style="padding-right:0px">
							Materia cursada:
						</div>
						<div class="col-md-2" style="margin-left:-60px">
							<g:select name="idMateria" from="${materias}" multiple="true" optionValue ="nombre" optionKey = "id" />
						</div>
					</div>
				</div>

				<br/>

				<div class="row">
					<div class="col-md-11 col-md-offset-0">
						<h3>
							<g:submitButton name="Filtrar" value="Filtrar" class="btn btn-danger"/>
						</h3>
					</div>
				</div>



			</g:form>




			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th class="text-center">Nombre usuario</th>
								<th class="text-center">Promedio de calificaciones</th>
								<th class="text-center">Materias cursadas</th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${usuarioInstanceList.sort { it.nombreUsuario  }}" var="usuarioInstance">
								<tr>
									<td class="text-center">${usuarioInstance.nombreUsuario}</td>
									<td class="text-center">${usuarioInstance.promedioCalificaciones.round(2)}</td>
									<td class="text-center">
										<g:form action="verMateriasCursadas" params="[idUsuario:"${usuarioInstance.id}"]">
											<button type="submit" title="Ver" style="background-color: Transparent;border: none;font-size: 20px;">
												<span class="glyphicon glyphicon-book" ></span>
											</button>
										</g:form>
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
