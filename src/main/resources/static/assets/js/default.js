$(function() {
    //망포역 기본 설정
    $("#bundangStation").val("K241").prop("selected", true);
    //출발역(수서역), 도착역(망포역) 기본설정
    $("#startStationCode").val("K221").prop("selected", true);
    $("#endStationCode").val("K241").prop("selected", true);
    //input time 현재 시간으로 입력
    $("#arrivalTime").val(new Date().toTimeString().slice(0, 5));

    $('#submit').on("click",function () {
        setArrivalTime();
    });

    $('#suseo').on("click",function () {
            stationCodeMessage = "수서역";
     });

    // 웹 소켓 초기화
   	realTimePositionSocketInit();
   	//realTimeSocketInit();

});



function setArrivalTime(){
    const queryString = $("form[id=recordingForm]").serialize();

    $.ajax({
        type : 'post',
        url:'http://www.gigi.p-e.kr/bundang-line',
        //url : 'http://localhost:8080/bundang-line',
        data : queryString,
        dataType : 'json',
        error: function(xhr, status, error){
            console.log(error);
        }
    });
}


