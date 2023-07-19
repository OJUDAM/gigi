<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>
<script>
$(function() {
    showChart(${congestions})
});
</script>
<section class="spotlight style1 orient-right content-align-left image-position-center onscroll-image-fade-in" id="graph">
    <div class="content">
        <h2>그래프</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi id ante sed ex pharetra lacinia sit amet vel massa. Donec facilisis laoreet nulla eu bibendum. Donec ut ex risus. Fusce lorem lectus, pharetra pretium massa et, hendrerit vestibulum odio lorem ipsum dolor sit amet.</p>
        <ul class="actions stacked">
            <li><a href="#" class="button">Learn More</a></li>
        </ul>
    </div>
    <div class="image">
        <canvas id="myChart"></canvas>
    </div>
</section>