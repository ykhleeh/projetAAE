
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
	<form method="get" action="lancerdes.html">
		<button>Lancer vos dés</button>
	</form>
	
	<form method="get" action="jouercarte.html">
		<input type="text" id="code" name="code">
		<button>jouer une carte</button>
	</form>
	
	<div ><h4 id="info"></h4></div>
	<div id="lancer"></div>
	<div id="jouer"></div>
	<div id="points"></div>
	<hr>
	
	
