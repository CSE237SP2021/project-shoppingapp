package com.shoppingapp;

import java.util.Map;
import java.util.Scanner;

public class Menu {
	private static final int SHOP_SIZE = 3;
	// Hardcoded for correctness purposes; similar to CHAR(24) and CHAR(15) in MySQL
	private static final int PRODUCT_NAME_LENGTH = 24;
	private static final int SHOP_NAME_LENGTH = 15;
	private static final int PRICE_LENGTH = 13;

	private static Shop[] shops = new Shop[SHOP_SIZE];
	private static Cart cart = new Cart();

	private static Scanner scanner;

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
		scanner = new Scanner(System.in); // Create a Scanner object
		// 1. Search items 2. View items 3. View cart 0. Quit
		makeMenuChoice();
		scanner.close();
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
			value = getValidUserInput(scanner, 4);
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
		displayShops();
		int shopNo = getValidUserInput(scanner, shops.length);
		displayItems(shopNo);
		System.out.println("Please enter the ID of the element to add to cart. \"0\" for exit");
		int productNo = getValidUserInput(scanner, shops[shopNo].getAllSales().length + 1);
		if (productNo > 0) {
			Product productToBeAdd = shops[shopNo].getAllSales()[productNo - 1];
			System.out.print("Please enter the number of the item you would like to buy: ");
			int numberOfTheItems = getUserInput();
			cart.addProduct(productToBeAdd, numberOfTheItems);
		}
	}

	/**
	 * TODO: Fill in the searchForItem functionality
	 */
	private static void searchForItem() {
		System.out.println("************************");
		System.out.println("UNDER CONSTRUCTION");
		System.out.println("This page is still under construction. Expect for Iteration 2!");
	}

	private static void viewCart() {
		System.out.println("************************");
			System.out.println("The items in your cart");
		for (Map.Entry<Product, Integer> productCountPair: cart.getCartProductsEntries()) {
			System.out.println("Name                    |Price in cent|Count");
			StringBuilder productName = generatePaddings(productCountPair.getKey().getName(), PRODUCT_NAME_LENGTH);
			StringBuilder price = generatePaddings("" + productCountPair.getKey().getPriceInCent(), PRICE_LENGTH);
			System.out.println(productName + "|" + price + productCountPair.getValue());
		}
		System.out.println();
		System.out.println("Total price in cent: " + cart.totalPriceInCent());
	}

	/**
	 * 
	 * @param newScanner the scanner for parsing the element, passed as a reference
	 *                   for better performance
	 * @param limit      the number of valid options, for fast checking
	 * @return value the value extracted
	 */
	private static int getValidUserInput(Scanner newScanner, int limit) {
		String choice;
		int value = 0;
		boolean successful = false;
		while (!successful) {
			System.out.print("Please enter your choice: ");
			if (newScanner.hasNextLine()) {
				choice = newScanner.nextLine();
				try {
					value = Integer.parseInt(choice);
					if (value < limit) {
						successful = true;
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

	private static void displayShops() {
		System.out.println("************************");
		System.out.println("Shops are as listed");
		for (int i = 0; i < shops.length; i++) {
			System.out.println("" + i + shops[i].toString());
		}
	}

	private static void displayItems(int shopNo) {
		System.out.println("************************");
		System.out.println("Products are as listed");
		System.out.println("Id|Name                    |Brand          |Price in cent");
		int productId = 1;
		for (Product product : shops[shopNo].getAllSales()) {
			printProduct(productId, product);
			productId++;
		}
	}
	
	/**
	 * prints the product in sorted order by their ids
	 * @param productId product id
	 * @param product the product object to be displayed
	 */
	private static void printProduct(int productId, Product product) {
		StringBuilder fixedProductName = generatePaddings(product.getName(), PRODUCT_NAME_LENGTH);
		StringBuilder fixedBrandName = generatePaddings(product.getBrand(), SHOP_NAME_LENGTH);

		System.out.println(" " + productId + "|" + fixedProductName + "|"
				+ fixedBrandName + "|" + product.getPriceInCent());
	}

	private static StringBuilder generatePaddings(String product, int size) {
		StringBuilder fixedName = new StringBuilder(product);
		int fixedNameLength = fixedName.length();
		if(fixedName.length() < size) {
			fixedName.append(" ".repeat(Math.max(0, size - fixedNameLength)));
		}
		return fixedName;
	}

	private static int getUserInput() {
		return scanner.nextInt();
	}
}
