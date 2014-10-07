
function changeCursor(id) {
                document.getElementById(id).style.cursor='pointer';
}

document.addEventListener('keydown', function(event) {
    if(event.keyCode == 13) {
    	var focusedElement = document.activeElement;
        alert("element who was focused : "+focusedElement.innerHTML);
    }
});


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

function emptyText(id0, id1, id2){
			document.getElementById(id0).value = "";
			if (typeof id1 !== 'undefined'){
				document.getElementById(id1).value = "";
			}
			if (typeof id2 !== 'undefined'){
				document.getElementById(id2).value = "";
			}
}
function inputTextBlurPwd(id,value){
	var inputName = document.getElementById(id);
	if(inputName.value == ""){
		inputName.value = value; //setAttribute("value","");
		inputName.className = "textGrey";
		inputName.type = "text";
	}
}
function inputTextFocusPwd(id, color){

	if (typeof color === 'undefined') { //color non specifié
		color = "";
	}
	var inputName = document.getElementById(id);
	//indexOf return la position du string trouvé, -1 si pas trouve    //if(inputName.className == "textGrey"){
	if(inputName.className.indexOf("textGrey") > -1){  
		inputName.className = "text"+color; //inputName.setAttribute("id","name");
		inputName.value = ""; //inputName.setAttribute("value","");
		inputName.type = "password";
	}
}
function emptyAll(id1,text1,id2,text2,id3,text3,id4,text4,id5,text5){
	var inputName1 = document.getElementById(id1);
	var inputName2 = document.getElementById(id2);
	var inputName3 = document.getElementById(id3);
	var inputName4 = document.getElementById(id4);
	var inputName5 = document.getElementById(id5);
	if(inputName1.value == text1){
		inputName1.value = "";
	}
	if(inputName2.value == text2){
		inputName2.value = "";
	}
	if(inputName3.value == text3){
		inputName3.value = "";
	}
	if(inputName4.value == text4){
		inputName4.value = "";
	}
	if(inputName5.value == text5){
		inputName5.value = "";
	}
}
function emtyEmail(id,text){
	var inputName = document.getElementById(id);
	if(inputName.value == text){
		inputName.value = "";
	}
}