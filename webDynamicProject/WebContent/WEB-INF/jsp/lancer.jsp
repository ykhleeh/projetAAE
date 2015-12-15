
<title>Lancer une partie</title>
</head>
<body>
	<c:if test="${message != null}">
		<div id="erreur">${message}</div>
	</c:if>
	<c:url var="creer" value="/creer.html"/>
	<form method="post" action="${creer}">
		<label for="nom">Le nom de la partie : </label><input type="text" id="nom" name="nom"/>
		<br>
		<input type="submit" value="Créer"/>
	</form>