<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jquery AJAX</title>
</head>
<body>

<div id="here"></div>

<input type="text" id="memoId"/>
<button id="btn">Click</button>

<label>
    <p>
        <input type="text" name="memoText"/>
        <button id="btn2">input</button>
    </p>
</label>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    $(document).ready(function() {

        // ajax 준비 - 데이터 가져오기
        // 버튼 클릭 시 기능 작동
        $("#btn").on("click", function () {
            let id = $('#memoId').val();
            $.getJSON('/memo/'+id, function (data) {
                let str;
                console.log(data);
                str = "<p>"+data.mno+"<br>"+data.memoText+"</p>";
                $("#here").html(str);
            });
        });

        $("#btn2").on("click", function (){
            let memo = {
                memoText: $('input[name="memoText"]').val(),    // input-name에 입력된 값 가져옴
            };
            // input-text 에 입력한 값 출력
            console.log(memo)

            $.ajax({
                url: '/input',
                method: 'post',
                data: JSON.stringify(memo),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function(data) {
                    console.log(data);
                }
            });
        });
    });

</script>
</body>
</html>
