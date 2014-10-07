<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- La section <head>. Nous aurons l'occasion d'en parler plus tard dans le cours ! -->
<head>
    <title>EasyHolidays</title>
    <meta charset='utf-8' />
    <link rel="shortcut icon" href="images/tree3.png" />
    
	<link rel="stylesheet" type="text/css" href="CSS/all2.css" />
   <!--  <link rel="stylesheet" type="text/css" href="CSS/police.css" />
    <link rel="stylesheet" type="text/css" href="CSS/3D1.css" />
    <link rel="stylesheet" type="text/css" href="CSS/styleWelcome2.css" /> -->
</head>


<!-- Mise en page classique avec le header, le corps, et le footer ! Les balises structurantes de
HTML5 sont employees. -->
<body id="bodyWelcome" >
<header id="headerWelcome" class="line">
	<div id="title" class="inline text3Dfonce " >
 	Organisation of holidays
	</div>
	
	<form method="post" action="logIn">
	<div id="divLogIn" class="line inline"> 
		<input id="coPseudo" class="textGrey inline" name="coPseudo" type="text" value="Pseudo or Email" onfocus="inputTextFocus('coPseudo', 'White')" onblur="inputTextBlur('coPseudo','Pseudo or Email')" >
		<input id="coPwd" class="textGrey inline" name="coPwd" type="text" value="Password" onfocus="inputTextFocusPwd('coPwd', 'White')" onblur="inputTextBlurPwd('coPwd','Password')" >
		<input id="confirmLogIn" type="submit" value="Log in" class="inline  newButton3D" onmouseover="changeCursor('confirmLogIn')"  />
       
	</div>
	</form>


</header>



<section id="sectionWelcome" class="line" >
	<article id="demo" class="inline"  >
		 <h2 id="buttonDemo" class="newButton3D" onmouseover="changeCursor('buttonDemo')" onclick="location.href='index.jsp?page=homepage'">DEMO </br> reach directly </br> the website </h2>
	</article>
	
	<article id="registration" class="inline">
		<h2 id="registrationH2" class="text3Dfonce"> Registration </h2>
		<form method="post" action="registration">
		
		  <div>
         <input id="login" class="textGrey inline"  name="login" type="text" value="Login" 
         onfocus="inputTextFocus('login', 'White')" onblur="inputTextBlur('login','Login')" required >
         <span class="textRed">${requestScope.errors['loginPerson']}</span>
		 </div>
		<div><input id="email" class="textGrey inline"  name="email" type="text" value="Email (optional)" 
		onfocus="inputTextFocus('email', 'White')" onblur="inputTextBlur('email','Email (optional)')" >
		 </div>
		<div><input id="pwd" class="textGrey inline"  name="pwd" type="text" value="Password" 
		onfocus="inputTextFocusPwd('pwd', 'White')" onblur="inputTextBlurPwd('pwd','Password')" required>
		 </div>
		<div><input id="confirmPwd" class="textGrey inline"  name="confirmPwd" type="text" value="Confirm your password" 
		onfocus="inputTextFocusPwd('confirmPwd', 'White')" onblur="inputTextBlurPwd('confirmPwd','Confirm your password')" required>
		 </div>
		<input id="confirmInscription" type="submit" value="Confirm" class="newButton3D" onmouseover="changeCursor('confirmInscription')" 
		onclick="emptyAll('login','Login', 'email','Email (optional)', 'pwd','Password', 'confirmPwd','Confirm your password')" />
       </form>
	</article>


</section>



<footer id="footerWelcome" >Copyright © - Tous droits reserves<br/> Nous contacter : fabiensauce@orange.fr</footer>

<script src="JS/actionJS/welcomeChanging.js" type="text/javascript"></script>
<script src="JS/actionJS/actionInputText.js" type="text/javascript"></script>

</body>
