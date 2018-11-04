package com.cg.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.cg.bean.BookingDetails;
import com.cg.bean.Hotel;
import com.cg.bean.RoomDetails;
import com.cg.bean.Users;

import com.cg.utils.DBUtils;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class BookingDAO implements IBookingDAO {

	Connection con = null;
	// Logger logger = Logger.getRootLogger();

	public BookingDAO() {
		// PropertyConfigurator.configure("resources\\log4j.properties");
		try {
			con = DBUtils.getConnection();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(con);
	}

	/********************************************************************************
	 * User Functions 
	 * 1. AddUser 
	 * 2. getUser 
	 * 3. validateUser
	 * 
	 *********************************************************************************/

//----------------------------------------------------------------------------------------------//	
	public int generateUserId(Users user) throws Exception {
		int id = 0;
		int strId = 0;
		String str = "select User_Id_Seq.nextval from dual";
		String str1;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str);
			while (rs.next()) {
				id = rs.getInt(1);
			}
			str1 = user.getRole();
			System.out.println(str1);

			if (str1.equalsIgnoreCase("user"))
				strId = id;

		} catch (SQLException e) {
			System.out.println("Cannot");
			e.printStackTrace();
			// logger.error("Cannot generate user id.....");
			// throw new BookingException("Cannot generate user id.....");
		}
		System.out.println(strId);
		return strId;

	}

	@Override
	public String registerUser(Users user) throws Exception {
		user.setUserId(generateUserId(user));

		String query = "insert into Users values(?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, user.getUserId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole());
			ps.setString(4, user.getUserName());
			ps.setString(5, user.getMobileNo());
			ps.setString(6, user.getPhone());
			ps.setString(7, user.getAddress());
			ps.setString(8, user.getEmail());

			int row = ps.executeUpdate();
			if (row > 0)
				System.out.println(" User details are added successfully....");

		} catch (SQLException e) {
			e.printStackTrace();
			// logger.error("Cannot add User details....");
			// throw new BookingException("Cannot add User details....");
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// logger.error("Error in closing database connection....");
			// throw new BookingException("Error in closing database connection....");
		}
		return user.getUserName();
	}

	// --------------------------------------------------------------------------------------------//
	@Override
	public Users getUser(int userId) throws Exception {
		Users newuser = new Users();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM USERS WHERE user_id=?");
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				newuser.setUserId(userId);
				newuser.setPassword(resultSet.getString("PASSWORD"));
				newuser.setRole(resultSet.getString("ROLE"));
				newuser.setUserName(resultSet.getString("USER_NAME"));
				newuser.setMobileNo(resultSet.getString("MOBILE_NO"));
				newuser.setPhone(resultSet.getString("PHONE"));
				newuser.setAddress(resultSet.getString("ADDRESS"));
				newuser.setEmail(resultSet.getString("EMAIL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Client.logger.error("Exception while retrieving details for user id
			// "+userId+" : "+e.getMessage());
			// throw new ConnectionException("Technical problems occured. Refer log.");
		} // end of catch
		finally {
			try {
				resultSet.close();
				preparedStatement.close();
				con.close();
			} // end of try
			catch (SQLException e) {
				// Client.logger.error("Exception while closing db connection:
				// "+e.getMessage());
				// throw new ConnectionException("Error in closing db connection");

			} // end of catch
		} // end of finally block
		return newuser;

	}// end of getUser method
		// --------------------------------------------------------------------------------------------//

	@Override
	public String validateUser(String username, String password) {

		Users newUser = null; // made change here
		// Connection connection = con.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		newUser = new Users();
		String role = "";
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM USERS WHERE user_name=? AND password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			// preparedStatement.setString(3, role);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String name = resultSet.getString(1);
				String pass = resultSet.getString(2);
				role = resultSet.getString(3);
				/*
				 * newUser.setUserId(resultSet.getString("USER_ID"));
				 * newUser.setPassword(resultSet.getString("PASSWORD"));
				 * newUser.setRole(resultSet.getString("ROLE"));
				 * newUser.setUserName(resultSet.getString("USER_NAME"));
				 * newUser.setMobileNo(resultSet.getString("MOBILE_NO"));
				 * newUser.setPhone(resultSet.getString("PHONE"));
				 * newUser.setAddress(resultSet.getString("ADDRESS"));
				 * newUser.setEmail(resultSet.getString("EMAIL"));
				 */
				newUser.setUserName(name);
				newUser.setRole(role);
				if (pass.equals(password)) {
					System.out.println("Valid User");
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			// Client.logger.error("Exception while user sign in: "+e.getMessage());
			// throw new ConnectionException("Technical problem occured. Refer log.");
		} // end of catch
		/*
		 * finally { try { resultSet.close(); preparedStatement.close(); con.close();
		 * 
		 * }// end of try catch (SQLException e) {
		 * //Client.logger.error("Exception while closing db connection: "+e.getMessage(
		 * )); //throw new ConnectionException("Error in closing db connection");
		 * 
		 * }// end of catch
		 */
		// end of finally block
		return role;
	}// end of UserSignin method

	// --------------------------------------------------------------------------------------------//

	/********************************************************************************
	 * Hotel Functions
	 *  1. AddHotel 
	 *  2. View All Hotels 
	 *  3. Search Hotel 
	 *  4. Delete Hotel 
	 *  5. getHotel
	 *  6. Modify Hotel
	 * 
	 *********************************************************************************/
	@Override
	public void viewAllHotels() throws Exception {
		 

		// Hotel =new Hotel();
		String query = "select * from Hotel";
		try {

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				Hotel h = new Hotel();
				int id = res.getInt("hotel_id");
				String hname = res.getString("hotel_name");
				String city = res.getString("city");
				String address = res.getString("address");
				String desc = res.getString("description");
				Double rate = res.getDouble("avg_rate_per_night");
				String rating = res.getString("rating");
				String email = res.getString("email");
				String fax = res.getString("fax");
				String p1 = res.getString("phone_no1");
				String p2 = res.getString("phone_no2");

				h.setHotelId(id);
				h.setHotelName(hname);
				h.setCity(city);
				h.setAddress(address);
				h.setDescription(desc);
				h.setAverageRatePerNight(rate);
				h.setRating(rating);
				h.setEmail(email);
				h.setFax(fax);
				h.setPhoneNo1(p1);
				h.setPhoneNo2(p2);
				System.out.print(id + " " + hname + " " + city + "  " + address + "  " + desc + "  " + rate + "  "
						+ "  " + rating + "  " + email + "  " + fax + "  " + p1 + "  " + p2);

			}
		} catch (SQLException e) {
			// logger.error(e.getMessage());
			// throw new BookingException(e.getMessage());
		}

	}

	// --------------------------------------------------------------------------------------------//
	@Override
	public boolean deleteHotel(int hotelId) throws Exception {

		int queryResult = 0;
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = con.prepareStatement("delete from hotel where hotel_id= ? ");
			preparedStatement.setInt(1, hotelId);
			queryResult = preparedStatement.executeUpdate();

			if (queryResult == 0) {
				System.out.println("Deletion Failed");
				/*
				 * Client.logger.error("Deletion failed."); throw new
				 * ConnectionException("Deleting hotel details failed.");
				 */

			} // end of if

			else {
				System.out.println("Deletion of " + hotelId + " successful!!");
				// Client.logger.info("Hotel details for Hotel Id "+hotelId+" deleted
				// successfully.");
				return true;

			} // end of else

		} // end of try

		catch (SQLException e) {
			e.printStackTrace();
			/*
			 * Client.logger.error("Exception occured while hotel deletion: "+e.getMessage()
			 * ); throw new ConnectionException("Technical problems occured. Refer log.");
			 */

		} // end of catch
		finally {
			try {
				con.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;

	}// end of method

//--------------------------------------------------------------------------------------------------------//
	@Override
	public Hotel getHotel(int hotelId) throws Exception {

		Hotel hotel = new Hotel();
		; // made change here

		PreparedStatement ps = null;
		ResultSet resultset = null;

		try {

			ps = con.prepareStatement("SELECT * FROM HOTEL  WHERE  hotel_id=?");
			ps.setInt(1, hotelId);
			resultset = ps.executeQuery();

			if (resultset.next()) {

				hotel.setHotelId(resultset.getInt(1));
				hotel.setCity(resultset.getString("CITY"));
				hotel.setHotelName(resultset.getString("HOTEL_NAME"));
				hotel.setAddress(resultset.getString("ADDRESS")); // made change here
				hotel.setDescription(resultset.getString("DESCRIPTION"));
				hotel.setAverageRatePerNight(resultset.getDouble("AVG_RATE_PER_NIGHT"));
				hotel.setPhoneNo1(resultset.getString("PHONE_NO1"));
				hotel.setPhoneNo2(resultset.getString("PHONE_NO2"));
				hotel.setRating(resultset.getString("RATING"));
				hotel.setEmail(resultset.getString("EMAIL"));
				hotel.setFax(resultset.getString("FAX"));

			} // end of if
			/*
			 * Client.logger.error("Details of hotel with Hotel Id "
			 * +hotelId+" retrieved successfully.");
			 */
			return hotel;

		} // end of try

		catch (Exception e) {
			/*
			 * Client.logger.error("Exception while retrieving hotel for Hotel Id "
			 * +hotelId+" :"+e.getMessage()); throw new
			 * ConnectionException("Technical problems occured. Refer log.");
			 */
		} // end of catch

		finally {

			try {

				resultset.close();
				ps.close();
				con.close();

			} // end of try

			catch (SQLException e) {

				/*
				 * Client.logger.error("Exception in closing db connection : "+e.getMessage());
				 * throw new ConnectionException("Error in closing db connection.");
				 */
			} // end of catch

		} // end of finally block
		return hotel;

	}// end of method

	// ---------------------------------------------------------------------------------------------//

	// --------------------------------------------------------------------------------------------//

	public int generateHotelId(Hotel hotel) throws Exception {
		int id = 0;
		int strId = 0;
		String str = "select Hotel_Id_Seq.nextval from dual";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str);
			while (rs.next()) {
				id = rs.getInt(1);
			}

			/*
			 * str1=user.getRole(); System.out.println(str1);
			 * 
			 * if(str1.equalsIgnoreCase("user"))
			 */

			strId = id;

		} catch (SQLException e) {
			System.out.println("Cannot");
			e.printStackTrace();
			// logger.error("Cannot generate user id.....");
			// throw new BookingException("Cannot generate user id.....");
		}
		System.out.println(strId);
		return strId;

	}

	@Override
	public String addHotels(Hotel hotel) throws Exception {
		hotel.setHotelId(generateHotelId(hotel));

		/* public String addHotels(Hotel hotel) throws Exception { */
		
		// Hotel hl = new Hotel();
		String query = "insert into Hotel values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, hotel.getHotelId());
			ps.setString(2, hotel.getHotelName());
			ps.setString(3, hotel.getCity());
			ps.setString(4, hotel.getAddress());
			ps.setString(5, hotel.getDescription());
			ps.setDouble(6, hotel.getAverageRatePerNight());
			ps.setString(7, hotel.getRating());
			ps.setString(8, hotel.getEmail());
			ps.setString(9, hotel.getFax());
			ps.setString(10, hotel.getPhoneNo1());
			ps.setString(11, hotel.getPhoneNo2());

			ps.executeUpdate(query);

			int row = ps.executeUpdate();
			if (row > 0)
				System.out.println(" Hotel details are added successfully....");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hotel.getHotelName();
	}

	// --------------------------------------------------------------------------------------------//
	@Override
	public Hotel SearchHotels(String hotelname) throws Exception {
		String query = "select * from Hotel where hotel_name= ?";
		Hotel hotel = new Hotel();

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, hotelname);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				int hotelId = result.getInt(1);
				String name = result.getString(2);
				String city = result.getString(3);
				String address = result.getString(4);
				String description = result.getString(5);
				Double avg = result.getDouble(6);
				String rating = result.getString(7);
				String email = result.getString(8);
				String fax = result.getString(9);
				String phone1 = result.getString(10);
				String phone2 = result.getString(11);

				hotel.setHotelId(hotelId);
				hotel.setHotelName(name);
				hotel.setCity(city);
				hotel.setAddress(address);
				hotel.setDescription(description);
				hotel.setAverageRatePerNight(avg);
				hotel.setRating(rating);
				hotel.setEmail(email);
				hotel.setFax(fax);
				hotel.setPhoneNo1(phone1);
				hotel.setPhoneNo2(phone2);
				System.out.print(hotelId + " " + name + " " + city + " " + address + "  " + description + "  " + avg
						+ "  " + "  " + rating + "  " + email + "  " + fax + "  " + phone1 + "  " + phone2);

				return hotel;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hotel;

	}
	//--------------------------------------------------------------------------------------------------------//
	@Override
	public boolean modifyHotel(Hotel hotel) throws Exception {
		
		
		PreparedStatement preparedStatement=null;	
		int queryResult=0;
		
		try {
				
			preparedStatement=con.prepareStatement("update hotel set city=?,hotel_name=?,address=?,description=?,avg_rate_per_night=?,phone_no1=?,phone_no2=?,rating=?,email=?,fax=? where hotel_id=?");
			
			preparedStatement.setString(1,hotel.getCity());			
			preparedStatement.setString(2,hotel.getHotelName());
			preparedStatement.setString(3,hotel.getAddress());
			preparedStatement.setString(4,hotel.getDescription());
			preparedStatement.setDouble(5,hotel.getAverageRatePerNight());
			preparedStatement.setString(6,hotel.getPhoneNo1());
			preparedStatement.setString(7,hotel.getPhoneNo2());
			preparedStatement.setString(8,hotel.getRating());
			preparedStatement.setString(9,hotel.getEmail());
			preparedStatement.setString(10,hotel.getFax());
			preparedStatement.setInt(11,hotel.getHotelId());
			
			queryResult=preparedStatement.executeUpdate();
			
			if(queryResult > 0){
				
				//Client.logger.error("Hotel Modification failed.");
				return true;
			}//end of if
			
			else{
				
				//Client.logger.info("Hotel details modified successfully for Hotel Id: "+hotel.getHotelId());
				return false;
				
			}//end of else
			
		}//end of try
		
		catch (SQLException e) {
			
			/*Client.logger.error("Exception occured while hotel details of hotel = "+hotel+" :"+e.getMessage());
			throw new ConnectionException("Technical Problems occured. Refer log.");*/
			
		}//end of catch
		finally{
			try {
				con.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return true;
		
		
		
	}//end of method
	
	
	// -------------------------------------------------------------------------------------------------------//

	/**************************************************************************
	 * 				Room Functions
	 * 1. getAllRooms
	 * 2. Add Room 
	 * 3. Delete Room 
	 * 4. Get Room
	 * 5. Modify Room
	 * 
	 ****************************************************************************/
	public RoomDetails searchRoom(int hotelId) throws Exception {

//		RoomDetails roomDetails=new RoomDetails();
		String query = "select room_id,room_no,room_type,per_night_rate,availability from roomdetails where hotel_id = ?";
		try {

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, hotelId);
			ResultSet res = ps.executeQuery();
			while (res.next()) {

				RoomDetails rd = new RoomDetails();

				rd.setRoomId(res.getInt("room_id"));
				rd.setHotel(new BookingDAO().getHotel(res.getInt("hotel_id")));
				rd.setRoomNo(res.getString("room_no"));
				rd.setRoomType(res.getString("room_type"));
				rd.setPerNightRate(res.getDouble("per_night_rate"));
				rd.setAvailability(res.getString("availability"));

				return rd;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// --------------------------------------------------------------------------------------------//

	public int generateRoomId(RoomDetails room) throws Exception {
		int id = 0;
		int strId = 0;
		String str = "select Room_Id_Seq.nextval from dual";
		// String str1;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str);
			while (rs.next()) {
				id = rs.getInt(1);
			}

			/*
			 * str1=user.getRole(); System.out.println(str1);
			 * 
			 * if(str1.equalsIgnoreCase("user"))
			 */

			strId = id;

		} catch (SQLException e) {
			System.out.println("Cannot");
			e.printStackTrace();
			// logger.error("Cannot generate user id.....");
			// throw new BookingException("Cannot generate user id.....");
		}
		System.out.println(strId);
		return strId;

	}

	@Override
	public int addRoom(RoomDetails room) throws Exception {
		room.setRoomId(generateRoomId(room));

		/* public String addHotels(Hotel hotel) throws Exception { */
		
		// Hotel hl = new Hotel();
		String query = "insert into RoomDetails values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, room.getHotel().getHotelId());
			ps.setInt(2, room.getRoomId());
			ps.setString(3, room.getRoomNo());
			ps.setString(4, room.getRoomType());
			ps.setDouble(5, room.getPerNightRate());
			ps.setString(6, room.getAvailability());

			ps.executeUpdate(query);

			int row = ps.executeUpdate();
			if (row > 0)
				System.out.println(" Hotel details are added successfully....");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return room.getRoomId();
	}

//----------------------------------------------------------------------------------------------//
	public boolean deleteRoom(int roomId) throws Exception {

		boolean isDeleted = false;

		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = con.prepareStatement("Delete from roomdetails where room_id=?");

			preparedStatement.setInt(1, roomId);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {
				isDeleted = true;
				// Client.logger.info("Room successfully deleted with room Id : "+roomId);
			}

		} catch (SQLException e) {

			// Client.logger.error("Exception while deleting room with room Id "+roomId+"
			// :"+e.getMessage());
			// throw new ConnectionException("Room data could not be deleted from
			// database!");

		} finally {

			try {

				preparedStatement.close();
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
				/*
				 * Client.logger.error("Exception while closing db connection: "+e.getMessage())
				 * ; throw new ConnectionException( "Error in closing db connection.");
				 */

			}

		} // end of catch

		return isDeleted;

	}// end of adminDeleteRoom

//----------------------------------------------------------------------------------------------//
	public RoomDetails getRoom(int roomId) throws Exception {

		RoomDetails room = new RoomDetails();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			preparedStatement = con.prepareStatement("Select * from roomdetails where room_id=?");
			preparedStatement.setInt(1, roomId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				room.setRoomId(resultSet.getInt("ROOM_ID"));
				room.setHotel(new BookingDAO().getHotel(resultSet.getInt("HOTEL_ID")));
				room.setRoomNo(resultSet.getString("ROOM_NO"));
				room.setRoomType(resultSet.getString("ROOM_TYPE"));
				room.setPerNightRate(resultSet.getDouble("PER_NIGHT_RATE"));
				room.setAvailability(resultSet.getString("ÄVAILABILITY"));

				// String available = resultSet.getString("AVAILABILITY");

				/*
				 * if (available.equals("Y")) { room.setAvailability(true); } else {
				 * room.setAvailability(false); }
				 */

				// }// end of if
			}
			if (room != null) {
				/*
				 * Client.logger.info("Room details regarding room ID " + room.getRoomId() +
				 * " found successfully.");
				 */
				System.out.println(room.getRoomId());

				return room;
			}

		} catch (SQLException e) {

			/*
			 * Client.logger.error("Exception occured while retrieving details for room Id "
			 * +roomId+" :"+e.getMessage()); throw new ConnectionException(
			 * "Room data could not be retrieved from database!");
			 */ e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * Client.logger.error("Exception occured while retrieving details for room Id "
			 * +roomId+" :"+e.getMessage()); throw new ConnectionException(
			 * "Room data could not be retrieved from database!");
			 * 
			 */
		} finally {

			try {

				con.close();
				preparedStatement.close();

			} catch (SQLException e) {

				/*
				 * Client.logger.error("Exception occured while closing db connection: "+e.
				 * getMessage()); throw new ConnectionException(
				 * "Room data could not be modified from database!");
				 */ e.printStackTrace();
			} // end of catch

		} // end of catch
		return room;

	}// end of getRoom

//--------------------------------------------------------------------------------------------//	

	public boolean modifyRoom(RoomDetails room) throws Exception {

		boolean result = false;
		int n = 0;
		
		PreparedStatement preparedStatement = null;
		

		try {

			preparedStatement = con.prepareStatement("Update roomdetails set hotel_id=?,room_no=?,room_type=?,per_night_rate=?,availability=?,photo_path=? where room_id=?");
			
			preparedStatement.setInt(1, room.getHotel().getHotelId());
			preparedStatement.setString(2, room.getRoomNo());
			preparedStatement.setString(3, room.getRoomType());
			preparedStatement.setDouble(4, room.getPerNightRate());
			preparedStatement.setString(5, room.getAvailability());			
			preparedStatement.setInt(7, room.getRoomId());

			n = preparedStatement.executeUpdate();

			if (n > 0) {
				result = true;
				System.out.println("Hotel Details modified sucessfully");
				//Client.logger.info("Room data modified for room Id : "+room.getRoomId());
			}
			else{
				//Client.logger.info("Room data not modified for room Id : "+room.getRoomId());
			}

		} catch (SQLException e) {

			/*
			 * Client.logger.error("Exception while modfying room data for room Id "+room.
			 * getRoomId()+" :"+e.getMessage()); throw new ConnectionException(
			 * "Room data could not be modified from database!");
			 */

		} 

		return result;

	}// end of adminModifyRoom

	
	
	
	

//------------------------------------------------------------------------------------------------//	
	/****************************************************************
	 * Booking Functions
	 *  1. Add Booking 
	 *  2. View Booking for Hotel
	 *  3. View Booking for Date
	 *  4. Get Booking
	 * 
	 * 
	 **************************************************************/
	public int generateBookingId(BookingDetails book) throws Exception {
		int id = 0;
		int strId = 0;
		String str = "select Book_Id_Seq.nextval from dual";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str);
			while (rs.next()) {
				id = rs.getInt(1);
			}

			strId = id;

		} catch (SQLException e) {
			e.printStackTrace();
			// logger.error("Cannot generate user id.....");
			// throw new BookingException("Cannot generate user id.....");
		}

		return strId;

	}

	@Override
	public int addBooking(BookingDetails book) throws Exception {
		book.setBookingId(generateBookingId(book));

		String query = "INSERT INTO bookingdetails VALUES(?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, book.getBookingId());
			ps.setInt(2, book.getRoom().getRoomId());
			ps.setInt(3, book.getUser().getUserId());
			ps.setDate(4, book.getBookedFrom());
			ps.setDate(5, book.getBookedTo());
			ps.setDouble(6, book.getNumberOfAdults());
			ps.setInt(7, book.getNumberOfChildren());
			ps.setDouble(8, book.getAmount());
			int row = ps.executeUpdate();
			if (row > 0)
				System.out.println(" Booking details are added successfully....");

		} catch (SQLException e) {
			e.printStackTrace();
			// logger.error("Cannot add User details....");
			// throw new BookingException("Cannot add User details....");
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// logger.error("Error in closing database connection....");
			// throw new BookingException("Error in closing database connection....");
		}
		return book.getBookingId();
	}

