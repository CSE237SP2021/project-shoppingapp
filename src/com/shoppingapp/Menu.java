package com.shoppingapp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Menu {
	private static final int SHOP_SIZE = 3;
	// Hardcoded for correctness purposes; similar to CHAR(24) and CHAR(15) in MySQL
	private static final int PRODUCT_NAME_LENGTH = 24;
	private static final int SHOP_NAME_LENGTH = 17;
	private static final int PRICE_LENGTH = 9;
	private static final int TOP_SALES_LENGTH = 11;

	private static final Shop[] shops = new Shop[SHOP_SIZE];
	private static final Cart cart = new Cart();

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
		System.out.println();
		System.out.println("                    Welcome to the Shopping App, best Shopping App in 2021!");
		System.out.println();
		scanner = new Scanner(System.in); // Create a Scanner object
		// 1. Search items 2. View items 3. View cart 4. Checkout Cart 0. Quit
		makeMenuChoice();
		scanner.close();
	}

	/**
	 * Provides the users a menu for recommendation.
	 */
	private static void makeMenuChoice() {
		int value = Integer.MAX_VALUE;
		while (value != 0) {
			System.out.println("******************************************************************************************");
			System.out.println();
			System.out.println("                    Please make your choice, by entering the assigned number");
			System.out.println();
			System.out.println("                    1: Search for a specific item");
			System.out.println("                    2: View shops");
			System.out.println("                    3: View cart");
			System.out.println("                    4: Checkout cart");
			System.out.println("                    0: Quit");
			value = getValidUserInput(scanner, 5);
			switch (value) {
			case 0: 
				System.out.println();
				System.out.println("                    Bye!");
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
			case 4:
				checkoutCart();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Option 1: search for a specific item
	 * searches an element by its name.
	 */
	private static void searchForItem() {
		System.out.println("******************************************************************************************");
		System.out.println("                    Please type your search queries");
		System.out.println();
		String choice = "";
		if (scanner.hasNextLine()) {
			choice = scanner.nextLine();
		}
		//Currently only supports filtering by name
		System.out.println();
		System.out.println("                    All products that matches your search are as listed");
		System.out.println();
		System.out.println("                    Id|Name                    |Brand            |Price |Total Sales");
		int productId = 0;
		for (Shop shop : shops) {
			int l = shop.getAllSales().length;
			for (int j = 0; j < l; j++) {
				if (shop.getAllSales()[j].getName().contains(choice)) {
					printProduct(productId, shop.getAllSales()[j]);
				}
				productId++;
			}
		}
	}
	
	// option 2 view items
	private static void viewItems() {
		displayShops();
		int shopNo = getValidUserInput(scanner, shops.length);
		displayItems(shopNo);
		System.out.println();
		System.out.println("                    Please enter the ID of the element to add to cart. \"0\" for exit");
		System.out.println();
		int productNo = getValidUserInput(scanner, shops[shopNo].getAllSales().length + 1);
		if (productNo > 0) {
			Product productToBeAdd = shops[shopNo].getAllSales()[productNo - 1];
			System.out.println();
			System.out.print("                    Please enter the number of the item you would like to buy: ");
			int numberOfTheItems = getUserIntInput();
			cart.addProduct(productToBeAdd, numberOfTheItems);
		}
		System.out.println();
		System.out.println("                    Purchase done, going back to the menu.");
		System.out.println();
	}

	// option 2 view cart
	private static void viewCart() {
		System.out.println("******************************************************************************************");
		System.out.println();
		System.out.println("                    The items in your cart");
		System.out.println();
		System.out.println("                    Name                    |Price    |Count");
		for (Map.Entry<Product, Integer> productCountPair: cart.getCartProductsEntries()) {
			StringBuilder productName = generatePaddings(productCountPair.getKey().getName(), PRODUCT_NAME_LENGTH);
			StringBuilder price = generatePaddings(convertCentToDollar(productCountPair.getKey().getPriceInCent()), PRICE_LENGTH);
			System.out.println("                    " + productName + "|" + price + "|" + productCountPair.getValue());
		}
		System.out.println();
		System.out.println("                    Total price: " + convertCentToDollar(cart.totalPriceInCent()));
		System.out.println();
		displayCartMenu();
	}
	
	// display cart menu options
	private static void displayCartMenu() {
		System.out.println();
		System.out.println("                    Please make your choice, by entering the assigned number");
		System.out.println();
		System.out.println("                    1: Remove an item from the cart");
		System.out.println("                    2: Modify the number of a certain item in the cart");
		System.out.println("                    3: Checkout cart");
		System.out.println("                    0: Quit to main manu");
		int value = getValidUserInput(scanner, 4);
		switch (value) {
		case 0:
			System.out.println();
			System.out.println("                    Going back to main menu");
			System.out.println();
			break;
		case 1:
			removeItemFromCart();
			break;
		case 2:
			modifyItemNumberInCart();
			break; 
		case 3:
			checkoutCart();
			break;
		default:
			break;
		}	
	}

	// modify number of items in cart
	private static void modifyItemNumberInCart() {
		String itemName = getValidItemNameInput(scanner);
		System.out.println();
		System.out.print("                    Please enter the number of the item you would like to change to: ");
		int numberOfTheItems = getUserIntInput();
		for (Iterator<Map.Entry<Product, Integer>> it = cart.getCartProductsEntries().iterator(); it.hasNext();){
		    Map.Entry<Product, Integer> item = it.next();
		    if( item.getKey().getName().equals(itemName) ) {
				if (numberOfTheItems == 0) {
					it.remove();
				}
				else {
					cart.changeProductCount(item.getKey(), numberOfTheItems);
				}
		    }
		}
		System.out.println();
		System.out.println("                    Item number modified successfully");
		System.out.println();
	}
	
	// remove item from cart
	private static void removeItemFromCart() {
		String itemName = getValidItemNameInput(scanner);
		for (Iterator<Map.Entry<Product, Integer>> it = cart.getCartProductsEntries().iterator(); it.hasNext();){
		    Map.Entry<Product, Integer> item = it.next();
		    if( item.getKey().getName().equals(itemName) ) {
			    it.remove();
		    }
		}
		System.out.println();
		System.out.println("                    Item removed from the cart successfully");
		System.out.println();
	}
	
	// checkout cart will clear all the items in the cart.
	private static void checkoutCart() {
		int totalPrice = cart.totalPriceInCent();
		cart.checkoutCartProducts();
		System.out.println("******************************************************************************************");
		System.out.println();
		System.out.println("                    The items in your cart is all cleared");
		System.out.println();
		System.out.println("                    Total price to pay: " + convertCentToDollar(totalPrice));
		System.out.println();
	}
	
	// check item name
	private static String getValidItemNameInput(Scanner newScanner) {
		String choice;
		String value = "";
		boolean successful = false;
		while (!successful) {
			System.out.println();
			System.out.println("                    Please enter the name of the item in the cart:");
			if (newScanner.hasNextLine()) {
				choice = newScanner.nextLine();
				for (Map.Entry<Product, Integer> productCountPair: cart.getCartProductsEntries()) {
					String itemName = productCountPair.getKey().getName().toString().trim();
					if(itemName.equals(choice)) {
						value = choice;
						successful = true;
					}
				}
			}
			if(!successful) {
				System.out.println();
				System.out.println("                    Item name doesn't exit in the cart. Please reenter");
			}
		}
		return value;
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
			System.out.println();
			System.out.print("                    Please enter your choice: ");
			if (newScanner.hasNextLine()) {
				choice = newScanner.nextLine();
				try {
					value = Integer.parseInt(choice);
					if (value < limit) {
						successful = true;
					} else {
						System.out.println();
						System.out.println("                    Invalid input: No valid options available! Please reenter");
					}
				} catch (Exception e) {
					System.out.println();
					System.out.println("                    Invalid input: This is not a value! Please reenter");
				}
			}
		}
		return value;
	}

	private static void displayShops() {
		System.out.println("******************************************************************************************");
		System.out.println();
		System.out.println("                    Shops are as listed");
		System.out.println();
		for (int i = 0; i < shops.length; i++) {
			System.out.println("                    " + i + shops[i].toString());
		}
	}

	private static void displayItems(int shopNo) {
		Map<Product,Integer> hashMap = new HashMap<>();
		System.out.println("******************************************************************************************");
		System.out.println();
		System.out.println("                    Products are as listed");
		System.out.println();
		System.out.println("                    Id|Name                    |Brand            |Price    |Total Sales");
		int productId = 1;
		for (Product product : shops[shopNo].getAllSales()) {
			hashMap.put(product, productId);
			printProduct(productId, product);
			productId++;
		}
		System.out.println("******************************************************************************************");
		System.out.println();
		System.out.println("                    Top sales for this shop are as listed");
		System.out.println();
		System.out.println("                    Id|Name                    |Brand            |Price    |Total Sales");
		for (Product product : shops[shopNo].getTopSales()) {
			printProduct(hashMap.get(product), product);
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
		StringBuilder fixedPriceName = generatePaddings(convertCentToDollar(product.getPriceInCent()), PRICE_LENGTH);
		StringBuilder fixedTopSalesNum = generatePaddings(product.getSalesTotalNum().toString(), TOP_SALES_LENGTH);
		
		if(productId < 10) {
			System.out.println("                     " + productId + "|" + fixedProductName + "|"
				+ fixedBrandName + "|" + fixedPriceName + "|" + fixedTopSalesNum);
		}else {
			System.out.println("                    " + productId + "|" + fixedProductName + "|"
					+ fixedBrandName + "|" + fixedPriceName + "|" + fixedTopSalesNum);
		}
		
	}

	/**
	 * add paddings to the string
	 * @param name the product name we have to add paddings on
	 * @param expectedLength the expected length of the function
	 * @return fixedProductName the padded name
	 */
	private static StringBuilder generatePaddings(String name, int expectedLength) {
		StringBuilder fixedName = new StringBuilder(name);
		int fixedNameLength = fixedName.length();
		if(fixedName.length() < expectedLength) {
			for (int i = 0; i < expectedLength - fixedNameLength; i++) {
				fixedName.append(" ");
			}
		}
		return fixedName;
	}

	private static int getUserIntInput() {
		int number = scanner.nextInt();
		while(number < 0) {
			scanner.nextLine();
			System.out.println();
			System.out.println("                    Please enter a non-nagetive number:");
			number = scanner.nextInt();
		}
		scanner.nextLine();
		return number;
	}

	private static String convertCentToDollar(int priceInCent) {
		return "$" + priceInCent / 100 + "." + priceInCent % 100;
	}
}
