<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>
<script>
$(function() {
    showChart(${congestions}, 'myChart')
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
                       showChart(data,'myChart');
                    }
                },
                error:function (data, textStatus) {
                    console.log('error');
                }
    })
}
function requestStatAndCount(stationCode) {
    $.ajax({
                type:'get',
                async: false,
                url:'http://www.gigi.p-e.kr/congestion/count',
                dataType:'json',
                data:{code:stationCode},
                success: function(data, textStatus) {
                    if(data !== 'null') {
                       showChart(data,'myChart');
                    }
                },
                error:function (data, textStatus) {
                    console.log('error');
                }
    })
}
</script>
<section class="wrapper style1 align-center" id="graph">
            <div class="inner" id="graph-container">
                 <canvas id="myChart"></canvas>
            </div>
    <div class="gallery style2 medium lightbox onscroll-fade-in">
            <c:forEach var="subway" items="${metaInfo}">
            <article>
                <a href="images/gallery/fulls/01.jpg" class="image">
                    <img src="images/gallery/thumbs/01.jpg" alt="" />
                </a>
                <div class="caption">
                    <ul class="icons fixed">
                        <c:forEach var="station" items="${subway.value}">
                            <li><a onclick="requestStatAndCount(${station.stationCode})" class="button small">${station.stationName}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </article>
        </c:forEach>

</section>