//----------------------------------------------------------------------------------------------//	
	public BookingDetails viewBookingsForHotel(String hotelId) throws Exception {

		PreparedStatement ps = null;
		ResultSet resultset = null;
		BookingDetails book = new BookingDetails();

		try {

			ps = con.prepareStatement(
					"select * from bookingdetails inner join roomdetails on bookingdetails.room_id=roomdetails.room_id  where roomdetails.hotel_id=?");
			ps.setString(1, hotelId);
			resultset = ps.executeQuery();

			while (resultset.next()) {

				book.setBookingId(resultset.getInt("BOOKING_ID"));
				book.setRoom(new BookingDAO().getRoom(resultset.getInt("ROOM_ID")));
				book.setUser(new BookingDAO().getUser(resultset.getInt("USER_ID")));
				book.setBookedFrom(resultset.getDate("BOOKED_FROM"));
				book.setBookedTo(resultset.getDate("BOOKED_TO"));
				book.setNumberOfAdults(resultset.getInt("NO_OF_ADULTS"));
				book.setNumberOfChildren(resultset.getInt("NO_OF_CHILDREN"));
				book.setAmount(resultset.getDouble("AMOUNT"));

				System.out.println(book.getBookingId() + "  " + book.getRoom().getRoomId() + "  "
						+ book.getUser().getUserId() + "  " + book.getBookedFrom() + "  " + book.getBookedTo() + "  "
						+ book.getNumberOfAdults() + "  " + book.getNumberOfChildren() + "  " + book.getAmount());

			} // end of while

			// Client.logger.info("Bookings for hotel "+hotelId+" retrieved successfully.");

		} // end of try

		catch (SQLException e) {

			/*
			 * Client.logger.error("Exception while retrieving bookings for hotel ID " +
			 * hotelId + ": " + e.getMessage()); throw new ConnectionException(
			 * "Technical problem occured. Refer log.");
			 */
		} // end of catch

		finally {

			try {

				resultset.close();
				ps.close();
				con.close();
			} // end of try

			catch (SQLException e) {

				/*
				 * Client.logger.error("Exception in closing DB connection: " + e.getMessage());
				 * throw new ConnectionException("Error in closing db connection. Refer log.");
				 */
			} // end of catch

		} // end of finally block
		return book;
	}// end of method

