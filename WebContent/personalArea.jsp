<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- La section <head>. Nous aurons l'occasion d'en parler plus tard dans le cours ! -->
<head>
    <title>EasyHolidays</title>
    <meta charset='utf-8' />
    <link rel="shortcut icon" href="images/tree3.png" />
    
    
	<link rel="stylesheet" type="text/css" href="CSS/all5.css" />
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
	<form method="post" action="logOut">
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
			 - group 1 <br>
			 - group 2 <br>
			 - group 3 <br>
		</div>
		</fieldset>
	 	<br>
		<form method="post" action="createGroup">
		 <input id="groupName" class="textGrey"  name="groupName" type="text" value="Group name" 
         onfocus="inputTextFocus('groupName', 'White')" onblur="inputTextBlur('groupName','Group name')" required >
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
		 <br>
		<form method="post" action="addPersonList">
		 <input id="nameNewContact" class="textGrey"  name="nameNewContact" type="text" value="Name new contact" 
         onfocus="inputTextFocus('nameNewContact', 'White')" onblur="inputTextBlur('nameNewContact','Name new contact')" required >
		<input id="addContact" type="submit" value="Add Contact" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('addContact')"/>
       </form>
       
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
		 
		 <form method="post" action="modifyLogin">
		 <div class="aroundBlack">Login : </div>
		 
		  <div><input id="login" class="textGrey inline"  name="login" type="text" value="${sessionScope.person.login}"
         onfocus="inputTextFocus('login', 'White')" onblur="inputTextBlur('login','${sessionScope.person.login}')" required >
         <input id="confirmModifyLogin" type="submit" value="Modify Login" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('confirmModifyLogin')" />
		 </div>
		 </form>
		 
		 <form method="post" action="modifyName">
		 <div class="aroundBlack">Name (visible into groups): </div>
		  <div><input id="name" class="textGrey inline"  name="name" type="text" value="${sessionScope.person.name}" 
         onfocus="inputTextFocus('name', 'White')" onblur="inputTextBlur('name','${sessionScope.person.name}')" required >
         <input id="confirmModifyName" type="submit" value="Modify Name" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('confirmModifyName')" />
		 </div>
		 </form>
		 
		 <form method="post" action="modifyEmail">
		 <div class="aroundBlack">Email adress :</div>
		  <div><input id="email" class="textGrey inline"  name="email" type="text" value="${sessionScope.person.email}" 
         onfocus="inputTextFocus('email', 'White')" onblur="inputTextBlur('email','${sessionScope.person.email}')" required >
         <input id="confirmModifyEmail" type="submit" value="Modify Email" class="newButton3D buttonBlueFonce" onmouseover="changeCursor('confirmModifyEmail')" />
		 </div>
		 </form>
		 
		 <form method="post" action="modifyPassword">
		 <div class="aroundBlack">Change Password :</div>
		  <div><input id="oldPwd" class="textGrey inline"  name="oldPwd" type="text" value="Old Password" 
         onfocus="inputTextFocusPwd('oldPwd', 'White')" onblur="inputTextBlurPwd('oldPwd','Old Password')" required >
         </div>
		  <div><input id="newPwd" class="textGrey inline"  name="newPwd" type="text" value="New Password" 
         onfocus="inputTextFocusPwd('newPwd', 'White')" onblur="inputTextBlurPwd('newPwd','New Password')" required >
         </div>
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


 





