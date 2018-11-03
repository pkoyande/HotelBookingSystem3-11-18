package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;


import com.cg.bean.BookingDetails;
import com.cg.bean.Hotel;
import com.cg.bean.RoomDetails;
import com.cg.bean.Users;

//import com.cg.exception.BookingException;
//import com.cg.exception.BookingException;
import com.cg.utils.DBUtils;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;


public class BookingDAO implements IBookingDAO{
	
	Connection con=null;
	//Logger logger = Logger.getRootLogger();
	
	
	public BookingDAO() {
		//PropertyConfigurator.configure("resources\\log4j.properties");   
		try {
			con = DBUtils.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(con);
	}
//----------------------------------------------------------------------------------------------//	
	public int generateUserId(Users user) throws Exception{
		int id=0;
		int strId=0;
		String str = "select User_Id_Seq.nextval from dual";
		String str1;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str);
			while(rs.next())
			{
				id = rs.getInt(1);
			}
			str1=user.getRole();
			System.out.println(str1);

			if(str1.equalsIgnoreCase("user"))
				strId = id;

		} catch (SQLException e) {
			System.out.println("Cannot");
			e.printStackTrace();
			//logger.error("Cannot generate user id.....");
			//throw new BookingException("Cannot generate user id.....");
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
			ps.setString(5,user.getMobileNo());
			ps.setString(6,user.getPhone());
			ps.setString(7, user.getAddress());
			ps.setString(8,user.getEmail());

			int row = ps.executeUpdate();
			if(row>0)
			System.out.println(" User details are added successfully....");
		
		} catch (SQLException e) {
			e.printStackTrace();
			//logger.error("Cannot add User details....");
			//throw new BookingException("Cannot add User details....");  
		}
		try 
		{
			con.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			//logger.error("Error in closing database connection....");
			//throw new BookingException("Error in closing database connection....");
		}
		return user.getUserName();
	}

	
	//--------------------------------------------------------------------------------------------//
	
	@Override
	public ArrayList<Hotel> viewAllHotels() throws Exception {
		// TODO Auto-generated method stub
		
			ArrayList<Hotel> hotels = null;


			//Hotel =new Hotel();
			String query="select * from Hotel";
			try {

				PreparedStatement ps=con.prepareStatement(query);
				ResultSet res=ps.executeQuery();
				while(res.next())
				{
					Hotel h=new Hotel();
					h.setHotelId(res.getInt("hotel_id"));
					h.setHotelName(res.getString("hotel_name"));
					h.setCity(res.getString("city"));
					h.setAddress(res.getString("address"));
					h.setDescription(res.getString("description"));
					h.setAverageRatePerNight(res.getDouble("avg_rate_per_night"));
					h.setRating(res.getString("rating"));
					h.setEmail(res.getString("email"));
					h.setFax(res.getString("fax"));
					h.setPhoneNo1(res.getString("phone_no1"));
					h.setPhoneNo2(res.getString("phone_no2"));
					
					hotels.add(h);
					
				}
			}catch (SQLException e) {
				//logger.error(e.getMessage());
				//throw new BookingException(e.getMessage());
			}
			
		
		return hotels;
	}
	
	//--------------------------------------------------------------------------------------------//
	
	
	public RoomDetails getAllRooms(String hotelId)
			throws Exception {

//		RoomDetails roomDetails=new RoomDetails();
		String query="select room_id,room_no,room_type,per_night_rate,availability from roomdetails where hotel_id = ?";
		try {

			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, hotelId);
			ResultSet res=ps.executeQuery();
			while(res.next())
			{
				RoomDetails rd=new RoomDetails();
				rd.getHotelId();
				rd.setRoomId(res.getString("room_id"));
				rd.setRoomNo(res.getString("room_no"));
				rd.setRoomType(res.getString("room_type"));
				rd.setPerNightRate(res.getDouble("per_night_rate"));
				rd.setAvailability(res.getInt("availability"));

				return rd;
			//	roomDetails.add(rd);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/*private String generateBookingId() throws Exception{    // Auto generation of Patient id.

		int id=0;
		String strId=null;
		String str = "select Booking_Id_Seq.nextval from dual";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str);
			while(rs.next())
			{
				id = rs.getInt(1);
			}

			String abc=Integer.toString(id);
			strId="B".concat(abc);
		}
		catch (SQLException e) {
			//logger.error("Cannot generate user id.....");
			//throw new BookingException("Cannot generate user id.....");
		}

		return strId;
	}*/

	//--------------------------------------------------------------------------------------------//	
	
	
	public int generateHotelId(Hotel hotel) throws Exception{
		int id=0;
		int strId=0;
		String str = "select Hotel_Id_Seq.nextval from dual";
		String str1;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str);
			while(rs.next())
			{
				id = rs.getInt(1);
			}
			
			/*str1=user.getRole();
			System.out.println(str1);

			if(str1.equalsIgnoreCase("user"))*/
			
			
				strId = id;

		} catch (SQLException e) {
			System.out.println("Cannot");
			e.printStackTrace();
			//logger.error("Cannot generate user id.....");
			//throw new BookingException("Cannot generate user id.....");
		}
		System.out.println(strId);
		return strId;

	}
	
	
	
	
	
	@Override
	public String addHotels(Hotel hotel) throws Exception {
		hotel.setHotelId(generateHotelId(hotel));
	
	/*public String addHotels(Hotel hotel) throws Exception {*/
		// TODO Auto-generated method stub
		//Hotel hl = new Hotel();
		String query ="insert into Hotel values(?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
			PreparedStatement ps =con.prepareStatement(query);
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
			if(row>0)
			System.out.println(" Hotel details are added successfully....");
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return hotel.getHotelName();
	}
	
	

