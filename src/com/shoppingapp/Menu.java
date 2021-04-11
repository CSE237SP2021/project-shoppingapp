package com.shoppingapp;

import java.util.Scanner;

public class Menu {

	private Scanner keyboardIn;

	private static final int SHOP_SIZE = 3;

	private static Shop[] shops = new Shop[SHOP_SIZE];

	private static Scanner newScanner;

	public static void main(String[] args) {
		// shop page initialization
		randomizedEntry();
		welcomePage();
	}

	/**
	 * fills the shop array for randomized details
	 */
	private static void randomizedEntry() {
		for (int i = 0; i < shops.length; i++) {
			shops[i] = new Shop();
		}
	}

	/**
	 * The welcome page for users.
	 */

	private static void welcomePage() {
		System.out.println("Welcome to the Shopping App, best Shopping App in 2021!");
		newScanner = new Scanner(System.in); // Create a Scanner object
		// 1. Search items 2. View items 3. View cart 0. Quit
		makeMenuChoice();
		newScanner.close();
	}

	/**
	 * Provides the users a menu for recommendation.
	 */
	private static void makeMenuChoice() {
		int value = Integer.MAX_VALUE;
		while (value != 0) {
			System.out.println("************************");
			System.out.println("Please make your choice, by entering the assigned number");
			System.out.println("1: Search for a specific item");
			System.out.println("2: View items");
			System.out.println("3: View cart");
			System.out.println("0: Quit");
			value = getValidOutput(newScanner, 4);
			switch (value) {
			case 0:
				System.out.println("Bye!");
				break;
			case 1:
				searchForItem();
				break;
			case 2:
				viewItems();
				break;
			case 3:
				viewCart();
				break;
			default:
				break;

			}
		}
	}
	
	private static void viewItems() {
		displayShop();
		displayProduct();
		int value = getValidOutput(newScanner, shops.length);
		goToShopPage(value);
	}

	/**
	 * searches an element by its name.
	 */
	private static void searchForItem() {
		System.out.println("************************");
		System.out.println("Please type your search queries");
		String choice = "";
		if (newScanner.hasNextLine()) {
			choice = newScanner.nextLine();
		}
		//Currently only supports filtering by name
		System.out.println("All products that matches your search are as listed");
		System.out.println("Id|Name                    |Brand          |Price in cent");
		int productId = 0;
		for (int i = 0; i < shops.length; i++) {
			int l = shops[i].getAllSales().length;
			for (int j = 0; j < l; j++) {
				if(shops[i].getAllSales()[j].getName().indexOf(choice) >= 0) {
				printProduct(productId, i, j); 
				}
				productId++;
			}
		}
	}

	private static void viewCart() {
		System.out.println("************************");
		System.out.println("UNDER CONSTRUCTION");
		System.out.println("This page is still under construction. Expect for Iteration 2!");
	}

	/**
	 * 
	 * @param newScanner the scanner for parsing the element, passed as a reference
	 *                   for better performance
	 * @param limit      the number of valid options, for fast checking
	 * @return value the value extracted
	 */
	private static int getValidOutput(Scanner newScanner, int limit) {
		String choice = "";
		int value = 0;
		boolean canary = false;
		while (!canary) {
			System.out.println("Please choose your target store, by typing the store number");
			if (newScanner.hasNextLine()) {
				choice = newScanner.nextLine();
				try {
					value = Integer.parseInt(choice);
					if (value < limit) {
						canary = true;
					} else {
						System.out.println("Invalid input: No valid options available!");
					}
				} catch (Exception e) {
					System.out.println("Invalid input: This is not a value!");
				}

			}
		}
		return value;
	}

	private static void displayShop() {
		System.out.println("************************");
		System.out.println("The shops are as listed");
		for (int i = 0; i < shops.length; i++) {
			System.out.println("" + i + shops[i].toString());
		}
	}

	private static void displayProduct() {
		System.out.println("************************");
		System.out.println("Products are as listed");
		System.out.println("Id|Name                    |Brand          |Price in cent");
		int productId = 0;
		for (int i = 0; i < shops.length; i++) {
			int l = shops[i].getAllSales().length;
			for (int j = 0; j < l; j++) {
				printProduct(productId, i, j); 
				productId++;
			}
		}
	}
	
	/**
	 * add paddings to the string
	 * @param fixedProductName the product name we have to add paddings on
	 * @param expectedLength the expected length of the function
	 * @return fixedProductName the padded name
	 */
	private static String printProductHelper(String fixedProductName, int expectedLength) {
		//generate padding for each product name
		int fixedProductNameLength = fixedProductName.length();
		if(fixedProductName.length() < expectedLength) {
    		for(int k = 0; k < expectedLength - fixedProductNameLength; k++) {
    			fixedProductName += " ";
    		}
    	}
		return fixedProductName;
	}
	
	/**
	 * prints the product in sorted order by their ids
	 * @param productId product id
	 * @param i the ith shop in the list
	 * @param j the jth product in the shop i
	 */
	private static void printProduct(int productId, int i, int j) {
		//Hardcoded for correctness purposes; similar to CHAR(24) and CHAR(15) in MySQL
		final int fixedProductSize = 24;
		final int fixedShopSize = 15;
		//generate padding for each product name
		String fixedProductName = shops[i].getAllSales()[j].getName();
		fixedProductName = printProductHelper(fixedProductName, fixedProductSize);
		
		//generate padding for each brand name
		String fixedBrandName = shops[i].getAllSales()[j].getBrand();
		fixedBrandName = printProductHelper(fixedBrandName, fixedShopSize);
		
		System.out.println(" " + productId + "|" + fixedProductName + "|"
				+ fixedBrandName + "|" + shops[i].getAllSales()[j].getPriceInCent());
	}

	private static void goToShopPage(int value) {
		System.out.println("************************");
		System.out.println("UNDER CONSTRUCTION");
		System.out.println("This page is still under construction. Expect for Iteration 2!");
	}

	private int getUserInput() {
		return keyboardIn.nextInt();
	}
}