//----------------------------------------------------------------------------------------------//	
	public BookingDetails viewBookingsForDate(Date date) throws Exception {

		PreparedStatement ps = null;
		ResultSet resultset = null;
		BookingDetails book = new BookingDetails();

		try {

			ps = con.prepareStatement("select * from bookingdetails where ? >=booked_from And ? <=booked_to");
			ps.setDate(1, date);
			ps.setDate(2, date);
			resultset = ps.executeQuery();

			while (resultset.next()) {

				book.setBookingId(resultset.getInt("BOOKING_ID"));
				book.setRoom(new BookingDAO().getRoom(resultset.getInt("ROOM_ID")));
				book.setUser(new BookingDAO().getUser(resultset.getInt("USER_ID")));
				book.setBookedFrom(resultset.getDate("BOOKED_FROM"));
				book.setBookedTo(resultset.getDate("BOOKED_TO"));
				book.setNumberOfAdults(resultset.getInt("NO_OF_ADULTS"));
				book.setNumberOfChildren(resultset.getInt("NO_OF_CHILDREN"));
				book.setAmount(resultset.getDouble("AMOUNT"));

				System.out.println(book.getBookingId() + "  " + book.getRoom().getRoomId() + "  "
						+ book.getUser().getUserId() + "  " + book.getBookedFrom() + "  " + book.getBookedTo() + "  "
						+ book.getNumberOfAdults() + "  " + book.getNumberOfChildren() + "  " + book.getAmount());

			} // end of while

			// Client.logger.info("Bookings for date "+date+" retrieved successfully.");

			// return bookingList;

		} // end of try

		catch (SQLException e) {

			/*
			 * Client.logger.error("Exception in retrieving bookings for date:" + date +
			 * ": " + e.getMessage()); throw new
			 * ConnectionException("Tehnical problem occured. Refer log.");
			 */
		} // end of catch

		finally {

			try {

				resultset.close();
				ps.close();
				con.close();

			} // end of try

			catch (SQLException e) {

				/*
				 * Client.logger.error("Exception in closing DB connection: " + e.getMessage());
				 * throw new ConnectionException("Error in closing db connection. Refer log.");
				 */
			} // end of catch

		} // end of finally block
		return book;

	}// end of method

	// ----------------------------------------------------------------------------------------------//
	public BookingDetails getBooking(int BookingId) throws Exception {

		BookingDetails book = new BookingDetails();
		PreparedStatement ps = null;
		ResultSet resultset = null;

		try {

			ps = con.prepareStatement("select * from bookingdetails where booking_id=?");
			ps.setInt(1, BookingId);
			resultset = ps.executeQuery();

			while(resultset.next()) {

				book.setBookingId(resultset.getInt("BOOKING_ID"));
				book.setRoom(new BookingDAO().getRoom(resultset.getInt("ROOM_ID")));
				book.setUser(new BookingDAO().getUser(resultset.getInt("USER_ID")));
				book.setBookedFrom(resultset.getDate("BOOKED_FROM"));
				book.setBookedTo(resultset.getDate("BOOKED_TO"));
				book.setNumberOfAdults(resultset.getInt("NO_OF_ADULTS"));
				book.setNumberOfChildren(resultset.getInt("NO_OF_CHILDREN"));
				book.setAmount(resultset.getDouble("AMOUNT"));

			} // end of if

			// Client.logger.info("Details for booking "+BookingId+" retrieved
			// successfully.");

		} // end of try

		catch (SQLException e) {

			/*
			 * Client.logger.error("Exception in retrieving booking for booking ID:" +
			 * BookingId + ": " + e.getMessage()); throw new
			 * ConnectionException("Tehnical problem occured. Refer log.");
			 */

		} // end of catch

		finally {

			try {

				resultset.close();
				ps.close();
				con.close();

			} // end of try

			catch (SQLException e) {

				/*
				 * Client.logger.error("Exception in closing DB connection: " + e.getMessage());
				 * throw new ConnectionException("Error in closing db connection. Refer log.");
				 */

			} // end of catch

		} // end of finally block

		return book;

	}// end of getBooking method

	// ----------------------------------------------------------------------------------------------//s
}
