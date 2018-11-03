package com.cg.service;

import java.util.ArrayList;

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
	
	public ArrayList<Hotel> viewAllHotels(Hotel hotel)throws Exception
	{
		return bref.viewAllHotels();
	}
	//--------------------------------------------------------------------------------------------//
	@Override
	public RoomDetails getAllRooms(String hotelId) throws Exception {
		// TODO Auto-generated method stub
		
		
		return bref.getAllRooms(hotelId);
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
	

}
