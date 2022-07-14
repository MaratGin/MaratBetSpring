<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MARATBET💜</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/chat.css">
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

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body onload="connect('${id}')">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />

<div class="headerr">
    <a class="Mainlogo" href="/">
        MARATBET
    </a>
    <a id="name" href="" class="history">
        ${name}
    </a>
    <a class="toMain" href="/introduction">
        На главную
    </a>
    <a class="toMain" href="/mainPage">
       К матчам
    </a>
    <a class="toMain" href="/logout">
        Выйти!
    </a>
    <hr class="headerLine">
</div>
<div class="container" style="position: absolute;
    color: #ffffff;
    top: 100px;
    height: 820px;
    right: 150px;
    left: 150px;
    bottom: 0;
    z-index: 0;">
    <div class="row clearfix">
        <div class="col-lg-12">
            <div class="card chat-app">
                <div class="chat">
                    <div class="chat-header clearfix">
                        <div class="row" style="height: 50px;">
                            <span style="color: black; font-family: 'Parimatch Regular', 'Helvetica',serif; font-size: 28pt;">MARATBET</span>
                            <button style="width: 150px; height: 35px; margin-left: 20px;" onclick="sendMessage('${id}','Присоединился!')">Подключиться!</button>
                        </div>
                    </div>
                    <div class="chat-history">
                        <ul class="m-b-0" id="messagesList">

                        </ul>
                    </div>
                    <div class="chat-message clearfix">
                        <div class="input-group mb-0">
                            <div class="input-group-prepend" >
                                <button onclick="sendMessage('${id}', $('#message').val())" class="input-group-text"><i class="fa fa-send"></i></button>
                            </div>
                            <input type="text" name="message" id="message" class="form-control" placeholder="Enter text here...">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var webSocket;

    function connect(id) {
        document.cookie = 'X-Authorization=' + '123456' + ';path=/';
        webSocket = new WebSocket('ws://localhost:8080/chatt');
        console.log("HERE!")

        webSocket.onmessage = function receiveMessage(response) {
            let data = response['data'];
            let json = JSON.parse(data);
            $('#messagesList').last().before("<li class='clearfix'>" + "<div class='message-data'>" + "<span class='message-data-time'>"+ json['from']+ "</span>"+ "</div>" +"<div class='message my-message'>" +  json['text'] +" </div>"+"</li>")
        };

        webSocket.onerror = function errorShow() {
            alert('Ошибка авторизации')
        }
    }

    function sendMessage(from, text) {
        let message = {
            "from": from,
            "text": text
        };

        webSocket.send(JSON.stringify(message));
    }
</script>

</body>
</html>
