
 <section id="sectionGroup" class="section line">
   <h1 id="h1Group" class="h1 ">Your Group</h1>

<!-- pour remmetre les h2 en 3D ecrire : class="text3Dfonce" -->
	<article id="articleComposition" class="inline">
	  	<fieldset> 
	    <legend><h2 class="" >Composition</h2></legend>
	    <div id="fieldMyComposition" class="aroundWhite">
			 - kate <br>
			 - bibiche <br>
			 - toto <br>
			 - kate <br>
			 - bibiche <br>
			 - toto <br>
			 - kate <br>
		</div>
		</fieldset>
	</article>
	
	<article id="articleAddMember" class="inline">
		<h2 class="" >Add person from your contact list</h2>
       	<form method="post" action="addFromList">
	       <select id="groupListContacts" class="textOrange">
				<option>emiel</option>
				<option>matthijs</option>
				<option>fabian</option>
				<option>kiki</option>
			</select>
		
		<input id="addMember" type="submit" value="Add member" class="newButton3D buttonGroup buttonOrangeClair" onmouseover="changeCursor('addMember')"/>
       </form>
       
		<h2 class="" >Add another person</h2>
		
		<div  class="aroundWhite">Search existing person : </div>
       	<form method="post" action="searchPerson">
	      <input id="searchPerson" class="textGrey"  name="searchPerson" type="text" value="Login of the person" 
         onfocus="inputTextFocus('searchPerson', 'Orange')" onblur="inputTextBlur('searchPerson','Login of the person')" required >
		<input id="addPerson" type="submit" value="Add person" class="newButton3D buttonGroup buttonOrangeClair" onmouseover="changeCursor('addPerson')"/>
       	</form>
       	
       	</br>
       	
       	<div  class="aroundWhite">Create yourself a person : </div>
       	<form method="post" action="searchPerson">
	      <div><input id="loginPerson" class="textGrey"  name="loginPerson" type="text" value="Login person" 
         onfocus="inputTextFocus('loginPerson', 'Orange')" onblur="inputTextBlur('loginPerson','Login Person')" required >
	      </div>
	      <div><input id="pwdPerson" class="textGrey"  name="loginPerson" type="text" value="Password person" 
         onfocus="inputTextFocus('pwdPerson', 'Orange')" onblur="inputTextBlur('pwdPerson','Password Person')" required >
         </div>
         <!-- <div id="andOr" class="aroundWhite">AND/OR</div>  -->
         <div>
	      <input id="emailPerson" class="textGrey"  name="emailPerson" type="text" value="Email (optional)" 
         onfocus="inputTextFocus('emailPerson', 'Orange')" onblur="inputTextBlur('emailPerson','Email (optional)')" required >
		<input id="createPerson" type="submit" value="Create person" class="newButton3D buttonGroup buttonOrangeClair" onmouseover="changeCursor('createPerson')"/>
       	</div>
       	</form>
       	
	</article>
 </section >


<script src="JS/group.js" type="text/javascript"></script>


