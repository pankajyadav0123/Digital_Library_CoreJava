package com.digitalLibrary;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;

//Main Class
public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();
    private static int bookId = 1;
    private static int memberId = 1;
    
    //Date and Time when Stop the Application
    private static final LocalDateTime STOP_DATE_TIME = LocalDateTime.of(2024, 5, 10, 0, 0); 

    public static void main(String[] args) {
        while (true) {
            if (isStopDateTimeReached()) {
                stopApplication();
            }
            
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book by keyword");
            System.out.println("4. Display All Books");
            System.out.println();
            System.out.println("5. Add Member");
            System.out.println("6. Search Member By Id");
            System.out.println("7. Remove Member");
            System.out.println();
            System.out.println("8. Issue Book");
            System.out.println("9. Return Book");
            System.out.println("10. View Issued Books");
            System.out.println("11. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    displayAllBooks();
                    break;
                case 5:
                    addMember();
                    break;
                case 6:
                	findMemberById();
                    break;
                case 7:
                	removeMember();
                    break;
                case 8:
                    issueBook();
                    break;
                case 9:
                    returnBook();
                    break;
                case 10:
                    viewIssuedBooks();
                    break;
                case 11:
                    stopApplication();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

   
    
	private static boolean isStopDateTimeReached() {
        return LocalDateTime.now().isAfter(STOP_DATE_TIME);
    }

    private static void stopApplication() {
        System.out.println("Stopping Application at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "...");
        System.exit(0);
    }

		
	
    //Add Book 
	private static void addBook() {
		System.out.println("Book Id: "+bookId);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter publication year: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine();

        Book book = new Book(bookId++, title, author, publicationYear);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }
	
	//Remove Book
    private static void removeBook() {
        System.out.print("Enter book id to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Book book = library.findBookById(id);
        if (book != null) {
            library.removeBook(id);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }
    
    //Search Book
    private static void searchBook() {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine().toLowerCase();
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : library.getAllBooks()) {
            if (book.getTitle().toLowerCase().contains(keyword) ||
                book.getAuthor().toLowerCase().contains(keyword) ||
                String.valueOf(book.getPublicationYear()).contains(keyword)) {
                foundBooks.add(book);
            }
        }

        if (foundBooks.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Found books:");
            for (Book book : foundBooks) {
                System.out.println(book.getBookId() + ". " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
    
    //Get All Book
    private static void displayAllBooks() {
        List<Book> books = library.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("List of all books:");
            for (Book book : books) {
                System.out.println(book.getBookId() + ". " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
    
    //Add Member
    private static void addMember() {
        System.out.println("Member Id: "+memberId);
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter member email: ");
        String email = scanner.nextLine();
        System.out.print("Enter member phone number: ");
        String phoneNumber = scanner.nextLine();

        Member member = new Member(memberId++, name, email, phoneNumber);
        library.addMember(member);
        System.out.println("Member added successfully.");
    }

    // Remove Member
    private static void removeMember() {
        System.out.print("Enter member id to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Member member = library.findMemberById(id);
        if (member != null) {
            library.removeMember(id);
            System.out.println("Member removed successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }
    
    //Find Member
    private static void findMemberById() {
        System.out.print("Enter member id to search: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Member member = library.findMemberById(id);
        if (member != null) {
            System.out.println(member);
            System.out.println("Member search successfully.");
        } else {
            System.out.println("Member not found.");
        }
      }
    
    //Issue Book
    private static void issueBook() {
        System.out.print("Enter member id: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book id: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        if (library.issueBook(memberId, bookId)) {
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Unable to issue book. Please check member and book id.");
        }
    }
    
    //Return Book
    private static void returnBook() {
        System.out.print("Enter member id: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book id: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        if (library.returnBook(memberId, bookId)) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Unable to return book. Please check member and book id.");
        }
    }
    
    //Get All Issue Book
    private static void viewIssuedBooks() {
        System.out.print("Enter member id: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        List<Book> issuedBooks = library.viewIssuedBooks(memberId);
        if (issuedBooks.isEmpty()) {
            System.out.println("No books issued to this member.");
        } else {
            System.out.println("Books issued to member:");
            for (Book book : issuedBooks) {
                System.out.println(book.getBookId() + ". " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
}

