package com.project;

import java.util.*;

//Library Class
class Library {
  private Map<Integer, Book> books;
  private Map<Integer, Member> members;
  private Map<Integer, List<Book>> issuedBooks;


  public Library() {
      books = new HashMap<>();
      members = new HashMap<>();
      issuedBooks = new HashMap<>();

  }

  //book data
  public void addBook(Book book) {
      books.put(book.getBookId(), book);
  }

  public void removeBook(int bookId) {
      books.remove(bookId);
  }

  public Book findBookById(int bookId) {
      return books.get(bookId);
  }

  public List<Book> getAllBooks() {
      return new ArrayList<>(books.values());
  }

  
  //member data
  public void addMember(Member member) {
      members.put(member.getMemberId(), member);
  }

  public void removeMember(int memberId) {
      members.remove(memberId);
  }

  public Member findMemberById(int memberId) {
      return members.get(memberId);
  }
  
  
  //issueBook data
  public boolean issueBook(int memberId, int bookId) {
      Member member = members.get(memberId);
      Book book = books.get(bookId);

      if (member == null || book == null) {
          return false;
      }

      List<Book> memberBooks = issuedBooks.computeIfAbsent(memberId, k -> new ArrayList<>());
      memberBooks.add(book);
      return true;
  }

  public boolean returnBook(int memberId, int bookId) {
      List<Book> memberBooks = issuedBooks.get(memberId);
      if (memberBooks == null) {
          return false;
      }

      for (Book book : memberBooks) {
          if (book.getBookId() == bookId) {
              memberBooks.remove(book);
              return true;
          }
      }
      return false;
  }

  public List<Book> viewIssuedBooks(int memberId) {
      return issuedBooks.getOrDefault(memberId, new ArrayList<>());
  }
}
