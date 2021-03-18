package com.shoppingapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
	@Test
	public void testAddProduct() {
		Product productUnderTest1 = new Product("Product Name A", "Product Brand", 200);
		Product productUnderTest2 = new Product("Product Name B", "Product Brand", 400);
		Product productUnderTest3 = new Product("Product Name A", "Product Brand", 200);
		Cart cartUnderTest = new Cart();

		assertEquals(0, cartUnderTest.productNums());

		boolean testResult = cartUnderTest.addProduct(productUnderTest1, 2);
		assertTrue(testResult);
		assertEquals(1, cartUnderTest.productNums());
		assertEquals(2, cartUnderTest.getProductCount(productUnderTest1));

		testResult = cartUnderTest.addProduct(productUnderTest2, 0);
		assertFalse(testResult);
		assertEquals(1, cartUnderTest.productNums());

		testResult = cartUnderTest.addProduct(productUnderTest2, 1);
		assertTrue(testResult);
		assertEquals(2, cartUnderTest.productNums());
		assertEquals(1, cartUnderTest.getProductCount(productUnderTest2));

		// The productUnderTest3 should be considered as the same product as productUnderTest1
		testResult = cartUnderTest.addProduct(productUnderTest3, 1);
		assertTrue(testResult);
		assertEquals(2, cartUnderTest.productNums());
		assertEquals(3, cartUnderTest.getProductCount(productUnderTest3));
	}

	@Test
	public void testTotalPriceInCent() {
		Product productUnderTest1 = new Product("Product Name A", "Product Brand", 200);
		Product productUnderTest2 = new Product("Product Name B", "Product Brand", 400);
		Product productUnderTest3 = new Product("Product Name A", "Product Brand", 200);
		Cart cartUnderTest = new Cart();

		assertEquals(0, cartUnderTest.totalPriceInCent());

		cartUnderTest.addProduct(productUnderTest1, 100);
		assertEquals(200 * 100, cartUnderTest.totalPriceInCent());

		cartUnderTest.addProduct(productUnderTest2, 50);
		assertEquals(200 * 100 + 400 * 50, cartUnderTest.totalPriceInCent());

		cartUnderTest.addProduct(productUnderTest3, 50);
		assertEquals(200 * 100 + 400 * 50 + 200 * 50, cartUnderTest.totalPriceInCent());
	}

	@Test
	public void testRemoveProduct() {
		Product productUnderTest1 = new Product("Product Name A", "Product Brand", 200);
		Cart cartUnderTest = new Cart();

		assertEquals(0, cartUnderTest.productNums());

		cartUnderTest.removeProduct(productUnderTest1);
		assertEquals(0, cartUnderTest.productNums());

		cartUnderTest.addProduct(productUnderTest1, 1);
		assertEquals(1, cartUnderTest.productNums());

		cartUnderTest.removeProduct(productUnderTest1);
		assertEquals(0, cartUnderTest.productNums());
	}

	@Test
	public void testChangeProductCount() {
		Product productUnderTest1 = new Product("Product Name A", "Product Brand", 200);
		Product productUnderTest2 = new Product("Product Name B", "Product Brand", 400);
		Cart cartUnderTest = new Cart();

		boolean testResult = cartUnderTest.addProduct(productUnderTest1, 1);
		assertEquals(1, cartUnderTest.getProductCount(productUnderTest1));
		assertTrue(testResult);

		testResult = cartUnderTest.changeProductCount(productUnderTest1, 5);
		assertEquals(5, cartUnderTest.getProductCount(productUnderTest1));
		assertTrue(testResult);

		testResult = cartUnderTest.changeProductCount(productUnderTest2, 5);
		assertFalse(testResult);
	}
}