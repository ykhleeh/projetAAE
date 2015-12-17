

function afficher(response, textStatus, xhr){
	
	$('#info').empty();
	$('#info').html(response.joueurCourant);

}
function refresh() {
	var $request = $.ajax({
		url: 'jeu.html',
		type: "get"
	})
	.done(function() {
		console.log("refreshing");
		refresh();
	})
	.fail(function (xhr, textStatus, errorThrown) {
		alert(errorThrown);
	});
	
}

$(function () {
//	console.log("hello = " + request.user);
	//$('#info').html(response.joueurCourant);
	//user = window.location.search;
	//user = user.substring(user.lastIndexOf('=') + 1);
	var $request = $.ajax({
		url: 'rejoindre.html',
		type: "get"
	})
	.done(refresh())
	.fail(function (xhr, textStatus, errorThrown) {
		alert(errorThrown);
	});
	
	//refresh();
	//t = setInterval(refresh, 1000);
});

