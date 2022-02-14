$(document).ready(function () {

    $("#submit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        submit_reviewed_sheet();

    });

});

function submit_reviewed_sheet() {
	
	var form = $('#fileUploadForm')[0];

    var name = $('#name').val();
    var phone = $('#phone').val();
    var email = $('#email').val();
    var fileName = $('#fileName').val();
    
    var data = new FormData(form);
    
	data.append("name", name);
	data.append("phone", phone);
	data.append("email", email);
	data.append("fileName", fileName);
    
	$.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: '/sw/api/upload-answer',
        data: data,
        
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#submit").prop("disabled", true);

        },
        error: function (e) {

            $("#result").text("Error:Something went wrong.");
            console.log("ERROR : ", e);
            $("#submit").prop("disabled", false);

        }
    });
}