<!doctype html>
    <html>
        <head>
            <meta name="layout" content="mainPantallas"/>
            <title>Crear publicaci&oacute;n</title>
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

        <div class="row" style="padding-left:10px">
          <div class="col-md-12">
            <h1>
              <span>
                Crear publicaci&oacute;n
              </span>
            </h1>
          </div>
        </div>

        <g:uploadForm action="formarPublicacion" params="[idUsuario:"${usuario.id}"]">
           <div class="row" style="padding-left:10px">
             <div class="col-md-12">
               <h3>
                 <span class="label label-success">
                   Materia (opcional)
                 </span>
               </h3>
             </div>
           </div>

           <div class="row" style="padding-top:10px;padding-left:10px">
             <div class="col-md-3">
               <g:select name="idCatedra" from="${catedras}" optionValue="${{it.materia.nombre + ", catedra " + it.profesor.nombre}}" optionKey="id" noSelection="${['null':'Elegir materia...']}" class="form-control"/>
             </div>
           </div>

           <br/>

           <div class="row">
             <div class="col-md-12">
             <div class="col-md-3">
               <h3>
                 <span class="label label-success">
                   Texto
                 </span>
               </h3>
             </div>
             <div class="col-md-9">
               <h3>
                 <span class="label label-success">
                   Materias requeridas para comentar (opcionales)
                 </span>
               </h3>
             </div>
             </div>
           </div>

           <div class="row" style="padding-top:10px">
             <div class="col-md-12">
             <div class="col-md-3">
               <g:textArea name="texto" style="height:50px" class="form-control" placeHolder="Ingrese un texto" style="height:150px" />
             </div>
             <div class="col-md-3">
               <g:select name="idsMateriasRequeridas" from="${materias}" optionValue="${{it.nombre}}" optionKey="id" multiple="true"  class="form-control" />
             </div>

             </div>
           </div>

           <br/>

           <div class="row">
             <div class="col-md-12">
               <div class="col-md-4">
               <h3>
                 <span class="label label-success">
                   Promedio requerido para comentar
                 </span>
               </h3>
             </div>
             <div class="col-md-8">
               <h3>
                 <span class="label label-success">
                   Archivo adjunto (opcional)
                 </span>
               </h3>
             </div>
           </div>
           </div>

           <div class="row" style="padding-top:10px">
             <div class="col-md-12">
               <div class="col-md-1">
                 <g:field type="number" step="0.01" name="promedioCalificacionesMinimoParaComentar" value="0" min="0" max="5" class="form-control"/>
               </div>
             <div class="col-md-2 col-md-offset-3 text-center">
               <label class="btn btn-default" style="color:#6E6E6E">
                 Seleccionar archivo <input type="file" style="display:none">
               </label>
             </div>
           </div>
           </div>

           <br/>

           <div class="row" style="padding-left:10px">
             <div class="col-md-12">
               <h3>
                 <span class="label label-success">
                   Encuesta (opcional)
                 </span>
               </h3>
             </div>
           </div>

           <div class="row" style="padding-top:10px; padding-left:10px">
             <div class="col-md-3">
               <g:textArea name="nombreEncuesta" style="height:100px" class="form-control" placeHolder="Ingrese una descripción de la encuesta"/>
             </div>
             <div class="col-md-3">
               <g:textArea name="nombreOpciones" style="height:100px" class="form-control" placeHolder="Ingrese las opciones separadas por una coma ','" />
             </div>
           </div>

           <br/>

           <div class="row">
             <div class="col-md-6 text-center">
                  <g:submitButton name = "Crear publicacion" value="Crear publicacion" class="btn btn-default"/>
                </div>
              </div>

            </g:uploadForm>

             <br/>
             <br/>
          <br/>



        </div>



    </body>
</html>
