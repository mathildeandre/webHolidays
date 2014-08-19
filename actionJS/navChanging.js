// Fonction modifiant le fond de page de façon aléatoire.
    function changeBackground() {
	//parseInt(Math.random()*4) genere un nombre aleatoirement entre 0 et 3 inclus
        window.document.getElementById("imgHeaderNav").style.background = 'url("../images/panoramic/panoramic'+parseInt(Math.random()*12)+'.jpg") no-repeat, url("../images/papier-peint14.jpeg")';
        window.document.getElementById("imgHeaderNav").style.backgroundSize = '100% 300px';
        window.document.getElementById("imgHeaderNav").style.backgroundAttachment="fixed";
 
        // Programmation d’un timer qui va réexécuter la fonction toutes les x secondes (en milliseconde)
        window.setTimeout(changeBackground, 2000); // en millisecondes
    }
    // Mise en place du fond de page une première fois au chargement de la page. => important sinon ce ne marche pas ?
    window.onload = changeBackground;



function changeCursor(id) {
                document.getElementById(id).style.cursor='pointer';
}
