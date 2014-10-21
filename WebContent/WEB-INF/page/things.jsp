

 <section id="sectionThings" class="section line">
   <h1 id="h1Things" class="h1">Things you need</h1> <!-- class="h1 text3Dfonce" -->
	
	<article id="articlePersonalThings" class="inline">
	  	<fieldset> 
	    <legend><h2>Personals things</h2></legend>
   		<div id="fieldPersonalThings" class="aroundWhite">
   		
   			<c:forEach var="thing" items="${sessionScope.things.listThingPersonal}">
				<div> - ${thing.name}</div>
			</c:forEach>
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
       	
       	</br>
       	
       	<form method="post" action="thingsServlet?action=deletePersonalThing">
			<select id="deletePersonalThing" name="deletePersonalThing" class="textPurple">
				<c:forEach var="thing" items="${sessionScope.things.listThingPersonal}">
					<option> - ${thing.name}</option>
				</c:forEach>
			</select>
		
			<input id="submitDeletePersonalThing" type="submit" value="Delete personal thing" class="newButton3D buttonThing buttonPurpleClair" onmouseover="changeCursor('submitDeletePersonalThing')"/>
       	</form>
       	
	</article>
	
	<article id="articleGroupThings" class="inline">
	  	<fieldset> 
	    <legend><h2>Group things</h2></legend>
   		<div id="fieldGroupThings" class="aroundWhite">
   		
   		
   		<!--  onchange="alert('id thing :  ${thingGroup.id}, idPerson : ${thingGroup.idPerson}')" -->
   		<form id="formGroupThing" method="post" action="thingsServlet?action=changeGroupThing">
   		
   			<input type="hidden" id="nameSelect" name="nameSelect" value="" >
   			
   			<c:forEach var="thingGroup" items="${sessionScope.things.listThingGroup}" varStatus="i">
				<div> - ${thingGroup.name} 
					<select name="${thingGroup.id}-${i.index}" class="textPurple" 
						onchange="submitSelect('${thingGroup.id}-${i.index}')">
						<option value="-1" >nobody</option> 
						<c:forEach var="member" items="${sessionScope.group.listMembers}">
							<c:set var="selected" value=""></c:set>
							<c:if test="${thingGroup.idPerson == member.id}">
								<c:set var="selected" value="selected"></c:set>
							</c:if>
		    				<option value="${member.id}" ${selected}> ${member.name}</option> 
		    			</c:forEach>
	    	
					</select>
				
				
				</div>
			</c:forEach>
		</form>
			
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
       	
       	</br>
       	
       		<form method="post" action="thingsServlet?action=deleteGroupThing">
			<select id="deleteGroupThing" name="deleteGroupThing" class="textPurple">
				<c:forEach var="thing" items="${sessionScope.things.listThingGroup}">
					<option> - ${thing.name}</option>
				</c:forEach>
			</select>
		
			<input id="submitDeleteGroupThing" type="submit" value="Delete group thing" class="newButton3D buttonThing buttonPurpleClair" onmouseover="changeCursor('submitDeleteGroupThing')"/>
       	</form>
       	
	</article>
	
	
	
	

 </section >
<script type="text/javascript">
function submitSelect(nameSelect){
	document.getElementById('nameSelect').value = nameSelect;
	document.forms["formGroupThing"].submit();
}
<!--

//-->
</script>
<script src="JS/things.js" type="text/javascript"></script>
