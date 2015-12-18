
function afficher(response, textStatus, xhr){
	
	$('#info').empty();
	$('#info').html(response.joueurCourant);

}

$(function(){
	$.ajax({
		url: 'jeumanager.html',
		type: 'get'
	}).done(function () {
		console.log("AFFICHAGEEEE");
		afficher;
	}).fail(function () {
		alert("affichage ko");
	});
});