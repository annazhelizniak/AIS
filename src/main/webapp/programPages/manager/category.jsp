<%@include file="headerManager.jsp"%>

<div class="categories">
    <h2>Усі категорії товарів</h2>

    <div class="filter">
        <div class="left-filter">
        </div>
        <div class="right-filter">
            <button onclick="document.getElementById('add-category-pop-up').style.display = 'block'" class="addButton ">Додати категорію</button>
        </div>
    </div>
    <table class="tableOfGoods">
        <tr>
            <th>ID</th>
            <th>Назва категорії</th>
            <th>Редагувати</th>
            <th>Видалити</th>
        </tr>
        <tr>
            <td>Timely id</td>
            <td>Timely name</td>
            <td><button onclick="" class="editButton">Редагувати</button></td>
            <td><button onclick="" class="deleteButton">Видалити</button></td>
        </tr>
    </table>
</div>

<div id="add-category-pop-up" class="modal">
    <div class="modal-content">
        <span class="close" onclick="document.getElementById('add-category-pop-up').style.display = 'none'">&times;</span>
        <h2>Додавання категорії</h2>
        <form>
            <label for="name">Назва:</label>
            <input type="text" id="name" name="name" required><br><br>
            <button class="add_good" type="submit" name="add_good">Додати</button>
        </form>
    </div>
</div>

</body>
</html>