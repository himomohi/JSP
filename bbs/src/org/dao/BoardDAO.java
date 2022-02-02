package org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.entity.BoardDTO;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;



public class BoardDAO {
	String driver="com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/iotweb?useUnicode=true&serverTimezone=UTC";
	String userid="root";
	String password="12345";
//	DataSource dataFactory;
	public BoardDAO(){
		try {
//			Context ctx=new InitialContext(); //초기화 시킬때 데이타 팩토리를 불러온다./
//			dataFactory=(DataSource)ctx.lookup("java:comp/env/jdbc/Mysql");
			Class.forName(driver);		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<BoardDTO> select() {
		ArrayList<BoardDTO> list=new ArrayList<BoardDTO>();
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			String sql="select num,author,title,content,date_format(writeday,'%Y.%m.%d')as writeday,readCnt,repRoot,repStep,repIndent from board";
			
			conn=DriverManager.getConnection(url,userid,password); //conn 변수에 드라이버 설정해둔 mysql 접속정보를 연결해준다.
			
			pstmt=conn.prepareStatement(sql); //pstmt 변수에 연결된 접속정보 를 작성한 sql 문을 연결하여 대입한다.
			rs=pstmt.executeQuery(); //Rs 타입 변수에 pstmt.셀렉트 쿼리문을 보낸다.
			
			while(rs.next()) {    //출력을 위해  rs.next() 문을 써줘서 한줄씩 나타내게 한다.
				 int num =rs.getInt("num"); 
				 String author=rs.getString("author");
				 String title=rs.getString("title");
				 String content=rs.getString("content");
				 String writeday=rs.getString("writeday");
				 int readCnt =rs.getInt("readCnt");
				 int repRoot =rs.getInt("repRoot");
				 int repStep =rs.getInt("repStep");
				 int repIndent =rs.getInt("repIndent");
				 
				 BoardDTO dto=new BoardDTO(num,author,title,content,writeday,readCnt,repRoot,repStep,repIndent);
				 list.add(dto);
				 
				 
				 System.out.println(list);
			
				
				
				
			}
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
				
			}
		
			
		}
		
		
		
		
		
		return list;
	}
	
///////////////// 글쓰기  ///////////////////////////////////
	public void write(String title,String author,String content) {
		
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int currval=0;
	
		
		try {
			conn=DriverManager.getConnection(url,userid,password); //conn 변수에 드라이버 설정해둔 mysql 접속정보를 연결해준다.
			
			String sql="select ifnull(max(num),0)+1 from board";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next())currval=rs.getInt(1);
				
				String sql2="INSERT INTO board(title, author, content, repRoot, repStep, repIndent) VALUES(?, ?, ?, '"+currval+"', 0, 0)";
				
				
				
				pstmt=conn.prepareStatement(sql2);
				pstmt.setString(1, title);
				pstmt.setString(2, author);
				pstmt.setString(3, content);
				
			
				int n=pstmt.executeUpdate();
				
				
				
			
			
	
	
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();

				
				if(conn != null) conn.close();
				
				
			}catch(Exception e) {
				e.printStackTrace();
				
			}
		
			
		}
		
	}
	
///////////////////////////////////////////////////////////조회수상승
	public void readCount(int _num) {
		
		Connection conn =null;
		PreparedStatement pstmt=null;
	
		
	
	
		
		try {
			conn=DriverManager.getConnection(url,userid,password); //conn 변수에 드라이버 설정해둔 mysql 접속정보를 연결해준다.
			
			
			
				String sql="UPDATE board set readcnt=readcnt+1 where num="+_num; //num 을 받아와서 조회수 를 +1 해준다.
				
				pstmt=conn.prepareStatement(sql);
			
			
				int n=pstmt.executeUpdate();
				

			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
				
			}
		
			
		}
		
	}	
