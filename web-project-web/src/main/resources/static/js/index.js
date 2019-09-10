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
                listr += '<li><a menuCode="'+ v2.menuCode +'" href="'+ v2.menuUrl +'" target="'+ v2.menuCode +'Iframe">'+ v2.menuName +'</a></li>';
            });
            listr += '</ul></li>';
        });
        $(".menu-first-ul").html(listr);
        $(".menu-second-ul").each(function(){
            if(!$(this).hasClass("current")){
                $(this).hide();
            }
        });
    });

    $('.menu-second-ul').on('click','li',function(){
        var numLi = 0 ;
        $.each($("#myTab").find("li"), function(){
            $(this).removeClass("active");
            numLi += 1;
        });
        $.each($("#myTabContent").find("div"), function(){
            $(this).removeClass("active in");
        });
        var justId = "#" + $(this).find("a").attr("menuCode");
        var isExsit = false;
        $.each($("#myTab").find("a"), function(){
            if(justId == $(this).attr("href")){
                $(this).parent().addClass("active");
                $($(this).attr("href")).addClass("active in");
                isExsit = true;
                return;
            }
        });
        if(!isExsit){
            if(numLi == 9){
                $("#homeTab").next().remove();
               var removeId = $("#homeTab").next().find("a").attr("href");
                $(removeId).remove();
            }
            var title = '<li class="active"><a href="#'+ $(this).find("a").attr("menuCode") +'" data-toggle="tab">'+ $(this).find("a").text() +'<span class="glyphicon glyphicon-remove"  onclick="closeTab(this);"></span></a></li>';
            var contain = '<div class="tab-pane fade active in" style="height:calc(100% - 4px)" id="'+ $(this).find("a").attr("menuCode") +'"><iframe width="100%" height="100%" style="border: 0" name="'+ $(this).find("a").attr("menuCode") +'Iframe" src="'+ $(this).find("a").attr("href") +'"></iframe></div>';
            $("#myTab").append(title);
            $("#myTabContent").append(contain);
        }
    });

    $(".menu-first-ul li span").click(function(){
        $(this).next().slideToggle(500);
    });
    $(".menu-second-ul li a").click(function(){
        $(this).next().slideToggle(500);
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

function closeTab(obj){
    var thisObj=$(obj);
    var containId = thisObj.parent().attr("href");
    if(thisObj.parent().parent().hasClass("active")){
        $.each($("#myTab").find("li"), function(){
            $(this).removeClass("active");
        });
        if(thisObj.parent().parent().next().length > 0){
            thisObj.parent().parent().next().addClass("active");
            var nextId = thisObj.parent().parent().next().find("a").attr("href");
            $(nextId).addClass("active in");
        }else{
            thisObj.parent().parent().prev().addClass("active");
            var preId = thisObj.parent().parent().prev().find("a").attr("href");
            $(preId).addClass("active in");
        }

        thisObj.parent().parent().remove();
        $(containId).remove();
    }else{
        thisObj.parent().parent().remove();
        $(containId).remove();
    }
}

function testsss() {
    alert("这是父页面的方法！");
}
