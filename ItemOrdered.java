package perikor;

import java.util.List;

public class ItemOrdered {
	Item item;
	private int quantity;
	private List<Item>Itemlist;
	
	public int getquantity() {
		return quantity;
	}
	
	public void updatequantity(int no) throws NoStockException{
		if(no>item.getstock()) {
			throw new NoStockException("not enought stock");
		}
		else
		{
			quantity=no;
			item.setstock(item.getstock()-no);
		}
	}
	//constractors
	
	public ItemOrdered(Item item,int no) {
		this.item=item;
		quantity=no;
		
	}
}
