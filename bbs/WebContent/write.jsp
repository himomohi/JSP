<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<h1>게시판 글쓰기 화면</h1>
	<form action="write.do">
		제   목 : <input type="text" name="title"/><br/>
		작성자 : <input type="text" name="author"/><br/>
		내   용 : <textarea name="content" rows="5"></textarea><br/>
		<input type="submit" value="저장"/>
	</form>
	<a href="list.do">목록보기</a>
</body>
</html>