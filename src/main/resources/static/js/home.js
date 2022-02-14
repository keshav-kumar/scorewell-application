
$(document).ready(function() {

	$('.logincontainer').slideToggle("hide");

	$('#subscribe').click(function(e) {
		e.preventDefault();
		$.ajax({
			type: "POST",
			url: '/sw/save-subscriber',
			data: {
				userName: $('#subscrib_email').val()
			},
			success: function(response) {
				$('#subscribeResult').html(response);
			},
		});
	});


	$('#test-series').click(function() {
		$('.logincontainer').slideToggle("slow");
	});
	
	$('#login').click(function() {


		alert("Login");


		var targetUrl = "/contact/";
		
		location.href = targetUrl;
		
	});



});