package objects;

public class ObjectCart {
	
	private long id,prodid;
	private int quantity;
	private String cartId,prodName,units;
	private byte[] prodphoto;
	private float  unitPrice;
	
	public void set_id(long id){
		this.id=id;
	}
	public long get_id(){
		return this.id;
	}
	public void set_cartId(String id){
		this.cartId=id;
	}
	public String get_cartId(){
		return this.cartId;
	}
	public void set_prodId(long id){
		this.prodid=id;
	}
	public long get_prodId(){
		return this.prodid;
	}
	public void set_quantity(int quant){
		this.quantity=quant;
	}
	public int get_quantity(){
		return this.quantity;
	}
	public byte[] get_photo(){
		return this.prodphoto;
			}
    public void set_photo(byte[] photo){
    	this.prodphoto=photo;
    }
    public void set_name(String name){
    	this.prodName=name;
    }
    public String get_name(){
    	return this.prodName;
    }
    public String get_units(){
    	return this.units;
    }
    public void set_units(String units){
    	this.units=units;
    }
    public void set_price(float price){
    	this.unitPrice=price;
    	
    }
    public float get_price(){
    	return this.unitPrice;
    }
}
