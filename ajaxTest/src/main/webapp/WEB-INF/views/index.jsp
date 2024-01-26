<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.high_light {
	background: yellow;
}

.active {
	background: pink;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		var list;
		var i = 0;

		$.ajax({
			url : "listBook",
			success : function(data) {
				list = data;
			}
		})
		
		var change = function() {
			if (i < list.length) {
				$("#book").html(list[i].bookname).attr("href", "detailBook?bookid="+list[i].bookid);
				i++;
			} else {
				i = 0;
			}
		}

		var si = setInterval(change, 1000);

		$("#stop").on("mouseenter", "a", function() {
			clearInterval(si);
		});
		
		$("#stop").on("mouseleave", "a", function() {
			si = setInterval(change, 1000);
		});

		$("#list").on("mouseenter", "tr", function() {
			$("tr").removeClass("high_light");
			$(this).addClass("high_light");
		});

		$("#list").on("click", "tr", function() {
			$("tr").removeClass("active");
			$(this).addClass("active");

			var tdList = $(this).find("td");
			$("#dno").val($(tdList[0]).html());
			$("#dname").val($(tdList[1]).html());
			$("#dloc").val($(tdList[2]).html());
		});

		var loadDept = function() {
			$.ajax({
				url : "listDept",
				success : function(r) {
					$("#list").empty();
					$.each(r, function() {
						var tr = $("<tr></tr>");
						var td1 = $("<td></td>").html(this.dno);
						var td2 = $("<td></td>").html(this.dname);
						var td3 = $("<td></td>").html(this.dloc);

						tr.append(td1, td2, td3);
						$("#list").append(tr);
					})
				}
			})
		}

		$("#add")
				.click(
						function() {
<%-- 
			var dno = $("#dno").val();
			var dname = $("#dname").val();
			var dloc = $("#dloc").val();
			var data = {"dno":dno, "dname":dname, "dloc":dloc}
			--%>
	var data = $("#f").serializeArray();
							$.ajax({
								url : "insert",
								data : data,
								success : function(r) {
									loadDept();
								}
							});

							$(".input").val("");
						});

		$("#update").click(function() {
			var data = $("#f").serializeArray();
			$.ajax({
				url : "update",
				data : data,
				success : function(r) {
					loadDept();
				}
			});

			$(".input").val("");
		});

		$("#delete").click(function() {
			var re = confirm("삭제하시겠습니까")
			if (re == true) {
				var data = {
					dno : $("#dno").val()
				};
				$.ajax({
					url : "delete",
					data : data,
					success : function(r) {
						loadDept();
					}
				});

				$(".input").val("");
			}
		});
		loadDept();
	});
</script>
</head>
<body>
	<h2>도서목록</h2>
	<table border="1" width="80%">
		<thead>
			<th>부서번호</th>
			<th>부서명</th>
			<th>부서위치</th>
		</thead>
		<tbody id="list">
		</tbody>
	</table>
	<form id="f">
		부서번호 : <input type="text" name="dno" id="dno" class="input"><br>
		부서이름 : <input type="text" name="dname" id="dname" class="input"><br>
		부서위치 : <input type="text" name="dloc" id="dloc" class="input"><br>
	</form>
	<button id="add">추가하기</button>
	<button id="update">수정하기</button>
	<button id="delete">삭제하기</button>

	<div id="stop">
		<a id="book" name="book"></a>
	</div>

</body>
</html>