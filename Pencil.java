package perikor;

public class Pencil extends Item{
	//variables
	private double tipSize;
	private static  char code='2';

	//methods
	public String getDetails() {
		return("Pencil\nTip size"+tipSize);
	}
	//constractor
	public Pencil(String name, double price,String desc,int stock,double tipSize) {
		super(name, price, desc,stock);
		String tmp =String.valueOf(super.getid());
		tmp=code+tmp;
		super.setid(Integer.parseInt(tmp));

		this.tipSize=tipSize;
	}
}
