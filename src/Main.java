import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

import dbConnection.DatabaseConnection;
import printer.Menu;
import programFunction.AddBook;
import programFunction.DeleteBook;
import programFunction.ReadBook;
import programFunction.UpdateBook;

public class Main {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);        
        System.out.println("DB ID를 입력해주세요");
       String id = sc.nextLine();
       System.out.println("DB PW를 입력해주세요");
       String pw = sc.nextLine();
       Connection check = DatabaseConnection.makeConnection(id, pw);
       if(check == null) {
    	   System.out.println("아이디, 비밀번호가 올바르지 않습니다.");
       	System.exit(-1);

       }else {
    	   try {
			check.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
     
        boolean isContinue = true;
		while(isContinue) {
			Menu.printMenu();
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				AddBook.addBook(sc, id, pw);
				break;
			
			case 2:
				UpdateBook.updateBook(sc, id, pw);
				break;
				
			case 3:
				DeleteBook.Delete(sc, id, pw);
				break;
				
			case 4:
				ReadBook.bookSelect(sc, id, pw);
				break;
				
			case 9:
				isContinue = false;
				break;
				
			default:
				System.out.println("잘못입력하셨습니다");
			}
			
		}
	}
}