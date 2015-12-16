
<title>Bienvenue au site de wazabi</title>
</head>
<body>
<div class="body">

<h2>Connectez-vous</h2><br>
<h3>Entrez un pseudo pour participer &agrave; une nouvelle partie</h3>
<c:if test="${message != null}">
	<div id="erreur">${message}</div>
</c:if>
<c:url var="login" value="login.html"/>
<form method="post" action="${login}">
	<label for="pseudo">Votre pseudo :</label><input type="text" id="pseudo" name="pseudo"/>
	<label for="mdp">Votre mot de passe : </label><input type="password" id="mdp" name="mdp"/>
	<br>
	<input type="submit" value="Se connecter"/>
	
</form>
<a href="inscription.html">S'inscrire</a>