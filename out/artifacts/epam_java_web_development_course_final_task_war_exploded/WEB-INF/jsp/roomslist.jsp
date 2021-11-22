<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html style="font-size: 16px;">
<head>
	<title>Rooms List</title>
	<link rel="stylesheet" href="../../css/Rooms-List.css" media="screen">
	<meta property="og:title" content="Rooms List">
	<c:import url="common/head.jsp"/>
</head>
<body class="u-body">
<c:import url="common/menu.jsp"/>
<section class="skrollable u-clearfix u-section-1" id="sec-5a0b">
	<div class="u-clearfix u-sheet u-sheet-1">
		<div class="u-clearfix u-custom-html u-expanded-width u-preserve-proportions u-custom-html-1">
			<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
			<script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
			<div class="container">
				<div class="row text-center">
					<div class="col-12">
						<h6>Available rooms for date from ${dateFrom} to ${dateTo}</h6>
						<form method="post" action="command?name=book">
						<table class="table table-image" align="center">
							<thead>
							<tr style="text-align:center">
								<th scope="col">Room Number</th>
								<th scope="col"></th>
								<th scope="col">Type Room</th>
								<th scope="col">Price</th>
								<th scope="col"></th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<th scope="row" class="text-center">
									<div class="d-flex justify-content-center mt-100 row">
										<div class="col-md-10">
											<select id="choices-multiple-remove-button"
											        placeholder="select a room number" multiple="" name="singleRoomsSelected">
												<c:forEach items="${singleRooms}"
												           var="roomID">
													<option value=${roomID}>${roomID}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</th>
								<td class="w-25">
									<img src="https://www.travelline.ru/resource/images/rt/3652/637321324083617504-c802f669-089d-466d-a31c-d7cd2ba5afe8"
									     class="img-fluid img-thumbnail" alt="image">
								</td>
								<td>Single Room</td>
								<td>42.2</td>
								<td>
									<button type="submit" class="btn btn-dark">Book</button>
								</td>
							</tr>
							<tr>
								<th scope="row" class="text-center">
									<div class="d-flex justify-content-center mt-100 row">
										<div class="col-md-10">
											<select id="choices-multiple-remove-button"
											        placeholder="select a room number" multiple="" name="doubleRoomsSelected">
												<c:forEach items="${doubleRooms}"
												           var="roomID">
													<option value="${roomID}">${roomID}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</th>
								<td class="w-25">
									<img src="https://www.travelline.ru/resource/images/rt/19641/637321325354036836-a772be13-ef6f-474d-be09-5fd8af3b7b30"
									     class="img-fluid img-thumbnail" alt="image">
								</td>
								<td>Double Room</td>
								<td>72.3</td>
								<td>
									<button type="button" class="btn btn-dark">Book</button>
								</td>
							</tr>
							<tr>
								<th scope="row" class="text-center">
									<div class="d-flex justify-content-center mt-100 row">
										<div class="col-md-10">
											<select id="choices-multiple-remove-button"
											        placeholder="select a room number" multiple="" name="suiteRoomsSelected">
												<c:forEach items="${suiteRooms}"
												           var="roomID">
													<option value="${roomID}">${roomID}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</th>
								<td class="w-25">
									<img src="https://www.travelline.ru/resource/images/rt/3650/637321325650296040-d8c427ef-b9ba-4699-a059-8e2fdbff6c44"
									     class="img-fluid img-thumbnail" alt="image">
								</td>
								<td>Studio</td>
								<td>92.5</td>
								<td>
									<button type="button" class="btn btn-dark">Book</button>
								</td>
							</tr>
							<tr>
								<th scope="row" class="text-center">
									<div class="d-flex justify-content-center mt-100 row">
										<div class="col-md-10">
											<select id="choices-multiple-remove-button"
											        placeholder="select a room number" multiple="" name="deluxeRoomsSelected">
												<c:forEach items="${deluxeRooms}"
												           var="roomID">
													<option value="${roomID}">${roomID}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</th>
								<td class="w-25">
									<img src="https://www.travelline.ru/resource/images/rt/3649/637321325955926799-0183cc73-13c0-4933-9df3-7a910272a833"
									     class="img-fluid img-thumbnail" alt="image">
								</td>
								<td>Lux</td>
								<td>121.1</td>
								<td>
									<button type="button" class="btn btn-dark">Book</button>
								</td>
							</tr>
							</tbody>
						</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="u-clearfix u-custom-html u-custom-html-2"></div>
		<div class="u-clearfix u-custom-html u-custom-html-3"></div>
	</div>
</section>
<c:import url="common/footer.jsp"/>
<c:import url="common/cookies.jsp"/>
</body>
</html>