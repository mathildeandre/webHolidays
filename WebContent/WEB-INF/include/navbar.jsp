<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var = "currentHomepage" value="id='liHomepage' onmouseover='changeCursor(\"liHomepage\")' onclick='location.href=\"index.jsp?page=homepage\"'"/>
<c:set var = "currentGroup" value="id='liGroupe' onmouseover='changeCursor(\"liGroupe\")' onclick='location.href=\"index.jsp?page=group\"'"/>
<c:set var = "currentDoodle" value="id='liDoodle' onmouseover='changeCursor(\"liDoodle\")' onclick='location.href=\"index.jsp?page=doodle\"'"/>
<c:set var = "currentMeals" value="id='liMeals' onmouseover='changeCursor(\"liMeals\")' onclick='location.href=\"index.jsp?page=meals\"'"/>
<c:set var = "currentExpenses" value="id='liExpenses' onmouseover='changeCursor(\"liExpenses\")' onclick='location.href=\"index.jsp?page=expenses\"'"/>
<c:set var = "currentThings" value="id='liThings' onmouseover='changeCursor(\"liThings\")' onclick='location.href=\"index.jsp?page=things\"'"/>
<c:set var = "currentHobbies" value="id='liHobbies' onmouseover='changeCursor(\"liHobbies\")' onclick='location.href=\"index.jsp?page=hobbies\"'"/>
	
	
<c:if test="${param['page'] == 'homepage'}" >
	<c:set var = "currentHomepage" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'group'}" >
	<c:set var = "currentGroup" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'doodle'}" >
	<c:set var = "currentDoodle" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'meals'}" >
	<c:set var = "currentMeals" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'expenses'}" >
	<c:set var = "currentExpenses" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'things'}" >
	<c:set var = "currentThings" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'hobbies'}" >
	<c:set var = "currentHobbies" value="class=\"current\""/>
</c:if>



<nav>
	<ul>
	<!--  <li <b><c:out value="${currentAccueil}" /></b> >Homepage</li>-->
	 <li ${currentHomepage} >Homepage</li>
	 <li ${currentGroup} >Group</li>
	 <li ${currentDoodle} >Doodle</li>
	 <li ${currentMeals} >Meals</li>
	 <li ${currentExpenses} >Expenses</li>
	 <li ${currentThings} >Things</li>
	 <li ${currentHobbies} >Hobbies</li>
	</ul>
</nav>
