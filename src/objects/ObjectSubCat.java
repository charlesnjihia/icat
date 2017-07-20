package objects;

public class ObjectSubCat {
	
	private long sub_cat,cat_id;
	private String subName;
	
	public long get_subid(){
		return this.sub_cat;
	}
	
	public void set_subid(long id){
		this.sub_cat=id;
	}
	public long get_catId(){
		return this.cat_id;
	}
	public void set_catId(long cat){
		this.cat_id=cat;
	}
	public String get_subName(){
		return this.subName;
	}
	public void set_subName(String name){
		this.subName=name;
	}

}
