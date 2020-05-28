package perikor;

public class Paper extends Item{
	//variables
	private int weight;
	private int pages;
	private static  char code='4';
	//methods
	public String getDetails() {
		return("Paper "+pages+"pages\n"+weight+"gramms");
	}
	public Paper(String name, double price,String desc,int stock,int wei,int pages) {
		super(name, price, desc,stock);
		String tmp =String.valueOf(super.getid());
		tmp=code+tmp;
		super.setid(Integer.parseInt(tmp));

		weight=wei;
		this.pages=pages;
	}
}
