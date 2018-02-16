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
    <title>Casilla de mensajes</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>

<body>
  <div class="row">
    <div class="col-md-12">
      <div class="col-md-2">
        <h3>
            <span class="label label-default">Mensajes enviados:</span>
        </h3>
      </div>
      <div class="col-md-6">
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>Para</th>
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
                  <g:link action="verMensaje" params="[idUsuario:"${usuarioInstance.id}", idMensajePrivado:"${mensaje.mensajePrivado.id}", idInformacion:"${mensaje.id}", mostrarResponder:"${false}"]" >
                    Ver mensaje
                  </g:link>
                </td>
              </tr>
            </g:each>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <div class="container">

    <div class="row">
      <div class="col-md-12 text-left">
        <g:form action="crearMensaje" params="[idUsuario:"${usuarioInstance.id}"]" >
          <g:actionSubmit  action="crearMensaje" value="Redactar nuevo mensaje" class="btn btn-warning"  />
        </g:form>
        <g:form action="index" params="[idUsuario:"${usuarioInstance.id}"]">
          <g:actionSubmit  action="index" value="Volver a la casilla" class="btn btn-warning"  />
        </g:form>
      </div>
    </div>

</div>

</body>
</html>
