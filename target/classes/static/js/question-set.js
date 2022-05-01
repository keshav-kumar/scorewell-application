$(document).ready(function () {

    $("#submit").click(function (event) {
        event.preventDefault();
        
        upload_answer();
    });
});

function upload_answer() {
    // Get form
    var form = $('#fileUploadForm')[0];

    var name = $('#name').val();
    var phone = $('#phone').val();
    var email = $('#email').val();
    var courseName = $('#courseName').val();
    var subjectName = $('#subjectName').val();
    var setName = $('#setName').val();
    
    var data = new FormData(form);
    
	data.append("name", name);
	data.append("phone", phone);
	data.append("email", email);
	data.append("courseName", courseName);
	data.append("subjectName", subjectName);
	data.append("setName", setName);

    if(name ==="" || phone === "" || email === ""){
    	$("#result").text("please fill the data.");
    }else{
    $("#result").removeClass();
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
			if(data.success) {
				$("#result").text(data.success);
				$("#result").addClass('alert alert-success');
				$("#submit").prop("disabled", true);
			} else {
				$("#result").text(data.error);
				$("#result").addClass('alert alert-danger');
			}
        },
        error: function (e) {

            $("#result").text("Error:Something went wrong.");
            console.log("ERROR : ", e);
            $("#submit").prop("disabled", false);

        }
    });
    
    }

}