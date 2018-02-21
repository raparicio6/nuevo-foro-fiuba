<!doctype html>
<html>
<head>
    <meta name="layout" content="mainPantallas"/>
    <title>Crear publicacion</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="navbar navbar-inverse">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li>
                            <a class="home navbar-brand" href="${createLink(controller : 'usuario' ,action : 'inicioUsuario', params: [idUsuario : usuario.id])}">
                                Volver al inicio
                            </a>
                        </li>
                        <li> <g:link controller="${"usuario"}">Cerrar sesi&oacute;n</g:link> </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <g:form action="agregarMateriaNecesaria" params="[idUsuario:"${usuario.id}", idPublicacion:"${publicacion.id}"]">
        <h1>Agregar materia requerida para comentar</h1>
        <g:select name="idMateria" from="${materias}" optionValue="${{it.nombre}}" optionKey="id" noSelection="${['null':'Elegir materia...']}" />
        <g:actionSubmit action="agregarMateriaNecesaria"  name = "Agregar materia" value="Agregar materia" class="btn btn-danger pull-right" style="margin-top:10px;"/>
    </g:form>

    <g:form action="dejarDeAgregarMaterias" params="[idUsuario:"${usuario.id}", idPublicacion:"${publicacion.id}"]">
        <g:actionSubmit value="Dejar de agregar materias" action="dejarDeAgregarMaterias" name="Dejar de agregar materias" class="btn btn-danger pull-right" style="margin-top:10px;"/>
    </g:form>

</div>
</body>
</html>
