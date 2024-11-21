package DaoImpl;

import java.util.List;

import Dao.BookDao;
import Exception.BookException;
import Exception.HotelException;
import Model.Book;
import Model.Hotel;
import Model.Room;
import Utility.com.Utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.Date;

public class BookDaoImpl implements BookDao{

	

	public void bookRoom(Book book) throws BookException {
		
		 EntityManager em = Utility.provideEntityManager();
	    try {
	        em.getTransaction().begin();
	        book.setBookingDate(LocalDateTime.now());
	        em.persist(book);
	        em.flush(); // Ensure the book entity is saved before executing further queries

	        // Update room status to "Booked" 
	        Query URS = em.createNativeQuery("UPDATE Room SET roomStatus = ? WHERE roomId = ?");
	        URS.setParameter(1, "Booked");
	        URS.setParameter(2, book.getRoom().getRoomId());
	        URS.executeUpdate();

	        // Decrement available rooms 
	        Query UAR = em.createNativeQuery("UPDATE Hotel SET availableRooms = availableRooms - 1 WHERE hotelId = ?");
	        UAR.setParameter(1, book.getHotel().getHotelId());
	        UAR.executeUpdate();

	        em.getTransaction().commit();
	    } catch (Exception e) {
	  
	        System.out.println("Error: " + e.getMessage()); 
	        throw new BookException("Error while adding the booking: " + e.getMessage());
	    } 
	}

	@Override
	public List<Book> getBookingByGuestId(int guestId) throws BookException {
		
	
		EntityManager em = Utility.provideEntityManager();
		TypedQuery<Book> query = em.createQuery("SELECT g FROM Book g WHERE g.guest.GuestId  = :guestId", Book.class);
	    query.setParameter("guestId", guestId); 

	    List<Book> books = query.getResultList();
	    
	    return books;
	}
	
	@Override
	public void cancelBooking(int roomId, int hotelId) throws BookException {
		
	    EntityManager em = Utility.provideEntityManager();

	    try {
	        em.getTransaction().begin();

	        // Step 1: Delete the booking
	        String del = "DELETE FROM book WHERE roomId = :roomId";
	        Query delBooking = em.createNativeQuery(del);
	        delBooking.setParameter("roomId", roomId);
	        int r = delBooking.executeUpdate();

	        if (r > 0) {
	            System.out.println("Booking with ID " + roomId + " canceled successfully.");
	        } else {
	            System.out.println("No booking found with ID " + roomId + ". Cancel failed.");
	            em.getTransaction().rollback();
	            return;
	        }

	        // Step 2: Update the room status to 'Available' after deletion
	        String up = "UPDATE room SET roomStatus = :status WHERE roomId = :roomId";
	        Query upRoomQ = em.createNativeQuery(up);
	        upRoomQ.setParameter("status", "Available");
	        upRoomQ.setParameter("roomId", roomId);
	        int res = upRoomQ.executeUpdate();

	        if (res <= 0) {
	            em.getTransaction().rollback();
	            throw new BookException("Failed to update room status.");
	        } else {
	            System.out.println("Room with ID " + roomId + " status updated to 'Available'.");
	        }

	        // Step 3: Update the available rooms count in the hotel
	        String update = "UPDATE hotel SET availableRooms = availableRooms + 1 WHERE HotelId = :hotelId";
	        Query UAQ = em.createNativeQuery(update);
	        UAQ.setParameter("hotelId", hotelId);
	        int result = UAQ.executeUpdate();

	        if (result <= 0) {
	            em.getTransaction().rollback();
	            throw new HotelException("Failed to update available room count.");
	        }

	        em.getTransaction().commit();
	    } catch (Exception e) {
	        throw new BookException("Error canceling booking: " + e.getMessage());
	    } 
	}

}
