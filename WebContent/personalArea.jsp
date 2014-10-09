<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- La section <head>. Nous aurons l'occasion d'en parler plus tard dans le cours ! -->
<head>
    <title>EasyHolidays</title>
    <meta charset='utf-8' />
    <link rel="shortcut icon" href="images/tree3.png" />
    
	<link rel="stylesheet" type="text/css" href="CSS/all9.css" />
   <!--<link rel="stylesheet" type="text/css" href="CSS/police.css" />
    <link rel="stylesheet" type="text/css" href="CSS/3D1.css" />
    <link rel="stylesheet" type="text/css" href="CSS/stylePersonalArea.css" />-->
</head>


<!-- Mise en page classique avec le header, le corps, et le footer ! Les balises structurantes de
HTML5 sont employees. -->
<body id="bodyWelcome" >
<header id="headerPersonalArea"  class="line">
	<div id="title" class="inline text3Dfonce " >
 	Organisation of holidays
	</div>
	
	<div id="titlePrivateArea" class="inline text3Dfonce"> 
		Personal Area
	</div>
	
	<div id="divLogOut" class="line inline"> 
	
		<div id="nameUser" class="inline text3Dfonce">${sessionScope.person.name}</div>
		
		<form method="post" action="logout">
			<!--ne s'aligne pas correctement: si on met deux ligne input.. avec le inline ca fonctionne^^-->
			<input id="buttonLogOut" type="submit" value="Log out" class="inline newButton3D buttonBlueFonce" onmouseover="changeCursor('buttonLogOut')"  />
		</form>
	
	</div>

</header>


