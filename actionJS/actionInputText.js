function inputTextBlur(id,value){
	var inputName = document.getElementById(id);
	if(inputName.value == ""){
		inputName.value = value; //setAttribute("value","");
		inputName.className = "textGrey";
	}
}
function inputTextFocus(id, color){

	if (typeof color === 'undefined') { //color non specifié
		color = "";
	}
	var inputName = document.getElementById(id);
	//indexOf return la position du string trouvé, -1 si pas trouve    //if(inputName.className == "textGrey"){
	if(inputName.className.indexOf("textGrey") > -1){  
		inputName.className = "text"+color; //inputName.setAttribute("id","name");
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
