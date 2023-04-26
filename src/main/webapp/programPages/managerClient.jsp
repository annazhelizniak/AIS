<%@include file="headerManager.jsp"%>

<div class="all-manager-clients">
    <h2>Усі постійні клієнти</h2>

    <div class="filter">
        <div class="left-filter">
            <label for="filter-client">Відфілтрувати за відсотком</label>
            <input type="number" id="filter-client" class="filter-client"><label for="filter-client">%</label>
        </div>
        <div class="right-filter">
            <input type="text" id="search_surname" class="search" placeholder="Пошук по прізвищу">
            <button onclick="document.getElementById('add-client-pop-up').style.display = 'block'" class="addButton ">Додати клієнта</button>
        </div>
    </div>
    <table class="tableOfGoods">
        <tr>
            <th>Номер карти</th>
            <th>ПІБ</th>
            <th>Телефон</th>
            <th>Адреса</th>
            <th>Відсоток</th>
            <th>Редагувати</th>
            <th>Видалити</th>
        </tr>
        <tr>
            <td>Timely id</td>
            <td>Прізвище + Ім'я + побатькові</td>
            <td>тел</td>
            <td>Місто, вул, індекс</td>
            <td>%</td>
            <td><button onclick="" class="editButton">Редагувати</button></td>
            <td><button onclick="" class="deleteButton">Видалити</button></td>
        </tr>
    </table>
</div>

<div id="add-client-pop-up" class="modal">
    <div class="modal-content">
        <span class="close" onclick="document.getElementById('add-client-pop-up').style.display = 'none'">&times;</span>
        <h2>Додавання клієта</h2>
        <form class="information_about_worker" action="">
            <label >ПІБ</label>
            <br>
            <label for="SET-client-surname" class="tab">    Прізвище:</label>
            <input id="SET-client-surname" type="text" name="surname" >
            <br>
            <label for="SET-client-name" class="tab">    Ім'я:</label>
            <input id="SET-client-name" type="text" name="name" >
            <br>
            <label for="SET-client-fathername" class="tab">    Побатькові:</label>
            <input id="SET-client-fathername" type="text" name="fathername" >
            <br><br>
            <label for="SET-client-phone">Телефон:</label>
            <input id="SET-client-phone" type="tel" name="phone" placeholder="+ (380)">
            <br><br>
            <label >Адреса:</label>
            <br>
            <label for="SET-client-city" class="tab">    Місто:</label>
            <input id="SET-client-city" type="text" name="city" >
            <br>
            <label for="SET-client-street" class="tab">    Вулиця:</label>
            <input id="SET-client-street" type="text" name="street" >
            <br>
            <label for="SET-client-index" class="tab">    Індекс:</label>
            <input id="SET-client-index" type="text" name="index" >
            <br> <br>
            <label for="SET-client-persent">Відсоток:</label>
            <input id="SET-client-persent" type="number" name="persent" >
            <br>
            <label for="SET-client-numberOfCard">Номер картки:</label>
            <input id="SET-client-numberOfCard" type="text" name="numberOfCard" placeholder="**** **** **** ****">
            <button class="submit_profile_changes" type="submit" name="submit_profile_changes">Зберегти</button>
        </form>
    </div>
</div>
</body>
</html>