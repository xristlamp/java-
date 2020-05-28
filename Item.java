package perikor;
/////////////oxi stigmiotipa
public abstract class Item {
	
	//variables
	private String name;
	private double price;
	private String description;
	private int stock;
	private  int id;
	private static int counter=0;
	
	//methods
	public double getprice() {
		return price;
	}
	public String getname() {
		return name;
	}
	public void setid(int id) {
		this.id = id;
	}
	public int getstock() {
		return stock;
	}
	public int getid() {
		return id;
	}
	public String getBasicInfo()
	{
		return("Item name:"+name+"\nItem price:"+price+"\nDescription: "+ description+"\nStock:"+stock+"\nID:"+id);
	}
	public abstract String getDetails();
	
	public String toString() {
		return (getDetails()+"\n"+getBasicInfo());
	}
	//seters
	public void setprice(double price)
	{
		this.price=price;
	}
	public void setstock(int stock)
	{
		this.stock=stock;
	}
	
	//constractor
	public Item(String name, double price,String desc,int stock ) {
		this.name=name;
		this.price=price;
		description=desc;
		this.stock=stock;
		counter++;
		id=counter;
		
	}
}
