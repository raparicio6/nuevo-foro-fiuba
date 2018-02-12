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

  <g:layoutHead/>
</head>

<body>

  <div class="container">

    <div class="row">
      <div class="col-md-12 text-center">
        <div class="jumbotron" style="background:#b22c49; margin-bottom:0px;">
          <h1>
           Nuevo Foro Fiuba</h1>
        </div>
      </div>
    </div>

  </div>

  <g:layoutBody/>

  <!-- <div class="footer" role="contentinfo"></div> -->

  <div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;" />
  </div>

  <asset:javascript src="application.js" />

</body>

</html>