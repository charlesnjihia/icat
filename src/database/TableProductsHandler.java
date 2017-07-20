package database;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TableProductsHandler{

  // Database fields
  private SQLiteDatabase database;
  private TableDatabase dbHelper;
  private String[] allColumns = { TableDatabase.PROD_ID,
		  TableDatabase.PROD_NAME, TableDatabase.PROD_CATEGORY,
		  TableDatabase.PROD_DESCRIPTION, TableDatabase.PROD_PHOTO,
		  TableDatabase.PROD_MORE_PHOTOS, TableDatabase.PRICE, TableDatabase.PRICE_DENO};

  public TableProductsHandler(Context context) {
    dbHelper = new TableDatabase(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public long createProduct(objects.ProductsObject prod) {
	 // Log.i("enter func","entered function"); 
    ContentValues values = new ContentValues();
    values.put(TableDatabase.PROD_ID, prod.get_id());
    values.put(TableDatabase.OWNER_ID, prod.get_ownerid());
    values.put(TableDatabase.PROD_NAME, prod.get_prodname());
    values.put(TableDatabase.PROD_CATEGORY, prod.get_prodcat());
    values.put(TableDatabase.PROD_SUB_CATEGORY, prod.get_subcat());
    values.put(TableDatabase.PROD_DESCRIPTION, prod.get_proddesc());
    values.put(TableDatabase.PROD_PHOTO, prod.get_prodphoto());
    values.put(TableDatabase.PRICE, prod.get_price());
    values.put(TableDatabase.PRICE_DENO, prod.get_deno());
    values.put(TableDatabase.AVAIL_TIME, prod.get_availTime());
    values.put(TableDatabase.AVAIL_UNITS, prod.get_availUnits());
    values.put(TableDatabase.MIN_ORDER, prod.get_minOrder());
    values.put(TableDatabase.ORDER_UNITS, prod.get_orderUnits());
    
  //  Log.i("just b4 insert","cud it be insertion");  
   long i= database.insert(TableDatabase.TABLE_PRODUCTS , null, values);
   //  Log.i("id ni","id ni"+i);
   /* Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Comment newComment = cursorToComment(cursor);
    cursor.close();
    return newComment;*/
    return i; 
     
     
  }
  
 
  
  
  public void updateProduct(objects.ProductsObject prod) {
		 // Log.i("enter func","entered function"); 
	    /*ContentValues values = new ContentValues();
	    values.put(TableDatabase.PROD_ID, prod.get_id());
	    values.put(TableDatabase.OWNER_ID, prod.get_ownerid());
	    values.put(TableDatabase.PROD_NAME, prod.get_prodname());
	    values.put(TableDatabase.PROD_CATEGORY, prod.get_prodcat());
	    values.put(TableDatabase.PROD_SUB_CATEGORY, prod.get_subcat());
	    values.put(TableDatabase.PROD_DESCRIPTION, prod.get_proddesc());
	    values.put(TableDatabase.PROD_PHOTO, prod.get_prodphoto());
	    values.put(TableDatabase.PRICE, prod.get_price());
	    values.put(TableDatabase.PRICE_DENO, prod.get_deno());
	    values.put(TableDatabase.AVAIL_TIME, prod.get_availTime());
	    values.put(TableDatabase.AVAIL_UNITS, prod.get_availUnits());
	    values.put(TableDatabase.MIN_ORDER, prod.get_minOrder());
	    values.put(TableDatabase.ORDER_UNITS, prod.get_orderUnits());*/
	    
	    String query="update "+TableDatabase.TABLE_PRODUCTS+" set "+
	    TableDatabase.PROD_NAME+"="+prod.get_prodname()+","+TableDatabase.PROD_CATEGORY+"="+prod.get_prodcat()+
	    ","+TableDatabase.PROD_SUB_CATEGORY+"="+prod.get_subcat()+
	    TableDatabase.PROD_DESCRIPTION+"="+prod.get_proddesc()+","+TableDatabase.PROD_PHOTO+"="+prod.get_prodphoto()+
	    TableDatabase.PRICE+"="+prod.get_price()+","+TableDatabase.PRICE_DENO+"="+prod.get_deno()+
	    TableDatabase.AVAIL_TIME+"="+prod.get_availTime()+","+TableDatabase.AVAIL_UNITS+"="+prod.get_availUnits()+
	    TableDatabase.MIN_ORDER+"="+prod.get_minOrder()+","+TableDatabase.ORDER_UNITS+"="+prod.get_orderUnits()+
	    " where "+TableDatabase.PROD_ID+"="+prod.get_id();
	   Cursor cursor= database.rawQuery(query, null); 
	   cursor.moveToFirst();
	   cursor.close();
	    
	  
	     
	  }
  
  public int getProdsCount() {
	    String countQuery = "SELECT  * FROM " + TableDatabase.TABLE_PRODUCTS;
	   // SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor =database.rawQuery(countQuery, null);
	    int cnt = cursor.getCount();
	    cursor.close();
	    return cnt;
	}
  
  public void addImages(byte[] prod,long prodid) {
	 
    ContentValues values = new ContentValues();
    values.put(TableDatabase.PROD, prodid);
    values.put(TableDatabase.IMAGE, prod);
    database.insert(TableDatabase.TABLE_IMAGES , null, values);     
  }
  
  
  
  
  
  

  public void deleteProduct(Long id) {
  
   // System.out.println("Comment deleted with id: " + id);
    database.delete(TableDatabase.TABLE_PRODUCTS, TableDatabase.PROD_ID
        + " = " + id, null);
  }

  public List<objects.ProductsObject> getAllProducts() {
    List<objects.ProductsObject> products = new ArrayList<objects.ProductsObject>();

    Cursor cursor = database.query(TableDatabase.TABLE_PRODUCTS,
        null, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
    	objects.ProductsObject prod = cursorToProduct(cursor);
     products.add(prod);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return products;
  }
  
  public List<objects.ProductsObject> getFragProducts(int start) {
	    List<objects.ProductsObject> products = new ArrayList<objects.ProductsObject>();
       
	   // String query="select * from "+TableDatabase.TABLE_PRODUCTS+" where prod_id >="+start+" limit 6";
	    String query="select * from "+TableDatabase.TABLE_PRODUCTS +" limit "+start+",6";
	    
	    
	   /* Cursor cursor = database.query(TableProducts.TABLE_PRODUCTS,
	        null, "PROD_ID >="+start, null, null, null,null);*/
	    Cursor cursor = database.rawQuery(query, null);  
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	objects.ProductsObject prod = cursorToProduct(cursor);
	     products.add(prod);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return products;
	  }
  public List<objects.ProductsObject> getSupplierFragProducts(int supId,int catId,int start) {
	    List<objects.ProductsObject> products = new ArrayList<objects.ProductsObject>();
     
	   // String query="select * from "+TableDatabase.TABLE_PRODUCTS+" where prod_id >="+start+" limit 6";
	    String query="select * from "+TableDatabase.TABLE_PRODUCTS+" where "+TableDatabase.OWNER_ID+"="+supId+" and "+TableDatabase.PROD_CATEGORY+"="+catId+" limit "+start+",6";
	    
	    
	   /* Cursor cursor = database.query(TableProducts.TABLE_PRODUCTS,
	        null, "PROD_ID >="+start, null, null, null,null);*/
	    Cursor cursor = database.rawQuery(query, null);  
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	objects.ProductsObject prod = cursorToProduct(cursor);
	     products.add(prod);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return products;
	  }
  public List<objects.ProductsObject> getSupplierFragProducts(int supId,int start) {
	    List<objects.ProductsObject> products = new ArrayList<objects.ProductsObject>();
   
	   // String query="select * from "+TableDatabase.TABLE_PRODUCTS+" where prod_id >="+start+" limit 6";
	    String query="select * from "+TableDatabase.TABLE_PRODUCTS+" where "+TableDatabase.OWNER_ID+"="+supId+" limit "+start+",6";
	    
	    
	   /* Cursor cursor = database.query(TableProducts.TABLE_PRODUCTS,
	        null, "PROD_ID >="+start, null, null, null,null);*/
	    Cursor cursor = database.rawQuery(query, null);  
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	objects.ProductsObject prod = cursorToProduct(cursor);
	     products.add(prod);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return products;
	  }

  public List<objects.ProductsObject> getCatsFragProducts(int subId,int catId,int start) {
	    List<objects.ProductsObject> products = new ArrayList<objects.ProductsObject>();
   
	   // String query="select * from "+TableDatabase.TABLE_PRODUCTS+" where prod_id >="+start+" limit 6";
	    String query="select * from "+TableDatabase.TABLE_PRODUCTS+" where "+TableDatabase.PROD_CATEGORY+"="+catId+" and "+TableDatabase.PROD_SUB_CATEGORY+"="+subId+" limit "+start+",6";
	    
	    
	   /* Cursor cursor = database.query(TableProducts.TABLE_PRODUCTS,
	        null, "PROD_ID >="+start, null, null, null,null);*/
	    Cursor cursor = database.rawQuery(query, null);  
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	objects. ProductsObject prod = cursorToProduct(cursor);
	     products.add(prod);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return products;
	  }
  public List<objects.ProductsObject> getCatsFragProducts(int catId,int start) {
	    List<objects.ProductsObject> products = new ArrayList<objects.ProductsObject>();
 
	   // String query="select * from "+TableDatabase.TABLE_PRODUCTS+" where prod_id >="+start+" limit 6";
	    String query="select * from "+TableDatabase.TABLE_PRODUCTS+" where "+TableDatabase.PROD_CATEGORY+"="+catId+" limit "+start+",6";
	    
	    
	   /* Cursor cursor = database.query(TableProducts.TABLE_PRODUCTS,
	        null, "PROD_ID >="+start, null, null, null,null);*/
	    Cursor cursor = database.rawQuery(query, null);  
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	objects.ProductsObject prod = cursorToProduct(cursor);
	     products.add(prod);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return products;
	  }

  
  public int getRecordCount(){
	  //int no=0;
	  Cursor cursor = database.query(TableDatabase.TABLE_PRODUCTS,
		        null, null, null, null, null, null); 
       int i=cursor.getCount();    
	return i;  
  }
  public int getCatRecordCount(int catId){
	  //int no=0;
	  Cursor cursor = database.query(TableDatabase.TABLE_PRODUCTS,
		        null, TableDatabase.PROD_CATEGORY+"="+catId, null, null, null, null); 
       int i=cursor.getCount();    
	return i;  
  }
  public int getSubRecordCount(int catId,int subId){
	  //int no=0;
	  Cursor cursor = database.query(TableDatabase.TABLE_PRODUCTS,
		        null, TableDatabase.PROD_CATEGORY+"="+catId+" and "+TableDatabase.PROD_SUB_CATEGORY+"="+ subId, null, null, null, null); 
       int i=cursor.getCount();    
	return i;  
  }
  
  public int getSupplierRecordCount(int supId,int catId){
	  //int no=0;
	  Cursor cursor = database.query(TableDatabase.TABLE_PRODUCTS,
		        null, TableDatabase.OWNER_ID+"="+supId, null, null, null, null); 
       int i=cursor.getCount();    
	return i;  
  }
  public int getSupCatRecordCount(int supId,int catId){
	  //int no=0;
	  Cursor cursor = database.query(TableDatabase.TABLE_PRODUCTS,
		        null, TableDatabase.OWNER_ID+"="+supId+" and "+TableDatabase.PROD_CATEGORY+"="+catId, null, null, null, null); 
       int i=cursor.getCount();    
	return i;  
  }
  
	public Cursor getProdsByDesc(String desc)
	{   
		//Log.i("First nAME","Fname:"+desc);
	 String sqlQuery = "";
	 Cursor result = null;
	  
	 sqlQuery  = " SELECT _id,prod_id" + ",prod_name";
	 //sqlQuery  = " SELECT _id,fname, lname ";
	 sqlQuery += " FROM products";
	 sqlQuery += " WHERE prod_name LIKE '%" + desc + "%' ";
	
	    
	/* Cursor cursor = database.query(TableCustomers.TABLE_CUSTOMERS,
		        null, null, null, null, null, null);*/
	 
	 
	  result = database.rawQuery(sqlQuery, null);
	if(result!=null)
	{
		Log.i("something","samooooooo");
		result.moveToFirst();
	}
	 //Log.i("First nAME","Fname:"+result.getString(0));
	
	 return result;
	}
  
  
  
  public List<objects.ProductsObject> getSimilarProducts(Integer prodId) {
	    List<objects.ProductsObject> products = new ArrayList<objects.ProductsObject>();

	    Cursor cursor = database.query(TableDatabase.TABLE_PRODUCTS,
	        null, TableDatabase.PROD_SUB_CATEGORY+"="+prodId, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	objects.ProductsObject prod = cursorToProduct(cursor);
	     products.add(prod);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return products;
	  }
  
  
  public objects.ProductsObject getProduct(Integer id) {
	    
	    Cursor cursor = database.query(TableDatabase.TABLE_PRODUCTS,
	        null, TableDatabase.PROD_ID+"="+id, null, null, null, null);
	      cursor.moveToFirst();
	      objects.ProductsObject prod = cursorToProduct(cursor);
	      
	     // Log.i("prod", prod);
	    		  cursor.close();
	    // make sure to close the cursor
	   
	    return prod;
	  }
   

  private objects.ProductsObject cursorToProduct(Cursor cursor) {
	  objects.ProductsObject prod = new objects.ProductsObject();
      prod.set_id(cursor.getLong(1));
      prod.set_ownerid(cursor.getLong(2));
      prod.set_prodname(cursor.getString(3));
      prod.set_prodcat(cursor.getLong(4));
     Log.i("db result","result"+cursor.getLong(0));
      prod.set_subcat(cursor.getLong(5));
      prod.set_desc(cursor.getString(6));
      prod.set_prodphoto(cursor.getString(7));
      prod.set_price(cursor.getFloat(8));
      prod.set_deno(cursor.getString(9));
      prod.set_availTime(cursor.getLong(10));
      prod.set_availUnits(cursor.getString(11));
      prod.set_minOrder(cursor.getLong(12));
      prod.set_orderUnits(cursor.getString(13));
      
    return prod;
  }
} 