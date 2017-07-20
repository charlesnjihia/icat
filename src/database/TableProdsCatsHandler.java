package database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TableProdsCatsHandler {
	private SQLiteDatabase database;
	private TableDatabase  prodCats ;
	//private TableProdsSubCats  subCats ;
	
	
	
	public TableProdsCatsHandler(Context context) {
	    prodCats = new TableDatabase(context);
	    //subCats = new TableProdsSubCats(context);
	  }
	
	 public void open() throws SQLException {
		    database = prodCats.getWritableDatabase();
		  //  database1 = subCats.getWritableDatabase();
		  }

     public void close() {
			  prodCats.close();
			 // subCats.close();
		  }
 public long createCategories(objects.ObjectProductCats prodcat)
 {
	 ContentValues values = new ContentValues();
	    values.put(TableDatabase.CATEGORY_ID, prodcat.get_id());
	    values.put(TableDatabase.CAT_NAME, prodcat.get_name());
	    values.put(TableDatabase.CAT_PHOTO, prodcat.get_photo());
	    values.put(TableDatabase.LIGHT_CODE, prodcat.getLight());
	    values.put(TableDatabase.DARK_CODE, prodcat.getDark());
	    values.put(TableDatabase.CAT_PHOTO, prodcat.get_photo());
	   long i= database.insert(TableDatabase.TABLE_PROD_CATEGORIES , null, values);
	    
	    Log.i("attempt to add cats",""+prodcat.get_name());
	    return  i;
 }
 
 public void updateCategories(objects.ObjectProductCats prodcat)
 {
	 
	 String query="update "+TableDatabase.TABLE_PROD_CATEGORIES+" set "+TableDatabase.CAT_NAME+"="+prodcat.get_name()+","+TableDatabase.CAT_PHOTO+"="+prodcat.get_photo()+" where "+TableDatabase.CATEGORY_ID+"="+prodcat.get_id();
		Cursor cursor= database.rawQuery(query, null); 
		cursor.moveToFirst();
		cursor.close();
	 
	 
	/* ContentValues values = new ContentValues();
	    values.put(TableDatabase.CATEGORY_ID, prodcat.get_id());
	    values.put(TableDatabase.CAT_NAME, prodcat.get_name());
	    values.put(TableDatabase.CAT_PHOTO, prodcat.get_photo());
	   long i= database.insert(TableDatabase.TABLE_PROD_CATEGORIES , null, values);
	    
	    Log.i("attempt to add cats",""+prodcat.get_name());
	    return  i;*/
 }
 

 
 
 public void updateSubCategories(objects.ObjectSubCat prodSub)
 {
	 
	 String query="update "+TableDatabase.TABLE_PROD_SUB_CATS+" set "+TableDatabase.SUB_NAME+"="+prodSub.get_subName()+","+TableDatabase.CAT_ID+"="+prodSub.get_catId()+" where "+TableDatabase.SUBCAT_ID+"="+prodSub.get_subid();
		Cursor cursor= database.rawQuery(query, null); 
		cursor.moveToFirst();
		cursor.close();
	 
	 
/*	 ContentValues values = new ContentValues();
	    
	    values.put(TableDatabase.CAT_ID, prodSub.get_catId());
	    values.put(TableDatabase.SUBCAT_ID, prodSub.get_subid());
	    values.put(TableDatabase.SUB_NAME, prodSub.get_subName());
	    database.insert(TableDatabase.TABLE_PROD_SUB_CATS, null, values);*/
 }
 
 public void createSubCategories(objects.ObjectSubCat prodSub)
 {
	 ContentValues values = new ContentValues();
	    
	    values.put(TableDatabase.CAT_ID, prodSub.get_catId());
	    values.put(TableDatabase.SUBCAT_ID, prodSub.get_subid());
	    values.put(TableDatabase.SUB_NAME, prodSub.get_subName());
	    database.insert(TableDatabase.TABLE_PROD_SUB_CATS, null, values);
 }
 
 
 public void addSupplierCats(objects.ObjectSupCat supcat)
 {
	 ContentValues values = new ContentValues();
	    
	    values.put(TableDatabase.CATEGORY_ID, supcat.get_catid());
	    values.put(TableDatabase.SUP_ID, supcat.get_supid());
	   
	    database.insert(TableDatabase.TABLE_SUP_CATS, null, values);
 }
 public List<objects.ObjectSupCat> getSupplierCats(int supId){
	 List<objects.ObjectSupCat> supCats=new ArrayList<objects.ObjectSupCat>();
	 
	 String query="Select a.sup_id,a.cat_id,b.cat_name from supplier_cats a left join prod_categories b on a.cat_id=b.cat_id where a.sup_id="+supId;
	 
	 Cursor cursor = database.rawQuery(query, null);  

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	objects.ObjectSupCat cat = cursorToSupCats(cursor);
	       supCats.add(cat);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	 
	 
	 
	 return supCats;
	 
 }
 
 
	
 public void deleteSubs(){
		 
	 database.delete(TableDatabase.TABLE_PROD_SUB_CATS, null,null);  
	 
 }
 
 
 
public List<objects.ObjectProductCats>	getAllCats(){
	List<objects.ObjectProductCats> cats=new ArrayList<objects.ObjectProductCats>();
	Cursor cursor = database.query(TableDatabase.TABLE_PROD_CATEGORIES,
	        null, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	objects.ObjectProductCats cat = cursorToCategory(cursor);
	         cats.add(cat);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	
	
	return cats;
	
}
public objects.ObjectProductCats getCat(int catId){
	Log.i("try fetching category","try fetching category");
	objects.ObjectProductCats cat =new objects.ObjectProductCats();
	Cursor cursor = database.query(TableDatabase.TABLE_PROD_CATEGORIES,
	        null, "cat_id="+catId, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	 cat = cursorToCategory(cursor);
	        
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	
	Log.i("object obtained",""+cat.get_name());
	return cat;
	
}
public objects.ObjectSubCat getSubCat(int subId){
	Log.i("try fetching category","try fetching category");
	objects.ObjectSubCat sub =new objects.ObjectSubCat();
	Cursor cursor = database.query(TableDatabase.TABLE_PROD_SUB_CATS,
	        null, "subsec_id="+subId, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	 sub = cursorToSubCategory(cursor);	        
	         cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	
	//Log.i("object obtained",""+cat.get_name());
	return sub;
	
}


public List<objects.ObjectSubCat> getSubCats(int catId){
	List<objects.ObjectSubCat> subCats=new ArrayList<objects.ObjectSubCat>();
	Cursor cursor = database.query(TableDatabase.TABLE_PROD_SUB_CATS,
	        null, "sec_id="+catId, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	objects.ObjectSubCat subCat = cursorToSubCategory(cursor);
	    	subCats.add(subCat);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	
	
	return subCats;
	
}
private objects.ObjectProductCats cursorToCategory(Cursor cursor) {
	objects.ObjectProductCats cat = new objects.ObjectProductCats();
  cat.set_id(cursor.getLong(1));
  cat.set_name(cursor.getString(2));
  cat.set_photo(cursor.getString(3));
  cat.setLight(cursor.getString(4));
  cat.setDark(cursor.getString(5));
        
  return cat;
}
 
private objects.ObjectSubCat cursorToSubCategory(Cursor cursor) {
	objects.ObjectSubCat subcat = new objects.ObjectSubCat();
 
	  subcat.set_catId(cursor.getLong(1));
	  subcat.set_subid(cursor.getLong(2));
	  subcat.set_subName(cursor.getString(3));
    
    
  return subcat;
}
 

private objects.ObjectSupCat cursorToSupCats(Cursor cursor) {
	objects.ObjectSupCat supcat=new  objects.ObjectSupCat();
	 supcat.set_supid(cursor.getInt(0));
	 supcat.set_catid(cursor.getInt(1));
	 supcat.set_catName(cursor.getString(2));
	 
        
  return supcat;
}
 	

	

}
