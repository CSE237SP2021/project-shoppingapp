package com.shoppingapp;

import java.util.NoSuchElementException;

public class Shop {

	/**
	 * The basic class for setting up shops.
	 */

    private String ownerName;
    private Product[] allSales;

    public Shop(String ownerName, Product[] allSales){

        this.ownerName = ownerName;
        this.allSales = allSales;
    	
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
    
    
}