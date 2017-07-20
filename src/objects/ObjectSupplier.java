package objects;

public class ObjectSupplier {
	
	private String name,sup_cats,phone;
	private long id,catId;
	private String logo;
	
	public void set_id(long id){
		this.id=id;
	}
	public long get_id(){
		return this.id;
	}
	
   public void set_name(String name){
	   this.name=name;
   }
   public String get_name(){
	   return this.name;
   }
   public void set_supcats(String supcats){
	   this.sup_cats=supcats;
   }
   public String get_supcats(){
	   return this.sup_cats;
   }
  
  
   public void set_logo(String logo){
	   this.logo=logo;
   }
   public String get_logo(){
	   return this.logo;
   }
   
   
   
}
