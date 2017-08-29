<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/sales.css"/>
    <script src="${contextPath}/resources/js/jquery-3.1.1.js"></script>
    <title>Title</title>
</head>
<body>
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
                    <span><i class="icon-minus-sign"></i> Разное </span>
                </li>
                <li>
                    <span><i class="icon-minus-sign"></i> Мягкие игрушки/Куклы </span>
                </li>
                <li>
                    <span><i class="icon-minus-sign"></i> Конструкторы LEGO </span>
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
 <div style="float: left; width: 85%" >
     <table class="table" id="mytab" >

         <tbody id="mybody">

         </tbody>
     </table>
 </div>
<script>
    function playingSet(category) {
        $.ajax({
            type: "GET",
            url: "/get_goods/" + category,

            dataType: "text",
            success: function (data) {
                var obj = JSON.parse(data);
                console.log(data);
                $('#mybody').html('');
                for (var i = 0; i < 13; i++) {
                    newrow = document.all.mybody.insertRow();
                    newcellSku = newrow.insertCell(0);
                    newcellSku.style.width = 50;
                    newcellSku.innerText = obj[i].sku;
                    newcellName = newrow.insertCell(1);
                    newcellName.style.width = 1200;
                    newcellName.style.marginLeft = 10;
                    newcellName.innerText = obj[i].name;
                    newcellPrice = newrow.insertCell(2);
                    newcellPrice.style.marginRight = 10;
                    newcellPrice.innerText = obj[i].price;
                }
            }
        });
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
