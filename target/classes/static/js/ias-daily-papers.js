$(document).ready(function () {

    $("#submit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        upload_answer();
        
        alert("Hello Dear");

    });

});

function fire_ajax_submit() {

    // Get form
    var name = $('#name').val();
    var phone = $('#phone').val();
    var email = $('#email').val();
    
    var data = new FormData();
    
	data.append("name", name);
	data.append("phone", phone);
	data.append("email", email);

    if(name ==="" || phone === "" || email === ""){
    	$("#result").text("please fille the data.");
    }
//    else{
//    
//    $.ajax({
//        type: "POST",
//        url: '/set-list',
//        data: data,
//        
////        processData: false,
////        contentType: false,
////        cache: false,
////        timeout: 600000,
//        success: function (data) {
//            $("#result").text(data);
//        },
//        error: function (e) {
//            $("#result").text("Error:Something went wrong.");
//        }
//    });
//    
//    }

}