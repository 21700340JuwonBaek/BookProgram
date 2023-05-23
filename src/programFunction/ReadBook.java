package programFunction;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dbConnection.DatabaseConnection;



public class ReadBook {

	public static void bookSelect(Scanner sc, String id, String pw) {
		System.out.println("=== 조회 방법 ===");
		System.out.println("1. 제목 검색");
		System.out.println("2. 저자 검색");
		System.out.println("3. 카테고리 검색");
		System.out.println("4. 판매량 Top5");
		System.out.println("5. 금액 Top5");
		System.out.println("9. 메인 화면");
		System.out.println("============");
		System.out.print("번호 입력 : ");
		int num = sc.nextInt();
		sc.nextLine(); 

		switch(num) {
		case 1 :
			System.out.print("제목 입력 : ");
			String title = sc.nextLine();
			bookTitle(sc, id, pw, title);
			break;
		case 2 :
			System.out.print("저자 입력 : ");
			String author = sc.nextLine();
			bookAuthor(sc, id, pw, author);
			break;
		case 3 :
			System.out.print("카테고리 입력 : ");
			String category = sc.nextLine();
			bookCategory(sc, id, pw, category);
			break;
		case 4 :
			System.out.print("판매량 Top5 : ");
			System.out.println("");
			bookBestSeller(sc, id, pw);
			break;   
		case 5 :
			System.out.print("금액 Top5 : ");
			System.out.println("");
			bookBestPrice(sc, id, pw);
			break;   
		case 9 :
			break;


		default : 
			System.out.println("번호를 다시 입력해주세요.");
		}

	}


