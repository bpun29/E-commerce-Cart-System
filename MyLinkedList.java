import java.io.BufferedWriter;
import java.io.FileWriter;

public class MyLinkedList {
	private ProductMM productList;
	private int totalprice=0;
	public Node head = null;
    public Node tail = null;

	public MyLinkedList() {
		productList = new ProductMM();
	}
	public void addNode(int productId, String productName, int quantity) {
		if(productList.getQuantityById(productId) < quantity) {
			Node newNode = new Node(productId, productName, productList.getQuantityById(productId));
			System.out.println("The quantity you want is exceed the amount that we have."+
					" The quantity you will get is "+ productList.getQuantityById(productId));
			if(head == null) {
	    		head = newNode;
	    		tail = newNode;
	    	}else {
	    		tail.next = newNode;
	    		tail = newNode;
	    	}
		}else {
			Node newNode = new Node(productId, productName, quantity);
			if(head == null) {
	    		head = newNode;
	    		tail = newNode;
	    	}else {
	    		tail.next = newNode;
	    		tail = newNode;
	    	}
		}
    }
//	public void updateQuantity(int productId, int quantity) {
//		Node current = head;
//        while (current != null) {
//            if (current.getProductId() == productId) {
//                current.setQuantity(quantity);
//                return; // Exit the method after updating the quantity
//            }
//            current = current.getNext();
//        }
//    }
	public void updateQuantity(int productId, int quantity) {
		Node current = head;
        while (current != null) {
            if (current.getProductId() == productId) {
            	if(productList.getQuantityById(productId) >= quantity) {
            		System.out.println("Update quantity for "+current.getProductName()+" is: "+quantity);
            		current.setQuantity(quantity);
            		return; //exit after update
            	}else {
            		System.out.println("The quantity you want is exceed the amount that we have."+
            							" The quantity you will get is "+ productList.getQuantityById(productId));
            		current.setQuantity(productList.getQuantityById(productId));
            		return; //exit after update
            	}
            }
            current = current.getNext();
        }
    }
    public void remove(int productId) {
        Node current = head;
        Node prev = null;

        while (current != null) {
            if (current.getProductId() == productId) {
                if (prev == null) {
                    head = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                return;
            }
            prev = current;
            current = current.getNext();
        }
    }
    //after the customer purchase, the cart is empty for next customer
    public void emptyCart() {
       head=null;
    }
    public boolean contains(int productId) {
        Node current = head;
        while (current != null) {
            if (current.getProductId() == productId) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public boolean contains(String productName) {
        Node current = head;
        while (current != null) {
            if (current.getProductName() == productName) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public boolean contains(Product product) {
        Node current = head;
        while (current != null) {
            if (current.getProduct().equals(product)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public int getQuantity(int productId) {
        Node current = head;
        while (current != null) {
            if (current.getProductId() == productId) {
                return current.getQuantity();
            }
            current = current.getNext();
        }
        return -1; // if the product is not found in the cart
    }
    public int display() {
        Node current = head;
        int tt=0;
        totalprice=0;
        if(head == null) {
        	System.out.println("Your cart is empty.");
        	return -1;
        }else {
        	System.out.println("\n------------------------------Your Shopping Cart------------------------------");
        }
        while (current != null) {
//        	if(current.getQuantity()==0) {
//            	return -1;
//            }
            System.out.println("ID: " + current.getProductId() + 
            					", Product Name: " + current.getProductName() + 
            					"\nQuantity: " + current.getQuantity() +
            					", Price: " + productList.getPriceById(current.getProductId()) +
            					"--> " + current.getQuantity()*productList.getPriceById(current.getProductId()) +
            					" baht.");
            System.out.println(); //for separate line
            tt+=current.getQuantity();
            totalprice += productList.getPriceById(current.getProductId())*current.getQuantity();
            current = current.getNext();
        }
        if(totalprice!=0) {
        	System.out.println("Total items: " + tt + " items.");
        	System.out.println("Total Price: " + totalprice + " baht.");
        	System.out.println(); //for separate line
        	return 1;
        }else{
        	return -1;
        }
//        else {
//        	System.out.println("Nothing in your cart.");
//        }
    }
    public void purchase() {
//    	System.out.println("\n******Thank you for shopping. Have a nice day~******");
    	Node current = head;
    	while (current != null) {
    		productList.changeItemQuantity(current.getProductId(), current.getQuantity());
    		current = current.getNext();
    	}
    }
    public void invoice(int i) {
//    	String data = "INVOICE";
    	Node current = head;
    	double ttp=0;
		int tti=0;
    		System.out.println("================================== Invoice ===================================");
            System.out.println("|ID       Name                           Price    Quantity  Total(baht)      |");
            System.out.println("|----------------------------------------------------------------------------|");
        while (current != null) {
            System.out.printf(" %-8d%-33s%-9d%-9d%d%n",
			                    current.getProductId(),
			                    current.getProductName(),
//			                    productList.getDesByID(current.getProductId()),
			                    productList.getPriceById(current.getProductId()),
			                    current.getQuantity(),
			                    productList.getPriceById(current.getProductId())*current.getQuantity());
            
            ttp+=productList.getPriceById(current.getProductId())*current.getQuantity();
    		tti+=current.getQuantity();
            current = current.getNext();
    	}
        if(i==1) {
        	ttp=0.9*ttp;
        }
        System.out.println("\n Total Item: "+tti+" items."+"\n Total Price: "+ttp+" baht.");
        System.out.println("|----------------------------------------------------------------------------|");
    	System.out.println("*******************Thank you for shopping. Have a nice day~******************\n");
    	
//    	while (current != null) {
//		data+="\nProductID: "+current.getProductId()+" Name: "+current.getProductName()+
//				"\nDetails: "+productList.getDesByID(current.getProductId())+
//				"\nQuantity: "+current.getQuantity();
//		ttp+=productList.getPriceById(current.getProductId())*current.getQuantity();
//		tti+=current.getQuantity();
//	}
//	data+="\nTotal Item: "+tti+" items."+"\nTotal Price: "+ttp+" baht.";
//    	try {
//		    FileWriter file = new FileWriter("output.txt");
//		    BufferedWriter output = new BufferedWriter(file);
//		    output.write(data);
//		    output.close();
//		 }
//		 catch (Exception e) {
//			 e.getStackTrace();
//		 }
    }
}
