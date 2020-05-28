package perikor;


public class Buyer extends User {
	private int bonus=0;
	private String buyerCategory="BRONZE";
	ShoppingCart cart;
	public void awardBonus(int x) {
		bonus=bonus+x;
	}
	public ShoppingCart getCart() {
		return cart;
	}
	public int getbonus() {
		return bonus;
	}
	public String BuyerStatus() {
		return ("buyer name:"+super.getUserName()+"\nbuer email:"+super.getUserEmail()+"\nbuyer bonus:"+bonus+"\nbuyer category"+buyerCategory);
	}
	public void setbuyerCategory() {
		if ((bonus>=100)&&(bonus<200)) {
			buyerCategory="SILVER";
			//50% transfer
			
		}
		else if (bonus>=200) {
			buyerCategory="GOLD";
			//no transfer
		}
			
	}
	
	public String getbuyerCategory() {
		return buyerCategory;
	}
	public void placeOrder(Item item,int amount) {
		//////take user input to set item,amount
		try {
			cart.addItemOrdered(item, amount);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public Buyer(String UserName,String UserEmail) {
		super (UserName,UserEmail);
		cart =new ShoppingCart(this);
	}
	public boolean showOwner() {
		return false;
	}

}
