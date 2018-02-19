<!doctype html>
    <html>
        <head>
            <title>Crear publicacion</title>
        </head>
    <body>
            <g:form controller="publicacion" action="formarPublicacion" value="Crear Publicacion" params="[idUsuario:"${usuario.id}"]">
            <h1>Materia</h1>
            <g:select name="idCatedra" from="${catedras}" optionValue="${{it.materia.nombre + ", catedra " + it.profesor.nombre}}" optionKey="id" />
            <h1>Texto</h1>
            <g:textArea name="texto" style="height:50px" class="form-control" placeHolder="Ingrese un texto" />
            <h1>Agregar encuesta</h1>
            <g:textArea name="nombreEncuesta" style="height:50px" class="form-control" placeHolder="Ingrese una descripcion de la encuesta" />
            <g:textArea name="nombreOpciones" style="height:50px" class="form-control" placeHolder="Ingrese las opciones separadas por una coma ','" />
            <h1>Materias requeridas para comentar</h1>
            <g:select name="idMateria" from="${materias}" optionValue="${{it.nombre}}" optionKey="id" />
            <h1>Promedio requerido para comentar</h1>
            <g:field type="float" step="any" name="puntajeMinimoParaComentar" value="0" min="0" max="5"/>
            <g:submitButton name = "Crear publicacion" value="Crear publicacion" />
        </g:form>
    </body>
</html>
