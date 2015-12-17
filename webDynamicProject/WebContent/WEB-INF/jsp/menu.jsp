
<title>Menu</title>
</head>
<body>
	<div class="body">
		<c:if test="${message != null}">
			<div id="erreur">${message}</div>
		</c:if>

		<div class="navbar navbar-default">
			<ul class="nav navbar-nav">
				<c:if test="${verifMenu == false}">
					<li><a href="lancer.html" id="lancerRef">Lancer une partie</a></li>
				</c:if>
				<li><a href="attente.html">Rejoindre partie</a></li>
				<li><a href="lister.html">Lister parties</a></li>
			</ul>
		</div>
	</div>