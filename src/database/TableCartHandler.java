package database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TableCartHandler {
	private SQLiteDatabase database,database1;
	private TableDatabase  cart ;
	private TableDatabase prodDetails;
	
	public TableCartHandler(Context context) {
	    cart = new TableDatabase(context);
	    prodDetails=new TableDatabase(context);
	   
	  }
	public void open() throws SQLException {
	    database = cart.getWritableDatabase();
	    database1=prodDetails.getWritableDatabase();
	  }
	public void close() {
		 cart.close();
		 prodDetails.close();
	  }
	
	public void deleteCart(String cartid){
		String query="delete from cart where cart_id='"+cartid+"'";
		database.rawQuery(query, null);
		
	}
	
	public void updateProdQty(long prodcode,String cartId,int qty){
		String query="update "+TableDatabase.TABLE_CART+" set quantity="+qty+"  where cart_id='"+cartId+"' and product_id="+prodcode;
		 database.execSQL(query);  
		
	}
	
	
	
	
	public void addtoCart(objects.ObjectCart cart)
	 {
		 ContentValues values = new ContentValues();	    
		    values.put(TableDatabase.CART_ID, cart.get_cartId());
		    values.put(TableDatabase.PRODUCT_ID, cart.get_prodId());
		    values.put(TableDatabase.QUANTITY, cart.get_quantity());
		    values.put(TableDatabase.UNITS, cart.get_units());
		    database.insert(TableDatabase.TABLE_CART , null, values);
	 }
	public List<objects.ObjectCart>	getCartProds(String cartId){
		List<objects.ObjectCart> prods=new ArrayList<objects.ObjectCart>();
		
	//String query="select a.*,b.prod_name,b.prod_photo,b.prod_price from "+TableDatabase.TABLE_CART+" a left join "+TableDatabase.TABLE_PRODUCTS+
	//			" b on a.product_id=b.prod_id where a.cart_id='"+cartId+"'";
	//String query="select cart.*,products.prod_name,products.prod_photo,products.prod_price from cart,products where cart.product_id=products.prod_id" +
		//	" and cart.cart_id='"+cartId+"'";
	String query="select * from "+TableDatabase.TABLE_CART+" where cart_id='"+cartId+"'";
	//String query="select * from "+TableDatabase.TABLE_CART;
		/*Cursor cursor = database.query(TableCart.TABLE_CART ,
		        null, null, null, null, null, null);*/
		        Cursor cursor = database.rawQuery(query, null);
		        Log.i("length on the database is ",""+cursor.getCount());

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      objects.ObjectCart prod = cursorToCart(cursor);
		      prods.add(prod);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		
		
		return prods;
		
	}
	
	public objects.ProductsObject getProdDetails(long prodid){
		objects.ProductsObject prod=new objects.ProductsObject();
		
String query="select prod_name,prod_photo,prod_price from "+TableDatabase.TABLE_PRODUCTS+
				" where prod_id ="+prodid;
	
		        Cursor cursor = database.rawQuery(query, null);
		      
 
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		       prod = cursorToProd(cursor);
		      
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		
		
		return prod;
		
	}
	
	
	
	public int getCartProdsCount(String cartId){
	
		
		Cursor cursor = database.query(TableDatabase.TABLE_CART,
		        null, TableDatabase.CART_ID+"='"+cartId+"'", null, null, null, null); 
       int i=cursor.getCount();    
	return i;  
		
	}
	
	private objects.ObjectCart cursorToCart(Cursor cursor) {
		objects.ObjectCart cart = new objects.ObjectCart();
	  cart.set_id(cursor.getLong(0));
	  cart.set_cartId(cursor.getString(1));
	  cart.set_prodId(cursor.getLong(2));	 
	  cart.set_quantity((int)cursor.getLong(3));
	  cart.set_units(cursor.getString(4));
	  
	 /* cart.set_name(cursor.getString(5));
	  cart.set_photo(cursor.getBlob(6));
	  cart.set_price(cursor.getFloat(7));
	  */
	
	  return cart;
	}	
	
	
	
	
	
	private objects.ProductsObject cursorToProd(Cursor cursor) {
		objects.ProductsObject prod = new objects.ProductsObject();
		
		prod.set_prodname(cursor.getString(0));
		prod.set_prodphoto(cursor.getString(1));
		prod.set_price(cursor.getFloat(2));
	 
	   
	    
	  return prod;
	}	
	
	
	

}
