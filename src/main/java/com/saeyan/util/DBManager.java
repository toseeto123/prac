package com.saeyan.util;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class DBManager {

	//반환형으로 Connection 사용 (con으로 반환)
	public static Connection getConnction(){
		Connection con=null;
		//dbcp연
		try {
			Context init=new InitialContext();
			Context env=(Context)init.lookup("java:/comp/env"); //object 형으로 가져오는 lookup형변환
			DataSource ds=(DataSource)env.lookup("jdbc/Product"); //DataSource 형변환 --> server.xml에서 이름 Product로 변경하기 -> 연결되었는지 확인하기위해서는 메인이 존재해야함
			con=ds.getConnection();
			System.out.println("데이터베이스연결성공");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return con;
	}
	
//	select를 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection con,Statement stmt,ResultSet rs) {
		try {
			rs.close();
			stmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	insert,delete,update 를 수행한후 리소스해제를 위한 메소드 (오버로딩) 
//	static으로 정한이유는 클래스이름으로 바로 접근하기위함.
	public static void close(Connection con,Statement stmt) {
		try {
			stmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
