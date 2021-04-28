package com.shoppingapp;

import com.shoppingapp.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
	@Test
	public void testProductCreation() {
		String productName = "MacBook Pro";
		String productBrand = "Apple Inc.";
		Integer productPriceInCent = 2000000;

		Product productUnderTest = new Product(productName, productBrand, productPriceInCent);

		assertEquals(productUnderTest.getName(), productName);
		assertEquals(productUnderTest.getBrand(), productBrand);
		assertEquals(productUnderTest.getPriceInCent(), productPriceInCent);
	}
}