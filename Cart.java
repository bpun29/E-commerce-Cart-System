import java.util.Scanner;

public class Cart implements UsersInterface {
	static ProductMM listy = new ProductMM();
	static MyLinkedList cart = new MyLinkedList();
	private static Scanner sc = new Scanner(System.in);
	 
	@Override
	public void viewProducts() {
		// TODO Auto-generated method stub
//		System.out.println("\n****************** WELCOME TO OUR SHOP, LISTY PINKY's SHOP ******************");
		listy.list();
		
	}
	
	@Override
	public void findProductbyID() {
		System.out.print("Product's id you want to find: ");
		if(sc.hasNextInt()) {
			int id = sc.nextInt();
			if(listy.findIndexByID(id) != -1) {
				System.out.println("ID: "+id+" -> "+listy.getNameById(id)+"\nDescription: "+listy.getDesByID(id)+
						"\nPrice: "+listy.getPriceById(id)+"\nIn-stock: "+listy.getQuantityById(id));
			}else {
				System.out.println("Not have product with this ID");
			}
		}else {
			System.out.println("Please enter an ID");
		}
		
	}
	
	@Override
	public void findProductbyName() {
		System.out.print("Product's name you want to find: ");
		if(sc.hasNext()) {
			String name = sc.nextLine();
			if(listy.findIndexByName(name) != -1) {
				System.out.println("ID: "+listy.getIdByName(name)+" -> "+name+"\nDescription: "+listy.getDesByID(listy.getIdByName(name))+
								"\nPrice: "+listy.getPriceById(listy.getIdByName(name))+"\nIn-stock: "+listy.getQuantityById(listy.getIdByName(name)));
			}else {
				System.out.println("Not have product with this name: '" +name+"'"); //if the customer enter an integer, it will show this
			}
		}else {
			System.out.println("Please enter a name");
		}
	}
	
	@Override
	public void filterPriceRange() {
		System.out.print("Enter the price range (e.g. 20-40): ");
        String input = sc.nextLine();

        // Split the input using the dash as a delimiter
        String[] range = input.split("-");

        if (range.length == 2) {
            try {
                int min = Integer.parseInt(range[0].trim());
                int max = Integer.parseInt(range[1].trim());
                int id=1, count=0;;
                while(listy.findIndexByID(id) != -1) {
                	if(listy.getPriceById(id) >= min && listy.getPriceById(id) <= max) {
                		System.out.println("\nID: "+id+" -> "+listy.getNameById(id)+"\nDescription: "+listy.getDesByID(id)+
        									"\nPrice: "+listy.getPriceById(id)+"\nIn-stock: "+listy.getQuantityById(id));
                		System.out.println(); //for separate line
                		count++;
                	}
                	id++;
                }
                if(count==0) {
                	System.out.println("No product in this price range.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer range.");
            }
        } else {
            System.out.println("Invalid input format. Please use the format 'min-max'.");
        }
	}
	
	@Override
	public void addOrChangeOrder() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the products'id you want. Type 0 to go back to home page.");
		System.out.print("ID: ");
		int id = sc.nextInt();
		
		while(listy.getNameById(id)=="error" || id==0) {
			 if(listy.getNameById(id)=="error" && id!=0) {
				 System.out.println("This id is not in our shop. Please enter the product's id you want.");
				 System.out.print("ID: ");
				 id = sc.nextInt();
			 }
			 else{
				 System.out.println("Thank you for shopping.");
				 break;
			 }
		}
		while(id!=0) {
//			 if(id==0) {
//				 break;
//			 }
			 System.out.println("\nPlease enter the quantity you want."+
					 			"\nIf you want to decrease the quantity please type in negative number.");
			 System.out.print("Quantity: ");
			 int quantity = sc.nextInt();
			 if(quantity<0) { 
				 //want to decrease the quantity -> must have this product in the cart
				 if(cart.contains(id)) { 
					 int oldQ = cart.getQuantity(id);
					 if(quantity+oldQ <= 0) {
						 System.out.println("You have decreased all of the quantity, so this product will be automatically removed.");
						 cart.remove(id);
//						 cart.display();
					 }
					 cart.updateQuantity(id, quantity+oldQ);
//					 cart.display();
				 }
			 }else {
				 if(cart.contains(id)) { //already have this product in the cart->update quantity
					 int oldQ = cart.getQuantity(id);
					 cart.updateQuantity(id, quantity+oldQ);
//					 cart.display();
				 }else {
					 if(quantity==0) {
						 System.out.println("This product will be not added to cart.");
					 }else {
						 cart.addNode(id, listy.getNameById(id), quantity);
					 }
//					 cart.display();
				 }
			 }
			 System.out.println("\nPlease enter next product's id you want. Type 0 to go back to home page.");
			 System.out.print("ID: ");
			 id = sc.nextInt();
		 }
	}

	@Override
	public void removeProduct() {
		// TODO Auto-generated method stub
		if(cart.head != null) {
			System.out.println("What is the products'id you want to remove.");
			System.out.print("ID: ");
			int id = sc.nextInt();
			if(!cart.contains(id)) {
				System.out.println("Do not have this product in your cart.\n");
			}else{
				cart.remove(id);
				System.out.println("Already remove product ID "+id+" -> "+listy.getNameById(id)+" from your cart\n");
			}
		}else {
			System.out.println("No product in your cart to be removed\n");
		}
		
//		cart.display();
	}

	@Override
	public void viewCart() {
		// TODO Auto-generated method stub
		cart.display();
		System.out.println(); // for separate line
	}
	
	@Override
	public void checkOut() {
		// TODO Auto-generated method stub
		if(cart.head!=null) {
			System.out.println("Thank you for shopping. Next step is making a payment.");
			System.out.print("APPLY DISCOUNT CODE IF NOT HAVE PRESS X: ");
			if(sc.next().equalsIgnoreCase("PINKBLOOD15")) {
				System.out.println("\n~CONGRATS~ you get 15% off");
				System.out.println("This is your invoice.");
				cart.invoice(1);
			}else {
				System.out.println("\nThis is your invoice.");
				cart.invoice(2);
			}
			cart.purchase();
			cart.emptyCart(); //cart is empty for next customer
		}else {
			System.out.println("You're cart is empty.\n");
		}
////		cart.display();
//		cart.purchase();
		//An invoice for the customer
//		System.out.println("This is your invoice.");
//		cart.invoice();
	}
	
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		listy.importProduct();
		System.out.println("THANK YOU~ GOOD BYE and HAVE A NICE DAY~");
	}
}
