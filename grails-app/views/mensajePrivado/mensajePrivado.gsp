<!doctype html>
<html>

<head>
    <meta name="layout" content="mainPantallas"/>
    <title>
      <g:if test="${accion=="ver" && !mostrarResponder}">
        Ver mensaje
      </g:if>
      <g:if test="${accion=="ver" && mostrarResponder}">
        Responder mensaje
      </g:if>
      <g:if test="${accion=="crear"}">
        Crear mensaje
      </g:if>
    </title>
</head>

<body>

  <div class="container">

    <div class="row">
      <div class="col-md-12">
        <div class="navbar navbar-inverse">
          <div class="container-fluid">
            <ul class="nav navbar-nav">
              <li><a class="home navbar-brand" href="${createLink(controller : 'usuario', action : 'inicioUsuario', params: [idUsuario : usuario.id])}">
                Volver al inicio
              </a></li>
              <li>
                <g:if test="${!mostrarResponder}">
                  <g:link action="verMensajesEnviados" params="[idUsuario:"${usuario.id}"]">Atr&aacute;s</g:link>
                </g:if>
                <g:else>
                  <g:link action="index" params="[idUsuario:"${usuario.id}"]">Atr&aacute;s</g:link>
                </g:else>
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
            Mensaje privado
          </span>
        </h1>
      </div>
    </div>

    <g:if test="${accion=="ver"}">
      <div class="row">
        <div class="col-md-12">
          <h3>
            <span class="label label-success">
              Texto
            </span>
          </h3>
        </div>
      </div>

      <br/>

      <div class="row">
        <div class="col-md-3">
          <g:textArea name="texto" value="${mensaje.texto}" disabled="true" style="height:150px" class="form-control" />
        </div>
      </div>
      <g:if test="${mensaje.archivo}">
        <br/>
        <div class="row">
          <div class="col-md-12">
            <h3>
              <span class="label label-default">
                Archivo
              </span>
            </h3>
          </div>
        </div>
        <div class="row" style="padding-top:10px">
          <div class="col-md-12">
            <g:link action="descargarArchivoAdjunto" params="[idArchivo:"${mensaje.archivo.id}"]"> Descargar ${mensaje.archivo.nombre} </g:link>
          </div>
        </div>
      </g:if>

      <g:if test="${mostrarResponder}">
        <br/>
        <div class="row">
          <div class="col-md-12">
            <h3>
              <span class="label label-success">
                Responder mensaje
              </span>
            </h3>
          </div>
        </div>
        <br/>
        <g:uploadForm action="enviarMensaje" params="[idUsuarioCreador:"${usuario.id}", idUsuarioReceptor:"${informacion.usuarioConElQueSeInteractua.id}", idMensajeAlCualResponde:"${mensaje.id}"]">
          <div class="row">
            <div class="col-md-3">
              <g:textArea name="texto" placeholder="Escriba una respuesta..." style="height:150px" class="form-control"/>
            </div>
          </div>
          <div class="row" style="padding-top:10px">
            <div class="col-md-2" style="padding-top:5px">
              <label class="btn btn-default" style="color:#6E6E6E">
                Seleccionar archivo <input type="file" style="display:none">
              </label>
            </div>
            <div class="col-md-9" >
              <g:form action="enviarMensaje">
                <button type="submit" title="Responder" style="background-color: Transparent;border: none;font-size: 35px;">
                  <span class="glyphicon glyphicon-envelope" style="color:#886A08" ></span>
                </button>
              </g:form>
            </div>
          </div>
          <br/>
          <br/>
          <br/>
        </g:uploadForm>
      </g:if>
    </g:if>

    <g:if test="${accion=="crear"}">
      <g:uploadForm action="enviarMensaje" params="[idUsuarioCreador:"${usuario.id}"]">
        <div class="row">
          <div class="col-md-12">
            <h3>
              <span class="label label-success">
                Receptor
              </span>
            </h3>
          </div>
        </div>

        <div class="row" style="padding-top:10px">
          <div class="col-md-12">
            <g:select name="idUsuarioReceptor" from="${usuarios}" optionValue ="nombre" optionKey = "id" />
          </div>
        </div>

        <br/>

        <div class="row">
          <div class="col-md-12">
            <h3>
              <span class="label label-success">
                Texto
              </span>
            </h3>
          </div>
        </div>

        <div class="row" style="padding-top:10px">
          <div class="col-md-3">
            <g:textArea name="texto" placeholder="Contenido del mensaje..." style="height:150px" class="form-control" />
          </div>
        </div>

        <div class="row" style="padding-top:10px">
          <div class="col-md-2" style="padding-top:5px">
            <label class="btn btn-default" style="color:#6E6E6E">
              Seleccionar archivo <input type="file" style="display:none">
            </label>
          </div>
          <div class="col-md-9" >
            <g:form action="Enviar">
              <button type="submit" title="Enviar mensaje" style="background-color: Transparent;border: none;font-size: 35px;">
                <span class="glyphicon glyphicon-envelope" style="color:#886A08" ></span>
              </button>
            </g:form>
          </div>
        </div>
      </g:uploadForm>
    </g:if>

  </div>

</body>
</html>
