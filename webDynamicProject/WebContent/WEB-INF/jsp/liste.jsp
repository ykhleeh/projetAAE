
<title>Liste des parties</title>
</head>
<body>

	<h1>LISTE</h1>
	<c:choose>
		<c:when test="${liste != null}">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<table class="table">
							<tr style="font-weight: bold">
								<th>Nom</th>
								<th>Vainqueur</th>
							</tr>
							<c:forEach var="partie" items="${liste}">
								<tr>
									<td>${partie.nom}</td>
									<td>${partie.vainqueur.nom}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<p>Pas de parties en DB</p>
		</c:otherwise>
	</c:choose>