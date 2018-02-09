<!doctype html>
<html>

<style>
input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}

div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}
</style>

<head>
    <meta name="layout" content="main"/>
    <title>Crear Mensaje</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>

<body>
    <g:form controller="crearMensaje" action="crearMensaje">
        <div>
            <label for="asunto">Asunto</label>
            <g:field type="text" id="asunto" name="Asunto" placeholder="Asunto del mensaje..."/>
        </div>

        <div>
            <label for="cont">Contenido</label>
            <g:textArea id="cont" name="Contenido" placeholder="Contenido del mensaje..."/>
        </div>

        <div>
            <label for="receptor">Usuario Receptor</label>
            <g:field type="text" id="receptor" name="Receptor" placeholder=""/>
        </div>

        <div>
            <g:submitButton name="crearMensaje" class="btn btn-danger pull-right" style="margin-top:5px;"/>
        </div>
    </g:form>

</body>
</html>
