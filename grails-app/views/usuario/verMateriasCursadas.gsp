<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="mainPantallas">
		<g:set var="entityName" value="${message(code: 'cursada.label', default: 'Cursada')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>

		<a href="#list-cursada" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">

			<content tag="nav">
	      <a><li>${usuario.nombre}</li></a>
	    </content>

			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="Volver al inicio"/></a></li>
			</ul>
		</div>

		<div>
			<h1><g:message code="Ver cursadas" args="[entityName]" /></h1>

			<table>
				<thead>
					<tr>
            <g:sortableColumn property="catedra" title="${message(code: 'cursada.catedra.peofesor.nombre.label', default: 'Catedra')}" />
            <g:sortableColumn property="materia" title="${message(code: 'cursada.catedra.materia.nombrelabel', default: 'Materia')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${cursadas}" status="i" var="cursada">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: cursada, field: "catedra.profesor.nombre")}</td>
            <td>${fieldValue(bean: cursada, field: "catedra.materia.nombre")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="10" />
			</div>
		</div>
	</body>
</html>
