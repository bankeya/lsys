define(function(require, exports, module) {
	var md5 = require("md5.js")
	$("#login").click(function(){
		login();
	})
	function login(){
		var account = $("#account").val();
		var password = $("#password").val();
		if (account == "") {
			$("#accountTips").removeClass("hidden");
		}else{
			$("#accountTips").addClass("hidden");
		}

		if (password == "") {
			$("#passwordTips").removeClass("hidden");
		}else{
			$("#passwordTips").addClass("hidden");
		}
		if (account != "" && password != "") {
			$.ajax({
				type: "get",
				url: "/dawn/rest/login/login?id="+account+"&password="+password,
				async: false,
				success: function(data) {
					if (data==1) {
						window.location.href="../index.html"
					}else if (data == -2) {
						alert("账号不存在！")
					}else{
						alert("密码错误！")
					}
				}
			});
		};
	}
});