package ServiceImpl;

import java.util.List;

import Dao.BookDao;
import DaoImpl.BookDaoImpl;
import Exception.BookException;
import Model.Book;
import service.BookService;

public class BookServiceImpl implements BookService{
	
	BookDao bookdao = new BookDaoImpl();

	@Override
	public void bookRoom(Book book) throws BookException {
		bookdao.bookRoom(book);
		
	}

	@Override
	public List<Book> getBookingByGuestId(int guestId) throws BookException {
		
		return bookdao.getBookingByGuestId(guestId);
	}

	@Override
	public void cancelBooking(int roomId, int hotelId) throws BookException {
		
		bookdao.cancelBooking(roomId, hotelId);
		
	}

}
