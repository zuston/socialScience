<%--
  Created by IntelliJ IDEA.
  User: zuston
  Date: 17/2/4
  Time: 下午11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="main-header">
    <div class="backbg-color">
        <!--banner starts-->
        <div class="banner-text">
            <div class="container">
                <div class="row">
                    <div class="banner-info text-center">
                        <h2><span class="grey">SHU</span> - SocialScience Platform</h2>
                    </div>
                    <form method="post" action="/search-corrupt" class="form-controller">
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
                            <div class="col-md-12">
                                <div class="nohidden corrupt">
                                    <label class="checkbox-inline">
                                        <input type="radio" id="radio" value="option-1-null" hidden=""><a class="tip">干部等级</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="corrupt-one" id="radio" value="option-1-null" checked><a class="option">无</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="corrupt-one" id="radio" value="zggb"><a class="option">中管干部</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="corrupt-one" id="radio" value="sggb"><a class="option">省管干部</a>
                                    </label>
                                    <br>
                                    <div class="col-md-12" style="height:10px;"></div>
                                    <label class="checkbox-inline">
                                        <input type="radio" id="radio" value="option-1-null" hidden=""><a class="tip">贪腐类型</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="corrupt-two" id="radio2" value="option-2-null" checked><a class="option">无</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="corrupt-two" id="radio2" value="zjsc"><a class="option">执纪审查</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="corrupt-two" id="radio2" value="djcf"><a class="option">党纪处分</a>
                                    </label>
                                    <br>
                                    <div class="col-md-12" style="height:10px;"></div>
                                    <label class="checkbox-inline">
                                        <input type="radio" id="radio" value="option-1-null" hidden=""><a class="tip">限制条件</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="corrupt-three" id="radio3" value="name" checked><a class="option">姓名</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="corrupt-three" id="radio3" value="area"><a class="option">省/市/区/县</a>
                                    </label>
                                </div>

                                <div class="hidden officer">
                                    <label class="checkbox-inline">
                                        <input type="radio" name="officer-one" id="radio" value="name" checked><a class="option">姓名</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="officer-one" id="radio" value="nativePlace"><a class="option">籍贯</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="officer-one" id="radio" value="position"><a class="option">职位</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="officer-one" id="radio" value="province"><a class="option">省</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="officer-one" id="radio" value="city"><a class="option">市</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="officer-one" id="radio" value="area"><a class="option">区、县</a>
                                    </label>
                                </div>

                                <div class="hidden area">
                                    <label class="checkbox-inline">
                                        <input type="radio" id="radio" value="option-1-null" hidden=""><a class="tip">任职地区</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="area-one" id="radio4" value="cityOption" checked><a class="option">地级市任职</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="area-one" id="radio4" value="areaOption"><a class="option">区县任职</a>
                                    </label>
                                    <br>
                                    <div class="col-md-12" style="height:10px;"></div>
                                    <label class="checkbox-inline">
                                        <input type="radio" id="radio" value="option-1-null" hidden=""><a class="tip">搜索选项</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="area-two" id="radio" value="code" checked><a class="option">地区代码</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="area-two" id="radio" value="province"><a class="option">省</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="area-two" id="radio" value="city"><a class="option">市</a>
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="area-two" id="radio" value="area"><a class="option">区县</a>
                                    </label>

                                </div>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
    <!--banner ends-->
</header>