	public static void bookTitle(Scanner sc, String DB_id, String pw, String title) {

		Connection conn = DatabaseConnection.makeConnection(DB_id, pw);

		try {
			// SQL 문 준비
			String sql = "SELECT * FROM book WHERE title like '%" + title + "%'" ;
			PreparedStatement statement = conn.prepareStatement(sql);

			// 쿼리 실행 및 결과 처리
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String author = resultSet.getString("author");
				String writtenDate = resultSet.getString("writtenDate");
				String company = resultSet.getString("company");
				int price = resultSet.getInt("price");
				String category = resultSet.getString("category");
				int remain = resultSet.getInt("remain");
				int saledNum = resultSet.getInt("saledNum");


				System.out.printf("%-2s %-10s %-10s %-10s %-10s %-5s %-3s %-2s %-2s\n", "ID", "Title", "Author", "WrittenDate", "Company", "Price",
						"Category", "Remain", "SaledNum");

				System.out.printf("%-2d %-10s %-10s %-10s %-10s %-5d %-8s %-6d %-8d\n", id, title, author, writtenDate, company, price,
						category, remain, saledNum);
				System.out.println();
			}
			conn.close();
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.err.println("오류 발생");
			e.printStackTrace();
		}      
		System.out.println("===엔터키 누르면 메인화면..===");
		sc.nextLine();
	}

	public static void bookAuthor(Scanner sc, String DB_id, String pw, String author) {

		Connection conn = DatabaseConnection.makeConnection(DB_id, pw);

		try {
			// SQL 문 준비
			String sql = "SELECT * FROM book WHERE author=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, author);

			// 쿼리 실행 및 결과 처리
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title"); 
				String writtenDate = resultSet.getString("writtenDate");
				String company = resultSet.getString("company");
				int price = resultSet.getInt("price");
				String category = resultSet.getString("category");
				int remain = resultSet.getInt("remain");
				int saledNum = resultSet.getInt("saledNum");


				System.out.printf("%-2s %-10s %-10s %-10s %-10s %-5s %-3s %-2s %-2s\n", "ID", "Title", "Author", "WrittenDate", "Company", "Price",
						"Category", "Remain", "SaledNum");

				System.out.printf("%-2d %-10s %-10s %-10s %-10s %-5d %-8s %-6d %-8d\n", id, title, author, writtenDate, company, price,
						category, remain, saledNum);
				System.out.println();
			}

			conn.close();
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.err.println("오류 발생");
			e.printStackTrace();
		}
		System.out.println("===엔터키 누르면 메인화면..===");
		sc.nextLine();
	}

	public static void bookCategory(Scanner sc, String DB_id, String pw, String category) {

		Connection conn = DatabaseConnection.makeConnection(DB_id, pw);

		try {
			// SQL 문 준비
			String sql = "SELECT * FROM book WHERE category=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,category);

			// 쿼리 실행 및 결과 처리
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title"); 
				String author = resultSet.getString("author");
				String writtenDate = resultSet.getString("writtenDate");
				String company = resultSet.getString("company");
				int price = resultSet.getInt("price");
				int remain = resultSet.getInt("remain");
				int saledNum = resultSet.getInt("saledNum");


				System.out.printf("%-2s %-10s %-10s %-10s %-10s %-5s %-3s %-2s %-2s\n", "ID", "Title", "Author", "WrittenDate", "Company", "Price",
						"Category", "Remain", "SaledNum");

				System.out.printf("%-2d %-10s %-10s %-10s %-10s %-5d %-8s %-6d %-8d\n", id, title, author, writtenDate, company, price,
						category, remain, saledNum);
				System.out.println();
			}

			conn.close();
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.err.println("오류 발생");
			e.printStackTrace();
		}
		System.out.println("===엔터키 누르면 메인화면..===");
		sc.nextLine();
	}

	public static void bookBestSeller(Scanner sc, String DB_id, String pw) {

		Connection conn = DatabaseConnection.makeConnection(DB_id, pw);
		try {
			// SQL 문 준비
			String sql = "SELECT * FROM book order by saledNum desc limit 5";
			PreparedStatement statement = conn.prepareStatement(sql);

			// 쿼리 실행 및 결과 처리
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title"); 
				String author = resultSet.getString("author");
				String writtenDate = resultSet.getString("writtenDate");
				String company = resultSet.getString("company");
				int price = resultSet.getInt("price");
				String category = resultSet.getString("category");
				int remain = resultSet.getInt("remain");
				int saledNum = resultSet.getInt("saledNum");


				System.out.printf("%-5s %-28s %-10s %-15s %-10s %-15s %-15s %-15s %-15s\n", "ID", "Title", "Author", "WrittenDate", "Company", "Price",
						"Category", "Remain", "SaledNum");

				System.out.printf("%-5d %-28s %-10s %-15s %-10s %-15d %-15s %-15d %-15d\n", id, title, author, writtenDate, company, price,
						category, remain, saledNum);
				System.out.println();
			}
			conn.close();
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			System.err.println("오류 발생");
			e.printStackTrace();
		}
		System.out.println("===엔터키 누르면 메인화면..===");
		sc.nextLine();
	}

	public static void bookBestPrice(Scanner sc, String DB_id, String pw) {
		Connection conn = DatabaseConnection.makeConnection(DB_id, pw);

		try {
			// SQL 문 준비
			String sql = "SELECT * FROM book order by price desc limit 5";
			PreparedStatement statement = conn.prepareStatement(sql);

			// 쿼리 실행 및 결과 처리
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title"); 
				String author = resultSet.getString("author");
				String writtenDate = resultSet.getString("writtenDate");
				String company = resultSet.getString("company");
				int price = resultSet.getInt("price");
				String category = resultSet.getString("category");
				int remain = resultSet.getInt("remain");
				int saledNum = resultSet.getInt("saledNum");


				System.out.printf("%-5s %-28s %-10s %-15s %-10s %-15s %-15s %-15s %-15s\n", "ID", "Title", "Author", "WrittenDate", "Company", "Price",
						"Category", "Remain", "SaledNum");

				System.out.printf("%-5d %-28s %-10s %-15s %-10s %-15d %-15s %-15d %-15d\n", id, title, author, writtenDate, company, price,
						category, remain, saledNum);
				System.out.println();
			}


		} catch (SQLException e) {
			System.err.println("오류 발생");
			e.printStackTrace();
		}
		System.out.println("===엔터키 누르면 메인화면..===");
		sc.nextLine();
	}
}