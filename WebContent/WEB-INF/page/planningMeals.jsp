
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<section id="sectionPlanning" class="section line">

<article id="formatPlanning">
<h2 id="h2FormatPlanning">Create your planning</h2>

<form method="post" action="planning?action=createPlanning">
	<div class="line">
		<div id="divDate" class="inline">
			From : <input type="text" id="chooseStartDate" name="chooseStartDate" class="datepick textGreen">  
			to : <input type="text" id="chooseSendDate" name="chooseEndDate" class="datepick textGreen" >
		</div>
		<div class="inline">
		<input id="createPlanning" type="submit" value="Create" class="newButton3D buttonGreenClair" onmouseover="changeCursor('createPlanning')" />
		</div>
	</div>
</form>

		
</article>		

<c:if test="${not empty requestScope.planningCreated}">
	<h1> My planning </h1>
 
 <article id="sectionReceipe" class="inline">
 	<div id="navRepas">
	<ul>
	 <li id="starter" ><a href="index.jsp?page=starter">Starter</a></li>
	 <li id="course" ><a href="index.jsp?page=courses">Course</a></li>
	 <li id="dessert" ><a href="dessert.html">Dessert</a></li>
	</ul>
 	</div>
 	<div id="receipe">
 	

 		
	 <div id="dragBurger" draggable="true" ondragstart="drag(event)"> Burger </div> 
	 
	 <div id="dragLasagna" draggable="true" ondragstart="drag(event)"> Lasagna </div> 
	 
	 <div id="dragPasta" draggable="true" ondragstart="drag(event)"> Pasta </div> 
	 
 	
 	</div>
 
</article>
 
 <article id="myPlanning" class="inline">
 	<div id="myPlanningTitle">
	
	<input id="startDate" name="startDate" type="hidden"  value="${requestScope.startDate}">  From  <span class="date"> ${requestScope.startDate} </span>
	<input id="endDate" name="endDate" type="hidden" value="${requestScope.endDate}">  to <span class="date"> ${requestScope.endDate} </span>
	 
	 </div>
	 
	 <div id="myPlanningContent">
	 
		<div id="dayPlanning" class="line">
	 		<div class="inline">
				<input id="previousDayButton" type="submit" value="previous"  onmouseover="changeCursor('previousDayButton')" onclick="previousDayFunc()"/>
			</div>
		
			<div class="inline">
				<h2 id="actualDay">${requestScope.startDay} ${requestScope.startDate}</h2>
			</div>
			<div class="inline">
				<input id="nextDayButton" type="submit" value="next"  onmouseover="changeCursor('nextDayButton')" onclick="nextDayFunc()"/>
			</div>
		</div>
	 
	

		<div id="lunch"> 
		<h2> Lunch </h2>
		<ul>
	 		<li id="starterLunch">Starter <div id="starterLunchDrop_${requestScope.startDay} ${requestScope.startDate}" class="inline dropZone" ondrop="drop(event)" ondragover="allowDrop(event)"></div></li>
	 		<li id="courseLunch" >Course <div id="courseLunchDrop_${requestScope.startDay} ${requestScope.startDate}" class="inline dropZone" ondrop="drop(event)" ondragover="allowDrop(event)"></div></li>
	 		<li id="dessertLunch"> Dessert <div id="dessertLunchDrop_${requestScope.startDay} ${requestScope.startDate}" class="inline dropZone" ondrop="drop(event)" ondragover="allowDrop(event)"></div></li>
		</ul>
		</div>


		<div id="dinner"> 
		<h2> Dinner </h2>
		<ul>
	 		<li id="starter">Starter <div id="starterDinnerDrop_${requestScope.startDay} ${requestScope.startDate}" class="inline dropZone" ondrop="drop(event)" ondragover="allowDrop(event)"></div></li>
	 		<li id="course" >Course <div id="courseDinnerDrop_${requestScope.startDay} ${requestScope.startDate}" class="inline dropZone" ondrop="drop(event)" ondragover="allowDrop(event)"></div></li>
	 		<li id="dessert"> Dessert <div id="dessertDinnerDrop_${requestScope.startDay} ${requestScope.startDate}" class="inline dropZone" ondrop="drop(event)" ondragover="allowDrop(event)"></div></li>
		</ul>
		</div>
 
 	</div>
 	
	</article>
</c:if>		
	
	</section>
	
<style type="text/css">
.ui-datepicker {
	    background: green;
	    border: 5px solid lime;
		border-radius:20px;
		width: 30vw;
		color: black;
 	    font-size:01vw;
        margin-left: 5px;
}
</style>

<script src="../actionJS/navChanging.js" type="text/javascript"></script> 
<script src="JS/planningMeals.js" type="text/javascript"></script>


</body>
