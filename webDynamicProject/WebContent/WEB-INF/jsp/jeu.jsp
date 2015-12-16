
<title>JEU</title>
</head>
<body>
	<c:if test="${message != null}">
		<div id="erreur">${message}</div>
	</c:if>
	<c:if test="${nomPartie != null}">
		<h1> Partie : ${nomPartie} </h1>
	</c:if>
	
	<button id="lancerDes">lancer vos dés</button>
	