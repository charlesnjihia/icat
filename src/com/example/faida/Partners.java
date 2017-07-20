package com.example.faida;



import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;

public class Partners extends Activity {
	ListView leftnav, partners;
	//GridView cats;
	
	RelativeLayout prev,next;
	//adapters.SuppliersGridAdapter supad;
	adapters.PartnersAdapter partad;
	adapters.ProductsLeftAdapter leftad;
	adapters.ProductsRightAdapter rightad;
	database.TableSuppliersHandler supsHandler;
	List<objects.ObjectSupplier> suppliers;
	SharedData sharedData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partners);
		//this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		/*
		sharedData.setCategory("key", 0, this);
		sharedData.setSubcategory("key", 0, this);
		sharedData.setSupplier("key", 0, this);
		*/
		supsHandler=new database.TableSuppliersHandler(this);
		supsHandler.open();
		suppliers=supsHandler.getSuppliers();
		supsHandler.close();
		
		
		
		
		
		partners=(ListView)findViewById(R.id.cats);
		leftnav=(ListView)findViewById(R.id.leftnav);
		
		
	/*	prev=(RelativeLayout)findViewById(R.id.prev);
	    next=(RelativeLayout)findViewById(R.id.next);
		
		*/
		
		
	        
		
		
		partad=new adapters.PartnersAdapter(this) ;
		partners.setAdapter(partad);
		
		leftad= new adapters.ProductsLeftAdapter(this);
		leftnav.setAdapter(leftad);
		
		rightad= new adapters.ProductsRightAdapter(this);
		//rightnav.setAdapter(rightad);
		
		partners.setOnItemClickListener(new OnItemClickListener() {
	 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
	 	    	
	 	    	objects.ObjectSupplier supOb=suppliers.get(position);
	 	    	int supId=(int) supOb.get_id();	 	    	
	       		Intent sup = new Intent(Partners.this,Products.class);
	       		sup.putExtra("supId",supId);
	       		startActivity(sup);
	 	    	
	       		  
	   		}});
		
		
		
		/*
		 prev.setOnClickListener(new OnClickListener() {
	     	   
	 			public void onClick(View arg0) {
	 				
	 				int index = cats.getFirstVisiblePosition();
	 				cats.smoothScrollToPosition(index-1); // For decrement. 
	 				

	 			}});
		 next.setOnClickListener(new OnClickListener() {
	     	   
	 			public void onClick(View arg0) {
	 				int index = cats.getFirstVisiblePosition();
	 				cats.smoothScrollToPosition(index+1); // For increment. 
	 				

	 			}});
		 */
		 
		 leftnav.setOnItemClickListener(new OnItemClickListener() {
		 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
		 	    	if(position==0){
			       		  Intent prods = new Intent(Partners.this, Home.class);
			       		  startActivity(prods);
			 	    	}
		 	    	
		 	    	if(position==1){
			       		  Intent prods = new Intent(Partners.this, Products.class);
			       		  startActivity(prods);
			 	    	}
		 	    	if(position==2){
			       		  Intent cats = new Intent(Partners.this, SavedCarts.class);
			       		  startActivity(cats);
			 	    	}
			 	    	if(position==3){
				       		  Intent cats = new Intent(Partners.this, Categories.class);
				       		  startActivity(cats);
				 	    	}
			 	    	if(position==5){
				       		  Intent sups = new Intent(Partners.this, Products.class);
				       		  startActivity(sups);
				 	    	}
			 	    	if(position==4){
				       		  Intent offers = new Intent(Partners.this, Suppliers.class);
				       		  startActivity(offers);
				 	    	}
			 	    	if(position==7){
				       		  Intent partners = new Intent(Partners.this, LoanAppPart1.class);
				       		  startActivity(partners);
				 	    	}
		 	    	
		       		//  Intent prods = new Intent(Products.this, ProductPreview.class);
		       		 // startActivity(prods);
		 	    	
		       		  
		   		}});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
