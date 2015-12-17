
<title>Lancer une partie</title>
</head>
<body>
	<div class="body">
		<c:if test="${message != null}">
			<div id="erreur">${message}</div>
		</c:if>
		<c:url var="creer" value="/creer.html" />
		<form method="post" action="${creer}">
			<div class="col-md-8">
				<input type="text" id="nom" name="nom" class="form-control"
					placeholder="Nom de la partie" />
			</div>
			<br> <input type="submit" value="Créer" />
		</form>

		<img class="imageKoala" alt="koala" src="images/AreUKoalified.jpg">