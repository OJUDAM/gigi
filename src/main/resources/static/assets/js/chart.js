
 function showChart(jsonData) {
        //차트 색상 랜덤
        function randomColor(labels) {
            var colors = [];
            for (let i = 0; i < labels.length; i++) {
                colors.push("#" + Math.round(Math.random() * 0xffffff).toString(16));
            }
            return colors;
        }
        function makeChart(ctx, type, labels, data) {
            var myChart = new Chart(ctx, {
                type: type,
                data: {
                    labels: labels,
                    datasets: [{
                        label: '지하철 혼잡도',
                        data: data,
                        //borderColor: Utils.CHART_COLORS.red,
                        //backgroundColor: Utils.transparentize(Utils.CHART_COLORS.red, 0.5),
                       // backgroundColor: randomColor(labels)
                        backgroundColor: [
                           //색상
                           'rgba(255, 99, 132, 0.2)',
                           'rgba(54, 162, 235, 0.2)',
                           'rgba(255, 206, 86, 0.2)',
                           'rgba(75, 192, 192, 0.2)',
                           'rgba(153, 102, 255, 0.2)',
                           'rgba(255, 159, 64, 0.2)'
                       ],
                       borderColor: [
                           //경계선 색상
                           'rgba(255, 99, 132, 1)',
                           'rgba(54, 162, 235, 1)',
                           'rgba(255, 206, 86, 1)',
                           'rgba(75, 192, 192, 1)',
                           'rgba(153, 102, 255, 1)',
                           'rgba(255, 159, 64, 1)'
                       ],
                       borderWidth: 1 //경계선 굵기
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            });
        }

        function convertJsonToObject(jsonData) {
            var jsonObject = JSON.stringify(jsonData);
            return JSON.parse(jsonObject);
        }


        var congestionData = convertJsonToObject(jsonData);

        var labelList = new Array();
        var valueList = new Array();
        var colorList = new Array();

        for(var i = 0; i<congestionData.length; i++) {
            var congestion = congestionData[i];
            labelList.push(congestion.stationName);
            valueList.push(congestion.congestionMin10);
        }

        var newLabels = labelList.slice(-5);
        var newMyData = valueList.slice(-5);
        // Chart.js 막대그래프 그리기
        var ctx = $('#myChart');
        makeChart(ctx, 'bar', newLabels, newMyData);

       // Chart.js 선그래프 그리기
        //ctx = $('#myChart2');
        //makeChart(ctx, 'line', newLabels, newMyData);
        // Chart.js 원그래프 그리기
        //ctx = $('#myChart3');
        //makeChart(ctx, 'pie', newLabels, newMyData);
        //ctx = $('#myChart4');
        //makeChart(ctx, 'doughnut', newLabels, newMyData);
};