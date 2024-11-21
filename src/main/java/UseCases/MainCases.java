package UseCases;

import java.sql.SQLException;
import java.util.Scanner;

import Dao.UserDao;

import Model.User;

public class MainCases {
	 
	public static Scanner sc = new Scanner(System.in); 
	
	public static void main(String[] args) {
		
		 DaoImpl.UserDaoImpl userDao = new DaoImpl.UserDaoImpl();
		    
		while(true) {
			System.out.println("|-----Welcome----| \n");
			System.out.println("| 1.Signup.....! |\n");
			System.out.println("| 2.Login....!   |\n");
			System.out.println("| 3.Logout....!  |\n");
			
			
			System.out.println("Enter Your Choice....");
			
			int ch = sc.nextInt();
			
			switch(ch) {
			
			case 1:
				User newuser = new User();
                System.out.print("Enter Username: ");
                newuser.setUsername(sc.next());
                System.out.print("Enter Password: ");
                newuser.setPassword(sc.next()); 
                System.out.print("Enter Email: ");
                newuser.setEmail(sc.next());  
                try {
                    userDao.signup(newuser);  
                    System.out.println("Signup Successful!");
                } catch (SQLException e) {
                    System.out.println("Error during signup: " + e.getMessage());
                }
                break;
                
			case 2:
				System.out.print("Enter Username: ");
                String loginUsername = sc.next();
                System.out.print("Enter Password: ");
                String loginPassword = sc.next();
                boolean loggedIn = false;
                try {
                    loggedIn = userDao.login(loginUsername, loginPassword);
                } catch (SQLException e) {
                    System.out.println("Error during login: " + e.getMessage());
                }
                
                if (loggedIn) {
                    System.out.println("Login Successful!");
                    Allmain();  // call all menu 
                } else {
                    System.out.println("Invalid Credentials. Please try again.");
                }
				break;
			case 3:
				System.out.println("Logout Succssfully...");
				sc.close();
				return;
				default:
					System.out.println("Invalid choice Try Again....");
				
			}
			
		}
		
	}
	
	public static void Allmain() {
		
		while(true) {
			System.out.println("1. All Cases for the Guest...!");
			System.out.println("2. All Cases for the Hotel...!");
			System.out.println("3. All Cases for the Room....!");
			System.out.println("4. All Cases for the Booking....!");
			System.out.println("5. Logout");
			
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				GuestUseCase.GuestCases();
				break;
			case 2:
				HotelUseCase.HotelCases();
				break;
			case 3:
				RoomUseCases.RoomCases();
				break;
			case 4:
				BookUseCase.BookCases();
				break;
			case 5:
				System.out.println("Exiting.....");
				sc.close();
				break;

			default:
				break;
			}
		}
		
	}

}
