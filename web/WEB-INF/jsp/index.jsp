<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.language}" scope="session"/>
<f:setBundle basename="local.menu" var="local"/>

<html style="font-size: 16px;">
<head>
	<title>Home</title>
	<link rel="stylesheet" href="../../css/Home.css" media="screen">
	<meta property="og:title" content="Home">
	<c:import url="common/head.jsp"/>
</head>
<body data-home-page="Home.html" data-home-page-title="Home" class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-image u-section-1" id="carousel_b7ce" data-image-width="1280" data-image-height="839">
	<div class="u-align-left u-clearfix u-sheet u-sheet-1">
		<h4 class="u-custom-font u-font-georgia u-text u-text-body-alt-color u-text-default u-text-1">
			<f:message key="index.hello.guest.part1" bundle="${local}"/>
			<c:if test="${user!=null}">
				${user.getFirstName()}
			</c:if>
			<c:if test="${user==null}">
				<f:message key="index.guest" bundle="${local}"/>
			</c:if>
			<f:message key="index.hello.guest.part2" bundle="${local}"/>
		</h4>
		<form action="command?name=check_free_room" method="post">
			<div class="u-align-center u-clearfix u-custom-html u-expanded-width u-custom-html-1">
				<input type="text" name="datefilter" value="">
			</div>
			<button type="submit"
			        class="u-border-2 u-border-white u-btn u-button-style u-hover-grey-50 u-none u-text-body-alt-color u-text-hover-white u-btn-1">
				<f:message key="index.check.availability" bundle="${local}"/>
			</button>
		</form>
	</div>
</section>
<c:import url="common/footer.jsp"/>
<%--<c:import url="common/cookies.jsp"/>--%>
</body>
</html>