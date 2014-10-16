<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var = "currentHomepage" value="id='liHomepage' onmouseover='changeCursor(\"liHomepage\")' 
onclick='location.href=\"indexDemo.jsp?page=homepageDemo\"'" />
<c:set var = "currentGroup" value="id='liGroupe' onmouseover='changeCursor(\"liGroupe\")' 
onclick='location.href=\"indexDemo.jsp?page=groupDemo\"'" />
<c:set var = "currentDoodle" value="id='liDoodle' onmouseover='changeCursor(\"liDoodle\")' 
onclick='location.href=\"indexDemo.jsp?page=doodleDemo\"'; 'alert(\"ceci nest pas un onlaod\")' " />
<c:set var = "currentMeals" value="id='liMeals' onmouseover='changeCursor(\"liMeals\")' 
onclick='location.href=\"indexDemo.jsp?page=homepageDemo\"'" />
<c:set var = "currentExpenses" value="id='liExpenses' onmouseover='changeCursor(\"liExpenses\")' 
onclick='location.href=\"indexDemo.jsp?page=expensesDemo\"'" />
<c:set var = "currentThings" value="id='liThings' onmouseover='changeCursor(\"liThings\")' 
onclick='location.href=\"indexDemo.jsp?page=thingsDemo\"'" />
<c:set var = "currentHobbies" value="id='liHobbies' onmouseover='changeCursor(\"liHobbies\")' 
onclick='location.href=\"indexDemo.jsp?page=hobbiesDemo\"'" />
	
	
<c:if test="${param['page'] == 'homepageDemo'}" >
	<c:set var = "currentHomepage" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'groupDemo'}" >
	<c:set var = "currentGroup" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'doodleDemo'}" >
	<c:set var = "currentDoodle" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'mealsDemo'}" >
	<c:set var = "currentMeals" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'expensesDemo'}" >
	<c:set var = "currentExpenses" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'thingsDemo'}" >
	<c:set var = "currentThings" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'hobbiesDemo'}" >
	<c:set var = "currentHobbies" value="class=\"current\""/>
</c:if>



<nav id="mainNav">
	<ul>
	<!--  <li <b><c:out value="${currentAccueil}" /></b> >Homepage</li>-->
	 <li ${currentHomepage} >Homepage</li>
	 <li ${currentGroup} >Members</li>
	 <li ${currentDoodle} >Doodle</li>
	 <li ${currentMeals} >Meals</li>
	 <li ${currentExpenses}  >Expenses</li>
	 <li ${currentThings} >Things</li>
	 <li ${currentHobbies} >Hobbies</li>
	</ul>
</nav>
