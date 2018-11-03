package com.cg.bean;

import java.time.LocalDate;

public class BookingDetails {
	

	private String bookingId;

	private String roomId;

	private String userId;
	
	private	LocalDate bookedFrom;

	private	LocalDate bookedTo;
	
	private int numberOfAdults;
	
	private int numberOfChildren;
	private double amount;





	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", roomId=" + roomId
				+ ", userId=" + userId + ", bookedFrom=" + bookedFrom
				+ ", bookedTo=" + bookedTo + ", numberOfAdults="
				+ numberOfAdults + ", numberOfChildren=" + numberOfChildren
				+ ", amount=" + amount + "]";
	}


	//getters and setters
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDate getBookedFrom() {
		return bookedFrom;
	}
	public void setBookedFrom(LocalDate bookedFrom) {
		this.bookedFrom = bookedFrom;
	}
	public LocalDate getBookedTo() {
		return bookedTo;
	}
	public void setBookedTo(LocalDate bookedTo) {
		this.bookedTo = bookedTo;
	}
	public int getNumberOfAdults() {
		return numberOfAdults;
	}
	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}
	public int getNumberOfChildren() {
		return numberOfChildren;
	}
	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
