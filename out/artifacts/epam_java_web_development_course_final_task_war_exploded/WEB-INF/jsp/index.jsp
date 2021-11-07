<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html style="font-size: 16px;">
<head>
	<title>Home</title>
	<link rel="stylesheet" href="../../css/Home.css" media="screen">
	<meta property="og:title" content="Home">
	<c:import url="common/head.jsp"/>
</head>
<body data-home-page="Home.html" data-home-page-title="Home" class="u-body">
<c:import url="common/menu.jsp"/>
<%--<section class="u-clearfix u-image u-section-1" id="carousel_b7ce" data-image-width="1280" data-image-height="839">--%>
<%--	<div class="u-align-left u-clearfix u-sheet u-sheet-1">--%>
<%--		<h6 class="u-custom-font u-font-georgia u-text u-text-body-alt-color u-text-default u-text-1">Hello Guest and--%>
<%--			welcome to our Hotel</h6>--%>
<%--		<div class="u-align-center u-clearfix u-custom-html u-custom-html-1">--%>
<%--			<label for="from">FROM</label>--%>
<%--			<input type="text" id="from" name="from">--%>
<%--		</div>--%>
<%--		<div class="u-align-center u-clearfix u-custom-html u-custom-html-2">--%>
<%--			<label for="from">TO</label>--%>
<%--			<input type="text" id="to" name="to">--%>
<%--		</div>--%>
<%--		<a href="https://nicepage.com/k/children-website-templates"--%>
<%--		   class="u-border-2 u-border-white u-btn u-button-style u-hover-grey-50 u-none u-text-body-alt-color u-text-hover-white u-btn-1">--%>
<%--			CHECK AVAILABILITY</a>--%>
<%--	</div>--%>
<%--</section>--%>
<section class="u-clearfix u-image u-section-1" id="carousel_b7ce" data-image-width="1280" data-image-height="839">
	<div class="u-align-left u-clearfix u-sheet u-sheet-1">
		<h6 class="u-custom-font u-font-georgia u-text u-text-body-alt-color u-text-default u-text-1">Hello Guest and welcome to our Hotel</h6>
		<div class="u-align-center u-clearfix u-custom-html u-expanded-width u-custom-html-1">
			<input type="text" name="datefilter" value="">
		</div>
		<a href="https://nicepage.com/k/children-website-templates" class="u-border-2 u-border-white u-btn u-button-style u-hover-grey-50 u-none u-text-body-alt-color u-text-hover-white u-btn-1"> CHECK AVAILABILITY</a>
	</div>
</section>
<c:import url="common/footer.jsp"/>
<c:import url="common/cookies.jsp"/>
</body>
</html>