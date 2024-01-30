import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProductMM {
	public static MyArrayList<Product> list = new MyArrayList<>();
	
	public void importProduct() {
		 String csvFile = "/Users/bym/Desktop/project data structure/productList.csv"; 
		 try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	            String line;
	            int lineNumber = 0;
	            while ((line = br.readLine()) != null) {
	                lineNumber++;
	                if (lineNumber == 1) {
	                    // Skip the header line (line 1)
	                    continue;
	                }
	                String[] data = line.split(",");
	                if (data.length == 5) {
	                    int id = Integer.parseInt(data[0]);
	                    String name = data[1];
	                    String description = data[2];
	                    int price = Integer.parseInt(data[3]);
	                    int stock = Integer.parseInt(data[4]);

	                    this.add(new Product(id, name, description, price, stock));
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	//print all products in list
	public void list() {
		System.out.println("\n=================================OUR PRODUCTS=================================");
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).log());
		}
		System.out.println(); //for separate line
	}

	//find an index of the given id
	public int findIndexByID(int id) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getID()==id) {
				return i;
			}
		}return -1; //don't have this id
	}
	//find an index of the given name
	public int findIndexByName(String name) {
		for(int i=0;i<list.size();i++) {		
			if(list.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}return -1; //don't have this id
	}
	public String getNameById(int id) {
		for(int i=0;i<list.size();i++) {		
			if(list.get(i).getID()==id) {
				String name="";
				name=list.get(i).getName();
				return name;
			}
		}return "error"; 
	}
	public int getIdByName(String name) {
		for(int i=0;i<list.size();i++) {		
			if(list.get(i).getName().equalsIgnoreCase(name)) {
				int id=-1;
				id=list.get(i).getID();
				return id;
			}
		}return -1; 
	}
	public int getQuantityById(int id) {
		for(int i=0;i<list.size();i++) {		
			if(list.get(i).getID()==id) {
				int quantity=-1;
				quantity=list.get(i).getCount();
				return quantity;
			}
		}return -1; 
	}
	public int getQuantityByName(String name) {
		for(int i=0;i<list.size();i++) {		
			if(list.get(i).getName().equalsIgnoreCase(name)) {
				int quantity=-1;
				quantity=list.get(i).getCount();
				return quantity;
			}
		}return -1; 
	}
	public String getDesByID(int id) {
		for(int i=0;i<list.size();i++) {		
			if(list.get(i).getID()==id) {
				return list.get(i).getDes();
			}
		}return ""; 
	}
	public int getPriceById(int id) {
		for(int i=0;i<list.size();i++) {		
			if(list.get(i).getID()==id) {
				int price=-1;
				price=list.get(i).getPrice();
				return price;
			}
		}return -1; 
	}

	public void add(int id, String name, String des, int price, int count) {
		//have that product already, so just update the quantity
		if(list.get(this.findIndexByID(id)).getID()==id) {
			int a=list.get(this.findIndexByID(id)).getCount();
			list.get(this.findIndexByID(id)).setCount(count+a);
		}
		list.add(new Product(id, name, des, price, count));	
	}
	public void add(int id, int count) {
		//have that product already, so just update the quantity
		if(list.get(this.findIndexByID(id)).getID()==id) {
			int a=list.get(this.findIndexByID(id)).getCount();
			list.get(this.findIndexByID(id)).setCount(count+a);
		}
	}
	public void add(Product product) {
		list.add(product);	
	}
	public void delete(int id) {
		//if that product have nothing left
		if(list.get(this.findIndexByID(id)).getCount()==0) {
			list.remove(this.findIndexByID(id));
		}
		int a = this.findIndexByID(id);
		list.remove(a);
	}
	//delete by quantity in case of a customer buy it
	public void changeItemQuantity(int id, int quantity) {
		int a = list.get(this.findIndexByID(id)).getCount();
		if(a > 0) {
			if(quantity <= a) {
				list.get(this.findIndexByID(id)).setCount(a-quantity);
				if(a-quantity==0) {
					list.remove(this.findIndexByID(id));
				}
			}else {
				System.out.println("This product is not enough.");
			}
		}else {
			list.remove(this.findIndexByID(id));
		}
	}

	public boolean contains(Product o) {
		if(list.contains(o)) {
			return true;
		}return false;
	}
	public int getQuantity(Product o) {
		if(list.contains(o)) {
			return o.getCount();
		}return 0;
	}
	
	public void testAddAndDelete() {
		 //the original product in the shop
		 this.list();
		 //add new product to the shop
		 this.add(new Product(8, "NCT 127's 'Sticker' album ", "The third album of NCT127", 415, 127));
		 System.out.println("==============================ADD PRODUCT ID 8================================");
		 this.list();
		 //delete some product in the shop
		 this.delete(1);
		 System.out.println("============================DELETE PRODUCT ID 1===============================");
		 this.list();
	}
}
