//package pkg;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Scanner;
//
//public class JDBCTemplate {
//   //   // MySQL 데이터베이스 연결 정보
//   //   String url = "jdbc:mysql://localhost:3306/db01"; // "db_name"은 사용하려는 데이터베이스 이름으로 변경해야 합니다.
//   //   String username = "root"; // MySQL 사용자 이름으로 변경해야 합니다.
//   //   String password = "1234"; // MySQL 비밀번호로 변경해야 합니다.
//
//
//   public static Connection connectToDatabase() {
//      Connection conn = null;
//
//      try {
//         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db01", "root", "1234");
//         //         System.out.println("MySQL 데이터베이스에 연결되었습니다.");
//
//      } catch (SQLException e) {
//         System.err.println("MySQL 데이터베이스 연결 중 오류가 발생하였습니다.");
//         e.printStackTrace();
//      }
//      return conn;
//   }
//
//   public static void close(Connection conn) {
//      if (conn != null) {
//         try {
//            conn.close();
//            //                  System.out.println("JDBC 연결이 종료되었습니다.");
//         } catch (SQLException e) {
//            e.printStackTrace();
//         }
//      }
//   }
//
//
//   public void DB_SelectBookTitle(Connection conn, String str) {
//      try {
//         // SQL 문 준비
//         String sql = "SELECT * FROM book WHERE title=?";
//         PreparedStatement statement = conn.prepareStatement(sql);
//         statement.setString(1, str);
//
//         // 쿼리 실행 및 결과 처리
//         ResultSet resultSet = statement.executeQuery();
//         while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String title = resultSet.getString("title"); 
//            String author = resultSet.getString("author");
//            String writtenDate = resultSet.getString("writtenDate");
//            String company = resultSet.getString("company");
//            int price = resultSet.getInt("price");
//            String category = resultSet.getString("category");
//            int remain = resultSet.getInt("remain");
//            int saledNum = resultSet.getInt("saledNum");
//
//
//            System.out.printf("%-2s %-10s %-10s %-10s %-10s %-5s %-3s %-2s %-2s\n", "ID", "Title", "Author", "WrittenDate", "Company", "Price",
//                  "Category", "Remain", "SaledNum");
//
//            System.out.printf("%-2d %-10s %-10s %-10s %-10s %-5d %-8s %-6d %-8d\n", id, title, author, writtenDate, company, price,
//                  category, remain, saledNum);
//            System.out.println();
//         }
//
//
//      } catch (SQLException e) {
//         System.err.println("오류 발생");
//         e.printStackTrace();
//      }
//
//   }
//
//   public void DB_SelectBookAuthor(Connection conn, String str) {
//      try {
//         // SQL 문 준비
//         String sql = "SELECT * FROM book WHERE author=?";
//         PreparedStatement statement = conn.prepareStatement(sql);
//         statement.setString(1, str);
//
//         // 쿼리 실행 및 결과 처리
//         ResultSet resultSet = statement.executeQuery();
//         while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String title = resultSet.getString("title"); 
//            String author = resultSet.getString("author");
//            String writtenDate = resultSet.getString("writtenDate");
//            String company = resultSet.getString("company");
//            int price = resultSet.getInt("price");
//            String category = resultSet.getString("category");
//            int remain = resultSet.getInt("remain");
//            int saledNum = resultSet.getInt("saledNum");
//
//
//            System.out.printf("%-2s %-10s %-10s %-10s %-10s %-5s %-3s %-2s %-2s\n", "ID", "Title", "Author", "WrittenDate", "Company", "Price",
//                  "Category", "Remain", "SaledNum");
//
//            System.out.printf("%-2d %-10s %-10s %-10s %-10s %-5d %-8s %-6d %-8d\n", id, title, author, writtenDate, company, price,
//                  category, remain, saledNum);
//            System.out.println();
//         }
//
//
//      } catch (SQLException e) {
//         System.err.println("오류 발생");
//         e.printStackTrace();
//      }
//   }
//
//
//   public void DB_SelectBookCategory(Connection conn, String str) {
//      try {
//         // SQL 문 준비
//         String sql = "SELECT * FROM book WHERE category=?";
//         PreparedStatement statement = conn.prepareStatement(sql);
//         statement.setString(1, str);
//
//         // 쿼리 실행 및 결과 처리
//         ResultSet resultSet = statement.executeQuery();
//         while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String title = resultSet.getString("title"); 
//            String author = resultSet.getString("author");
//            String writtenDate = resultSet.getString("writtenDate");
//            String company = resultSet.getString("company");
//            int price = resultSet.getInt("price");
//            String category = resultSet.getString("category");
//            int remain = resultSet.getInt("remain");
//            int saledNum = resultSet.getInt("saledNum");
//
//
//            System.out.printf("%-2s %-10s %-10s %-10s %-10s %-5s %-3s %-2s %-2s\n", "ID", "Title", "Author", "WrittenDate", "Company", "Price",
//                  "Category", "Remain", "SaledNum");
//
//            System.out.printf("%-2d %-10s %-10s %-10s %-10s %-5d %-8s %-6d %-8d\n", id, title, author, writtenDate, company, price,
//                  category, remain, saledNum);
//            System.out.println();
//         }
//
//
//      } catch (SQLException e) {
//         System.err.println("오류 발생");
//         e.printStackTrace();
//      }
//
//   }
//   
//   public void DB_SelectBookBestSeller(Connection conn) {
//      try {
//         // SQL 문 준비
//         String sql = "SELECT * FROM book order by saledNum desc limit 5";
//         PreparedStatement statement = conn.prepareStatement(sql);
//
//         // 쿼리 실행 및 결과 처리
//         ResultSet resultSet = statement.executeQuery();
//         while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String title = resultSet.getString("title"); 
//            String author = resultSet.getString("author");
//            String writtenDate = resultSet.getString("writtenDate");
//            String company = resultSet.getString("company");
//            int price = resultSet.getInt("price");
//            String category = resultSet.getString("category");
//            int remain = resultSet.getInt("remain");
//            int saledNum = resultSet.getInt("saledNum");
//
//
//            System.out.printf("%-5s %-28s %-10s %-15s %-10s %-15s %-15s %-15s %-15s\n", "ID", "Title", "Author", "WrittenDate", "Company", "Price",
//                  "Category", "Remain", "SaledNum");
//
//            System.out.printf("%-5d %-28s %-10s %-15s %-10s %-15d %-15s %-15d %-15d\n", id, title, author, writtenDate, company, price,
//                  category, remain, saledNum);
//            System.out.println();
//         }
//
//
//      } catch (SQLException e) {
//         System.err.println("오류 발생");
//         e.printStackTrace();
//      }
//
//   }
//   
//   
//   public void DB_SelectBookBestPrice(Connection conn) {
//      try {
//         // SQL 문 준비
//         String sql = "SELECT * FROM book order by price desc limit 5";
//         PreparedStatement statement = conn.prepareStatement(sql);
//
//         // 쿼리 실행 및 결과 처리
//         ResultSet resultSet = statement.executeQuery();
//         while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String title = resultSet.getString("title"); 
//            String author = resultSet.getString("author");
//            String writtenDate = resultSet.getString("writtenDate");
//            String company = resultSet.getString("company");
//            int price = resultSet.getInt("price");
//            String category = resultSet.getString("category");
//            int remain = resultSet.getInt("remain");
//            int saledNum = resultSet.getInt("saledNum");
//
//
//            System.out.printf("%-5s %-28s %-10s %-15s %-10s %-15s %-15s %-15s %-15s\n", "ID", "Title", "Author", "WrittenDate", "Company", "Price",
//                  "Category", "Remain", "SaledNum");
//
//            System.out.printf("%-5d %-28s %-10s %-15s %-10s %-15d %-15s %-15d %-15d\n", id, title, author, writtenDate, company, price,
//                  category, remain, saledNum);
//            System.out.println();
//         }
//
//
//      } catch (SQLException e) {
//         System.err.println("오류 발생");
//         e.printStackTrace();
//      }
//
//   }
//   
//   public void DB_DeleteBookTitleList(Connection conn, String str) {
//      try {
//         // SQL 문 준비
//         String sql = "SELECT * FROM book WHERE title LIKE ?";
//         PreparedStatement statement = conn.prepareStatement(sql);
//         String names = '%'+str+'%';
//         statement.setString(1, names);
//         
//
//
//         // 쿼리 실행 및 결과 처리
//         ResultSet resultSet = statement.executeQuery();
//         while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String title = resultSet.getString("title"); 
//            String author = resultSet.getString("author");
//            String writtenDate = resultSet.getString("writtenDate");
//            String company = resultSet.getString("company");
//            int price = resultSet.getInt("price");
//            String category = resultSet.getString("category");
//            int remain = resultSet.getInt("remain");
//            int saledNum = resultSet.getInt("saledNum");
//
//            
//            System.out.printf("%-5s %-28s %-10s %-15s %-10s %-15s %-15s %-15s %-15s\n", "ID", "Title", "Author", "WrittenDate", "Company", "Price",
//                  "Category", "Remain", "SaledNum");
//
//            System.out.printf("%-5d %-28s %-10s %-15s %-10s %-15d %-15s %-15d %-15d\n", id, title, author, writtenDate, company, price,
//                  category, remain, saledNum);
//            System.out.println();
//         }
//
//
//      } catch (SQLException e) {
//         System.err.println("오류 발생");
//         e.printStackTrace();
//      }
//
//   }
//   public void DB_DeleteBook(Connection conn, int book_id) {
//      try {
//           // SQL 문 준비
//           String sql = "DELETE FROM book WHERE id=?";
//           PreparedStatement statement = conn.prepareStatement(sql);
//           statement.setInt(1, book_id);
//
//           // 쿼리 실행 및 결과 처리
//           int rowsAffected = statement.executeUpdate();
//           if (rowsAffected > 0) {
//               System.out.println("삭제 완료.");
//               System.out.println();
//           } else {
//               System.out.println("해당하는 도서를 찾을 수 없습니다.");
//               System.out.println();
//           }
//       } catch (SQLException e) {
//           System.err.println("오류 발생");
//           e.printStackTrace();
//       }
//   }
//   
//   
//   public void DB_DeleteBookAll(Connection conn) {
//      try {
//           // SQL 문 준비
//           String sql = "DELETE FROM book";
//           PreparedStatement statement = conn.prepareStatement(sql);
//
//
//           // 쿼리 실행 및 결과 처리
//           int rowsAffected = statement.executeUpdate();
//           if (rowsAffected > 0) {
//               System.out.println("전체 삭제 완료.");
//               System.out.println();
//           } else {
//               System.out.println("등록된 도서가 없습니다..");
//               System.out.println();
//           }
//       } catch (SQLException e) {
//           System.err.println("오류 발생");
//           e.printStackTrace();
//       }
//   }
//}