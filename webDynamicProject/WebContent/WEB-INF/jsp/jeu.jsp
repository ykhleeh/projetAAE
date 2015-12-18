
<title>JEU</title>
</head>
<body>
	<div ><h4 id="info"></h4></div>

	<c:if test="${message != null}">
		<div id="erreur">${message}</div>
	</c:if>
	<c:if test="${nomPartie != null}">
		<h1> Partie : ${nomPartie} </h1>
	</c:if>
	<c:if test="${user != null}">
		<h1> Vous êtes : ${user} </h1>
	</c:if>
	<c:if test="${out != null}">
		<c:forEach var="truc" items="${out.cartes}">
			<c:out value="truc"></c:out>
		</c:forEach>
	</c:if>	
	<div id="adversaires"></div>
	<div id="cartes"></div>
	
	<div id="des"></div>
	<form id="autres"></form>
	<div id="dialog"></div>
	<form method="get" action="lancerdes.html">
		<button>Lancer vos dés</button>
	</form>
	
	<form method="get" action="jouercarte.html">
		<input type="text" id="code" name="code">
		<input type="text" id ="cible" name="cible">
		<button>jouer une carte</button>
	</form>
	
			
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/plateau.js"></script>
	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
 	<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
	<hr>
	
	
