
       <section >
       
       <div id="myBox" onclick="popup('../groupe/groupe.html')">
       	Bonjour
       </div>
       
        <article>
	 <h1 class="h1">Guide pratique </h1>
<!--<h2 id="h2Groupe" class="line" onmouseover="changeCursor('h2Groupe')" onclick="location.href='../groupe/groupe.html'">
		<img class="inline" src="../images/groupe.png" width="10%">
		<div class=inline" > Groupe </div>
	</h2>

-->
	<h2 id="h2Groupe" onmouseover="changeCursor('h2Groupe')" onclick="location.href='../groupe/groupe.html'">Groupe</h2>
                <p>Le groupe constitue toutes les personnes avec qui vous partez en vacances. </br>
		Pour le constituer, aller sur l'onglet "Groupe" puis ajouter chaque nom correspondant aux personnes de votre groupe</p>
	<h2 id="h2Doodle" onmouseover="changeCursor('h2Doodle')" onclick="location.href='../doodle/doodle.html'">Doodle</h2>
                <p>Le doodle vous permet de savoir ce que chaque personne du groupe pense d'un sujet. Par exemple pour vous mettre d'accord de la date de vos vacances, proposez des dates et chaque personne ira donner son avis.</p>
	<h2 id="h2Repas" onmouseover="changeCursor('h2Repas')" onclick="location.href='../repas/repas.html'">Repas - Courses</h2>
                <p>Cette partie consiste à planifier des repas durant vos vacances et generer automatiquement la liste de course qu'il vous faut en fonction du nombre de personne dans votre groupe</p>
	<h2 id="h2Calc" onmouseover="changeCursor('h2Calc')" onclick="location.href='../calculation/calculation.html'">Calcul des dépenses</h2>
                <p>Vous pouvez calculer qui doit combien à qui ! Et ce de manière très simple : à chaque fois qu'une personne paye quelquechose elle l'écrit. A tout moment vous pouvez voir qui doit de l'argent à qui.</p>
	<h2 id="h2Affaire" onmouseover="changeCursor('h2Affaire')" onclick="location.href='../affaire/affaire.html'">Loisirs</h2>
                <p>Vous pouvez créer votre liste d'affaire et la partager avec tous les membres de votre groupe afin d'être sur de ne rien oublier !</p>
	<h2 id="h2Loisirs" onmouseover="changeCursor('h2Loisirs')" onclick="location.href='../groupe/groupe.html'">Loisirs</h2>
                <p>Ici vous trouverez tout type de loisirs qui peuvent accompagner vos vacances : sport, jeu de societe, autres activités...</p>
	</article>
       </section>



<script src="../actionJS/navChanging.js" type="text/javascript"></script>

<SCRIPT>
    function popup(page) {
      window.open (page, 'hello', config='height=100, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, directories=no, status=no');
    }
  </SCRIPT>