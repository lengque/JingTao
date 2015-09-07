$(function() {
    window.host = "http://localhost:8080";
    window.test = true;
    window.runInServer = true;
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
        proxy2: function(url, method, params) {
        //proxy : function(datajs, callback, url, method){
            $.ajax({
                type: method,
                data: params,
                url: url,
                dataType: 'json',
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
        }
    }
    
    window.hookRequire = function(name,args,callback){
        if(!$.isArray(args)) return;
        if(callback && $.isFunction(callback)){
            topic.callback = callback;
        }
        window.nativeRequest[name].apply(this,args);
    }


    var statusForDebug = true;
    //for logon
    var loginForm = $(".loginbox");
    if (false && loginForm.length > 0) {
        var validate = loginForm.validate({
            debug: statusForDebug, //调试模式取消submit的默认提交功能   
            errorClass: "label.requiredError", //默认为错误的样式类为：error   
            focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
            onkeyup: false,
            submitHandler: function(form) { //表单提交句柄,为一回调函数，带一个参数：form 
                debugger;
                alert("提交表单");
                form.submit(); //提交表单   
            },
            rules: {
                username: {
                    required: true,
                    minlength: 6
                },
                password: {
                    required: true,
                    minlength: 6
                }
            },
            messages: {
                username: {
                    required: "*必填"
                },
                password: {
                    required: "*必填"
                }
            }

        });
    }


    var requestUrl = {
        "logon": "/SSHProject/login.action"
    }
    window.requestUrl = requestUrl;
});

function callBackHandler(name, data){
    var result = {data:data.data};
    result.valid = data.data.header.statusCode !="0000"?false:true;
    messageHandler.apply(this,[name, topic.callback,result]);
}
function messageHandler(name,handler,rs){
    var rsData=rs.data;
    
    if(rs.valid){
		handler.success.apply(this,[rs]);
		return;
	}else{
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
    }
	if(handler.error && $.isFunction(handler.error)){
		if(rsData && rsData.hasOwnProperty('header'))
		{
            handler.error.apply(self,[rs]);
        }
	}
    
    if(rsData && rsData.hasOwnProperty('header') && rsData.header.statusCode == "SYS900"){
        //dialog show here
    }
    
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
