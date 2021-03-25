package com.shoppingapp;

import java.util.Scanner;

public class Menu {
	
	private static final int SHOP_SIZE = 3;
	
	private static Shop[] shops = new Shop[SHOP_SIZE];

    public static void main(String[] args) {
    // write your code here
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
    	
    	System.out.println("The shops are as listed");
    	System.out.println("************************");
    	for(int i = 0; i < shops.length; i++) {
    		System.out.println("" + i + shops[i].toString());
    	}
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
    
    	private static void goToShopPage(int value){
    		
    	}
}
