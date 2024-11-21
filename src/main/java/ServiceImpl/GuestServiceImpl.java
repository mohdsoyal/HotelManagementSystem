package ServiceImpl;

import java.util.List;

import Dao.GuestDao;
import DaoImpl.GuestDaoImpl;
import Exception.GuestException;
import Model.Guest;
import service.GuestService;

public class GuestServiceImpl  implements GuestService{
	
	GuestDao guestdao = new GuestDaoImpl();

	@Override
	public void addGuest(Guest guest) throws GuestException {
		guestdao.addGuest(guest);
		
	}

	@Override
	public void updateGuest(Guest guest) throws GuestException {
		guestdao.addGuest(guest);
		
	}

	@Override
	public Guest getGuestById(int guestId) throws GuestException {
		
		return guestdao.getGuestById(guestId);
	}

	@Override
	public List<Guest> getAllGuest() throws GuestException {
	
		return guestdao.getAllGuest();
	}

	@Override
	public void deleteGuest(int guestId) throws GuestException {
		guestdao.deleteGuest(guestId);
		
	}

}
