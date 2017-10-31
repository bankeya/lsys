define(function(require, exports, module) {

	var word = GetQueryString("word")
	if (!word) {
		window.location.href = "/dawn/html/error.html"
		return
	};
	$.ajax({
		type : "get",
		url : "/dawn/rest/wiki/" + decodeURI(word),
		async : false,
		success : function(data) {
			$("#word").html(data);
		}
	});

	function GetQueryString(name) {
		var url = window.location.href;
		var reg = /\?/;
		if (url.match(reg)) {
			// 判断传入参数，以问号截取，问号后是参数
			var chars = url.split('?')[1];

			// 再截&号
			var arr = chars.split('&');

			// 获得截取后的数组为键值对字符串
			for (var i = 0; i < arr.length; i++) {

				// 保守一点确定看是否为 name=value形式
				var num = arr[i].indexOf("=");

				if (num > 0) {
					if (decodeURIComponent(arr[i].substring(0, num)) == name) {
						return decodeURIComponent(arr[i].substr(num + 1));
					}
				}
			}

		}
		return null;
	}
});