package com.cg.dao;

import java.util.ArrayList;

import com.cg.bean.BookingDetails;
import com.cg.bean.Hotel;
import com.cg.bean.RoomDetails;
import com.cg.bean.Users;



public interface IBookingDAO {
	
	public String registerUser(Users user)throws Exception;
	public ArrayList<Hotel> viewAllHotels() throws Exception;
	public RoomDetails getAllRooms(String hotelId)throws Exception;
	public String addHotels(Hotel hotel)throws Exception;
	public Hotel SearchHotels(int id)throws Exception;
	public String validateUser(String username,String password) throws Exception;
	
}
