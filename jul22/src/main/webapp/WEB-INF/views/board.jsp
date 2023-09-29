<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/menu-white.css">
<link rel="stylesheet" href="./css/board.css">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="./img/favicon.ico" type="image/x-icon">

</head>
<body>
	<div class = bg_board>
		<div class="head">BOARD</div>
	</div>
	<br>
	<br>
	<br>
	<div class = btn>
		<input type="text">
		<img alt="" src="./img/search.png" style="width: 8%;" > &nbsp;
		<img alt="" src="./img/write.png" style="width: 8%;" onclick="location.href='./write'">
	</div>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>좋아요</th>
		</tr>
		<c:forEach items="${list }" var="row">
			<tr class="board_list" onclick="location.href='./detail?bno=${row.bno }'">
				<td class="td1">${row.bno }</td>
				<td class="title">${row.btitle }</td>
				<td class="td1">${row.bwrite }</td>
				<td class="td2">${row.bdate }</td>
				<td class="td1">${row.blike }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="block1"></div>
</body>
</html>