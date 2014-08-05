




var init = true;
var tab = new Array();

function addPerson(){
	if(init){
		tab=[];
		init=false;
	}
	var inputName = document.getElementById("name");
	var name = inputName.value;
	inputName.value = "add person"; //setAttribute("value","");
	inputName.id = "nameGris";

	var div = document.getElementById("listPerson");
	div.innerHTML += "- "+name+" </br> ";

	tab.push(name);
}

function changeColor(){
	var inputName = document.getElementById("nameGris");
	inputName.id = "name"; //inputName.setAttribute("id","name");
	inputName.value = ""; //inputName.setAttribute("value","");
}

var initDuree=false;
function choixDuree(){
		//$('#jour').after('<input type="text" name="nom" value="duree" size="10">');
		$('input[type=radio][name=duree]:checked').after('<input type="text" name="nom" value="duree" size="10">');

	//$('#jour').after('<p>Nombre de jour</p>');
	//var message = prompt($('input[type=radio][name=duree]:checked').attr('value'));
}
