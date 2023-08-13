//웹소켓 생성
var realTimeSocket;

function realTimeSocketInit()
{
    //realTimeSocket = new WebSocket("ws://localhost:8080/socket/realTime");
    realTimeSocket = new WebSocket("ws://158.180.68.72:8080/socket/realTime");
    realTimeSocket.onopen = realTimeOnOpen;
    realTimeSocket.onclose = realTimeOnClose;
    realTimeSocket.onmessage = realTimeOnMessage;
    realTimeSocket.onerror = realTimeOnError;
}
//웹소켓 연결
function realTimeOnOpen(event){
 console.log("realTimeSocket 연결 완료");
 //realTimeSocketMsgSend();
}

//메시지를 송신할 때 사용
function realTimeSocketMsgSend(){
 // 메시지 포맷
 var msg = {
    type : "message",
 }

 // 세션리스트에 메시지를 송신한다.
 realTimeSocket.send(msg)
}

//웹소켓 닫힘
function realTimeOnClose(event){
 console.log("realTimeSocket 웹소켓이 닫혔습니다.");

 // 웹소켓이 닫혀있으면 재연결을 시도합니다.
 // webSocket이 close되면 오브젝트의 속성, 메서드가 사라지기 때문에,
 // 해당 함수를 호출하여 초기화하여 줍니다.
 realTimeSocketInit();
}


//웹소켓 메시지 수신
function realTimeOnMessage(event){
  const receiveData = event.data; // 수신 data
  const realTimeJsonArray = JSON.parse(receiveData).data; //string -> json

  //ul 하위 태그 초기화
  $('#realTimeTable').empty();

  $('#realTimeTable').append("<th>train-no</th>");
  $('#realTimeTable').append("<th>train-name</th>");
  $('#realTimeTable').append("<th>message</th>");

  //시간표 새로 생성
  for(var i = 0; i<realTimeJsonArray.length; i++) {
    const realTimeData = realTimeJsonArray[i];
    $('#realTimeTable').append("<tr><td><a onclick='sendMessageAfterInit("+realTimeData.trainNo+")'>" + realTimeData.trainNo + "</a></td><td>" + realTimeData.trainName + "</td><td>" + realTimeData.arrivalMessage+ "</td></tr>");

  }
}

//웹소켓 에러
function realTimeOnError(event){
console.log("realTimeSocket 에러가 발생하였습니다.");
}