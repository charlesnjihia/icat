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

public class Suppliers extends Activity {
	ListView leftnav;
	GridView cats;
	
	RelativeLayout prev,next;
	adapters.SuppliersGridAdapter supad;
	adapters.ProductsLeftAdapter leftad;
	adapters.ProductsRightAdapter rightad;
	database.TableSuppliersHandler supsHandler;
	List<objects.ObjectSupplier> suppliers;
	SharedData sharedData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categories);
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
		
		
		
		
		
		cats=(GridView)findViewById(R.id.cats);
		leftnav=(ListView)findViewById(R.id.leftnav);
		
		
	/*	prev=(RelativeLayout)findViewById(R.id.prev);
	    next=(RelativeLayout)findViewById(R.id.next);
		
		*/
		
		
	        
		
		
		supad=new adapters.SuppliersGridAdapter(this) ;
		cats.setAdapter(supad);
		
		leftad= new adapters.ProductsLeftAdapter(this);
		leftnav.setAdapter(leftad);
		
		rightad= new adapters.ProductsRightAdapter(this);
		//rightnav.setAdapter(rightad);
		
		cats.setOnItemClickListener(new OnItemClickListener() {
	 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
	 	    	
	 	    	objects.ObjectSupplier supOb=suppliers.get(position);
	 	    	int supId=(int) supOb.get_id();	 	    	
	       		Intent sup = new Intent(Suppliers.this,Products.class);
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
			       		  Intent prods = new Intent(Suppliers.this, Home.class);
			       		  startActivity(prods);
			 	    	}
		 	    	
		 	    	if(position==1){
			       		  Intent prods = new Intent(Suppliers.this, Products.class);
			       		  startActivity(prods);
			 	    	}
		 	    	if(position==2){
			       		  Intent cats = new Intent(Suppliers.this, SavedCarts.class);
			       		  startActivity(cats);
			 	    	}
			 	    	if(position==3){
				       		  Intent cats = new Intent(Suppliers.this, Categories.class);
				       		  startActivity(cats);
				 	    	}
			 	    	if(position==5){
				       		  Intent sups = new Intent(Suppliers.this, Products.class);
				       		  startActivity(sups);
				 	    	}
			 	    	if(position==6){
				       		  Intent offers = new Intent(Suppliers.this, Partners.class);
				       		  startActivity(offers);
				 	    	}
			 	    	if(position==7){
				       		  Intent partners = new Intent(Suppliers.this, LoanAppPart1.class);
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
