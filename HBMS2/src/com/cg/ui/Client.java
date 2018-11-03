package com.cg.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.cg.bean.BookingDetails;
import com.cg.bean.Hotel;
import com.cg.bean.RoomDetails;
import com.cg.bean.Users;
import com.cg.dao.BookingDAO;
import com.cg.dao.IBookingDAO;
import com.cg.service.BookingService;
import com.cg.service.IBookingService;

public class Client {

	static IBookingService service = new BookingService();
	static IBookingDAO bref = new BookingDAO();

	public static void main(String[] args) {

		Client c = new Client();
		System.out.print("\n\t Welcome to Hotel Booking Management System\n"
				+ "1. Register into the system ( For new User ).\n" + "2. Login to the system.\n" + " Enter choice: ");

		Scanner sc = new Scanner(System.in);
		int ch = sc.nextInt();

		switch (ch) {
		case 1:
			//IBookingService service = new BookingService();
			System.out.print("Enter Name: ");
			String name = sc.next();
			System.out.print("Enter Email id: ");
			String emailid = sc.next();
			System.out.print("Enter Phone number: ");
			String phoneno = sc.next();
			System.out.print("Enter Mobile number: ");
			String mobileno = sc.next();
			System.out.print("Enter Address: ");
			String addr = sc.next();
			System.out.print("Your role? (User): ");
			String role = sc.next();
			System.out.print("Create your Password: ");
			String pwd = sc.next();

			Users user = new Users();
			user.setAddress(addr);
			user.setEmail(emailid);
			user.setMobileNo(mobileno);
			user.setPassword(pwd);
			user.setPhone(phoneno);
			user.setRole(role);
			user.setUserName(name);
			
			try {
				String abc = service.addUser(user);
				
				System.out.println("USer Added Successfully  " + abc);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//register();
			
			
			break;
		case 2: {
			c.login();
		}
			break;
		case 3: {
			System.out.println("Exit");
			break;
		}
		default:
			System.out.println("Invalid Option");
			break;
		}
		sc.close();
	}

//	private static void register() throws Exception {
	/*	Scanner sc = new Scanner(System.in);
		System.out.print("Enter Name: ");
		String name = sc.next();
		System.out.print("Enter Email id: ");
		String emailid = sc.next();
		System.out.print("Enter Phone number: ");
		String phoneno = sc.next();
		System.out.print("Enter Mobile number: ");
		String mobileno = sc.next();
		System.out.print("Enter Address: ");
		String addr = sc.next();
		System.out.print("Your role? (Customer): ");
		String role = sc.next();
		System.out.print("Create your Password: ");
		String pwd = sc.next();

		Users user = new Users();
		user.setAddress(addr);
		user.setEmail(emailid);
		user.setMobileNo(mobileno);
		user.setPassword(pwd);
		user.setPhone(phoneno);
		user.setRole(role);
		user.setUserName(name);*/
		//String uid=service.addUser(user);
//	}

	private void login() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter User Name: ");
		String username = sc.next();
		System.out.print("Enter Password: ");
		String password = sc.next();
		try {
			String flag = service.validateUser(username, password);
			if (flag.equalsIgnoreCase("user")) {
				System.out.print("\n\tMENU\n" + "1. Search for hotel rooms.\n" + "2. Book Hotel Rooms.\n"
						+ "3. View Booking Status.\n" + "4. Exit" + " Enter choice: ");
				int ch = sc.nextInt();
				switch (ch) {
				case 1:
					//this.search();
					break;
				case 2:
					//this.book();
					break;
				case 3:
					
					//this.view();
					break;
				case 4:
					System.exit(0);
				default:
					break;
				}
			}
			
			
			else if (flag.equalsIgnoreCase("admin")) {
				System.out.println("\n\tMENU\n" + "1. Perform Hotel Management.\n" + "2. Perform Room Management.\n" + "3. Exit" + " Enter choice: ");
				int ch = sc.nextInt();
				switch (ch) {
				case 1:
				performHotelManagement();
				break;
				case 2:
				//performRoomManagement();
				break;
				case 3:
				System.exit(0);
				default:
				System.out.println("Please enter a valid option!");
				}
				}


				else
				System.out.println("Invalid User");

				}catch(

			Exception e)
			{
				System.out.println(e.getMessage());
			}
			}

