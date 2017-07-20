package database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TablePhotosHandler {
	
	private SQLiteDatabase database;
	private TableDatabase  photo ;
	
	
	public TablePhotosHandler(Context context) {
	   photo = new TableDatabase(context);
	    	   
	  }
	public void open() throws SQLException {
	    database =photo.getWritableDatabase();
	    
	  }
	public void close() {
		photo.close();
		 
	  }
	public void addPhoto(objects.ObjectPhotos foto)
	 {
		 ContentValues values = new ContentValues();	    
		 values.put(TableDatabase.PROD,foto.get_id());	
		 values.put(TableDatabase.IMAGE,foto.get_photo());	
		 database.insert(TableDatabase.TABLE_IMAGES, null, values);
	 }
	
	public List<objects.ObjectPhotos> getOthers(Integer prodId){
		List<objects.ObjectPhotos> images=new ArrayList<objects.ObjectPhotos>();
		
		 Cursor cursor = database.query(TableDatabase.TABLE_IMAGES,
			        null, TableDatabase.PROD+"="+prodId, null, null, null, null);
		 
		  cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	objects.ObjectPhotos image = cursorToImage(cursor);
		    images.add(image);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		 
		 return images;
		
	}

	private objects.ObjectPhotos cursorToImage(Cursor cursor) {
		objects.ObjectPhotos image= new objects.ObjectPhotos();
	      image.set_id(cursor.getLong(1));
	      image.set_photo(cursor.getString(2));
	      return image;
	}


}
	



