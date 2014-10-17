function functionDatepicker(){
	jQuery('.datepick').datepicker({
		dateFormat: 'dd/mm/yy'});
}

tabDays=["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"]; 



function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text/html", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data=ev.dataTransfer.getData("text/html");
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
	var tabActualDay = actualDate.split("/");
	var actualDay = tabActualDay[0];
	var actualMonth = tabActualDay[1];
	var actualYear = tabActualDay[2];
	
	/*
	var endDate = document.getElementById("endDate").value;
	var tabEndDate = endDate.split("/");
	var endDay = tabActualDay[0];
	var endMonth = tabEndDate[1];
	var endYear = tabEndDate[2]; */
	
	if(actualDay<31){
		actualDay++;
		document.getElementById("actualDay").innerHTML = (actualDay+'/'+actualMonth+'/'+actualYear);
	}
	
}


functionDatepicker();