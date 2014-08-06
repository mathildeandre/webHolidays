function inputTextBlur(id,value){
	var inputName = document.getElementById(id);
	if(inputName.value == ""){
		inputName.value = value; //setAttribute("value","");
		inputName.className = "textGrey";
	}
}
function inputTextFocus(id){
	var inputName = document.getElementById(id);
	if(inputName.className != "text"){
		inputName.className = "text"; //inputName.setAttribute("id","name");
		inputName.value = ""; //inputName.setAttribute("value","");
	}
}

function checkBoxMouseOver(id){
	var theCheckBoxMO = document.getElementById("theCheckBoxMouseOver");
	if(theCheckBoxMO.checked){
		var myCheckBox = document.getElementById(id);
		myCheckBox.checked = !(myCheckBox.checked);
		//alert(id[0]+" , "+id[1]);
		verifAllRow(id[0]);
	}
		
}
