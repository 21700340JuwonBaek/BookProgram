package printer;

import java.util.List;

import book.Book;

public class BookPrinter {
	public static void printBooks(List<Book> bookList) {
		for(Book book: bookList) {
			System.out.println(book.getId() + "   " + book.getTitle() + "   " + book.getAuthor() + "   " 
			+book.getWrittenDate() + "   "+ book.getCategory() + "   " + book.getCompany() + "   " 
			+ book.getPrice() + "    " + book.getRemain() + "   " + book.getSaledNum());
		}
	}
}
