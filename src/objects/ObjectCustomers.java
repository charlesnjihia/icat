package objects;

public class ObjectCustomers {
	private String name,surname,mobile,location,email;
	private long id,issent;
	
	public void set_id(long id){
		this.id=id;
	}
	
	public void set_issent(long id){
		this.issent=id;
	}
	public long get_issent(){
		return this.issent;
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
	public void set_surname(String sname){
		this.surname=sname;
	}
   public String get_surname(){
	   return this.surname;
   }
   public void set_mobile(String mobile){
	   this.mobile=mobile;
   }
   public String get_mobile(){
	   return this.mobile;
   }
   public void set_location(String loc){
	   this.location=loc;
   }
   public String get_location(){
	   return this.location;
   }
   public void set_email(String email){
	   this.email=email;
   }
   public String get_email(){
	   return this.email;
   }
}
