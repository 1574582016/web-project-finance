$(function(){
    var menuNameJust = window.localStorage.getItem("menuName");
    if(typeof menuNameJust == "undefined" || menuNameJust == null || menuNameJust == ""){
        window.location.href="/home";
        window.localStorage.setItem("menuName" , "false");
    }

    $.APIPost("/api/menu/getMenuList",function (data) {
        var listr = "";
        // console.log(data);
        $.each(data.data.result, function (i, v) {
            listr += '<li><span class="menu-title"><span class="'+ v.menuIcon +'"></span>&nbsp;'+ v.menuName +'</span>';
            if(i == 0){
                listr += '<ul class="menu-second-ul current">';
            }else{
                listr += '<ul class="menu-second-ul">';
            }
            $.each(v.childMenu, function (j, v2) {
                listr += '<li><a href="'+ v2.menuUrl +'">'+ v2.menuName +'</a></li>';
            });
            listr += '</ul></li>';
        });
        $(".menu-first-ul").html(listr);
        setTimeout(function () {

            $('.menu-second-ul').on('click','li',function(){
                window.localStorage.setItem("menuName",$(this).text());
                var str = '<li><a id="home" onclick="javascript:homeClick()" href="javascript:">首页</a></li><li class="active" href="'+ $(this).find("a").attr("href") +'">'+ $(this).text() +'</li>';
                // console.log(str);
                window.localStorage.setItem("breadcrumbInfo",str);
            });
            
            var menuName = window.localStorage.getItem("menuName");
            if(typeof menuName != "undefined" && menuName != null && menuName != "" && menuName != 'false'){
                $('.menu-second-ul li').each(function(){
                    $(this).parent().removeClass("current");
                    var name = $(this).text();
                    var href = $(this).find("a").attr("href");
                    if(name == menuName){
                        $(this).parent().addClass("current");
                        $(this).addClass("click-menu");
                        return false;
                    }
                });
            }

            var breadcrumbInfo = window.localStorage.getItem("breadcrumbInfo");
            // console.log(breadcrumbInfo);
            if(typeof breadcrumbInfo != "undefined" && breadcrumbInfo != null && breadcrumbInfo != ""){
                $(".breadcrumb").html(breadcrumbInfo);
            }


            setTimeout(function () {
                $(".menu-second-ul").each(function(){
                    if(!$(this).hasClass("current")){
                        $(this).hide();
                    }
                });
            },100);


            $(".menu-first-ul li span").click(function(){
                $(this).next().slideToggle(500);
            });
            $(".menu-second-ul li a").click(function(){
                $(this).next().slideToggle(500);
            });
        },100);
    });

    $(".collapse-button").click(function(){
        if($(this).children().hasClass("glyphicon-chevron-down")){
            $(this).children().removeClass("glyphicon-chevron-down");
            $(this).children().addClass("glyphicon-chevron-up");
        }else{
            $(this).children().removeClass("glyphicon-chevron-up");
            $(this).children().addClass("glyphicon-chevron-down");
        }
    });
    
    $("#logout-button").click(function () {
        showDialog("系统提示" ,"您是否要退出系统？",function () {
            window.location.href="/logout";
            return false;
        })
    });
    
    
    $("#addDataButton").click(function () {
        var o_password = $("#o_password").val();
        var n_password = $("#n_password").val();
        if(isEmpty(o_password)){
            showWarningAlert("原密码不能为空");
            return ;
        }
        if(isEmpty(n_password)){
            showWarningAlert("新密码不能为空");
            return ;
        }
        $.APIPost("/api/user/changeUserPassword?oldPassword=" + o_password + "&newPassword=" + n_password,function (data) {
            if(data.success){
                hideModal("myChangePassword");
                showSuccessAlert(data.message);
            }else{
                showFailedAlert(data.message);
            }
        });
    });


});

function addNextBread(title) {
    var href = $(".breadcrumb .active").attr("href");
    var name = $(".breadcrumb .active").text();
    var str = '<li><a id="home" onclick="javascript:homeClick()" href="javascript:">首页</a></li><li><a id="lastStepUrl" href="'+ href +'" onclick="javascript:backLastBread();">'+ name +'</a></li><li class="active">'+ title +'</li>';
    window.localStorage.setItem("breadcrumbInfo",str);
}

function backLastBread() {
    var href = $(".breadcrumb #lastStepUrl").attr("href");
    var name = $(".breadcrumb #lastStepUrl").text();
    var str = '<li><a id="home" onclick="javascript:homeClick()" href="javascript:">首页</a></li><li class="active"  href="'+ href +'">'+ name +'</li>';
    window.localStorage.setItem("breadcrumbInfo",str);
    return href;
}

function homeClick() {
    var str = '<li class="active"><a id="home" onclick="javascript:homeClick()" href="javascript:">首页</a></li>';
    window.localStorage.setItem("breadcrumbInfo",str);
    // window.location.href="/home";
    return false;
}

function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}
