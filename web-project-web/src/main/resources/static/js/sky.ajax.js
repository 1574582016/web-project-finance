/* www.jq22.com
 * author liuxl
 * date 2018/2/27
 * description 封装ajax用于后台异常提示
* */
$(function () {
    $.APIGet = function (url, data, callback) {
        if (jQuery.isFunction(data)) {
            callback = data;
            data = undefined;
        }
        $.APIxhr({url: url, data: data, type: "GET", success: callback});
    };

    $.APIPost = function (url, data, callback) {
        if (jQuery.isFunction(data)) {
            callback = data;
            data = undefined;
        }
        $.APIxhr({url: url, data: data, type: "POST", success: callback});
    };

    $.APIDelete = function (url, data, callback) {
        if (jQuery.isFunction(data)) {
            callback = data;
            data = undefined;
        }
        $.APIxhr({url: url, data: data, type: "DELETE", success: callback});
    };

    $.APIPut = function (url, data, callback) {
        if (jQuery.isFunction(data)) {
            callback = data;
            data = undefined;
        }
        $.APIxhr({url: url, data: data, type: "PUT", success: callback});
    };

    $.APIxhr = function (setting) {

        var _self = this;
        //判断传入的参数是否为空
        _self.IsNull = function (value) {
            if (typeof (value) == "function") {
                return false;
            }
            if (value == undefined || value == null || value == "" || value.length == 0) {
                return true;
            }
            return false;
        }

        //默认设置
        _self.DefautlSetting = {
            url: "",
            data: "",
            dataType: "json",
            type: "get",
            contentType: "application/json;charset=utf-8",
            async: false,
            cache: false,
            success: function (result, status, xhr) {
            },
            error: function () {
                // console.log(result);
            },
        };
        //判断传入的设置，如果为null则采用默认设置，否则采用自定义设置
        _self.Setting = {
            url: _self.IsNull(setting.url) ? _self.DefautlSetting.url : setting.url,
            data: _self.IsNull(setting.data) ? _self.DefautlSetting.data : setting.data,
            dataType: _self.IsNull(setting.dataType) ? _self.DefautlSetting.dataType : setting.dataType,
            type: _self.IsNull(setting.type) ? _self.DefautlSetting.type : setting.type,
            contentType: _self.IsNull(setting.contentType) ? _self.DefautlSetting.contentType : setting.contentType,
            async: _self.IsNull(setting.async) ? _self.DefautlSetting.async : setting.async,
            cache: _self.IsNull(setting.cache) ? _self.DefautlSetting.cache : setting.cache,
            success: _self.IsNull(setting.success) ? _self.DefautlSetting.success : setting.success,
            error: _self.IsNull(setting.error) ? _self.DefautlSetting.error : setting.error,
        };

        //绑定事件
        _self.Bind = function () {

            $.ajax({
                url: _self.Setting.url,
                data: _self.Setting.data,
                dataType: _self.Setting.dataType,
                type: _self.Setting.type,
                contentType: _self.Setting.contentType,
                async: _self.Setting.async,
                cache: _self.Setting.cache,
                // headers: {token: window.localStorage.getItem("token")},
                beforeSend: function (XMLHttpRequest) {
                    
                },
                success: function (result, status, xhr) {
                    
                    _self.Setting.success(result, status, xhr);
                },
                error: function (result, status, xhr) {
                    /*layer.close(index);*/
                    _self.Setting.error();
                },
            });

        };
        //执行事件
        _self.Bind();
    };

});

