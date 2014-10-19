
 <section id="sectionGroup" class="section line">
   <h1 id="h1Group" class="h1 ">Your Group</h1> <!-- class="h1 text3Dfonce" -->

<!-- pour remmetre les h2 en 3D ecrire : class="text3Dfonce" -->
	<article id="articleComposition" class="inline">
	  	<fieldset> 
	    <legend><h2 class="" >Composition</h2></legend>
	    <div id="fieldMyComposition" class="aroundWhite">
	    
	    
	     	<c:forEach var="members" items="${sessionScope.group.listMembers}">
	    		<li > ${members.name}</li> 
	    	</c:forEach>
	    
		</div>
		</fieldset>
	</article>
	
	<article id="articleAddMember" class="inline">
		<h2 class="" >Add person from your contact list</h2>
       	<form method="post" action="group?action=addContactIntoGroup">
			<select id="groupListContacts" name="groupListContacts" class="textOrange">
		       	<c:forEach var="contactPerson" items="${sessionScope.contactList}">
		    		<option value="${contactPerson.login}"> ${contactPerson.login}</option> 
		    	</c:forEach>
			</select>
		
		<input id="addMember" type="submit" value="Add member" class="newButton3D buttonGroup buttonOrangeClair" onmouseover="changeCursor('addMember')"/>
       </form>
       
		<h2 class="" >Add another person</h2>
		
		<div  class="aroundWhite">Search existing person : </div>
		 <span class="textRed">${requestScope.errors['searchPerson']}</span>
       	<form method="post" action="group?action=searchPerson">
	      <input id="searchPerson" class="textGrey"  name="searchPerson" type="text" value="Login of the person" 
         onfocus="inputTextFocus('searchPerson', 'Orange')" onblur="inputTextBlur('searchPerson','Login of the person')" required >
         
		<input id="addPerson" type="submit" value="Add person" class="newButton3D buttonGroup buttonOrangeClair" onmouseover="changeCursor('addPerson')"/>
       	</form>
       	
       	</br>
       	
       	<div  class="aroundWhite">Create yourself a person : </div>
      <form method="post" action="group?action=createPerson">
       	
			<div class="textGreen">  ${requestScope.createPerson} </div>
       	
	      <div><input id="loginPerson" class="textGrey"  name="login" type="text" value="Login person" 
         onfocus="inputTextFocus('loginPerson', 'Orange')" onblur="inputTextBlur('loginPerson','Login Person')" required >
         <span class="textRed">${requestScope.errors['loginPerson']}</span>
	      </div>
	      <div><input id="pwdPerson" class="textGrey"  name="pwd" type="text" value="Password person" 
         onfocus="inputTextFocus('pwdPerson', 'Orange')" onblur="inputTextBlur('pwdPerson','Password Person')" required >
		<span class="textRed">${requestScope.errors['pwd']}</span>
         </div>
         
         <!-- 
         <div><input id="confirmPwdPerson" class="textGrey"  name="confirmPwd" type="text" value="Confirm Password person" 
         onfocus="inputTextFocus('confirmPwdPerson', 'Orange')" onblur="inputTextBlur('confirmPwdPerson','Password Person')" required >
         <span class="textRed">${requestScope.errors['confirmPwd']}</span>
         </div>   -->
         <!-- <div id="andOr" class="aroundWhite">AND/OR</div>  -->
         <div>
	      <input id="emailPerson" class="textGrey"  name="email" type="text" value="Email (optional)" 
         onfocus="inputTextFocus('emailPerson', 'Orange')" onblur="inputTextBlur('emailPerson','Email (optional)')" >
         <span class="textRed">${requestScope.errors['emailPerson']}</span>
		<input id="createPerson" type="submit" value="Create person" class="newButton3D buttonGroup buttonOrangeClair" onmouseover="changeCursor('createPerson')"
			onclick="emptyAll('loginPerson','Login person', 'emailPerson','Email (optional)', 'pwdPerson','Password person', 'confirmPwdPerson','Confirm Password person')" >
		
       	</div>
      </form>
       	
	</article>
 </section >

<script src="JS/group.js" type="text/javascript"></script>


