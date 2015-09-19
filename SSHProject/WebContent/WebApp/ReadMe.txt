
写server side的同事请注意，如果要跑server，需要更改一些信息:

1, \SSHProject\WebContent\WebApp\lib\js\common.js
    window.test = true; //false as running in server
2, 对应的html，要改成绝对路径
3, html入口路径是: \SSHProject\default.html

写client side的同事请注意,

1, 如果需要添加新的功能，需要和其它功能一样保持正确的层级目录，方便为了在本地测试dummy数据
2, 如果要添加新的功能，涉及到发的request请求，都尽量把dummy也加上，保证在本地跑dummy是绝对正确的
