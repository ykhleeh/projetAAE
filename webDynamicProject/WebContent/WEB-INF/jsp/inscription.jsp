
<title>Inscrivez-vous</title>
</head>
<body>
<h2>Inscrivez-vous</h2><br>
<h3>Entrez un pseudo pour participer &agrave; une nouvelle partie</h3>
<c:if test="${message != null}">
	<div id="erreur">${message}</div>
</c:if>
<c:url var="index" value="index.html"/>
<form method="post" action="${index}">
	<label for="pseudo">Votre pseudo :</label><input type="text" id="pseudo" name="pseudo"/>
	<label for="mdp">Votre mot de passe : </label><input type="password" id="mdp" name="mdp"/>
	<br>
	<input type="submit" value="S'inscrire"/>
</form>