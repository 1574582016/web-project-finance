
function showSuccessAlert(content,callback,showTime){
	showAlertModel({content:content,icon:'succeed',showTime:showTime,callback:callback});
}


function showFailedAlert(content,callback,showTime){
	showAlertModel({content:content,icon:'failed',showTime:showTime,callback:callback});
}

function showDialog(modelTitle,content,callback){
	showDialogModel({content:content,modelTitle:modelTitle,callback:callback});
}


function showAlertModel(setting){
	/**
	*this 当前对象
	*/
	var _self = this;
	
	 /*
    *判断为null或者空值
    */
    _self.IsNull = function(value) {
        if (typeof (value) == "function") { return false; }
        if (value == undefined || value == null || value == "" || value.length == 0) {
            return true;
        }
        return false;
    }
    
    /*
    *默认配置
    */
    _self.DefautlSetting = {
        content: "",//提示内容
        icon: "",//图标内容，默认无图标，succeed是勾，failed是×
        showTime:1500,//提示框显示时间
        callback: function() { }//自定义回掉函数
    };
    
    /*
    *读取配置
    */
    _self.Setting = {
        content: _self.IsNull(setting.content) ? _self.DefautlSetting.content : setting.content,
        icon: _self.IsNull(setting.icon) ? _self.DefautlSetting.icon : setting.icon,
        showTime: _self.IsNull(setting.showTime) ? _self.DefautlSetting.showTime : setting.showTime,
        callback: _self.IsNull(setting.callback) ? _self.DefautlSetting.callback : setting.callback
    };
    if(_self.Setting.icon == "succeed"){
    	  $("#showIconType").attr('class',"glyphicon glyphicon-ok");
    	  $("#showIconType").css("color","#73a839");
  	}else if(_self.Setting.icon == "failed"){
  	    $("#showIconType").attr('class',"glyphicon glyphicon-remove");
    	  $("#showIconType").css("color","red");
  	}else{
  		  $("#showIconType").attr('class',"");
    	  $("#showIconType").css("color","");
  	};
    $("#showAlertInfo").html(_self.Setting.content);
    $('#showAlertModel').modal('show');
    //定时隐藏提示框
    window.setTimeout(function(){ hideModal('showAlertModel');},_self.Setting.showTime);
        $('#showAlertModel').on('hidden.bs.modal', function () {
           _self.Setting.callback();
        })
	}

function showDialogModel(setting){
	/**
	*this 当前对象
	*/
	var _self = this;
	
	 /*
    *判断为null或者空值
    */
    _self.IsNull = function(value) {
        if (typeof (value) == "function") { return false; }
        if (value == undefined || value == null || value == "" || value.length == 0) {
            return true;
        }
        return false;
    }
    
    /*
    *默认配置
    */
    _self.DefautlSetting = {
        content: "",//询问内容
        modelTitle: "提示框",//标题
        callback: function() { }//自定义回掉函数
    };
    
    /*
    *读取配置
    */
    _self.Setting = {
        content: _self.IsNull(setting.content) ? _self.DefautlSetting.content : setting.content,
        modelTitle: _self.IsNull(setting.modelTitle) ? _self.DefautlSetting.modelTitle : setting.modelTitle,
        callback: _self.IsNull(setting.callback) ? _self.DefautlSetting.callback : setting.callback
    };
    
    $("#showDialogTitle").html(_self.Setting.modelTitle);
    $("#showDialogInfo").html(_self.Setting.content);
    $('#showDialogModel').modal('show');
    $("#showDialogModel").find(".confirmButton").on("click", function () {
        $('#showDialogModel').modal('hide');
        $('#showDialogModel').on('hidden.bs.modal', function () {
            _self.Setting.callback();
            
        });
    });
    
    
	}

//隐藏提示栏
function hideModal(id) {
    $('#'+id).modal('hide');
}
//显示提示栏
function showModal(id) {
    $('#'+id).modal('show');
}