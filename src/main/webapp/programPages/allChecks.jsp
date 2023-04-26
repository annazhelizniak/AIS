<%@include file="headerManager.jsp"%>

<div class="all-checks">
    <div class="filter check-filter">
        <div class="left-filter">
            <label for="filter">Відфільтрувати за</label>
            <select name="filter" id="filter">
                <option value="name">Дата створення</option>
                <option value="producer" >Сумма</option>
            </select>
        </div>
        <div class="right-filter">
            <input type="text" id="search_check" class="search_check" placeholder="Пошук по чакам" required >
        </div>
    </div>
    <h2>Список чеків</h2>
    <div class="listOfChecks">
        <form class="check" action="">
            <fieldset>
                <legend>Чек <span>код</span></legend>

                <label>Дата: <span class="span-name">сьогодняшня дата</span></label> <br>
                <br>
                <label class="good-in-check"><span class="span-name">Toвар</span> - <span class="span-cont">20</span> шт</label><br>
                <label class="good-price-in-check tab">     <span class="span-price">300</span> грн</label><br>
                <br>
                <label>Загальна сума: <span></span></label><br>
                <label>ПДВ: <span></span></label><br>
                <button class="create_check" type="submit" name="create_check">Роздрукувати чек</button>
            </fieldset>
        </form>
    </div>
</div>

</body>
</html>