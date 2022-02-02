<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리플 달기</title>
</head>
<body>
	<form action="reply.do" method="POST">
		
				<input type="text" name="num" value="${replyui.num}"> 
		
				<input type="text" name="repRoot" value="${replyui.repRoot}">
		
				<input type="text" name="repSetp" value="${replyui.repStep}"> 
		
				<input type="text" name="repIndent" value="${replyui.repIndent}"> 
				
				원래글번호${replyui.num}&nbsp; 조회수 ${replyui.readCnt}<br> 
				
				제목 : <input type="text" name=" title" value="${replyui.title}"> <br>
				작성자 : <input type="text" name="author" value="${replyui.author}"> <br>
				내용 : <textarea name="content" rows="5" cols="30">${replyui.content}</textarea> 	 <br>
				
				<input type="submit" value="댓글 달기">					
		
		
		
	</form>
	<a href="list.do">목록보기</a>

</body>
</html>