/////////////////////////////////////////////////
///////////////// 글내용 보기  ///////////////////////////////////
public BoardDTO retrieve(int _num) {
	readCount(_num);
	BoardDTO data=new BoardDTO();
	Connection conn =null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
		String sql="select * from board where num=?";
		
		conn=DriverManager.getConnection(url,userid,password); //conn 변수에 드라이버 설정해둔 mysql 접속정보를 연결해준다.
		
		pstmt=conn.prepareStatement(sql); //pstmt 변수에 연결된 접속정보 를 작성한 sql 문을 연결하여 대입한다.
		pstmt.setInt(1, _num);
		rs=pstmt.executeQuery(); //Rs 타입 변수에 pstmt.셀렉트 쿼리문을 보낸다.
		
		while(rs.next()) {    //출력을 위해  rs.next() 문을 써줘서 한줄씩 나타내게 한다.
			 int num =rs.getInt("num"); 
			 String author=rs.getString("author");
			 String title=rs.getString("title");
			 String content=rs.getString("content");
			 String writeday=rs.getString("writeday");
			 int readCnt =rs.getInt("readCnt");
		
			 
			 data.setNum(num);
			 data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadCnt(readCnt);
			 

			 
		
		
			
			
			
		}
		
		
		
		
		
	}catch(Exception e) {
	
		System.out.println(e.getMessage());
		e.printStackTrace();
	}finally {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	
		
	}
	
	
	
	return data;
}
///////////////// 글 삭제하기  ///////////////////////////////////
public void delete(int _num) {
	
	Connection conn =null;
	PreparedStatement pstmt=null;
	
	
	try {
	
		
		conn=DriverManager.getConnection(url,userid,password); //conn 변수에 드라이버 설정해둔 mysql 접속정보를 연결해준다.
			
				String sql="delete from board where num=?";
				pstmt=conn.prepareStatement(sql); 
				pstmt.setInt(1, _num);
				pstmt.executeUpdate();
	
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	
		
	}

}
//////////////////////////////////
public void update(int num,String title,String author,String content) {
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	try {
		conn=DriverManager.getConnection(url,userid,password); //conn 변수에 드라이버 설정해둔 mysql 접속정보를 연결해준다.
		
	
			String sql="update board set title=?,author=?,content=? where num=?";
			pstmt=conn.prepareStatement(sql);
				
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setString(3, content);
			pstmt.setInt(4, num);
			
		
			int n=pstmt.executeUpdate();
			
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
		

			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	
		
	}
	
}
////////////////////////////검색기능//////////
/*
public ArrayList<BoardDTO>search(String searchName,String searchValue){
	ArrayList<BoardDTO> list=new ArrayList<BoardDTO>();
	Connection conn =null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
		String sql="select num,author,title,content,date_format(writeday,'%Y.%m.%d')as writeday,readCnt from board";
		
		if(searchName.equals("title")) {
			sql += " where title like ?";  //쿼리문 뒤에 띄어쓰기 조심
			
		}else {
			sql += " where author like ?";  //쿼리문 뒤에 띄어쓰기 조심
		}
		conn=DriverManager.getConnection(url,userid,password); //conn 변수에 드라이버 설정해둔 mysql 접속정보를 연결해준다.
		
		pstmt=conn.prepareStatement(sql); //pstmt 변수에 연결된 접속정보 를 작성한 sql 문을 연결하여 대입한다.
		pstmt.setString(1, "%"+searchValue+"%");
		rs=pstmt.executeQuery(); //Rs 타입 변수에 pstmt.셀렉트 쿼리문을 보낸다.
		
		
		
		while(rs.next()) {    //출력을 위해  rs.next() 문을 써줘서 한줄씩 나타내게 한다.
			 int num =rs.getInt("num"); 
			 String author=rs.getString("author");
			 String title=rs.getString("title");
			 String content=rs.getString("content");
			 String writeday=rs.getString("writeday");
			 int readCnt =rs.getInt("readCnt");
	 
			 BoardDTO dto=new BoardDTO();
			 dto.setNum(num);
			 dto.setAuthor(author);
			 dto.setTitle(title);
			 dto.setContent(content);
			 dto.setWriteday(writeday);
			 dto.setReadCnt(readCnt);
			 list.add(dto);
			 
			 
			 System.out.println(list);
		
			
			
			
		}
		
		
		
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	
		
	}
	
	
	
	
	
	return list;
	
	
	
}
*/

