<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="fragments/header.jsp"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/sales.css"/>
    <script src="${contextPath}/resources/js/jquery-3.1.1.js"></script>
    <title>Title</title>
</head>
<div class="tree well" style="width: 15%; height: 100%; float: left">
    <ul style="padding-left: 1%">
        <li>
            <span><i class="icon-folder-open"></i> Товары для детей </span>
            <ul>
                <li>
                    <a href="list.jsp">
                        <span onclick="playingSet(1); return false">Игровые наборы</span>
                    </a>
                </li>
                <li>
                    <a href="list.jsp">
                        <span onclick="playingSet(2); return false">Радиоуправляемые модели</span>
                    </a>
                </li>
                <li>
                    <a href="list.jsp">
                        <span onclick="playingSet(3); return false">Разное</span>
                    </a>
                </li>
                <li>
                    <a href="list.jsp">
                        <span onclick="playingSet(4); return false">Мягкие игрушки/Куклы</span>
                    </a>
                </li>
                <li>
                    <a href="list.jsp">
                        <span onclick="playingSet(5); return false">Настольные игры</span>
                    </a>
                </li>
                <li>
                    <a href="list.jsp">
                        <span onclick="playingSet(6); return false">Конструкторы LEGO</span>
                    </a>
                </li>
            </ul>
        </li>
        <%--<li>--%>
        <%--<span><i class="icon-folder-open"></i> Parent2</span> <a href="">Goes somewhere</a>--%>
        <%--<ul>--%>
        <%--<li>--%>
        <%--<span><i class="icon-leaf"></i> Child</span> <a href="">Goes somewhere</a>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<span><i class="icon-minus-sign"></i> Настольные игры </span> <a href="">Goes somewhere</a>--%>
        <%--<ul>--%>
        <%--<li>--%>
        <%--<span><i class="icon-leaf"></i> Конструкторы LEGO </span> <a href="">Goes somewhere</a>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<span><i class="icon-minus-sign"></i> Grand Child</span> <a href="">Goes somewhere</a>--%>
        <%--<ul>--%>
        <%--<li>--%>
        <%--<span><i class="icon-minus-sign"></i> Great Grand Child</span> <a href="">Goes somewhere</a>--%>
        <%--<ul>--%>
        <%--<li>--%>
        <%--<span><i class="icon-leaf"></i> Great great Grand Child</span> <a href="">Goes somewhere</a>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<span><i class="icon-leaf"></i> Great great Grand Child</span> <a href="">Goes somewhere</a>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<span><i class="icon-leaf"></i> Great Grand Child</span> <a href="">Goes somewhere</a>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<span><i class="icon-leaf"></i> Great Grand Child</span> <a href="">Goes somewhere</a>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<span><i class="icon-leaf"></i> Grand Child</span> <a href="">Goes somewhere</a>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <%--</li>--%>
    </ul>
</div>
<div style="float: left; width: 85%; height: 50%; display: block; overflow: scroll">
    <table class="table" id="mytab">

        <tbody id="mybody">

        </tbody>
    </table>
</div>

<div id="order" style=" width: 85%; height: 12%; margin-left: 15%; margin-top: 26%; position: relative">
    <hr>
    <div class="form-group ">
        <div class="col-sm-2">
            <input type="text" class="form-control " id="mobile_phone" placeholder="Моб. телефон"/><br>
            <input type="text" class="form-control " id="email" placeholder="Email"/>
        </div>
    </div>
    <div class="form-group ">
        <div class="col-sm-2" style="float: left">
            <input type="text" class="form-control " id="lastName" placeholder="Фамилия"/><br>
            <input type="text" class="form-control " id="name" placeholder="Имя"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-2" style="float: left">
            <select class="form-control" id="selDelivery">
                <option>Офис</option>
                <option>Минск</option>
                <option>Почта</option>
                <option>Курьер Бай</option>
            </select><br>
            <input type="text" class="form-control " id="address" placeholder="Адрес"/><br>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-2" style="float: left">
            <select class="form-control" id="selDay">
                <option>Понедельник</option>
                <option>Вторник</option>
                <option>Среда</option>
                <option>Четверг</option>
                <option>Пятница</option>
                <option>Суббота</option>
                <option>Воскресенье</option>
            </select>
            <label class="checkbox-inline"><input style="margin-top: 15% " type="checkbox" value="">Оплата
                картой</label><br>
            <label class="checkbox-inline"><input type="checkbox" value="">Утро</label>
        </div>
    </div>
