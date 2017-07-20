package objects;

public class ObjectSupCat {
	int supId,catId;
	String catName;
	
	public void set_supid(int sup){
		this.supId=sup;
	}
	public int get_supid(){
		return this.supId;
	}
	public void set_catid(int cat){
		this.catId=cat;
	}
	public int get_catid(){
		return this.catId;
	}
	public String get_catName(){
		return this.catName;
	}
public void set_catName(String name){
	this.catName=name;
}
}
