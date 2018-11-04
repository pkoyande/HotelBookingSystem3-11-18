package com.cg.service;



import java.sql.Date;

import com.cg.bean.BookingDetails;
import com.cg.bean.Hotel;
import com.cg.bean.RoomDetails;
import com.cg.bean.Users;
import com.cg.dao.BookingDAO;
import com.cg.dao.IBookingDAO;

public class BookingService implements IBookingService {
	
	IBookingDAO bref;
	public BookingService() {
		bref = new BookingDAO();
		// TODO Auto-generated constructor stub
	}
	//--------------------------------------------------------------------------------------------//
	@Override
	public String addUser(Users user) throws Exception {
		// TODO Auto-generated method stub
		
		return bref.registerUser(user);
	}
	
	//--------------------------------------------------------------------------------------------//
	
	public void viewAllHotels()throws Exception
	{
		return;
	}
	//--------------------------------------------------------------------------------------------//
	@Override
	public RoomDetails searchRoom(int hotelId) throws Exception {
		// TODO Auto-generated method stub
		
		
		return bref.searchRoom(hotelId);
	}
	
	
	//--------------------------------------------------------------------------------------------//
	public String addHotels(Hotel hotel) throws Exception {
		// TODO Auto-generated method stub
		return bref.addHotels(hotel);
	}
	
	//--------------------------------------------------------------------------------------------//
	@Override
	public String validateUser(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		
		
		return bref.validateUser(username, password);
	}
	
	//--------------------------------------------------------------------------------------------//
	@Override
	public int addRoom(RoomDetails room) throws Exception {
		// TODO Auto-generated method stub
		return bref.addRoom(room);
	}
	@Override
	public Users getUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		return bref.getUser(userId);
	}
	@Override
	public Hotel SearchHotels(String hotelname) throws Exception {
		// TODO Auto-generated method stub
		return bref.SearchHotels(hotelname);
	}
	@Override
	public boolean deleteHotel(int hotelId) throws Exception {
		// TODO Auto-generated method stub
		return bref.deleteHotel(hotelId);
	}
	@Override
	public Hotel getHotel(int hotelId) throws Exception {
		// TODO Auto-generated method stub
		return bref.getHotel(hotelId);
	}
	@Override
	public boolean deleteRoom(int roomId) throws Exception {
		// TODO Auto-generated method stub
		return bref.deleteHotel(roomId);
	}
	@Override
	public RoomDetails getRoom(int roomId) throws Exception {
		// TODO Auto-generated method stub
		return bref.getRoom(roomId);
	}
	
	
	@Override
	public int addBooking(BookingDetails book) throws Exception {
		// TODO Auto-generated method stub
		return bref.addBooking(book);
	}
	@Override
	public BookingDetails viewBookingsForHotel(String hotelId) throws Exception {
		// TODO Auto-generated method stub
		return bref.viewBookingsForHotel(hotelId);
	}
	@Override
	public BookingDetails viewBookingsForDate(Date date) throws Exception {
		// TODO Auto-generated method stub
		return bref.viewBookingsForDate(date);
	}
	@Override
	public BookingDetails getBooking(int BookingId) throws Exception {
		// TODO Auto-generated method stub
		return bref.getBooking(BookingId);
	}
	@Override
	public boolean modifyRoom(RoomDetails room) throws Exception {
		// TODO Auto-generated method stub
		return bref.modifyRoom(room);
	}
	@Override
	public boolean modifyHotel(Hotel hotel) throws Exception {
		// TODO Auto-generated method stub
		return bref.modifyHotel(hotel);
	}
	
	
	//--------------------------------------------------------------------------------------------//
	
	
	

}
