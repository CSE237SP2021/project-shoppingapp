package com.shoppingapp;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private final Map<Product, Integer> products;

	public Cart() {
		this.products = new HashMap<>();
	}

	public boolean addProduct(Product product, Integer count) {
		if (count < 1) {
			System.err.println("Invalid product counts when adding product to the cart");
			return false;
		}

		products.merge(product, count, Integer::sum);
		return true;
	}

	public void removeProduct(Product product) {
		products.remove(product);
	}

	public int productNums() {
		return products.size();
	}

	public int totalPriceInCent() {
		int priceInCent = 0;

		for (Map.Entry<Product, Integer> productCountPair:
		     products.entrySet()) {
			priceInCent += productCountPair.getKey().getPriceInCent() * productCountPair.getValue();
		}

		return priceInCent;
	}

	public boolean changeProductCount(Product product, Integer count) {
		boolean productNotExisted = products.get(product) == null;
		if (productNotExisted) {
			return false;
		}
		products.put(product, count);
		return true;
	}

	public Integer getProductCount(Product product) {
		return products.get(product);
	}
}
