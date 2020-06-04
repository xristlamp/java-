package perikor;
import java.util.*;

public class Menu {
	private Owner owner=new Owner("Admin","Admin");
	private Buyer a;
	Scanner scnr=new Scanner(System.in);
	private String userEmail;
	EShop shop=new EShop();
	private List <Buyer>Buyerlist=shop.getBuyerlist();
	User user=new Buyer("","");
	private List<Item>Itemlist=shop.getItemlist();
	
	private void signup() {
		int tmp=1;
		System.out.println("your email is not recognized.\n do you want to try again loging in(0) or signup(1)");
		try {
			tmp = selection(1);
		}
		catch(NoOptionException ex) {
			System.out.println(ex.getMessage());
		}
		catch(NumberFormatException e) {
			System.out.println("wrong input");
		}
		if(tmp==0) {
			return;
		}
		else {
			
			//create buyer
			System.out.print("Insert your name:");
			String b=scnr.nextLine();
			System.out.print("Insert your email:");
			String c=scnr.nextLine();
			shop.addBuyer(b,c);
			System.out.println("you have suchefull been signed up to the EShop");
		}
		
	}
	private boolean selection () throws NoOptionException{
		String answer=scnr.nextLine();
		if(answer.equals("yes")){
			return true;
		}
		else if(answer.equals("no")) {
			return false;
		}
		else 
			throw new NoOptionException("not such opoion aveliable");
	}
	private  int selection(int n) throws NoOptionException{
		int tmp=0;
		while (true) {
			try {
			tmp=Integer.parseInt(scnr.nextLine());
			break;
			}
			catch(NumberFormatException e) {
				System.out.println("Wrong input");
			}
		}
		if (tmp>n||tmp<0) {
			throw new NoOptionException("not such option aveliable");
		}
		else return tmp;
	}
	
	private void browseStore(int x) throws NoOptionException{
		while(true){
			shop.showCategories();
			System.out.println("those are the avelible categories at the moment");
			System.out.println("type the  category to see the products or back to go back");
			String tmp=scnr.nextLine();
			if (tmp.equals("back")) {
				return;
			}
			else if(tmp.equals("Pen")||tmp.equals("Pencil")||tmp.equals("Notebook")||tmp.equals("Paper")){
				shop.showProductsInCategories(tmp);
				try {
					categoryListing(x);
				}
				catch(NoOptionException e) {
					System.out.println(e.getMessage());
				}
			}
			else throw new NoOptionException("sorry the catogory you are looking for is not aveliable at the moment");
		}
		
	}
	
