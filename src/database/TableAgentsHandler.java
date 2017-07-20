package database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TableAgentsHandler {
	private SQLiteDatabase database;
	private TableDatabase  agents;
	
	
	public TableAgentsHandler(Context context) {
	   agents = new TableDatabase(context);
	    	   
	  }
	public void open() throws SQLException {
	    database = agents.getWritableDatabase();
	    
	  }
	public void close() {
		 agents.close();
		 
	  }
	public void addAgent(objects.ObjectAgents agent)
	 {
		 ContentValues values = new ContentValues();
		 
		    values.put(TableDatabase.AG_NAME,agent.get_name());
		    values.put(TableDatabase.AG_ID,agent.get_id());
		    values.put(TableDatabase.AG_CODE,agent.get_pass());
		  
		    database.insert(TableDatabase.TABLE_AGENTS, null, values);
	 }
	
	public void updateAgent(objects.ObjectAgents agent)
	 {
		
		 String query="update "+TableDatabase.TABLE_AGENTS+" set "+TableDatabase.AG_NAME+"="+agent.get_name()+","+TableDatabase.AG_CODE+"="+agent.get_pass()+" where "+TableDatabase.AG_ID+"="+agent.get_id();
			Cursor cursor= database.rawQuery(query, null); 
			cursor.moveToFirst();
			cursor.close();
		/* ContentValues values = new ContentValues();
		 
		    values.put(TableDatabase.AG_NAME,agent.get_name());
		    values.put(TableDatabase.AG_ID,agent.get_id());
		    values.put(TableDatabase.AG_CODE,agent.get_pass());
		  
		    database.insert(TableAgents.TABLE_AGENTS, null, values);*/
	 }
	
	
	public objects.ObjectAgents getAgent(String agCode){
		List<objects.ObjectAgents> sups=new ArrayList<objects.ObjectAgents>();
		
		/*String query="a.*,b.PROD_NAME,b.PROD_PHOTO from "+TableCart.TABLE_CART+" a join "+TableProducts.TABLE_PRODUCTS+
				" b on a.PRODUCT_ID=b.ID where a.CART_ID="+cartId;*/
		
		
		Cursor cursor = database.query(TableDatabase.TABLE_AGENTS ,
		        null, "code='"+agCode+"'", null, null, null, null);
		       // Cursor cursor = database.rawQuery(query, null);

		    cursor.moveToFirst();
		    //while (!cursor.isAfterLast()) {
		    objects.ObjectAgents ag = cursorToAgent(cursor);
		       //  sups.add(sup);
		      //cursor.moveToNext();
		   // }
		    // make sure to close the cursor
		    cursor.close();
		
		
		return ag;
		
	}
	public String getAgentName(String agCode){
		List<objects.ObjectAgents> sups=new ArrayList<objects.ObjectAgents>();
		
		/*String query="a.*,b.PROD_NAME,b.PROD_PHOTO from "+TableCart.TABLE_CART+" a join "+TableProducts.TABLE_PRODUCTS+
				" b on a.PRODUCT_ID=b.ID where a.CART_ID="+cartId;*/
		
		
		Cursor cursor = database.query(TableDatabase.TABLE_AGENTS ,
		        null, "code='"+agCode+"'", null, null, null, null);
		       // Cursor cursor = database.rawQuery(query, null);
		String name="";
             if(cursor.getCount()<1){
            	 name="";}else{
            		 cursor.moveToFirst();	 
	                name=cursor.getString(2);
	                cursor.close();
            	 }
		return name;
		
	}
	
	
	
	
	private objects.ObjectAgents cursorToAgent(Cursor cursor) {
		objects.ObjectAgents agt = new objects.ObjectAgents();
		  agt.set_id(cursor.getLong(1));
		  agt.set_name(cursor.getString(2));
		  agt.set_pass(cursor.getString(3));
		  return agt;
		}
	

}
