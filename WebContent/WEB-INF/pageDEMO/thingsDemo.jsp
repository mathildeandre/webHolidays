

 <section id="sectionThings" class="section line">
   <h1 id="h1Things" class="h1">Things you need</h1> <!-- class="h1 text3Dfonce" -->
	
	<article id="articlePersonalThings" class="inline">
	  	<fieldset> 
	    <legend><h2>Personals things</h2></legend>
   		<div id="fieldPersonalThings" class="aroundWhite">
   		
   			<div> - sleeping bag</div>
   			<div> - sunscreen</div>
   			<div> - swimsuit</div>
   			<div> - ..</div>
			
			
		</div>
		</fieldset>
		
		</br>		
		</br>		
		</br>
		
		<div  class="aroundWhite">Add personals things : </div>
       	<form method="post" action="demoServlet?action=submit&page=thingsDemo">
	      	<input id="inputPersonalThing" name="inputPersonalThing" class="textGrey" type="text" value="Thing" 
         		onfocus="inputTextFocus('inputPersonalThing', 'Purple')" 
         		onblur="inputTextBlur('inputPersonalThing','Thing')" required >
         		
			<input id="addPersonalThing" type="submit" class="newButton3D buttonThing buttonPurpleClair" 
				onmouseover="changeCursor('addPersonalThing')" value="Add Thing"
				onclick="alert('${sessionScope.displayErrorThings}\n${sessionScope.displayErrorDemo}')" />
       	</form>
       	
	</article>
	
	<article id="articleGroupThings" class="inline">
	  	<fieldset> 
	    <legend><h2>Group things</h2></legend>
   		<div id="fieldGroupThings" class="aroundWhite">
   			<div> - car 1 <select >
						<option >nobody</option> 
						<c:forEach var="member" items="${sessionScope.group.listMembers}" varStatus="i">
							<c:set var="selected" value=""></c:set>
							<c:if test="${i.index == 2}">
								<c:set var="selected" value="selected"></c:set>
							</c:if>
		    				<option ${selected}> ${member.name}</option> 
		    			</c:forEach>
	    	
					</select>
			</div>
			<div> - car 2 <select >
						<option >nobody</option> 
						<c:forEach var="member" items="${sessionScope.group.listMembers}" varStatus="i">
							<c:set var="selected" value=""></c:set>
							<c:if test="${i.index == 3}">
								<c:set var="selected" value="selected"></c:set>
							</c:if>
		    				<option ${selected}> ${member.name}</option> 
		    			</c:forEach>
	    	
					</select>
			</div>
			<div> - climbing rope <select >
						<option >nobody</option> 
						<c:forEach var="member" items="${sessionScope.group.listMembers}" varStatus="i">
							<c:set var="selected" value=""></c:set>
							<c:if test="${i.index == 4}">
								<c:set var="selected" value="selected"></c:set>
							</c:if>
		    				<option ${selected}> ${member.name}</option> 
		    			</c:forEach>
	    	
					</select>
			</div>
			<div> - ball <select >
						<option >nobody</option> 
						<c:forEach var="member" items="${sessionScope.group.listMembers}" varStatus="i">
							<c:set var="selected" value=""></c:set>
							<c:if test="${i.index == 2}">
								<c:set var="selected" value="selected"></c:set>
							</c:if>
		    				<option ${selected}> ${member.name}</option> 
		    			</c:forEach>
	    	
					</select>
			</div>
			
   			
		</div>
		</fieldset>
		
		</br>		
		</br>		
		</br>
		
		<div  class="aroundWhite">Add group things : </div>
       	<form method="post" action="demoServlet?action=submit&page=thingsDemo">
	      	<input id="inputGroupThing" class="textGrey"  name="inputGroupThing" type="text" value="Thing" 
         		onfocus="inputTextFocus('inputGroupThing', 'Purple')" 
         		onblur="inputTextBlur('inputGroupThing','Thing')" required >
			<input id="addGroupThing" type="submit" class="newButton3D buttonThing buttonPurpleClair" 
				onmouseover="changeCursor('addGroupThing')" value="Add Thing"
				onclick="alert('${sessionScope.displayErrorThings}\n${sessionScope.displayErrorDemo}')"/>
       	</form>
       	
	</article>
	
	
	
	

 </section >

<script src="JS/things.js" type="text/javascript"></script>
