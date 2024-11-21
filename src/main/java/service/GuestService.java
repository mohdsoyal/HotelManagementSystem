package service;

import java.util.List;

import Exception.GuestException;
import Model.Guest;

public interface GuestService {
	
   void addGuest(Guest guest) throws GuestException;
	
	void updateGuest(Guest guest)throws GuestException;
	
	Guest getGuestById(int guestId) throws GuestException;
	
	List<Guest> getAllGuest() throws GuestException;
	
	void deleteGuest(int guestId) throws GuestException;

}
