import java.sql.Connection;
import java.util.Objects;
import java.util.Scanner;

import dbConnection.DatabaseConnection;
import printer.Menu;
import programFunction.AddBook;

public class Main {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);        
     
        boolean isContinue = true;
		while(isContinue) {
			Menu.printMenu();
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				AddBook.addBook(sc);
				break;
			
			case 2:
				System.out.println("Update");
				break;
				
			case 3:
				System.out.println("Delete");
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
