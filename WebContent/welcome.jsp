<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- La section <head>. Nous aurons l'occasion d'en parler plus tard dans le cours ! -->
<head>
    <title>EasyHolidays</title>
    <meta charset='utf-8' />
    <link rel="shortcut icon" href="images/tree3.png" />

    <link rel="stylesheet" type="text/css" href="CSS/police.css" />
    <link rel="stylesheet" type="text/css" href="CSS/button.css" />
    <link rel="stylesheet" type="text/css" href="CSS/styleWeelcome.css" />
</head>


<!-- Mise en page classique avec le header, le corps, et le footer ! Les balises structurantes de
HTML5 sont employÃ©es. -->
<body id="imgHeaderNav" >
<header class="line">
	<div class="inline" id="title">
 	Organisation of 
	</div>
	
	<div  id="title2">
 	Organisation of holidays 
	</div>
	
	<form method="post" action="connexion">
	<div id="connexion" class="line inline"> 
		<input class="textGrey inline" id="coGroup" name="coGroup" type="text" value="Pseudo or Email" onfocus="inputTextFocus('coGroup', 'White')" onblur="inputTextBlur('coGroup','Pseudo or Email')" >
		<input class="textGrey inline" id="coPwd" name="coPwd" type="text" value="Password" onfocus="inputTextFocusPwd('coPwd', 'White')" onblur="inputTextBlurPwd('coPwd','Password')" >
		<input id="confirmConnexion" type="submit" value="Connection" class="inline  b3white" onmouseover="changeCursor('confirmConnexion')"  />
       
	</div>
	</form>


</header>



<section class="line" id="mySection">
	<article id="accederSite" class="inline"  >
		 <div id="bAccesSite" class=" b3white" onmouseover="changeCursor('bAccesSite')" onclick="location.href='index.jsp?page=homepage'">DEMO </br> reach directly </br> the website </div>
	</article>
	
	<article id="creationGroup" class="inline">
		<h2> Registrationsx </h2>
		<form method="post" action="inscription">
		  <div><input class="textGrey inline" id="pseudo" name="pseudo" type="text" value="Pseudo" 
         onfocus="inputTextFocus('pseudo', 'White')" onblur="inputTextBlur('pseudo','Pseudo')" required >
		 </div><span class="textRed">${requestScope.errors['nameGroup']}</span>
		<div><input class="textGrey inline" id="email" name="email" type="email" value="Email (optional)" 
		onfocus="inputTextFocus('email', 'White')" onblur="inputTextBlur('email','Email (optional)')" >
		 </div>
		<div><input class="textGrey inline" id="pwd" name="pwd" type="text" value="Password" 
		onfocus="inputTextFocusPwd('pwd', 'White')" onblur="inputTextBlurPwd('pwd','Password')" required>
		 </div><span class="textRed">${requestScope.errors['pwd']}</span>
		<div><input class="textGrey inline" id="confirmPwd" name="confirmPwd" type="text" value="Confirm your password" 
		onfocus="inputTextFocusPwd('confirmPwd', 'White')" onblur="inputTextBlurPwd('confirmPwd','Confirm your password')" required>
		 </div><span class="textRed">${requestScope.errors['confirmPwd']}</span>
		<input id="confirmInscription" type="submit" value="Confirm" class="b3white" onmouseover="changeCursor('confirmInscription')" 
		onclick="emtyAll('nameGroup','Name of your group','pseudo','Your name into the group', 'email','Your email adress (optional)', 'pwd','Your password (admin)', 'confirmPwd','Confirm your password')" />
       </form>
	</article>


</section>



<footer >Copyright © - Tous droits reserves<br/> Nous contacter : fabiensauce@orange.fr</footer>

<script src="JS/actionJS/welcomeChanging.js" type="text/javascript"></script>
<script src="JS/actionJS/actionInputText.js" type="text/javascript"></script>

</body>


 





