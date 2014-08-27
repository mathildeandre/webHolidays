
// Fonction modifiant le fond de page de façon aléatoire.
    function changeBackground() {
	//parseInt(Math.random()*4) genere un nombre aleatoirement entre 0 et 3 inclus
        window.document.getElementById("imgHeaderNav").style.background = 'url("images/design'+parseInt(Math.random()*2)+'.jpg")';
        //window.document.getElementById("imgHeaderNav").style.backgroundSize = '100% 100%';
        window.document.getElementById("imgHeaderNav").style.backgroundAttachment="fixed";
 
        // Programmation d’un timer qui va réexécuter la fonction toutes les x secondes (en milliseconde)
        window.setTimeout(changeBackground, 5000); // en millisecondes
    }
    // Mise en place du fond de page une première fois au chargement de la page. => important sinon ce ne marche pas ?
    window.onload = changeBackground;


