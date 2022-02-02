<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
<form action="write.do" method="POST" name="action">
	<fieldset style="width:300;">
	<legend>글쓰기</legend>
		
			제목 : <input type="text" name="title"> <p>
			작성자 : <input type="text" name="author"><p>
			내용 <p>
			<textarea name="content" cols="50" rows="5"></textarea> <p>
			<input type="submit" value="저장">  <input type="button" value="목록" onclick="location.href='list.do'"> 
	</fieldset>
</form>


</body>

</html>