	private void categoryListing(int x) throws NoOptionException{
		System.out.println("type the code of the product to see mote info about it or 0 to go back");
		int tmp;
		while(true) {
			try {
				tmp=Integer.parseInt(scnr.nextLine());
				break;
			}
			catch (NumberFormatException e) {
				System.out.println("wrong input");
			}
		}
		try {
			if(0==tmp){
				
				return;
				
			}
			else {
				if(x==1) {
					Item item=shop.ShowProduct(tmp);
					System.out.println("do you want to buy this product?(yes/no)");
					boolean sel=false;
					while (true) {
						try {
							sel=selection();
							break;
						}
						catch (NoOptionException e) {
							System.out.println(e.getMessage());
						}
					}
						
							if(sel) {
								boolean i=true;
								while (i) {
									try {
										System.out.println("enter the amount you want to order");
										a.placeOrder(item, Integer.parseInt(scnr.nextLine()));
										i=false;
									}
									catch (Exception e) {
										System.out.println("not enought stock");
									}
								}
							}
						
					
				}
				else {
					System.out.println("do you want to make changes to this product?(y/n)");
					if("y".equals(scnr.hasNextLine())) {
						System.out.println("enter the new amount of stock");
						shop.updateItemStock(tmp, Integer.parseInt(scnr.nextLine()));
					}
				}
			}
		}
		catch (NoOptionException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private int view()
	{
		///returns added bonus if checkout
		try {
			a.cart.showCart(a.getbuyerCategory());
			System.out.println("type the code of the product to select it(to delete or change its amount),type (empty) to empty the cart , type (checkout)to checkout or (back) to go back");
			String tmp=scnr.nextLine();
			if(tmp.equals("empty")) {
				a.cart.clearCart();
				return 0;
			}
			else if(tmp.equals("checkout")) {
				int addBonus=a.cart.checkout(a.getbuyerCategory());
				System.out.println("your added bonus are"+addBonus);
				return addBonus;
			}
			else if(tmp.equals("back")) {
				return 0;
			}
			else {
				System.out.println("do you want to delete it (0),change the amount(1),or go back(2)?");
				try {
					int temp=selection(2);
					if (temp==0) {
						try {
							a.cart.removeItemOrederd(Integer.parseInt(tmp));
						}
						catch (NumberFormatException ex) {
							System.out.println("wrong input");
						}
						catch(NoOptionException e) {
							System.out.println(e.getMessage());
						}
						
					}
					else if(temp==1) {
						try {
							System.out.println("what do you want the new quantyty to be?");
							a.cart.changeItemOrderedQuantity(Integer.parseInt(tmp),Integer.parseInt(scnr.nextLine()));
						}
						catch(NumberFormatException excep) {
							System.out.println("wrong input");
						}
					}
					else
						return 0;
				}
				catch (NoOptionException exc) {
					System.out.println("not such option");
				}
					
				return 0;
			}
		}
		catch(EmptyCartException exce) {
			System.out.println(exce.getMessage());
			return 0;
		}
		
	}
	private void check() {
		shop.checkStatus();
		System.out.println("enter the number of the costumer to see his/her cart or enter (back) to go back");
		int select;
		try {
			select=selection(shop.getBuyerlist().size());
			//Buyerlist.get(select).cart.showCart();
			int j=0;
			for(Buyer i:Buyerlist) {
				if(j==select) {
					i.cart.showCart(a.getbuyerCategory());
					break;
				}
				j++;
			}
			System.out.println("do you want to delete this costumer?(y/n)");
			if("y".equals(scnr.nextLine())) {
				shop.removeBuyer(select);
			}
		}
		catch (EmptyCartException e) {
			System.out.println("the cart is empty");
		}
		catch(NoOptionException ex) {
			System.out.println(ex.getMessage());
		}
		
		
		
	}
	
	private void add() {
		shop.addItem();
	}
	private void test() {
		shop.addBuyer("UserA", "EmailA");
		shop.addBuyer("UserB", "EmailB");
		shop.addBuyer("UserC", "EmailC");
		shop.addBuyer("UserD", "EmailD");
		shop.addBuyer("UserE", "EmailE");
		shop.addBuyer("UserF", "EmailF");
		shop.addBuyer("UserG", "EmailG");
		shop.addBuyer("UserK", "EmailK");
		
		shop.addItem(1,"pen1",1.5,"Descirption1",20);
		shop.addItem(1,"pen2",3.0,"Descirption2",20);
		shop.addItem(1,"pen3",1.0,"Descirption3",20);
		shop.addItem(1,"pen4",2.0,"Descirption4",20);
		shop.addItem(1,"pen5",2.5,"Descirption5",20);
		shop.addItem(2,"Pencil1",1.5,"Description1",15);
		shop.addItem(2,"Pencil2",2.0,"Description2",15);
		shop.addItem(2,"Pencil3",3.5,"Description3",15);
		shop.addItem(2,"Pencil4",2.5,"Description4",15);
		shop.addItem(3,"Notebook1",5,"Description1",5);
		shop.addItem(3,"Notebook2",5,"Description2",5);
		shop.addItem(3,"Notebook3",4.5,"Description3",5);
		shop.addItem(3,"Notebook4",6,"Description4",5);
		shop.addItem(3,"Notebook5",5.2,"Description5",5);
		shop.addItem(4,"Paper1",10,"Description1",5);
		shop.addItem(4,"Paper2",11,"Description2",5);
		shop.addItem(4,"Paper3",12,"Description3",5);
		Item it=shop.getItemlist().get(3);
		shop.getBuyerlist().get(2).placeOrder(it, 5);


	}
	
	public void mainMenu(){
		while(true) {
		System.out.println("do you want to lanch the program with preadded users and items?(yes/no)");
		
			try {	
				if(selection()) {
					test();
					break;
				}
			}
			catch (NoOptionException e) {
				System.out.println(e.getMessage());
			}
		}
		
		//loged??
		while (true) {
			
			System.out.println("insert your email");
			userEmail=scnr.nextLine();
			User i=null;
			if (userEmail.equals("Admin")) {
				//owner
				
				System.out.print("welcome to our Shop ");
				System.out.println(owner.getUserName()+"\nyou are an owner ");
				String tmp = "";
				while (true) {
					
					System.out.println("you can(browse)the store");
					System.out.println("you can (check) the status of the costumers conected to the store");
					System.out.println("you can (add) an item to the store");
					System.out.println("you can also (logout)or(exit) the store");
					tmp = scnr.nextLine();
					if(tmp.equals("browse")) {
						try {
							browseStore(2);
						}
						catch(Exception e) {
							System.out.println("error");
						}
					}
					else if(tmp.equals("check")) {
						check();
					}
					else if(tmp.equals("add")) {
						add();
					}
					else if(tmp.equals("exit")) {
						System.exit(0);
					}
					else if(tmp.equals("logout")) {
						break;						
					}
					
					else {
						System.out.println("not such option aveliable");
					}
				}
				
			}
			else {
				for(Buyer b:Buyerlist) {
					if (userEmail.equals(b.getUserEmail())) {
						i=b;
						///buyer
						String mail=i.getUserEmail();
						String name = i.getUserName();
						a=b;
						
						System.out.print("welcome to our Shop ");
						
						String tmp="";
						while (true) {
							System.out.println(a.getUserName()+"\nyou have "+a.getbonus()+" bonus,this means you are in the "+a.getbuyerCategory()+" category");
							System.out.println("you can (browse) the store ");
							System.out.println("you can (view) your shopping cart");
							System.out.println("you can (checkout)");
							System.out.println("also you can (logout) or exit the program");
							tmp=scnr.nextLine();
							if(tmp.equals("browse")) {
								try {
									browseStore(1);
								}
								catch (NoOptionException e) {
									System.out.println(e.getMessage());
								}
							}
							else if(tmp.equals("view")) {
								int Bonus=view();
								a.awardBonus(Bonus);
							}
							else if(tmp.equals("checkout")) {
								int addBonus=a.cart.checkout(a.getbuyerCategory());
								System.out.println("your added bonus are: "+addBonus);
								a.awardBonus(addBonus);
							}
							else if(tmp.equals("exit")) {
								System.exit(0);
							}
							else if(tmp.equals("logout")) {
								break;
							}
							
							else {
								System.out.println("not such option aveliable");
							}
						}
						break;
					}
				}
			}
			if(i==null) {
			//user sinup
			signup();
			continue;
			}
			
			
		}
		
	}
}
