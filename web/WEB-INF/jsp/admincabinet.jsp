<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.language}" scope="session"/>
<f:setBundle basename="local.menu" var="local"/>
<html style="font-size: 16px;">
<head>
	<title>Admin Cabinet</title>
	<link rel="stylesheet" href="../../css/Admin-Cabinet.css" media="screen">
	<meta property="og:title" content="Admin Cabinet">
	<c:import url="common/head.jsp"/>
</head>
<body class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-image u-section-1" id="sec-b41e" data-image-width="1280" data-image-height="839">
	<div class="u-align-left u-clearfix u-sheet u-sheet-1">
		<br>
		<h3 class="u-align-center u-custom-font u-font-georgia u-text u-text-default u-text-1" style="color: white">
			<f:message
					key="admin.cabinet.name" bundle="${local}"/></h3>
		<br>
		<table class="u-align-center table"
		       style="margin-left: auto;margin-right: auto;text-align: center">
			<thead class="u-grey-80 u-opacity u-opacity-70">
			<tr class="text-center">
				<th scope="col" style="text-align: center"><f:message key="admin.cabinet.col1" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="admin.cabinet.col2" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="admin.cabinet.col3" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="admin.cabinet.col4" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="admin.cabinet.col5" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="admin.cabinet.col6" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="admin.cabinet.col7" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="admin.cabinet.col8" bundle="${local}"/></th>
				<th scope="col" style="text-align: center"><f:message key="admin.cabinet.col9" bundle="${local}"/></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${clientOrders}" var="clientOrder">
				<tr class=" text-center u-grey-10 u-opacity-85">
					<th scope="col" style="text-align: center">${clientOrder.getOrderID()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getFirstName()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getLastName()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getEmail()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getRoomID()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getRoomClass()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getCheckInDate()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getCheckOutDate()}</th>
					<th scope="col" style="text-align: center">${clientOrder.getOrderStatus()}</th>
					<th style="text-align: center">
						<a>
							<c:if test="${clientOrder.isCanBeCanceled()}">
								<a href="<c:url value="command?name=order_handling&type=reject&orderID=${clientOrder.getOrderID()}&roomID=${clientOrder.getRoomID()}"/>">
										<%--									<form action="command?name=order_handling&type=reject&orderID=${clientOrder.getOrderID()}&roomID=${clientOrder.getRoomID()}"--%>
										<%--									      method="post">--%>
									<button type="button" class="btn btn-danger"><f:message
											key="admin.cabinet.reject"
											bundle="${local}"/></button>
								</a>
								<%--									</form>--%>
								<a href="<c:url value="command?name=order_handling&type=approve&orderID=${clientOrder.getOrderID()}&roomID=${clientOrder.getRoomID()}"/>">
										<%--									<form action="command?name=order_handling&type=approve&orderID=${clientOrder.getOrderID()}&roomID=${clientOrder.getRoomID()}"--%>
										<%--									      method="post">--%>
									<button type="button" class="btn btn-success"><f:message
											key="admin.cabinet.approve"
											bundle="${local}"/></button>
										<%--									</form>--%>
								</a>
							</c:if>
						</a>
					</th>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</section>
<c:import url="common/footer.jsp"/>
<%--<c:import url="common/cookies.jsp"/>--%>
</body>
</html>