package DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Dao.GuestDao;
import Exception.GuestException;
import Model.Guest;
import Utility.com.Utility;

public class GuestDaoImpl implements GuestDao {

	@Override
	public void addGuest(Guest guest) throws GuestException {
	    EntityManager em = Utility.provideEntityManager();
	
	        em.getTransaction().begin();
	        em.persist(guest);  
	        em.getTransaction().commit();
	        em.close();
	    
	}

	@Override
	public void updateGuest(Guest guest) throws GuestException {
	    EntityManager em = Utility.provideEntityManager();
	    
	    try {
	        em.getTransaction().begin();
	        Guest g = em.find(Guest.class, guest.getGuestId());
	        if (g != null) {
	            em.merge(guest);  
	        } else {
	            throw new GuestException("Guest not found with ID: " + guest.getGuestId());
	        }
	        em.getTransaction().commit();
	    } finally {
	        em.close();
	    }
	}


	

	@Override
	public Guest getGuestById(int guestId) throws GuestException {
		
	    EntityManager em = Utility.provideEntityManager();
	    
	    try {
	        Guest guest = em.find(Guest.class, guestId);
	        if (guest == null) {
	            throw new GuestException("Guest with ID " + guestId + " not found.");
	        }
	        return guest;
	        
	    } finally {
	        em.close();
	    }
	}


	@Override
	public List<Guest> getAllGuest() throws GuestException {

		EntityManager em = Utility.provideEntityManager();
	    
		TypedQuery<Guest> query = em.createQuery("SELECT g FROM Guest g", Guest.class);
	    
	    List<Guest> guests = query.getResultList();
	    
	    return guests;
	}

	@Override
	public void deleteGuest(int guestId) throws GuestException {
	    EntityManager em = Utility.provideEntityManager(); 
	   
	    try {
	        em.getTransaction().begin();
	        Query query = em.createQuery("DELETE FROM Guest g WHERE g.GuestId = :guestId"); 
	        query.setParameter("guestId", guestId); 
	        
	        int deletedCount = query.executeUpdate(); 

	        if (deletedCount == 0) {
	            throw new GuestException("No guest found with ID: " + guestId); 
	        }

	        em.getTransaction().commit();
	    } catch (Exception e) {
	        throw new GuestException("Error deleting guest: " + e.getMessage()); 
	    } 
	}


}
