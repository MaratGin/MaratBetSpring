<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/profile.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=David+Libre&display=swap" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Libre+Barcode+128+Text&display=swap" rel="stylesheet">
</head>
<body>
<div class="blur-image">
    <div class="content">
        <h3 class="profile"> Профиль  </h3>
        <!--    <img src="Photos/LoginImage.png" class="blur-image" >-->
        <#if url??>
            <img src="/images/${url}" class="avatar" alt="photo profile">
            <#else >
                <img src="/resources/photos/Task1.png" class="avatar" alt="photo profile">
        </#if>
        <br>
        <form method="post" action="/upload" enctype="multipart/form-data">
        <input type="file" name="file" class ="change" value="Изменить аватар"/>
        <input type="submit" value="Поменять" class="change"/>
        </form>
    </div>

</div>
<div class="date">
    <span class="dateSpan">Дата регистрации:  </span> <span class="dateSpan">${date}</span>
</div>
<div class="win">
    <span class="winSpan">Выиграно ставок:  </span> <span class="winSpan">${win}</span>
</div>
<div class="lose">
    <span class="loseSpan">Проиграно ставок:  </span> <span class="loseSpan">${lose}</span>
</div>
<div class="login">
    <span class="loginSpan">Ваш логин:  </span> <span class="loginSpan">${login}</span>
</div>
<div class="email">
    <span class="loginSpan">Ваш e-mail:  </span> <span class="loginSpan">${email}</span>
</div>
</body>
</html>