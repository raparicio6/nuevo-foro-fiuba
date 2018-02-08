<!doctype html>
<html>
<head>
    <meta name="layout" content="main2"/>
    <title>Nuevo foro fiuba</title>

</head>
<body>

    <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <asset:image src="Logo.jpg" class="grails-logo"/>
        </div>
    </div>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>
            <g:form controller="usuario" action="seleccionarUsuario" max="10">
              <g:actionSubmit  action="seleccionarUsuario"  value="Iniciar sesion" />
            </g:form></h1>
    </div>

</body>
</html>
