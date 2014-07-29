
var myArray = new Array();

function createTab(){
	myArray.push('math');
	var userName = prompt('Entrez votre prénom :');
	myArray.push(userName);
	alert(myArray[0]);
}

createTab();


