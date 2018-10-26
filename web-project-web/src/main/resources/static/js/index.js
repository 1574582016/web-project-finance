$(function(){
    // window.localStorage.removeItem("menuName");
    // window.localStorage.removeItem("menuUrl");

    $.APIPost("/api/menu/getMenuList",function (data) {
        var listr = "";
        console.log(data);
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
                var str = '<li><a id="home" href="/home">首页</a></li><li class="active" href="'+ $(this).find("a").attr("href") +'">'+ $(this).text() +'</li>';
                // console.log(str);
                window.localStorage.setItem("breadcrumbInfo",str);
            });
            $("#home").click(function () {
                $(".breadcrumb").html('<li class="active"><a id="home" href="#">首页</a></li>');
            });

            var menuName = window.localStorage.getItem("menuName");
            if(typeof menuName != "undefined" && menuName != null && menuName != ""){
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

});

function addNextBread(title) {
    var href = $(".breadcrumb .active").attr("href");
    var name = $(".breadcrumb .active").text();
    var str = '<li><a id="home" href="/home">首页</a></li><li><a id="lastStepUrl" href="'+ href +'" onclick="javascript:backLastBread();">'+ name +'</a></li><li class="active">'+ title +'</li>';
    window.localStorage.setItem("breadcrumbInfo",str);
}

function backLastBread() {
    var href = $(".breadcrumb #lastStepUrl").attr("href");
    var name = $(".breadcrumb #lastStepUrl").text();
    var str = '<li><a id="home" href="/home">首页</a></li><li class="active"  href="'+ href +'">'+ name +'</li>';
    window.localStorage.setItem("breadcrumbInfo",str);
    return href;
}
