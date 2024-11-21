package UseCases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import Exception.BookException;
import Exception.GuestException;
import Model.Book;
import Model.Guest;
import Model.Hotel;
import Model.Room;
import ServiceImpl.BookServiceImpl;
import service.BookService;

public class BookUseCase {

	public static Scanner sc = new Scanner(System.in);
	public static BookService bookservice = new BookServiceImpl();

	public static void BookCases() {
	    while (true) {
	        System.out.println("1. Enter TO Book Room");
	        System.out.println("2. Get Room By Guest ID");
	        System.out.println("3. Cancel Booking By BookingId !");
	        System.out.println("4. Exit ! ");
	        
	        
	        System.out.println("Enter Your Choice !");
	        
	        int choice = sc.nextInt();

	        switch (choice) {
	        case 1:
	            Book book = new Book();
	            System.out.println("Enter Your Check-In Date '{YY-MM-DD}': ");
	            book.setCheckIn(sc.next());

	            System.out.println("Enter Your Check-Out Date '{YY-MM-DD}': ");
	            book.setCheckOut(sc.next());

	            System.out.println("Enter Guest ID: ");
	            int guestId = sc.nextInt();
	            Guest guest = new Guest();
	            guest.setGuestId(guestId);
	            book.setGuest(guest);

	            System.out.println("Enter Room ID: ");
	            int roomId = sc.nextInt();
	            Room room = new Room();
	            room.setRoomId(roomId);
	            book.setRoom(room);

	            System.out.println("Enter Hotel ID: ");
	            int hotelId = sc.nextInt();
	            Hotel hotel = new Hotel();
	            hotel.setHotelId(hotelId);
	            book.setHotel(hotel);

	            try {
	                bookservice.bookRoom(book);
	                System.out.println("Room Booking Successful!");
	            } catch (BookException e) {
	                System.err.println("Error during booking: " + e.getMessage());
	            }
	            System.out.println("Booking Successfully ! ");
	            break;

	        case 2:
	        	System.out.println("Enter Guest ID:");
	        	int Id = sc.nextInt();

	        	try {
	        		List<Book> bookings = bookservice.getBookingByGuestId(Id);
	        	    System.out.println("+-------+--------------+-------------------+----------------------------+------------+----------+---------+");
	        	    System.out.println("| BID   | CheckIn      | CheckOut          | BookingDate                | GuestId    | HotelId  | RoomId  |");
	        	    System.out.println("+-------+--------------+-------------------+----------------------------+------------+----------+---------+");

	        	    for (Book b : bookings) {
	        	        int bId = b.getBookingId();
	        	        String cIN = b.getCheckIn();
	        	        String cOut = b.getCheckOut();
	        	        LocalDateTime bDate = b.getBookingDate();
	        	        int gId = b.getGuest().getGuestId();
	        	        int hId = b.getHotel().getHotelId();
	        	        int rId = b.getRoom().getRoomId();

	        	        System.out.printf("| %-5d | %-12s | %-17s | %-20s | %-10d | %-8d | %-7d |\n", bId, cIN, cOut, bDate, gId, hId, rId);
	        	    }

	        	    System.out.println("+-------+--------------+-------------------+----------------------------+------------+----------+---------+");

	        	} catch (GuestException e) {
	        	    System.out.println(e.getMessage());
	        	}
	        	break;


	        case 3:
	        	System.out.println("Enter Room Id");
	            int bId = sc.nextInt();
	            
	            System.out.println("Enter Hotel ID: ");
	            int hId = sc.nextInt();
	            
	            bookservice.cancelBooking(bId, hId);
	            break;
	            

	        case 4:
	            System.out.println("Exiting...");
	            sc.close();
	            return;

	        default:
	            System.out.println("Invalid choice. Please try again.");
	        }
	    }
	}
}