String.prototype.startsWith = function(str) {return (this.match("^"+str)==str)};
$(function() {
    window.host = "http://localhost:8080";
    window.test = true;
    window.runInServer = true;
    var requestUrl = {
        "logon": "/SSHProject/login.action"
    }
    window.requestUrl = requestUrl;
    var topic = {};
    var debug = true;

    ! function(enable) {
        if (enable) return null;

        console.log = function() {};
        console.warn = function() {};
        console.error = function() {};
        console.group = function() {};
        console.groupEnd = function() {};
        console.groupCollapsed = function() {};

    }(debug);
    
    var buildParams = function(instance){
        var params = $.param(instance);
        if(params && params.indexOf('=')==0){
            params = params.substr(1);
        }
        return params;
    }
    
    window.nativeRequest = {
        proxy: function(options) {
            $.ajax({
                type: options.method || 'get',
                data: buildParams($.isFunction(options.data) ? options.data.call(this) : options.data || []),
                url: options.url,
                dataType: 'json',
                beforeSend: function() {
                    //loading
                    console.info("proxy, need handle some info before send request...");
                },
                complete: function(data, status) {
                    try {
                        var temp = data.responseText;
                        eval("var respData=" + data + ";");
                        eval(options.callback + "(respData)");
                    } catch (err) {
                        alert("Error occurs while parsing respone, please check response data:\n" + err);
                    }
                }

            });
        },
        proxy2: function(parameters, callback, url, method) {
        //proxy : function(datajs, callback, url, method){
            $.ajax({
                type: method,
                data: params,
                url: url,
                dataType: 'json',
                data: {"requestJson":parameters},
                beforeSend: function() {
                    //loading
                    //console.info("proxy2, need handle some info before send request...");
                },
                complete: function(data, status) {
                    try {
                        eval("var responseData="+data.responseText+";");
                        
						eval(callback+"(responseData)");
                    } catch (err) {
                        alert("Error occurs while parsing respone, please check response data:\n" + err);
                    }
                }

            });
        },
        ajaxGet: function(url, parameters, success, fail) {
            console.log('---请求参数---');
            console.log("request url--->"+url);
            console.log("request parameters--->"+parameters);

            // 链接参数到url
            var full_url = window.host + url + "?";
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
        },
        ajaxPost : function(name, parameters, success, fail) {
            var url = window.requestUrl[name];
            console.log('---POST Request---');
            console.log('url: %o', url);
            console.log('parameters: %o', parameters);
            if(typeof parameters == "object")
            if (parameters==null) {parameters={};};
            // POST
            $.ajax({
                type: 'POST',
                url: window.host + url,
                dataType: 'json',
                data: {"requestJson":parameters},
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
        },
        request : function(options){
            var url = getURL(window.requestUrl[options.name]);
            if(window.test){
                require(["dummy/"+options.name+"_resp"], function(data){
                    if(options.callback && $.isFunction(options.callback)){
                        var handler = options.callback.call(this,data);
                        var result = {data:data};
                        result.valid = data.header.statusCode !="0000"?false:true;
                        messageHandler.apply(this,[handler,result]);
                    }
                });
            }else{
                window.hookRequire("proxy2", [options.datajs, "callBackHandler", url,options.method], options.callback);
            }
        }
    }
    
    window.hookRequire = function(name,args,callback){
        if(!$.isArray(args)) return;
        if(callback && $.isFunction(callback)){
            topic.callback = callback;
        }
        window.nativeRequest[name].apply(this,args);
    }
});

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
function callBackHandler(data){
    console.log("callBackHandler("+data+")");
    var result = {data:data};
    result.valid = data.header.statusCode !="0000"?false:true;
    messageHandler.apply(this,[topic.callback,result]);
}
function messageHandler(handler,rs){
    var rsData=rs.data;
    var statusCode = rsData["header"]["statusCode"];
    if(rs.valid){
		handler.success.apply(this,[rs]);
		return;
	}
    /*
    var existJqueryUITag = $("script");
    var isExist = false;
    for(var i in existJqueryUITag){
        isExist = $(existJqueryUITag[i]).attr("src").indexOf("jquery-ui")>-1 ? true : false;
        if(isExist){
            break;
        }
    }
    if(!isExist){
        var createScript = document.createElement("script");
        if(window.runInServer){
            $(createScript).attr("src", "WebApp/lib/js/jquery-ui.min.js");
        }else{
            $(createScript).attr("src", "../../../jquery-ui.min.js");
        }
    }
    */
	if(handler.error && $.isFunction(handler.error)){
		if(rsData && rsData.hasOwnProperty('header'))
		{
            handler.error.apply(this,[rs]);
        }
	}
    
    //filter common error
    if(rsData && rsData.hasOwnProperty('header')){// && statusCode != "SYS900"
        //dialog show here
        var _msg = commonErrorNls[statusCode];
        if (_msg!="") {
            bootbox.alert(_msg);
        }else{
            bootbox.alert("未知的错误，请联系管理员！");
        }
    }
}

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

function getURL(link){
    var url = link;
    if(!!!url){
        console.log("getURL is empty...");
        return;
    }
    if(!url.startsWith("http") && !url.startsWith("https")){
        url = window.host + link;
    }
    return url;    
}

$(function() {
    //base64 encode and decode
    var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    var base64DecodeChars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);

    function base64encode(str) {
        var out, i, len;
        var c1, c2, c3;
        len = str.length;
        i = 0;
        out = "";
        while (i < len) {
            c1 = str.charCodeAt(i++) & 0xff;
            if (i == len) {
                out += base64EncodeChars.charAt(c1 >> 2);
                out += base64EncodeChars.charAt((c1 & 0x3) << 4);
                out += "==";
                break;
            }
            c2 = str.charCodeAt(i++);
            if (i == len) {
                out += base64EncodeChars.charAt(c1 >> 2);
                out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
                out += base64EncodeChars.charAt((c2 & 0xF) << 2);
                out += "=";
                break;
            }
            c3 = str.charCodeAt(i++);
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
            out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
            out += base64EncodeChars.charAt(c3 & 0x3F);
        }
        return out;
    }

    function base64decode(str) {
        var c1, c2, c3, c4;
        var i, len, out;
        len = str.length;
        i = 0;
        out = "";
        while (i < len) {
            /* c1 */
            do {
                c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
            }
            while (i < len && c1 == -1);
            if (c1 == -1)
                break;
            /* c2 */
            do {
                c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
            }
            while (i < len && c2 == -1);
            if (c2 == -1)
                break;
            out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));
            /* c3 */
            do {
                c3 = str.charCodeAt(i++) & 0xff;
                if (c3 == 61)
                    return out;
                c3 = base64DecodeChars[c3];
            }
            while (i < len && c3 == -1);
            if (c3 == -1)
                break;
            out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));
            /* c4 */
            do {
                c4 = str.charCodeAt(i++) & 0xff;
                if (c4 == 61)
                    return out;
                c4 = base64DecodeChars[c4];
            }
            while (i < len && c4 == -1);
            if (c4 == -1)
                break;
            out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
        }
        return out;
    }


})
