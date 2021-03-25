package com.shoppingapp;

import java.util.NoSuchElementException;

public class Shop {

	/**
	 * The basic class for setting up shops.
	 */

	private String shopName;
    private String ownerName;
    private Product[] allSales;
    
    private final String[] randomShopNames = {"Goblin Market", "Bauer Hall", "Village Hall"};
    
    private final String[] randomOwners = {"Apple", "Amazon", "Facebook", "Google"};
    
    //private final Product[] randomProducts = {new Product(), new Product(), new Product()};

    public Shop(String shopName, String ownerName, Product[] allSales){

    	this.shopName = shopName;
        this.ownerName = ownerName;
        this.allSales = allSales;
    	
    }
    
    /**
     * Randomized shop generator with allOwners function
     */
    public Shop() {
    	this.shopName = randomOwners[(int) Math.floor(Math.random()* randomOwners.length)];
    	this.ownerName = randomShopNames[(int) Math.floor(Math.random()* randomShopNames.length)];
    	this.allSales = new Product[]{new Product(), new Product(), new Product()};
    }
    
    public String getShopName() {
    	return this.shopName;
    }

    
    public String getownerName() {
    	return this.ownerName;
    }
    
    public Product[] getallSales() {
    	return this.allSales;
    }
    
    /**
     * get the top sales(note that the array starts at 0 instead of 1).
     * @param number The number of top sales in the shop
     * @return an array of string if applicable, null if out of bounds
     */
    public Product[] getTopSales(int number) {
    	if(this.allSales.length <= number) {
    		throw new NoSuchElementException();
    	}
    	else {
    		if(number == this.allSales.length - 1) {
    			return this.allSales;
    		}
    		Product[] newSales = new Product[number + 1];
    		for(int i = 0; i < newSales.length; i++) {
    			newSales[i] = this.allSales[i];
    		}
    		return newSales;
    	}
    }
    
    public String toString() {
    	this.shopName = shopName;
        this.ownerName = ownerName;
        this.allSales = allSales;
    	return "|" + this.shopName + "|" + this.ownerName;
    }
    
    
}