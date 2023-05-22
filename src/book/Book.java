package book;

import java.sql.Date;

public class Book {
	private int id;
	private String title;
	private String author;
	private Date writtenDate;
	private String company;
	private int price;
	private String category;
	private int remain;
	private int saledNum;
	
	
	public Book(int id, String title, String author, Date writtenDate, String company, int price, String category,
			int remain, int saledNum) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.writtenDate = writtenDate;
		this.company = company;
		this.price = price;
		this.category = category;
		this.remain = remain;
		this.saledNum = saledNum;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getWrittenDate() {
		return writtenDate;
	}
	public void setWrittenDate(Date writtenDate) {
		this.writtenDate = writtenDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	public int getSaledNum() {
		return saledNum;
	}
	public void setSaledNum(int saledNum) {
		this.saledNum = saledNum;
	}
	
	

}
