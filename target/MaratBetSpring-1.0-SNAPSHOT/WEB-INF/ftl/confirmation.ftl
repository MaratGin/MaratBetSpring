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
<body>
<h1> <div class="MainDiv">MARATBET </div> </h1>
<#--<%--action="${pageContext.request.contextPath}/main" method="post"--%>-->
<form action="${pageContext.request.contextPath}/confirmation" method="post">
    <h1> <div class="maratbet">На вашу почту ${email} отправлено сообщение с кодом, введите код</div> </h1>

    <br>
    <label>
        <input id="login" type="text" name="confirmation" maxlength="15" minlength="5" placeholder="Enter code" class="login" >
    </label>
    <br>
    <#--    <%--    <button type="submit" name="submit" class="submit" onclick="checkEverything()"> Зарегестрироваться! </button>--%>-->
    <input id="submit" type="submit" name="submit" value="Зарегистрироваться" class="submit"    >
    <#--    <%--    <p style="font-size: small"> <label class="Remember" for="rem"> Remember me! </label>  <input  id="rem" type="checkbox" name="Remember me" value="true"> </p>--%>-->
    <#--    <%--    <form action="${pageContext.request.contextPath}/main" method="post" ><input id="redirect" type="hidden"></form>--%>-->
</form>

<br>
<h3 id="registrationStatus" style="color: white; text-align: center; font-family:'Parimatch', sans-serif; font-size: 30pt; text-shadow:  0 0 7px red"> ${registrationStatus}</h3>
</body>
</html>


