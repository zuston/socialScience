<%--
  Created by IntelliJ IDEA.
  User: zuston
  Date: 17/1/19
  Time: 下午2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>社会科学官员检索</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="css/ss.css">
    <script src="jquery.quovolver.min.js"></script>
    <!--[if lt IE 9]-->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <!--[endif]-->
</head>
<body>
<!--header starts-->
<jsp:include page="include/searchInc.jsp" flush="false" />


<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12">

                <c:choose>
                    <c:when test="${not empty error}">
                        <div class="tableStyle">
                            <p class="error">抱歉，${error}</p>
                        </div>
                    </c:when>
                    <c:when test="${corruptBeenList!=null&&fn:length(corruptBeenList)>0}">
                        <c:forEach items="${corruptBeenList}" var="corruptBean">
                            <div class="tableStyle">
                                <p class="title">${corruptBean.title}</p>
                                <p class="time">${corruptBean.time.split(" ")[0]}</p>
                                <p class="time">来源：${corruptBean.resource}</p>
                                <%--<p>--%>
                                    <%--${corruptBean.content}--%>
                                <%--</p>--%>
                                <c:forEach items="${corruptBean.content.split(' ')}" var="line">
                                    <p class="content">${line}</p>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="tableStyle">
                            <p class="error">抱歉，没有找到您要的数据</p>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>


    </div>
</section>

<jsp:include page="include/footer.jsp" flush="false" />


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/owl.carousel.js"></script>
<script src="js/jquery.mixitup.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.quovolver.js"></script>
<!--for smooth scrolling-->
<script>
    $(document).ready(function(){

        var initSelectValue = $(".select").children("option:selected").val();
        $(".select").change(function(){
            var changeValue = $(this).children("option:selected").val();
            $("."+initSelectValue).addClass("hidden").removeClass("nohidden");
//            $("."+initSelectValue+" #radio").attr("name","undefined");
            $("."+changeValue).removeClass("hidden");
//            $("."+changeValue+" #radio").attr("name","optionRadio1");
            $(".form-controller").attr("action","search-"+changeValue);
            initSelectValue = changeValue;
        });
    });

</script>

</body>
</html>
