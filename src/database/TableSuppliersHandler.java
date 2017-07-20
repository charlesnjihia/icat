package database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TableSuppliersHandler {
	private SQLiteDatabase database;
	private TableDatabase  suppliers ;
	
	
	public TableSuppliersHandler(Context context) {
	   suppliers = new TableDatabase(context);
	    	   
	  }
	public void open() throws SQLException {
	    database = suppliers.getWritableDatabase();
	    
	  }
	public void close() {
		 suppliers.close();
		 
	  }
	public void addSupplier(objects.ObjectSupplier sup)
	 {
		 ContentValues values = new ContentValues();	    
		 values.put(TableDatabase.SUP_ID,sup.get_id());	
		 values.put(TableDatabase.SUP_NAME,sup.get_name());	
		 values.put(TableDatabase.SUP_LOGO,sup.get_logo());
		 values.put(TableDatabase.SUP_CATS,sup.get_supcats());
		 
		 database.insert(TableDatabase.TABLE_SUPPLIERS, null, values);
	 }
	
	public void updateSupplier(objects.ObjectSupplier sup)
	 {
		String query="update "+TableDatabase.TABLE_SUPPLIERS+" set "+TableDatabase.SUP_NAME+"="+sup.get_name()+","+TableDatabase.SUP_LOGO+"="+sup.get_logo()+","+TableDatabase.SUP_CATS+"="+sup.get_supcats()+" where "+TableDatabase.SUP_ID+"="+sup.get_id();
		Cursor cursor= database.rawQuery(query, null); 
		cursor.moveToFirst();
		cursor.close();
		 /*ContentValues values = new ContentValues();	    
		 values.put(TableDatabase.SUP_ID,sup.get_id());	
		 values.put(TableDatabase.SUP_NAME,sup.get_name());	
		 values.put(TableDatabase.SUP_LOGO,sup.get_logo());
		 values.put(TableDatabase.SUP_CATS,sup.get_supcats());
		 
		 database.insert(TableDatabase.TABLE_SUPPLIERS, null, values);*/
	 }
	
	public List<objects.ObjectSupplier> getSuppliers(){
		List<objects.ObjectSupplier> sups=new ArrayList<objects.ObjectSupplier>();
		
		/*String query="a.*,b.PROD_NAME,b.PROD_PHOTO from "+TableCart.TABLE_CART+" a join "+TableProducts.TABLE_PRODUCTS+
				" b on a.PRODUCT_ID=b.ID where a.CART_ID="+cartId;*/
		
		
		Cursor cursor = database.query(TableDatabase.TABLE_SUPPLIERS ,
		        null, null, null, null, null, null);
		       // Cursor cursor = database.rawQuery(query, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	objects.ObjectSupplier sup = cursorToSup(cursor);
		         sups.add(sup);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		
		
		return sups;
		
	}
	public objects.ObjectSupplier getSupplier(int supId){
		objects.ObjectSupplier sup=new objects.ObjectSupplier();
		
		/*String query="a.*,b.PROD_NAME,b.PROD_PHOTO from "+TableCart.TABLE_CART+" a join "+TableProducts.TABLE_PRODUCTS+
				" b on a.PRODUCT_ID=b.ID where a.CART_ID="+cartId;*/
		
		
		Cursor cursor = database.query(TableDatabase.TABLE_SUPPLIERS ,
		        null, "sup_id="+supId, null, null, null, null);
		       // Cursor cursor = database.rawQuery(query, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	sup = cursorToSup(cursor);
		        
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		
		
		return sup;
		
	}
	private objects.ObjectSupplier cursorToSup(Cursor cursor) {
		objects.ObjectSupplier sup = new objects.ObjectSupplier();
		  sup.set_id(cursor.getLong(1));
		  sup.set_name(cursor.getString(2));		  
		  sup.set_logo(cursor.getString(3));
		  sup.set_supcats(cursor.getString(4));
		  return sup;
		}		

}
