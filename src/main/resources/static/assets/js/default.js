$(function() {
    //망포역 기본 설정
    $("#bundangStation").val("K241").prop("selected", true);
    //input time 현재 시간으로 입력
    $("#arrivalTime").val(new Date().toTimeString().slice(0, 5));

    $('#submit').on("click",function () {
        setArrivalTime();
    });

    // 웹 소켓 초기화
   	webSocketInit();

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

//웹소켓 생성
var webSocket;

function webSocketInit()
{
    webSocket = new WebSocket("ws://localhost:8080/websocket");
    webSocket.onopen = onOpen;
    webSocket.onclose = onClose;
    webSocket.onmessage = onMessage;
    webSocket.onerror = onError;
}
//웹소켓 연결
function onOpen(event){
 console.log("연결 완료");
 socketMsgSend();
}

//메시지를 송신할 때 사용
function socketMsgSend(){
 // 메시지 포맷
 var msg = {
    type : "message",
    value : "메시지입니다.",
 }

 // 세션리스트에 메시지를 송신한다.
 webSocket.send(msg)
}

//웹소켓 닫힘
function onClose(event){
 console.log("웹소켓이 닫혔습니다.");

 // 웹소켓이 닫혀있으면 재연결을 시도합니다.
 // webSocket이 close되면 오브젝트의 속성, 메서드가 사라지기 때문에,
 // 해당 함수를 호출하여 초기화하여 줍니다.
 webSocketInit();
}


//웹소켓 메시지 수신
function onMessage(event){
  const receiveData = event.data; // 수신 data
  const realTimeJsonArray = JSON.parse(receiveData).data; //string -> json
  console.log(realTimeJsonArray);

  //ul 하위 태그 초기화
  $('#realTimeTable').empty();
  //시간표 새로 생성
  for(var i = 0; i<realTimeJsonArray.length; i++) {
    const realTimeData = realTimeJsonArray[i];
    $('#realTimeTable').append('<li><h2>'+realTimeData.trainNo+'-'+realTimeData.trainName+'-'+realTimeData.arrivalMessage+'</h2></li>');
  }

}

//웹소켓 에러
function onError(event){
console.log("에러가 발생하였습니다.");
}

