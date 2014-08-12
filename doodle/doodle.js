   jQuery(document).ready(function(){
   	jQuery('#debut').datepicker({
	   dateFormat: 'dd/mm/yy'});
   	jQuery('#fin').datepicker();
   });

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

	var newRow = document.getElementById("tab").insertRow(-1);
	var cell0 = newRow.insertCell(0);
	cell0.innerHTML += "<b>"+nameAdd+"</b>";

	var cell;
	var id;
	for(var i=1; i<=theColumnNumber; i++){
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
}

function addColumn(){

}

