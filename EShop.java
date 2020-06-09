package perikor;
import java.util.*;
public class EShop {
	private List<Item>Itemlist=new ArrayList<Item>();
	private List<Buyer>Buyerlist=new ArrayList<Buyer>();
	Scanner scnr=new Scanner(System.in);
	
	
	public List<Item> getItemlist() {
		return Itemlist;
	}
	public List<Buyer> getBuyerlist() {
		return Buyerlist;
	}
	public void addItem(int type,String name,double price,String description,int stock) {
		Random rand = new Random();
		int clr=rand.nextInt(5);
		String color;
		if(clr==0) {
			color="blue";
		}
		else if(clr==1) {
			color ="green";
		}
		else if(clr==2) {
			color="yellow";
		}
		else if(clr==3) {
			color="red";
		}
		else {
			color="black";
		}
		double c=rand.nextDouble()+rand.nextInt(3)+1;
		if(type==1) {
			
			Itemlist.add(new Pen(name,price,description,stock,color,c));
		}
		else if(type==2) {
			Itemlist.add(new Pencil(name,price,description,stock,c));
		}
		else if(type==3) {
			Itemlist.add(new Notebook(name,price,description,stock,rand.nextInt(5)));
		}
		else {
			Itemlist.add(new Paper(name,price,description,stock,(rand.nextInt(20)+30),rand.nextInt(40)+60));
		}
	}
	public void addItem() {
		
		int i=0;
		
		while(i!=1) {
			System.out.println("what type of item do you want to add?\nAveliable options are:Pen,Penncil,Notebook,Paper.Or type (back) to go back");
			scnr.reset();
			String tmp=scnr.nextLine();
			//scnr.reset();
			if(tmp.equals("back")) {
				return;
			}
			if((tmp.equals("Pen"))||(tmp.equals("Pencil"))||(tmp.equals("Notebook"))||(tmp.equals("Paper"))) {
				
				System.out.println("Insert the name of the item:");
				String name=scnr.nextLine();;
				
				scnr.reset();
				System.out.println("Insert the price of the item:");
				double price=0;
				try {
					 price=Double.parseDouble(scnr.nextLine());
					 
				}
				catch(NumberFormatException e) {
					System.out.println("wrong input");
					continue;
				}
				scnr.reset();
				System.out.println("Insert a description of the product");
				String descr=scnr.nextLine();
				
				scnr.reset();
				System.out.println("Insert how much stock will the item have");
				int stock=0;
				try {
					 stock =Integer.parseInt(scnr.nextLine());
				}
				catch(NumberFormatException e) {
					System.out.println("wrong input");
					continue;
				}
				scnr.reset();
				
				
				if(tmp.equals("Pen")){
					
					System.out.println("Insert the color of the pen:");
					String color=scnr.nextLine();
					
					scnr.reset();
					System.out.println("Instert the size of the pens tip:");
					double tipSize=0;
					try {
						tipSize=Double.parseDouble(scnr.nextLine());
					}
					catch(NumberFormatException e) {
						System.out.println("wrong input");
						continue;
					}
					Itemlist.add(new Pen(name,price,descr,stock,color,tipSize));
				}
				else if(tmp.equals("Pencil")) {
					System.out.println("Instert the size of the pens tip:");
					double tipSize=0;
					try {
						 tipSize=Double.parseDouble(scnr.nextLine());
					}
					catch(NumberFormatException e) {
						System.out.println("wrong input");
						continue;
					}
					Itemlist.add( new Pencil(name,price,descr,stock,tipSize));
				}
				else if(tmp.equals("Notebook")) {
					System.out.println("Insert the number of sections:");
					int sec=0;
					try {	
						sec=Integer.parseInt(scnr.nextLine());
					}
					catch(NumberFormatException e) {
						System.out.println("wrong input");
						continue;
					}
					Itemlist.add(new Notebook(name,price,descr,stock,sec));
				}
				else {
					System.out.println("Insert the weight of the paper in gramms:");
					int weight=0;
					try {
						weight=Integer.parseInt(scnr.nextLine());
						
					}
					catch(NumberFormatException e) {
						System.out.println("wrong input");
						continue;
					}
					scnr.reset();
					System.out.println("Insert the amount of pages:");
					int pages=0;
					try {
						pages=Integer.parseInt(scnr.nextLine());
					}
					catch(NumberFormatException e) {
						System.out.println("wrong input");
						continue;
					}
					Itemlist.add(new Paper(name,price,descr,stock,weight,pages));
				}
				
				scnr.reset();
				
			}
			
			else {
				   System.out.println("wrong type of item please insert the corect type");
			}
			i=1;
		}
		
	}
	public void print() {
		for (Item i :Itemlist) {
		      System.out.println(i.toString());
		    }
	}
	public Item getItemByld  (int code)throws NoOptionException{
		
		Item tmp=null;
		for (Item i:Itemlist) {
			if (i.getid()==code)
				tmp=i;
			
		}
		if (tmp!=null) {
			return tmp;
		}
		else throw new NoOptionException("not such item aveliable");
	}
	public void removeItem(int code) {
		for (Item i:Itemlist) {
			int j=0;
			int t=0;
			if (i.getid()==code) {
				t++;
				Itemlist.remove(t);
				j=1;
			}
			if (j==0){
				System.out.println("no Item found");
			}
		}
		
	}
	public void addBuyer(String UserName,String UserEmail) {
		Buyerlist.add(new Buyer(UserName,UserEmail));
	}
	public void removeBuyer(int n) {
		Buyerlist.remove(n);
	}
	public void updateItemStock(int code,int stock) {
		for (Item i:Itemlist) {
			if(i.getid()==code) {
				i.setstock(stock);
			}
		}
	}
	public void showCategories() {
		System.out.println("the curent categories are:\nPen\nPencil\nNotebook\nPaper");
	}
	public void showProductsInCategories(String item) {
		//needs exception
		int tmp=0;
		if(item.equals("Pen")) {
			tmp=1;
		}
		else if(item.equals("Pencil")) {
			tmp=2;
		}
		else if(item.equals("Notebook")) {
			tmp=3;
		}
		else if(item.equals("Paper")) {
			tmp=4;
		}
		
		for (Item i:Itemlist) {
			
			char temp=String.valueOf(i.getid()).charAt(0);
			int no=Character.getNumericValue(temp);
			if(no==tmp) {
				System.out.println(i.getBasicInfo()+"\n\n");
			}
		}
	}
	
	
	
	public Item ShowProduct(int code) throws NoOptionException{
		int j=0;
		Item k=null;
		for(Item i:Itemlist) {
			if(code==i.getid()) {
				i.toString();
				j=1;
				k=i;
			}
		}
		if(j==0)
			throw new NoOptionException("not such product found");
	
		else 
			return k;
	}
	public void checkStatus() {
		int j=0;
		for(Buyer i:Buyerlist) {		
			System.out.println(j+"  "+i.BuyerStatus());
			System.out.println("\n\n\n");
			j++;
		}
	}
	
}
