package service;

import java.util.List;

import javax.persistence.EntityManager;

import Exception.HotelException;
import Model.Guest;
import Model.Hotel;
import Utility.com.Utility;

public interface HotelService {
	
	   public void addHotel(Hotel hotel) throws  HotelException;
		
		public List<Hotel> getHotelsByCity(String city) throws  HotelException;
		
		public List<Hotel> getAllHotels() throws  HotelException;
		
		public int countHotelsByCity(String city) throws  HotelException;
		
		public static Hotel getHotelById(int id) throws HotelException {
		    EntityManager em = Utility.provideEntityManager();
		    Hotel hotel = em.find(Hotel.class, id);
		    em.close();
		    return hotel;
		}


}
