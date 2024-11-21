package DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Dao.RoomDao;
import Exception.RoomException;
import Model.Hotel;
import Model.Room;
import Utility.com.Utility;

public class RoomDaoImpl  implements RoomDao{

	@Override
	public void addRoom(Room room) throws RoomException {
		EntityManager em = Utility.provideEntityManager();
		
        em.getTransaction().begin();
        em.persist(room);  
        em.getTransaction().commit();
        em.close();
		
	}

	@Override
	public void updateRoomStatus(int roomid, String status) throws RoomException {
		
		
	}

	
	@Override
	public List<Room> getRoomByHotelId(int hotelId) throws RoomException {
	    EntityManager em = Utility.provideEntityManager();
	    
	    try {
	        TypedQuery<Room> query = em.createQuery("SELECT g FROM Room g WHERE g.hotel.HotelId = :hotelId", Room.class);
	        query.setParameter("hotelId", hotelId);
	        
	        List<Room> rooms = query.getResultList();
	        return rooms;
	    } catch (Exception e) {
	        throw new RoomException("Error while retrieving rooms: " + e.getMessage());
	    } 
	}

	@Override
	public List<Room> getAllRoom() throws RoomException {
		
		 EntityManager em = Utility.provideEntityManager();
		    
		    try {
		        TypedQuery<Room> query = em.createQuery("SELECT g FROM Room g", Room.class);
		    
		        List<Room> rooms = query.getResultList();
		        return rooms;
		    } catch (Exception e) {
		        throw new RoomException("Error while retrieving rooms: " + e.getMessage());
		    }
		
	}

}
