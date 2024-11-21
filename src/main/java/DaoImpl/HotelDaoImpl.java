package DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Dao.HotelDao;
import Exception.HotelException;
import Model.Guest;
import Model.Hotel;
import Utility.com.Utility;

public class HotelDaoImpl implements HotelDao{

	@Override
	public void addHotel(Hotel hotel) throws HotelException {
		 EntityManager em = Utility.provideEntityManager();
			
	        em.getTransaction().begin();
	        em.persist(hotel);  
	        em.getTransaction().commit();
	        em.close();
		
	}

	@Override
	public List<Hotel> getHotelsByCity(String city) throws HotelException {
		 EntityManager em = Utility.provideEntityManager();
		    
		 TypedQuery<Hotel> query = em.createQuery("SELECT g FROM Hotel g WHERE g.City = :city", Hotel.class);
		 query.setParameter("city", city); 
		    
		    List<Hotel> citys = query.getResultList();
		    
		    return citys;
			
	}

	@Override
	public List<Hotel> getAllHotels() throws HotelException {
		
        EntityManager em = Utility.provideEntityManager();
	    
		TypedQuery<Hotel> query = em.createQuery("SELECT g FROM Hotel g", Hotel.class);
	    
	    List<Hotel> hotels = query.getResultList();
	    
	    return hotels;
		
	}

	@Override
	public int countHotelsByCity(String city) throws HotelException {
		    
		
		     EntityManager em = Utility.provideEntityManager();
		    
		     TypedQuery<Long> query = em.createQuery("SELECT COUNT(h) FROM Hotel h WHERE h.City = :city", Long.class);
		        query.setParameter("city", city);
		        
		        Long count = query.getSingleResult(); 
		        return count.intValue();
	
		
		
	}

}
