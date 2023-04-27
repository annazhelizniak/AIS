<%@include file="../headerManager.jsp"%>


<div class="create_a_check">
    <form class="check" action="">
        <fieldset>
            <legend>Чек</legend>

            <label>Дата: <span class="span-name">сьогодняшня дата</span></label> <br>
            <br>
            <label class="good-in-check"><span class="span-name">Toвар</span> - <span class="span-cont">20</span> шт</label><br>
            <label class="good-price-in-check tab">     <span class="span-price">300</span> грн</label><br>
            <br>
            <label>Загальна сума: <span></span></label><br>
            <label>ПДВ: <span></span></label><br>
            <button class="create_check" type="submit" name="create_check">Стоврити чек</button>
            <button class="type_and_create_check" type="submit" name="type_and_create_check">Стоврити та роздрукувати чек</button>
        </fieldset>
    </form>

    <input type="text" id="search_for_check" class="search_for_check" placeholder="Пошук по товарам" required>
    <table class="tableOfGoods_check">
        <tr id="main-row">
            <th>ID</th>
            <th>Назва</th>
            <th>Виробник</th>
            <th>Характеристики</th>
            <th>Категорії</th>
            <th>Ціна</th>
            <th>Кількість одииць</th>
            <th>Є акційним</th>
        </tr>
        <tr onclick="">
            <td>Timely id</td>
            <td>Timely name</td>
            <td>Timely vurobnuk</td>
            <td>Timely ch</td>
            <td>Категорії</td>
            <td>Timely price</td>
            <td>TImely number</td>
            <td><input type="checkbox" readonly></td>
        </tr>
    </table>
</div>
</body>
</html>
