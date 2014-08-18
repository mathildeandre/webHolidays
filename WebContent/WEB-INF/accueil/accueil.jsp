<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="shortcut icon" href="images/tree3.png" />

    <!-- il est important de mettre general au dessus du style propre a la classe comme ca general est moins prioritaire-->
    <link rel="stylesheet" type="text/css" href="CSS/general.css" />
    <link rel="stylesheet" type="text/css" href="CSS/button.css" />
    <link rel="stylesheet" type="text/css" href="CSS/nav.css" />
    <link rel="stylesheet" type="text/css" href="CSS/styleAccueil.css" />
    <link rel="stylesheet" type="text/css" href="CSS/active.css" />
    
</head>
<body id="imgHeaderNav" >
<header> <!--<img src="images/sun.png" alt="Tree" height="100" /> -->
 	Organisation of holidays
	<!--<img src="images/tree3.png" alt="Tree" height="100" />-->
</header>

<nav>
	<ul>
	 <li class="current" >Homepage</li>
	 <li id="liGroupe" onmouseover="changeCursor('liGroupe')" onclick="location.href='groupe/groupe.html'" >Group</li>
	 <li id="liDoodle" onmouseover="changeCursor('liDoodle')" onclick="location.href='doodle/doodle.html'" >Doodle</li>
	 <li id="liRepas" onmouseover="changeCursor('liRepas')" onclick="location.href='repas/repas.html'">Meals</li>
	 <li id="liCalc" onmouseover="changeCursor('liCalc')" onclick="location.href='calculation/calculation.html?tab=emiel,matthijs,fabian,kiki'">Expenses</li>
	 <li id="liAffaire" onmouseover="changeCursor('liAffaire')" onclick="location.href='affaires/affaire.html'">Things</li>
	 <li id="liLoisirs" onmouseover="changeCursor('liLoisirs')" onclick="location.href='loisirs/loisirs.html'">Hobbies</li>
	</ul>
</nav>
 <section >
       
       <div id="myBox" onclick="popup('../groupe/groupe.html')">
       	Hello
       </div>
       
        <article>
	 <h1>Practical guide </h1>
<!--<h2 id="h2Groupe" class="line" onmouseover="changeCursor('h2Groupe')" onclick="location.href='../groupe/groupe.html'">
		<img class="inline" src="../images/groupe.png" width="10%">
		<div class=inline" > Groupe </div>
	</h2>

-->
	<h2 id="h2Groupe" onmouseover="changeCursor('h2Groupe')" onclick="location.href='../groupe.html'">Group</h2>
                <p>The group of all people going in holidays with you. </br></p>
	<h2 id="h2Doodle" onmouseover="changeCursor('h2Doodle')" onclick="location.href='../doodle/doodle.html'">Doodle</h2>
                <p> Doodle is a tab where every body can answer to a specific question. It simplifies scheduling but also organisation. For example you can ask what people prefer eating tonight. </p>
	<h2 id="h2Repas" onmouseover="changeCursor('h2Repas')" onclick="location.href='../repas/repas.html'">Meals</h2>
                <p> You can discover some nice recipes (most of them are french). You can also plan enjoyable meals and have an automatic shopping list. It makes your shopping faster and your cooking easier ! </p>  
	<h2 id="h2Calc" onmouseover="changeCursor('h2Calc')" onclick="location.href='../calculation/calculation.html?tab=emiel,matthijs,fabian,kiki'">Expenses</h2>
                <p> You can write all your expenses and then the service will calculate how much every body owes to every one. </p>
	<h2 id="h2Affaire" onmouseover="changeCursor('h2Affaire')" onclick="location.href='../affaire/affaire.html'">Things</h2>
		<p> You can create a list of things for the trip. You can then share it with people from the group. </p>
              <!--  <p>Vous pouvez créer votre liste d'affaire et la partager avec tous les membres de votre groupe afin d'être sur de ne rien oublier !</p> -->
	<h2 id="h2Loisirs" onmouseover="changeCursor('h2Loisirs')" onclick="location.href='../groupe/groupe.html'">Hobbies</h2>
		<p> Here you will find all kind of hobbies that can fit your holidays : sport, boardgames, nice activities ... </p>
                <!-- <p>Ici vous trouverez tout type de loisirs qui peuvent accompagner vos vacances : sport, jeu de societe, autres activités...</p> -->
	</article>
       </section>



<footer>Copyright fs - Tous droits réservés<br/> Contact : fabiensauce@orange.fr  mathildeandre@orange.fr</footer>

<script src="actionJS/navChanging.js" type="text/javascript"></script>
<script src="actionJS/actionInputText.js" type="text/javascript"></script>

<SCRIPT>
    function popup(page) {
      window.open (page, 'hello', config='height=100, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, directories=no, status=no');
    }
  </SCRIPT>
</body>
</html>