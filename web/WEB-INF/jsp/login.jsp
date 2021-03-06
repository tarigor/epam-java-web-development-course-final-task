<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="html" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>

<f:setLocale value="${sessionScope.language}" scope="session"/>
<f:setBundle basename="local.local" var="local"/>

<html style="font-size: 16px;">
<head>
	<title>Log In</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Log-in.css" media="screen">
	<meta property="og:title" content="Log In">
	<c:import url="common/head.jsp"/>
</head>
<body class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-image u-section-1" id="sec-3a70" data-image-width="1280" data-image-height="839">
	<div class="u-clearfix u-sheet u-sheet-1">
		
		<div class="u-clearfix u-custom-html u-grey-60 u-opacity u-opacity-70 u-custom-html-1">
			<meta charset="utf-8">
			<title></title>
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
			<link rel="stylesheet" href="css/style.css">
			<br>
			<div class="wrapper" style="background-image: url('images/bg-registration-form-2.jpg');">
				<div class="inner">
					<form action="${pageContext.request.contextPath}/command?name=login" method="post">
						<c:if test="${loginAndCompleteRequest}">
							<input type="hidden" name="loginAndCompleteRequest" value="${loginAndCompleteRequest}">
							<input type="hidden" name="persons" value="${clientRequest.getPersons()}">
							<input type="hidden" name="roomClass" value="${clientRequest.getRoomClass()}">
							<input type="hidden" name="dateFrom" value="${clientRequest.getDateFrom()}">
							<input type="hidden" name="dateTo" value="${clientRequest.getDateTo()}">
						</c:if>
						<h3 class="text-center"><f:message key="login.login" bundle="${local}"/></h3>
						<c:if test="${registrationCompleted}">
							<label style="text-align: center;font-size: medium;color: #62C584"><f:message
									key="login.registration.completed"
									bundle="${local}"/></label>
						</c:if>
						<div class="form-wrapper">
							<label for=""><f:message key="email" bundle="${local}"/></label>
							<input type="text" name="email" class="form-control">
							<c:if test="${emailState}">
								<label class="text-warning"
								       style="font-size: xx-small">${emailDesc}</label>
							</c:if>
						</div>
						<div class="form-wrapper">
							<label for=""><f:message key="password" bundle="${local}"/></label>
							<input type="password" name="password" class="form-control">
							<c:if test="${passwordState}">
								<label class="text-warning"
								       style="font-size: xx-small">${passwordDesc}</label>
							</c:if>
						</div>
						<br>
						<button type="submit">
							<c:choose>
								<c:when test="${loginAndCompleteRequest}">
									<f:message key="login.login.and.booking" bundle="${local}"/>
								</c:when>
								<c:otherwise>
									<f:message key="login.login" bundle="${local}"/>
								</c:otherwise>
							</c:choose>
						</button>
						
						<c:if test="${userIsMissing}">
							<br>
							<label class="text-warning"
							       style="font-size: medium"><f:message key="${errorType}" bundle="${local}"/></label>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<c:import url="common/footer.jsp"/>
<%--<c:import url="common/cookies.jsp"/>--%>
</body>
</html>