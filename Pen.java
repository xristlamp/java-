package perikor;

public class Pen extends Item{
	//variables
	private String color;
	private double tipSize;
	private static  char code='1';
	
	//methods
	public String getDetails() {
		return(color+" Pen"+"\nTip size:"+tipSize);
	}
	//constractor
	public Pen(String name, double price,String desc,int stock,String color,double tipSize) {
		super(name, price, desc,stock);
		String tmp =String.valueOf(super.getid());
		tmp=code+tmp;
		super.setid(Integer.parseInt(tmp));
		this.color=color;
		this.tipSize=tipSize;
	}

}
