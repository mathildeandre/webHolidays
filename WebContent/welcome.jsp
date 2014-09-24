<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- La section <head>. Nous aurons l'occasion d'en parler plus tard dans le cours ! -->
<head>
    <title>EasyHolidays</title>
    <meta charset='utf-8' />
    <link rel="shortcut icon" href="images/tree3.png" />

    <link rel="stylesheet" type="text/css" href="CSS/police.css" />
    <link rel="stylesheet" type="text/css" href="CSS/button.css" />
    <link rel="stylesheet" type="text/css" href="CSS/styleWelcome.css" />
</head>


<!-- Mise en page classique avec le header, le corps, et le footer ! Les balises structurantes de
HTML5 sont employÃ©es. -->
<body id="imgHeaderNav" >
<header class="line">
	<div class="inline" id="title">
 	Organisation of holidays
	</div>
	
	<form method="post" action="connexion">
	<div id="connexion" class="line inline"> 
		<input class="textGrey inline" id="coGroup" name="coGroup" type="text" value="Group" onfocus="inputTextFocus('coGroup', 'Blue')" onblur="inputTextBlur('coGroup','Group')" >
		<input class="textGrey inline" id="coPwd" name="coPwd" type="text" value="Password" onfocus="inputTextFocus('coPwd', 'Blue')" onblur="inputTextBlur('coPwd','Password')" >
		<input id="confirmConnexion" type="submit" value="Connexion" class="inline  b3white" onmouseover="changeCursor('confirmConnexion')"  />
       
	</div>
	</form>


</header>



<section class="line" id="mySection">
	<article id="accederSite" class="inline"  >
		 <div id="bAccesSite" class="button3D b3white" onmouseover="changeCursor('bAccesSite')" onclick="location.href='index.jsp?page=homepage'">Acceder au site </br> sans creer </br> de groupe </div>
	</article>
	
	<article id="creationGroup" class="inline">
		<h2> Creer votre groupe </h2>
		<form method="post" action="inscription">
         <div><input class="textGrey inline" id="nameGroup" name="nameGroup" type="text" value="Name of your group" 
         onfocus="inputTextFocus('nameGroup', 'Blue')" onblur="inputTextBlur('nameGroup','Name of your group')" required >
		 </div><span class="textRed">${requestScope.errors['nameGroup']}</span>
		  <div><input class="textGrey inline" id="nameAdmin" name="nameAdmin" type="text" value="Your name into the group" 
         onfocus="inputTextFocus('nameAdmin', 'Blue')" onblur="inputTextBlur('nameAdmin','Your name into the group')" required >
		 </div><span class="textRed">${requestScope.errors['nameGroup']}</span>
		<div><input class="textGrey inline" id="emailAdmin" name="emailAdmin" type="email" value="Your email adress (optional)" 
		onfocus="inputTextFocus('emailAdmin', 'Blue')" onblur="inputTextBlur('emailAdmin','Your email adress (optional)')" >
		 </div>
		<div><input class="textGrey inline" id="pwdAdmin" name="pwdAdmin" type="text" value="Your password (admin)" 
		onfocus="inputTextFocusPwd('pwdAdmin', 'Blue')" onblur="inputTextBlurPwd('pwdAdmin','Your password (admin)')" required>
		 </div><span class="textRed">${requestScope.errors['pwdAdmin']}</span>
		<div><input class="textGrey inline" id="confirmPwdAdmin" name="confirmPwdAdmin" type="text" value="Confirm your password" 
		onfocus="inputTextFocusPwd('confirmPwdAdmin', 'Blue')" onblur="inputTextBlurPwd('confirmPwdAdmin','Confirm your password')" required>
		 </div><span class="textRed">${requestScope.errors['confirmPwdAdmin']}</span>
		<input id="confirmInscription" type="submit" value="Confirm" class="b3white" onmouseover="changeCursor('confirmInscription')" 
		onclick="emtyAll('nameGroup','Name of your group','nameAdmin','Your name into the group', 'emailAdmin','Your email adress (optional)', 'pwdAdmin','Your password (admin)', 'confirmPwdAdmin','Confirm your password')" />
       </form>
	</article>


</section>



<footer >Copyright © - Tous droits reserves<br/> Nous contacter : fabiensauce@orange.fr</footer>

<script src="JS/actionJS/welcomeChanging.js" type="text/javascript"></script>
<script src="JS/actionJS/actionInputText.js" type="text/javascript"></script>

</body>


 





