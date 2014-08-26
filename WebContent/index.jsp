<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html onload="changeBackground()">
<!-- Inclut fichiers nécessaires -->
<%@include file="/WEB-INF/include/head.jsp" %>

<!-- Mise en page classique avec le header, le corps, et le footer ! Les balises structurantes de
HTML5 sont employées. -->
<body id="imgHeaderNav" >

<%@include file="/WEB-INF/include/header.jsp" %>

<%@include file="/WEB-INF/include/navbar.jsp" %>


<% 
    if (request.getParameter("page").equals("accueil")) { %>
        <%@include file="/WEB-INF/page/accueil.jsp"%>
<%
    }
    else if (request.getParameter("page").equals("group")) { %>
		<%@include file="/WEB-INF/page/groupe.jsp"%>
<%
}
%>

<%@include file="/WEB-INF/include/footer.jsp" %>

</body>
</html>