<!doctype html>

<html>

  <head>
    <meta name="layout" content="mainPantallas" />
    <title>Comentario</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
  </head>

  <body>
    <div class="container">

      <div class="row">
        <div class="col-md-12">
          <div class="navbar navbar-inverse">
            <div class="container-fluid">
              <ul class="nav navbar-nav">
                <li><a class="home navbar-brand" href="${createLink(uri: '/')}">
                  Volver al inicio
                </a></li>
                <li>
                  <g:if test="${comentario.publicacionComentada}">
                    <g:link controller="Publicacion" action="verPublicacion" id="${comentario.publicacionComentada.id}" params="[idUsuario:"${usuario.id}"]">${"Volver"}</g:link>
                  </g:if>
                  <g:else>
                    <g:link action="verComentario" id="${comentario.comentarioComentado.id}" params="[idUsuario:"${usuario.id}"]">${"Volver"}</g:link>
                  </g:else>
                </li>
                <li><a>
                  ${usuario.nombre}
                </a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <br/>

      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
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

      <g:form action="modificarTextoComentario" id="${comentario.id}" params="[idUsuario:"${usuario.id}"]">
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
                        <g:form action="calificarComentario" id="${comentario.id}" params="[idUsuario:"${usuario.id}"]">
                          <g:actionSubmit action="calificarPositivo" value="Me gusta" class="btn btn-danger pull-right" style="margin-top:10px;" />
                        </g:form>
                      </g:else>
                    </div>
                    <div class="col-md-5 col-md-offset-4">
                      <g:if test="${!modificar}">
                        <g:form action="calificarComentario" id="${comentario.id}" params="[idUsuario:"${usuario.id}"]">
                          <g:actionSubmit action="calificarNegativo" value="No me gusta" class="btn btn-danger pull-right" style="margin-top:10px;" />
                        </g:form>
                      </g:if>
                      <g:if test="${modificar}">
                        <g:form action="eliminarComentario" id="${comentario.id}" params="[idUsuario:"${usuario.id}"]">
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

      <div class="row">
        <div class="col-md-12">
          <div class="col-md-2">
            <h3>
                <span class="label label-default">Calificaciones:</span>
            </h3>
          </div>
          <div class="col-md-6">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>Usuario que calific&oacute;</th>
                  <th>Tipo de calificaci&oacute;n</th>
                  <th>Promedio de calificaciones del usuario que calific&oacute;</th>
                </tr>
              </thead>
              <tbody>
                <g:each in="${comentario.calificaciones}" var="calificacion">
                  <tr>
                    <td>${calificacion.usuario.nombre}</td>
                    <td>${calificacion.puntaje.tipo}</td>
                    <td>${calificacion.usuario.promedioCalificaciones.round(2)}</td>
                  </tr>
                </g:each>
              </tbody>
            </table>
          </div>
        </div>
      </div>

    <g:if test="${!subComentario}">
      <div class="row">
        <div class="col-md-12">
          <div class="col-md-2">
            <h3>
                <span class="label label-default">Comentarios:</span>
            </h3>
          </div>
          <div class="col-md-6">
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
                <g:each in="${comentarios}" var="comentarioInstance">
                  <tr>
                    <td>${comentarioInstance.usuarioCreador.nombre}</td>
                    <td>${comentarioInstance.texto}</td>
                    <td>${comentarioInstance.fechaHoraCreacion}</td>
                    <td>
                      <g:link controller="comentario" action="verComentario" id="${comentarioInstance.id}" params="[idUsuario:"${usuario.id}"]">
                        <span class="glyphicon glyphicon-zoom-in"></span>
                      </g:link>
                    </td>
                  </tr>
                </g:each>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <br/>

      <div class="row">
        <div class="col-md-5 col-md-offset-2">
          <g:form action="comentar" id="${comentario.id}" params="[idUsuario:"${usuario.id}"]">
            <g:textArea style="height:50px" class="form-control" name="textoComentario" placeholder="Ingrese un comentario" />
            <g:submitButton name="Comentar" value="Comentar" class="btn btn-danger pull-right" style="margin-top:5px;" />
          </g:form>
        </div>
      </div>
    </g:if>

    </div>
  </body>

</html>
