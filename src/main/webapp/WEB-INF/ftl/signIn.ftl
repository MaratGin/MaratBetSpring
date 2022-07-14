<!doctype html>
<html lang="en">
<head>
    <title>Войти</title>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/signIn.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=David+Libre&display=swap" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Libre+Barcode+128+Text&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

</head>
<body>
<h1> <div class="MainDiv">MARATBET</div> </h1>
<#--<form action="${pageContext.request.contextPath}/signIn" method="POST"  >-->
    <form  method="post">
        <br>
        <label>
            <input id="email" type="email" name="email" maxlength="55" minlength="5" placeholder="email?" class="login" >
        </label>
        <br>
        <label>
            <input id="password" type="password" name="password" maxlength="15" minlength="5" placeholder="password?" class="password">
        </label>
        <br>
        <input id="submit" type="submit" name="submit" value="Войти" class="submit"  >

<#--        <%--    <button  id="submitBtn"  class="submit" onclick="sendAuth()" > Войти </button>--%>-->
<#--        <%--    <p style="font-size: small"> <label class="Remember" for="rem"> Remember me! </label>  <input  id="rem" type="checkbox" name="Remember me" value="true"> </p>--%>-->
<#--        <%--    <input type="hidden" name="status" class="status">--%>-->
    </form>
    <a class="vk" onclick="location.href='';" href="https://oauth.vk.com/authorize?client_id=7997612&redirect_uri=http://localhost:8080/vk&display=page&v=5.131&scope=status,email">Войти через ВК</a>

    <br>
<#if error??>
    <h3 id="k" style="color: white; text-align: center; font-family:'Parimatch', sans-serif; font-size: 30pt;  text-shadow:  0 0 7px red;"> Неправильный логин или пароль!</h3>
</#if>

    <script>


        function sendAuth() {
            var jjj = document.getElementById("k").innerText = " Here!";

            var login = document.getElementById("login").value;
            var password = document.getElementById("password").value;

            var val = {
                login: login,
                password: password
            };

            $.ajax({
                url: '/signIn',           /* Куда пойдет запрос */
                method: 'post',             /* Метод передачи (post или get) */
                dataType: 'json',          /* Тип данных в ответе (xml, json, script, html). */
                data: {
                    product: JSON.stringify(val) /* Параметры передаваемые в запросе. */
                },
                success: function(data){   /* функция которая будет выполнена после успешного запроса.  */
                    var jjj = document.getElementById("k").innerText = " YES!";

                    alert(data);            /* В переменной data содержится ответ от /products. */
                }

            })

        }
        function sendLogin() {
            var k = document.getElementById("k")
            k.innerText = "123123123"
            var login = document.getElementById("login").value;
            var val = {
                login: login
            }
            $.ajax({
                url: '/main',           /* Куда пойдет запрос */
                method: 'get',             /* Метод передачи (post или get) */
                dataType: 'json',          /* Тип данных в ответе (xml, json, script, html). */
                data: {
                    product: JSON.stringify(val) /* Параметры передаваемые в запросе. */
                },
                success: function(data){   /* функция которая будет выполнена после успешного запроса.  */
                    alert(data);            /* В переменной data содержится ответ от /products. */
                }
            })
            var k = document.getElementById("k")


        }

        function loginValidator(){
            var login = document.getElementById("login").value;

            if(/^[a-zA-Z0-9]+$/.test(login) === false)
            {document.getElementById("registrationStatus").innerText ="В логине должны быть только латинские буквы и цифры!"; return 1;} else {
                document.getElementById("registrationStatus").innerText = ""
            }
            if(login.length < 4 || login.length > 20)
            { document.getElementById("registrationStatus").innerText ="В логине должен быть от 4 до 20 символов"; return 2;} else {
                document.getElementById("registrationStatus").innerText = ""
            }
            if(parseInt(login.substr(0, 1)))
            {document.getElementById("registrationStatus").innerText ="Логин должен начинаться с буквы"; return 3;} else {
                document.getElementById("registrationStatus").innerText = ""
            }
            return 0;
        }

        function validatePassword(){
            var password = document.getElementById("password").value;
            if(/^[a-zA-Z1-9]+$/.test(password) === false)
            {document.getElementById("registrationStatus").innerText ="В пароле должны быть только латинские буквы и цифры!)"; return 4;} else {
                document.getElementById("registrationStatus").innerText = ""

            }
            if(password.length < 4 || password.length > 20)
            { document.getElementById("registrationStatus").innerText ="В пароле должен быть от 4 символов"; return 5;} else {
                document.getElementById("registrationStatus").innerText = ""

            }
            if (password !== confirmPassword) {
                document.getElementById("registrationStatus").innerText = "пароли не совпдают";
                return 6;
            } else {
                document.getElementById("registrationStatus").innerText = ""

            }

        }
    </script>


</body>
</html>
