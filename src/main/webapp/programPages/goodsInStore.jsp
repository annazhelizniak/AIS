<%@include file="headerManager.jsp"%>

<div class="goods_in_store">
    <h2>Товари, що є в наявності</h2>

    <div class="filter">
        <div class="left-filter">
            <p>Сортувати за</p>
            <select name="filter">
                <option value="name">Назвою</option>
                <option value="producer" >К-сть одиниць</option>
            </select>
            <p>Є акційним:</p>
            <select name="filter">
                <option value="all">Всі</option>
                <option value="sale" >Акційні</option>
                <option value="nosale" >Не акційні</option>
            </select>
        </div>
        <div class="right-filter">
            <input type="text" id="search_input" class="search_input" placeholder="Пошук по товарам" required >
        </div>
    </div>
    <table class="tableOfGoods">
        <tr>
            <th>UPC</th>
            <th>Назва</th>
            <th>Виробник</th>
            <th>Характеристики</th>
            <th>Категорії</th>
            <th>Ціна</th>
            <th>Кількість одииць</th>
            <th>Є акційним</th>
            <th>Редагувати</th>
            <th>Видалити</th>
        </tr>
        <tr>
            <td>Timely id</td>
            <td>Timely name</td>
            <td>Timely vurobnuk</td>
            <td>Timely ch</td>
            <td>Категорії</td>
            <td>Timely price</td>
            <td>TImely number</td>
            <td><input type="checkbox" readonly></td>
            <td><button onclick="" class="editButton">Редагувати</button></td>
            <td><button onclick="" class="deleteButton">Видалити</button></td>
        </tr>
    </table>
</div>

<div id="add-pop-up" class="modal">
    <div class="modal-content">
        <span class="close" onclick="document.getElementById('add-pop-up').style.display = 'none'">&times;</span>
        <h2>Додавання товару</h2>
        <form>
            <label for="name">Назва:</label>
            <input type="text" id="name" name="name" required><br><br>
            <label for="manufacturer">Виробник:</label>
            <input type="text" id="manufacturer" name="manufacturer" required><br><br>
            <label for="features">Характеристики:</label>
            <textarea id="features" name="features" rows="4" cols="50"></textarea><br><br>
            <label for="categories">Категорії:</label>
            <input type="text" id="categories" name="categories" required><br><br>
            <label for="availability">Є в наявності:</label>
            <input type="checkbox" id="availability" name="availability" readonly><br><br>
            <div id="additional-fields">
                <label for="price">Ціна товару у грн:</label>
                <input type="number" id="price" name="price"><br><br>
                <label for="quantity">Кількість одиниць:</label>
                <input type="number" id="quantity" name="quantity"><br><br>
                <label for="promo">Чи є товар акційним?</label>
                <input type="checkbox" id="promo" name="promo" value="yes">
            </div><br><br>
            <button class="add_good" type="submit" name="add_good">Додати</button>
        </form>
    </div>
</div>

</body>
</html>