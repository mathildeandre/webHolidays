
/* declaration des variables */
var theRowNumber = 1;



/* Recuperation du tableau */
var url = window.location.search;
var tabStr = url.substring(url.lastIndexOf("=")+1);
//alert("start:"+tabStr+":finshed");
var tabPerson = tabStr.split(",");

/* start */
createTHead();
addRow()
/* fin start */





function createTHead(){
	//var arrayLines = document.getElementById("tab").rows;
	//****************
	var tr = document.getElementById('tab').tHead.children[0];
	var th;	
	//****************


	for(var i=0; i<tabPerson.length; i++){
		//****************
   	 	th = document.createElement('th');
		th.innerHTML = tabPerson[i];
		tr.appendChild(th);
		//*************
		/*var cell = arrayLines[0].insertCell(-1);
		cell.innerHTML += "<b>"+tabPerson[i]+"</b>";*/
	}
   	 	th = document.createElement('th');
		th.innerHTML = "Everybody";
		tr.appendChild(th);
   	 	th = document.createElement('th');
		th.innerHTML = "Description";
		tr.appendChild(th);
/*
	var cellCheckAll = arrayLines[0].insertCell(-1);
	cellCheckAll.innerHTML += "<b>Everybody</b>";

	var cellDescrip = arrayLines[0].insertCell(-1);
	cellDescrip.innerHTML += "<b>Description</b>"; 
*/


}


/****************************************** EXTRA PERSON *******************************************/
function addExtraPerson(){
	
	var inputName = document.getElementById("name");
	var name = inputName.value;

	inputName.value = "add an extra person"; //setAttribute("value","");
	inputName.className = "textGrey";
	//var div = document.getElementById("listPerson");
	//div.innerHTML += "- "+name+" </br> ";


	tabPerson.push(name);

	
	k = tabPerson.length - 1;

	//add into THead
	var arrayLines = document.getElementById("tab").rows;
	var cell = arrayLines[0].insertCell(k+2);
	cell.innerHTML += "<b>"+name+"</b>";

	//add every checkbox
	var arrayLines = document.getElementById("tab").rows; 
	var hauteur = arrayLines.length;

	for(i=1; i<hauteur; i++){
		/* ajout des checkbox pr chaque lignes */
		id = i + (k+2).toString();
		var cell = arrayLines[i].insertCell(k+2);
		cell.innerHTML = '<input id="'+id+'" type="checkbox" onclick="verifAllRow(\''+i+'\')" onmouseover="checkBoxMouseOver(\''+id+'\')">';
		/* ajout du nom dans les select*/
		var select = document.getElementById(i.toString()+"0");
		select.innerHTML += '<option value="'+k+'">'+name+'</option>';
		//alert(select.innerHTML);
	}
}


/******************************** ROW ************************************/


function addRow(){
	//if (isStarted){
		var newRow = document.getElementById("tab").insertRow(-1);

		// Add Select
		var cell0 = newRow.insertCell(0);
		cell0.innerHTML += createTextSelect(theRowNumber);

		// Add Input Amount
		var cell1 = newRow.insertCell(1);
		var id = theRowNumber + "1";
		cell1.innerHTML = '<input class="textGrey" id="'+id+'" type="number" value="00.00"  onfocus="inputTextFocus(\''+id+'\', \'Red\')" onblur="inputTextBlur(\''+id+'\',\'00.00\')" >';

		// Add checkbox for persons
		for(i=0; i<tabPerson.length; i++){
			id = theRowNumber + (i+2).toString();
			var cell = newRow.insertCell(i+2);
			cell.innerHTML = '<input  id="'+id+'" type="checkbox" onclick="verifAllRow(\''+theRowNumber+'\')" onmouseover="checkBoxMouseOver(\''+id+'\')" >';			cell.style.width= "300px";
		}
		
		// Add button checkAllTheRow
		var cellButtonAll = newRow.insertCell(tabPerson.length+2);
		cellButtonAll.innerHTML = '<input type="checkbox" id="all'+theRowNumber+'" name="Checkall" value="check/uncheck all" onclick="checkAllRow('+theRowNumber+')"><label for="all'+theRowNumber+'" > all </label>';
		cellButtonAll.style.backgroundColor = "#FF8080";


		// Add Description
		var cellDescript = newRow.insertCell(tabPerson.length+3);
		cellDescript.innerHTML = '<textarea  class="textGrey" id="descript'+theRowNumber+'" rows="1" cols="50" onfocus="inputTextFocus(\'descript'+theRowNumber+'\', \'Red\')"  onblur="inputTextBlur(\'descript'+theRowNumber+'\',\'Description\')" >Description</textarea> ';
		
		theRowNumber++;
		alternBackground();
}

function textArea(){

	alert(document.getElementById("descript1").value);
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




function verifAllRow(rowNum){
	var boolAllChecked = true;
	for(i=0; i<tabPerson.length; i++){
		if(document.getElementById(rowNum.toString()+(i+2).toString()).checked == false){
			boolAllChecked = false;
		}
	}
	
	var checkAll = document.getElementById("all"+rowNum);
	checkAll.checked = boolAllChecked;
}



function checkAllRow(rowNum){
	var checkAll = document.getElementById("all"+rowNum);
	var bool = checkAll.checked;
	/*if(hidden.value == "true"){ //si toutes les checkbox sont deja cochees
		bool = false;
		hidden.value = "false";
		
	}
	else{
		bool = true;
		hidden.value = "true";
	} */
	for(var i=0; i<tabPerson.length; i++){
		var id = rowNum + (i+2).toString();
		document.getElementById(id).checked = bool;
	}
}
function reset(){
	//window.location = "accueil3.html";//"file:///home/entdev3/Documents/GIT/web/accueil3.html"
	location.href='calculation.html?tabPerson='+tabPerson;
}




/** ROW BACKGROUND ALTERNATE */
function alternBackground(){
	var arrayLignes = document.getElementById("tab").rows; //on récupère les lignes du tableau
	var hauteur = arrayLignes.length;//on peut donc appliquer la propriété length


	for(var i=1; i<hauteur; i++){
		if(i%2 == 0){
		  arrayLignes[i].style.backgroundColor= "#FF8080";
		}
	}
}





