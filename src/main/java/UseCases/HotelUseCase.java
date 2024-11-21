package UseCases;

import java.util.List;
import java.util.Scanner;

import Exception.GuestException;
import Exception.HotelException;
import Model.Guest;
import Model.Hotel;
import ServiceImpl.HotelServiceImpl;
import service.HotelService;

public class HotelUseCase {

	public static Scanner sc = new Scanner(System.in);
	public static HotelService hotelservice = new HotelServiceImpl();

	public static void HotelCases() {

		while (true) {
			System.out.println("\nWelcome to Hotel Management System!");
			System.out.println("1. Add New Hotel !");
			System.out.println("2. Get All Hotel !");
			System.out.println("3. Get Hotel By City Name ! ");
			System.out.println("4. Count Hotel By City !");
			System.out.println("5. Exit");

			System.out.print("Enter Your Choice: ");

			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {

			case 1:
				AddNewHotel();
				break;
			case 2:
                AllHotel();
                break;
			case 3:
				searchHotelCity();
				break;
			case 4:
				    Hotel h4 = new Hotel();
				    System.out.println("Enter City: ");
				    h4.setCity(sc.next());
				    int hotelCount = hotelservice.countHotelsByCity(h4.getCity());
				    System.out.println("Number of hotels in " + h4.getCity() + ": " + hotelCount);
				    break;
				
			case 5:
				System.exit(5);
				break;
				default:
					System.err.println("Invalid Choice Plz Try Again !");	
				
			}

		}

	}

	public static void AddNewHotel() throws HotelException {

		Hotel h1 = new Hotel();

		System.out.println("Enter Hotel Name ! ");
		h1.setHotelName(sc.next());

		System.out.println("Enter Hotel City ! ");
		h1.setCity(sc.next());

		System.out.println("Enter Hotel Total Rooms");
		h1.setTotalRooms(sc.nextInt());

		System.out.println("Enter Total Available Rooms ");
		h1.setAvailableRooms(sc.nextInt());

		hotelservice.addHotel(h1);
		System.out.println("Hotel Add Successfully ! ");

	}

	public static void AllHotel() throws HotelException {

		try {
			List<Hotel> hotel = hotelservice.getAllHotels();

			System.out.println("+-------+--------------+-------------------+--------------+------------------+");
			System.out.println("| HID   | HName        | HCity             | TotalRooms   | Avialables Rooms |");
			System.out.println("+-------+--------------+-------------------+--------------+------------------+");

			for (Hotel h : hotel) {
				System.out.printf("| %-5d | %-12s | %-17s | %-12s | %-16s |\n", h.getHotelId(), h.getHotelName(),
						h.getCity(), h.getTotalRooms(), h.getAvailableRooms());
			}

			System.out.println("+-------+--------------+-------------------+--------------+------------------+");

		} catch (GuestException e) {
			System.out.println(e.getMessage());
		}
	}
	
public static void searchHotelCity() throws HotelException{
	
	System.out.println("Enter City And Find Hotel");
	String cityname = sc.next();

	try {
	    List<Hotel> hotels = hotelservice.getHotelsByCity(cityname);

	    if (hotels.isEmpty()) {
	        System.out.println("No hotels found in the specified city.");
	    } else {
	        System.out.println("+-------+--------------+-------------------+--------------+------------------+");
	        System.out.println("| HID   | HName        | HCity             | TotalRooms   | Available Rooms  |");
	        System.out.println("+-------+--------------+-------------------+--------------+------------------+");

	        for (Hotel hotel : hotels) {
	            int id = hotel.getHotelId();
	            String hname = hotel.getHotelName();
	            String hcity = hotel.getCity();
	            int totalRooms = hotel.getTotalRooms();
	            int ar = hotel.getAvailableRooms();

	            System.out.printf("| %-5d | %-12s | %-17s | %-12d | %-16d |\n", id, hname, hcity, totalRooms, ar);
	        }
	        
	        System.out.println("+-------+--------------+-------------------+--------------+------------------+");
	    }

	} catch (HotelException e) {
	    System.out.println(e.getMessage());
	}

	
}

}
