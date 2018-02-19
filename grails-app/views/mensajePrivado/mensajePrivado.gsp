<!doctype html>
<html>

<style>
input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}

div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}
</style>

<head>
    <meta name="layout" content="mainPantallas"/>
    <title>Crear Mensaje</title>
</head>

<body>
      <g:if test="${accion=="ver"}">
        <div>
            <h1>Texto</h1>
            <g:textArea name="texto" value="${mensaje.texto}" disabled="true"/>
            <g:if test="${mensaje.archivo}">
              <h1>Archivo</h1>
              <a href="${mensaje.archivo.nombreFinal}">Abrir archivo</a>
            </g:if>
        </div>
        <g:if test="${mostrarResponder}">
          <div>
              <h1>Responder mensaje</h1>
              <g:form action="enviarMensaje" params="[idUsuarioCreador:"${usuario.id}", idUsuarioReceptor:"${informacion.usuarioConElQueSeInteractua.id}", idMensajeAlCualResponde:"${mensaje.id}"]">
                <g:textArea name="texto" placeholder="Escriba una respuesta..."/>
                <g:field name="archivoAdjunto" type="file"/>
                <g:submitButton name="enviarMensaje" value="Responder"/>
              </g:form>
          </div>
        </g:if>
      </g:if>

      <g:if test="${accion=="crear"}">
        <g:form action="enviarMensaje" params="[idUsuarioCreador:"${usuarioInstance.id}"]">
            <div>
                <h1>Receptor</h1>
                <g:select  name="idUsuarioReceptor" from="${usuarios}" optionValue ="nombre" optionKey = "id" />
            </div>
            <div>
                <h1>Texto</h1>
                <g:textArea name="texto" placeholder="Contenido del mensaje..."/>
                <g:field name="archivoAdjunto" type="file"/>
            </div>
            <div>
                <g:submitButton name="Enviar" value="Enviar mensaje"/>
            </div>
        </g:form>
      </g:if>

</body>
</html>
