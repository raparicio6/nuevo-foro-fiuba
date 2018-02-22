<!doctype html>

<html>

  <head>
    <meta name="layout" content="mainPantallas" />
    <title>Comentario</title>
  </head>

  <body>
    <div class="container">

      <div class="row">
        <div class="col-md-12">
          <div class="navbar navbar-inverse">
            <div class="container-fluid">
              <ul class="nav navbar-nav">
                <li>
                  <a class="home navbar-brand" href="${createLink(controller : 'usuario' ,action : 'inicioUsuario', params: [idUsuario : usuario.id])}">
                    Volver al inicio
                  </a>
                </li>
                <li>
                  <g:if test="${comentario.publicacionComentada}">
                    <g:link controller="Publicacion" action="verPublicacion" params="[idUsuario:"${usuario.id}", idPublicacion:"${comentario.publicacionComentada.id}"]">Atr&aacute;s</g:link>
                  </g:if>
                  <g:else>
                    <g:link action="verComentario" params="[idUsuario:"${usuario.id}", idComentario:"${comentario.comentarioComentado.id}"]">Atr&aacute;s</g:link>
                  </g:else>
                </li>
                <li> <g:link controller="${"usuario"}">Cerrar sesi&oacute;n</g:link> </li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <br/>

      <g:if test="${flash.message}">
        <div class="row">
          <div class="col-md-12">
            <div class="message" role="status">${flash.message}</div>
          </div>
        </div>
      </g:if>

      <div class="row">
        <div class="col-md-12">
          <h1>
            <span>
              Ver comentario
            </span>
          </h1>
        </div>
      </div>

      <br/>

      <g:form action="modificarTextoComentario" params="[idUsuario:"${usuario.id}", idComentario:"${comentario.id}"]">
        <div class="row">
          <div class="col-md-12">
            <div class="col-md-7" style="padding-right:0px; padding-left:0px">
              <div class="col-md-2">
                <h3>
                  <span class="label label-default">Texto:</span>
                </h3>
              </div>
              <div class="col-md-10" style="padding-right:0px">
                <g:textArea style="height:150px" class="form-control" name="nuevoTexto" value="${comentario.texto}"/>
                <div class="row">
                  <div class="col-md-12">
                    <div class="col-md-3" style="padding-right:0px">
                      <g:if test="${modificar}">
                        <g:submitButton name="Modificar" value="Modificar" class="btn btn-danger pull-right" style="margin-top:10px;" />
                      </g:if>
                      <g:else>
                        <g:form action="calificarComentario" params="[idUsuario:"${usuario.id}", idComentario:"${comentario.id}"]">
                          <g:actionSubmit action="calificarPositivo" value="Me gusta" class="btn btn-danger pull-right" style="margin-top:10px;" />
                        </g:form>
                      </g:else>
                    </div>
                    <div class="col-md-5 col-md-offset-4">
                      <g:if test="${!modificar}">
                        <g:form action="calificarComentario" params="[idUsuario:"${usuario.id}", idComentario:"${comentario.id}"]">
                          <g:actionSubmit action="calificarNegativo" value="No me gusta" class="btn btn-danger pull-right" style="margin-top:10px;" />
                        </g:form>
                      </g:if>
                      <g:if test="${modificar}">
                        <g:form action="eliminarComentario" params="[idUsuario:"${usuario.id}", idComentario:"${comentario.id}"]">
                          <g:actionSubmit action="eliminarComentario" value="Eliminar comentario" class="btn btn-danger pull-right" style="margin-top:10px;" />
                        </g:form>
                      </g:if>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-5" style="padding-left:0px;padding-right:0px">
              <div class="col-md-3">
                <h3>
                  <span class="label label-default">Datos:</span>
                </h3>
              </div>
              <div class="col-md-9" style="padding-left:0px; padding-right:0px">
                <ul class="list-group">
                  <li class="list-group-item">
                    Usuario Creador:
                    <span class="label label-info">
                      ${comentario.usuarioCreador.nombre}
                    </span>
                  </li>
                  <li class="list-group-item">
                    Fecha y hora de creaci&oacute;n:
                    <span class="label label-info">
                      ${comentario.fechaHoraCreacion}
                    </span>
                  </li>
                  <li class="list-group-item">
                    Promedio de calificaciones del usuario que comenta:
                    <span class="label label-info">
                      ${comentario.usuarioCreador.promedioCalificaciones.round(2)}
                    </span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </g:form>

      <br/>
      <br/>

      <div class="row">
        <div class="col-md-12 col-md-offset-1">
            <h3>
                <span class="label label-default">Calificaciones:</span>
            </h3>
          </div>
      </div>
      <div class="row">
          <div class="col-md-8 col-md-offset-2">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>Usuario que calific&oacute;</th>
                  <th>Tipo de calificaci&oacute;n</th>
                  <th>Promedio de calificaciones del usuario que calific&oacute;</th>
                </tr>
              </thead>
              <tbody>
                <g:each in="${comentario.calificaciones.sort { it.fechaHoraCreacion }}" var="calificacion">
                  <tr>
                    <td>${calificacion.usuario.nombre}</td>
                    <td>${calificacion.puntaje.tipo.toString().replace("_", " ")}</td>
                    <td>${calificacion.usuario.promedioCalificaciones.round(2)}</td>
                  </tr>
                </g:each>
              </tbody>
            </table>
          </div>
        </div>


    <g:if test="${!subComentario}">
      <br/>
      <div class="row">
        <div class="col-md-11 col-md-offset-1">

            <h3>
                <span class="label label-default">Comentarios:</span>
            </h3>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 col-md-offset-3">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>Nombre usuario</th>
                  <th>Mensaje</th>
                  <th>Fecha y hora de creaci&oacute;n</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                <g:each in="${comentarios.sort { it.fechaHoraCreacion }}" var="comentarioInstance">
                  <tr>
                    <td>${comentarioInstance.usuarioCreador.nombre}</td>
                    <td>${comentarioInstance.texto}</td>
                    <td>${comentarioInstance.fechaHoraCreacion}</td>
                    <td>
                      <g:form controller="comentario" action="verComentario" params="[idUsuario:"${usuario.id}", idComentario:"${comentarioInstance.id}"]">
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

      <br/>

      <div class="row">
        <div class="col-md-5 col-md-offset-3">
          <g:form action="comentar" params="[idUsuario:"${usuario.id}", idComentario:"${comentario.id}"]">
            <g:textArea style="height:50px" class="form-control" name="textoComentario" placeholder="Ingrese un comentario" />
            <g:submitButton name="Comentar" value="Comentar" class="btn btn-danger pull-right" style="margin-top:5px;" />
          </g:form>
        </div>
      </div>
    </g:if>

    </div>
  </body>

</html>
