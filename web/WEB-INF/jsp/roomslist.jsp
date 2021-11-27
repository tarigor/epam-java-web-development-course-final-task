<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}" scope="session"/>
<f:setBundle basename="local.menu" var="local"/>

<html style="font-size: 16px;">
<head>
	<title>Rooms List</title>
	<link rel="stylesheet" href="../../css/Rooms-List.css" media="screen">
	<meta property="og:title" content="Rooms List">
	<c:import url="common/head.jsp"/>
</head>
<body class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-image u-section-1" id="sec-3a70" data-image-width="1280" data-image-height="700">
	<div class="u-clearfix u-sheet u-sheet-1">
		<a style="color: white;text-align: center">
			<f:message key="roomslist.page.name" bundle="${local}"/></a>
		<br>
		<div class="u-clearfix u-custom-html u-expanded-width u-preserve-proportions u-custom-html-1">
			<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
			<script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
			<div class="container">
				<div class="row text-center">
					<div class="col-12">
						<form method="post" action="command?name=send_invoice">
							<input type="hidden" name="requestID" value="${clientRequest.getRequestID()}">
							<input type="hidden" name="clientID" value="${clientRequest.getClientID()}">
							<input type="hidden" name="persons" value="${clientRequest.getPersons()}">
							<table class="u-align-center table"
							       style="margin-left: auto;margin-right: auto;text-align: center">
								<thead class="u-grey-80 u-opacity u-opacity-70">
								<tr class="text-center" style="font-size: x-small">
									<th scope="col" style="text-align: center"><f:message
											key="admin.cabinet.request.col1"
											bundle="${local}"/></th>
									<th scope="col" style="text-align: center"><f:message
											key="admin.cabinet.request.col2"
											bundle="${local}"/></th>
									<th scope="col" style="text-align: center"><f:message
											key="admin.cabinet.request.col3"
											bundle="${local}"/></th>
									<th scope="col" style="text-align: center"><f:message
											key="admin.cabinet.request.col4"
											bundle="${local}"/></th>
									<th scope="col" style="text-align: center"><f:message
											key="admin.cabinet.request.col5"
											bundle="${local}"/></th>
									<th scope="col" style="text-align: center"><f:message
											key="admin.cabinet.request.col6"
											bundle="${local}"/></th>
									<th scope="col" style="text-align: center"><f:message
											key="admin.cabinet.request.col7"
											bundle="${local}"/></th>
									<th scope="col" style="text-align: center"><f:message
											key="admin.cabinet.request.col8"
											bundle="${local}"/></th>
									<th scope="col" style="text-align: center"><f:message
											key="admin.cabinet.request.col9"
											bundle="${local}"/></th>
									<th></th>
								</tr>
								</thead>
								<tbody>
								<tr class=" text-center u-grey-10 u-opacity-85" style="font-size: x-small">
									<th scope="col" style="text-align: center">${clientRequest.getRequestID()}</th>
									<th scope="col" style="text-align: center">${clientRequest.getFirstName()}</th>
									<th scope="col" style="text-align: center">${clientRequest.getLastName()}</th>
									<th scope="col" style="text-align: center">${clientRequest.getEmail()}</th>
									<th scope="col" style="text-align: center">${clientRequest.getPersons()}</th>
									<th scope="col" style="text-align: center">${clientRequest.getRoomClass()}</th>
									<th scope="col" style="text-align: center">${clientRequest.getDateFrom()}</th>
									<th scope="col" style="text-align: center">${clientRequest.getDateTo()}</th>
									<th scope="col" style="text-align: center"><f:message
											key="${clientRequest.getRequestStatus().getDescription()}"
											bundle="${local}"/></th>
									<th style="text-align: center">
										<a href="<c:url value="command?name=request_handling"/>">
											<button type="button" class="btn btn-warning"><f:message
													key="roomslist.request.cancel"
													bundle="${local}"/></button>
										</a>
									</th>
								</tr>
								</tbody>
							</table>
							<br>
							<%--						<form method="post" action="command?name=send_invoice">--%>
							<input type="hidden" name="dateFrom" value="${dateFrom}">
							<input type="hidden" name="dateTo" value="${dateTo}">
							<table class="table table-image" align="center">
								<thead class="u-grey-80 u-opacity u-opacity-70">
								<tr style="text-align:center">
									<th scope="col" style="text-align:center">Room Number</th>
									<th scope="col" style="text-align:center"></th>
									<th scope="col" style="text-align:center">Persons In Room</th>
									<th scope="col" style="text-align:center">Type Room</th>
									<th scope="col" style="text-align:center">Price</th>
								</tr>
								</thead>
								<tbody class="u-grey-10 u-opacity-85">
								<tr>
									<th scope="row" class="text-center">
										<div class="d-flex justify-content-center mt-100 row">
											<div class="col-md-10">
												<select id="choices-multiple-remove-button"
												        placeholder="select a room number" multiple=""
												        name="singleRoomsSelected">
													<c:forEach items="${roomArrayList}" var="room">
														<c:if test="${room.getRoomType().equals('single')}">
															<option value=${room.getRoomID()}>${room.getRoomID()}</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
									</th>
									<td>
										<img src="https://www.travelline.ru/resource/images/rt/3652/637321324083617504-c802f669-089d-466d-a31c-d7cd2ba5afe8"
										     class="img-fluid img-thumbnail" alt="image" style="height:100px;">
									</td>
									<td style="text-align:center">1</td>
									<td style="text-align:center">Single Room</td>
									<td style="text-align:center">42.2</td>
								</tr>
								<tr>
									<th scope="row" class="text-center">
										<div class="d-flex justify-content-center mt-100 row">
											<div class="col-md-10">
												<select id="choices-multiple-remove-button"
												        placeholder="select a room number" multiple=""
												        name="doubleRoomsSelected">
													<c:forEach items="${roomArrayList}" var="room">
														<c:if test="${room.getRoomType().equals('double')}">
															<option value=${room.getRoomID()}>${room.getRoomID()}</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
									</th>
									<td>
										<img src="https://www.travelline.ru/resource/images/rt/19641/637321325354036836-a772be13-ef6f-474d-be09-5fd8af3b7b30"
										     class="img-fluid img-thumbnail" alt="image" style="height:100px;">
									</td>
									<td style="text-align:center">2</td>
									<td style="text-align:center">Double Room</td>
									<td style="text-align:center">72.3</td>
								</tr>
								<tr>
									<th scope="row" class="text-center">
										<div class="d-flex justify-content-center mt-100 row">
											<div class="col-md-10">
												<select id="choices-multiple-remove-button"
												        placeholder="select a room number" multiple=""
												        name="suiteRoomsSelected">
													<c:forEach items="${roomArrayList}" var="room">
														<c:if test="${room.getRoomType().equals('suite')}">
															<option value=${room.getRoomID()}>${room.getRoomID()}</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
									</th>
									<td>
										<img src="https://www.travelline.ru/resource/images/rt/3650/637321325650296040-d8c427ef-b9ba-4699-a059-8e2fdbff6c44"
										     class="img-fluid img-thumbnail" alt="image" style="height:100px;">
									</td>
									<td style="text-align:center">3</td>
									<td style="text-align:center">Studio</td>
									<td style="text-align:center">92.5</td>
								</tr>
								<tr>
									<th scope="row" class="text-center">
										<div class="d-flex justify-content-center mt-100 row">
											<div class="col-md-10">
												<select id="choices-multiple-remove-button"
												        placeholder="select a room number" multiple=""
												        name="deluxeRoomsSelected">
													<c:forEach items="${roomArrayList}" var="room">
														<c:if test="${room.getRoomType().equals('deluxe')}">
															<option value=${room.getRoomID()}>${room.getRoomID()}</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
									</th>
									<td>
										<img src="https://www.travelline.ru/resource/images/rt/3649/637321325955926799-0183cc73-13c0-4933-9df3-7a910272a833"
										     class="img-fluid img-thumbnail" alt="image" style="height:100px;">
									</td>
									<td style="text-align:center">2</td>
									<td style="text-align:center">Deluxe</td>
									<td style="text-align:center">121.1</td>
								</tr>
								<tr class="text-center">
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td>
										<button type="submit" class="btn btn-dark">Send Invoice To Client</button>
									</td>
								</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<c:import url="common/footer.jsp"/>
</body>
</html>