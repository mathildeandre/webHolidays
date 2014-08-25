
<!DOCTYPE html>
<html onload="changeBackground()">

<head>
    <title>J'organise mes Vacances !!</title>
    <meta charset='utf-8' />
    <link rel="shortcut icon" href="images/tree3.png" />

	<link rel="stylesheet" type="text/css" href="../CSS/general.css"/>
    <link rel="stylesheet" type="text/css" href="../CSS/button.css" />
	<link rel="stylesheet" type="text/css" href="../CSS/styleGroupe.css"/>
    <link rel="stylesheet" type="text/css" href="../CSS/nav.css" />
</head>


<body id="imgHeaderNav" >
<header> <!--<img src="images/sun.png" alt="Tree" height="100" /> -->
 	Aorganisation of Holidays
	<!--<img src="images/tree3.png" alt="Tree" height="100" />-->
</header>


<nav>
	<ul>
	 <li id="liAccueil" onmouseover="changeCursor('liAccueil')" onclick="location.href='../accueil/accueil.html'">Homepage</li>
	 <li class="current" >Group</li>
	 <li id="liDoodle" onmouseover="changeCursor('liDoodle')" onclick="location.href='../doodle/doodle.html'" >Doodle</li>
	 <li id="liRepas" onmouseover="changeCursor('liRepas')" onclick="location.href='../repas/repas.html'">Meals</li>
	 <li id="liCalc" onmouseover="changeCursor('liCalc')" onclick="location.href='../calculation/calculation.html?tab=emiel,matthijs,fabian,kiki'">Expenses</li>
	 <li id="liAffaire" onmouseover="changeCursor('liAffaire')" onclick="location.href='../affaire/affaire.html'">Things</li>
	 <li id="liLoisirs" onmouseover="changeCursor('liLoisirs')" onclick="location.href='../loisirs/loisirs.html'">Hobbies</li>
	</ul>
</nav>



 <section >
   <h1>Your Group</h1>

  <article>
   <h2>Composition</h2>
<div class="composition">

	<div id="listPerson">
	 <p>- Emiel </p> 
	 <p>- Matthijs </p> 
	 <p>- Fabian </p> 
	 <p>- Kiki </p> 
	</div>
</div>


   <h2>Add person to your group</h2>
<div class="ajout">
		<div class="line">
		<input class="textGrey inline" id="name" type="text" name="name" value="add person" onfocus="inputTextFocus('name','Orange')" onblur="inputTextBlur('name','add person')" >
		<div class="button3D b3orange inline" id="b1" onmouseover="changeCursor('b1')" onclick="addPerson()"> Add person </div>
		</div>
		<!--<input type="button" name="addPerson" value="Add person" onclick="addPerson()">-->
</div>

	<div id="buttonsBottom">
		<div class="button3D b3orange right" id="buttonReset" onmouseover="changeCursor('buttonReset')" onclick="location.href='groupe.html';"> Clear the group </div>
		<!--<input  type="button" name="reset" value="Clear the group" onclick="location.href='groupe.html';">-->
	  </br>
		<div class="button3D b3orange" id="confirm" onmouseover="changeCursor('confirm')" onclick="alert('reste plus qu\'a le faire pour de vrai ;) ');">CONFIRM</div>
	</div>
  </article>
 </section >





<footer><p>Copyright fs - Tous droits réservés<br/> Nous contacter : fabiensauce@orange.fr</p></footer>


<script src="../actionJS/navChanging.js" type="text/javascript"></script>

<script src="../actionJS/actionInputText.js" type="text/javascript"></script>
<script src="groupe.js" type="text/javascript"></script>
</body>

</html>
