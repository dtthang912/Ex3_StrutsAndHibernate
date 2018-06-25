/**
 * 
 */
function checkCheckbox() { 
	var checkboxes = document.getElementsByName("selectedIndexes");
	var isChecked = false;
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			isChecked = true;
			break;
		}
	}
	if (!isChecked){
		$(".message").html("Choose at least one row to perform!");
		$(".message").css("color","red");
	}
	return isChecked;
}
function checkMark() {
	var marks = document.getElementsByClassName("student-mark");
	var error = "";
	for (var i = 0; i < marks.length; i++) {
		if(marks[i].value.trim().length == 0){
			$(".student-mark").eq(i).closest(".form-group").addClass("has-error");
			return "- Mark must not be empty";
		}
		else if (isNaN(marks[i].value)) {
			$(".student-mark").eq(i).closest(".form-group").addClass("has-error");
			return "- Mark must be a number";
		} else if (marks[i].value< 0 || marks[i].value > 10) {
			$(".student-mark").eq(i).closest(".form-group").addClass("has-error");
			return "- Mark must be from 0 to 10";
		}
	}
	return error;
}
function checkName(){
	var names = document.getElementsByClassName("student-name");
	var error = "";
	for (var i = 0; i < names.length; i++){
		if(names[i].value.trim().length == 0){
			$(".student-name").eq(i).closest(".form-group").addClass("has-error");
			return "- Name must not be empty";
		}
		else if(names[i].value.trim().length > 45){
			$(".student-name").eq(i).closest(".form-group").addClass("has-error");
			return "- Name length must be from 1 to 45";
		}
	}
	return error;
}
function checkStudentInput(){
	$(".form-group").removeClass("has-error");
	var markError = checkMark();
	var nameError = checkName();
	if(markError == "" && nameError == ""){
		return true;
	}
	else{
		$(".message").html("Error:<br>" + nameError + "<br>" + markError);
		$(".message").css("color","red");
		return false;
	}
}
function checkDelete(){
	if(checkCheckbox() && confirm("Are you sure?")) return true;
	return false;
}