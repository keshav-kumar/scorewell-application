$(document).ready(function() {

	$('#datepicker').datepicker({
		dateFormat : 'dd-mm-yy'
	}).datepicker('setDate', new Date());

	$("#submit").click(function(event) {
		event.preventDefault();
		fire_ajax_submit(queCounter);

	});

	var queCounter = 2;
	
	$("#addQuestion").click(function (event) {
		event.preventDefault();
	    if(queCounter>10){
	            alert("Only 10 textboxes allow");
	            return false;
	    }   
	        
	    var newQuestionBoxDiv = $(document.createElement('div'))
	         .attr("id", 'QuestionBoxDiv' + queCounter);
	                
	    newQuestionBoxDiv.after().html('<label>Question '+ queCounter + ' : </label>' +
	    	  '<textarea name="que' + queCounter + '"id="que'+ queCounter +'" cols="100" rows="3"></textarea>'
//	 '<input type="text" name="textbox' + queCounter + '"id="textbox'+ queCounter + '" value="" >'
    );
	            
	    newQuestionBoxDiv.appendTo("#QuestionBoxesGroup");
	    queCounter++;
     });
	
	$("#removeQuestion").click(function (event) {
		event.preventDefault();
	    if(queCounter==1){
          alert("No more textbox to remove");
          return false;
	    }   
	    queCounter--;
        $("#QuestionBoxDiv" + queCounter).remove();
     });
	
	$("#getQuestion").click(function(event) {
		event.preventDefault();
		var msg = '';
		for (i = 1; i < queCounter; i++) {
			msg += "\n Question #" + i + " : " + $('#que' + i).val();
		}
		alert(msg);
	});

});

function fire_ajax_submit(queCounter) {

	var form = $('#fileUploadForm')[0];

	var course = $('#course').val();
	var subject = $('#subject').val();
	var setName = $('#setName').val();
	var publishDate = $('#datepicker').val();
	
	var data = new FormData(form);
	var errorMasg = "";
	if(queCounter == 1){
		errorMasg = "Please add atleast one question.";
	}
	
	for (i = 1; i < queCounter; i++) {
		var que = $('#que' + i).val();
		data.append("que"+i, que);
		if(que === ""){
			errorMasg = "Question No "+i+" is empty";
		}
	}
	data.append("TotalQuestions", queCounter);
	
	data.append("course", course);
	data.append("subject", subject);
	data.append("setName", setName);
	data.append("publishDate", publishDate);

	if (errorMasg != "" ){
		$("#result").text(errorMasg);

		alert(errorMasg);

	} else {

		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "/sw/api/create-question-set",
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {

				$("#result").text(data);
				console.log("SUCCESS : ", data);
				$("#submit").prop("disabled", true);
			},
			error : function(e) {
				$("#result").text(e.responseText);
				console.log("ERROR : ", e);
				$("#submit").prop("disabled", false);
			}
		});
	}

}