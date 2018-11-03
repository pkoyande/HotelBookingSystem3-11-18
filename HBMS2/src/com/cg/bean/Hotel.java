package com.cg.bean;



public class Hotel {

	
	private int hotelId;
	
	private String hotelName;
	private String city;
	private String address;
	private String description;
	
	private double averageRatePerNight;
	private String rating;
	private String email;
	private String fax;
	
	private String phoneNo1;
	
	private String phoneNo2;





	//getters and setters

	

	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", city=" + city + ", address=" + address
				+ ", description=" + description + ", averageRatePerNight=" + averageRatePerNight + ", rating=" + rating
				+ ", email=" + email + ", fax=" + fax + ", phoneNo1=" + phoneNo1 + ", phoneNo2=" + phoneNo2 + "]";
	}

	public String getHotelName() {
		return hotelName;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAverageRatePerNight() {
		return averageRatePerNight;
	}

	public void setAverageRatePerNight(double averageRatePerNight) {
		this.averageRatePerNight = averageRatePerNight;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhoneNo1() {
		return phoneNo1;
	}

	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}

	public String getPhoneNo2() {
		return phoneNo2;
	}

	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}

}
