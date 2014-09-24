<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html onload="changeBackground()">

<!-- Inclut fichiers nécessaires -->

<%@include file="/WEB-INF/include/head.jsp" %>

<!-- Mise en page classique avec le header, le corps, et le footer ! Les balises structurantes de
HTML5 sont employées. -->
<body id="bodyIndex" >

<%@include file="/WEB-INF/include/header.jsp" %>

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
%>

<%@include file="/WEB-INF/include/footer.jsp" %>
    
<script src="JS/actionJS/navChanging.js" type="text/javascript"></script>
<script src="JS/actionJS/actionInputText.js" type="text/javascript"></script>

</body>
</html>