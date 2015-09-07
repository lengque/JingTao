var commonErrorNls = {
    "SYS900": "系统错误",

    "USER001": "用户未登录",
    "USER002": "用户名不能为空",
    "USER003": "用户名不要超过16位",
    "USER004": "用户名已存在",
    "USER005": "用户不存在",
    "USER006": "密码为空",
    "USER007": "密码不能少于6位",
    "USER008": "密码不能超过20位",
    "USER009": "两次输入密码不一致",
    "USER010": "密码不正确",
    "USER011": "原始密码错误",
    "USER012": "电话号码为空",
    "USER013": "电话格式错误",
    "USER014": "身份证号码不能为空",
    "USER015": "身份证号格式错误",
    "USER016": "真实姓名过长",
    "USER017": "邮箱为空",
    "USER018": "邮箱格式错误",
    "USER019": "地址不能超过36位",

    "ORD001": "标题不能为空",
    "ORD002": "标题不能超过20字",
    "ORD003": "新旧程度为空",
    "ORD004": "新旧程度有问题",
    "ORD005": "价格为空",
    "ORD006": "详细描述为空",
    "ORD007": "详细描述不应超过150字",
    "ORD008": "类目为空",
    "ORD009": "无权操作该条信息",
    "ORD010": "查找的信息不存在"
};
function handleResError(response){
	if (response.hasOwnProperty("header") && response["header"].hasOwnProperty("statusCode")
				&& response.hasOwnProperty("body") ) {
        var statusCode = response["header"]["statusCode"];
	    if (statusCode === "0000") {
	    	return true;
	    }else{
	    	var _msg = commonErrorNls[statusCode];
	    	if (_msg!="") {
	    		bootbox.alert(_msg);
	    	}else{
	    		bootbox.alert("未知的错误，请联系管理员！");
	    	}
	    	return false;
	    }
	}else{
		if (response.hasOwnProperty("msg")){
			bootbox.alert(response["msg"]);
		}else{
			bootbox.alert("未知的错误，请联系管理员！（格式错误）");
		}
		return false;
	}
}
function ajaxGet(url, parameters, success, fail) {
    console.log('---请求参数---');
    console.log('url:');
    console.log(url);
    console.log('parameters:');
    console.log(parameters);

    // 链接参数到url
    var full_url = url_base + url + "?";
    var i = 0;
    if (parameters!=null) {
        $.each(parameters, function (key, value) {
            if (i == 0) {
                full_url += key + "=" + value;
            } else {
                full_url += "&" + key + "=" + value;
            }
            i++;
        });
    };

    // GET
    $.ajax({
        type: 'GET',
        url: full_url,
        dataType: 'json',
        beforeSend: function(xhr) {
        }
    }).fail(function (error) {
        console.error(error);
        if (typeof fail != "undefined") {
        	fail();
        }else{
            bootbox.alert("网络连接有问题,请检查是否网络正常，或者联系管理员!");
        }
    }).done(function (response) {
        console.log(response);
        success(response);
    });
}
function ajaxPost(url, parameters, success, fail) {
    console.log('---POST Request---');
    console.log('url: %o', url);
    console.log('parameters: %o', parameters);
    if (parameters==null) {parameters={};};
    // POST
    $.ajax({
        type: 'POST',
        url: url_base + url,
        dataType: 'json',
        data: parameters,
        beforeSend: function(xhr) {
        }
    }).fail(function (error) {
        console.error(error);
        if (typeof fail != "undefined") {
        	fail();
        }else{
            bootbox.alert("网络连接有问题,请检查是否网络正常，或者联系管理员!");
        }
    }).done(function (response) {
        console.log('POST Response: %o', response);
        success(response);
    });
}