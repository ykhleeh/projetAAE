
function afficher(response, textStatus, xhr){
	
	$('#info').empty();
	$('#info').html(response.joueurCourant);

}

$(function(){
	afficher;
});