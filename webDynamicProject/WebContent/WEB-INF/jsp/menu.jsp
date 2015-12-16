
<title>Menu</title>
</head>
<body>
	<c:if test="${message != null}">
		<div id="erreur">${message}</div>
	</c:if>
	<c:if test="${verifMenu == false}">
		<a href="lancer.html" id="lancerRef" >Lancer une partie</a>
	</c:if>
	<a href="attente.html">Rejoindre partie</a>
	<a href="lister.html">Lister parties</a>
	<script src = 'https://code.jquery.com/jquery-2.1.4.min.js'></script>
	<script src = '../js/menu.js'></script>