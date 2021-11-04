<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html style="font-size: 16px;">
<head>
	<title>Client Cabinet</title>
	<link rel="stylesheet" href="../../css/Client-Cabinet.css" media="screen">
	<meta property="og:title" content="Client Cabinet">
	<c:import url="common/head.jsp"/>
</head>
<body class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-grey-10 u-section-1" id="sec-b41e">
	<div class="u-align-left u-clearfix u-sheet u-sheet-1">
		<h3 class="u-align-center u-custom-font u-font-georgia u-text u-text-default u-text-1">Client Cabinet</h3>
		<div class="u-clearfix u-custom-html u-expanded-width u-custom-html-1">
			<div class="container">
				<div class="row">
					<div class="col-15">
						<table class="table table-bordered">
							<thead>
							<tr class="table-secondary text-center">
								<th scope="col"></th>
								<th scope="col">Booking id</th>
								<th scope="col">Client Name</th>
								<th scope="col">Room Number</th>
								<th scope="col">Room Type</th>
								<th scope="col">Persons Amount</th>
								<th scope="col">Start Date</th>
								<th scope="col">End Date</th>
								<th scope="col">Status Of Booking</th>
							</tr>
							</thead>
							<tbody>
							<tr class="table-light text-center">
								<td>
									<div class="custom-checkbox custom-control">
										<input type="checkbox" class="custom-control-input" id="customCheck1"
										       checked="">
										<label class="custom-control-label" for="customCheck1"></label>
									</div>
								</td>
								<th scope="col">1</th>
								<th scope="col">Igor Taren</th>
								<th scope="col">1</th>
								<th scope="col">Single</th>
								<th scope="col">1</th>
								<th scope="col">2021-10-01</th>
								<th scope="col">2021-10-10</th>
								<th scope="col">requested</th>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<c:import url="common/footer.jsp"/>
<c:import url="common/cookies.jsp"/>
</body>
</html>