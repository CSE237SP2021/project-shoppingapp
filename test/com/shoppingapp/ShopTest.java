package com.shoppingapp;

import com.shoppingapp.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopTest {
	@Test
	public void testGetterFunction() {
		
		String shopName = "Apple Store";
		String ownerName = "Tim Cook";
		
		String productName = "MacBook Pro";
		String productBrand = "Apple Inc.";
		Integer productPriceInCent = 2000000;

		Product productUnderTest = new Product(productName, productBrand, productPriceInCent);
		
		String productName2 = "MacBook Pro Kai";
		String productBrand2 = "Apple Inc.";
		Integer productPriceInCent2 = 20000000;

		Product productUnderTestKai2 = new Product(productName2, productBrand2, productPriceInCent2);
		
		Product[] topSales = {productUnderTest, productUnderTestKai2};
		Shop shop1 = new Shop(shopName, ownerName, topSales);
		assertEquals(shop1.getOwnerName(), ownerName);
		assertEquals(shop1.getAllSales(), topSales);
	
	}
	
	@Test
	public void testGetTopSales() {

		String shopName = "Apple Store";
		String ownerName = "Tim Cook";
		
		String productName = "MacBook Pro";
		String productBrand = "Apple Inc.";
		Integer productPriceInCent = 2000000;

		Product productUnderTest = new Product(productName, productBrand, productPriceInCent);
		
		String productName2 = "MacBook Pro Kai";
		String productBrand2 = "Apple Inc.";
		Integer productPriceInCent2 = 20000000;

		Product productUnderTestKai2 = new Product(productName2, productBrand2, productPriceInCent2);
		
		Product[] topSales = {productUnderTest, productUnderTestKai2};
		
		Shop shop1 = new Shop(shopName, ownerName, topSales);
		
		try{
			shop1.getTopSales(2);
	        fail("Should have thrown an NoSuchElementException");
	    } catch(Exception e) {
	    	
	    } 
		
		try{
			Product[] productList = shop1.getTopSales(1);
	        assertEquals(productList, topSales);
	    } catch(Exception e) {
	    	fail("Should not have thrown an NoSuchElementException");
	    } 
		
	}
}
