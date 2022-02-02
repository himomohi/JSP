<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="javax.servlet.http.HttpServletRequest"  import="javax.servlet.http.HttpServletResponse"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<title>리스트</title>
</head>
<body>
<div class="container">
	<table border="1"  class="table table-striped table-hover">
	<thead>
		<tr>
			<td colspan="5">
				<form action="search.do">
				<div class="input-group">
					<select  name="searchName" size="1" class="btn btn-dark">
						<option value="author">작성자 </option>
						<option value="title">글제목 </option>
					</select>
					 <input type="hidden" id="gameToken" name="game_token" value="xm234jq">
<!-- 				<input type="text" name="searchValue" placeholder="search"/> -->
					<div class="col-3">
				<input type="text" class="form-control" name="searchValue" list="list"  placeholder="search"  />
						</div>
									<datalist id="list" >
						<c:forEach var="dto" items="${list}" >
							<option value="${dto.author}"/>
						</c:forEach>
					</datalist>
				
					<input type="submit" value="찾기" class="btn btn-dark">
					
		
				</form> 
			</div>
			</td>
		</tr>
		
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
	    </tr>
	    </thead>
	    <tbody>
	    <tr>
	    <c:out value="${i}"/>
	    </tr>
	<c:forEach var="dto" items="${list}" >
	
		<tr>	
			
			<td>${dto.num}</td>
<%-- 			<td>${dto.author} </td> --%>


			<td>
					<c:forEach begin="1" end="${dto.repIndent}">
						<%="&nbsp;&nbsp;"%> 
								
						
					</c:forEach>
					
				<a href="retrieve.do?num=${dto.num}">${dto.title}</a> 
			
			
			</td>
<%-- 			<td>${dto.title }</td> --%>
			<td>${dto.author}  </td>
			
			<td>${dto.writeday} </td>
			<td>${dto.readCnt}  </td>
<%-- 			<td>${dto.repRoot}  </td> --%>
<%-- 			<td>${dto.repStep} </td> --%>
<%-- 			<td>${dto.repIndent}  </td> --%>
			
		</tr>
		<c:out value="${i}"/>
	</c:forEach>

</tbody>
	</table>
	<input type="button" class="btn btn-dark" value="글쓰기" onclick="location.href='writeui.jsp'" >

</body>
</div>
</html>