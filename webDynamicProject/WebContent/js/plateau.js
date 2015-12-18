
function afficher(response, textStatus, xhr){
	
	$('#info').empty();
	$('#info').html(response.user);

}

$(function(){
	$.ajax({
		url: 'jeumanager.html',
		type: 'get'
	}).done(function (response) {
		$('#info').html(response.user);
		console.log("AFFICHAGEEEE " + response.user);
		//afficher;
	}).fail(function () {
		alert("affichage ko");
	});
});