package perikor;

public class Notebook extends Item{
	//variables
	private int sections;
	private static  char code='3';
	//methods
	public String getDetails() {
		return("Notebook with "+sections+"sections");
	}
	//constractor
	public Notebook(String name, double price,String desc,int stock,int sec) {
		super(name, price, desc,stock);
		String tmp =String.valueOf(super.getid());
		tmp=code+tmp;
		super.setid(Integer.parseInt(tmp));
		sections=sec;
	}
}
