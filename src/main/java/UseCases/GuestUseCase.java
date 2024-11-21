package UseCases;

import java.util.List;
import java.util.Scanner;

import Exception.GuestException;
import Model.Guest;
import ServiceImpl.GuestServiceImpl;
import service.GuestService;

public class GuestUseCase {

	public static Scanner sc = new Scanner(System.in);
	public static GuestService guestservice = new GuestServiceImpl();

	public static void GuestCases() {

		while (true) {
			System.out.println("\nWelcome to Hotel Management System!");
			System.out.println("1. Create a New Guest");
			System.out.println("2. Update Guest Account");
			System.out.println("3. Fetch Guest By ID");
			System.out.println("4. Fetch All Guests");
			System.out.println("5. Delete Guest By ID !");
			System.out.println("6. Exit");

			System.out.print("Enter Your Choice: ");

			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {

			case 1:
				createGuest();
				break;
			case 2:	
				    System.out.println("Enter Guest ID to Update:");
				    int guestId = sc.nextInt();
				    
				    Guest existingGuest = guestservice.getGuestById(guestId);  // Retrieve guest by ID
				    if (existingGuest != null) {
				        System.out.println("Enter New Guest Name:");
				        existingGuest.setGuestName(sc.next());

				        System.out.println("Enter New Email Id:");
				        existingGuest.setGuestEmail(sc.next());

				        System.out.println("Enter New Guest Phone Number:");
				        existingGuest.setGuestPhone(sc.next());

				        System.out.println("Enter New Guest Address:");
				        existingGuest.setGuestAddress(sc.next());

				        guestservice.updateGuest(existingGuest);  // Update existing guest
				        System.out.println("Guest Updated Successfully!");
				    } else {
				        System.out.println("Guest with ID " + guestId + " not found.");
				    }
				    break;

			case 3:
				GuestGet();
				break;

			case 4:
				displayAllGuests();
				break;

			case 5:
				DeleteGuest();
				break;
			case 6:
				System.exit(6);
				break;

			default:
				System.out.println("Invalid Choice Plz Try Again ! ");
			}
		}

	}

	private static void createGuest() throws GuestException {
		Guest d = new Guest();

		System.out.println("Enter Guest Name ! ");
		d.setGuestName(sc.next());

		System.out.println("Enter Email Id ! ");
		d.setGuestEmail(sc.next());

		System.out.println("Enter Guest Phone Number !");
		d.setGuestPhone(sc.next());

		System.out.println("Enter Guest Address !");
		d.setGuestAddress(sc.next());

		guestservice.addGuest(d);
		System.out.println("Guest Add Successfully ! ");

	}
	
	

	private static void GuestGet() throws GuestException {
		System.out.println("Enter Guest ID to fetch:");
		int guestId = sc.nextInt();

		try {
			Guest guest = guestservice.getGuestById(guestId);
			System.out.println("+-------+--------------+-------------------+------------+------------+");
			System.out.println("| GID   | GName        | GEmail            | GPhone     | GAddress   |");
			System.out.println("+-------+--------------+-------------------+------------+------------+");

			int gId = guest.getGuestId();
			String gName = guest.getGuestName();
			String gEmail = guest.getGuestEmail();
			String gPhone = guest.getGuestPhone();
			String gAddress = guest.getGuestAddress();

			System.out.printf("| %-5d | %-12s | %-17s | %-10s | %-10s |\n", gId, gName, gEmail, gPhone, gAddress);
			System.out.println("+-------+--------------+-------------------+------------+------------+");

		} catch (GuestException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void displayAllGuests() throws GuestException {
		try {
			List<Guest> guests = guestservice.getAllGuest(); // Fetch all guests

			System.out.println("+-------+--------------+-------------------+------------+--------------+");
			System.out.println("| GID   | GName        | GEmail            | GPhone     | GAddress     |");
			System.out.println("+-------+--------------+-------------------+------------+--------------+");

			for (Guest guest : guests) {
				System.out.printf("| %-5d | %-12s | %-17s | %-10s | %-12s |\n", guest.getGuestId(),
						guest.getGuestName(), guest.getGuestEmail(), guest.getGuestPhone(), guest.getGuestAddress());
			}

			System.out.println("+-------+--------------+-------------------+------------+--------------+");

		} catch (GuestException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void DeleteGuest() throws GuestException {

		System.out.println("Enter Guest ID to Delete Guest:");
		int guestId = sc.nextInt();
		guestservice.deleteGuest(guestId);

		System.out.println("Guest with ID " + guestId + " has been deleted.");
	}
}
