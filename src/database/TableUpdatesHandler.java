package database;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TableUpdatesHandler {
	
	 private SQLiteDatabase database;
	 private TableDatabase dbHelper;
	  
	  
 public TableUpdatesHandler(Context context) {
		    dbHelper = new TableDatabase(context);
		  }
 public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }
	  
public void addInitialVals(objects.ObjectUpdate objectUpdate){
	Log.i("Inserting things","things");
	    ContentValues values = new ContentValues();
	    values.put(TableDatabase.TABLE_NAME, objectUpdate.getTableName());
	    values.put(TableDatabase.LAST_ID, objectUpdate.getLastId());
	    values.put(TableDatabase.LAST_DATE, "NULL");
	    database.insert(TableDatabase.TABLE_UPDATES , null, values);
}

@SuppressLint("SimpleDateFormat")
public void updateTableDate(String tableName){
	
	Long date=System.currentTimeMillis();
    Date tDate=new Date(date);
   	String todaydate = new SimpleDateFormat("yyyy-MM-dd").format(tDate);
	
	  
String query="update "+TableDatabase.TABLE_UPDATES+" set "+TableDatabase.LAST_DATE+"='"+todaydate+"' where "+TableDatabase.TABLE_NAME+"='"+tableName+"'";
Cursor cursor= database.rawQuery(query, null); 
cursor.moveToFirst();
cursor.close();
 
 Log.i("Date update done for table",""+tableName+" with value "+todaydate);
}

@SuppressLint("SimpleDateFormat")
public void updateTableDate(){
	Log.i("The whole issue is here","HERE");
   Long date=System.currentTimeMillis();
   Date tDate=new Date(date);
   String todaydate = new SimpleDateFormat("yyyy-MM-dd").format(tDate);
   
   //Log.i("Gotten date",""+todaydate);
   
 //  ContentValues values = new ContentValues();
  // values.put(TableDatabase.LAST_DATE, todaydate);
  	  
String query="update "+TableDatabase.TABLE_UPDATES+" set "+TableDatabase.LAST_DATE+"= '"+todaydate+"'";
database.execSQL(query);
/*Cursor cursor= database.rawQuery(query, null); 
cursor.moveToFirst();
cursor.close();*/
 
// Log.i("Date update done for all tables  with value "+todaydate,"");
}





public void updateTable(objects.ObjectUpdate update){
	  
String query="update "+TableDatabase.TABLE_UPDATES+" set "+TableDatabase.LAST_ID+"="+update.getLastId()+" where "+TableDatabase.TABLE_NAME+"='"+update.getTableName()+"'";
Cursor cursor= database.rawQuery(query, null); 
cursor.moveToFirst();
cursor.close();
 
 Log.i("update done for table",""+update.getTableName()+" with value "+update.getLastId());
}

public long getTablelastVal(String tableName){
	 long lastVal = 0;
	String query="select * from "+TableDatabase.TABLE_UPDATES+"  where "+TableDatabase.TABLE_NAME+"='"+tableName+"'";
	 Cursor cursor = database.rawQuery(query, null);  
	 cursor.moveToFirst();	    
	    lastVal=cursor.getLong(2);	
	    // make sure to close the cursor
	    cursor.close();
	    
	    return lastVal;
	
}

public objects.ObjectUpdate getTableUpdateVals(String tableName){
	objects.ObjectUpdate update=new objects.ObjectUpdate();
	String query="select * from "+TableDatabase.TABLE_UPDATES+"  where "+TableDatabase.TABLE_NAME+"='"+tableName+"'";
	 Cursor cursor = database.rawQuery(query, null);  
	 cursor.moveToFirst();	    
	    update= cursorToUpdate(cursor);	
	    // make sure to close the cursor
	    cursor.close();
	 return update;   
	
}

public String getTablelastDate(String tableName){
	 String lastDate = "";
	 
	String query="select * from "+TableDatabase.TABLE_UPDATES+"  where "+TableDatabase.TABLE_NAME+"='"+tableName+"'";
	 Cursor cursor = database.rawQuery(query, null);  
	 cursor.moveToFirst();	    
	    lastDate=cursor.getString(3);	
	    // make sure to close the cursor
	    cursor.close();
	    
	    return lastDate;
	
}

public int getAppStatus(){
	String query="select * from "+TableDatabase.TABLE_APP_STATUS+"";//  where "+TableDatabase.TABLE_NAME+"='"+tableName+"'";
	 Cursor cursor = database.rawQuery(query, null); 
	 
	 int status=cursor.getCount();
	 
	 return status;
	
}


private objects.ObjectUpdate cursorToUpdate(Cursor cursor) {
	objects.ObjectUpdate upt = new objects.ObjectUpdate();
	  upt.setTableName(cursor.getString(1));
	  upt.setLastId(cursor.getLong(2));
	  upt.setDate(cursor.getString(3));
	  return upt;
	}		



}
