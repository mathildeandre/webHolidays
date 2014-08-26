<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<% 
String currentAccueil = "id='liAccueil' onmouseover='changeCursor(\"liAccueil\")' onclick='location.href=\"index.jsp?page=accueil\"'";
String currentGroup = "id='liGroupe' onmouseover='changeCursor(\"liGroupe\")' onclick='location.href=\"index.jsp?page=group\"'";
    if (request.getParameter("page").equals("accueil")) {
    	currentAccueil="class='current'";
    }
    else if (request.getParameter("page").equals("group")) { 
    	currentGroup="class='current'";
	}
%>

<c:set var = "currentAccueil" value="id='liAccueil' onmouseover='changeCursor(\"liAccueil\")' onclick='location.href=\"index.jsp?page=accueil\"'"/>
<c:set var = "currentGroup" value="id='liGroupe' onmouseover='changeCursor(\"liGroupe\")' onclick='location.href=\"index.jsp?page=group\"'"/>
	
	
<c:if test="${param['page'] == 'accueil'}" >
	<c:set var = "currentAccueil" value="class=\"current\""/>
</c:if>
<c:if test="${param['page'] == 'group'}" >
	<c:set var = "currentGroup" value="class=\"current\""/>
</c:if>



<nav>
	<ul>
	<!--  <li <b><c:out value="${currentAccueil}" /></b> >Homepage</li>-->
	 <li ${currentAccueil} >Homepage</li>
	 <li ${currentGroup} >Group</li>
	 
	 
	 <li id="liDoodle" onmouseover="changeCursor('liDoodle')" onclick="location.href='doodle/doodle.html'" >Doodle</li>
	 <li id="liRepas" onmouseover="changeCursor('liRepas')" onclick="location.href='repas/repas.html'">Meals</li>
	 <li id="liCalc" onmouseover="changeCursor('liCalc')" onclick="location.href='calculation/calculation.html?tab=emiel,matthijs,fabian,kiki'">Expenses</li>
	 <li id="liAffaire" onmouseover="changeCursor('liAffaire')" onclick="location.href='affaires/affaire.html'">Things</li>
	 <li id="liLoisirs" onmouseover="changeCursor('liLoisirs')" onclick="location.href='loisirs/loisirs.html'">Hobbies</li>
	</ul>
</nav>
