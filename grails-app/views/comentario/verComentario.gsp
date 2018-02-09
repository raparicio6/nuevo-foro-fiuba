<!doctype html>

<html>

  <head>
    <meta name="layout" content="main2" />
    <title>Comentario</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
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
                    </div>
                    <div class="col-md-5 col-md-offset-4">
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
                    Usuario Creador: ${comentario.usuarioCreador.nombre}
                  </li>
                  <li class="list-group-item">
                    Fecha y hora: ${comentario.fechaHora}
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </g:form>

    <g:if test="${!subComentario}">
      <div class="row">
        <div class="col-md-12">
          <div class="col-md-2">
            <h4>
                <span class="label label-default">Comentarios:</span>
            </h4>
          </div>
          <div class="col-md-6">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>Nombre usuario</th>
                  <th>Mensaje</th>
                  <th>Hora</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                <g:each in="${comentario.comentarios}" var="comentarioInstance">
                  <tr>
                    <td>${comentarioInstance.usuarioCreador.nombre}</td>
                    <td>${comentarioInstance.texto}</td>
                    <td>${comentarioInstance.fechaHora}</td>
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
          <g:form controller="usuario" action="comentar" params="[idUsuario:"${usuario.id}", idComentario:"${comentario.id}"]">
            <g:textArea style="height:50px" class="form-control" name="textoComentario" placeholder="Ingrese un comentario" />
            <g:submitButton name="Comentar" value="Comentar" class="btn btn-danger pull-right" style="margin-top:5px;" />
          </g:form>
        </div>
      </div>
    </g:if>

    </div>
  </body>

</html>
