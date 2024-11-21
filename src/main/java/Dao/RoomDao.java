package Dao;

import java.util.List;

import Exception.RoomException;
import Model.Room;

public interface RoomDao {

    void addRoom(Room room) throws RoomException;
	
	void updateRoomStatus(int roomid , String status) throws RoomException;
	
	List<Room> getRoomByHotelId(int hotelId) throws RoomException; 
   
	List<Room> getAllRoom() throws RoomException ;

}
