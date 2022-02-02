package org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.entity.BoardDTO;

public class BoardDAO2 {

	DataSource dataFactory;
	public BoardDAO2() {
		try {
			Context ctx=new InitialContext();
			dataFactory=(DataSource)ctx.lookup("java:comp/env/jdbc/Mysql");
		}catch(Exception e) {
			
		}
	}
	
	public ArrayList<BoardDTO> list() {
		
		ArrayList<BoardDTO> list=new ArrayList<BoardDTO>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataFactory.getConnection();
			String sql="SELECT num, author, title, content, date_format(writeday, '%Y.%m.%d') as writeday, readCnt, repRoot, repStep, repIndent FROM board ORDER BY repRoot DESC, repStep ASC";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int num=rs.getInt("num");
				String author=rs.getString("author");
				String title=rs.getString("title");;
				String content=rs.getString("content");;
				String writeday=rs.getString("writeday");;
				int readCnt=rs.getInt("readCnt");
				int repRoot=rs.getInt("repRoot");
				int repStep=rs.getInt("repStep");
				int repIndent=rs.getInt("repIndent");
				
				BoardDTO data=new BoardDTO();
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadCnt(readCnt);
				data.setRepRoot(repRoot);
				data.setRepStep(repStep);
				data.setRepIndent(repIndent);
				
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
	
	public void write(String _title, String _author, String _content) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int currval=0;
		
		try {
			conn=dataFactory.getConnection();
			String sql="SELECT ifnull(max(num), 0)+1 FROM board";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) currval=rs.getInt(1);
			
			String query="INSERT INTO board(title, author, content, repRoot, repStep, repIndent) VALUES(?, ?, ?, '"+currval+"', 0, 0)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, _title);
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			
			int n=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void readCnt(int _num) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=dataFactory.getConnection();
			String query="UPDATE board SET readcnt=readcnt+1 WHERE num="+_num;
			pstmt=conn.prepareStatement(query);
			
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

	public BoardDTO retrieve(int _num) {
		
		readCnt(_num);
		
		BoardDTO data=new BoardDTO();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataFactory.getConnection();
			String sql="SELECT * FROM board WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, _num);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int num=rs.getInt("num");
				String author=rs.getString("author");
				String title=rs.getString("title");;
				String content=rs.getString("content");;
				String writeday=rs.getString("writeday");;
				int readCnt=rs.getInt("readCnt");
				
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadCnt(readCnt);
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
		
		return data;
	}
	
	public void update(int _num, String _title, String _author, String _content) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=dataFactory.getConnection();
			
			String query="UPDATE board SET title=?, author=?, content=? WHERE num=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, _title);
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			pstmt.setInt(4, _num);
			
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
	
	public void delete(int _num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=dataFactory.getConnection();
			
			String query="DELETE FROM board WHERE num=?";
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(1, _num);
			
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
	
	public ArrayList<BoardDTO> search(String _searchName, String _searchValue){
		ArrayList<BoardDTO> list=new ArrayList<BoardDTO>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataFactory.getConnection();
			String sql="SELECT num, author, title, content, date_format(writeday, '%Y.%m.%d') as writeday, readCnt FROM board";
			if(_searchName.equals("title")) {
				sql +=" WHERE title LIKE ?";
			}else {
				sql +=" WHERE author LIKE ?";
			}
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+_searchValue+"%");
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
	
	public BoardDTO replyui(int _num) {
		BoardDTO data=new BoardDTO();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataFactory.getConnection();
			String sql="SELECT num, author, title, content, date_format(writeday, '%Y.%m.%d') as writeday, readCnt, repRoot, repStep, repIndent FROM board WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, _num);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
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
			
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	public void makeReply(int _repRoot, int _repStep) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=dataFactory.getConnection();
			String query="UPDATE board SET repStep=repStep+1 WHERE repRoot=? AND repStep > ?";
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(1, _repRoot);
			pstmt.setInt(2, _repStep);
			
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
	
	
	public void reply(int _num, String _title, String _author, String _content, int _repRoot, int _repStep, int _repIndent) {
		
		makeReply(_repRoot, _repStep);
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=dataFactory.getConnection();
			
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








