<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zuston
  Date: 17/1/17
  Time: 下午3:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>index</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h2>Social-Science-Platform</h2>

<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">

        <form class="bs-example bs-example-form" role="form" method="post" action="/search">
            <div class="row">
                <div class="col-lg-6">
                    <div class="input-group">
                        <div class="input-group-btn">

                            <select class="form-control btn btn-default dropdown-toggle select" name="select" style="width:140px;" type="button">
                                <option value="corrupt">贪腐情况查询</option>
                                <option value="officer">官员情况查询</option>
                                <option value="area">市区县往期任职</option>
                            </select>

                        </div><!-- /btn-group -->
                        <input type="text" class="form-control" name="searchContent" placeholder="please enter content">
                        <span class="input-group-btn">
                        <input class="btn btn-default" type="submit" value="submit">
                    </span>
                    </div><!-- /input-group -->


                    <div class="nohidden corrupt">
                        <label class="checkbox-inline">
                            <input type="radio" name="optionRadio1" id="radio" value="option-1-null" checked>无
                        </label>
                        <label class="checkbox-inline">
                            <%--中管干部--%>
                            <input type="radio" name="optionRadio1" id="radio" value="zggb">中管干部
                        </label>
                        <label class="checkbox-inline">
                            <%--省管干部--%>
                            <input type="radio" name="optionRadio1" id="radio" value="sggb">省管干部
                        </label>
                        <br>
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
    <div class="col-md-3"></div>
</div>
</body>
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
</html>
