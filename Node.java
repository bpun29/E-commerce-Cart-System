
class Node {
    private int productId;
    private String productName;
    Node next;
    private Product product;
    private int quantity;
//    public Node head = null;
//    public Node tail = null;

    public Node(int productId) {
        this.productId = productId;
        this.next = null;
    }
    public Node(String productName) {
        this.productName = productName;
        this.next = null;
    }
    public Node(int productId, String productName, int quantity) {
    	this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.next = null;
    }
    public Node(Product product) {
        this.product = product;
        this.next = null;
    }
//    public void addNode(int productId, String productName, int quantity) {
//    	Node newNode = new Node(productId, productName, quantity);
//    	if(head == null) {
//    		head = newNode;
//    		tail = newNode;
//    	}else {
//    		tail.next = newNode;
//    		tail = newNode;
//    	}
//    }
    public int getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
    	return this.quantity;
    }
    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
