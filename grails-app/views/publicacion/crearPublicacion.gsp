<!doctype html>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <g:form action="formarPublicacion" value="Crear Publicacion" params=""${usuario.id}", idPublicacion:"${publicacion.id}"]">
            <g:select name="idMateria" from="${materias}" optionValue="${{it.nombre}}" optionKey="id" />
            <g:select name="idCatedra" from="${catedras}" optionValue="${{it.nombre}}" optionKey="id" />
            <g:textArea name="texto" style="height:50px" class="form-control" placeHolder="Ingrese un texto" />
        </g:form>
    </body>
</html>