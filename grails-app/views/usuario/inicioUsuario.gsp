<!doctype html>
<html>
<head>
    <meta name="layout" content="mainPantallas"/>
    <title>Home</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
  <div class="container">

      <div class="row">
        <div class="col-md-12">
          <div class="navbar navbar-inverse">
            <div class="container-fluid">
              <ul class="nav navbar-nav">
                <li> <g:link controller="publicacion" action="crearPublicacion" params="[idUsuario:"${usuarioInstance.id}"]">${"Crear publicacion"}</g:link></li>
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
            Hola ${usuarioInstance.nombre}!
          </span>
        </h1>
      </div>
    </div>

  </div>

</body>
</html>
