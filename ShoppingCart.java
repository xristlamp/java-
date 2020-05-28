package perikor;

import java.util.*;

public class ShoppingCart {
	private List<ItemOrdered>orderList=new ArrayList<ItemOrdered>();
	Buyer user;
	public ShoppingCart (Buyer user) {
		this.user=user;
	}
	public List getOrederList() {
		return orderList;
	}
	public void addItemOrdered(Item item,int no) throws Exception{
		////chek stock
		if(no>item.getstock()) {
			throw new Exception("not enought stock");
		}
		for (ItemOrdered i: orderList) {
			if(i.item.getid()==item.getid()) {
				i.updatequantity(i.getquantity()+no);
			}
			else {
				orderList.add(new ItemOrdered(item,no));
				item.setstock(item.getstock()-no);
			}
		}
	}
	public void removeItemOrederd(int code) throws  NoOptionException{
		int j=0;
		for (ItemOrdered i: orderList) {
			j++;
			if (code==i.item.getid()) {
				orderList.remove(j);
				break;
			}
		
		}
		if(j==orderList.size()) {
			throw new NoOptionException("item not found");
		}
	}
	public void changeItemOrderedQuantity(int code,int no) throws NoOptionException{
		int j=0;
		int k=0;
		for (ItemOrdered i:orderList) {
			j++;
			if(code==i.item.getid()) {
				while (k==0) {
					try {
						k=1;
						i.updatequantity(no);
						break;
					}
					catch(NoStockException e) {
						System.out.println(e.getMessage()+"please enter an other value");
					}
				}
			}
		}
		if(j==orderList.size()) {
			throw new NoOptionException("item not found");
		}
	}
	public void showCart() throws EmptyCartException{
		if (orderList.size()==0) {
			throw new EmptyCartException("the cart is empty");
		}
		else {
			for (ItemOrdered i: orderList) {
				System.out.println(i.item.getname()+"   "+i.getquantity()+" "+i.getquantity()+"+"+i.item.getprice()+"="+i.getquantity()+i.item.getprice());;
			}
			double tmp=calculateCourierCost()+calculateNet();
			System.out.println("Price:"+calculateNet()+"\nCourier Cost:"+calculateCourierCost()+"\n"+tmp);
		}
	}
	public void clearCart() {
		orderList.clear();
	}
	public int checkout() {
		//if returns 0 no chekc out flag==bonus added
		int flag=0;
		try {
			showCart();
		}
		catch (EmptyCartException e) {
			System.out.println(e.getMessage());
		}
		Scanner scnr=new Scanner(System.in);
		double tmp=calculateCourierCost()+calculateNet();
		System.out.println("your overall cost is"+tmp);
		System.out.println("do you want to contineu with your order?(type 1 for yes and 0 for no");
		int temp=scnr.nextInt();
		if	(temp==0) {
			flag=0;
		}
		else {
			clearCart();
			flag=(int)Math.round(tmp)/10;
		}
		return flag;
	}
	public double calculateNet() {
		double tmp=0;
		for (ItemOrdered i:orderList) {
			tmp = tmp+ (i.item.getprice()*i.getquantity());
		}
		return tmp;
	}
	public double calculateCourierCost() {
		double tmp=calculateNet()/50;
		if(tmp<=3) {
			tmp=3;
		}
		if(user.getbuyerCategory().equals("SILVER"))
		{
			tmp=tmp/2;
		}
		else if(user.getbuyerCategory().equals("GOLD")) {
			tmp=0;
		}
		return tmp;
	}
	
	
	
}
