<%@include file="headerManager.jsp"%>

<div class="profile">
    <h2>Вітаємо у системі</h2>
    <form class="information_about_worker" action="">
        <fieldset>
            <legend>Ваші данні</legend>
            <label >ПІБ</label>
            <br>
            <label for="GET-surname" class="tab">    Прізвище:</label>
            <input id="GET-surname" type="text" name="surname" readonly>
            <br>
            <label for="GET-name" class="tab">    Ім'я:</label>
            <input id="GET-name" type="text" name="name" readonly>
            <br>
            <label for="GET-fathername" class="tab">    Побатькові:</label>
            <input id="GET-fathername" type="text" name="fathername" readonly>
            <br><br>
            <label for="GET-dateOfBirth">Дата народження:</label>
            <input id="GET-dateOfBirth" type="date" name="dateOfBirth" readonly>
            <br><br>
            <label for="GET-salary">Зарплата:</label>
            <input id="GET-salary" type="number" name="salary" readonly>
            <br><br>
            <label for="GET-dateOfStart">Дата початку роботи:</label>
            <input id="GET-dateOfStart" type="date" name="dateOfStart" readonly>
            <br><br>
            <label for="GET-phone">Телефон:</label>
            <input id="GET-phone" type="tel" name="phone" readonly>
            <br><br>
            <label >Адреса:</label>
            <br>
            <label for="GET-city" class="tab">    Місто:</label>
            <input id="GET-city" type="text" name="city" readonly>
            <br>
            <label for="GET-street" class="tab">    Вулиця:</label>
            <input id="GET-street" type="text" name="street" readonly>
            <br>
            <label for="GET-index" class="tab">    Індекс:</label>
            <input id="GET-index" type="text" name="index" readonly>
            <br>
            <br>
         </fieldset>
    </form>
</div>
</body>
</html>