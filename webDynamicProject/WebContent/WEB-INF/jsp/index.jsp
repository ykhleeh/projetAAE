
<title>Bienvenue au site de wazabi</title>
</head>
<body>
	<div class="body">
		<div class="row">
			<div class="col-sm-6 pull-left">
				<h2>Connectez-vous</h2>
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



		<!--             <form action="/users/login" name="login" role="form" class="form-horizontal" method="post" accept-charset="utf-8"> -->
		<!--                 <div class="form-group"> -->
		<!--                 <div class="col-md-8"><input name="username" placeholder="Idenfiant" class="form-control" type="text" id="UserUsername"/></div> -->
		<!--                 </div>  -->

		<!--                 <div class="form-group"> -->
		<!--                 <div class="col-md-8"><input name="password" placeholder="Mot de passe" class="form-control" type="password" id="UserPassword"/></div> -->
		<!--                 </div>  -->

		<!--                 <div class="form-group"> -->
		<!--                 <div class="col-md-offset-0 col-md-8"><input  class="btn btn-success btn btn-success" type="submit" value="Connexion"/></div> -->
		<!--                 </div> -->

		<!--             </form> -->
		<!--             <p class="credits">Développé par <a href="http://www.monsite.com" target="_blank">une super agence</a>.</p> -->
		<!--         </div> -->
		<!--         </div> -->

		<!--     </div> -->
		<!-- </div> -->
		<!-- </div> -->
		<!-- </div> -->