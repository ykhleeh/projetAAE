
<title>Inscrivez-vous</title>
</head>
<body>
	<div class="body">
		<div class="row">
			<div class="col-sm-6 pull-left">
				<h1>Inscrivez-vous</h1>
				<br>
				<c:if test="${message != null}">
					<div id="erreur">${message}</div>
				</c:if>
				<c:url var="index" value="/inscrire.html" />
				<form class="formSignIn" method="post" action="${index}">
					<div class="login">
						<input type="text" id="pseudo" name="pseudo" placeholder="Login" class="form-control"/>
					</div>
					<div class="mdp">
						<input type="password" id="mdp" name="mdp" placeholder="Mot de passe" class="form-control"/>
					</div>
					<input type="submit" value="S'inscrire" />
				</form>
			</div>
			<div class="col-sm-6 pull-right">
				<img class="imageKoala" alt="koala" src="images/ThugKoala.jpg">
			</div>
		</div>
