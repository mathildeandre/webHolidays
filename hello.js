var ohai = "hey";
function sayHello() {
    ohai = 'Hello world !';
}

sayHello();

//alert(ohai);     
/********************************************************************/
function myFunction(arg) {
    alert('Votre argument : ' + arg);
}

//myFunction(prompt('Que souhaitez-vous passer en argument à la fonction ?'));


/********************************************************************/
function sayHello() {
    return 'Bonjour !';
    alert('Attention ! Le texte arrive !');
}
/********************************************************************/


alert(sayHello());


//showMsg(); 



function showMsg() {
    var nom = prompt('Entrez votre nom :');
	alert('votre nom est ... ' + nom + ' !!');
}


