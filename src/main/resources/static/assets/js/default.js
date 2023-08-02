$(function() {
    //망포역 기본 설정
    $("#bundangStation").val("K241").prop("selected", true);
    //input time 현재 시간으로 입력
    $("#arrivalTime").val(new Date().toTimeString().slice(0, 5));

    $('#submit').on("click",function () {
        setArrivalTime();
    });
});


function setArrivalTime(){
    const queryString = $("form[id=recordingForm]").serialize();

    $.ajax({
        type : 'post',
        url : 'http://localhost:8080/bundang-line',
        data : queryString,
        dataType : 'json',
        error: function(xhr, status, error){
            console.log(error);
        }
    });

}