<%@include file="headerManager.jsp"%>

<div class="manager-all-goods">
  <h2>Товари, що можуть бути в магазині</h2>

  <div class="filter">
    <div class="left-filter">
      <label for="filter">Категорія</label>
      <select name="filter" id="filter">
        <option value="all">будь-яка</option>
        <option value="1" >____</option>
      </select>
    </div>
    <div class="right-filter">
      <input type="text" id="search_input" class="search" placeholder="Пошук по товарам">
      <button onclick="" class="searchButton">Шукати</button>
      <button onclick="document.getElementById('add-pop-up').style.display = 'block'" class="addButton">Додати товар</button>
    </div>
  </div>
  <table class="tableOfGoods">
    <tr>
      <th>ID</th>
      <th>Назва</th>
      <th>Виробник</th>
      <th>Характеристики</th>
      <th>Категорії</th>
      <th>Є в наявності</th>
      <th>Редагувати</th>
      <th>Видалити</th>
    </tr>
    <tr>
      <td>Timely id</td>
      <td>Timely name</td>
      <td>Timely vurobnuk</td>
      <td>Timely ch</td>
      <td>Категорії</td>
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
      <input type="checkbox" id="availability" name="availability" value="yes"><br><br>
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