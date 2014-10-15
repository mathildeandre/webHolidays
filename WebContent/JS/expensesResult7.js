
/*init */
var tabExpense = [];
var tabPerson = [];
var tabCouple = [];


var nbPers = document.getElementById("nbMemberHidden").value;
for(k=0; k<nbPers; k++){
	var idPers = document.getElementById("thName"+k).value;
	
	tabPerson.push(idPers);
}

/* fin init*/

//on lance la fonction une premiere fois
calculationResult();


function calculationResult(){
	creationTabExpense();
	//afficheTab(tabExpense, "tab expense : ");

	creationTabCouple();
	//afficheTab(tabCouple, "tab couple : ");

	afficheResult();
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
	for(var k=0; k<tabCouple.length; k++){
		text += tabPerson[tabCouple[k][2]] + " -> " + tabCouple[k][1] + "€ to "+ tabPerson[tabCouple[k][0]] + "</br>\n";
	}
	
	
	var resultDiv = document.getElementById("fieldResultExpenses");
	resultDiv.innerHTML = text+'</br>(-> : "owes")';
	
	
	//alert(text+'(-> : "owes")');
}

/********************************** calculations ************************************/



function creationTabCouple(){
	tabCouple=[]; //on revide le tab
	var indexMax = findIndexMax(tabExpense);
	var indexMin = findIndexMin(tabExpense, (-1)*tabExpense[indexMax]);
	while(indexMax != -1){ //tant qu'il y a des personne encore avec un montant payé > 0
		if(indexMin == -1){ //dans le cas ou il a par ex deux positif : 2 et 1 et un negatif -3
			indexMin = findIndexMin(tabExpense);
			var amount = tabExpense[indexMax];
			tabExpense[indexMin] += amount;
			tabExpense[indexMax] = 0;

			tabCouple.push([indexMax, amount.toFixed(1), indexMin]);

		}
		else{
			var amount = tabExpense[indexMin]*(-1);
			tabExpense[indexMax] -= amount;
			tabExpense[indexMin] = 0;

			tabCouple.push([indexMax, amount.toFixed(1), indexMin]);
		}
		indexMax = findIndexMax(tabExpense);
		indexMin = findIndexMin(tabExpense, (-1)*tabExpense[indexMax]);
	}

}


/* construit tous les couples dans tabCouple */
function creationTabExpense(){ 
	tabExpense = [];//on revide le tab
	for(var i=0; i<tabPerson.length; i++){
		tabExpense.push(0);
	}
	
	var arrayLignes = document.getElementById("tab").rows; //on récupère les lignes du tableau
	var hauteur = arrayLignes.length-1;//length -1 car th ne nous interess pas


	for(var i=0; i<hauteur; i++){
		var idBuyer = parseInt(document.getElementById(i.toString()+"select").value);
		var amountBuyerPayed = parseFloat(document.getElementById(i+"total").value);

		// on compte combien de personne sont concernees par le payement
		var nbChecked = 0;
		for(var j=0; j<tabPerson.length; j++){
			if(document.getElementById(i.toString()+j.toString()).checked){
				nbChecked++;
			}
		}
		if(nbChecked > 0){
			var amountPerson = (amountBuyerPayed/nbChecked); //.toFixed(1); //prix par personne
			var positionBuyer=-1;
			//on veut determiner la position de colonne du buyer
			for(var p=0; p<tabPerson.length; p++){
				var idPerson = parseInt(document.getElementById("th"+p.toString()).value);
				if(idBuyer == idPerson){
					positionBuyer = p;
				}
			}
			if(positionBuyer == -1){
				alert(" ERREUR position pers.");
			}
			//est ce que le buyer a payer pour lui meme?
			if (document.getElementById(i.toString()+positionBuyer).checked == true ){ //si celui qui a payé est coché
				amountBuyerPayed -= amountPerson; //alors on lui soustrait sa part
			}

			tabExpense[positionBuyer] += amountBuyerPayed;
			for(var k=0; k<tabPerson.length; k++){
				if(document.getElementById(i.toString()+k.toString()).checked && (positionBuyer != k) ){
					tabExpense[k] -= amountPerson;
				}
			}
		}
		
	}
}


function findIndexMin(tab,borneMin){
	var min = 0;
	var index = -1;
	if (typeof borneMin === 'undefined') { //borneMin non specifié
		borneMin = -Infinity;
	}
	for(var i=0; i<tab.length; i++){
		if(borneMin <= tab[i] && tab[i] < min){
			min = tab[i];
			index = i;
		}
	}
	return index;
}
function findIndexMax(tab){
	var max = 0.1;
	var index = -1;
	for(var i=0; i<tab.length; i++){
		if(tab[i] > max){
			max = tab[i];
			index = i;
		}
	}
	return index;
}





