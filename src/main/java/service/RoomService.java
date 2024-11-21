package service;

import java.util.List;

import Exception.RoomException;
import Model.Hotel;
import Model.Room;

public interface RoomService {

	   void addRoom(Room room) throws RoomException;
		
		void updateRoomStatus(int roomid , String status) throws RoomException;
		
		List<Room> getRoomByHotelId(int  hotelId) throws RoomException;
		
		List<Room> getAllRoom() throws RoomException ;

}
