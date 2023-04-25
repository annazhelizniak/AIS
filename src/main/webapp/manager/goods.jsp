<%@include file="header.jsp"%>
<div class="goods">
    <h2>Товари, що можуть бути в магазині</h2>
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
            <td><input type="text" value="Timely id"></td>
            <td><input type="text" value="Timely name"></td>
            <td><input type="text" value="Timely vurobnuk"></td>
            <td><input type="text" value="Timely ch"></td>
            <td>
                <select name="Категорії">
                    <option value="value1">Значение 1</option>
                    <option value="value2" >Значение 2</option>
                    <option value="value3">Значение 3</option>
                </select>
            </td>
            <td><input type="checkbox"></td>
            <td><button onclick="" class="editButton">Редагувати</button></td>
            <td><button onclick="" class="deleteButton">Видалити</button></td>
        </tr>
    </table>
</div>
</body>
</html>