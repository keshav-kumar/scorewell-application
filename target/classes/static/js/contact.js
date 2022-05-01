$(document).ready(function () {

    $("#sendMessage").click(function (event) {
        event.preventDefault();
        sendMessage();
    });
});

function sendMessage(){

	var emailPattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
	var name = $('#name').val();
    var email = $('#email').val();
    var subject = $('#subject').val();
    var message = $('#message').val();
    
    var data = new FormData();
    
	data.append("name", name);
	data.append("subject", subject);
	data.append("email", email);
	data.append("message", message);
	
    if(name ==="" || subject === "" || email === "" || message === ""){
    	$("#result").text("please fille the data.");
    }else if((!emailPattern.test(email))){
    	$("#result").text("Not a valid e-mail address.");
    }else{
    	$.ajax({
            type: "POST",
//            enctype: 'multipart/form-data',
            url: '/sw/sendmessage',
            data: data,
            
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                $("#result").text(data);
                console.log("SUCCESS : ", data);
//                $("#submit").prop("disabled", true);
            },
            error: function (e) {
                $("#result").text("Error:Something went wrong.");
                console.log("ERROR : ", e);
//                $("#submit").prop("disabled", false);
            }
        });
    }
    
}