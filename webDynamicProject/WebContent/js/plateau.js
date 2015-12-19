

$(function(){
	$.ajax({
		url: 'jeumanager.html',
		type: 'get'
	}).done(function (response) {
		//response.overrideMimeType("application/json");
		//$('#info').html(response.user);
		if(response.vainqueur){
			alert('Le vainqueur est ' + response.vainqueur);
			console.log('Le vainqueur est ' + response.vainqueur);
			window.location.href = 'menuverif.html';
		}
		console.log("AFFICHAGEEEE USER " + response.user);
		console.log("Joueur courant " + response.joueurCourant);
		if (response.user !== response.joueurCourant){
			$('#boutonLancerDe').hide();
		}
		var nbWazabi = 0;
		var listeAdversaires = $("<h3>Vos adversaires</h3><table><th>Nom</th><th>Nb Dés</th><th>Nb cartes</th></table>");
		
		response.joueurs.forEach(function(joueur) {
			var aa = $("<tr></tr>");
			var ab = $("<td>"+joueur+"</td>");
			aa.append(ab);
				$.ajax({
					url: 'affichermains.html',
					type: 'post', 
					data: {pseudo : joueur}
				})
				.done(function(response) {
					var ac = $("<td>"+response.nbDes+"</td><td>"+response.nbCartes+"</td>");					
					aa.append(ac);
				})
				.fail(function (xhr, textStatus, errorThrown) {
					alert("jeeuuuuu ko "+errorThrown);
				});	
			
				listeAdversaires.append(aa);
			
		});
		$('#adversaires').append(listeAdversaires);
		var listeDes = $("<h3>Vos des</h3>")
		listeDes.append("<br><table class=\"tableDes\">");
		response.des.forEach(function(de) {
			var d = $("<th>");
			switch (de.valeur) {
			case "w" : d.append("<img class=\"de\" alt=\"de\" src=\"images/wasabi.PNG\"/>");
						break;
			case "d" : d.append("<img class=\"de\" alt=\"de\" src=\"images/de.PNG\"/>");
						break;
			default : d.append("<img class=\"de\" alt=\"de\" src=\"images/pioche.PNG\"/>");
						break;
			}
			//d.append("/></th>")
			listeDes.append(d);
		})
//		var listeDes = $("<h3>Vos des</h3><br><table><th>Id</th><th>Valeur</th></table>");
//		response.des.forEach(function(de) {
//			var bb = $("<tr><td>"+de.id_de+"</td><td>"+de.valeur+"</td></tr>");
//			if (de.valeur=='w')
//				nbWazabi++;
//			console.log("DEEE " + de.id_de);
//			listeDes.append(bb);
//		});
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

		var afficherAutres = function (joueurs){
			
		}
		
		var demanderUser = function(c) {
			response.joueurs.forEach(function(joueur) {
				var aAjouter = $("<button id=\"choix\" name=\""+joueur+"\">"+joueur+"</button><br>");
				console.log("c = " + c);
				aAjouter.click(function() {
					alert($(this).text() + "   " + c);
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
				$('#autres').append(aAjouter);
			});
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