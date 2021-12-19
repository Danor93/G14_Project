package Entities;

import java.io.Serializable;

public class Dish implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String dishName,supplierName,choiceFactor,choiceDetails,ingredients,extra,restCode,size,detailsOfChoice;;
	private float price;
	private int inventory;
	private DishType dishType;


	public Dish(String dishName, String supplierName, String choiceFactor, String choiceDetails, String ingredients,
			String extra, float price, int inventory, DishType dishType) {

		this.dishName = dishName;
		this.supplierName = supplierName;
		this.choiceFactor = choiceFactor;
		this.choiceDetails = choiceDetails;
		this.ingredients = ingredients;
		this.extra = extra;
		this.price = price;
		this.inventory = inventory;
		this.dishType = dishType;
	}

	
	
	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	public String getExtra() {
		return extra;
	}



	public String getChoiceFactor() {
		return choiceFactor;
	}



	public void setChoiceFactor(String choiceFactor) {
		this.choiceFactor = choiceFactor;
	}



	public String getChoiceDetails() {
		return choiceDetails;
	}



	public void setChoiceDetails(String choiceDetails) {
		this.choiceDetails = choiceDetails;
	}



	public String getIngredients() {
		return ingredients;
	}


	public String getDishName() {
		return dishName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public float getPrice() {
		return price;
	}

	public int getInventory() {
		return inventory;
	}

	public DishType getDishType() {
		return dishType;
	}

	public String getRestCode() {
		return restCode;
	}

	public void setRestCode(String restCode) {
		this.restCode = restCode;
	}


	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getDetailsOfChoice() {
		return detailsOfChoice;
	}

	public void setDetailsOfChoice(String detailsOfChoice) {
		this.detailsOfChoice = detailsOfChoice;
	}

	@Override
	public String toString() {
		return "Dish [dishName=" + dishName + ", supplierName=" + supplierName + ", extra=" + extra + ", ingredients="
				+ ingredients + ", restCode=" + restCode + ", choiceFactor=" + choiceFactor + ", detailsOfChoice="
				+ detailsOfChoice + ", price=" + price + ", inventory=" + inventory + ", dishType=" + dishType + "]";
	}
}