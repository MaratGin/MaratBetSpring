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