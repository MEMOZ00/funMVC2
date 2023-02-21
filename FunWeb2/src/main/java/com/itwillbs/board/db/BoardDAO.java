package com.itwillbs.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {
	public Connection getConnection() throws Exception{
		//서버에서 미리 1, 2 단계 => 디비연결 => 이름을 불러 연결정보를 가져오기
		// => 속도 향상, 디비연결 정보 수정 최소화
		// DataBase Connection Pool (DBCP)=> 디비 연결정보 서버 저장
		// 1. META-INF context.xml (디비연결정보)
		// 2. MemberDAO 디비연결정보 불러서 사용
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		Connection con=ds.getConnection();
		return con;
	}
	
	public void insertBoard(BoardDTO dto) {
		System.out.println("BoardDAO insertBoard()");
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			// 예외가 발생할 가능성이 높은 명령(1~4단계)
			// 1~2 단계
			con=getConnection();
			// num 구하기
			int num=1;
			//3  최대 num + 1 
			String sql="select max(num) from board";
			pstmt=con.prepareStatement(sql);
			//4
			rs=pstmt.executeQuery();
			//5
			if(rs.next()) {
				num=rs.getInt("max(num)")+1;
			}
			// 3단계 SQL구문 만들어서 실행할 준비(insert)
			sql="insert into board(num, name, subject, content, readcount, date, file, re_ref, re_lev, re_seq) values(?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			// ? 채워넣기
			pstmt.setInt(1, num);  
			pstmt.setString(2, dto.getName()); 
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getReadcount());
			pstmt.setTimestamp(6, dto.getDate());
			//파일추가
			pstmt.setString(7, dto.getFile());
			//답글관련 일반글(기준글) 추가
			pstmt.setInt(8, num); //re_ref 그룹번호     num
			pstmt.setInt(9, 0); //re_lev 답글 들여쓰기  0
			pstmt.setInt(10, 0); //re_seq 답글순서     0
			
			// 4단계 SQL구문을 실행(insert,update,delete)
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// 예외가 발생하면 처리하는 곳
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return;
	}//insertBoard() 메서드
	
	public void reinsertBoard(BoardDTO dto) {
		System.out.println("BoardDAO reinsertBoard()");
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			// 예외가 발생할 가능성이 높은 명령(1~4단계)
			// 1~2 단계
			con=getConnection();
			// num 구하기
			int num=1;
			//3  최대 num + 1 
			String sql="select max(num) from board";
			pstmt=con.prepareStatement(sql);
			//4
			rs=pstmt.executeQuery();
			//5
			if(rs.next()) {
				num=rs.getInt("max(num)")+1;
			}
			// 3단계 SQL구문 만들어서 실행할 준비(insert)
			sql="insert into board(num, name, subject, content, readcount, date, file, re_ref, re_lev, re_seq) values(?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			// ? 채워넣기
			pstmt.setInt(1, num);  
			pstmt.setString(2, dto.getName()); 
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getReadcount());
			pstmt.setTimestamp(6, dto.getDate());
			//파일추가
			pstmt.setString(7, dto.getFile());
			//답글관련  추가
			pstmt.setInt(8, dto.getRe_ref()); //re_ref 그룹번호        그대로
			pstmt.setInt(9, dto.getRe_lev()+1); //re_lev 답글 들여쓰기  기존+1
			pstmt.setInt(10, dto.getRe_seq()+1); //re_seq 답글순서     기존+1
			
			// 4단계 SQL구문을 실행(insert,update,delete)
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// 예외가 발생하면 처리하는 곳
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return;
	}//insertBoard() 메서드
	
	
	// 리턴할형 ArrayList<BoardDTO>  getBoardList(int startRow,int pageSize) 메서드 정의 
	public ArrayList<BoardDTO> getBoardList(int startRow,int pageSize){
		System.out.println("BoardDAO getBoardList()");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BoardDTO> boardList=new ArrayList<>();
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			// 기본 num기준 오름차순 => 최근글 위로 올라오게 정렬 (num 내림차순)
//			String sql="select * from board order by num desc";
//			String sql="select * from board order by num desc limit 시작행-1, 몇개";
			String sql="select * from board order by num desc limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()) {
				// 하나의 글의 바구니에 저장
				BoardDTO dto=new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setDate(rs.getTimestamp("date"));
				// 바구니의 주소값을 배열 한칸에 저장
				boardList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return boardList;
	}//
	
	// 리턴할형 ArrayList<BoardDTO>  getBoardList(int startRow,int pageSize, String search) 메서드 정의 
	public ArrayList<BoardDTO> getBoardList(int startRow,int pageSize, String search){
		System.out.println("BoardDAO getBoardList()");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BoardDTO> boardList=new ArrayList<>();
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			// 기본 num기준 오름차순 => 최근글 위로 올라오게 정렬 (num 내림차순)
//			String sql="select * from board order by num desc";
//			String sql="select * from board order by num desc limit 시작행-1, 몇개";
//			String sql="select * from board where subject like '%검색어%'  order by num desc limit ?, ?";
			String sql="select * from board where subject like ? order by num desc limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow-1);
			pstmt.setInt(3, pageSize);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()) {
				// 하나의 글의 바구니에 저장
				BoardDTO dto=new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setDate(rs.getTimestamp("date"));
				// 바구니의 주소값을 배열 한칸에 저장
				boardList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return boardList;
	}//
	
	// 리턴할형 BoardDTO  getBoard(int num) 메서드 정의 
	public BoardDTO getBoard(int num) {
		System.out.println("BoardDAO getBoard()");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BoardDTO dto=null;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select * from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()) {
				// 하나의 글의 바구니에 저장
				dto=new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setDate(rs.getTimestamp("date"));
				//file 추가
				dto.setFile(rs.getString("file"));
				//답글 관련 추가
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_seq(rs.getInt("re_seq"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return dto;
	}//
	
	// 리턴할형 없음 updateBoard(BoardDTO dto) 메서드 정의
	public void updateBoard(BoardDTO dto) {
		System.out.println("BoardDAO updateBoard()");
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="update board set subject=?, content=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
			// 4단계 SQL구문을 실행(insert,update,delete)
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
	}//
	
	
	// 리턴할형 없음 fupdateBoard(BoardDTO dto) 메서드 정의
	public void fupdateBoard(BoardDTO dto) {
		System.out.println("BoardDAO fupdateBoard()");
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="update board set subject=?, content=?, file=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			// file 추가
			pstmt.setString(3, dto.getFile());
			
			pstmt.setInt(4, dto.getNum());
			// 4단계 SQL구문을 실행(insert,update,delete)
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
	}//
	
	
	// 리턴할형 없음 deleteBoard(int num) 메서드 정의
	public void deleteBoard(int num) {
		System.out.println("BoardDAO deleteBoard()");
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="delete from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			// 4단계 SQL구문을 실행(insert,update,delete)
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
	}//
	
	// int 리턴할형 getBoardCount() 메서드 정의 
	public int getBoardCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select count(*) from board";
			pstmt=con.prepareStatement(sql);
			//4
			rs=pstmt.executeQuery();
			//5
			if(rs.next()) {
				count=rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
				if(rs!=null) try { rs.close();} catch (Exception e2) {}
				if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
				if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return count;
	}//
	
	// int 리턴할형 getBoardCount(String search) 메서드 정의 
	public int getBoardCount(String search) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select count(*) from board where subject like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			//4
			rs=pstmt.executeQuery();
			//5
			if(rs.next()) {
				count=rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
				if(rs!=null) try { rs.close();} catch (Exception e2) {}
				if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
				if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return count;
	}//
	
}//클래스
