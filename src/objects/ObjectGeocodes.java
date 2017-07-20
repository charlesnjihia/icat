package objects;

public class ObjectGeocodes {
	String geoname;
	Long ipcode;
	
	public void set_name(String name){
		this.geoname=name;
	}
	public String get_name(){
		return this.geoname;
	}
	public void set_code(Long code){
		this.ipcode=code;
	}
	public Long get_code(){
		return this.ipcode;
	}

}
