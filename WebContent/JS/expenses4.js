
/* declaration des variables */
var theRowNumber = 1;



/* Recuperation du tableau */
var url = window.location.search;
var tabStr = url.substring(url.lastIndexOf("=")+1);
//alert("start:"+tabStr+":finshed");
var tabPerson = tabStr.split(",");

	//on reecrit le tab de maniere temp
	tabPerson=["emiel","matthijs","fabian","kiki","niaude","math","fab","loulou"]; 
	
/* start */
//createTHead();
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
	var tr = document.getElementById('tab').tHead.children[0];
	var th = document.createElement('th');
	th.innerHTML = name;
	tr.insertBefore(th, tr.children[k+2]);
	/*** old ***
	var arrayLines = document.getElementById("tab").rows;
	var cell = arrayLines[0].insertCell(k+2);
	cell.innerHTML += "<b>"+name+"</b>";
	*/
	
	
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
		var newRow = document.getElementById("tbody").insertRow(-1);

		// Add Select
		var cell0 = newRow.insertCell(0);
		cell0.innerHTML += createTextSelect(theRowNumber);

		// Add Input Amount
		var cell1 = newRow.insertCell(1);
		var id = theRowNumber + "1";
		cell1.innerHTML = '<input class="textRed" id="'+id+'" type="number" value="00.00"  onfocus="inputTextFocus(\''+id+'\', \'Red\')" onblur="inputTextBlur(\''+id+'\',\'00.00\')" >';

		// Add checkbox for persons
		for(i=0; i<tabPerson.length; i++){
			id = theRowNumber + (i+2).toString();
			var cell = newRow.insertCell(i+2);
			cell.innerHTML = '<input  id="'+id+'" type="checkbox" onclick="verifAllRow(\''+theRowNumber+'\')" onmouseover="checkBoxMouseOver(\''+id+'\')" >';			cell.style.width= "300px";
		}
		
		// Add button checkAllTheRow
		var cellButtonAll = newRow.insertCell(tabPerson.length+2);
		cellButtonAll.innerHTML = '<label name for="all'+theRowNumber+'" >All </label><input type="checkbox" id="all'+theRowNumber+'" name="Checkall" value="check/uncheck all" onclick="checkAllRow('+theRowNumber+')">';
		/*permet de mettre toutes les cells All avec background*/
		//cellButtonAll.style.backgroundColor = "#FF8080";


		// Add Description
		var cellDescript = newRow.insertCell(tabPerson.length+3);
		cellDescript.innerHTML = '<textarea  class="textGrey" id="descript'+theRowNumber+'" rows="1" cols="17" onfocus="inputTextFocus(\'descript'+theRowNumber+'\', \'Red\')"  onblur="inputTextBlur(\'descript'+theRowNumber+'\',\'Description\')" >Description</textarea> ';
		
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
		  arrayLignes[i].style.backgroundColor= "rgba(255,77,77,0.2)";
		}
	}
}


function deletee(){
/*var row = document.getElementById("myRow");
row.deleteCell(0);

document.getElementById("myTable").deleteRow(0);	*/
}

function afficheCell(){
	var txt = "";
   var table = document.getElementById("tab");
   //alert(table.innerHTML);
   
   
	var arrayLignes = document.getElementById("tab").rows; //on récupère les lignes du tableau
	var hauteur = arrayLignes.length;//on peut donc appliquer la propriété length
	var largeur = arrayLignes[0].length;
	
	
	for(i=0; i<hauteur; i++){

		var arrayColonnes = arrayLignes[i].cells;
		var largeur = arrayColonnes.length;

		for(j=0; j<largeur; j++){
			txt += " *** ("+i+","+j+"):"+arrayColonnes[j].innerHTML;
		}
		txt += "\n \n";
	}
	alert(txt);
	
	
	
	
	/*var row1 = arrayLignes[1];
	var cel = row1[3];
	
   alert(cel.value);
   
   */
   var tbody = table.getElementsByTagName('tbody')[0];
   var row1 = tbody.getElementsByTagName("tr")[0];//tbody[0]?
   var tdElements = row1.getElementsByTagName('td');
   
   //alert(tdElements[1].innerHTML);
  /* 
    <td onClick="Test(this.innerHTML)">Site du Zéro</td>
    
    
	/*for(var i=1; i<hauteur; i++){
		if(i%2 == 0){
		  arrayLignes[i].style.backgroundColor= "#FF8080";
		}
	}*/
}

/*************** useless ************/
function colorTabColonnes(){ //Color une colonne sur deux en plus foncé
	var arrayLignes = document.getElementById("tab1").rows; //l'array est stocké dans une variable
	var longueur = arrayLignes.length;//on peut donc appliquer la propriété length

	arrayLignes[0].style.backgroundColor = "#F5BDCB"; //rose
	for(i=1; i<longueur; i++){

		var arrayColonnes = arrayLignes[i].cells;
		var largeur = arrayColonnes.length;

		for(j=0; j<largeur; j++){
			if(j % 2 == 0){  //si la clé est paire
				arrayColonnes[j].style.backgroundColor = "#bdcbf5"; //bleu clair
			}
		else{ //elle est impaire
				arrayColonnes[j].style.backgroundColor = "#829eeb"; //bleu fonce
			}
		}
	}

}








