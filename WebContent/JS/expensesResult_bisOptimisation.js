
function optimisation(){
	var over = false;
	var i = 0;
	var j = 0;
	var k = 0;
	//on travail sur newTabCouple
	while(i < newTabCouple.length){

		//couple à traiter 
		var idBuyer = newTabCouple[i][0];
		var amount = newTabCouple[i][1];
		var idBen = newTabCouple[i][2];
		var found = false;
		j = i+1;
		var toFind;
		var compl;
		var config = "";

		//find 3 couples to reduce
		while(!found && j < newTabCouple.length){
			found=true;
			if(idBuyer == newTabCouple[j][0]){
				config="ac";
				toFind = newTabCouple[j][2];
				compl = idBen;
			}
			else if (idBuyer == newTabCouple[j][2]){
				config = "ca";
				toFind = newTabCouple[j][0];
				compl = idBen;
			}
			else if (idBen == newTabCouple[j][0]){
				config = "bc";
				toFind = newTabCouple[j][2];
				compl = idBuyer;
			}
			else if (idBen == newTabCouple[j][2]){
				config = "cb";
				toFind = newTabCouple[j][0];
				compl = idBuyer;

			}
			else {  found = false; 
				j++;
			}


			if(found){ //on trouve
				k = j+1;
				var found2 = false;
				while(!found2 && k < newTabCouple.length){
					if( (toFind == newTabCouple[k][0] || toFind == newTabCouple[k][2]) && 
						(compl == newTabCouple[k][0] || compl == newTabCouple[k][2]) ){
					 found2 = true;	
					 //ACTION!!!!!!
					 var couple1 = newTabCouple[i]; // a b
					 var couple2 = newTabCouple[j];
					 var couple3 = newTabCouple[k];

					 newTabCouple.splice(k,1); //on delete le couple de newTabCouple[k]

					 switch (config) {
					    case "ac": // couple2 : a c
						if(couple3[0] == idBen){ // couple3 : b c
							couple1[1] -= couple3[1]; 
							couple2[1] += couple3[1];
						}
						if(couple3[2] == idBen){ // couple3 : c b
							couple2[1] -= couple3[1]; 
							couple1[1] += couple3[1];
						}
						break;
					    case "ca": // couple2 : c a
						couple2 = inverserCouple(couple2); //mnt couple2 : a c
						if(couple3[0] == idBen){ // couple3 : b c
							couple1[1] -= couple3[1]; 
							couple2[1] += couple3[1];
						}
						if(couple3[2] == idBen){ // couple3 : c b
							couple2[1] -= couple3[1]; 
							couple1[1] += couple3[1];
						}
						break;
					    case "bc": // couple2 : b c 
						couple2 = inverserCouple(couple2); //mnt couple2 : c b
						if(couple3[0] == idBuyer){ // couple3 : a c
							coupl1[1] += couple3[1]; 
							coupl2[1] -= couple3[1];
						}
						if(couple3[2] == idBuyer){ // couple3 : c a
							couple1[1] -= couple3[1]; 
							couple2[1] += couple3[1];

						}
						break;
					    case "cb": // couple2 : c b
						if(couple3[0] == idBuyer){ // couple3 : a c
							couple1[1] += couple3[1]; 
							couple2[1] -= couple3[1];
						}
						if(couple3[2] == idBuyer){ // couple3 : c a
							couple1[1] -= couple3[1]; 
							couple2[1] += couple3[1];
						}
						break;
					 } // fin switch
					 newTabCouple[i]=couple1; // a b
					 newTabCouple[j]=couple2;
					} //fin if	
					else{ 
						k++;
					}
				}//fin while3
				if(!found2){ //on est sorti du while car parcouru tout le tab -> mais pas trouvé	
				 found = false; 
				 j++;
				}
			}
		}//fin while2 : on est sorti car le found reste a true
		if(!found){//on est sorti du while car j>tab.length : on a finit pour ce i
			i++;
		}
	}//fin while1
}