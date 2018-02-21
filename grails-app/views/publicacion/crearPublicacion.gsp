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

            <g:uploadForm action="formarPublicacion" params="[idUsuario:"${usuario.id}"]">
              <h1>Materia</h1>
              <g:select name="idCatedra" from="${catedras}" optionValue="${{it.materia.nombre + ", catedra " + it.profesor.nombre}}" optionKey="id" noSelection="${['null':'Elegir materia...']}" />
              <h1>Texto</h1>
              <g:textArea name="texto" style="height:50px" class="form-control" placeHolder="Ingrese un texto" />
              <h1>Archivo adjunto</h1>
              <input type="file" name="archivo" />
              <h1>Agregar encuesta</h1>
              <g:textArea name="nombreEncuesta" style="height:50px" class="form-control" placeHolder="Ingrese una descripcion de la encuesta" />
              <g:textArea name="nombreOpciones" style="height:50px" class="form-control" placeHolder="Ingrese las opciones separadas por una coma ','" />
              <h1>Materia requerida para comentar</h1>
              <g:select name="idMateria" from="${materias}" optionValue="${{it.nombre}}" optionKey="id" noSelection="${['null':'Elegir materia...']}" />
              <h1>Promedio requerido para comentar</h1>
              <g:field type="number" step="0.01" name="promedioCalificacionesMinimoParaComentar" value="0" min="0" max="5"/>
              <g:submitButton name = "Crear publicacion" value="Crear publicacion" />
            </g:uploadForm>
          </div>
    </body>
</html>
