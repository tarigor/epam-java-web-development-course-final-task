<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html style="font-size: 16px;">
<head>
	<title>Sign Up</title>
	<link rel="stylesheet" href="../../css/Sign-up.css" media="screen">
	<meta property="og:title" content="Sign Up">
	<c:import url="common/head.jsp"/>
</head>
<body class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-image u-section-1" id="sec-3a70" data-image-width="1280" data-image-height="839">
	<div class="u-clearfix u-sheet u-sheet-1">
		<div class="u-clearfix u-custom-html u-grey-60 u-opacity u-opacity-70 u-custom-html-1">
			<meta charset="utf-8">
			<title></title>
			<meta name="viewport" content="width=device-width, initial-scale=1.0"><!-- MATERIAL DESIGN ICONIC FONT -->
			<link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
			<!-- STYLE CSS -->
			<link rel="stylesheet" href="css/style.css">
			<div class="wrapper" style="background-image: url('images/bg-registration-form-2.jpg');">
				<div class="inner">
					<form action="">
						<h3>Registration Form</h3>
						<div class="form-group">
							<div class="form-wrapper">
								<label for="">First Name</label>
								<input type="text" class="form-control">
							</div>
							<div class="form-wrapper">
								<label for="">Last Name</label>
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="form-wrapper">
							<label for="">Email</label>
							<input type="text" class="form-control">
						</div>
						<div class="form-wrapper">
							<label for="">Password</label>
							<input type="password" class="form-control">
						</div>
						<div class="form-wrapper">
							<label for="">Confirm Password</label>
							<input type="password" class="form-control">
						</div>
						<br>
						<button>Register Now</button>
						<input type="hidden" value="" name="recaptchaResponse">
					</form>
				</div>
			</div><!-- This templates was made by Colorlib (https://colorlib.com) -->
		</div>
	</div>
</section>
<c:import url="common/footer.jsp"/>
<c:import url="common/cookies.jsp"/>
</body>
</html>