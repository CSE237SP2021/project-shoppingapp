package com.shoppingapp;

import java.util.Objects;

public class Product {

	private String name;
	private String brand;
	private Integer priceInCent;
	
	private String[] randomNames = {"Xenoblade", "Green Socks", "Pesticide", "Potion of Invincibility", "Goblin Landmines"};
	private String[] randomBrands = {"Apple", "Amazon", "Facebook", "Google"};

	public Product(String name, String brand, Integer priceInCent) {
		this.name = name;
		this.brand = brand;
		this.priceInCent = priceInCent;
	}
	
	public Product() {
		this.name = randomNames[(int) Math.floor(Math.random()* randomNames.length)];
		this.brand = randomBrands[(int) Math.floor(Math.random()* randomBrands.length)];
		this.priceInCent = (int) Math.floor(Math.random() * 200000);
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
