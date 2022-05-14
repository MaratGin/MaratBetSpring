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
<form action="/signUp" method="post" >
    <br>
    <label>
        <input id="email" type="email" name="email" maxlength="25"  placeholder="email" class="login">
    </label>
    <br>
    <label>
        <input id="login" type="text" name="login" maxlength="15" minlength="5" placeholder="Login" class="login" >
    </label>
    <br>
    <label>
        <input id="password" type="password" name="password" maxlength="15" minlength="5" placeholder="Password" class="password">
    </label>
    <br>
    <label>
        <input id="repassword" type="password" name="repassword" maxlength="15" minlength="5" placeholder="Confirm Password" class="password" >
    </label>
    <br>
<#--    <%--    <button type="submit" name="submit" class="submit" onclick="checkEverything()"> Зарегестрироваться! </button>--%>-->
    <input id="submit" type="submit" name="submit" value="Зарегистрироваться" class="submit"    >
<#--    <%--    <p style="font-size: small"> <label class="Remember" for="rem"> Remember me! </label>  <input  id="rem" type="checkbox" name="Remember me" value="true"> </p>--%>-->
<#--    <%--    <form action="${pageContext.request.contextPath}/main" method="post" ><input id="redirect" type="hidden"></form>--%>-->
</form>

<br>
<h3 id="registrationStatus" style="color: white; text-align: center; font-family:'Parimatch', sans-serif; font-size: 30pt; text-shadow:  0 0 7px red"> ${registrationStatus}</h3>


<script>

    function signIncheckEverything() {
        document.getElementById("registrationStatus").style.textShadow = '0 0 7px red'
        var a = loginnValidator();
        var b = validateePassword();
        var c = emailValidate();
        if(a && b && c ) {
            document.getElementById("registrationStatus").style.textShadow = '0 0 7px green'
            console.log("script reg enable!")

            document.getElementById("registrationStatus").innerText = "Success!!!" ;
            var login = document.getElementById("login").value;
            $.ajax({

            })
            $.ajax({
                url: '/main',           /* Куда пойдет запрос */
                method: 'post',
                dataType: 'html',/* Метод передачи (post или get) *//* Тип данных в ответе (xml, json, script, html). */
                data: login, /* Параметры передаваемые в запросе. */
                success: function(data){
                    document.getElementById("registrationStatus").innerText = "Success POST!!!" ;
                    /* функция которая будет выполнена после успешного запроса.  */
                    console.log(data);            /* В переменной data содержится ответ от /products. */
                }

            })
            setTimeout(function (){return true;}, 1000);
        } else {
            return false;

        }


    }

    function loginnValidator(){
        console.log("script loginnValidator!")

        var login = document.getElementById("login").value;

        if(login.length < 4 || login.length > 20)
        { document.getElementById("registrationStatus").innerText ="В логине должно быть от 4 до 20 символов";
            return false;
        } else {
        }
        if(/^[a-zA-Z0-9]+$/.test(login) === false)
        {document.getElementById("registrationStatus").innerText = document.getElementById("registrationStatus").innerText + n +"n\ В логине должны быть только латинские буквы и цифры!";
            return false;
        } else {
        }

        if(!isNaN(parseInt(login.substr(0, 1))))
        {document.getElementById("registrationStatus").innerText ="Логин должен начинаться с буквы";
            return false;
        } else {

        }

        return true;
    }

    function validateePassword(){
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("repassword").value;

        if(password.length < 4 || password.length > 20)
        { document.getElementById("registrationStatus").innerText ="В пароле должно быть от 4 до 20 символов!";
            return false;} else {

        }

        if(confirmPassword.length < 4 || confirmPassword.length > 20) {
            document.getElementById("registrationStatus").innerText ="В пароле должно быть от 4 до 20 символов!";
            return false;
        } else {

        }

        if (password !== confirmPassword) {
            document.getElementById("registrationStatus").innerText = "пароли не совпдают";
            return false;
        } else {

        }
        return true;
    }

    function emailValidate() {
        var email = document.getElementById("email").value;
        var pattern = /^[a-z ]+@[^ ]+\.[a-z]{2,3}$/;
        if (email.match(pattern)) {

        } else {
            document.getElementById("registrationStatus").innerText = "некорректный e-mail адрес!";
            return false;
        }
        return true;
    }
</script>
</body>
</html>


