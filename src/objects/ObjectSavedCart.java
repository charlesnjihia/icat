package objects;

public class ObjectSavedCart {
	private long id,custId,numItems;
	private String cartId,date,custName;
	
	public void set_Items(Long num){
		this.numItems=num;
	}
	public long get_Items(){
		return this.numItems;
	}
	
	public void set_id(long id){
		this.id=id;
	}
	public long get_id(){
		return this.id;
	}
	public void set_custId(long id){
		this.custId=id;
	}
	public long get_custId(){
		return this.custId;
	}
	public void set_cartId(String id){
		this.cartId=id;
	}
	public String get_cartId(){
		return this.cartId;
	}
	public void set_date(String date){
		this.date=date;
	}
	public String get_date(){
		return this.date;
	}
	public void set_name(String name){
		this.custName=name;
	}
	public String get_name(){
		return this.custName;
	}

}
