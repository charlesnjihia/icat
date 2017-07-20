package database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TableCustomersHandler {
	
	
		private SQLiteDatabase database;
		private TableDatabase  customers;
		
		
		public TableCustomersHandler(Context context) {
		   customers = new TableDatabase(context);
		    	   
		  }
		public void open() throws SQLException {
		    database = customers.getWritableDatabase();
		    
		  }
		public void close() {
			 customers.close();
			 
		  }
		public long addCustomer(objects.ObjectCustomers cust)
		 {
			 ContentValues values = new ContentValues();
			 
			    values.put(TableDatabase.NAME,cust.get_name());
			    values.put(TableDatabase.SURNAME,cust.get_surname());	
			    values.put(TableDatabase.PHONE,cust.get_mobile());
			    values.put(TableDatabase.EMAIL,cust.get_email());
			    values.put(TableDatabase.LOCATION,cust.get_location());
			    values.put(TableDatabase.SENT,cust.get_issent());
			 long id=  database.insert(TableDatabase.TABLE_CUSTOMERS, null, values);
			 return id;
		 }
		public List<objects.ObjectCustomers> getAllCustomers() {
		    List<objects.ObjectCustomers> customers = new ArrayList<objects.ObjectCustomers>();

		    Cursor cursor = database.query(TableDatabase.TABLE_CUSTOMERS,
		        null, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	objects.ObjectCustomers cust = cursorToCustomer(cursor);
		     customers.add(cust);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return customers;
		  }	
		
		public List<objects.ObjectCustomers> getUnsentCustomers() {
		    List<objects.ObjectCustomers> customers = new ArrayList<objects.ObjectCustomers>();

		    Cursor cursor = database.query(TableDatabase.TABLE_CUSTOMERS,
		        null, "cust_sent=0", null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	objects.ObjectCustomers cust = cursorToCustomer(cursor);
		     customers.add(cust);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return customers;
		  }	
		
		public void updateUnsentCustomers(){
			   
		    String query="update "+TableDatabase.TABLE_CUSTOMERS+" set "+
		    TableDatabase.SENT+"= 1  where "+TableDatabase.SENT+"=0";
		    Cursor cursor= database.rawQuery(query, null); 
			   cursor.moveToFirst();
			   cursor.close();
			
			
		}
		
		
		
		
		public Cursor getCustomersByDesc(String desc)
		{   
			//Log.i("First nAME","Fname:"+desc);
		 String sqlQuery = "";
		 Cursor result = null;
		  
		 sqlQuery  = " SELECT _id" + ",fname,lname,phone ";
		 //sqlQuery  = " SELECT _id,fname, lname ";
		 sqlQuery += " FROM customer_infor";
		 sqlQuery += " WHERE fname LIKE '%" + desc + "%' ";
		
		    
		/* Cursor cursor = database.query(TableCustomers.TABLE_CUSTOMERS,
			        null, null, null, null, null, null);*/
		 
		 
		  result = database.rawQuery(sqlQuery, null);
		if(result!=null)
		{
			//Log.i("something","samooooooo");
			result.moveToFirst();
		}
		 //Log.i("First nAME","Fname:"+result.getString(0));
		
		 return result;
		}
		 
		
		
		
		
		public objects.ObjectCustomers getCustomers(long id) {
			objects.ObjectCustomers customer= new objects.ObjectCustomers();

		    Cursor cursor = database.query(TableDatabase.TABLE_CUSTOMERS,
		        null, "_id="+id, null, null, null, null);

		    cursor.moveToFirst();
		    
		       customer = cursorToCustomer(cursor);
		     
		    //  cursor.moveToNext();
		  
		    // make sure to close the cursor
		    cursor.close();
		    return customer;
		  }	
		
		
		
		
		
		private objects.ObjectCustomers cursorToCustomer(Cursor cursor) {
			objects.ObjectCustomers cust = new objects.ObjectCustomers();
		    
			cust.set_id(cursor.getLong(0));
			cust.set_name(cursor.getString(1));
			cust.set_surname(cursor.getString(2));
			cust.set_mobile(cursor.getString(3));
			cust.set_email(cursor.getString(4));
			cust.set_location(cursor.getString(5));
			      
		    return cust;
		  }
		
		
		

}
