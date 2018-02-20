<!doctype html>
<html>

<head>
    <meta name="layout" content="mainPantallas"/>
    <title>Ver mensaje</title>
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
                  <g:link action="verMensajesEnviados" params="[idUsuario:"${usuario.id}"]">Volver</g:link>
                </g:if>
                <g:else>
                  <g:link action="index" params="[idUsuario:"${usuario.id}"]">Volver</g:link>
                </g:else>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

      <g:if test="${accion=="ver"}">

            <h1>Texto</h1>
            <g:textArea name="texto" value="${mensaje.texto}" disabled="true"/>
            <g:if test="${mensaje.archivo}">
              <h1>Archivo</h1>
              <a href="${mensaje.archivo.nombreFinal}">Abrir archivo</a>
            </g:if>

        <g:if test="${mostrarResponder}">

              <h1>Responder mensaje</h1>
              <g:form action="enviarMensaje" params="[idUsuarioCreador:"${usuario.id}", idUsuarioReceptor:"${informacion.usuarioConElQueSeInteractua.id}", idMensajeAlCualResponde:"${mensaje.id}"]">
                <g:textArea name="texto" placeholder="Escriba una respuesta..."/>
                <g:field name="archivoAdjunto" type="file"/>
                <g:submitButton name="enviarMensaje" value="Responder"/>
              </g:form>

        </g:if>
      </g:if>

      <g:if test="${accion=="crear"}">
        <g:form action="enviarMensaje" params="[idUsuarioCreador:"${usuarioInstance.id}"]">

                <h1>Receptor</h1>
                <g:select  name="idUsuarioReceptor" from="${usuarios}" optionValue ="nombre" optionKey = "id" />

                <h1>Texto</h1>
                <g:textArea name="texto" placeholder="Contenido del mensaje..."/>
                <g:field name="archivoAdjunto" type="file"/>

                <g:submitButton name="Enviar" value="Enviar mensaje"/>

        </g:form>
      </g:if>

  </div>

</body>
</html>
