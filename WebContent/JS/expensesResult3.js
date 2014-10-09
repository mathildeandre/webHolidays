
/* nouveau type de calculation : remplace les fichiers "calculationResult_basic.js" & "calculationResult_optimisation.js" */

var tabCouple;
var tabExpense;

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
	tabCouple=[];
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
	tabExpense = [];
	for(var i=0; i<tabPerson.length; i++){
		tabExpense.push(0);
	}
	
	var arrayLignes = document.getElementById("tab").rows; //on récupère les lignes du tableau
	var hauteur = arrayLignes.length;//on peut donc appliquer la propriété length


	for(var i=1; i<hauteur; i++){

		var idBuyer = parseInt(document.getElementById(i.toString()+"0").value);
		var amountBuyerPayed = parseFloat(document.getElementById(i.toString()+"1").value);

		// on compte combien de personne sont concernees par le payement
		var nbChecked = 0;
		for(var j=0; j<tabPerson.length; j++){
			if(document.getElementById(i.toString()+(j+2).toString()).checked){
				nbChecked++;
			}
		}
		var amountPerson = (amountBuyerPayed/nbChecked); //.toFixed(1); //prix par personne

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





