package Dao;

import java.util.List;

import Exception.GuestException;
import Exception.HotelException;
import Model.Guest;
import Model.Hotel;

public interface HotelDao {
	
	   public void addHotel(Hotel hotel) throws  HotelException;
		
		public List<Hotel> getHotelsByCity(String city) throws  HotelException;
		
		public List<Hotel> getAllHotels() throws  HotelException;
		
		public int countHotelsByCity(String city) throws  HotelException;

}
