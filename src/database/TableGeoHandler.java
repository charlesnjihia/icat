package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TableGeoHandler {
	private SQLiteDatabase database;
	private TableDatabase  geo ;
	
	
	public TableGeoHandler(Context context) {
	   geo = new TableDatabase(context);
	    	   
	  }
	public void open() throws SQLException {
	    database = geo.getWritableDatabase();
	    
	  }
	public void close() {
		 geo.close();
		 
	  }
	public void addGeoCode(objects.ObjectGeocodes gee)
	 {
		 ContentValues values = new ContentValues();	    
		 values.put(TableDatabase.IPRO_ID,gee.get_code());	
		 values.put(TableDatabase.GEO_NAME,gee.get_name());	
		 database.insert(TableDatabase.TABLE_GEO_CODES, null, values);
	 }

}