/*	@Override
	public BookingDetails addBookingDetails(BookingDetails bookingDetails) throws Exception {
		// TODO Auto-generated method stub
	BookingDetails booking = new BookingDetails();
	bookingDetails.setBookingId(generateBookingId());
	String sql="insert into BookingDetails(booking_id,room_id,user_id,booked_from,booked_to,no_of_adults,no_of_children,amount) values(?,?,?,?,?,?,?,?)";

		
		
		
		return bookingDetails;
	}
	*/

	//--------------------------------------------------------------------------------------------//	
	public Hotel SearchHotels(int id)throws Exception
	{
		String query= "select * from Hotel where id= ?";
		
		try
		{
			PreparedStatement ps= con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			
			while(result.next())
			{
				int hotelId =result.getInt(1);
				String name=result.getString(2);
				String city = result.getString(3);
				String address = result.getString(4);
				String description = result.getString(5);
				Double avg = result.getDouble(6);
				String rating = result.getString(7);
				String email = result.getString(8);
				String fax = result.getString(9);
				String phone1 = result.getString(10);
				String phone2 = result.getString(11);
				
				Hotel hotel = new Hotel();
				hotel.setHotelId(hotelId);
				hotel.setHotelName(name);
				hotel.setCity(city);
				hotel.setAddress(address);
				hotel.setDescription(description);
				hotel.setAverageRatePerNight(avg);
				hotel.setEmail(email);
				hotel.setFax(fax);
				hotel.setPhoneNo1(phone1);
				hotel.setPhoneNo2(phone2);
				
				return hotel;
							
			}
						
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	
//------------------------------------------------------------------------------------------------------------//
	
public String validateUser(String username,String password)
{
	
		Users newUser = null; //made change here
		//Connection connection = con.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		newUser = new Users();
		String role="";
		try {
			preparedStatement = con.prepareStatement("SELECT * FROM USERS WHERE user_name=? AND password=?" );
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			//preparedStatement.setString(3, role);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				String name= resultSet.getString(1);
				String pass =resultSet.getString(2);
				role = resultSet.getString(3);
				/*newUser.setUserId(resultSet.getString("USER_ID"));
				newUser.setPassword(resultSet.getString("PASSWORD"));
				newUser.setRole(resultSet.getString("ROLE"));
				newUser.setUserName(resultSet.getString("USER_NAME"));
				newUser.setMobileNo(resultSet.getString("MOBILE_NO"));
				newUser.setPhone(resultSet.getString("PHONE"));
				newUser.setAddress(resultSet.getString("ADDRESS"));
				newUser.setEmail(resultSet.getString("EMAIL"));*/
				newUser.setUserName(name);
				newUser.setRole(role);
				if(newUser.equals(password))
				{
					System.out.println("Valid User");
				}
				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		//	Client.logger.error("Exception while user sign in: "+e.getMessage());
			//throw new ConnectionException("Technical problem occured. Refer log.");
		}// end of catch
		/*finally {
			try {
				resultSet.close();
				preparedStatement.close();
				con.close();

			}// end of try
			catch (SQLException e) {
				//Client.logger.error("Exception while closing db connection: "+e.getMessage());
				//throw new ConnectionException("Error in closing db connection");

			}// end of catch
*/		
		// end of finally block
		return role;
	}//end of UserSignin method
	
	
	
	
	
}
	
	
	


