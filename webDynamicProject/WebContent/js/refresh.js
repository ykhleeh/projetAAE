var URL_BASE = 'http://localhost:8080/webDynamicProject/';
var t;
function refresh() {
	var user = window.location.search;
	user = user.substring(user.lastIndexOf('=') + 1);
	$.ajax({
		url: URL_BASE + 'jeu.html',
		type: "get",
		data: "pseudo=" + user,
	}).done(function (response, textStatus, xhr) {
		$('#result').html(response.etat);
		var message = "";
		if (response.etat !== 'EN_COURS' && response.etat !== 'FINIE') {
			 message = "En attente d'autres joueurs";
		} else if (response.etat === 'FINIE') {
			message = "Partie Annul&eacute;e";
			clearInterval(t);
		} else {
			window.location = "jeu.html?user=" + user;
			return;
		}
		var $p = $('<p>');
		$p.html(message);
		$('#result').append($p)
	}).fail(function (xhr, textStatus, errorThrown) {
		alert(errorThrown);
	});
}

$(function () {
	refresh();
	t = setInterval(refresh, 5000);
});