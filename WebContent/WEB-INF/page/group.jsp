
 <section id="sectionGroup" class="section">
   <h1 class="h1 text3Dfonce">Your Group</h1>


	<article id="myComposition" >
	  	<fieldset> 
	    <legend><h2 class="text3Dfonce" >Composition</h2></legend>
	    <div id="fieldMyComposition" class="aroundWhite">
			 - kate <br>
			 - bibiche <br>
			 - toto <br>
		</div>
		</fieldset>
	 	<br>
	 	
	 	
	</article>
	
	<article>
		<h2 class="text3Dfonce" >Add person from your contact list</h2>
       	<form method="post" action="addFromList">
	       <select id="groupListContacts" class="textGrey">
				<option>emiel</option>
				<option>matthijs</option>
				<option>fabian</option>
				<option>kiki</option>
			</select>
		
		<input id="addMember" type="submit" value="Add member" class="newButton3D buttonOrangeFonce" onmouseover="changeCursor('addMember')"/>
       </form>
       
	</article>
	
	<article>
       
		<h2 class="text3Dfonce" >Add another person</h2>
		
		<div class="aroundWhite">Search existing person : </div>
       	<form method="post" action="searchPerson">
	      <input id="searchPerson" class="textGrey"  name="searchPerson" type="text" value="Login/Name person" 
         onfocus="inputTextFocus('searchPerson', 'White')" onblur="inputTextBlur('searchPerson','Login/Name Person')" required >
		<input id="addPerson" type="submit" value="Add person" class="newButton3D buttonOrangeFonce" onmouseover="changeCursor('addPerson')"/>
       	</form>
       
       </br></br>
       <div class="aroundWhite">Create yourself a person : </div>
       	<form method="post" action="searchPerson">
	      <div><input id="loginPerson" class="textGrey"  name="loginPerson" type="text" value="Login person" 
         onfocus="inputTextFocus('loginPerson', 'White')" onblur="inputTextBlur('loginPerson','Login Person')" required >
	      </div>
	      <div><input id="pwdPerson" class="textGrey"  name="loginPerson" type="text" value="Password person" 
         onfocus="inputTextFocus('pwdPerson', 'White')" onblur="inputTextBlur('pwdPerson','Password Person')" required >
         </div>
         <div class="aroundWhite">AND/OR</div> 
         <div>
	      <input id="emailPerson" class="textGrey"  name="emailPerson" type="text" value="Email person" 
         onfocus="inputTextFocus('emailPerson', 'White')" onblur="inputTextBlur('emailPerson','Password Person')" required >
		<input id="createPerson" type="submit" value="Create person" class="newButton3D buttonOrangeFonce" onmouseover="changeCursor('createPerson')"/>
       	</div>
       	</form>
       	
       	
       	
       	
	</article>
       	
       	
 </section >


<script src="JS/group.js" type="text/javascript"></script>


