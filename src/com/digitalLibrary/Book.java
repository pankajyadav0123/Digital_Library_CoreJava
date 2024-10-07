package com.project;

//Book Class
class Book {
  private int bookId;
  private String title;
  private String author;
  private int publicationYear;

  //Constructor
  public Book(int bookId, String title, String author, int publicationYear) {
      this.bookId = bookId;
      this.title = title;
      this.author = author;
      this.publicationYear = publicationYear;
  }
  
  // Getters and setters
  public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
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


	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear + "]";
	}
	
  
	
}
