package designPattern;

import java.util.HashMap;

public class BookReader {
	private Library library;
	private UserManager userManager;
	private Display display;

	private Book activeBook;
	private User activeUser;

	public BookReader() {
		userManager = new UserManager();
		display = new Display();
		library = new Library();
	}

	public Library getLibrary() {
		return library;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public Display getDisplay() {
		return display;
	}

	public Book getActiveBook() {
		return activeBook;
	}

	public void setActiveBook(Book activeBook) {
		this.activeBook = activeBook;
		display.displayBook(activeBook);
	}

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
		display.displayUser(activeUser);
	}
}

class Library {
	private HashMap<Integer, Book> books;

	public Book addBook(int id, String details) {
		if (books.containsKey(id))
			return null;
		Book book = new Book(id, details);
		books.put(id, book);
		return book;
	}

	public boolean remove(Book b) {
		return remove(b.getBookId());
	}

	public boolean remove(int id) {
		if (!books.containsKey(id)) {
			return false;
		}
		books.remove(id);
		return true;
	}

	public Book find(int id) {
		return books.get(id);
	}
}

class UserManager {
	private HashMap<Integer, User> users;

	public User addUser(int id, String details, int accountType) {
		if (users.containsKey(id))
			return null;
		User user = new User(id, details, accountType);
		users.put(id, user);
		return user;
	}

	public User find(int id) {
		return users.get(id);
	}

	public boolean remove(User u) {
		return remove(u.getUserid());
	}

	public boolean remove(int id) {
		if (!users.containsKey(id))
			return false;
		users.remove(id);
		return true;
	}
}

class Display {
	private Book activeBook;
	private User activeUser;
	private int pageNum = 0;

	public void displayUser(User user) {
		activeUser = user;
		refreshUsername();
	}

	public void displayBook(Book book) {
		pageNum = 0;
		activeBook = book;
		refreshTitle();
		refreshDetails();
		refreshPage();
	}

	public void turnPageForward() {
		pageNum++;
		refreshPage();
	}

	public void turnPageBackward() {
		pageNum--;
		refreshPage();
	}

	public void refreshUsername() {
		/* updates username display */}

	public void refreshTitle() {
		/* updates title display */}

	public void refreshDetails() {
		/* updates details display */ }

	public void refreshPage() {
		/* updated page display */}
}

class Book{
	private int bookId;
	private String details;
	
	public Book(int id, String details) {
		this.bookId = id;
		this.details = details;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}

class User{
	private int userid;
	private String details;
	private int accountType;
	
	public User(int id, String details, int accountType) {
		this.userid = id;
		this.details = details;
		this.accountType = accountType;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
}
