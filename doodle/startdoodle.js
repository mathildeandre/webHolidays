jQuery(document).ready(function(){
	    jQuery('#debut').datepicker();
	    jQuery('#fin').datepicker();
});


/* declaration des variables */
var theRowNumber = 1;
var theColumnNumber = 1;


/* Recuperation du tableau */
var url = window.location.search;
var tabStr = url.substring(url.lastIndexOf("=")+1);
//alert("start:"+tabStr+":finshed");
var tabPerson = tabStr.split(",");

/* start */
for(var i=0; i<tabPerson.length; i++){
	addPersonne(tabPerson[i]);
}
//addRow()
/* fin start */




/****************************************** EXTRA PERSON *******************************************/

function changeColor(){
	var inputName = document.getElementById("nameGris");
	inputName.id = "name"; //inputName.setAttribute("id","name");
	inputName.value = ""; //inputName.setAttribute("value","");
}

/** a faire ... */
function addExtraPerson(){
	var inputName = document.getElementById("name");
	var name = inputName.value;
	inputName.value = "add person"; //setAttribute("value","");
	inputName.id = "nameGris";

	//var div = document.getElementById("listPerson");
	//div.innerHTML += "- "+name+" </br> ";

	//tab.push(name);
	addPersonne(name);
	tabPerson.push(name);
	k = tabPerson.length - 1;
	var arrayLines = document.getElementById("tab").rows; //l'array est stocké dans une variable
	var longueur = arrayLines.length;//on peut donc appliquer la propriété length

	for(i=1; i<longueur; i++){
		/* ajout des checkbox pr chaque lignes */
		id = i + (k+2).toString();
		var cell = arrayLines[i].insertCell(k+2);
		cell.innerHTML = '<input id="'+id+'" type="checkbox" >';
		/* ajout du nom dans les select*/
		var select = document.getElementById(i.toString()+"0");
		select.innerHTML += '<option value="'+k+'">'+name+'</option>';
		//alert(select.innerHTML);
	}
}

function addPersonne(nameAdd){
	//if (!isStarted){
		//var nameAdd = document.getElementById("name").value;
		//ajoutTab(nameAdd);

		var arrayLines = document.getElementById("tab").rows;
		var cell = arrayLines[0].insertCell(-1);
	 	//var table = document.getElementById ("tab");
		//var firstRow = table.rows[0];
		//var cell = firstRow.insertCell (1);

		cell.innerHTML += "<b>"+nameAdd+"</b>";
	
}

/****************************************** // EXTRA PERSON *******************************************/


/******************************** done ************************************/

function reset(){
	//window.location = "accueil3.html";//"file:///home/entdev3/Documents/GIT/web/accueil3.html"
	location.href='calculation_holiday.html?tabPerson='+tabPerson;
}
function checkAllOfTheRow(rowNum){
	var hidden = document.getElementById("hidden"+rowNum);
	var bool;
	if(hidden.value == "true"){ //si toutes les checkbox sont deja cochees
		bool = false;
		hidden.value = "false";
		
	}
	else{
		bool = true;
		hidden.value = "true";
	}
	for(var i=0; i<tabPerson.length; i++){
		var id = rowNum + (i+2).toString();
		document.getElementById(id).checked = bool;
	}
}
function addRow(){

		$('#tab').append('<tr><td> <input type="texte" id="personne">  </td> <td> <input id="box" type="checkbox" </td></tr>');

		theRowNumber++;
}

function addColumn(){

	$('#tab').find('tr').each(function(){
	        $(this).find('td').eq(theColumnNumber).after('<td </td>')
		   });
		//theColumnNumber++;
}
/* depends of "addRow()" */
function createTextSelect(rowNumber){

	//'<select name="pays" id="pays"><option value="emiel">emiel</option><option value="kirsty">kirsty</option></select>'
	var nameSelect = 'select'+rowNumber;
	var id = rowNumber + "0";
	var text = '<select id="'+id+'" name="'+nameSelect+'" id="'+nameSelect+'">';
	for(i=0; i<tabPerson.length; i++){
		text += '<option value="'+i+'">'+tabPerson[i]+'</option>';
	}
	text += '</select>';
	return text;
}
