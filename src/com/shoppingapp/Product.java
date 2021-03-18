package com.shoppingapp;

import java.util.Objects;

public class Product {

	private String name;
	private String brand;
	private Integer priceInCent;

	public Product(String name, String brand, Integer priceInCent) {
		this.name = name;
		this.brand = brand;
		this.priceInCent = priceInCent;
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