			private void addHotel() {

				Hotel hotel = null;
				String hotelId = null;
				System.out.println("ADD HOTEL TO DATABASE");

				//hotel = populateHotel();

				try {
					hotelId = service.addHotels(hotel);
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}
				if (hotelId != null) {
					System.out.println("Hotel added to the database. Hotel ID is " + hotelId);
				} else {
					System.out.println("Hotel not added!");
				}
			}
/*
			private void deleteHotel() {

				String hotelId;
				System.out.println("DELETE HOTEL");

				System.out.println("Enter the hotel ID of hotel to be deleted:");

				hotelId = Client.sc.next();
				try {
					if (adminService.adminDeleteHotel(hotelId)) {
						System.out.println("Hotel deleted from database.");
					} else {
						System.out.println("Hotel could not be deleted.");
					}
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}
			}*/

			public void performHotelManagement() {

			/*	int option1 = 0;
				Hotel hotel = null;
				String hotelId = null;*/

				loop1: while (true) {

					System.out.println("PERFORM HOTEL MANAGEMENT");
					System.out.println("1. Add a new hotel to database."); // Done
					System.out.println("2. Delete an existing hotel in database.");
					System.out.println("3. Go to previous menu.\n");

					Scanner sc = new Scanner(System.in);
					int ch = sc.nextInt();

					switch (ch) {

					case 1:

						addHotel();						
						break;

					case 2:

						//deleteHotel();
						break;

					case 3:
						return;

					default:
						System.out.println("Please enter a valid option!");
					}
				}
			}
}

		/*	public void performRoomManagement() {

				int option1 = 0;

				loop1: while (true) {

					System.out.println("PERFORM ROOM MANAGEMENT");
					System.out.println("1. Add a new room to database.");
					System.out.println("2. Delete an existing room in database.");
					System.out.println("3. Go to previous menu.\n");

					Scanner sc = new Scanner(System.in);
					int ch = sc.nextInt();
					switch (ch) {

					case 1:

						addRoom();
						break;

					case 2:

						deleteRoom();

					case 3:
						return;

					default:

						System.out.println("Please enter a valid option!");

					}
				}
			}

			private void deleteRoom() {

				String roomId;
				System.out.println("DELETE ROOM");

				System.out.println("Enter the room ID of hotel to be deleted:");

				roomId = Client.sc.next();
				try {
					if (adminService.adminDeleteRoom(roomId)) {
						System.out.println("Room deleted from database.");
					} else {
						System.out.println("Room could not be deleted.");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			private void addRoom() {
				Room room = null;
				String roomId = null;
				System.out.println("ADD ROOM TO DATABASE");

				room = populateRoom();

				try {
					roomId = adminService.adminAddRoom(room);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				if (roomId != null) {
					System.out.println("Room added to the database. Room ID is " + roomId);
				} else {
					System.out.println("Room not added!");
				}
			}// end of method


	
	
// Phir se uncomment karna hai
	

				/* private void search() {

				try {
				List<Hotel> hotelList = service.viewAllHotels();
				for (Hotel hotel : hotelList) {
				System.out.println(hotel);
				}
				} catch (Exception e) {
				System.out.println(e.getMessage());
				}
				}*/
				/*
				private void book() throws Exception {

				Scanner sc = new Scanner(System.in);
				Hotel hotel = new Hotel();
				List<Hotel> hotelList = service.viewAllHotels(hotel);
				for (Hotel hotel : hotelList) {
				System.out.println(hotel);
				}

				System.out.print("Enter Hotel ID: ");
				String hId = sc.next();
				try {
				List<RoomDetails> roomList = service.getAllRooms(hId);
				for (RoomDetails rd : roomList) {
				System.out.println(rd);
				}

				System.out.print("Enter Room ID to book Room: ");
				String roomId = sc.next();
				System.out.print("Enter your ID: ");
				String userId = sc.next();
				System.out.print("From (dd-MM-yyyy): ");
				String fdate = sc.next();
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate bookedFrom = LocalDate.parse(fdate, format);

				System.out.print("To (dd-MM-yyyy): ");
				String tdate = sc.next();
				LocalDate bookedTo = LocalDate.parse(tdate, format);
				System.out.print("Total number of Adults: ");
				int noAdult = sc.nextInt();
				System.out.print("Total number of Childrens: ");
				int noChild = sc.nextInt();

				BookingDetails bookingDetails = new BookingDetails();
				bookingDetails.setRoomId(roomId);
				bookingDetails.setUserId(userId);
				bookingDetails.setBookedFrom(bookedFrom);
				bookingDetails.setBookedTo(bookedTo);
				bookingDetails.setNumberOfAdults(noAdult);
				bookingDetails.setNumberOfChildren(noChild);

				BookingDetails bid = service.addBookingDetails(bookingDetails);
				System.out.println("Your Booking ID: " + bid.getBookingId());
				} catch (Exception e) {
				System.out.println(e.getMessage());
				}
				sc.close();
				}
				*//*
				private void view() {
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter Booking ID: ");
				String bid = sc.next();
				try {
				BookingDetails bookingDetails = service.viewBookingDetails(bid);
				System.out.println(bookingDetails);
				} catch (Exception e) {
				System.out.println(e.getMessage());
				}
				sc.close();
				}
				*/
				

			
			
			
		
			/* 
				System.out.println("Invalid User");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
*/
			
			
/*	private void search() {

		try {
			List<Hotel> hotelList = service.viewAllHotels();
			for (Hotel hotel : hotelList) {
				System.out.println(hotel);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}*/
