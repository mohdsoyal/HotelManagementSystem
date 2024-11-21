package UseCases;

import java.util.List;
import java.util.Scanner;

import Exception.HotelException;
import Exception.RoomException;
import Model.Hotel;
import Model.Room;
import ServiceImpl.RoomServiceImpl;
import service.HotelService;
import service.RoomService;

public class RoomUseCases {

	public static Scanner sc = new Scanner(System.in);
	public static RoomService roomservice = new RoomServiceImpl();

	public static void RoomCases() {

		while (true) {
			System.out.println("1. :Add The New Room : ! ");
			System.out.println("2. :Get Room By Hotel Id : !");
			System.out.println("3. :Get All Room");
			System.out.println("4. :Exit ! ");

			System.out.println("Enter Your Choice..........");
			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				NewHotel();
				break;

			case 2:
				FindHotelHotelId();
				break;
			case 3:
				AllRoom();
				break;
			case 4:
				System.exit(4);
				default:
					System.out.println("Invalid Choice Plz Try Again....!");

			}

		}

	}

	public static void NewHotel() throws HotelException {
		Room r1 = new Room();

		System.out.println("Enter Room Type AC or Non-Ac: ");
		r1.setRoomType(sc.next());

		System.out.println("Enter Room Price: ");
		r1.setRoomPrice(sc.nextInt());

		System.out.println("Enter Hotel ID: ");
		int hotelId = sc.nextInt();
		Hotel hotel = HotelService.getHotelById(hotelId);
		if (hotel != null) {
			r1.setHotel(hotel);
			roomservice.addRoom(r1);
			System.out.println("Room added successfully!");
		} else {
			System.out.println("Hotel with ID " + hotelId + " not found.");
		}
	}

	public static void FindHotelHotelId() throws RoomException {

		System.out.println("Enter Hotel Id !");
		int hId = sc.nextInt();

		try {
			List<Room> rooms = roomservice.getRoomByHotelId(hId);

			if (rooms.isEmpty()) {
				System.out.println("No hotels found in the specified city.");
			} else {
				System.out.println("+----------+--------------+-------------------+--------------+------------+");
				System.out.println("| RoomID   | RoomPrice    | RoomStatus        | RoomType     | HotelId    |");
				System.out.println("+----------+--------------+-------------------+--------------+------------+");

				for (Room r : rooms) {
					int rid = r.getRoomId();
					int rPrice = r.getRoomPrice();
					String rStatus = r.getRoomStatus();
					String rType = r.getRoomType();
					int hid = r.getHotel().getHotelId();

					System.out.printf("| %-8d | %-12d | %-17s | %-12s | %-10d |\n", rid, rPrice, rStatus, rType, hid);
				}

				System.out.println("+----------+--------------+-------------------+--------------+------------+");
			}

		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void AllRoom() throws RoomException {

		try {
			List<Room> rooms = roomservice.getAllRoom();

			if (rooms.isEmpty()) {
				System.out.println("No hotels found in the specified city.");
			} else {
				System.out.println("+----------+--------------+-------------------+--------------+------------+");
				System.out.println("| RoomID   | RoomPrice    | RoomStatus        | RoomType     | HotelId    |");
				System.out.println("+----------+--------------+-------------------+--------------+------------+");

				for (Room r : rooms) {
					int rid = r.getRoomId();
					int rPrice = r.getRoomPrice();
					String rStatus = r.getRoomStatus();
					String rType = r.getRoomType();
					int hid = r.getHotel().getHotelId();

					System.out.printf("| %-8d | %-12d | %-17s | %-12s | %-10d |\n", rid, rPrice, rStatus, rType, hid);
				}

				System.out.println("+----------+--------------+-------------------+--------------+------------+");
			}

		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}

	}

}
