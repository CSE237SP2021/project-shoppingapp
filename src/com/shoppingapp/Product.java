package com.shoppingapp;

import java.util.Objects;

public class Product {

	private String name;
	private String brand;
	private Integer priceInCent;
	private Integer salesTotalNum;
	
	private String[] randomNames = {"Xenoblade", "Green Socks", "Pesticide", "Potion of Invincibility", "Goblin Landmines", "Scroll of Mana"};
	private String[] randomBrands = {"Apple", "Amazon", "Facebook", "Google", "Robinhood", "Citadel of Adon", "Robotics Facility"};

	public Product(String name, String brand, Integer priceInCent) {
		this.name = name;
		this.brand = brand;
		this.priceInCent = priceInCent;
		this.salesTotalNum = 0;
	}

	public Product(String name, String brand, Integer priceInCent, Integer salesTotalNum) {
		this.name = name;
		this.brand = brand;
		this.priceInCent = priceInCent;
		this.salesTotalNum = salesTotalNum;
	}
	
	public Product() {
		this.name = randomNames[(int) Math.floor(Math.random()* randomNames.length)];
		this.brand = randomBrands[(int) Math.floor(Math.random()* randomBrands.length)];
		this.priceInCent = (int) Math.floor(Math.random() * 200000);
		this.salesTotalNum = (int) Math.floor(Math.random() * 20000);
	}
	
	public Product(Product product) {
		this.name = product.name;
		this.brand = product.brand;
		this.priceInCent = product.priceInCent;
		this.salesTotalNum = product.salesTotalNum;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public Integer getPriceInCent() {
		return priceInCent;
	}
	
	public Integer getSalesTotalNum() {
		return salesTotalNum;
	}
	
	public void increment(int addNum) {
		salesTotalNum += addNum;
	}
	

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(name, product.name) && Objects.equals(brand, product.brand) && Objects.equals(priceInCent, product.priceInCent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, brand, priceInCent);
	}
}
