<%@include file="headerManager.jsp"%>

<div class="manager-all-checks">
    <h2>Фільтрація чеків</h2>
    <div class="filter check-filter">
        <div class="left-filter">
            <label for="cashier">Якщо цікавлять чеки одного касира введіть його ID</label>
            <input type="text" id="cashier" class="cashier" >
            <label for="data-period1">Введіть період</label>
            <input type="date" id="data-period1" class="data-period" >
            <input type="date" id="data-period2" class="data-period" >
            <button class="searchButton">Пошук</button>
        </div>
        <div class="right-filter">
            <input type="text" id="search_check" class="search" placeholder="Пошук по номеру чеку">
        </div>
    </div>
    <h2>Список чеків</h2>
    <div class="listOfChecks">
        <form class="check" action="">
            <fieldset>
                <legend>Чек <span>код</span></legend>
                <label>Створив: <span class="span-name-cashier">id працівника</span></label> <br>
                <br>
                <label>Дата: <span class="span-name">сьогодняшня дата</span></label> <br>
                <br>
                <label class="good-in-check"><span class="span-name">Toвар</span> - <span class="span-cont">20</span> шт</label><br>
                <label class="good-price-in-check tab">     <span class="span-price">300</span> грн</label><br>
                <br>
                <label>Загальна сума: <span></span></label><br>
                <label>ПДВ: <span></span></label><br>
                <button class="create_check" type="submit" name="create_check">Роздрукувати чек</button>
                <button class="deleteButton" name="deleteButton">Видалити</button>
            </fieldset>
        </form>
    </div>

    <h2>Загальна сума проданих товарів з чеків:</h2>
    <p><span id="allEarnedMoney">3030</span> грн</p>
</div>

</body>
</html>
