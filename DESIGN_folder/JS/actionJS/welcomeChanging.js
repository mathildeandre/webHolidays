
// Fonction modifiant le fond de page de façon aléatoire.
    function changeBackground() {
    	var elem = window.document.getElementById("bodyWelcome");
    	
	//parseInt(Math.random()*4) genere un nombre aleatoirement entre 0 et 3 inclus
        //elem.style.background = 'url("images/design'+parseInt(Math.random()*2)+'.jpg")';
    	elem.style.background = 'url("images/design0.jpg")';
        //elem.style.backgroundSize = '100% 100%';
        elem.style.backgroundAttachment="fixed";
 
        // Programmation d’un timer qui va réexécuter la fonction toutes les x secondes (en milliseconde)
        window.setTimeout(changeBackground, 5000); // en millisecondes
    }
    // Mise en place du fond de page une première fois au chargement de la page. => important sinon ce ne marche pas ?
    window.onload = changeBackground;


