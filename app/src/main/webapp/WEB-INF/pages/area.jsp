<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--
  Created by IntelliJ IDEA.
  User: zuston
  Date: 17/1/17
  Time: 下午10:44
  To change this template use File | Settings | File Templates.
--%>
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
                    <c:when test="${areaList!=null}">
                        <c:if test="${areaList.get('cityOption')!=null}">
                            <c:forEach items="${areaList.get('cityOption')}" var="city">
                                <div class="tableStyle">
                                    <p class="baikeName">${city.province}&nbsp;&nbsp;<a class="baikeNameName">${city.city}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地区代码：&nbsp;&nbsp;<a class="baikeNameName">${city.code}</a></p>
                                    <p class="simpleInfo"></p>

                                    <h3>地级市任职情况</h3>
                                    <c:forEach items="${city.mayor}" var="me">
                                        <div class="resume areaResume">
                                            <p><a class="year">${me.value.year.toString()}</a> <a class="name">${me.value.name}</a></p>
                                            <div class="row param">
                                                <div class="col-md-6">
                                                    <p>年龄 ： ${me.value.age.toString()}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>教育程度 ： ${me.value.education.toString()}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>任职来源 ： ${me.value.origin}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>籍贯 ： ${me.value.nativePlace}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>任职时省长籍贯 ： ${me.value.provinceGovNativePlace}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>任职时省长 ： ${me.value.provinceGovName}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>任职来源 ： ${me.value.beforeExperience}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>卸任去向 ： ${me.value.afterExperience}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>生日 ： ${me.value.birth}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>任职时省委书记 ： ${me.value.provincePartyName}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>任职时省委籍贯 ： ${me.value.provincePartyNativePlace}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>卸任去向 ： ${me.value.afterExperience}</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>生日 ： ${me.value.birth}</p>
                                                </div>
                                            </div>
                                        </div>

                                    </c:forEach>

                                </div>
                            </c:forEach>
                        </c:if>


                        <c:if test="${areaList.get('countyOption')!=null}">
                            <c:forEach items="${areaList.get('countyOption')}" var="county">
                                <div class="tableStyle">
                                    <p class="baikeName">${county.province}&nbsp;&nbsp;<a class="baikeNameName">${county.city}</a>&nbsp;&nbsp;${county.area}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地区代码：&nbsp;&nbsp;<a class="baikeNameName">${county.code}</a></p>

                                        <h3>区/县市任职情况</h3>
                                        <h4>党委任职</h4>
                                        <c:forEach items="${county.countyPartyPersonHashMap}" var="me">
                                            <div class="resume areaResume">
                                            <p>
                                                <a class="year">${me.key}</a>
                                                <a class="name">${me.value.name}</a>
                                            </p>
                                            <div class="row param">
                                                <div class="col-md-4">
                                                    <p>年龄 ： ${me.value.age}</p>
                                                </div>
                                                <div class="col-md-4">
                                                    <p>来源 ： ${me.value.origin}</p>
                                                </div>
                                                <div class="col-md-4">
                                                    <p>卸任去向 ： ${me.value.after}</p>
                                                </div>
                                                <div class="col-md-4">
                                                    <p>任职之前 ： ${me.value.before}</p>
                                                </div>
                                            </div>
                                            </div>
                                        </c:forEach>
                                    <h4>政府任职</h4>
                                        <c:forEach items="${city.countyGovPersonHashMap}" var="me">
                                        <div class="resume areaResume">
                                        <p><a class="year">${me.value.year}</a> <a class="name">${me.value.name}</a></p>
                                        <div class="row param">
                                        <div class="col-md-4">
                                        <p>年龄 ： ${me.value.age}</p>
                                        </div>
                                        <div class="col-md-4">
                                        <p>来源 ： ${me.value.origin}</p>
                                        </div>
                                        <div class="col-md-4">
                                        <p>卸任去向 ： ${me.value.after}</p>
                                        </div>
                                        <div class="col-md-4">
                                        <p>任职之前 ： ${me.value.before}</p>
                                        </div>
                                        </div>
                                        </div>
                                        </c:forEach>

                                </div>
                            </c:forEach>
                        </c:if>

                        <c:if test="${empty areaList}">
                            <div class="tableStyle">
                                <p class="error">没有找到您要的数据</p>
                            </div>
                        </c:if>

                    </c:when>
                    <c:otherwise>
                        <div class="tableStyle">
                            <p class="error">没有找到您要的数据</p>
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


