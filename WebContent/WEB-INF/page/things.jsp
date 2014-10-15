

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
       	<form method="post" action="thingsServlet?action=addPersonalThing">
	      	<input id="inputPersonalThing" name="inputPersonalThing" class="textGrey" type="text" value="Thing" 
         		onfocus="inputTextFocus('inputPersonalThing', 'Purple')" 
         		onblur="inputTextBlur('inputPersonalThing','Thing')" required >
         		
			<input id="addPersonalThing" type="submit" class="newButton3D buttonThing buttonPurpleClair" 
				onmouseover="changeCursor('addPersonalThing')" value="Add Thing" />
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
       	<form method="post" action="thingsServlet?action=addGroupThing">
	      	<input id="inputGroupThing" class="textGrey"  name="inputGroupThing" type="text" value="Thing" 
         		onfocus="inputTextFocus('inputGroupThing', 'Purple')" 
         		onblur="inputTextBlur('inputGroupThing','Thing')" required >
			<input id="addGroupThing" type="submit" class="newButton3D buttonThing buttonPurpleClair" 
				onmouseover="changeCursor('addGroupThing')" value="Add Thing"/>
       	</form>
       	
	</article>
	
	
	
	

 </section >

<script src="JS/things.js" type="text/javascript"></script>
