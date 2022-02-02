<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="javax.servlet.http.HttpServletRequest"  import="javax.servlet.http.HttpServletResponse"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
<form action="update.do" method="POST" name="action">
<fieldset width="300">
<legend>글쓰기</legend>

	<input type="hidden" name="num" value="${retrieve.num}">
	글번호 : ${retrieve.num} &nbsp;&nbsp;&nbsp;&nbsp; 조회수:${retrieve.readCnt} <br>
제목 :  <input type="text" name="title" value="${retrieve.title}" ><br>
작성자 :  <input type="text" name="author" value="${retrieve.author}" ><br>

내용 <br>
<textarea name="content" cols="50" rows="5">${retrieve.content}</textarea> <br>
<input type="submit" value="수정"> <br>
</fieldset>

</form>





<input type="button" value="삭제" onclick="location.href='delete.do?num=${retrieve.num}'"> 
<input type="button" value="답글" onclick="location.href='replyui.do?num=${retrieve.num}'"> 
<a href="replyui.do?num=${retrieve.num}">답변달기</a>
<input type="button" value="목록" onclick="location.href='list.do'"> 

</body>

</html>