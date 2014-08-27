<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	
<c:if test="${param['page'] == 'homepage'}" >
	<c:set var = "extraCSS" value="
		<link rel='stylesheet' type='text/css' href='CSS/styleHomepage.css' />
	"/>
</c:if>
<c:if test="${param['page'] == 'group'}" >
	<c:set var = "extraCSS" value="
		<link rel='stylesheet' type='text/css' href='CSS/styleGroup.css' />
	"/>
</c:if>

<c:if test="${param['page'] == 'doodle'}" >
	<c:set var = "extraCSS" value="
		<link rel='stylesheet' type='text/css' href='CSS/styleDoodle.css'/> <!--mise en forme-->
		<link rel='stylesheet' type='text/css' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/themes/smoothness/jquery-ui.css' />
		<script src='http://code.jquery.com/jquery-1.9.1.js'></script>
  		<script src='http://code.jquery.com/ui/1.10.2/jquery-ui.js'></script>
  	"/>
</c:if>
<c:if test="${param['page'] == 'meals'}" >
	<c:set var = "extraCSS" value="
		<link rel='stylesheet' type='text/css' href='CSS/styleMeals.css' />
	"/>
</c:if>
<c:if test="${param['page'] == 'expenses'}" >
	<c:set var = "extraCSS" value="
		<link rel='stylesheet' type='text/css' href='CSS/styleExpenses.css' />
	"/>
</c:if>
<c:if test="${param['page'] == 'things'}" >
	<c:set var = "extraCSS" value="
		<link rel='stylesheet' type='text/css' href='CSS/styleThings.css' />
	"/>
</c:if>
<c:if test="${param['page'] == 'hobbies'}" >
	<c:set var = "extraCSS" value="
		<link rel='stylesheet' type='text/css' href='CSS/styleHobbies.css' />
	"/>
</c:if>




<head>
    <title>Holidays !!</title>
    <meta name="description" content="C'est la description de ma page ! apparait dans les recherches google" />
    
    
    <meta charset='utf-8' />
    <link rel="shortcut icon" href="images/tree3.png" />

    <!-- il est important de mettre general au dessus du style propre a la classe comme ca general est moins prioritaire-->
    <link rel="stylesheet" type="text/css" href="CSS/general.css" />
    <link rel="stylesheet" type="text/css" href="CSS/header.css" />
    <link rel="stylesheet" type="text/css" href="CSS/button.css" />
    <link rel="stylesheet" type="text/css" href="CSS/nav.css" />
    ${extraCSS}
    <link rel="stylesheet" type="text/css" href="CSS/active.css" />
    
</head>