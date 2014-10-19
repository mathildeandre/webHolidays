
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<style>
#div1 {width:60px;height:20px;padding:10px;border:1px solid red;}

#div2 {width:60px;height:10px;padding:10px;border:1px solid red;}
</style>

<section id="sectionPlanning" class="section line">

<article id="formatPlanning">
<h2 id="h2FormatPlanning">Create your planning</h2>

<form method="post" action="planning?action=createPlanning">
	<div class="line">
		<div id="divDate" class="inline">
			From : <input type="text" id="startDate" name="startDate" class="datepick textGreen">  
			to : <input type="text" id="finC" name="endDate" class="datepick textGreen" >
		</div>
		<div class="inline">
		<input id="createPlanning" type="submit" value="Create" class="newButton3D buttonGreenClair" onmouseover="changeCursor('createPlanning')"
		/>
		</div>
	</div>
</form>

	
		
</article>		

<c:if test="${not empty requestScope.planningCreated}">
	<h1> My planning </h1>
 
 <article id="navRepas" class="inline">
	<ul>
	 <li id="starter" ><a href="index.jsp?page=starter">Starter</a></li>
	 <li id="course" ><a href="index.jsp?page=courses">Course</a></li>
	 <li id="dessert" ><a href="dessert.html">Dessert</a></li>
	</ul>
 
 	<div id="receipe">
 	

 		
	 <div id="drag1" draggable="true" ondragstart="drag(event)"> Burger </div> 
	 
	 <div id="drag2" draggable="true" ondragstart="drag(event)"> Lasagna </div> 
	 
	 <div id="drag3" draggable="true" ondragstart="drag(event)"> Pasta </div> 
	 
 	
 	</div>
 
</article>
 
 <article id="myPlanning" class="inline">
	<h1> create your planning</br>
	
	 From : <input id="debutC" name="startDate" type="hidden" > ${requestScope.startDate}
	 To : <input id="debutC" name="startDate" type="hidden" > ${requestScope.endDate}
	 
	 <div class="line">
	 <div class="inline">
		<input id="previousDay" type="submit" value="previous" class="newButton3D buttonGreenClair" onmouseover="changeCursor('previousDay')" onclick="previousDay()"/>
	</div>
	<div id="actualDay" class="inline">
			lundi 20/09
	</div>
	<div class="inline">
		<input id="nextDay" type="submit" value="next" class="newButton3D buttonGreenClair" onmouseover="changeCursor('nextDay')" onclick="nextDayFunc()"/>
	</div>
	</div>
	 
	

	<div id="lunch"> Lunch 

		<ul>
	 		<li id="starter">Starter <div id="div1" class="inline" ondrop="drop(event)" ondragover="allowDrop(event)"></div></li>
	 		<li id="course" >Course <div id="div2" class="inline" ondrop="drop(event)" ondragover="allowDrop(event)"></div></li>
	 		<li id="dessert"> Dessert <div id="div2" class="inline" ondrop="drop(event)" ondragover="allowDrop(event)"></div></li>
		</ul>
	</div>


<div id="dinner"> Dinner</div>
 
 	
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
