
var init = true;
var tab=["emiel","matthijs","fabian","kiki","aina","mathilde"]; 



function addPerson(){
	if(init){
		tab=[];
		init=false;
	}
	var inputName = document.getElementById("name");
	var name = inputName.value;
	inputName.value = "add person"; //setAttribute("value","");
	inputName.className = "textGrey";

	var div = document.getElementById("listPerson");
	div.innerHTML += "<p>- "+name+" </p> ";

	tab.push(name);
}

function nextPage(){
	location.href='calculation_holiday.html?tab='+tab;
}
