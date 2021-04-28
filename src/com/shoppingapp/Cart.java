package com.shoppingapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {

	private final Map<Product, Integer> cartProducts;

	// use Hashmap to store cart
	public Cart() {
		this.cartProducts = new HashMap<>();
	}

	// add certain number of products to the cart
	public boolean addProduct(Product product, Integer count) {
		if (count < 1) {
			System.out.println();
			System.err.println("                    Invalid product counts when adding product to the cart. Please reenter");
			return false;
		}

		cartProducts.merge(product, count, Integer::sum);
		return true;
	}

	// remove product
	public void removeProduct(Product product) {
		cartProducts.remove(product);
	}

	// get the number of products
	public int productNums() {
		return cartProducts.size();
	}

	// get the total price(in cent) of the products in the cart
	public int totalPriceInCent() {
		int priceInCent = 0;

		for (Map.Entry<Product, Integer> productCountPair: cartProducts.entrySet()) {
			priceInCent += productCountPair.getKey().getPriceInCent() * productCountPair.getValue();
		}

		return priceInCent;
	}

	// change the number of products in the cart
	public boolean changeProductCount(Product product, Integer count) {
		boolean productNotExisted = cartProducts.get(product) == null;
		if (productNotExisted) {
			return false;
		}
		cartProducts.put(product, count);
		return true;
	}

	public int getProductCount(Product product) {
		return cartProducts.get(product);
	}

	public Set<Map.Entry<Product, Integer>> getCartProductsEntries() {
		return cartProducts.entrySet();
	}
	
	public void checkoutCartProducts() {
		for(Product oneProduct: cartProducts.keySet()) {
			//responsive update
			oneProduct.increment(cartProducts.get(oneProduct));
		}
		cartProducts.clear();
	}
}
