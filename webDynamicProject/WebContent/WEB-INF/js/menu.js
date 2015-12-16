
function launchAjaxRequest(_url, _method, _data, cbDone, cbFail) {
	// To notify the use that requeest in executing
	var reqAjax = $.ajax({
		url : _url,
		method : _method || 'GET',
		data : _data
	});

	if (cbDone)
		reqAjax.done(function(data) {
			setFlash('', 'success');
			cbDone(data);
		});
	if (cbFail)
		reqAjax.fail(function(xhr, textStatus, err) {
			setFlash(xhr.responseText, 'error');
			cbFail(xhr.responseText);
		});

	return reqAjax;
}

function setFlash(msg, status) {
	$('.flash').remove();
	$('body').prepend(
			'<div class="flash ' + status + '"><span>' + msg + '</span></div>');
}


function delayedAlert() {
  timeoutID = window.setTimeout(slowAlert, 2000);
}

function slowAlert() {
  alert("That was really slow!");
}
