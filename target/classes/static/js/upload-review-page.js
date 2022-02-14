$(document).ready(function () {

    $("#submit").click(function (event) {

        event.preventDefault();
        upload_review();

    });

});

function upload_review() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var name = $('#userName').val();
    var phone = $('#phone').val();
    var email = $('#emailId').val();
    var setName = $('#setName').val();
    var reviewComment = $('#revComment').val();
    var uploadFileName = $('#fileName').val();
    
    var data = new FormData(form);
    
	data.append("name", name);
	data.append("phone", phone);
	data.append("email", email);
	data.append("reviewComment", reviewComment);
	data.append("fileName", uploadFileName);
	data.append("setName", setName);
    
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: '/sw/api/reviewed-upload',
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
        	$("#result").text("Error: "+e.responseText);
            console.log("ERROR : ", e.responseText);
            $("#submit").prop("disabled", false);

        }
    });
    
}