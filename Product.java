
public class Product {
	
	private int id; //every product has its own unique id
	private String name;
	private String des;
	private int price;
	private int count;
	
	public Product() {
	}
	public Product(int id, String name, String des, int price, int count) {
		this.id=id;
		this.name=name;
		this.des=des;
		this.price=price;
		this.count=count;
	}
	public int getID() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getDes() {
		return this.des;
	}
	public int getPrice() {
		return this.price;
	}
	public int getCount() {
		return this.count;
	}
	public void setID(int a) {
		this.id=a;
	}
	public void setName(String a) {
		this.name=a;
	}
	public void setDes(String a) {
		this.des=a;
	}
	public void setPrice(int a) {
		this.price=a;
	}
	public void setCount(int a) {
		this.count=a;
	}
	public String log() {
		return "ID: "+this.id+" -> "+this.name+"\nDescription: "+this.des+"\nPrice: "+this.price+"\nIn-stock: "+this.count+
				"\n-----------------------------------------------------------------------------";
	}
}