public ArrayList<BoardDTO> search(String searchName, String searchValue){
	ArrayList<BoardDTO> list=new ArrayList<BoardDTO>();
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
		conn=DriverManager.getConnection(url,userid,password); 
		String sql="SELECT num, author, title, content, date_format(writeday, '%Y.%m.%d') as writeday, readCnt FROM board";
		if(searchName.equals("title")) {
			sql +=" WHERE title LIKE ?";
		}else {
			sql +=" WHERE author LIKE ?";
		}
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, "%"+searchValue+"%");
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			int num=rs.getInt("num");
			String author=rs.getString("author");
			String title=rs.getString("title");;
			String content=rs.getString("content");;
			String writeday=rs.getString("writeday");;
			int readCnt=rs.getInt("readCnt");
			
			BoardDTO data=new BoardDTO();
			data.setNum(num);
			data.setAuthor(author);
			data.setTitle(title);
			data.setContent(content);
			data.setWriteday(writeday);
			data.setReadCnt(readCnt);
			
			list.add(data);
		}
		
	}catch(Exception e) {
		
	}finally {
		try {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	return list;
}
////////////////////////////////////////////////
public void makeReply(int _repRoot,int _repStep) {
Connection conn =null;
PreparedStatement pstmt=null;





try {
conn=DriverManager.getConnection(url,userid,password); //conn 변수에 드라이버 설정해둔 mysql 접속정보를 연결해준다.



String sql="UPDATE board SET repStep=repStep+1 WHERE repRoot=? AND repStep > ?"; //num 을 받아와서 조회수 를 +1 해준다.

pstmt=conn.prepareStatement(sql);


pstmt.setInt(1, _repRoot);
pstmt.setInt(2, _repStep);



int n=pstmt.executeUpdate();



}catch(Exception e) {
e.printStackTrace();
}finally {
try {
if(pstmt != null) pstmt.close();
if(conn != null) conn.close();

}catch(Exception e) {
e.printStackTrace();

}


}


}
/////////////////////////////////////////////////////////
public BoardDTO replyui(int _num) {
	BoardDTO data=new BoardDTO();
	Connection conn =null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
		String sql="SELECT num, author, title, content, date_format(writeday, '%Y.%m.%d') as writeday, readCnt, repRoot, repStep, repIndent FROM board WHERE num=?";
		
		conn=DriverManager.getConnection(url,userid,password); //conn 변수에 드라이버 설정해둔 mysql 접속정보를 연결해준다.
		
		pstmt=conn.prepareStatement(sql); //pstmt 변수에 연결된 접속정보 를 작성한 sql 문을 연결하여 대입한다.
		pstmt.setInt(1, _num);
		rs=pstmt.executeQuery(); //Rs 타입 변수에 pstmt.셀렉트 쿼리문을 보낸다.
		
		if(rs.next()) {    //출력을 위해  rs.next() 문을 써줘서 한줄씩 나타내게 한다.

			 
			 data.setNum(rs.getInt("num"));
			 data.setAuthor(rs.getString("author"));
			 data.setTitle(rs.getString("title"));
			 data.setContent(rs.getString("content"));
			 data.setWriteday(rs.getString("writeday"));
			 data.setReadCnt(rs.getInt("readCnt"));
			 data.setRepRoot(rs.getInt("repRoot"));
			 data.setRepStep(rs.getInt("repStep"));
			 data.setRepIndent(rs.getInt("repIndent"));

			
		}
		
		
		
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	
		
	}
	
	
	
	
	
	return data;
	
	
}

///////////////////////////////////////
//public void reply(int _num,String _title,String _author,String _content,int _repRoot,int _repStep,int _repIndent){
//	
//		makeReply(_repRoot,_repStep);
//		Connection conn =null;
//		PreparedStatement pstmt=null;
//		
//		try {
//			conn=DriverManager.getConnection(url,userid,password); //conn 변수에 드라이버 설정해둔 mysql 접속정보를 연결해준다.
//		
//		
//				String sql2="INSERT INTO board(title, author, content, repRoot, repStep, repIndent) VALUES(?,?,?,?,?,?)";
//				
//				
//				pstmt=conn.prepareStatement(sql2);
//				pstmt.setString(1, _title);
//				pstmt.setString(2, _author);
//				pstmt.setString(3, _content);
//				pstmt.setInt(4, _repRoot);
//				pstmt.setInt(5, _repStep+1);
//				pstmt.setInt(6, _repIndent+1);
//				
//			
//				int n=pstmt.executeUpdate();
//				
//			
//	
//	
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//				
//				
//			}catch(Exception e) {
//				e.printStackTrace();
//				
//			}
//		
//			
//		}
//	
//	
//	
//	}


public void reply(int _num, String _title, String _author, String _content, int _repRoot, int _repStep, int _repIndent) {
	
	makeReply(_repRoot, _repStep);
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	try {
		conn=DriverManager.getConnection(url,userid,password);
		
		String query="INSERT INTO board(title, author, content, repRoot, repStep, repIndent) VALUES(?, ?, ?, ?, ?, ?)";
		pstmt=conn.prepareStatement(query);
		pstmt.setString(1, _title);
		pstmt.setString(2, _author);
		pstmt.setString(3, _content);
		pstmt.setInt(4, _repRoot);
		pstmt.setInt(5, _repStep+1);
		pstmt.setInt(6, _repIndent+1);
		
		int n=pstmt.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


}
