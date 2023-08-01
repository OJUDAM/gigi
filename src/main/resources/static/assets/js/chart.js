
 function showChart(jsonData, chartId) {
        //그래프 색상
        var backColor = [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)',
            'rgba(230, 235, 152, 0.2)'
            ]
        //그래프 가장자리 색상
        var borderColor=[
           'rgba(255, 99, 132, 1)',
           'rgba(54, 162, 235, 1)',
           'rgba(255, 206, 86, 1)',
           'rgba(75, 192, 192, 1)',
           'rgba(153, 102, 255, 1)',
           'rgba(255, 159, 64, 1)',
           'rgba(230, 235, 152, 1)'
           ]
        function randomColor(labels) {
            var colors = [];
            for (let i = 0; i < labels.length; i++) {
                colors.push("#" + Math.round(Math.random() * 0xffffff).toString(16));
            }
            return colors;
        }
        function makeChart(ctx, type, labels, data00, data10, data20, data30, data40, data50, userCount) {
            var myChart = new Chart(ctx, {
                type: type,
                data: {
                    labels: labels,
                    datasets: [{
                        label: '00분',
                        data: data00,
                        backgroundColor: backColor[0],
                       borderColor: borderColor[0],
                       borderWidth: 1 //경계선 굵기
                    },
                    {
                        label: '10분',
                        data: data10,
                       backgroundColor: backColor[1],
                       borderColor: borderColor[1],
                       borderWidth: 1 //경계선 굵기
                    },
                    {
                        label: '20분',
                        data: data20,
                       backgroundColor: backColor[2],
                       borderColor: borderColor[2],
                       borderWidth: 1 //경계선 굵기
                    },
                    {
                         label: '30분',
                         data: data30,
                        backgroundColor: backColor[3],
                        borderColor: borderColor[3],
                        borderWidth: 1 //경계선 굵기
                    },
                     {
                         label: '40분',
                         data: data40,
                        backgroundColor: backColor[4],
                        borderColor: borderColor[4],
                        borderWidth: 1 //경계선 굵기
                    },
                     {
                         label: '50분',
                         data: data50,
                        backgroundColor: backColor[5],
                        borderColor: borderColor[5],
                        borderWidth: 1 //경계선 굵기
                    },
                    {
                         label: '이용자 수',
                         type: 'line',
                         data: userCount,
                        backgroundColor: backColor[6],
                        borderColor: borderColor[6],
                        borderWidth: 1 //경계선 굵기
                    }

                    ]
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

        var labelList = new Array();
        var valueList00 = new Array();
        var valueList10 = new Array();
        var valueList20 = new Array();
        var valueList30 = new Array();
        var valueList40 = new Array();
        var valueList50 = new Array();
        var lineValue = new Array();

        console.log(jsonData);
        for(var i = 0; i<jsonData.length; i++) {
            var congestion = jsonData[i];

            labelList.push('[' + congestion.day+ ']' + congestion.stationName + '(' + congestion.hour+ '시/' + congestion.directAt + ')');
            valueList00.push(congestion.congestionMin00);
            valueList10.push(congestion.congestionMin10);
            valueList20.push(congestion.congestionMin20);
            valueList30.push(congestion.congestionMin30);
            valueList40.push(congestion.congestionMin40);
            valueList50.push(congestion.congestionMin50);
            lineValue.push(congestion.userCountPerHour);

        }

        var newLabels = labelList.slice(-6);
        var newMyData00 = valueList00.slice(-6);
        var newMyData10 = valueList10.slice(-6);
        var newMyData20 = valueList20.slice(-6);
        var newMyData30 = valueList30.slice(-6);
        var newMyData40 = valueList40.slice(-6);
        var newMyData50 = valueList50.slice(-6);

        resetCanvas();
        // Chart.js 막대그래프 그리기
        var ctx = $('#' +chartId);
        var ctx2 = $('#myChart2');
        makeChart(ctx, 'bar', newLabels, newMyData00, newMyData10, newMyData20, newMyData30, newMyData40, newMyData50, lineValue);
        makeChart(ctx2, 'bar', newLabels, newMyData00, newMyData10, newMyData20, newMyData30, newMyData40, newMyData50, lineValue);

       // Chart.js 선그래프 그리기
        //ctx = $('#myChart2');
        //makeChart(ctx, 'line', newLabels, newMyData);
        // Chart.js 원그래프 그리기
        //ctx = $('#myChart3');
        //makeChart(ctx, 'pie', newLabels, newMyData);
        //ctx = $('#myChart4');
        //makeChart(ctx, 'doughnut', newLabels, newMyData);
 function resetCanvas() {
   $('#myChart').remove(); // this is my <canvas> element
   $('#graph-container').append('<canvas  height="200" id="myChart"></canvas>');
   $('#myChart2').remove(); // this is my <canvas> element
   $('#graph-container2').append('<canvas width="1000" height="1000" id="myChart2"></canvas>');

 };
};