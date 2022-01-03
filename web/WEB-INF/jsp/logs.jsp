<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.language}" scope="session"/>
<f:setBundle basename="local.local" var="local"/>

<html style="font-size: 16px;">
<head>
	<title>Home</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Home.css" media="screen">
	<meta property="og:title" content="Home">
	<c:import url="common/head.jsp"/>
</head>
<body data-home-page="Home.html" data-home-page-title="Home" class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-image u-section-1" id="carousel_b7ce" data-image-width="1280" data-image-height="839">
	<div class="u-align-left u-clearfix u-sheet u-sheet-1">
		<pre class="u-text-my">${pageContext.request.getAttribute("fileContent")}
		</pre>
	</div>
	<style>
		.u-text-my {
			overflow-x: auto;
			white-space: pre-wrap;
			white-space: -moz-pre-wrap;
			white-space: -pre-wrap;
			white-space: -o-pre-wrap;
			word-wrap: break-word;
		}
	</style>
</section>
<c:import url="common/footer.jsp"/>
<%--<c:import url="common/cookies.jsp"/>--%>
</body>
</html>