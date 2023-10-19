//웹소켓 생성
var realTimePositionSocket;
var stationCodeMessage;
function realTimePositionSocketInit()
{
    //realTimePositionSocket = new WebSocket("ws://localhost:8080/socket/realTime/position");
    realTimePositionSocket = new WebSocket("ws://158.180.68.72:8080/socket/realTime/position");
    realTimePositionSocket.onopen = realTimePositionOnOpen;
    realTimePositionSocket.onclose = realTimePositionOnClose;
    realTimePositionSocket.onmessage = realTimePositionOnMessage;
    realTimePositionSocket.onerror = realTimePositionOnError;
}

function sendMessageAfterInit(stationCode){
    realTimePositionSocketInit();
    stationCodeMessage = stationCode;
}
//웹소켓 연결
function realTimePositionOnOpen(event){
    console.log("realTimePositionSocket 연결 완료");
    setInterval(() => realTimePositionSocketMsgSend("test"),3000);
}
//realTimePositionSocket.onopen = () => requestRepeat();
//메시지를 송신할 때 사용
function realTimePositionSocketMsgSend(sendMsg){
 // 세션리스트에 메시지를 송신한다.
 realTimePositionSocket.send(sendMsg);
}

//웹소켓 닫힘
function realTimePositionOnClose(event){
 console.log("realTimePositionSocket 웹소켓이 닫혔습니다.");

 // 웹소켓이 닫혀있으면 재연결을 시도합니다.
 // webSocket이 close되면 오브젝트의 속성, 메서드가 사라지기 때문에,
 // 해당 함수를 호출하여 초기화하여 줍니다.
 realTimePositionSocketInit();
}


//웹소켓 메시지 수신
function realTimePositionOnMessage(event){
  const receiveData = event.data; // 수신 data
  const realTimeJsonArray = JSON.parse(receiveData).data; //string -> json

  //ul 하위 태그 초기화
  $('#realTimePositionTable').empty();

  $('#realTimePositionTable').append("<th>train-no</th>");
  $('#realTimePositionTable').append("<th>train-name</th>");
  $('#realTimePositionTable').append("<th>station</th>");
  $('#realTimePositionTable').append("<th>time</th>");
  $('#realTimePositionTable').append("<th>remain</th>");

  //시간표 새로 생성
  for(var i = 0; i<realTimeJsonArray.length; i++) {
    const realTimeData = realTimeJsonArray[i];
    $('#realTimePositionTable').append("<tr><td>" + realTimeData.trainNo + "</td><td>" + realTimeData.arrivalStationCode + "</td><td>" + realTimeData.arrivalCode+ "</td><td>"+realTimeData.createdAt+"</td><td>"+secondToMin(realTimeData.remainTime)+"</td></tr>");

  }
}

function secondToMin(second){

    if(second < 0) {
        return "도착";
    }
    const minutes = Math.floor(second / 60);
    const seconds = second - minutes * 60;

    return minutes + ":" + seconds;
}
//웹소켓 에러
function realTimePositionOnError(event){
console.log("realTimePositionSocket 에러가 발생하였습니다.");
}