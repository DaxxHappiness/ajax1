<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fetch </title>
</head>
<body>

<div id="here"></div>

<input type="text" id="memoId"/>
<button id="btn">Click</button>

<label>
    <p>
        <input type="text" name="memoText" id="id2"/>
        <button id="btn2">input</button>
    </p>
</label>

<script>
    let btn = document.querySelector("#btn");
    let id = document.querySelector("#memoId");
    let here = document.querySelector("#here");
    let btn2 = document.querySelector("#btn2");
    // 데이터를 가져오는 것
    btn.addEventListener("click", function (){
        let val = id.value;
        console.log(val)
        fetch("/memo/"+val, { method: "GET", })
            .then((response) => response.json())        // json으로 응답 처리, 무조건 json만 사용 가능함! 고정형!
            .then((data) => {
                console.log(data);
                here.innerHTML = "<p>"+data.mno+"<br>"+data.memoText+"<p/>";
            });
    });
    // 데이터를 입력하는 것
    btn2.addEventListener("click", function (){
        const val = document.querySelector("#id2").value;
        let memo = {
            memoText: val,
        };
        fetch("/input/", {
            method: "POST",
            headers: { "Content-Type": "application/json", },
            body: JSON.stringify(memo),
        })
            .then((response) => response.json())
            .then((data) => console.log(data));
    });
</script>

</body>
</html>
