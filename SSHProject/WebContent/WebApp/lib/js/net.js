var url_base = "https://www.baidu.com";
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
//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}