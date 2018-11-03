package com.cg.service;

import java.util.ArrayList;

import com.cg.bean.Hotel;
import com.cg.bean.RoomDetails;
import com.cg.bean.Users;


public interface IBookingService {
	public String addUser(Users user)throws Exception;
	public ArrayList<Hotel> viewAllHotels(Hotel hotel)throws Exception;
	public RoomDetails getAllRooms(String hotelId)throws Exception;
	public String addHotels(Hotel hotel)throws Exception;
	public String validateUser(String username,String password) throws Exception;
	
}
