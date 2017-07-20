package objects;

public class ProductsObject {

	private long prod_id,owner_id,prod_category,prod_sub_cat,avail_time,min_order;
	private String prod_name,prod_desc,deno,avail_units,order_units;
	float price;
	String prod_photo;
	
	
	public void set_minOrder(long min_order){
		this.min_order=min_order;
	}
	public long get_minOrder(){
		return this.min_order;
	}
	
	public void set_orderUnits(String order_units){
		this.order_units=order_units;
	}
	public String get_orderUnits(){
		return this.order_units;
	}
	
	
	
	
	public void set_availTime(long avail_time){
		this.avail_time=avail_time;
	}
	public long get_availTime(){
		return this.avail_time;
	}
	
	public void set_availUnits(String avail_units){
		this.avail_units=avail_units;
	}
	public String get_availUnits(){
		return this.avail_units;
	}
	
	
	public void set_id(long id){
		this.prod_id=id;
	}
	public long get_id(){
		return this.prod_id;
	}
	public void set_ownerid(long ownerid){
		this.owner_id=ownerid;
	}
	public long get_ownerid(){
		return this.owner_id;
	}
	public void set_prodcat(long prodcat){
		this.prod_category=prodcat;
	}
	public long get_prodcat(){
		return this.prod_category;
	}
	public void set_subcat(long subcat){
		this.prod_sub_cat=subcat;
	}
	public long get_subcat(){
		return this.prod_sub_cat;
	}
	public void set_prodname(String name){
		this.prod_name=name;
	}
	public String get_prodname(){
		return this.prod_name;
	}
	public void set_desc(String desc){
		this.prod_desc=desc;
	}
	public String get_proddesc(){
		return this.prod_desc;
	}
	public void set_prodphoto(String photo){
		this.prod_photo=photo;
	}
	public String get_prodphoto(){
		return this.prod_photo;
	}
	
	public void set_deno(String deno){
		this.deno=deno;
	}
	public String get_deno(){
		return this.deno;
	}
	public void set_price(float price){
		this.price=price;
	}
	public float get_price(){
		return this.price;
	}

}
