<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<jsp:include page="header/header.jsp"></jsp:include>

<body class="is-preload">
		<!-- Wrapper -->
			<div id="wrapper" class="divided">

				<!-- One -->
					<section class="banner style1 orient-left content-align-left image-position-right fullscreen onload-image-fade-in onload-content-fade-right">
						<div class="content">
							<h1>지하철 데이터</h1>
							<h1>분석</h1>
							<p class="major">출퇴근 시간 수도권의 지하철은 항상 발 디딜 틈 없이 혼잡합니다.
                                             <br>
                                             도로를 주행하는 버스, 택시 뿐만 아니라 지하철 역시 혼잡도에 따라 도착 시간이 지연됩니다.
                                             <br>
                                             이러한 지연되는 시간을 분석하기 위해
                                             <br>
                             <a href="https://openapi.sk.com/products/detail?svcSeq=54&menuSeq=249">지오비전 퍼즐</a>
                                             에서 제공하는
                                             <br>
                             <a href="https://openapi.sk.com/products/detail?svcSeq=54&menuSeq=414"> 지하철 혼잡도 API</a>
                                            를 이용하여 분석해보았습니다.</p>
							<ul class="actions stacked">
								<li><a href="#record" class="button big wide smooth-scroll-middle">Get Started</a></li>
							</ul>
						</div>
						<div class="image">
							<img src="images/banner.jpeg" alt="지하철 이미지" />
						</div>
					</section>

                <!-- Two (Graph) -->
				<jsp:include page="section/graph.jsp"></jsp:include>



				<!-- Four -->
					<section class="spotlight style1 orient-right content-align-left image-position-center onscroll-image-fade-in">
						<div class="content">

							<table id="realTimeTable" class="actions stacked">
							</table>
						</div>
						<div class="image">
							<img src="images/spotlight03.jpg" alt="" />
						</div>
					</section>


				<!-- Seven -->
					<section class="wrapper style1 align-center" id="record">
						<div class="inner medium">
							<h2>Get in touch</h2>
							<form id="recordingForm">
								<div class="fields">
									<div class="field half">
										<label for="station">Station</label>
										<select name="stationCode" id="bundangStation" form="recordingForm">
										    <c:forEach var="station" items="${bundangLine}">
                                                <option value="${station.stationCode}">${station.stationName}</option>
                                            </c:forEach>
                                        </select>
									</div>
									<div class="field half">
                                        <label for="time">Time</label>
                                        <input type="time" name="arrivalTime" id="arrivalTime" value="" />
                                    </div>
									</div>

									<div class="field">
										<label for="message">Message</label>
										<textarea name="message" id="message" rows="6"></textarea>
									</div>
								</div>
								<ul class="actions special">
									<li><input type="submit" id="submit" value="Send Message" /></li>
								</ul>
							</form>

						</div>
					</section>

				<!-- Footer -->
					<footer class="wrapper style1 align-center">
						<div class="inner">
							<ul class="icons">
								<li><a href="#" class="icon brands style2 fa-twitter"><span class="label">Twitter</span></a></li>
								<li><a href="#" class="icon brands style2 fa-facebook-f"><span class="label">Facebook</span></a></li>
								<li><a href="#" class="icon brands style2 fa-instagram"><span class="label">Instagram</span></a></li>
								<li><a href="#" class="icon brands style2 fa-linkedin-in"><span class="label">LinkedIn</span></a></li>
								<li><a href="#" class="icon style2 fa-envelope"><span class="label">Email</span></a></li>
							</ul>
							<p>&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
						</div>
					</footer>

			</div>

		    <!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
            <script src="assets/js/chart.js"></script>
            <script src="assets/js/default.js"></script>
	</body>
</html>