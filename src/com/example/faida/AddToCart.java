package com.example.faida;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AddToCart extends Activity {
	
	database.TableCustomersHandler customers;
	database.TableCartHandler cart;
	database.TableSavedCartsHandler svCart;
	SharedData data= new SharedData();
	AutoCompleteTextView name;
	TextView mnumber,chekker;
	EditText quant;
	int customerid=-2; 
	Button ok,back,newclient;
	
	 static Long prodId;
	 static float prodCost;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_user);
		prodId=getIntent().getLongExtra("product",0);
	    prodCost=getIntent().getFloatExtra("price",0);
	    
	    Log.i("Product ID value is this"," "+prodId);
		
		customers=new database.TableCustomersHandler(this);
		customers.open();
		cart=new database.TableCartHandler(this);
	   // cart.open();
	    svCart=new database.TableSavedCartsHandler(this);
	   
		
		 name=(AutoCompleteTextView)findViewById(R.id.name);
		 mnumber=(TextView)findViewById(R.id.fone);
		 chekker=(TextView)findViewById(R.id.chekker);
		 
		 SearchCustomer customerAdpter=new SearchCustomer(this);
	       name.setAdapter(customerAdpter);
	       name.setThreshold(2);
	       name.setOnItemClickListener(customerAdpter);
	       
	       ok=(Button)findViewById(R.id.ok);
	       back=(Button)findViewById(R.id.cancel);
	       newclient=(Button)findViewById(R.id.adduser);
	       quant=(EditText)findViewById(R.id.qty);
	       
	       back.setOnClickListener(new OnClickListener() {
	     	   
	 			public void onClick(View arg0) {
	 				
	 				onBackPressed();
	 				
	 				/* Intent det = new Intent(ProductDetails.this, ProductPreview.class);
	 				 det.putExtra("prodId",prodId);
		       		  startActivity(det);*/
	 				

	 			}});
	       
	       
	       ok.setOnClickListener(new OnClickListener() {
	     	   
	 			public void onClick(View arg0) {
	 				String qty=quant.getText().toString();
	 				
	 				if(customerid==-2){
	 					
	 					chekker.setText("Add a Client!!");
	 				}else if(qty.length()<1){
	 					chekker.setText("Indicate Quantity!!");
	 				}else{
	 					
	 					int quantity=Integer.parseInt(qty);
	 					
	 					 String[] letters={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	 	    	 		
		    	    	 addCustIdToShare(customerid);
		    			 String cartid="";
		    			for(int i=1;i<=5;i++){
		    				int x=(int)(Math.random() * 25) ;
		    				String c=letters[x];
		    				cartid+=c;
		    			}
		    			final String cartId=cartid;
		    			
		    		   addCartIdToShare(cartId); 
		    		
		    		//add the product to the cart
		    		objects.ObjectCart cartOb=new objects.ObjectCart();
		    		cartOb.set_cartId(cartid);
		    		cartOb.set_id(0);
		    		cartOb.set_prodId(prodId);
		    		cartOb.set_quantity(quantity);
		    		cartOb.set_units("units");
		    		cart.open();
		    		cart.addtoCart(cartOb);	
		    		cart.close();
		    		
		    		
	 					
	 			//prompt user proceed
		    		
		    		// retrieve display dimensions
		    		Rect displayRectangle = new Rect();
		    		Window window = AddToCart.this.getWindow();
		    		window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
		    		
		        //createdialog
		    		int width=(int)(displayRectangle.width() * 0.7f);
		    		int height =(int)(displayRectangle.height() * 0.6f);
		    		
		    		 //start of the dialog
		    		final Dialog d =new Dialog(AddToCart.this, R.style.CustomDialog);
		    		d.setContentView(R.layout.check_out);
		    		RelativeLayout toprods,check;
		    		toprods=(RelativeLayout)d.findViewById(R.id.back);
		    		check=(RelativeLayout)d.findViewById(R.id.check);
		    		
		    		  toprods.setOnClickListener(new OnClickListener() {
		   	     	   
		  	 			public void onClick(View arg0) {
		  	 				
		  	 				Intent prods = new Intent(AddToCart.this, Products.class);
							//intent
							startActivity(prods);
		  	 				

		  	 			}});
		    		  check.setOnClickListener(new OnClickListener() {
		   	     	   
		  	 			public void onClick(View arg0) {
		  	 			
		  	 				objects.ObjectSavedCart svCartOb=new objects.ObjectSavedCart();
	    					svCartOb.set_cartId(cartId);
	    					svCartOb.set_custId(customerid);
	    					svCartOb.set_date("11-12-13");
	    					
	    					 svCart.open();
	    					svCart.addtoSvCart(svCartOb);
	    					svCart.close();
	    					data.nullifyCustId("customer",-2,AddToCart.this);
	    					
	    					
	    					Intent savedCarts = new Intent(AddToCart.this, SavedCarts.class);
	    					

	    					//intent
	    					startActivity(savedCarts);	
		  	 				
		  	 				
		  	 				
		  	 				
		  	 				
		  	 				

		  	 			}});
		    		// show it
		    			d.getWindow().setLayout(width, height);
		    			d.show(); 
		    		
		    	//end prompt
	 					
	 					
	 					
	 				}
	 				

	 			}});
	       
	       
	       newclient.setOnClickListener(new OnClickListener() {
	     	   
	 			public void onClick(View arg0) {
	 				 regUser();

	 			}});
		
		
}
	
	public void regUser(){
		
		
		// retrieve display dimensions
		Rect displayRectangle = new Rect();
		Window window = AddToCart.this.getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
		
    //createdialog
		int width=(int)(displayRectangle.width() * 0.7f);
		int height =(int)(displayRectangle.height() * 0.6f);
		
		 //start of the dialog
		final Dialog d =new Dialog(AddToCart.this, R.style.CustomDialog);
		d.setContentView(R.layout.regg_user);
		final EditText fname,mname,lname,email,id,fone;
		final TextView verify;
		Button add,cancel;
		fname=(EditText)d.findViewById(R.id.fname);
		mname=(EditText)d.findViewById(R.id.mname);
		lname=(EditText)d.findViewById(R.id.lname);
		email=(EditText)d.findViewById(R.id.email);
		verify=(TextView)d.findViewById(R.id.very);
		id=(EditText)d.findViewById(R.id.id);
		fone=(EditText)d.findViewById(R.id.fone);		
		add=(Button)d.findViewById(R.id.adduser);
		cancel=(Button)d.findViewById(R.id.cancel);
		
		add.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				String fName=fname.getText().toString();
 				String mName=mname.getText().toString();
 				String lName=lname.getText().toString();
 				String emaill=email.getText().toString();
 				String idd=id.getText().toString();
 				String phone=fone.getText().toString();
 				
 				
 				if((fName.length()<1 && lName.length()<1 && lName.length()<1)||emaill.length()<1||idd.length()<1||phone.length()<1){
 					verify.setText("Provide all necessary details!");
 				}else{
 					
 					objects.ObjectCustomers cust=new objects.ObjectCustomers();
 					cust.set_id(9);
 					cust.set_name(fName);
 					cust.set_surname(mName);
 					cust.set_mobile(phone);
 					cust.set_location("Kinangop");
 					cust.set_email(emaill);
 				    cust.set_issent(0);
 				    
 				    customers.open();
 					final int custId=(int) customers.addCustomer(cust);
 					customers.close();
 					//return custId;
 					 customerid=custId;
 					
 					name.setText(fName+" "+mName);
 					mnumber.setText(phone);
 					d.cancel();
 				}
 				
 				
 			}});
		cancel.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				d.cancel();
 				
 			}});
		
		
		
		// show it
		d.getWindow().setLayout(width, height);
		d.show(); 
	}
	
	
