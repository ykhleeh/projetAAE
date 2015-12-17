
<title>Salle d'attente</title>
</head>
<body>
	<div class="body">
		<div ><h4 id="info"></h4></div>
	
		<!-- 	<form action="rejoindre.html" method="post"></form> -->
		<c:if test="${message != null}">
			<div id="erreur">${message}</div>
		</c:if>
			<button value="ok" id="ok">OK</button>
		<img class="imageKoala" alt="koala" src="images/KoaJoinMe.jpg">
		
		
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/plateau.js"></script>

		<%-- 	<jsp:forward page="rejoindre.html"></jsp:forward> --%>