</div>
<hr>
<div style="float: left; display: block; width: 85%; height: 35%; overflow: scroll">
    <table class="table" id="tableOrder">

        <tbody id="bodyOrder" >

        </tbody>
    </table>
</div>
<script>
    myArray = [];
    function playingSet(category) {
        $.ajax({
            type: "GET",
            url: "/get_goods/" + category,
            dataType: "text",
            success: function (data) {
                var obj = JSON.parse(data);
                var count = Object.keys(obj).length;
                $('#mybody').html('');
                for (var i = 0; i < count; i++) {
                    newrow = document.all.mybody.insertRow();
                    newrow.style.height = 10;
                    newcellSku = newrow.insertCell(0);
                    newcellSku.style.width = 50;
                    newcellSku.style.height = 10;
                    newcellSku.innerText = obj[i].sku;
                    newcellName = newrow.insertCell(1);
                    newcellName.style.width = 1200;
                    newcellName.style.marginLeft = 10;
                    newcellName.prototype = obj[i];
                    newcellName.innerText = obj[i].name;
                    newcellName.addEventListener('click', function (wrapTD) {
                            return function () {
                                order = {};
                                order.sku = wrapTD.prototype.sku;
                                order.name = wrapTD.prototype.name;
                                order.priceValue = wrapTD.prototype.price;
                                order.amount = 1;
                                check = false;
                                if (myArray.length !== 0){
                                    for(var i = 0; i <= myArray.length-1; i++){
                                        if(myArray[i].sku === order.sku){
                                            myArray[i].amount++;
                                            check = true;
                                        }
                                    }
                                    if (check === true){
                                        selectGoods(myArray);
                                    }else {
                                        myArray.push(order);
                                        selectGoods(myArray);
                                    }
                                    console.log(myArray);
                                }else {
                                    myArray.push(order);
                                    selectGoods(myArray);
                                }

                            }
                        }
                        (newcellName)
                    );
                    newcellPrice = newrow.insertCell(2);
                    newcellPrice.style.marginRight = 10;
                    newcellPrice.innerText = obj[i].price;
                }
            }
        })
        ;
    }

    function selectGoods(myArray) {
        $("#bodyOrder").children().remove()
        for(var i = 0; i <= myArray.length-1; i++) {
            newrow = document.all.tableOrder.insertRow();
            newcellSku = newrow.insertCell(0);
            newcellSku.innerText = myArray[i].sku;
            newcellName = newrow.insertCell(1);
            newcellName.innerText = myArray[i].name;
            newcellMinus = newrow.insertCell(2);
            newcellMinus.style.width = 5;
            newcellMinus.style.paddingRight = 0;
            newcellMinus.id = i;
            newcellMinus.innerHTML = newcellMinus.innerHTML + " <button id='minus'  type='button' data-type='minus' class='btn btn-default btn-number' onclick=''>-</button><br>";
            newcellMinus.addEventListener('click', function () {
                    return function () {
                        myArray[this.id].amount--;
                        $('.this.i').on('click', function () {
                            $('td#this.i').remove();
                        });
                        newcellAmount.innerText = myArray[this.id].amount;
                    }
                }
                (newcellMinus)
            );
            newcellAmount = newrow.insertCell(3);
            newcellAmount.id = this.i;
            newcellAmount.style.width = 5;
            newcellAmount.innerText = myArray[i].amount;
            newcellPlus = newrow.insertCell(4);
            newcellPlus.style.width = 5;
            newcellPlus.style.paddingLeft = 0;
            newcellPlus.id = i;
            newcellPlus.innerHTML = newcellPlus.innerHTML + " <button type='button' data-type='plus' class='btn btn-default btn-number' >+</button><br>";
            newcellPlus.addEventListener('click', function () {
                    return function () {
                        console.log(this.id);
                        myArray[this.id].amount++;
                        $('.this.id').on('click', function () {
                            $('td#this.id').remove();
                        });
                        newcellAmount.innerText = myArray[this.id].amount;
                    }
                }
                (newcellPlus)
            );
            newcellPrice = newrow.insertCell(5);
            newcellPrice.innerText = myArray[i].priceValue;
        }
    }


    $(function () {
        $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
        $('.tree li.parent_li > span').on('click', function (e) {
            var children = $(this).parent('li.parent_li').find(' > ul > li');
            if (children.is(":visible")) {
                children.hide('fast');
                $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
            } else {
                children.show('fast');
                $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
            }
            e.stopPropagation();
        });
    });
</script>
</body>
</html>
