
<title>Bienvenue sur Koazabi</title>
</head>
<body>
	<div class="body">
		<div class="row">
			<div class="col-sm-6 pull-left">
				<h1>Connectez-vous</h1>
				<br>
				<c:if test="${message != null}">
					<div id="erreur">${message}</div>
				</c:if>
				<c:url var="login" value="login.html" />
					<form class="formLogin" method="post" action="${login}">
						<div class="login">
						<label for="pseudo">Votre pseudo :</label><input type="text"
							id="pseudo" name="pseudo" /> 
						</div>
						<div class="mdp">
						<label for="mdp">Votre mot
							de passe : </label><input type="password" id="mdp" name="mdp" />
						</div>	
						<input type="submit" value="Se connecter" />

					</form>
					<a href="inscription.html">S'inscrire</a>
			</div>
			<div class="col-sm-6 pull-right">
				<img class="imageKoala" alt="koala" src="images/SwagDeKoala.png">
			</div>
		</div>
