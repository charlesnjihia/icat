package objects;

public class ObjectProductCats {
	
	private long id;
	private String name;
	private String catPhoto;
	private String light;
	private String dark;
	
	public String getLight() {
		return light;
	}
	public void setLight(String light) {
		this.light = light;
	}
	public String getDark() {
		return dark;
	}
	public void setDark(String dark) {
		this.dark = dark;
	}
	public long get_id(){
		return this.id;
	}
	public void set_id(long id){
		this.id=id;
	}
	public String get_name(){
		return this.name;
	}
	public void set_name(String name){
		this.name=name;
	}
	public void set_photo(String photo){
		this.catPhoto=photo;
	}
	public String get_photo(){
		return this.catPhoto;
	}

}
