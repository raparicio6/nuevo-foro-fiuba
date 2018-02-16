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
    <title>Ver Mensaje</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>

<body>
        <div>
            <h1>Texto</h1>
            <g:textArea name="texto" value="${mensaje.texto}" disabled="true"/>
            <h1>Archivo</h1>
            <g:resource base="${mensaje.archivo.path}"/>
        </div>
        <g:if test="${mostrarResponder}">
          <div>
              <h1>Responder mensaje</h1>
              <g:form action="enviarMensaje" params="[idUsuarioCreador:"${usuario.id}", idUsuarioReceptor:"${informacion.usuarioConElQueSeInteractua.id}", mensajeAlCualResponde:"${mensaje.id}"]">
                <g:textArea name="texto" placeholder="Escriba una respuesta..."/>
                <g:textArea name="archivoAdjunto" placeholder="Path del archivo..."/>
                <g:submitButton name="enviarMensaje" value="Responder"/>
              </g:form>
          </div>
        </g:if>
</body>
</html>
