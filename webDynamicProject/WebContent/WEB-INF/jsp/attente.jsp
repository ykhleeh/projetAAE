
<title>Salle d'attente</title>
</head>
<body>
<!-- 	<form action="rejoindre.html" method="post"></form> -->
	<c:if test="${message != null}">
		<div id="erreur">${message}</div>
	</c:if>
	<c:url var="rejoindre" value="rejoindre.html"/>
<form method="post" action="${rejoindre}">
	<input type="submit" value="ok"/>
	
</form>
<%-- 	<jsp:forward page="rejoindre.html"></jsp:forward> --%>