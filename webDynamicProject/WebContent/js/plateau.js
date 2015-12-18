

$(function(){
	$.ajax({
		url: 'jeumanager.html',
		type: 'get'
	}).done(function (response) {
		//response.overrideMimeType("application/json");
		//$('#info').html(response.user);
		console.log("AFFICHAGEEEE USER " + response.user);
		console.log("Joueur courant " + response.joueurCourant);
		var nbWazabi = 0;
		var listeAdversaires = $("<h3>Vos adversaires</h3><table><th>Id</th><th>Nom</th></table>");
		response.joueurs.forEach(function(joueur) {
			var aa = $("<tr><td>"+joueur+"</td></tr>");
			listeAdversaires.append(aa);
		});
		$('#adversaires').append(listeAdversaires);
		var listeDes = $("<h3>Vos des</h3><br><table><th>Id</th><th>Valeur</th></table>");
		response.des.forEach(function(de) {
			var bb = $("<tr><td>"+de.id_de+"</td><td>"+de.valeur+"</td></tr>");
			if (de.valeur=='w')
				nbWazabi++;
			console.log("DEEE " + de.id_de);
			listeDes.append(bb);
		});
		$('#des').append(listeDes);
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
				console.log("c = " + c);
				a.click(function() {
					console.log($(this).text() + "   " + c);
					$.ajax({
						url: 'jouercarte.html',
						type: 'post', 
						data: {code : c, cible : $(this).text()}
					})
					.done(function(response) {
						window.location.href = 'jeu.html';
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
		
		var demanderGouD = function(c){
			$('#dialog').dialog({
				  dialogClass: "no-close",
				  buttons: [
				    {
				      text: "Gauche",
				      click: function() {
				        $( this ).dialog( "close" );
							$.ajax({
								url: 'jouercarte.html',
								type: 'post', 
								data: {code : c, cible : "g"}
							})
							.done(function(response) {
								window.location.href = 'jeu.html';
							})
							.fail(function (xhr, textStatus, errorThrown) {
								alert("jeeuuuuu ko "+errorThrown);
							});					        
				      }
				    }, 
				    {
					    text: "Droite",
					      click: function() {
					        $( this ).dialog( "close" );
					        	$.ajax({
									url: 'jouercarte.html',
									type: 'post', 
									data: {code : c, cible : "d"}
								})
								.done(function(response) {
									window.location.href = 'jeu.html';
								})
								.fail(function (xhr, textStatus, errorThrown) {
									alert("jeeuuuuu ko "+errorThrown);
								});						        
					      }
					    }				    
				  ]
				});			
		}
		
		var jouerCarteSimple = function (c){
			$.ajax({
				url: 'jouercarte.html',
				type: 'post', 
				data: {code : c, cible : ""}
			})
			.done(function(response) {
				window.location.href = 'jeu.html';
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