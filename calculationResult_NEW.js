
/* nouveau type de calculation : remplace les fichiers "calculationResult_basic.js" & "calculationResult_optimisation.js"

/********************************  Fonctions sur calculation results ************************************/
var tabCouple;
var newTabCouple; 
var tabExpense;

function calculationResult(){
	var num=12.39;
	//alert("num : "+num.toFixed(1));
	creationTabExpense();
	afficheTab(tabExpense, "tab expense : ");
	

	creationTabCouple();
	afficheTab(tabCouple, "tab couple : ");
}


function afficheTab(tab, str){
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



function creationTabCouple(){
	tabCouple=[];
	var indexMax = findIndexMax(tabExpense);
	var indexMin = findIndexMin(tabExpense, (-1)*tabExpense[indexMax]);
	alert("borne : "+((-1)*tabExpense[indexMax]));
	alert("indexmin : "+indexMin);
	while(indexMax != -1){ //tant qu'il y a des personne encore avec un montant payé > 0
		
		alert("tab expense [indexMax] : "+tabExpense[indexMax]);
		alert("tab expense [indexMin] : "+tabExpense[indexMin]);
		tabExpense[indexMax] += tabExpense[indexMin];
		tabExpense[indexMin] = 0;
		
		alert("after , tab expense [indexMax] : "+tabExpense[indexMax]);
		alert("after , tab expense [indexMin] : "+tabExpense[indexMin]);
		indexMax = findIndexMax(tabExpense);
		alert("rooo : "+(-1)*tabExpense[indexMax]);
		indexMin = findIndexMin(tabExpense, (-1)*tabExpense[indexMax]);
		alert("rooo2 : "+indexMin);
	}

}

function findIndexMin(tab,borneMin){
	var min = 0;
	var index = -1;
	if (typeof borneMin === 'undefined') { //borneMin non specifié
		for(var i=0; i<tab.length; i++){
			if(tab[i] < min){
				min = tab[i];
				index = i;
			}
		}
	}
	else{
		for(var i=0; i<tab.length; i++){
			if(borneMin <= tab[i] && tab[i] < min){
				min = tab[i];
				index = i;
			}
		}

	}
	
	
	return index;
}
function findIndexMax(tab){
	var max = 0;
	var index = -1;
	for(var i=0; i<tab.length; i++){
		if(tab[i] > max){
			max = tab[i];
			index = i;
		}
	}
	return index;
}

/* construit tous les couples dans tabCouple */
function creationTabExpense(){ 
	tabExpense = [];
	for(var i=0; i<tabPerson.length; i++){
		tabExpense.push(0);
	}
	/*
	var tab = document.getElementById("tab");
	var tags = tab.getElementsByTagName("input");
	alert("mon premier tag : "+tags[0].innerHTML);
	*/
	
	var arrayLignes = document.getElementById("tab").rows; //on récupère les lignes du tableau
	var hauteur = arrayLignes.length;//on peut donc appliquer la propriété length


	for(var i=1; i<hauteur; i++){

		var idBuyer = parseInt(document.getElementById(i.toString()+"0").value);
		var amountBuyerPayed = parseFloat(document.getElementById(i.toString()+"1").value);
		
		/*
		alert("idBuyer : "+idBuyer);
		alert("parseint idBuyer : "+parseInt(idBuyer+2));
		alert("type : "+typeof(idBuyer));
		*/

		// on compte combien de personne sont concernees par le payement
		var nbChecked = 0;
		for(var j=0; j<tabPerson.length; j++){
			if(document.getElementById(i.toString()+(j+2).toString()).checked){
				nbChecked++;
			}
		}
		var amountPerson = (amountBuyerPayed/nbChecked).toFixed(1); //prix par personne

		//est ce que le buyer a payer pour lui meme?
		if (document.getElementById(i.toString()+(idBuyer+2)).checked == true ){ //si celui qui a payé est coché
			amountBuyerPayed -= amountPerson; //alors on lui soustrait sa part
		}

		tabExpense[idBuyer] += amountBuyerPayed;
		for(var k=0; k<tabPerson.length; k++){
			if(document.getElementById(i.toString()+(k+2).toString()).checked && (idBuyer != k) ){
				tabExpense[k] -= amountPerson;
			}
		}

	}
}








