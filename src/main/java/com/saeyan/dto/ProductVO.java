package com.saeyan.dto;


public class ProductVO {

	private Integer code,price;
	private String name,description,pictureUrl;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
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
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	@Override
	public String toString() {
		return "ProductVO [code=" + code + ", price=" + price + ", name=" + name + ", description=" + description
				+ ", pictureUrl=" + pictureUrl + "]";
	}
	
	
	
}