private void addCustIdToShare(long id){
		
		data.setCustId("cust", id, this);
		
	}
	private void addCartIdToShare(String cartid){
		
		data.setCart("cart", cartid, this);
		
	}
	
	
	
	
	class SearchCustomer extends CursorAdapter implements OnItemClickListener{
		 

		@SuppressWarnings("deprecation")
		public SearchCustomer(Context context) {
			
			super(AddToCart.this, null);
			// TODO Auto-generated constructor stub
		}
		@Override
	    public Cursor runQueryOnBackgroundThread(CharSequence constraint)
	    {
			customers.open();
	        Cursor currentCursor = null;
	        
	        if (getFilterQueryProvider() != null)
	        {
	            return getFilterQueryProvider().runQuery(constraint);
	        }
	        
	        String args = "";
	        
	        if (constraint != null)
	        {
	            args = constraint.toString();       
	        }
	 
	        currentCursor = customers.getCustomersByDesc(args);
	        
	        customers.close();
	        return currentCursor;
	    }

		@Override
		public void bindView(View view, Context arg1, Cursor cursor) {
			TextView txtAuto=(TextView) view.findViewById(R.id.txtAuto);
			String fname=cursor.getString(cursor.getColumnIndexOrThrow("fname"));
			String lname=cursor.getString(cursor.getColumnIndexOrThrow("lname"));
			txtAuto.setText(fname+" "+lname);			
			
		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup root) {
			LayoutInflater inflator =(LayoutInflater)AddToCart.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view=inflator.inflate(R.layout.autocomplete_singleview, root,false);
			return view;
			
		}
		@Override
		public void onItemClick(AdapterView<?> adapter, View arg1, int position,
				long arg3) {
			Cursor cursor=(Cursor) adapter.getItemAtPosition(position);
			String Cid=cursor.getString(cursor.getColumnIndexOrThrow("_id"));
			customerid=Integer.parseInt(Cid);
			String fname=cursor.getString(cursor.getColumnIndexOrThrow("fname"));
			String lname=cursor.getString(cursor.getColumnIndexOrThrow("lname"));
			name.setText(fname+" "+lname);
			//EditText customerNo=(EditText)findViewById(R.id.mNumber);
			TextView mnumber=(TextView)findViewById(R.id.fone);
			String mNo=cursor.getString(cursor.getColumnIndexOrThrow("phone"));
			mnumber.setText(mNo);
		}
		
	}}
