package com.example.faida;

import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class SavedCarts extends Activity {
	
	ListView cartlist;
	adapters.SavedAdapter cartad;
	ImageView back;
	List<objects.ObjectSavedCart> savedCarts;	
	database.TableSavedCartsHandler svCart;
	database.TableCartHandler cart;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saved_carts);	
		
		 svCart=new database.TableSavedCartsHandler(this);
	        svCart.open();
	        savedCarts=svCart.getSavedCarts();
	        svCart.close();
	        cart=new database.TableCartHandler(this);
	        cart.open();
	        
	    
		
	     for(int i=0;i<savedCarts.size();i++){
	        	objects.ObjectSavedCart svCart=new objects.ObjectSavedCart();
	        	svCart=savedCarts.get(i);
	        	long x=cart.getCartProdsCount(svCart.get_cartId()) ;
	        	svCart.set_Items(x);
	        	
	        }
	     cart.close();
		
		cartad=new adapters.SavedAdapter(this,savedCarts);
		cartlist=(ListView)findViewById(R.id.svcarts);
		cartlist.setAdapter(cartad);
		back=(ImageView)findViewById(R.id.back);
		
		TextView cartNum=(TextView)findViewById(R.id.cartnum);
		cartNum.setText(savedCarts.size()+" CARTS");
		
		back.setOnClickListener(new OnClickListener() {
	     	   
			public void onClick(View arg0) {
				
				Intent prods = new Intent(SavedCarts.this,Products.class);				
				  startActivity(prods);

			}});
		
		cartlist.setOnItemClickListener(new OnItemClickListener() {
	 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
	 	    	
	 	    	objects.ObjectSavedCart cart=savedCarts.get(position);	     		 
	     		 String cartId=cart.get_cartId();
	     		 Long clientId=cart.get_custId(); 	     		
	     		 viewCartDetails(cartId,clientId);
	     		 
	   		}});		
		
		
	}
	public void viewCartDetails(String cartId,long clientId){
		
		Intent cartDetails = new Intent(this,Cart.class);
		cartDetails.putExtra("cartid", cartId);
		cartDetails.putExtra("clientid", clientId);
		//Log.i("id-----",""+cartId);
		 	 // prodDetails.putExtra("prodimage", image);
		  startActivity(cartDetails);	
		
	}

}
