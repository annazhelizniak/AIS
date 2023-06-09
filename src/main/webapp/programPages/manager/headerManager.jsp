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
</head>
<body>
<!-- Меню-->
<div class="navigation">
  <a class="active" href="profileManager.jsp" onclick="setActiveLink(event, this)">Кабінет користувача</a> <!-- Інформація по менеджера-->
  <a class="" href="workers.jsp" onclick="setActiveLink(event, this)">Працівники</a>
  <a class="" href="managerClients.jsp" onclick="setActiveLink(event, this)">Клієнти</a>
  <a class="" href="category.jsp" onclick="setActiveLink(event, this)">Категорії</a>
  <a class="" href="managerAllGoods.jsp" onclick="setActiveLink(event, this)">Всі товари</a><!-- Всі товари і інформація про них-->
  <a class="" href="managerGoodsInStore.jsp" onclick="setActiveLink(event, this)">Товари магазину</a>
  <a class="" href="managerAllChecks.jsp" onclick="setActiveLink(event, this)">Чеки</a>
  <a id="exit" href="${pageContext.request.contextPath}/index.jsp">Вийти</a>
  <p class="position">Менеджер</p>
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



