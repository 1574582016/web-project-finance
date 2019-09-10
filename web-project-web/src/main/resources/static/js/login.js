$(function(){
    window.localStorage.removeItem("menuName");
    window.localStorage.removeItem("breadcrumbInfo");

    $("#login-button").click(function () {
        var userName = $("#userName").val().trim();
        var password = $("#password").val().trim();
        if(userName.left == 0 || password == 0){
            $("#error-alert").html("用户名或密码不能为空！");
            return false;
        }else{
            $("#error-alert").html("&nbsp;");
            $.post("/api/user/login",{userName : userName , password:password },function (data) {
//					console.log(data);
                if(data.success){
                    window.location.href="/index";
                    return false;
                }else{
                    $("#error-alert").html(data.message);
                    return false;
                }
            });
            return false;
        }

    });

});

if (window != top) {
    top.location.href = location.href;
}