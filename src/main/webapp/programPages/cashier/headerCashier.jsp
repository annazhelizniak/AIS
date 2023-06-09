<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Manager ZLAGODA</title>
  <link rel="stylesheet" type="text/css"
        href="../../style/menu.css"/>
  <link rel="stylesheet" type="text/css"
        href="../../style/profile.css"/>
  <link rel="stylesheet" type="text/css"
        href="../../style/allGoods.css"/>
  <link rel="stylesheet" type="text/css"
        href="../../style/check.css"/>
</head>
<body>
<!-- Меню-->
<div class="navigation">
  <a class="active" href="profileCashier.jsp" onclick="setActiveLink(event, this)">Кабінет користувача</a> <!-- Інформація по касира-->
  <a class="" href="allGoods.jsp" onclick="setActiveLink(event, this)">Всі товари</a>
  <a class="" href="goodsInStore.jsp" onclick="setActiveLink(event, this)">Товари магазину</a>
  <a class="" href="clients.jsp" onclick="setActiveLink(event, this)">Клієнти</a>
  <a class="" href="allChecks.jsp" onclick="setActiveLink(event, this)">Чеки</a>
  <a class="" href="createCheck.jsp" onclick="setActiveLink(event, this)">Створити чек</a>
  <a id="exit" href="${pageContext.request.contextPath}/index.jsp">Вийти</a>
  <p class="position">Касир</p>
</div>
<!--Керування стилями меню-->
<script>
  function setActiveLink(event, link) {
    event.preventDefault(); // Відмінити дію за замовчуванням (перезавантаження сторінки)
    const links = document.querySelectorAll('a');
    links.forEach(link => {
      link.classList.remove('active');
    });

    link.classList.add('active');
  }
</script>
<!-- Основна частина-->



