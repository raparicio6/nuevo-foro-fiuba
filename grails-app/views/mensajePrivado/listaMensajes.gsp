<!doctype html>
<html>

<head>
    <meta name="layout" content="mainPantallas"/>
    <title>${title}</title>
</head>

<body>

  <div class="container">

    <div class="row">
      <div class="col-md-12">
        <div class="navbar navbar-inverse">
          <div class="container-fluid">
            <ul class="nav navbar-nav">
              <li>
                <a class="home navbar-brand" href="${createLink(controller : 'usuario', action : 'inicioUsuario', params: [idUsuario : usuarioInstance.id])}">
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
        <div class="col-md-3">
          <h3>
            <span class="label label-success">Mensajes ${enviadosRecibidos}:</span>
          </h3>
        </div>
        <div class="col-md-6">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>${dePara}</th>
                <th>Texto</th>
                <th>Fecha y hora</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <g:each in="${informacionMensajes.sort { it.mensajePrivado.fechaHoraCreacion }}" var="infoMensaje">
                <tr>
                  <td>${infoMensaje.usuarioConElQueSeInteractua.nombre}</td>
                  <td>${infoMensaje.mensajePrivado.texto}</td>
                  <td>${infoMensaje.mensajePrivado.fechaHoraCreacion}</td>
                  <td>
                    <g:form action="verMensaje" params="[idUsuario:"${usuarioInstance.id}", idMensajePrivado:"${infoMensaje.mensajePrivado.id}", idInformacion:"${infoMensaje.id}", mostrarResponder:"${mostrarResponder}"]" style="display: inline-block" >
                      <g:if test="${mostrarResponder}">
                      <button type="submit" title="Responder" style="background-color: Transparent;border: none;font-size: 20px;">
                        <span class="glyphicon glyphicon-pencil" ></span>
                      </button>
                      </g:if>
                      <g:else>
                        <button type="submit" title="Ver" style="background-color: Transparent;border: none;font-size: 20px;">
                          <span class="glyphicon glyphicon-zoom-in"></span>
                        </button>
                      </g:else>
                    </g:form>
                    <g:form action="eliminarMensaje" params="[idUsuario:"${usuarioInstance.id}", idInformacionMensajeUsuario:"${infoMensaje.id}"]" style="display: inline-block">
                      <button type="submit" title="Eliminar mensaje" style="background-color: Transparent;border: none;font-size: 20px;">
                        <span class="glyphicon glyphicon-remove" style="color:#F78181" ></span>
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

    <br/>

    <div class="row">
      <div class="col-md-12">
        <div class="col-md-3 col-md-offset-3">
          <g:form action="crearMensaje" params="[idUsuario:"${usuarioInstance.id}"]" >
            <g:actionSubmit action="crearMensaje" value="Redactar nuevo mensaje" class="btn btn-danger"  />
          </g:form>
        </div>
        <div class="col-md-3 col-md-offset-1">
          <g:form action="verMensajesEnviados" params="[idUsuario:"${usuarioInstance.id}"]">
            <g:actionSubmit action="${action}" value="${value}" class="btn btn-danger"  />
          </g:form>
        </div>
      </div>
    </div>

</div>

</body>
</html>
