package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConnection {
	
	public static Connection makeConnection(String id, String pw) {
//		System.out.println("데이터베이스 ID를 입력하세요");
//		String id = sc.nextLine();
//		System.out.println("데이터베이스 비밀번호를 입력하세요");
//		String pw = sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/db01?autoReconnect=true"; 
        
      try {
    	  Connection conn = DriverManager.getConnection(url, id, pw);
          System.out.println("MySQL 데이터베이스에 연결되었습니다.");
          return conn;
      } catch (SQLException e) {
          System.err.println("MySQL 데이터베이스 연결 중 오류가 발생하였습니다.");
          e.printStackTrace();
          return null;
      }
	}
}
