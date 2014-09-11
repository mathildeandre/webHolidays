
<!-- La section <head>. Nous aurons l'occasion d'en parler plus tard dans le cours ! -->
<head>
    <title>J'organise mes Vacances !!</title>
    <meta charset='utf-8' />
    <link rel="shortcut icon" href="images/tree3.png" />

    <link rel="stylesheet" type="text/css" href="CSS/header.css" />
    <link rel="stylesheet" type="text/css" href="CSS/button.css" />
    <link rel="stylesheet" type="text/css" href="CSS/styleWelcome.css" />
</head>
<!-- Mise en page classique avec le header, le corps, et le footer ! Les balises structurantes de
HTML5 sont employées. -->
<body id="imgHeaderNav" >
<header class="line">
	<div class="inline" id="title">
 	Organisation of holidays
	</div>
	
	<form method="post" action="connexion">
	<div id="connexion" class="line inline"> 
		<input class="textGrey inline" id="coGroup" name="coGroup" type="text" value="Group" onfocus="inputTextFocus('coGroup', 'Blue')" onblur="inputTextBlur('coGroup','Group')" >
		<input class="textGrey inline" id="coPwd" name="coPwd" type="text" value="Password" onfocus="inputTextFocus('coPwd', 'Blue')" onblur="inputTextBlur('coPwd','Password')" >
		<input id="confirm" type="submit" value="Connexion" class="inline button3D b3white" onmouseover="changeCursor('confirm')"  />
       
	</div>
	</form>


</header>



<section class="line" id="mySection">
	<article id="accederSite" class="inline"  >
		 <div id="bAccesSite" class="button3D b3white" onmouseover="changeCursor('bAccesSite')" onclick="location.href='index.jsp?page=homepage'">Acceder au site </br> sans créer </br> de groupe </div>
	</article>
	
	<article id="creationGroup" class="inline">
		<h2> Creer votre groupe </h2>
		<form method="post" action="welcome">
         <div><input class="textGrey inline" id="nameGroup" name="nameGroup" type="text" value="Name of your group" 
         onfocus="inputTextFocus('nameGroup', 'Blue')" onblur="inputTextBlur('nameGroup','Name of your group')" >
		 </div>
		<div><input class="textGrey inline" id="email" name="email" type="email" value="Your email adress" 
		onfocus="inputTextFocus('email', 'Blue')" onblur="inputTextBlur('email','Your email adress')" >
		 </div>
		<div><input class="textGrey inline" id="pwdAdmin" name="pwdAdmin" type="text" value="Your password (admin)" 
		onfocus="inputTextFocusPwd('pwdAdmin', 'Blue')" onblur="inputTextBlurPwd('pwdAdmin','Your password (admin)')" >
		 </div>
		<div><input class="textGrey inline" id="confirmPwdAdmin" name="confirmPwdAdmin" type="text" value="Confirm your password" 
		onfocus="inputTextFocusPwd('confirmPwdAdmin', 'Blue')" onblur="inputTextBlurPwd('confirmPwdAdmin','Confirm your password')" >
		 </div>
		<div><input class="textGrey inline" id="pwdMembers" name="pwdMembers" type="text" value="Password for members of your group" 
		onfocus="inputTextFocusPwd('pwdMembers', 'Blue')" onblur="inputTextBlurPwd('pwdMembers','Password for members of your group')" >
		 </div>
		<div><input class="textGrey inline" id="confirmPwdMembers" name="confirmPwdMembers" type="text" value="Confirm members's password" 
		onfocus="inputTextFocusPwd('confirmPwdMembers', 'Blue')"  onblur="inputTextBlurPwd('confirmPwdMembers','Confirm members's password')"></div>
		<input id="confirm" type="submit" value="Confirm" class="button3D b3white" onmouseover="changeCursor('confirm')"  />
       </form>
	</article>


</section>



<footer >Copyright � - Tous droits reserves<br/> Nous contacter : fabiensauce@orange.fr</footer>

<script src="JS/actionJS/indexChanging.js" type="text/javascript"></script>
<script src="JS/actionJS/actionInputText.js" type="text/javascript"></script>

</body>


 





