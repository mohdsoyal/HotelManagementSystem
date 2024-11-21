package ServiceImpl;

import java.util.List;

import Dao.RoomDao;
import DaoImpl.RoomDaoImpl;
import Exception.RoomException;
import Model.Room;
import service.RoomService;

public class RoomServiceImpl implements RoomService{

	RoomDao roomdao = new RoomDaoImpl();
	@Override
	public void addRoom(Room room) throws RoomException {
		roomdao.addRoom(room);
		
	}

	@Override
	public void updateRoomStatus(int roomid, String status) throws RoomException {
            roomdao.updateRoomStatus(roomid, status);
		
	}

	@Override
	public List<Room> getRoomByHotelId(int hotelId) throws RoomException {
		return roomdao.getRoomByHotelId(hotelId);
	}

	@Override
	public List<Room> getAllRoom() throws RoomException {
		
		return roomdao.getAllRoom();
	}

}
