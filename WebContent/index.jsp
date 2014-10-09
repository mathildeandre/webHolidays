<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html onload="changeBackground()">

<!-- Inclut fichiers nécessaires -->



<head>
    <title>EasyHolidays !!</title>
    <meta name="description" content="C'est la description de ma page ! apparait dans les recherches google" />
    
    
    <meta charset='utf-8' />
    <link rel="shortcut icon" href="images/tree3.png" />
<link rel='stylesheet' type='text/css' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/themes/smoothness/jquery-ui.css' />
    <!-- il est important de mettre general au dessus du style propre a la classe comme ca general est moins prioritaire-->
    <link rel="stylesheet" type="text/css" href="CSS/all9.css" />
    <!-- <link rel="stylesheet" type="text/css" href="CSS/active.css"  />-->
    
</head>



<!-- Mise en page classique avec le header, le corps, et le footer ! Les balises structurantes de
HTML5 sont employées. -->
<body id="bodyIndex">

<header id="headerIndex" class="line">
	<div id="titleIndex" class="inline text3Dfonce">
 	Organisation of holidays
	</div>
	
	<div id="connexion" class="line inline right"> 
		
		<div id="nameGroup" class="inline text3Dfonce">NameGroup${requestScope.nameGroup}</div>
			<form method="post" action="GotoPersoArea">
			<input id="persoArea" type="submit" value="Personal Area" class="inline newButton3Dcreux" onmouseover="changeCursor('persoArea')"  />
			</form>
			<form method="post" action="D">
			<input id="deconnexion" type="submit" value="Deconnexion" class="inline newButton3Dcreux" onmouseover="changeCursor('deconnexion')"  />
       		</form>
	</div>

</header>

<%@include file="/WEB-INF/include/navbar.jsp" %>


<% 
    if (request.getParameter("page").equals("homepage")) { %>
        <%@include file="/WEB-INF/page/homepage.jsp"%>
<%
    }
    else if (request.getParameter("page").equals("group")) { %>
		<%@include file="/WEB-INF/page/group.jsp"%>
<%
	}
    else if (request.getParameter("page").equals("doodle")) { %>
		<%@include file="/WEB-INF/page/doodle.jsp"%>
        <script>changeBackground("doodle"); </script>
<%
	}
    else if (request.getParameter("page").equals("meals")) { %>
	<%@include file="/WEB-INF/page/meals.jsp"%>
<%
	}
    else if (request.getParameter("page").equals("expenses")) { %>
	<%@include file="/WEB-INF/page/expenses.jsp"%>
<%
	}
    else if (request.getParameter("page").equals("things")) { %>
	<%@include file="/WEB-INF/page/things.jsp"%>
<%
	}
    else if (request.getParameter("page").equals("hobbies")) { %>
	<%@include file="/WEB-INF/page/hobbies.jsp"%>
<%
	}
    else if (request.getParameter("page").equals("perso")) { %>
	<jsp:forward page='/registration'/>
<%
	}
%>

<footer id="footerIndex">Copyright fs - Tous droits réservés<br/> Contact : fabiensauce@orange.fr  mathildeandre@orange.fr</footer>

    
<script src="JS/actionJS/navChanging.js" type="text/javascript"></script>
<script src="JS/actionJS/actionInputText.js" type="text/javascript"></script>

</body>
</html>