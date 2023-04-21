<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login ZLAGODA</title>
</head>
<style>
    <%@include file="/style/login_page.css"%>
</style>
<body>
<div class="login_page">
    <h2>Вітаємо у системі ZLAGODA</h2>
    <h3>Авторизуйтесь</h3>
    <br/>
    <div class="authorization_block">
        <form class="authorization_form" action="#" method="post">
            <label for="auth_email">Логін</label>
            <input type="email" id="auth_email" placeholder="email або телефон" required >
            <label  for="auth_pass">Пароль</label>
            <input type="password" id="auth_pass" required >
            <button class="form_auth_button" type="submit" name="form_auth_submit">Увійти</button>
        </form>
    </div>
</div>
</body>
</html>