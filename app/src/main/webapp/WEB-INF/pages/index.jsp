<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: zuston
  Date: 17/1/17
  Time: 下午3:56
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>社会科学官员检索</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link href="js/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="css/ss.css">
    <script src="js/jquery.quovolver.min.js"></script>
    <!--[if lt IE 9]-->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <!--[endif]-->
</head>
<body>
<!--header starts-->

<jsp:include page="include/searchInc.jsp" flush="false" />

<jsp:include page="include/footer.jsp" flush="true" />

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
