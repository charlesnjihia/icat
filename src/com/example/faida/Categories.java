package com.example.faida;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;

public class Categories extends Activity {
	ListView leftnav;
	GridView cats;
	
	RelativeLayout prev,next;
	adapters.CategoriesGridAdapter catad;
	adapters.ProductsLeftAdapter leftad;
	 database.TableProdsCatsHandler catHandler;
	 List<objects.ObjectProductCats> catObjs;
	 SharedData sharedData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categories);
		/*
		sharedData.setSupplier("key", 0, this);
		sharedData.setSubcategory("key", 0, this);
		*/
		
		 catHandler=new database.TableProdsCatsHandler(this);
		catHandler.open();
		catObjs=catHandler.getAllCats();
		catHandler.close();
		
		
		
		cats=(GridView)findViewById(R.id.cats);
		leftnav=(ListView)findViewById(R.id.leftnav);
		
		
	//	prev=(RelativeLayout)findViewById(R.id.prev);
	//    next=(RelativeLayout)findViewById(R.id.next);
		
		
		
		
	        
		
		
		catad=new adapters.CategoriesGridAdapter(this) ;
		 cats.setAdapter(catad);
		
		leftad= new adapters.ProductsLeftAdapter(this);
		leftnav.setAdapter(leftad);
		
		//rightad= new adapters.ProductsRightAdapter(this);
		//rightnav.setAdapter(rightad);
		
		cats.setOnItemClickListener(new OnItemClickListener() {
	 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
	 	    	objects.ObjectProductCats cat=catObjs.get(position);
	 	    	int catId=(int) cat.get_id();
	       		  Intent catAct = new Intent(Categories.this,SubCats.class);
	       		  catAct.putExtra("catId",catId);
	       		  startActivity(catAct);
	 	    	
	       		  
	   		}});
		
		
		
		
		/* prev.setOnClickListener(new OnClickListener() {
	     	   
	 			public void onClick(View arg0) {
	 				
	 				int index = cats.getFirstVisiblePosition();
	 				cats.smoothScrollToPosition(index-1); // For decrement. 
	 				

	 			}});
		 next.setOnClickListener(new OnClickListener() {
	     	   
	 			public void onClick(View arg0) {
	 				int index = cats.getFirstVisiblePosition();
	 				cats.smoothScrollToPosition(index+1); // For increment. 
	 				

	 			}}); */
		 
		 leftnav.setOnItemClickListener(new OnItemClickListener() {
		 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
		 	    	
		 	    	if(position==0){
			       		  Intent prods = new Intent(Categories.this, Home.class);
			       		  startActivity(prods);
			 	    	}
		 	    	if(position==1){
			       		  Intent prods = new Intent(Categories.this, Products.class);
			       		  startActivity(prods);
			 	    	}
		 	    	if(position==2){
			       		  Intent cats = new Intent(Categories.this, SavedCarts.class);
			       		  startActivity(cats);
			 	    	}
			 	    	
			 	    	if(position==4){
				       		  Intent sups = new Intent(Categories.this, Suppliers.class);
				       		  startActivity(sups);
				 	    	}
			 	    	if(position==5){
				       		  Intent offers = new Intent(Categories.this, Products.class);
				       		  startActivity(offers);
				 	    	}
			 	    	if(position==6){
				       		  Intent partners = new Intent(Categories.this, Partners.class);
				       		  startActivity(partners);
				 	    	}
			 	    	if(position==7){
				       		  Intent partners = new Intent(Categories.this, LoanAppPart1.class);
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
