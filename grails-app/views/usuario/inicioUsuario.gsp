<!doctype html>
<html>
<head>
    <meta name="layout" content="mainPantallas"/>
    <title>Inicio</title>    
</head>
<body>
  <div class="container">

      <div class="row">
        <div class="col-md-12">
          <div class="navbar navbar-inverse">
            <div class="container-fluid">
              <ul class="nav navbar-nav">
                <li> <g:link controller="publicacion" action="crearPublicacion" params="[idUsuario:"${usuarioInstance.id}"]">Crear publicaci&oacute;n</g:link></li>
                <li> <g:link controller="publicacion" action="listaPublicaciones" params="[idUsuario:"${usuarioInstance.id}"]">${"Ver publicaciones"}</g:link> </li>
                <li> <g:link controller="${"mensajePrivado"}" params="[idUsuario:"${usuarioInstance.id}"]">${"Mensajes privados"}</g:link> </li>
                <li> <g:link controller="${"usuario"}" action="listaUsuarios" params="[idUsuario:"${usuarioInstance.id}"]">${"Buscar usuarios"}</g:link> </li>
                <li> <g:link controller="${"usuario"}">Cerrar sesi&oacute;n</g:link> </li>
              </ul>
            </div>
          </div>
        </div>
      </div>

    </br>

    <div class="row">
      <div class="col-md-12 text-center">
        <h1>
          <span class="label label-info">
            Hola ${usuarioInstance.nombreUsuario}!
          </span>
        </h1>
      </div>
    </div>

  </div>

</body>
</html>
