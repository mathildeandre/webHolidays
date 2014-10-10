function functionDatepicker(){
	jQuery('.datepick').datepicker({
dateFormat: 'dd/mm/yy'});
}

/* declaration des variables */
var theRowNumber = 1;
var theColumnNumber = 1;
var tabPerson=["emiel","matthijs","fabian","kiki","aina","mathilde"]; 
var init = true;



/* start */
for(var i=0; i<tabPerson.length; i++){
	addPersonne(tabPerson[i]);
}
//addRow()
/* fin start */




/****************************************** EXTRA PERSON *******************************************/

function addPersonne(nameAdd){

	/*
	   var tr = document.getElementById('tab').tHead.children[0];
	   var th = document.createElement('th');


	   th.innerHTML = "hhhh";
	   tr.appendChild(th);
	 */

	var newRow = document.getElementById("tab").insertRow(-1);
	var cell0 = newRow.insertCell(0);
	cell0.innerHTML += "<div class='names'>"+nameAdd+"</div>";

	var cell;
	var id;
	for(var i=1; i<theColumnNumber; i++){
		cell = newRow.insertCell(i);
		id = theRowNumber + "1";
		cell.innerHTML += '<div class="roundedTwo"> <input type="checkbox" value="None" id="'+id+'" name="check"> <label for="'+id+'"></label> </div> '; 
	}
	theRowNumber++;

}



/******************************** done ************************************/

function reset(){
	//window.location = "accueil3.html";//"file:///home/entdev3/Documents/GIT/web/accueil3.html"
	location.href='calculation_holiday.html?tabPerson='+tabPerson;
}

function addRow(){
	var name = document.getElementById("name");
	addPersonne(name.value);
	/*
	   var newRow = document.getElementById("tab").insertRow(-1);
	   var cell0 = newRow.insertCell(0);
	   var id = theRowNumber + "1"; 
	   cell0.innerHTML += '<input id="'+id+'" type="text">'; 

	   var cell;
	   var id;
	   for(var i=1; i<=theColumnNumber; i++){
	   cell = newRow.insertCell(i);
	   id = theRowNumber + "1";
	   cell.innerHTML += '<input id="'+id+'" type="checkbox">'; 
	   }
	   theRowNumber++;
	 */
	
}

function addColumn(){

	var dateRad = document.getElementById("date");
	var textRad = document.getElementById("text");
	var arrayLines = document.getElementById("tab").rows;





	if(textRad.checked==false && dateRad.checked==false){
		alert("Veulliez cochez un des choix");
	}

	else{


		var tr = document.getElementById('tab').tHead.children[0];
		var th = document.createElement('th');

		if(textRad.checked){
			var text = document.getElementById("textCol").value;
			if(text==""){
				alert("veulliez entrez un titre de colonne");
			}else{
				var id;
				for(i=1; i<theRowNumber; i++){
					id = i +""+ theColumnNumber;
					var cell = arrayLines[i].insertCell(theColumnNumber);
					cell.innerHTML += '<div class="roundedTwo"> <input type="checkbox" value="None" id="'+id+'" name="check"> <label for="'+id+'"></label> </div> '; 
				}
				th.innerHTML = text;
				tr.appendChild(th);
				
				theColumnNumber++;
			}
		}
		else if(dateRad.checked){
			var dateS = document.getElementById("debutC").value;
			var dateE = document.getElementById("finC").value;
			if(dateS=="" || dateE==""){
				alert("Selectionnez une date de début et une date de fin");
			}else{
				var id;
				for(i=1; i<theRowNumber; i++){
					id = i +""+ theColumnNumber;
					var cell = arrayLines[i].insertCell(theColumnNumber);
					cell.innerHTML += '<div class="roundedTwo"> <input type="checkbox" value="None" id="'+id+'" name="check"> <label for="'+id+'"></label> </div> '; 
				}
				th.innerHTML = 'Du '+dateS+' au ' +dateE;
				tr.appendChild(th);

				theColumnNumber++;
			}
		}

	}

	alternBackground();
}

/** ROW BACKGROUND ALTERNATE */
function alternBackground(){
	var arrayLignes = document.getElementById("tab").rows; //on récupère les lignes du tableau
	var hauteur = arrayLignes.length;//on peut donc appliquer la propriété length


	for(var i=1; i<hauteur; i++){
		if(i%2 == 0){
		  arrayLignes[i].style.backgroundColor= "rgba(0,153,51,0.1)";
		}
	}
}



function checkRadio(id){
	if(id=="date"){
		var text = document.getElementById("textCol");
		text.value="titre colonne";
	}
	else if(id=="text"){
		document.getElementById("debutC").value = "";
		document.getElementById("finC").value = "";
	}

	var rad = document.getElementById(id);
	rad.checked = true;
}


functionDatepicker();
