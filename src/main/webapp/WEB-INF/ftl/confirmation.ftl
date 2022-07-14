<!doctype html>
<html lang="en">
<head>
    <title>Регистрация</title>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/signIn.css">
<#--    <script src="${pageContext.request.contextPath}/javascript/validator.js"></script>-->
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=David+Libre&display=swap" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Libre+Barcode+128+Text&display=swap" rel="stylesheet">

</head>
<body class = "confirmation">
<h1> <div class="MainDiv">MARATBET </div> </h1>
<#--<%--action="${pageContext.request.contextPath}/main" method="post"--%>-->

    <h2 class="maratbet"> На вашу почту <div >${email}</div> отправлено сообщение с кодом, введите код</h2>
<form action="/confirmation" method="post">
    <br>
    <label>
        <input id="code" type="text" name="code" maxlength="15" minlength="5" placeholder="Enter code" class="confirmation" >
        <input id="email" type="text" name="email" maxlength="15" minlength="5" placeholder="Enter e-mail again" class="confirmation" value="${emaill}" style="visibility: hidden"  >
    </label>
    <br>
    <#--    <%--    <button type="submit" name="submit" class="submit" onclick="checkEverything()"> Зарегестрироваться! </button>--%>-->
    <input id="submit" type="submit" name="submit" value="Зарегистрироваться" class="submitCode" >
    <#--    <%--    <p style="font-size: small"> <label class="Remember" for="rem"> Remember me! </label>  <input  id="rem" type="checkbox" name="Remember me" value="true"> </p>--%>-->
    <#--    <%--    <form action="${pageContext.request.contextPath}/main" method="post" ><input id="redirect" type="hidden"></form>--%>-->
</form>

<br>
<h3 id="registrationStatus" style="color: white; text-align: center; font-family:'Parimatch', sans-serif; font-size: 30pt; text-shadow:  0 0 7px red"> ${registrationStatus}</h3>
</body>
<script>function sendConfirm() {
        var code = document.getElementById("code").value;
        var email = document.getElementById("email").value;
        var confirmDto = {
            email: email,
            code: code
        };

        $.ajax({
            url: '/confirmation',
            method: 'post',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(confirmDto),

            success: function(data){
                alert(data);
            }

        })

    }</script>
</html>


