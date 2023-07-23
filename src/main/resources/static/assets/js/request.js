function requestData(sUrl) {
    $.ajax({
                type:'post',
                async:false, //false가 기본값임 - 비동기
                url:'http://localhost:8080/loginCheck',
                dataType:'text',
                data:{id:id},
                success: function(data, textStatus) {
                    if(data === 'usable') {
                        $('#message').text('사용할 수 있는 ID입니다.')
                        $('#checkBtn').prop('disabled', true) // 사용할 수 있는 ID면 버튼을 비활성화 시킨다.
                    } else {
                        $('#message').text('이미 사용 중인 아이디입니다.')
                    }
                },
                error:function (data, textStatus) {
                    console.log('error');
                }
    })
}