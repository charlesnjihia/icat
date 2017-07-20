package com.example.faida;

import java.util.List;





import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Products extends FragmentActivity{
	
	MyAdapter mAdapter;
	ViewPager prodsPager;
	ListView leftnav,rightnav;
	
	adapters.ProductsLeftAdapter leftad;
	adapters.HomRightAdapter rightad;
	ImageView catshow,checkout;
	int catOn=1,prodsCount;
	static int noOfFrags;
	int extraProds=0;
	int supId,catId,subcatId;
	
	database.TableProductsHandler prodshandler;
	List<objects.ProductsObject> allProds,frgProds;
	
	SharedData sharedData;
	database.TableProdsCatsHandler handler;
	 List<objects.ObjectProductCats> catObjs;

	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.products);	
		 
		/**************determinants of the products to be fetched ******/ 
		 
		// supId=sharedData.getSupplier(this);
		// catId=sharedData.getCategory(this);
		// subcatId=sharedData.getSubcategory(this);
		 
		/**************determinants of the products to be fetched *****/ 
		 
		 prodshandler= new database.TableProductsHandler(this);
		 prodshandler.open();
		 
		 
		 if(supId==0 && catId==0 && subcatId==0){
			 
		 allProds=prodshandler.getAllProducts();
		 }
		 else{ 
			 
			 allProds=prodshandler.getAllProducts(); 		 
		 }
		// prodsCount=allProds.size();
		 prodsCount=prodshandler.getProdsCount();
		 prodshandler.close();
		// Log.i("total products",""+prodsCount);
	     noOfFrags=calcFragsNo();
		 
		 
		/* for(int i=0;i<allProds.size();i++){
			 objects.ProductsObject prod=allProds.get(i);
			 Log.i("Product Name",""+prod.get_prodname());
		 }*/
		 
		 handler=new database.TableProdsCatsHandler(this);
		 handler.open();
		 catObjs=handler.getAllCats();
		 handler.close();
		 
		 
		// catshow=(ImageView)findViewById(R.id.cat);
		 checkout=(ImageView)findViewById(R.id.checkout);
		 
		 mAdapter = new MyAdapter(getSupportFragmentManager());
		 prodsPager = (ViewPager) findViewById(R.id.pager);
		 prodsPager.setAdapter(mAdapter);
		 
		 leftnav=(ListView)findViewById(R.id.leftnav);
			rightnav=(ListView)findViewById(R.id.rightnav);
			
			//prodad=new adapters.ProductsGridAdapter(this) ;
			//prods.setAdapter(prodad);
			
			leftad= new adapters.ProductsLeftAdapter(this);
			leftnav.setAdapter(leftad);
			
			rightad= new adapters.HomRightAdapter(this);
			rightnav.setAdapter(rightad);
			
			rightnav.setOnItemClickListener(new OnItemClickListener() {
		 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
		 	    	objects.ObjectProductCats cat=catObjs.get(position);
		 	    	int catId=(int)cat.get_id();
			       		  Intent prods = new Intent(Products.this,Home.class);
			       		  startActivity(prods);
			 	    
		 	    }});
			/*
			catshow.setOnClickListener(new OnClickListener() {
		     	   
	 			public void onClick(View arg0) {
	 				
	 				if(catOn==1){
	 					rightnav.setVisibility(View.INVISIBLE);
	 					checkout.setVisibility(View.VISIBLE);
	 					catOn=0;
	 				  catshow.setImageResource(R.drawable.cat_add);
		       		  
	 				}else{
	 					rightnav.setVisibility(View.VISIBLE);
	 					checkout.setVisibility(View.INVISIBLE);
	 					catOn=1;
	 					catshow.setImageResource(R.drawable.cat_rem);
	 				}
	 				

	 			}});
			
		 */
			leftnav.setOnItemClickListener(new OnItemClickListener() {
		 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
		 	    	if(position==0){
			       		  Intent prods = new Intent(Products.this,Home.class);
			       		  startActivity(prods);
			 	    	}
		 	    	if(position==2){
			       		  Intent cats = new Intent(Products.this, SavedCarts.class);
			       		  startActivity(cats);
			 	    	}
			 	    	if(position==3){
				       		  Intent cats = new Intent(Products.this, Categories.class);
				       		  startActivity(cats);
				 	    	}
			 	    	if(position==4){
				       		  Intent sups = new Intent(Products.this, Suppliers.class);
				       		  startActivity(sups);
				 	    	}
			 	    	if(position==5){
				       		  Intent offers = new Intent(Products.this, Products.class);
				       		  startActivity(offers);
				 	    	}
			 	    	if(position==6){
				       		  Intent partners = new Intent(Products.this, Partners.class);
				       		  startActivity(partners);
				 	    	}
			 	    	if(position==7){
				       		  Intent partners = new Intent(Products.this, LoanAppPart1.class);
				       		  startActivity(partners);
				 	    	}
		 	    	
		       		//  Intent prods = new Intent(Products.this, ProductPreview.class);
		       		 // startActivity(prods);
		 	    	
		       		  
		   		}});			
		 }
		 
		 public static class MyAdapter extends FragmentStatePagerAdapter {
		 public MyAdapter(FragmentManager fragmentManager) {
		 super(fragmentManager);
		 }
		 
		 @Override
		 public int getCount() {			 
			// Log.i("Number of fragments",""+noOfFrags);
		 return  noOfFrags;
		 }
		 
		 @Override
		 public Fragment getItem(int position) {
			 
			Log.i("Position as on the adapter",""+position);
			switch (position )
		 {
		 case 0: // Fragment # 0 - This will show image
		 return ProdsFragment.init(position);
		 case 1: // Fragment # 1 - This will show image
			 return ProdsFragment.init(position);
		 default:// Fragment # 2-9 - Will show list
			 return ProdsFragment.init(position);
		 } 
		 }
		 
		 
		 
		 
		 
		 }
		 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public int calcFragsNo(){
		   int i=prodsCount%6;
	   	if(i>0){
	   		extraProds=i;
	       return ((int)prodsCount/6) +1; 
	   	}else{
	   		return (int)prodsCount/6;
	   	}        
		   
	   }
	    

}
