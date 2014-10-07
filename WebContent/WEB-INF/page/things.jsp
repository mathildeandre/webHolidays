

 <section id="sectionThings">
   <h1>List of stuffs</h1>

  <article>
   <h2>My list</h2>
<div class="composition">

	<div id="listPerson">
	</div>
</div>


   <h2>Ajout</h2>
<div class="ajout">
		<p>Add stuff to bring for your trip :</p>
		<div class="line">
		<input class="textGrey inline" id="name" type="text" name="name" value="add stuff" onfocus="inputTextFocus('name','Purple')" onblur="inputTextBlur('name','add stuff')" >
		<div id="but1" class="button3D b3purple inline" onmouseover="changeCursor('but1')" onclick="addPerson()"> Add stuff </div>
		</div>
		<!--<input type="button" name="addPerson" value="Add person" onclick="addPerson()">-->
</div>

	<div id="buttonsBottom">
		<div class="button3D b3purple right" id="buttonReset" onmouseover="changeCursor('buttonReset')" onclick="location.href='affaire.html';"> Clear all stuff </div>
		<!--<input  type="button" name="reset" value="Clear the group" onclick="location.href='groupe.html';">-->
	  </br>
		<div class="button3D b3purple" id="confirm" onmouseover="changeCursor('confirm')" onclick="alert('reste plus qu\'a le faire pour de vrai ;) ');">CONFIRM</div>
	</div>
  </article>
 </section >

<script src="JS/things.js" type="text/javascript"></script>
