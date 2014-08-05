

/********************************  Fonctions sur calculation results ************************************/
var tabCouple;
var newTabCouple; 

/*
function calculationResult(){
	creationTabExpense();


	creationTabCouple();
	//afficheTabCouple(tabCouple, "tab couple : ");
	deleteDuplicate();
	//afficheTabCouple(newTabCouple, "newTabCouple : ");
	optimisation();
	//afficheTabCouple(newTabCouple, "after optimisation : ");
	afficheTabCouple(newTabCouple, "before cleaning : ");
	cleanTab();
	afficheTabCouple(newTabCouple, "after cleaning : ");
	afficheResult();

}
*/

//tab.splice(0,1); remove 1 element at the place n°0
function inverserCouple(cpl){
	var a=cpl[0];
	var m=cpl[1];
	var b=cpl[2];
	cpl[0]=b;
	cpl[1]=m*(-1);
	cpl[2]=a;
	return cpl
}


function afficheTabCouple(tab, str){
	var txt = "";
	for(var i=0; i<tab.length; i++){
		txt += tab[i] + "\n";
	}
	alert(str+txt);
}



function afficheResult(){
	var text = '';
	for(var k=0; k<newTabCouple.length; k++){
		text += tabPerson[newTabCouple[k][2]] + " -> : " + newTabCouple[k][1] + "€ to "+ tabPerson[newTabCouple[k][0]] + "\n";
	}
	alert(text+'(-> : "owes")');
}

/********************************** fin fonctions sur calculation results ************************************/





/* construit tous les couples dans tabCouple */
function creationTabCouple(){ 
	tabCouple = [];
	/*
	var tab = document.getElementById("tab");
	var tags = tab.getElementsByTagName("input");
	alert("mon premier tag : "+tags[0].innerHTML);
	*/
	
	var arrayLignes = document.getElementById("tab").rows; //on récupère les lignes du tableau
	var longueur = arrayLignes.length;//on peut donc appliquer la propriété length


	for(var i=1; i<longueur; i++){//on peut directement définir la variable i dans la boucle

		var largeur = arrayLignes[i].cells.length; // arrayColonnes.length;

		//alert("longueur : "+longueur+" - i : "+i);
		var idBuyer = document.getElementById(i.toString()+"0").value;
		var amount = parseFloat(document.getElementById(i.toString()+"1").value);
		var tabBeneficiary = [];

		for(var j=2; j<largeur-1; j++){
			var element = document.getElementById(i.toString()+j.toString());
    			if(element.checked == true){
				tabBeneficiary.push(j-2);
			}
		}
		var amountPerson = amount/tabBeneficiary.length; //float

		for(var k=0; k<tabBeneficiary.length; k++){
			if(idBuyer != tabBeneficiary[k]){
				var unCouple = [idBuyer,amountPerson,tabBeneficiary[k]];
				tabCouple.push(unCouple);
			}
		}
	}
}



function deleteDuplicate(){
	newTabCouple = [];

	for(var i=0; i<tabCouple.length; i++){
		var idBuyer = tabCouple[i][0];
		var amount = tabCouple[i][1];
		var idBenef = tabCouple[i][2];
		var alreadySeen = false;

		for(var k=0; k<newTabCouple.length; k++){
			if((idBuyer == newTabCouple[k][0]) && (idBenef == newTabCouple[k][2])){
				alreadySeen = true;
			}
			if((idBuyer == newTabCouple[k][2]) && (idBenef == newTabCouple[k][0])){
				alreadySeen = true;
			}
		}
		if(!alreadySeen){
			for(var j=i+1; j<tabCouple.length; j++){
			
				if((idBuyer == tabCouple[j][0]) && (idBenef == tabCouple[j][2])){ //on a un doublon
					amount += tabCouple[j][1];
				}
				if((idBuyer == tabCouple[j][2]) && (idBenef == tabCouple[j][0])){ //on a un doublon
					amount -= tabCouple[j][1];
				}
			}
			
			if(amount < 0){ 
				amount = amount*(-1); 
				var x = idBuyer;
				idBuyer = idBenef;
				idBenef = x;
			}
			var newCouple = [idBuyer,amount,idBenef];
			newTabCouple.push(newCouple);
		}
		
	}
}

function cleanTab(){
	for(var i=0; i<newTabCouple.length; i++){
		if(newTabCouple[i][1] == 0){
			newTabCouple.splice(i,1);
		}
		if(newTabCouple[i][1] < 0){
			newTabCouple[i] = inverserCouple(newTabCouple[i]);
		}
	}
}




