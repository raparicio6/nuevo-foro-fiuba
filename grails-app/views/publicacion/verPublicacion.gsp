<%@ page import = "nuevo_foro_fiuba.Publicacion.EstadoPublicacion" %>
  <!doctype html>

  <html>

  <head>
    <meta name="layout" content="main2" />
    <title>Publicacion</title>
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
                <li>
                  <g:link action="listaPublicaciones" max="10" id="${usuario.id}">${"Volver al listado"}</g:link>
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
              Ver publicaci&oacute;n
            </span>
          </h1>
        </div>
      </div>

      <br/>

      <g:form controller="publicacion" action="modificarTextoPublicacion" id="${publicacion.id}" params="[idUsuario:"${usuario.id}"]">
        <div class="row">
          <div class="col-md-12">
            <div class="col-md-7" style="padding-right:0px; padding-left:0px">
              <div class="col-md-2">
                <h3>
                  <span class="label label-default">Texto:</span>
                </h3>
              </div>
              <div class="col-md-10" style="padding-right:0px">
                <g:textArea style="height:150px" class="form-control" name="nuevoTexto" value="${publicacion.texto}" />
                <div class="row">
                  <div class="col-md-12">
                    <div class="col-md-3 col-md-offset-1" style="padding-right:0px">
                      <g:if test="${modificar}">
                        <g:submitButton name="Modificar" value="Modificar" class="btn btn-danger" style="margin-top:10px;" />
                      </g:if>
                    </div>
                    <div class="col-md-4">
                      <g:if test="${!modificar}">
                        <g:form action="calificarPublicacion" id="${publicacion.id}" params="[idUsuario:"${usuario.id}"]">
                          <g:actionSubmit action="calificarNegativo" value="No me gusta" class="btn btn-danger pull-right" style="margin-top:10px;" />
                        </g:form>
                      </g:if>
                      <g:else>
                        <g:form action="eliminarPublicacion" id="${publicacion.id}" params="[idUsuario:"${usuario.id}"]">
                          <g:actionSubmit action="eliminarPublicacion" value="Eliminar publicaci&oacute;n" class="btn btn-danger" style="margin-top:10px;" />
                        </g:form>
                      </g:else>
                    </div>
                    <div class="col-md-4">
                      <g:if test="${modificar}">
                        <g:form action="cambiarEstado" id="${publicacion.id}" params="[idUsuario:"${usuario.id}"]">
                          <g:actionSubmit action="cambiarEstado" value="Cambiar estado" class="btn btn-danger " style="margin-top:10px; margin-left:5px" />
                        </g:form>
                      </g:if>
                      <g:else>
                        <g:form action="calificarPublicacion" id="${publicacion.id}" params="[idUsuario:"${usuario.id}"]">
                          <g:actionSubmit action="calificarPositivo" value="Me gusta" class="btn btn-danger pull-right" style="margin-top:10px;" />
                        </g:form>
                      </g:else>
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
                    Promedio de calificaciones m&iacute;nimo para comentar:
                      <g:if test="${modificar}">
                        <g:form action="modificarPromedioRequeridoParaComentar" id="${publicacion.id}" params="[idUsuario:"${usuario.id}"]">
                          <g:field type="number" name="promedio" value="${publicacion.promedioRequeridoParaComentar}" min="0" max="5"/>
                            <button type="submit" title="Guardar" style="background-color: Transparent;border: none;">
                              <span class="glyphicon glyphicon-ok"></span>
                            </button>
                        </g:form>
                      </g:if>
                      <g:else>
                        ${publicacion.promedioRequeridoParaComentar}
                      </g:else>
                  </li>
                  <li class="list-group-item">
                    Usuario Creador: ${publicacion.usuarioCreador.nombre}
                  </li>
                  <li class="list-group-item">
                      Materia:
                      <g:if test="${modificar}">
                        <g:form controller="publicacion" action="modificarCatedra" id="${publicacion.id}" params="[idUsuario:"${usuario.id}"]">
                          <g:select name="idCatedra" from="${catedras}" optionValue="${{it.materia.nombre + ", " + "catedra " + it.profesor.nombre}}" optionKey="id" value="${publicacion.catedraRelacionada.id}"/>
                          <button type="submit" title="Guardar" style="background-color: Transparent;border: none;">
                            <span class="glyphicon glyphicon-ok"></span>
                          </button>
                        </g:form>
                      </g:if>
                      <g:else>
                        ${publicacion.catedraRelacionada.materia.nombre}, catedra ${publicacion.catedraRelacionada.profesor.nombre}
                      </g:else>
                  </li>
                  <li class="list-group-item">
                    Estado: ${publicacion.estado}
                  </li>
                  <li class="list-group-item">
                    Promedio de calificaciones del usuario creador: ${publicacion.usuarioCreador.promedioCalificaciones}
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
                <g:each in="${publicacion.calificaciones}" var="calificacion">
                  <tr>
                    <td>${calificacion.usuario.nombre}</td>
                    <td>${calificacion.puntaje.tipo}</td>
                    <td>${calificacion.usuario.promedioCalificaciones}</td>
                  </tr>
                </g:each>
              </tbody>
            </table>
          </div>
        </div>
      </div>

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
                  <th>Hora</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                <g:each in="${publicacion.comentarios}" var="comentario">
                  <tr>
                    <td>${comentario.usuarioCreador.nombre}</td>
                    <td>${comentario.texto}</td>
                    <td>${comentario.fechaHora}</td>
                    <td>
                      <g:link controller="comentario" action="verComentario" id="${comentario.id}" params="[idUsuario:"${usuario.id}"]" title="Ver">
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
          <g:form controller="usuario" action="comentar" id="${publicacion.id}" params="[idUsuario:"${usuario.id}"]">
            <g:textArea name="textoComentario" style="height:50px" class="form-control" placeHolder="Ingrese un comentario" />
            <g:submitButton name="Comentar" class="btn btn-danger pull-right" style="margin-top:5px;" />
          </g:form>
        </div>
      </div>
      <!-- NAME TIENE QUE SER IGUAL AL NOMBRE DEL PARAMETRO DEL METODO -->

    </div>
  </body>

  </html>
