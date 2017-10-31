define(function(require, exports, module) {
	var user = $.cookie("user");
	if (user == null) {
		$("#loginRef").removeClass("hidden")
		$("#userInfo").addClass("hidden")
		$("#logout").addClass("hidden")
	} else {
		$("#loginRef").addClass("hidden")
		$("#userInfo").removeClass("hidden")
		$("#logout").removeClass("hidden")
		$.ajax({
			type : "get",
			url : "/dawn/rest/user/getUserInfo?id=" + user.split("|")[0],
			async : true,
			success : function(data) {
				$("#userInfo").text(data.name)
			}
		});
	}
	$("#logout").click(function() {
		logout();
	})
	$("#search").click(function() {
		search($("#wiki").val())
	})
	var talentAll = getAllTalent();
	var ocuupationID = 1;
	var masteryID = 1;

	var occu = "";
	for (var i = 0; i < talentAll.length; i++) {
		var ocuupation = talentAll[i];
		var occupationHtml = "<div id='ocuupation" + ocuupation.id + "' class = 'ocuupation'>" + ocuupation.name + "</div>"
		occu += occupationHtml;
	}
	$("#occupation").html(occu)

	$(".ocuupation").click(function() {
		$(this).siblings().removeClass("select")
		$(this).addClass("select")
		var mas = "";
		ocuupationID = $(this).attr("id").substring(10);
		var mastery = getData(ocuupationID, talentAll, "mastery");
		for (var i = 0; i < mastery.length; i++) {
			var masteryHtml = "<div id='mastery" + mastery[i].id + "' class = 'mastery'>" + mastery[i].name + "</div>"
			mas += masteryHtml;
		}
		$("#mastery").html(mas)

		$(".mastery").click(function() {
			$(this).siblings().removeClass("select")
			$(this).addClass("select")
			var tal = "";
			masteryID = $(this).attr("id").substring(7);

			var talent = getData(masteryID, mastery, "talent");
			if (talent) {
				for (var i = 0; i < talent.length; i++) {
					var talentHtml = "<div id='talent" + talent[i].id + "' class = 'talent'>" + talent[i].level + "</div>"
					if (talent[i].point) {
						for (var j = 0; j < talent[i].point.length; j++) {
							var point = "<div>" + talent[i].point[j].name + "</div>"
							talentHtml += point
						};
					};
					tal += talentHtml
				}
				

				$("#talent").html(tal)
			} else {
				$("#talent").html("")
			}
		})
		$("#mastery").children()[0].click();
	})
	$("#occupation").children()[0].click();
	function getData(id, data, exp) {
		for (var i = 0; i < data.length; i++) {
			if (id == data[i].id) {
				return data[i][exp] ? data[i][exp] : null;
			};
		};
		return null;
	}

	function search(val) {
		window.location.href = "/dawn/html/wiki.html?word=" + val;
	}
	function logout() {
		$.ajax({
			type : "get",
			url : "/dawn/rest/login/logout",
			async : false,
			success : function(data) {
				if (data == 0) {
					window.location.href = "/dawn/index.html"
				};
			}
		});
	}

	function getAllTalent() {
		var talent = ""
		$.ajax({
			type : "get",
			url : "/dawn/rest/talent/getAll",
			async : false,
			success : function(data) {
				talent = data;
			}
		});
		return talent.occupation;
	}

});