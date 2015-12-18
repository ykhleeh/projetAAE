
function afficher(response, textStatus, xhr){
	
	$('#info').empty();
	$('#info').html(response.user);

}

$(function(){
	$.ajax({
		url: 'jeumanager.html',
		type: 'get'
	}).done(function (response) {
		//response.overrideMimeType("application/json");
		//$('#info').html(response.user);
		console.log("AFFICHAGEEEE USER " + response.user);
		console.log("AFFICHAGEEEE TOUT " + response);
		var nbWazabi = 0;
		response.des.forEach(function(de) {
			if (de.valeur=='w')
				nbWazabi++;
			console.log("DEEE " + de.id_de);
		});
		console.log("Nb de wazabis = " + nbWazabi);
		response.cartes.forEach(function(carte) {
			$('#cartes').append("<img src=" + carte.src +" alt="+ carte.codeEffet +" class=carte"+ carte.codeEffet +"  style=\"width:200px;height:200px;\"> ");
			console.log("CAAARTE " + carte.src)
		});
		$('.carte1').click(function() {
			alert("carte 1 jouée");
			jouerCarteSimple(1);
		});
		$('.carte3').click(function() {
			alert("carte 3 jouée");
			jouerCarteSimple(1);
		});		
		$('.carte7').click(function() {
			alert("carte 7 jouée");
			jouerCarteSimple(1);
		});
		$('.carte8').click(function() {
			alert("carte 8 jouée");
			jouerCarteSimple(1);
		});
		$('.carte10').click(function() {
			alert("carte 10 jouée");
			jouerCarteSimple(1);
		});		
		
		$('.carte2').click(function() {
			alert("carte 2 jouée");
			demanderGouD(2);
		});
		$('.carte4').click(function() {
			alert("carte 4 jouée");
			demanderUser(4);
		});
		$('.carte5').click(function() {
			alert("carte 5 jouée");
			demanderUser(5);
		});
		$('.carte6').click(function() {
			alert("carte 6 jouée");
			demanderUser(6);
		});
		$('.carte9').click(function() {
			alert("carte 9 jouée");
			demanderUser(9);
		});

		var demanderUser = function(c) {
			var aAjouter = $("<ul id=\"choix\"></ul>");
			response.joueurs.forEach(function(joueur) {
				var a =$("<li class=\"joueur\" id="+joueur+">"+joueur+"</li>");
				
				a.click(function() {
					console.log($(this).text());
					$.ajax({
						url: 'jouercarte.html',
						type: 'post', 
						data: {code : c, cible : $(this).text()}
					})
					.done(function(response) {
						console.log("envoi jouerCarte");
						$.ajax({
							url: 'jeu.html',
							type: 'get', 
							data: {response:response}
						})
					})
					.fail(function (xhr, textStatus, errorThrown) {
						alert("jeeuuuuu ko "+errorThrown);
					});
				});
				aAjouter.append(a);
			});
			$('#autres').append(aAjouter);
			console.log("Joueursss "+response.joueurs);
		};
		
		var demanderGouD = function(callback){
			
		}
		
		var jouerCarteSimple = function (code){
			$.ajax({
				url: 'jouercarte.html',
				type: 'post', 
				data: {code : c, cible : ""}
			})
			.done(function(response) {
				console.log("envoi jouerCarte");
				$.ajax({
					url: 'jeu.html',
					type: 'get', 
					data: {response:response}
				})
			})
			.fail(function (xhr, textStatus, errorThrown) {
				alert("jeeuuuuu ko "+errorThrown);
			});			
		}
		
		//afficher;
	}).fail(function () {
		alert("affichage ko");
	});
});