package com.shoppingapp;

import java.util.Scanner;

public class Menu {

	private Scanner keyboardIn;

	private static final int SHOP_SIZE = 3;

	private static Shop[] shops = new Shop[SHOP_SIZE];

    public static void main(String[] args) {
    	// shop page initialization
    	randomizedEntry();
    	welcomePage();
    }
    
    /**
     * fills the shop array for randomized details
     */
    private static void randomizedEntry() {
    	for(int i = 0; i < shops.length; i++) {
    		shops[i] = new Shop();
    	}
    }
    
    /**
     * The welcome page for users.
     */
    
    private static void welcomePage() {
    	Scanner newScanner = new Scanner(System.in);  // Create a Scanner object
    	System.out.println("Welcome to the Shopping App, best Shopping App in 2021!");
    	
    	displayShop();
    	displayProduct();
    	String choice = "";
    	int value = 0;
    	boolean canary = false;
    	while(!canary) {
    		System.out.println("Please choose your target store, by typing the store number");
    		if(newScanner.hasNextLine()) {
    			choice = newScanner.nextLine();
    			try{
    				value = Integer.parseInt(choice);
    				if(value < shops.length) {
    					canary = true;
    				}else {
    					System.out.println("Invalid input");
    				}
    			}catch(Exception e){
    				System.out.println("Invalid input");
    			}
    			
    		} 
    	}
    	goToShopPage(value);
    	newScanner.close();
    }

	private static void displayShop() {
		System.out.println("The shops are as listed");
    	System.out.println("************************");
    	for(int i = 0; i < shops.length; i++) {
    		System.out.println("" + i + shops[i].toString());
    	}
	}
	
	private static void displayProduct() {
		System.out.println("Projcts are as listed");
    	System.out.println("************************");
    	System.out.println("Id|Name|Brand|Price in cent");
    	int productId = 0;
    	for(int i = 0; i < shops.length; i++) {
    		int l = shops[i].getAllSales().length;
    		for(int j=0; j<l; j++) {
        		System.out.println(productId+"|"+shops[i].getAllSales()[j].getName()+"|"+shops[i].getAllSales()[j].getBrand()+"|"+shops[i].getAllSales()[j].getPriceInCent());
        		productId++;
    		}
    	}
	}
    
    private static void goToShopPage(int value){
    		
    }
    
	private int getUserInput() {
		return keyboardIn.nextInt();
	}
}
