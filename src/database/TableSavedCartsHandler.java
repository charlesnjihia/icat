package database;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TableSavedCartsHandler {
	
	private SQLiteDatabase database;
	private TableDatabase  svCart ;
	private TableDatabase cust;
	
	public TableSavedCartsHandler(Context context) {
	    svCart = new TableDatabase(context);
	     cust=new TableDatabase(context);  
	  }
	
	public void open() throws SQLException {
	    database = svCart.getWritableDatabase();
	    cust.getWritableDatabase();
	  }
	public void close() {
		 svCart.close();
		 cust.close();
		
	  }
	@SuppressLint("SimpleDateFormat")
	public void addtoSvCart(objects.ObjectSavedCart svcart)
	 {
		Long date=System.currentTimeMillis();
        Date tDate=new Date(date);
       	String todaydate = new SimpleDateFormat("yyyy-MM-dd").format(tDate);
       	
		 ContentValues values = new ContentValues();	    
		    values.put(TableDatabase.CART_ID,svcart.get_cartId());
		    values.put(TableDatabase.CUSTOMER_ID,svcart.get_custId());
		    values.put(TableDatabase.DATE,todaydate);	
		    values.put(TableDatabase.ISSENT,0);
		   Long id= database.insert(TableDatabase.TABLE_SVCARTS , null, values);
		    Log.i("indeed created","created and saved"+id);
	 }
	
	public void updatePurchase(String cartId){
		String query="update "+TableDatabase.TABLE_SVCARTS+" set sent=1  where cart_id='"+cartId+"'";
		 Log.i("IDDDDDDDDDDD",""+cartId);
		 
		 try {
			
			 Cursor cursor= database.rawQuery(query, null); 
			 cursor.moveToFirst();
			 
			// database.execSQL(query,null);
			// Log.i("cart updated accordingly","ly");
			 
			}
			catch (Exception e) {
			    Log.i("error is here", "SQLiteConstraintException:" + e.getMessage());
			}
		// database.execSQL(query);  
        
	}
	
	public List<objects.ObjectSavedCart> getSavedCarts(){
		List<objects.ObjectSavedCart> carts=new ArrayList<objects.ObjectSavedCart>();
		
		String query="select a.*,b.fname,b.lname from "+TableDatabase.TABLE_SVCARTS+" as a left join "+TableDatabase.TABLE_CUSTOMERS+
				" as b on a.customer_id=b._id order by a._id DESC";
		
		/*Cursor cursor = database.query(TableSavedCarts.TABLE_SVCARTS,
		        null, null, null, null, null, null);*/
		 Cursor cursor = database.rawQuery(query, null);  

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	objects.ObjectSavedCart cart = cursorToSvCart(cursor);
		       carts.add(cart);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		
		
		return carts;
		
	}
	
	public int getStatus(String cartId){
		int status=0;
		
		String query="select sent from "+TableDatabase.TABLE_SVCARTS+" where cart_id='"+cartId+"'";
		Cursor cursor = database.rawQuery(query, null);  

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	
	    status=	cursor.getInt(0);
	      cursor.moveToNext();
	    }
		return status;
	}
	
	public List<objects.ObjectSavedCart> getSentCarts(){
		List<objects.ObjectSavedCart> carts=new ArrayList<objects.ObjectSavedCart>();
		
		String query="select a.*,b.fname,b.lname from "+TableDatabase.TABLE_SVCARTS+" as a left join "+TableDatabase.TABLE_CUSTOMERS+
				" as b on a.customer_id=b._id where a.sent=1";
		
		/*Cursor cursor = database.query(TableSavedCarts.TABLE_SVCARTS,
		        null, null, null, null, null, null);*/
		 Cursor cursor = database.rawQuery(query, null);  

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	objects.ObjectSavedCart cart = cursorToSvCart(cursor);
		       carts.add(cart);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		
		
		return carts;
		
	}
	
	
	private objects.ObjectSavedCart cursorToSvCart(Cursor cursor) {
		objects. ObjectSavedCart cart = new objects.ObjectSavedCart();
		  cart.set_id(cursor.getLong(0));
		  cart.set_cartId(cursor.getString(1));
		  cart.set_custId(cursor.getLong(2));
		  cart.set_date(cursor.getString(3));
		  cart.set_name(cursor.getString(5)+" "+cursor.getString(6));
		  return cart;
		}	
			
	
	
	
	
	
	
	

}
