<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MARATBET💜</title>
    <link rel="stylesheet" type="text/css" href="css/mainPage.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Russo+One&display=swap" rel="stylesheet">



    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab&display=swap" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Libre+Barcode+128+Text&display=swap" rel="stylesheet">
</head>
<body>
<div class="headerr">
    <a class="Mainlogo" href="/">
        MARATBET
    </a>
    <a   href="/balance" class="balance" >
        Баланс:<span id="balance">${balance}</span> MRT
    </a>
    <a id="name" href="" class="history">
        ${name}
    </a>
    <a class="toMain" href="${pageContext.request.contextPath}/introduction">
        На главную
    </a>
    <hr class="headerLine">

</div>

<#--<%--private int id;--%>-->
<#--<%--private String teamOneLogo;--%>-->
<#--<%--private String teamOneName;--%>-->
<#--<%--private double teamOneK;--%>-->
<#--<%--private String teamTwoLogo;--%>-->
<#--<%--private String teamTwoName;--%>-->
<#--<%--private double teamTwoK;--%>-->
<#--<%--private String date;--%>-->
<#--<%--private String time;--%>-->
<#--<%--private double drawK;--%>-->
<div class="content">
    <span class="current" > Предстоящие матчи: </span>
    <#foreach match in matches>
        <table>
            <tr>

                <td class="match">
                    <div class="team1">
                        <img class="team1" src="${match.teamOneLogo}"/> " alt="team1"/>
                        <span id="teamOne" class="team1">${match.teamOneName}</span>
                        <input type="submit" name="submit" value="П1=${match.teamOneK}" class="firstWin" onclick="makeBetP1()">

                    </div>


                    <div class="matchInfo">
                        <span  class="matchId">Матч номер <span id="matchId">${match.id}</span> </span>
                        <label>
                            <input id="summ" type="number" name="Value" maxlength="15" minlength="5" placeholder="Сумма?" class="value">
                        </label>
                        <span class="date">${match.date}</span>
                        <br>
                        <span class="time">${match.time}</span>
                        <br>
                        <input type="submit" name="submit" value="Н=${match.drawK}" class="draw" onclick="makeBetD()">
                    </div>
                    <div class="team2">
                        <img class="team2" src="${match.teamTwoLogo}"  alt="team2"/>
                        <span id="TeamTwo" class="team2">${match.teamTwoName}</span>

                        <input type="submit" name="submit" value="П2=${match.teamTwoK}" class="secondWin" onclick="makeBetP2()">

                    </div>
                </td>
            </tr>
        </table>

    </#foreach>

</div>
<script>
    function makeBetP1(){
        var value = parseFloat(document.getElementById("summ").value);
        var matchId = parseInt(document.getElementById("matchId").innerHTML);
        var balance = parseFloat(document.getElementById("balance").innerHTML);
        if (Number(value) === undefined || Number(value) === null){
            alert("Ошибка, введите корректное значение1 " + value);
        } else {
            if (parseFloat(balance) - value > 0) {
                if (value > 0) {

                    var myBet = {
                        userId: 1,
                        matchId: matchId,
                        outcome: 1,
                        value: value
                    };

                    $.ajax({
                        url: '/bet',           /* Куда пойдет запрос */
                        method: 'post',             /* Метод передачи (post или get) */
                        dataType: 'json',          /* Тип данных в ответе (xml, json, script, html). */
                        data: {
                            myBet: JSON.stringify(myBet) /* Параметры передаваемые в запросе. */
                        },
                        success: function (data) {   /* функция которая будет выполнена после успешного запроса.  */


                            /* В переменной data содержится ответ от /products. */
                        }
                    });
                    document.getElementById("balance").innerHTML = String(balance - value);
                    alert("Ставка сделана! \n" + "Победа" + document.getElementById("teamOne").innerHTML + "\n" + "Cумма:" + value);
                    document.getElementById("balance").innerHTML = String(balance - value);
                } else {
                    alert("Ошибка, введите корректное значение2 " + value);
                }
            } else {

                alert("Ошибка, введите корректное значение3 " + k + " / " + balance + " / "+ value);
            }
        }
    }
    function makeBetP2(){
        var value = parseFloat(document.getElementById("summ").value);
        var matchId = parseInt(document.getElementById("matchId").innerHTML);
        var balance = parseFloat(document.getElementById("balance").innerHTML);
        if (Number(value) === undefined || Number(value) === null){
            alert("Ошибка, введите корректное значение1 " + value);
        } else {
            if (parseFloat(balance) - value > 0) {
                if (value > 0) {

                    var myBet = {
                        userId: 1,
                        matchId: matchId,
                        outcome: 2,
                        value: value
                    };

                    $.ajax({
                        url: '/bet',           /* Куда пойдет запрос */
                        method: 'post',             /* Метод передачи (post или get) */
                        dataType: 'json',          /* Тип данных в ответе (xml, json, script, html). */
                        data: {
                            myBet: JSON.stringify(myBet) /* Параметры передаваемые в запросе. */
                        },
                        success: function (data) {   /* функция которая будет выполнена после успешного запроса.  */


                            /* В переменной data содержится ответ от /products. */
                        }
                    });
                    document.getElementById("balance").innerHTML = String(balance - value);
                    alert("Ставка сделана! \n" + "Победа" + document.getElementById("teamTwo").innerHTML + "\n" + "Cумма:" + value);
                    document.getElementById("balance").innerHTML = String(balance - value);
                } else {
                    alert("Ошибка, введите корректное значение2 " + value);
                }
            } else {

                alert("Ошибка, введите корректное значение3 " + k + " / " + balance + " / "+ value);
            }
        }
    }
    function makeBetD(){
        var value = parseFloat(document.getElementById("summ").value);
        var matchId = parseInt(document.getElementById("matchId").innerHTML);
        var balance = parseFloat(document.getElementById("balance").innerHTML);
        if (Number(value) === undefined || Number(value) === null){
            alert("Ошибка, введите корректное значение1 " + value);
        } else {
            if (parseFloat(balance) - value > 0) {
                if (value > 0) {

                    var myBet = {
                        userId: 1,
                        matchId: matchId,
                        outcome: 2,
                        value: value
                    };

                    $.ajax({
                        url: '/bet',           /* Куда пойдет запрос */
                        method: 'post',             /* Метод передачи (post или get) */
                        dataType: 'json',          /* Тип данных в ответе (xml, json, script, html). */
                        data: {
                            myBet: JSON.stringify(myBet) /* Параметры передаваемые в запросе. */
                        },
                        success: function (data) {   /* функция которая будет выполнена после успешного запроса.  */


                            /* В переменной data содержится ответ от /products. */
                        }
                    });
                    document.getElementById("balance").innerHTML = String(balance - value);
                    alert("Ставка сделана! \n" + "Ничья" + document.getElementById("teamTwo").innerHTML + "\n" + "Cумма:" + value);
                    document.getElementById("balance").innerHTML = String(balance - value);
                } else {
                    alert("Ошибка, введите корректное значение2 " + value);
                }
            } else {

                alert("Ошибка, введите корректное значение3 " + k + " / " + balance + " / "+ value);
            }
        }
    }
</script>
<%--<script>--%>
    <#--<%--    function sendPOSTMain--%>-->
    <#--<%--</script>--%>-->

</body>
</html>
