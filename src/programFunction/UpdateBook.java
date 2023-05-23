package programFunction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import book.Book;
import dbConnection.DatabaseConnection;
import printer.BookPrinter;

public class UpdateBook {
	
	private static Book setTarget(Scanner sc, String id, String pw) {
		
		try (Connection conn = DatabaseConnection.makeConnection(id, pw)){
			System.out.println("책의 이름 혹은 저자를 입력해주세요. 전체 이름을 입력하지 않아도 됩니다.");
			String titleOrAuthor = sc.nextLine();
			String query = "SELECT * FROM book WHERE title like '%"+titleOrAuthor+"%' or author like '%"+titleOrAuthor+"%'";
			PreparedStatement ps = conn.prepareStatement(query);
//			ps.setString(1, titleOrAuthor);
//			ps.setString(2, titleOrAuthor);
			
			System.out.println(ps);
			
			ResultSet rs = ps.executeQuery();
			List<Book> bookList = new LinkedList<>();
			List<Integer> searchedBookId = new LinkedList<>();
			
			while(rs.next()) {
				Book currentBook = new Book(
						rs.getInt("ID"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getDate("writtenDate"),
						rs.getString("company"),
						rs.getInt("price"),
						rs.getString("category"),
						rs.getInt("remain"),
						rs.getInt("saledNum")
						);
				bookList.add(currentBook);
				searchedBookId.add(rs.getInt("ID"));
			}
			
			conn.close();
			ps.close();
			rs.close();
			
			if(bookList.isEmpty()) {
				System.out.println("검색결과가 존재하지 않습니다.");
				return null;
			}
			else if(bookList.size() == 1) {
				System.out.println("아래의 책을 변경합니다");
				BookPrinter.printBooks(bookList);
				return bookList.get(0);
			}else {
				System.out.println("검색된 정보를 출력합니다.");
				System.out.println("업데이트 할 책의 ID를 입력하세요.");
				BookPrinter.printBooks(bookList);
				int updateTargetId = sc.nextInt();
				sc.nextLine();
				for(Book book : bookList) {
					if(book.getId()==updateTargetId) return book;
				}
				
				return null;
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public static int updateBook(Scanner sc, String id, String pw) {
		Book target = setTarget(sc, id, pw);
		String userSelect = "";
		LinkedList targetList = new LinkedList<>();
		targetList.add(target);
		while(!userSelect.equals("exit")) {
			BookPrinter.printBooks(targetList);
			userSelect = sc.nextLine();
			switch(userSelect) {
				case "title":
					String updatedTitle = sc.nextLine();
					target.setTitle(updatedTitle);
					break;
					
				case "author":
					String updatedAuthor = sc.nextLine();
					target.setAuthor(updatedAuthor);
					break;
					
				case "writtenDate":
					Date date = Date.valueOf(sc.nextLine());
					target.setWrittenDate(date);
					break;
					
				case "company":
					String updatedCompany = sc.nextLine();
					target.setCompany(updatedCompany);
					break;
					
				case "price":
					int updatedPrice = sc.nextInt();
					sc.nextLine();
					target.setPrice(updatedPrice);
					break;
					
				case "category":
					String updatedCategory = sc.nextLine();
					target.setCategory(updatedCategory);
					break;
					
				case "remain":
					int updatedRemain = sc.nextInt();
					target.setRemain(updatedRemain);
					break;
					
				case "saledNum":
					int updatedSaledNum = sc.nextInt();
					target.setSaledNum(updatedSaledNum);
					break;
					
				case "exit":
					break;
				
			}
			
			targetList.set(0, target);
		}
		
		try (Connection conn = DatabaseConnection.makeConnection(id, pw)){
			String query = "UPDATE book set title = ?, author = ?, writtenDate = ?,"
						   + " company = ?, price = ?,  category = ?, remain = ?, saledNum = ? "
						   + "WHERE ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, target.getTitle());
			ps.setString(2, target.getAuthor());
			ps.setDate(3, target.getWrittenDate());
			ps.setString(4, target.getCompany());
			ps.setInt(5, target.getPrice());
			ps.setString(6, target.getCategory());
			ps.setInt(7, target.getRemain());
			ps.setInt(8, target.getSaledNum());
			ps.setInt(9, target.getId());
			
			
			int returnValue = ps.executeUpdate();
			
			conn.close();
			ps.close();
			
			
			return returnValue;


		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

}
