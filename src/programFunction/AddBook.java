package programFunction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dbConnection.DatabaseConnection;

public class AddBook {
	private static int dbContains(Scanner sc, String id, String pw, String title, String author, String company) throws SQLException {
		//넣으려는 객체가 DB에 존재하는지 확인하는 메소드
		Connection conn = DatabaseConnection.makeConnection(id, pw);
		String query = "SELECT * FROM book WHERE title=? AND author=? AND company = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, title);
		ps.setString(2, author);
		ps.setString(3, company);
		ResultSet rs= ps.executeQuery();
		
		int result = -1;
		if(rs.next()) {result = rs.getInt("ID");}

		conn.close();
		ps.close();
		rs.close();
		
		if(result !=0) return result;
		else return result;
	}
	
	
	private static int addRemains(Scanner sc, String connectonId, String pw, int id) throws SQLException {
		Connection conn = DatabaseConnection.makeConnection(connectonId, pw);
		//책이 존재할 경우, remain값을 입력받아 업데이트 한다. 
		System.out.println("재입고가 얼마만큼 되었나요?");
		int remains = sc.nextInt();
		if(remains < 0) {
			System.out.println("잘못된 값입니다. 메인화면으로 돌아갑니다.");
			
			conn.close();
			return -1;
		}else {
			String query = "UPDATE book SET remain = remain + ? WHERE ID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, remains);
			ps.setInt(2, id);
			int return_value = ps.executeUpdate(); // update된 row가 반환된다. 
			
			conn.close();
			ps.close();
			return return_value;
		}
		
	}
	
	private static int addNewBooks(Scanner sc, String id, String pw, String title, String author, String company) throws SQLException {
		Connection conn = DatabaseConnection.makeConnection(id, pw);

		//존재가 DB에 없다면, row 추가(전체 입력)
		System.out.println("검색결과, 책이 존재하지 않습니다.");
		System.out.println("새로 입고하려는 책의 정보를 입력해주세요!");

		System.out.println("새로 입고하려는 책의 출판일은 언제인가요?");
		Date date = Date.valueOf(sc.nextLine());
		
		System.out.println("책의 가격은 얼마인가요?");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.println("책의 카테고리는 무엇인가요??");
		String category = sc.nextLine();
		
		System.out.println("책의 재고량은 얼마인가요??");
		int remains = sc.nextInt();
		sc.nextLine();
		
		System.out.println("현재까지 몇 권이 팔렸나요??");
		int saledNum = sc.nextInt();
		sc.nextLine();
		
		String query = "INSERT INTO book(title, author, writtenDate, company, price, category, remain, saledNum)"
					 + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, title);
		ps.setString(2, author);
		ps.setDate(3, date);
		ps.setString(4, company);
		ps.setInt(5, price);
		ps.setString(6, category);
		ps.setInt(7, remains);
		ps.setInt(8, saledNum);
		int returnValue = ps.executeUpdate();
		
		
		conn.close();
		ps.close();
		return returnValue;
		
	}
	
	public static void addBook(Scanner sc, String id, String pw) {
		
		System.out.println("책이 DB에 존재하는지 확인합니다.");
		System.out.println("책의 제목을 입력하세요.");
		String title = sc.nextLine();
		
		System.out.println("저자를 입력하세요.");
		String author = sc.nextLine();
		
		System.out.println("출판사를 입력하세요.");
		String company = sc.nextLine();
		
		
		int resultNum = -1;
		try {
			resultNum = dbContains(sc, id, pw, title, author, company);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Addbook dbContains");
		}
		
		
		
		if(resultNum > 0) {
			try {
				addRemains(sc, id, pw, resultNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Add Remains");
			}
		}else {
			try {
				addNewBooks(sc, id, pw, title, author, company);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
