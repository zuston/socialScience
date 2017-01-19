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
<!--header starts-->
<header class="main-header">
    <div class="backbg-color">
        <!--banner starts-->
        <div class="banner-text">
            <div class="container">
                <div class="row">
                    <div class="banner-info text-center">
                        <h2><span class="grey">SHU</span> - SocialScience Platform</h2>
                    </div>
                    <form method="post" action="/search">
                        <div class="banner-search col-md-offset-2 col-md-8 col-md-offset-2">
                            <div class="col-md-3">
                                <select class="form-control sellone select" name="select">
                                    <option value="corrupt">贪腐情况查询</option>
                                    <option value="officer">官员情况查询</option>
                                    <option value="area">市区县往期任职</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <input type="text" class="form-control selltwo" placeholder="enter the search content" name="searchContent">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-btn">
                                    <button type="submit">Search</button>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="nohidden corrupt">
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="option-1-null" hidden=""><a class="tip">干部等级</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="option-1-null" checked><a class="option">无</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="zggb">中管干部
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="sggb">省管干部
                                    </label>
                                    <br>
                                    <div class="col-md-12" style="height:10px;"></div>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="option-1-null" hidden=""><a class="tip">贪腐类型</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio2" id="radio2" value="option-2-null" checked>无
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio2" id="radio2" value="zjsc">执纪审查
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio2" id="radio2" value="djcf">党纪处分
                                    </label>
                                    <br>
                                    <div class="col-md-12" style="height:10px;"></div>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="option-1-null" hidden=""><a class="tip">限制条件</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio3" id="radio3" value="name" checked>姓名
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio3" id="radio3" value="area">省/市/区/县
                                    </label>
                                </div>

                                <div class="hidden officer">
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="name" checked>姓名
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="nativePlace">籍贯
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="position">职位
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="province">省
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="city">市
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="area">区、县
                                    </label>
                                </div>

                                <div class="hidden area">

                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio4" id="radio4" value="cityOption" checked>地级市任职
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio4" id="radio4" value="areaOption">区县任职
                                    </label>
                                    <br>
                                    <div class="col-md-12" style="height:10px;"></div>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="code" checked>地区代码
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="province">省
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="city">市
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="optionRadio1" id="radio" value="area">区县
                                    </label>

                                </div>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</header>


<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <c:choose>
                    <c:when test="${officerMap!=null}">
                        <c:forEach items="${officerMap.get('personBean')}" var="officer">
                            <div class="tableStyle">
                                <p class="baikeName">姓名：<a class="baikeNameName">${officer.name}</a></p>
                                <p class="simpleInfo">${officer.simpleInfo}</p>
                                <div class="row param">
                                    <c:forEach items="${officer.paramDict}" var="dict">
                                        <div class="col-md-4">
                                            <p>${dict}</p>
                                        </div>
                                    </c:forEach>
                                </div>
                                <h3>简历</h3>
                                <div class="resume">
                                    <c:forEach items="${officer.resume}" var="line">
                                        <p>${line}</p>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                        <c:forEach items="${officerMap.get('officerBean')}" var="officer">
                            <div class="tableStyle">
                                <p class="baikeName">姓名：<a class="baikeNameName">${officer.name}</a></p>
                                <p class="simpleInfo">${officer.province},${officer.duty},${officer.position}</p>
                                <div class="row param">
                                    <div class="col-md-4">
                                        <p>生日：${officer.birth}</p>
                                    </div>
                                    <div class="col-md-4">
                                        <p>籍贯：${officer.nativePlace}</p>
                                    </div>
                                    <div class="col-md-4">
                                        <p>性别：${officer.sex}</p>
                                    </div>
                                    <div class="col-md-4">
                                        <p>教育程度：${officer.education}</p>
                                    </div>
                                </div>
                                <h3>简历</h3>
                                <div class="resume">
                                    <p>${officer.resume}</p>
                                </div>
                            </div>
                        </c:forEach>
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





<!--news section-->
<section class="contact" id="contact" style="margin-top:30px;">
    <div class="container">
        <div class="contact-info">
            <div class="col-md-12">
                <div class="col-md-8">
                    <div class="cont-txt">
                        <h3>Contact Us to Sign Up for an Open House</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi vehicula dapibus mauris.</p>
                    </div>
                </div>
                <div class="col-md-4 text-center">
                    <a href="#" class="cont-btn">Contact Us</a>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="footer-line">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <p>Copyright &copy; 2017.SHU All rights reserved.<a href="http://www.cssmoban.com/" target="_blank" title="SocialScience">社会学院</a> </p>
            </div>
        </div>
    </div>
</section>
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
            $("."+initSelectValue+" #radio").attr("name","undefined");
            $("."+changeValue).removeClass("hidden");
            $("."+changeValue+" #radio").attr("name","optionRadio1");
            initSelectValue = changeValue;
        });
    });

</script>

</body>
</html>

