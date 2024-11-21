package ServiceImpl;

import java.util.List;

import Dao.HotelDao;
import DaoImpl.HotelDaoImpl;
import Exception.HotelException;
import Model.Hotel;
import service.HotelService;

public class HotelServiceImpl implements HotelService{
	
	 HotelDao hoteldao = new HotelDaoImpl();

	@Override
	public void addHotel(Hotel hotel) throws HotelException {
		hoteldao.addHotel(hotel);
		
	}

	@Override
	public List<Hotel> getHotelsByCity(String city) throws HotelException {
	
		return hoteldao.getHotelsByCity(city);
	}

	@Override
	public List<Hotel> getAllHotels() throws HotelException {
		
		return hoteldao.getAllHotels();
	}

	@Override
	public int countHotelsByCity(String city) throws HotelException {
		
		return hoteldao.countHotelsByCity(city);
	}

}
