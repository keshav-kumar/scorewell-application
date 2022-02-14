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

}