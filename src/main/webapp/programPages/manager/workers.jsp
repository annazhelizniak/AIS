<%@include file="headerManager.jsp"%>

<div class="workers">
    <h2>Працівники магазину</h2>
    <div class="filter">
        <div class="left-filter">
            <label for="only_cashier">Тільки касири</label>
            <input type="checkbox" id="only_cashier">
        </div>
        <div class="right-filter">
            <input type="text" id="search_input" class="search" placeholder="Пошук за прізвищем">
            <button onclick="" class="searchButton">Шукати</button>
            <button onclick="document.getElementById('add-worker-pop-up').style.display = 'block'" class="addButton">Додати працівника</button>
        </div>
    </div>
    <table class="tableOfGoods">
        <tr>
            <th>ID</th>
            <th>ПІБ</th>
            <th>Посада</th>
            <th>Зарплата</th>
            <th>Дата початку роботи</th>
            <th>Дата народження</th>
            <th>Телефон</th>
            <th>Місто</th>
            <th>Вулиця</th>
            <th>Індекс</th>
            <th>Редагувати</th>
            <th>Видалити</th>
        </tr>
        <tr>
            <td>Timely id</td>
            <td>Timely fullname</td>
            <td>Timely касир</td>
            <td>Timely 3000</td>
            <td>дата</td>
            <td>дата</td>
            <td>телефон</td>
            <td>Місто</td>
            <td>Вулиці</td>
            <td>Індекс</td>
            <td><button onclick="" class="editButton">Редагувати</button></td>
            <td><button onclick="" class="deleteButton">Видалити</button></td>
        </tr>
    </table>
</div>

<div id="add-worker-pop-up" class="modal">
    <div class="modal-content">
        <span class="close" onclick="document.getElementById('add-worker-pop-up').style.display = 'none'">&times;</span>
        <h2>Додавання працівника</h2>
        <form action="">
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
            <button class="add_good" type="submit" name="add_good">Додати</button>
        </form>

    </div>
</div>

</body>
</html>