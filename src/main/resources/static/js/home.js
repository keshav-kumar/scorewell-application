
$(document).ready(function() {

	$('.logincontainer').slideToggle("hide");

	


	$('#test-series').click(function() {
		$('.logincontainer').slideToggle("slow");
	});
	
	$('#login').click(function() {


		alert("Login");


		var targetUrl = "/contact/";
		
		location.href = targetUrl;
		
	});



});

$('#subscribe').click(function(e) {
var userinput = $('#subscrib_email').val();
var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i
	e.preventDefault();
	if(pattern.test(userinput)) {
		$.ajax({
			type: "POST",
			url: '/sw/save-subscriber',
			data: {
				userName: userinput
			},
			success: function(response) {
				alert(response)
			},
		});
	}
});