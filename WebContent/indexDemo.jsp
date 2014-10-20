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
    <link rel="stylesheet" type="text/css" href="CSS/all15.css" />
    <script src='http://code.jquery.com/jquery-1.9.1.js'></script>
  	<script src='http://code.jquery.com/ui/1.10.2/jquery-ui.js'></script>
    <!-- <link rel="stylesheet" type="text/css" href="CSS/active.css"  />-->
    
</head>



<!-- Mise en page classique avec le header, le corps, et le footer ! Les balises structurantes de
HTML5 sont employées. -->
<body id="bodyIndex" onload="alert('BABAR')">

<header id="headerIndex" class="line" >
	<div id="titleIndex" class="inline text3Dfonce">
 	Organisation of holidays
	</div>
	
	<div id="connexion" class="line inline right"> 
		
			<div id="nameGroup" class="inline text3Dfonce">GroupDemo</div>			
			
			
			<form method="post" action="logout">
			<input id="deconnexion" type="submit" value="Log out" class="inline newButton3Dcreux" onmouseover="changeCursor('deconnexion')"  />
       		</form>
	</div>

</header>

<%@include file="/WEB-INF/include/navbarDemo.jsp" %>


<% 
    if (request.getParameter("page").equals("homepageDemo")) { %>
        <%@include file="/WEB-INF/pageDEMO/homepageDemo.jsp"%>
<%
    }
    else if (request.getParameter("page").equals("groupDemo")) { %>
		<%@include file="/WEB-INF/pageDEMO/groupDemo.jsp"%>
<%
	}
    else if (request.getParameter("page").equals("doodleDemo")) { %>
		<%@include file="/WEB-INF/pageDEMO/doodleDemo.jsp"%>
<%
	}
    else if (request.getParameter("page").equals("mealsDemo")) { %>
	<%@include file="/WEB-INF/pageDEMO/mealsDemo.jsp"%>
<%
	}
    else if (request.getParameter("page").equals("expensesDemo")) { %>
	<%@include file="/WEB-INF/pageDEMO/expensesDemo.jsp"%>
<%
	}
    else if (request.getParameter("page").equals("thingsDemo")) { %>
	<%@include file="/WEB-INF/pageDEMO/thingsDemo.jsp"%>
<%
	}
    else if (request.getParameter("page").equals("hobbiesDemo")) { %>
	<%@include file="/WEB-INF/pageDEMO/hobbiesDemo.jsp"%>
<%
	}
%>

<footer id="footerIndex">Copyright fs - Tous droits réservés<br/> Contact : fabiensauce@orange.fr  mathildeandre@orange.fr</footer>

    
<script src="JS/actionJS/navChanging.js" type="text/javascript"></script>
<script src="JS/actionJS/actionInputText1.js" type="text/javascript"></script>

</body>
</html>