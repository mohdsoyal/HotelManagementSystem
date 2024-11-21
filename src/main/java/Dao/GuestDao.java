package Dao;

import java.util.List;

import Exception.GuestException;
import Model.Guest;

public interface GuestDao {

	
void addGuest(Guest guest) throws GuestException;
	
	void updateGuest(Guest guest)throws GuestException;
	
	Guest getGuestById(int guestId) throws GuestException;
	
	List<Guest> getAllGuest() throws GuestException;
	
	void deleteGuest(int guestId) throws GuestException;
}
