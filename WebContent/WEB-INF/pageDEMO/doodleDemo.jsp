
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 	<section id="sectionNavDoodle">
 		<h2 id="h2navDoodle">Choose your doodle</h2>
		<ul>
			<c:forEach var="doodle" items="${sessionScope.doodles}" varStatus="numDoodle">
			
			<form method="post" action="doodleServlet?action=displayDoodle">
				<li><a> <input id="idDoodle" name="idDoodle" type="submit" value="${doodle.nameDoodle}" onmouseover="changeCursor('idDoodle')"/></a></li>
			</form>
			
			</c:forEach>
		
		</ul>
		
		
		<div  class="aroundWhite">Create new doodle : </div>
		<form method="post" action="doodleServlet?action=createDoodle">
	      <input id="inputNewDoodle" class="textGrey"  name="nameNewDoodle" type="text" value="Name doodle" 
         onfocus="inputTextFocus('inputNewDoodle', 'Green')" onblur="inputTextBlur('inputNewDoodle','Name doodle')" required >
		<input id="createDoodle" type="submit" value="Create" class="newButton3D buttonGreenClair" onmouseover="changeCursor('createDoodle')"/>
       	</form>
	</section>


<section id="sectionDoodle" class="section">
	<h1 id="h1Doodle" class="h1">Doodle</h1> <!-- class="h1 text3Dfonce" -->
	
	<article>
	<h2>Wishes of the group</h2>
		<form method="post" action="doodleServlet?action=saveDoodle">    	
		
			<TABLE id="tab" BORDER="1"> 
			<input type="hidden" id="nbMembers" name="nbMembers" value="${fn:length(sessionScope.group.listMembers)}" >
				<c:set var="numDoodleInList" value="0"></c:set>
				<c:choose>
   					 <c:when test="${requestScope.numDoodleInList != null}">
   					 	
   					 	 <c:set var="numDoodleInList" value="${requestScope.numDoodleInList}"></c:set>
   					 
						<CAPTION>${sessionScope.doodles[numDoodleInList].nameDoodle}</CAPTION> 
    				</c:when>
   				<c:otherwise>
        			<CAPTION>${sessionScope.doodles[numDoodleInList].nameDoodle}</CAPTION>        			
    			</c:otherwise>
				</c:choose>
        		<input type="hidden" id="idDoodleHidden" name="idDoodleHidden" value="${sessionScope.doodles[numDoodleInList].idDoodle}" >
        		<input type="hidden" id="nameDoodleHidden" name="nameDoodleHidden" value="${sessionScope.doodles[numDoodleInList].nameDoodle}" >	
				<input type="hidden" id="nbColumn" name="nbColumn" value="${sessionScope.doodles[numDoodleInList].size}" >
			<THEAD>
				<TR> 
				 <TH> Names </TH> 
				 <c:set var="currentDoodle" value="${sessionScope.doodles[numDoodleInList]}"></c:set>
				 <c:forEach var="colDoodle" items="${currentDoodle.listColDoodle}" varStatus="numCol">
						<TH>
						<input type="hidden" name="${numCol.index+1}idCol" value="${colDoodle.id}" >
						
						<input id="${numCol.index+1}title" name="${numCol.index+1}title" type="hidden" value="${colDoodle.name}" >${colDoodle.name}</TH> 
				</c:forEach>
				 
				 </TR>
			</THEAD>
			
			<TBODY>
			
				<c:forEach var="member" items="${sessionScope.group.listMembers}" varStatus="numLine">
				<input id="${numLine.index+1}idP" name="${numLine.index+1}idP" type="hidden" value="${member.id}"></td>
				
				<TR>
				
				<td>
				
				<input id="${numLine.index+1}name" name="${numLine.index+1}name" class="textRed"  type="text" value="${member.name}"></td>
				
					<c:forEach var="nbCol" begin="1" end="${currentDoodle.size}" varStatus="numCol">
						 <c:set var="currentCol" value="${currentDoodle.listColDoodle[numCol.index-1]}"></c:set>
						<td><input class="roundedTwo" type="checkbox" name="${numCol.index}${numLine.index+1}" id="${numCol.index}${numLine.index+1}" ${currentCol.mapCheckBox[member.id]}></td> 
					</c:forEach>
				
				</TR>
				</c:forEach>
				
			</TBODY>
		</TABLE>
		<input id="saveDoodle" type="submit" value="Save Doodle" class="newButton3D buttonRedFonce right" 
				onmouseover="changeCursor('saveDoodle')"/>
		</form>
		
	</article>
	
	
	
	
	<article id="articleAddColumn" >
	
		<div id="divAddColumn" class="aroundWhite">Add a column : </div>
		
		<div class="line">
			<div class="inline">
				<div id="divDate" class="line">
					<input id="date" type="radio" name="text" value="Du au">  
					From : <input type="text" id="debutC" class="datepick textGreen" onfocus="checkRadio('date')">  
					to : <input type="text" id="finC" class="datepick textGreen" onfocus="checkRadio('date')">
				</div>
				
				
				<div id="divTitle" class="line">
					<input id="text" type="radio" name="text" value="text"> 
					Column title : <input id="textCol" class="textGrey" type="texte"  value="column title" onfocus="checkRadio('text'); inputTextFocus('textCol', 'Green')" onblur="inputTextBlur('textCol','column title')"> 
				</div>
			</div>
			<div class="inline">
				<input id="addColumn" type="submit" value="Add" class="newButton3D buttonGreenClair" onmouseover="changeCursor('addColumn')" onclick="addColumn(); emptyText('textCol', 'debutC','finC');  inputTextBlur('textCol','column title');"/>
			</div>
		</div>
		<!--
		<div id="funcDate" class="line">
			
				<div id="champsDates" class="inline" >
	       	
						<input id="date" type="radio" name="text" value="Du au">  
					 	From : <input type="text" id="debutC" class="datepick" onfocus="checkRadio('date')">  
					 	to : <input type="text" id="finC" class="datepick" onfocus="checkRadio('date')">
					 	</br> 
						<input id="text" type="radio" name="text" value="text"> 
						Column title : <input class="textGrey" type="texte" id="textCol" value="column title" onfocus="checkRadio('text'); inputTextFocus('textCol', 'green')" onblur="inputTextBlur('textCol','column title')"> 
				</div>
				
				<div class="inline" id="butAddCol">
					<div class="button3D b3green" id="addCol" name="addColumn" onmouseover="changeCursor('addCol')" onclick="addColumn(); emptyText('textCol', 'debutC','finC');  inputTextBlur('textCol','titre colonne');"> 
						OK
					</div>
				</div>
			</div>
			
			-->
	</article>
</section>



<style type="text/css">
.ui-datepicker {
	    background: green;
	        border: 5px solid lime;
		border-radius:20px;
		width: 50vw;
		    color: black;
 	font-size:100%;

  margin-left: 5px;
}
</style>


<script src="JS/doodle7.js" type="text/javascript"></script>
<script src="JS/doodleTab.js" type="text/javascript"></script>
