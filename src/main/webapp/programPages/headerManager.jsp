<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Manager ZLAGODA</title>
  <link rel="stylesheet" type="text/css"
        href="../style/menu.css"/>
  <link rel="stylesheet" type="text/css"
        href="../style/profile.css"/>
  <link rel="stylesheet" type="text/css"
        href="../style/allGoods.css"/>
</head>
<body>
<!-- Меню-->
<div class="navigation">
  <a class="active" href="profile.jsp">Кабінет користувача</a> <!-- Інформація по менеджера-->
  <a href="allGoods.jsp">Всі товари</a><!-- Всі товари і інформація про них-->
  <a href="goodsInStore.jsp">Товари магазину</a>
  <a href="createCheck.jsp">Чек</a>
  <a id="exit" href="${pageContext.request.contextPath}/index.jsp">Вийти</a>
  <p class="position">Менеджер</p>
</div>
<!-- Основна частина-->



