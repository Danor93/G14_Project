package Entities;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Restaurant;
	private int OrderNumber;
	private String OrderTime;
	private String PhoneNumber;
	private OrderType OrderType;
	private String OrderAddress;

	public Order(String Restaurant, int orderNumber, String orderTime, String phoneNumber, OrderType OrderType,
			String orderAddress) {
		super();
		this.Restaurant = Restaurant;
		this.OrderNumber = orderNumber;
		this.OrderTime = orderTime;
		this.PhoneNumber = phoneNumber;
		this.OrderType = OrderType;
		this.OrderAddress = orderAddress;
	}

	public String getRestaurant() {
		return Restaurant;
	}

	public void setRestaurant(String resturant) {
		Restaurant = resturant;
	}

	public int getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		OrderNumber = orderNumber;
	}

	public String getOrderTime() {
		return OrderTime;
	}

	public void setOrderTime(String orderTime) {
		OrderTime = orderTime;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public OrderType getOrderType() {
		return OrderType;
	}

	public void setOrderType(OrderType OrderType) {
		this.OrderType = OrderType;
	}

	public String getOrderAddress() {
		return OrderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		OrderAddress = orderAddress;
	}
}