<section id="sectionPersonalArea" class="line" >
		<!-- <div id="hint" class="aroundBlack">
		In order to create your first group : fill up "Group name", 
		create the group and the click on it into "My Group"
		</div> -->
		
		
		
	<article id="myGroups" class="inline"  >
	  	<fieldset> 
	    <legend><h2 class="text3Dfonce" >My Group</h2></legend>
	    <div id="fieldMyGroups" class="aroundBlack">
	    
	    <c:forEach var="group" items="${requestScope['listGroups']}">
	    		<li id="liGroup" onmouseover="changeCursor('liGroup')" onclick="location.href='index.jsp?page=homepage <% session.setAttribute("nameGroup", "${group.name}"); %>'" > ${group.name}</li> 
	    </c:forEach>
	    
		</div>
		</fieldset>
	 	<br>
		<form method="post" action="creationGroup">
		 <input id="groupName" class="textGrey"  name="groupName" type="text" value="Group name" 
         onfocus="inputTextFocus('groupName', 'White')" onblur="inputTextBlur('groupName','Group name')" required >
		 <div class="textRed">${requestScope.errors['createGroup']}</div>
		<input id="createGroup" type="submit" value="Create Group" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('createGroup')"/>
       </form>
	</article>
	
	
	
	<article id="myContacts" class="inline">
		<fieldset >
	    <legend><h2 class="text3Dfonce" >My Contacts</h2></legend>
	    <div id="fieldMyContact" class="aroundBlack">
			 - emiel <br>
			 - matthijs <br>
			 - fabian <br>
			 - kiki <br>
			 - emiel <br>
			 - matthijs <br>
			 - fabian <br>
			 - kiki <br>
			 - emiel <br>
			 - matthijs <br>
			 - fabian <br>
			 - kiki <br>
			 - emiel <br>
			 - matthijs <br>
			 - fabian <br>
			 - kiki <br>
			 - emiel <br>
			 - matthijs <br>
			 - fabian <br>
			 - kiki <br>
			 - emiel <br>
			 - matthijs <br>
			 - fabian <br>
			 - kiki <br>
		</div>
		</fieldset>
		<!-- 
		<form method="post" action="addPersonList">
		 <input id="nameNewContact" class="textGrey"  name="nameNewContact" type="text" value="Name new contact" 
         onfocus="inputTextFocus('nameNewContact', 'White')" onblur="inputTextBlur('nameNewContact','Name new contact')" required >
		<input id="addContact" type="submit" value="Add Contact" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('addContact')"/>
       </form>
        -->
        
		 <br>
		 <div  class="aroundBlack">Search existing person : </div>
       	<form method="post" action="searchPerson">
	      <input id="PAsearchPerson" class="textGrey"  name="PAsearchPerson" type="text" value="Login of the person" 
         onfocus="inputTextFocus('PAsearchPerson', 'Blue')" onblur="inputTextBlur('PAsearchPerson','Login of the person')" required >
		<input id="PAaddPerson" type="submit" value="Add person" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('PAaddPerson')"/>
       	</form>
       	
       	</br>
       	
       	<div  class="aroundBlack">Create yourself a person : </div>
       	<form method="post" action="searchPerson">
	      <div><input id="PAloginPerson" class="textGrey"  name="PAloginPerson" type="text" value="Login person" 
         onfocus="inputTextFocus('PAloginPerson', 'Blue')" onblur="inputTextBlur('PAloginPerson','Login Person')" required >
	      </div>
	      <div><input id="PApwdPerson" class="textGrey"  name="PApwdPerson" type="text" value="Password person" 
         onfocus="inputTextFocus('PApwdPerson', 'Blue')" onblur="inputTextBlur('PApwdPerson','Password Person')" required >
         </div>
         <!-- <div id="andOr" class="aroundWhite">AND/OR</div>  -->
         <div>
	      <input id="PAemailPerson" class="textGrey"  name="PAemailPerson" type="text" value="Email (optional)" 
         onfocus="inputTextFocus('PAemailPerson', 'Blue')" onblur="inputTextBlur('PAemailPerson','Email (optional)')" required >
		<input id="createPerson" type="submit" value="Create person" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('createPerson')"/>
       	</div>
       	</form>
       	
       	
       </br> 
       
       	<div  class="aroundBlack">Delete a contact : </div>
       <form method="post" action="deletePersonList">
	       <select id="listContacts" class="textGrey">
				<option>emiel</option>
				<option>matthijs</option>
				<option>fabian</option>
				<option>kiki</option>
			</select>
		
		<input id="deleteContact" type="submit" value="Delete contact" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('deleteContact')"/>
       </form>
	</article>




	<article id="myProfil" class="inline"  >
		 <h2 class="text3Dfonce">My Profil</h2>
		 
		 <form method="post" action="persoArea?action=modifyLogin">
		 <div class="aroundBlack">Login : </div>
		 
		 <c:if test="${requestScope.actionDone == 'modifyLogin'}" >
			<div class="textGreen">  The change of login has been successful </div>
		</c:if> 
		 <div class="textRed">${requestScope.errors['loginPerson']}</div>
		
		  <div><input id="login" class="textGrey inline"  name="login" type="text" value="${sessionScope.person.login}"
         onfocus="inputTextFocus('login', 'White')" onblur="inputTextBlur('login','${sessionScope.person.login}')" required >
         <input id="confirmModifyLogin" type="submit" value="Modify Login" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('confirmModifyLogin')" />
		 </div>
		 </form>
		 
		 <form method="post" action="persoArea?action=modifyName">
		 <c:if test="${requestScope.actionDone == 'modifyName'}" >
			<div class="textGreen">  The change of name has been successful </div>
		</c:if> 
		 <div class="aroundBlack">Name (visible into groups): </div>
		  <div><input id="name" class="textGrey inline"  name="name" type="text" value="${sessionScope.person.name}" 
         onfocus="inputTextFocus('name', 'White')" onblur="inputTextBlur('name','${sessionScope.person.name}')" required >
         <input id="confirmModifyName" type="submit" value="Modify Name" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('confirmModifyName')" />
		 </div>
		 </form>
		 
		 <form method="post" action="persoArea?action=modifyEmail">
		 <c:if test="${requestScope.actionDone == 'modifyEmail'}" >
			<div class="textGreen">  The change of email has been successful </div>
		</c:if> 
		 <div class="textRed">${requestScope.errors['emailPerson']}</div>
		
		 <div class="aroundBlack">Email adress :</div>
		  <div><input id="email" class="textGrey inline"  name="email" type="text" value="${sessionScope.person.email}" 
         onfocus="inputTextFocus('email', 'White')" onblur="inputTextBlur('email','${sessionScope.person.email}')" required >
         <input id="confirmModifyEmail" type="submit" value="Modify Email" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('confirmModifyEmail')" />
		 </div>
		 </form>
		 
		 <form method="post" autocomplete="off" action="persoArea?action=modifyPwd">
		 <c:if test="${requestScope.actionDone == 'modifyPwd'}" >
			<div class="textGreen">  The change of pwd has been successful </div>
		</c:if> 
		<div class="textRed">${requestScope.errors['oldPwd']}</div>
		 <div class="aroundBlack">Change Password :</div>
		  <div><input id="oldPwd" class="textGrey inline"  name="oldPwd" type="text" value="Old Password" 
         onfocus="inputTextFocusPwd('oldPwd', 'White')" onblur="inputTextBlurPwd('oldPwd','Old Password')" required >
         </div>
		<div class="textRed">${requestScope.errors['pwd']}</div>
		  <div><input id="newPwd" class="textGrey inline"  name="newPwd" type="text" value="New Password" 
         onfocus="inputTextFocusPwd('newPwd', 'White')" onblur="inputTextBlurPwd('newPwd','New Password')" required >
         </div>
		<div class="textRed">${requestScope.errors['confirmPwd']}</div>
		  <div><input id="confirmNewPwd" class="textGrey inline"  name="confirmNewPwd" type="text" value="Confirm New Pwd" 
         onfocus="inputTextFocusPwd('confirmNewPwd', 'White')" onblur="inputTextBlurPwd('confirmNewPwd','Confirm New Pwd')" required >
         
         <input id="confirmModifyPwd" type="submit" value="Modify Password" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('confirmModifyPwd')" />
		 </div>
		 </form>
	</article>
</section>


<footer id="footerPersonalArea">Copyright © - Tous droits reserves<br/> Nous contacter : fabiensauce@orange.fr</footer>

<script src="JS/actionJS/welcomeChanging.js" type="text/javascript"></script>
<script src="JS/actionJS/actionInputText.js" type="text/javascript"></script>

</body>


 





