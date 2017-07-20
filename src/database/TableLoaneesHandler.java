package database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TableLoaneesHandler {
	private SQLiteDatabase database;
	private TableDatabase  agents;
	
	
	public TableLoaneesHandler(Context context) {
	   agents = new TableDatabase(context);
	    	   
	  }
	public void open() throws SQLException {
	    database = agents.getWritableDatabase();
	    
	  }
	public void close() {
		 agents.close();
		 
	  }
	public void addLoanee(objects.ObjectLoanClients client,boolean status)
	 {
		
		int sent=0;
		if(status==true){
			sent=1;
			
		}		
		 ContentValues values = new ContentValues();
		 
		    values.put(TableDatabase.FNAME,client.getClName());
		    values.put(TableDatabase.SNAME,client.getClmName());
		    values.put(TableDatabase.LNAME,client.getCllName());
		    values.put(TableDatabase.IDNO,client.getClId());
		    values.put(TableDatabase.AGE,client.getClAge());
		    values.put(TableDatabase.GENDER,client.getClGender());
		    values.put(TableDatabase.FONE,client.getClPhone());
		    values.put(TableDatabase.EMAILL,"email");
		    values.put(TableDatabase.LOCATIONN,client.getClLoc());		    
		    values.put(TableDatabase.OCCUPATION,client.getClOcc());
		    values.put(TableDatabase.EDUCATION,client.getClEd());
		    values.put(TableDatabase.DEPENDANTS,client.getClDep());
		    values.put(TableDatabase.MARSTATUS,client.getClMar());
		    values.put(TableDatabase.LOAN,client.getClLoan());		    
		    values.put(TableDatabase.INCOME,client.getClIncome());
		    values.put(TableDatabase.COSTS,client.getClCosts());		    
		    values.put(TableDatabase.INITFUND,client.getClInit());
		    values.put(TableDatabase.OWNERS,client.getClOwners());
		    values.put(TableDatabase.REGDATE,client.getClRegdate());
		    values.put(TableDatabase.LOFFICER,client.getClLOfficer());
		    values.put(TableDatabase.INFSENT,sent);
		    
		    
		  Log.i("about to add to db","Added to database");
		    database.insert(TableDatabase.TABLE_LOANCLIENTS, null, values);
	 }
	
	public void updateSent()
	 {
		
		 String query="update "+TableDatabase.TABLE_LOANCLIENTS+" set sent=1 where sent=0";
			Cursor cursor= database.rawQuery(query, null); 
			cursor.moveToFirst();
			cursor.close();
		/* ContentValues values = new ContentValues();
		 
		    values.put(TableDatabase.AG_NAME,agent.get_name());
		    values.put(TableDatabase.AG_ID,agent.get_id());
		    values.put(TableDatabase.AG_CODE,agent.get_pass());
		  
		    database.insert(TableAgents.TABLE_AGENTS, null, values);*/
	 }
	
	
	public List<objects.ObjectLoanClients> getLoanees(String agCode){
		List<objects.ObjectLoanClients> loanees=new ArrayList<objects.ObjectLoanClients>();
		
		/*String query="a.*,b.PROD_NAME,b.PROD_PHOTO from "+TableCart.TABLE_CART+" a join "+TableProducts.TABLE_PRODUCTS+
				" b on a.PRODUCT_ID=b.ID where a.CART_ID="+cartId;*/
		
		
		Cursor cursor = database.query(TableDatabase.TABLE_LOANCLIENTS ,
		        null, null, null, null, null, null);
		       // Cursor cursor = database.rawQuery(query, null);

		    cursor.moveToFirst();
		   while (!cursor.isAfterLast()) {
		    objects.ObjectLoanClients loanee = cursorToLoanee(cursor);
		    loanees.add(loanee);
		    cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		
		
		return loanees;
		
	}
	
	
	public List<objects.ObjectLoanClients> getUnsentLoanees(){
		List<objects.ObjectLoanClients> loanees=new ArrayList<objects.ObjectLoanClients>();
		
		/*String query="a.*,b.PROD_NAME,b.PROD_PHOTO from "+TableCart.TABLE_CART+" a join "+TableProducts.TABLE_PRODUCTS+
				" b on a.PRODUCT_ID=b.ID where a.CART_ID="+cartId;*/
		
		
		Cursor cursor = database.query(TableDatabase.TABLE_LOANCLIENTS ,
		        null, "sent=0", null, null, null, null);
		       // Cursor cursor = database.rawQuery(query, null);

		    cursor.moveToFirst();
		   while (!cursor.isAfterLast()) {
		    objects.ObjectLoanClients loanee = cursorToLoanee(cursor);
		    loanees.add(loanee);
		    cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		
		
		return loanees;
		
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
	
	
	
	
	private objects.ObjectLoanClients cursorToLoanee(Cursor cursor) {
		objects.ObjectLoanClients cl = new objects.ObjectLoanClients();
		  cl.setClName(cursor.getString(1));
		 cl.setClmName(cursor.getString(2));
		  cl.setCllName(cursor.getString(3));
		  cl.setClId(cursor.getString(4));
		  cl.setClAge(cursor.getInt(5));
		  cl.setClGender(cursor.getString(6));
		  cl.setClPhone(cursor.getString(7));
		  cl.setClEmail("email");
		  cl.setClLoc(cursor.getString(9));
		  cl.setClOcc(cursor.getString(10));
		  cl.setClEd(cursor.getString(11));
		  cl.setClDep(cursor.getInt(12));
		  cl.setClMar(cursor.getString(13));
		  cl.setClLoan(cursor.getFloat(14));
		  cl.setClIncome(cursor.getFloat(15));
		  cl.setClCosts(cursor.getFloat(16));
		  cl.setClInit(cursor.getString(17));
		  cl.setClOwners(cursor.getString(18));
		  cl.setClRegdate(cursor.getString(19));
		  cl.setClLOfficer(cursor.getString(20));
		  return cl;
		}
	

}
