package objects;

public class ObjectAgents {
	
	private long id;
	private String name,password;
	
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
	public void set_pass(String code)
	{
		this.password=code;
	}
	public String get_pass(){
		return this.password;
	}


}
