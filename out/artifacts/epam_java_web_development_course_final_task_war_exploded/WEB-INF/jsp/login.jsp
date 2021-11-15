<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<html style="font-size: 16px;">
<head>
	<title>Log In</title>
	<link rel="stylesheet" href="../../css/Log-in.css" media="screen">
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
			<div class="wrapper" style="background-image: url('images/bg-registration-form-2.jpg');">
				<div class="inner">
					<form action="${pageContext.request.contextPath}/command?name=login" method="post">
						<h3 class="text-center">Log-in</h3>
						<div class="form-wrapper">
							<label for="">Email</label>
							<input type="text" name="email" class="form-control">
							<c:if test="${emailState}">
								<label class="text-warning"
								       style="font-size: xx-small">${emailDesc}</label>
							</c:if>
						</div>
						<div class="form-wrapper">
							<label for="">Password</label>
							<input type="password" name="password" class="form-control">
							<c:if test="${passwordState}">
								<label class="text-warning"
								       style="font-size: xx-small">${passwordDesc}</label>
							</c:if>
						</div>
						<br>
						<button type="submit">Log-in</button>
						<c:if test="${userIsMissing}">
							<br>
							<label class="text-warning"
							       style="font-size: medium">${userMissingMessage}</label>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<c:import url="common/footer.jsp"/>
<c:import url="common/cookies.jsp"/>
</body>
</html>