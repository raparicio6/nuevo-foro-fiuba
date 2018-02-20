<!doctype html>
<html>

<head>
    <meta name="layout" content="mainPantallas"/>
    <title>Mensajes privados</title>
</head>

<body>

  <div class="container">

    <div class="row">
      <div class="col-md-12">
        <div class="navbar navbar-inverse">
          <div class="container-fluid">
            <ul class="nav navbar-nav">
              <li><a class="home navbar-brand" href="${createLink(controller : 'usuario', action : 'inicioUsuario', params: [idUsuario : usuarioInstance.id])}">
                Volver al inicio
              </a></li>
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
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <g:each in="${mensajes}" var="mensaje">
                <tr>
                  <td>${mensaje.usuarioConElQueSeInteractua.nombre}</td>
                  <td>${mensaje.mensajePrivado.texto}</td>
                  <td>
                    <g:form action="verMensaje" params="[idUsuario:"${usuarioInstance.id}", idMensajePrivado:"${mensaje.mensajePrivado.id}", idInformacion:"${mensaje.id}", mostrarResponder:"${mostrarResponder}"]" >
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
    </div>

    <br/>

    <div class="row">
      <div class="col-md-12">
        <div class="col-md-3 col-md-offset-3">
          <g:form action="crearMensaje" params="[idUsuario:"${usuarioInstance.id}"]" >
            <g:actionSubmit  action="crearMensaje" value="Redactar nuevo mensaje" class="btn btn-danger"  />
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
