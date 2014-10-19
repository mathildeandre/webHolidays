function functionDatepicker(){
	jQuery('.datepick').datepicker({
		dateFormat: 'dd/mm/yy'});
}

tabDays=["Sunday", "Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"]; 


function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text/html", ev.target.id);
}

function drop(ev) {
    var data=ev.dataTransfer.getData("text/html");
	if(ev.target.hasChildNodes()){
		ev.target.parentNode.replaceChild(document.getElementById(data).cloneNode(true), ev.target);
	}
    ev.preventDefault();
    ev.target.appendChild(document.getElementById(data).cloneNode(true)); 
}


function checkRadio(id){
	if(id=="date"){
		var text = document.getElementById("textCol");
		text.value="column title";
		text.className = "textGrey";
	}
	else if(id=="text"){
		document.getElementById("debutC").value = "";
		document.getElementById("finC").value = "";
	}

	var rad = document.getElementById(id);
	rad.checked = true;
}


function createPlanning(){
	var dateS = document.getElementById("debutC").value;
	var dateE = document.getElementById("finC").value;
	
	document.getElementById("startDate").value = dateS;
	document.getElementById("endDate").value = dateE;
	document.getElementById("actualDay").innerHTML = dateS;	
}



function nextDayFunc(){
	var actualDate = document.getElementById("actualDay").innerHTML;
	var tabActualDate = actualDate.split(" ");
	tabActualDate = tabActualDate[1].split("/");
	var actualDay = parseInt(tabActualDate[0]);
	var actualMonth = parseInt(tabActualDate[1]);
	var actualYear = parseInt(tabActualDate[2]);
	
	var endDate = document.getElementById("endDate").value;
	var tabEndDate = endDate.split("/");
	var endDay = parseInt(tabEndDate[0]);
	var endMonth = parseInt(tabEndDate[1]);
	var endYear = parseInt(tabEndDate[2]); 
	
	if(! isLowerDate(actualDay, actualMonth, actualYear, endDay, endMonth, endYear)){
		document.getElementById("nextDayButton").value = "end";
	}else{
		var numberDay = parseInt(daysInMonth(actualMonth, actualYear));
		if(actualDay >= numberDay){
			actualDay = 1;
			if(actualMonth >= 12){
				actualMonth=1;
				actualYear++;
			}else{
				actualMonth++;
			}
		}else{
			actualDay++;
		}
		/* on ecrit bien les jours, mois, annee */
		if(actualDay < 10){
			actualDay = "0"+actualDay;
		}
		if(actualMonth < 10){
			actualMonth = "0"+actualMonth;
		}

		var date = new Date(actualYear ,actualMonth-1, actualDay);
		var day = tabDays[date.getDay()];
		document.getElementById("actualDay").innerHTML = day + " " +(actualDay+'/'+actualMonth+'/'+actualYear);
	}	
}

function isLowerDate(date1Day, date1Month, date1Year, date2Day, date2Month, date2Year){
	if(date1Year > date2Year){
		return false;
	}
	if(date1Month>date2Month && date1Year>=date2Year){
		return false;
	}
	if(date1Day>=date2Day && date1Month>=date2Month && date1Year>=date2Year){
		return false;
	}
	return true;
}

function daysInMonth(month, year) {
    return new Date(year, month, 0).getDate();
}


functionDatepicker();