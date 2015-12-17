

function afficher(response, textStatus, xhr){
	
	$('#info').empty();
	$('#info').html(response.joueurCourant);

}
function refresh() {
	var $request = $.ajax({
		url: 'jeu.html',
		type: "get",
		data: "pseudo=" + user,
		dataType : 'json',
		assync: false
	})
	.done(afficher)
	.fail(function (xhr, textStatus, errorThrown) {
		alert(errorThrown);
	});
	
}

$(function () {
	console.log("hello = ");

	//user = window.location.search;
	//user = user.substring(user.lastIndexOf('=') + 1);
	$('#ok').click(function (e) {
		$request = $.ajax({
			url: 'jeu.html',
			type: "get"
		})
		.done(function(data){
			window.location.href='rejoindre.html';
			
			//console.log("data = " +data);
		})
		.fail(function (xhr, textStatus, errorThrown) {
			alert(errorThrown);
		});
	});
	
	//afficher();
	//refresh();
	//t = setInterval(refresh, 1000);
});

