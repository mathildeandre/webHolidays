
<!-- La section <head>. Nous aurons l'occasion d'en parler plus tard dans le cours ! -->
<head>
    <title>J'organise mes Vacances !!</title>
    <meta charset='utf-8' />
    <link rel="shortcut icon" href="images/tree3.png" />

    <link rel="stylesheet" type="text/css" href="CSS/header.css" />
    <link rel="stylesheet" type="text/css" href="CSS/button.css" />
    <link rel="stylesheet" type="text/css" href="CSS/styleIndex.css" />
</head>
<!-- Mise en page classique avec le header, le corps, et le footer ! Les balises structurantes de
HTML5 sont employées. -->
<body id="imgHeaderNav" >
<header class="line">
	<div class="inline" id="title">
 	Organisation of holidays
	</div>
	
	<div id="connexion" class="line inline"> 
		<input class="textGrey inline" id="coGroup" type="text" value="Group" onfocus="inputTextFocus('coGroup', 'Blue')" onblur="inputTextBlur('coGroup','Group')" >
		<input class="textGrey inline" id="coPwd" type="text" value="Password" onfocus="inputTextFocus('coPwd', 'Blue')" onblur="inputTextBlur('coPwd','Password')" >
		<div class="inline button3D b3white">Connexion</div>
	</div>


</header>



<section class="line" id="mySection">
	<article id="accederSite" class="inline"  >
		 <div id="bAccesSite" class="button3D b3white" onmouseover="changeCursor('bAccesSite')" onclick="location.href='index.jsp?page=accueil'">Acceder au site </br> sans créer </br> de groupe </div>
	</article>
	
	<article id="creationGroup" class="inline">
		<h2> Créer votre groupe </h2>
		
		<div><input class="textGrey inline" id="nameGroup" type="text" value="Nom du groupe" onfocus="inputTextFocus('nameGroup', 'Blue')" onblur="inputTextBlur('nameGroup','Nom du groupe')" >
		 </div>
		<div><input class="textGrey inline" id="email" type="text" value="Votre adresse electronique" onfocus="inputTextFocus('email', 'Blue')" onblur="inputTextBlur('email','Votre adresse electronique')" >
		 </div>
		<div><input class="textGrey inline" id="mdpAdmin" type="text" value="Votre mot de passe (admin)" onfocus="inputTextFocus('mdpAdmin', 'Blue')" onblur="inputTextBlur('mdpAdmin','Votre mot de passe (admin)')" >
		 </div>
		<div><input class="textGrey inline" id="confirmMdpAdmin" type="text" value="Confirmation de votre mdp" onfocus="inputTextFocus('confirmMdpAdmin', 'Blue')" onblur="inputTextBlur('confirmMdpAdmin','Confirmation de votre mdp')" >
		 </div>
		<div><input class="textGrey inline" id="mdpMembre" type="text" value="Mot de passe pour les membres du groupes" onfocus="inputTextFocus('mdpMembre', 'Blue')" onblur="inputTextBlur('mdpMembre','Mot de passe pour les membres du groupes')" >
		 </div>
		<div><input class="textGrey inline" id="confirmMdpMembre" type="text" value="Confirmation du mdp des membres" onfocus="inputTextFocus('confirmMdpMembre', 'Blue')" onblur="inputTextBlur('confirmMdpMembre','Confirmation du mdp des membres')" ></div>
		<div id="confirm" class="button3D b3white" onmouseover="changeCursor('confirm')" onclick="location.href='../accueil/accueil.html'">Confirmer</div>
	</article>


</section>



<footer >Copyright fs - Tous droits réservés<br/> Nous contacter : fabiensauce@orange.fr</footer>

<!--<script src="actionJS/navChanging.js" type="text/javascript"></script>-->
<script src="actionJS/indexChanging.js" type="text/javascript"></script>
<script src="actionJS/actionInputText.js" type="text/javascript"></script>

</body>


 





