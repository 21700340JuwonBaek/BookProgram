package programFunction;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import book.Book;
import dbConnection.DatabaseConnection;
import printer.BookPrinter;

public class DeleteBook {

   public static void Delete(Scanner sc, String id, String pw) {
      System.out.println("=== 조회 방법 ===");
      System.out.println("1. 제목 검색 후 삭제");
      System.out.println("2. 전체 삭제");
      System.out.println("============");
      System.out.print("번호 입력 : ");
      int num = sc.nextInt();
      sc.nextLine(); 
      switch(num) {
	      case 1 :
	    	  deleteBook(sc, id, pw, num);
	    	  break;
	    	  
	      case 2:	         
	    	  deleteAllBook(id, pw);
	         break;      
	         
	      case 9:
	    	  return;
	
	      default : 
	         System.out.println("번호를 다시 입력해주세요.");
      }
   }


   public static Book setDeleteTarget(Scanner sc, String id, String pw, String title) {
      Connection conn = DatabaseConnection.makeConnection(id, pw);
      String query = "SELECT * FROM book WHERE title LIKE ? or author LIKE ?";
      PreparedStatement ps;
	try {
		ps = conn.prepareStatement(query);
	      String names = '%'+title+'%';
	      ps.setString(1, names);	      
	      ps.setString(2, names);	     
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
				System.out.println("아래의 책을 삭제합니다");
				BookPrinter.printBooks(bookList);
				return bookList.get(0);
			}else {
				System.out.println("검색된 정보를 출력합니다.");
				System.out.println("삭제 할 책의 ID를 입력하세요.");
				BookPrinter.printBooks(bookList);
				int updateTargetId = sc.nextInt();
				sc.nextLine();
				for(Book book : bookList) {
					if(book.getId()==updateTargetId) return book;
				}
				
				return null;
				
			}

	      
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}

   }
   
   
   public static void deleteBook(Scanner sc, String id, String pw, int userMenuSelect) {
	   int target;
	   Connection conn = null;
	   if(userMenuSelect == 1) {
		   conn = DatabaseConnection.makeConnection(id, pw);
		   String titleOrAuthor = sc.nextLine();
		   Book book = setDeleteTarget(sc, id, pw, titleOrAuthor);
		   target = book.getId();}

	   else {
		   target = sc.nextInt();
		   sc.nextLine();
	   }

	   String query = "DELETE FROM book WHERE id=?";
	   try {
		   PreparedStatement ps = conn.prepareStatement(query);
		   ps.setInt(1, target);
		   ps.executeUpdate();

		   conn.close();
		   ps.close();
	   } catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
   }
   
   

   public static void deleteAllBook(String id, String pw) {
	      Connection conn = DatabaseConnection.makeConnection(id, pw);
	      String query = "DELETE FROM book";
	      try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			conn.clearWarnings();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      

   }

}