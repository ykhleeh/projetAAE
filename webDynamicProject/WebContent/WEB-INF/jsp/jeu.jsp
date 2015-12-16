
<title>JEU</title>
</head>
<body>
	<c:if test="${message != null}">
		<div id="erreur">${message}</div>
	</c:if>
	<c:if test="${nomPartie != null}">
		<h1> Partie : ${nomPartie} </h1>
	</c:if>
	<c:if test="${listeCarte != null}">
		<c:forEach var="truc" items="${listeCarte}">
			<c:out value="truc"></c:out>
		</c:forEach>
	</c:if>	
	<form method="get" action="/lancerdes.html">
		<button>Lancer vos dés</button>
	</form>
	
	<div ><h4 id="info"></h4></div>
	<div id="lancer"></div>
	<div id="jouer"></div>
	<div id="points"></div>
	<hr>