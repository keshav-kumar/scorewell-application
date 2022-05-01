$(document).ready(function () {
	
	$('.delete_button').click(function (event) {
		event.preventDefault();
		alert($(this).attr("value"));
	     var questionSetName = $(this).attr("value");
 
		$.ajax({
			type : "GET",
			url : "/sw/api/delete-question-set?setname=" + questionSetName,
			success : function(data) {
				event.preventDefault();
				alert(data);
				location.reload();
			}
		});
	     
	     
//			$.ajax({
//				type : "GET",
//				url : '/sw/api/delete-question-set?setname='+questionSetName,
//				success : function(response) {
//					location.reload();
//				},
//			});
			
	});
	
//    $(".delete_button").click(function (event) {
//
//        //stop submit the form, we will post it manually.
//        event.preventDefault();
//        
//        
//
//        alert("Delete Called "+$('.delete_button').val()+" :: "+$('#deleteQueSet').val());
//        		
//        		
//        $.ajax({
////			type : "GET",
//			url : '/sw/api/delete-question-set?setname='+$('#setname').val(),
//			contentType: "application/json",
//	        dataType: 'json',
//			success : function(response) {
//				location.reload(); 
////				$('#subscribeResult').html(response);
//			},
//		});
//        
//        
//        
//        location.reload(); 
//
//    });

});