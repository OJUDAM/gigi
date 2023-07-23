<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>
<script>
$(function() {
    showChart(${congestions})
});

function requestStat(stationCode) {
    $.ajax({
                type:'get',
                async: false,
                url:'http://www.gigi.p-e.kr/congestion',
                dataType:'json',
                data:{code:stationCode},
                success: function(data, textStatus) {
                    if(data !== 'null') {
                       showChart(data);
                    }
                },
                error:function (data, textStatus) {
                    console.log('error');
                }
    })
}
</script>
<section class="spotlight style1 orient-right content-align-left image-position-center onscroll-image-fade-in" id="graph">
    <div class="content">
        <h2>지하철 혼잡도 통계 그래프</h2>
        <p>주요 역 정보입니다.</p>

         <ul class="actions stacked">
            <c:forEach var="station" items="${metaInfo}">
                <li><a onclick="requestStat(${station.stationCode})" class="button">${station.stationName}</a></li>
                <li><a href="#" class="button">Learn More</a></li>
            </c:forEach>
         </ul>

    </div>
    <div class="image">
        <canvas id="myChart"></canvas>
    </div>
</section>