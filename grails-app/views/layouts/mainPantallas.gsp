<!doctype html>
<html lang="en" class="no-js">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <title>
    <g:layoutTitle default="Grails" />
  </title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />

  <asset:stylesheet src="application.css" />
  <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
  <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

  <g:layoutHead/>
</head>

<body>

  <div class="container">

    <div class="row">
      <div class="col-md-12">
        <div class="jumbotron" style="background:#b22c49; margin-bottom:0px;">
          <div class="row">
          <div class="col-md-4 col-md-offset-1">
            <asset:image src="Logo.png" class="grails-logo" style="width:275px; height:175px;"/>
          </div>
          <div class="col-md-7">
            <h1><span class="label label">Nuevo Foro Fiuba</span></h1>
          </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <g:layoutBody/>

  <div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;" />
  </div>

  <asset:javascript src="application.js" />

</body>

</html>
