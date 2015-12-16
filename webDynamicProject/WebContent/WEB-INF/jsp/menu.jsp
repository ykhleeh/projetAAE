
<title>Menu</title>
</head>
<body>
	<c:if test="${message != null}">
		<div id="erreur">${message}</div>
	</c:if>

	<div class="navbar navbar-default">
		<ul class="nav navbar-nav">
			<li><a href="lancer.html">Lancer une partie</a></li>
			<li><a href="attente.html">Rejoindre partie</a></li>
			<li><a href="lister.html">Lister parties</a></li>
		</ul>
	</div>