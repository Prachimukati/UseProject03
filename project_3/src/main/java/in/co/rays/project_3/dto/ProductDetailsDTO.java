package in.co.rays.project_3.dto;

import java.util.Date;

public class ProductDetailsDTO extends BaseDTO {
	
	private String name;
	private String description;
	private int price;
	private Date dateOfPerchase;
	private String category;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getDateOfPerchase() {
		return dateOfPerchase;
	}
	public void setDateOfPerchase(Date dateOfPerchase) {
		this.dateOfPerchase = dateOfPerchase;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
