import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	 static Cart cart = new Cart();
	 private static Scanner sc = new Scanner(System.in);
	 
	 public static void main(String[] args) {
		 ProductMM listy = new ProductMM();
		 
		 listy.importProduct();
		 
//		 @@@ UNCOMMENT TO TEST 
//		 add new product and delete some product test
//		listy.testAddAndDelete();
		 
		 while (true) {
			 System.out.println("================== WELCOME TO OUR SHOP, LISTY PINKY's SHOP ==================");
			 System.out.println("|              GET SPECIAL DISCOUNT BY THIS CODE: 'PINKBLOOD15'             |");
			 System.out.println("---------------------------SELECT YOUR CHOICE--------------------------------");
	         System.out.println("1 -> View Our Available Products");
	         System.out.println("2 -> Find Product By ID");
	         System.out.println("3 -> Find Product By Name");
	         System.out.println("4 -> Filter By Price Range");
	         System.out.println("5 -> Add Or Change Your Order");
	         System.out.println("6 -> Remove Your Order");
	         System.out.println("7 -> View Your Cart");
	         System.out.println("8 -> Make A payment");
	         System.out.println("9 -> Restart The Program");
	         System.out.println("-----------------------------------------------------------------------------");
	         System.out.print("Enter your choice: ");
	         
	         int choice = sc.nextInt();
	         switch(choice) {
	         case 1:
	        	 cart.viewProducts();
	        	 break;
	         case 2:
	        	 cart.findProductbyID();
	        	 break;
	         case 3:
	        	 cart.findProductbyName();
	        	 break;
	         case 4:
	        	 cart.filterPriceRange();
	        	 break;
	         case 5:
	        	 cart.addOrChangeOrder();
	        	 break;
	         case 6:
	        	 cart.removeProduct();
	        	 break;
	         case 7:
	        	 cart.viewCart();
	        	 break;
	         case 8:
	        	 cart.checkOut();
	        	 break;
	         case 9:
	        	 cart.exit();
	        	 break;
	        default:
	            System.out.println("Invalid choice. Please enter a valid option.");
	        		 
	         }
		 }
	 }
}