/*
	private void book() throws Exception {

		Scanner sc = new Scanner(System.in);
		Hotel hotel = new Hotel();
		List<Hotel> hotelList = service.viewAllHotels(hotel);
		for (Hotel hotel : hotelList) {
			System.out.println(hotel);
		}

		System.out.print("Enter Hotel ID: ");
		String hId = sc.next();
		try {
			List<RoomDetails> roomList = service.getAllRooms(hId);
			for (RoomDetails rd : roomList) {
				System.out.println(rd);
			}

			System.out.print("Enter Room ID to book Room: ");
			String roomId = sc.next();
			System.out.print("Enter your ID: ");
			String userId = sc.next();
			System.out.print("From (dd-MM-yyyy): ");
			String fdate = sc.next();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate bookedFrom = LocalDate.parse(fdate, format);

			System.out.print("To (dd-MM-yyyy): ");
			String tdate = sc.next();
			LocalDate bookedTo = LocalDate.parse(tdate, format);
			System.out.print("Total number of Adults: ");
			int noAdult = sc.nextInt();
			System.out.print("Total number of Childrens: ");
			int noChild = sc.nextInt();

			BookingDetails bookingDetails = new BookingDetails();
			bookingDetails.setRoomId(roomId);
			bookingDetails.setUserId(userId);
			bookingDetails.setBookedFrom(bookedFrom);
			bookingDetails.setBookedTo(bookedTo);
			bookingDetails.setNumberOfAdults(noAdult);
			bookingDetails.setNumberOfChildren(noChild);

			BookingDetails bid = service.addBookingDetails(bookingDetails);
			System.out.println("Your Booking ID: " + bid.getBookingId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		sc.close();
	}
*//*
	private void view() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Booking ID: ");
		String bid = sc.next();
		try {
			BookingDetails bookingDetails = service.viewBookingDetails(bid);
			System.out.println(bookingDetails);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		sc.close();
	}
*/
//}
