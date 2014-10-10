

 <section id="sectionThings" class="section line">
   <h1 id="h1Things" class="h1">Things you need</h1> <!-- class="h1 text3Dfonce" -->
	
	<article id="articlePersonalThings" class="inline">
	  	<fieldset> 
	    <legend><h2>Personals things</h2></legend>
   		<div id="fieldPersonalThings" class="aroundWhite">
			 - rope <br>
			 - climbing shoes <br>
			 - powder <br>
			 - food <br>
		</div>
		</fieldset>
		
		</br>		
		</br>		
		</br>
		
		<div  class="aroundWhite">Add personals things : </div>
       	<form method="post" action="personalThings">
	      <input id="inputPersonalThing" class="textGrey"  name="inputPersonalThing" type="text" value="Thing" 
         onfocus="inputTextFocus('inputPersonalThing', 'Purple')" onblur="inputTextBlur('inputPersonalThing','Thing')" required >
		<input id="addPersonalThing" type="submit" value="Add Thing" class="newButton3D buttonThing buttonPurpleClair" onmouseover="changeCursor('addPersonalThing')"/>
       	</form>
       	
	</article>
	
	<article id="articleGroupThings" class="inline">
	  	<fieldset> 
	    <legend><h2>Group things</h2></legend>
   		<div id="fieldGroupThings" class="aroundWhite">
			 - pasta	<select id="thingsListContacts" class="textPurple">
								<option>emiel</option>
								<option>matthijs</option>
								<option>fabian</option>
								<option>kiki</option>
							</select>
			 <br> 
			 - cheese	<select id="thingsListContacts" class="textPurple">
								<option>emiel</option>
								<option>matthijs</option>
								<option>fabian</option>
								<option>kiki</option>
							</select>
			 <br> 
			 - braid	<select id="thingsListContacts" class="textPurple">
								<option>emiel</option>
								<option>matthijs</option>
								<option>fabian</option>
								<option>kiki</option>
							</select>
			 <br> 
			 - car	<select id="thingsListContacts" class="textPurple">
								<option>emiel</option>
								<option>matthijs</option>
								<option>fabian</option>
								<option>kiki</option>
							</select>
			 <br> 
		</div>
		</fieldset>
		
		</br>		
		</br>		
		</br>
		
		<div  class="aroundWhite">Add group things : </div>
       	<form method="post" action="personalThings">
	      <input id="inputGroupThing" class="textGrey"  name="inputGroupThing" type="text" value="Thing" 
         onfocus="inputTextFocus('inputGroupThing', 'Purple')" onblur="inputTextBlur('inputGroupThing','Thing')" required >
		<input id="addGroupThing" type="submit" value="Add Thing" class="newButton3D buttonThing buttonPurpleClair" onmouseover="changeCursor('addGroupThing')"/>
       	</form>
       	
	</article>
	
	
	
	


 <!--   
	<div class="ajout">
		<p>Add stuff to bring for your trip :</p>
		<div class="line">
		<input class="textGrey inline" id="name" type="text" name="name" value="add stuff" onfocus="inputTextFocus('name','Purple')" onblur="inputTextBlur('name','add stuff')" >
		<div id="but1" class="button3D b3purple inline" onmouseover="changeCursor('but1')" onclick="addPerson()"> Add stuff </div>
		</div>
	</div>

	<div id="buttonsBottom">
		<div class="button3D b3purple right" id="buttonReset" onmouseover="changeCursor('buttonReset')" onclick="location.href='affaire.html';"> Clear all stuff </div>
	 </br>
		<div class="button3D b3purple" id="confirm" onmouseover="changeCursor('confirm')" onclick="alert('reste plus qu\'a le faire pour de vrai ;) ');">CONFIRM</div>
	</div>
  </article>
-->
 </section >

<script src="JS/things.js" type="text/javascript"></script>
