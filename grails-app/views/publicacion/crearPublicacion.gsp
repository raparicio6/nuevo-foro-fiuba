<!doctype html>
<html>
    <head>
        <title>Crear publicacion</title>
    </head>
    <body>
        <g:form action="formarPublicacion" value="Crear Publicacion" params="[idUsuario:"${usuario.id}"]">
            <h1>Materia</h1>
            <g:select name="idCatedra" from="${catedras}" optionValue="${{it.materia.nombre + ", catedra " + it.profesor.nombre}}" optionKey="id" />
            <h1>Texto</h1>
            <g:textArea name="texto" style="height:50px" class="form-control" placeHolder="Ingrese un texto" />
            <g:submitButton name = "Crear publicacion" value="Crear publicacion" />
        </g:form>
    </body>
</html>
