package com.cg.service;

import java.sql.Date;

import com.cg.bean.BookingDetails;
import com.cg.bean.Hotel;
import com.cg.bean.RoomDetails;
import com.cg.bean.Users;


public interface IBookingService {
	//18 Functions
	//Users
	public String addUser(Users user)throws Exception;
	public String validateUser(String username,String password) throws Exception;
	public Users getUser(int userId) throws Exception; 
	
	//Rooms
	public RoomDetails searchRoom(int hotelId)throws Exception;
	public int addRoom(RoomDetails room) throws Exception;
	public boolean deleteRoom(int roomId) throws Exception;
	public RoomDetails getRoom(int roomId) throws Exception;
	public boolean modifyRoom(RoomDetails room) throws Exception;
	
	//Hotels
	
	public Hotel SearchHotels(String hotelname)throws Exception;
	public boolean deleteHotel(int hotelId) throws Exception;
	Hotel getHotel(int hotelId) throws Exception;
	public void viewAllHotels()throws Exception;
	public String addHotels(Hotel hotel)throws Exception;
	public boolean modifyHotel(Hotel hotel) throws Exception;
	
	
	//Booking
	public int addBooking(BookingDetails book) throws Exception;
	public BookingDetails viewBookingsForHotel(String hotelId) throws Exception;
	public BookingDetails viewBookingsForDate(Date date) throws Exception;
	public BookingDetails getBooking(int BookingId) throws Exception;
	
}
