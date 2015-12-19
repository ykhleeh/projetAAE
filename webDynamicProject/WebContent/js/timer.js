

function afficher(response, textStatus, xhr){
	
	$('#info').empty();
	$('#info').html(response.user);

}
function refresh() {
	var $request = $.ajax({
		url: 'attente.html',
		type: 'get'
	})
	.done(function() {
		console.log("refreshing");
	})
	.fail(function (xhr, textStatus, errorThrown) {
		alert("jeeuuuuu ko "+errorThrown);
	});
	
}

$(function () {
//	console.log("hello = " + request.user);
	//$('#info').html(response.joueurCourant);
	//user = window.location.search;
	//user = user.substring(user.lastIndexOf('=') + 1);
	var ok = false;
	$('#jouer').click(function() {
		$.ajax({
			url: 'jeu.html',
			type: "get"
		})
		.done(function(response) {
			ok=true;
			if (response.joueurs){
				$.ajax({
					url: 'jeu.html',
					type: 'post',
					data: response
				});
				window.location.href = "jeu.html";
				//afficher(response);
				console.log("JOUEZZZZZ");
			}else{
				alert("Il n'y a pas assez de joueurs, débrouillez-vous.");
				window.location.href = "menuverif.html";
			}
			return;
		})
		
		.fail(function (xhr, textStatus, errorThrown) {
			window.location.href = "index.html";
			alert("jeu ko : " + errorThrown);
		});		
	});
	console.log("Encore refresh");
	if (!ok){
	
		$.ajax({
			url: 'rejoindre.html',
			type: 'get'
		})
		.done(function (){
			
		})
		.fail(function (xhr, textStatus, errorThrown) {
			window.location.href = "index.html";			
			alert("rejoindre ko " + errorThrown);
		});
	}
	
	
	
	refresh();
	t = setInterval(refresh, 1000);
});

