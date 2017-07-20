package com.example.faida;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Category extends Activity {
	//ListView leftnav,rightnav;
	//GridView prods;
//	adapters.ProductsGridAdapter prodad;
	adapters.HomeLeftAdapter leftad;
	adapters.ProductsRightAdapter rightad;
	TextView catname;
	ImageView up,prods,cats,home;
	LinearLayout tosubs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category);
		
		up=(ImageView)findViewById(R.id.back);
		prods=(ImageView)findViewById(R.id.prods);
		cats=(ImageView)findViewById(R.id.cats);
		home=(ImageView)findViewById(R.id.home);
		
		tosubs=(LinearLayout)findViewById(R.id.tosubs);
		
		catname=(TextView)findViewById(R.id.cat_name);
		Typeface font = Typeface.createFromAsset(
			   this.getAssets(), 
			    "Roboto-Thin.ttf");
			catname .setTypeface(font);
			
			
			up.setOnClickListener(new OnClickListener() {
		     	   
	 			public void onClick(View arg0) {
	 				
	 				 Intent det = new Intent(Category.this, Categories.class);
		       		  startActivity(det);
	 				

	 			}});
			
			tosubs.setOnClickListener(new OnClickListener() {
		     	   
	 			public void onClick(View arg0) {
	 				
	 				 Intent subs = new Intent(Category.this, SubCats.class);
		       		  startActivity(subs);
	 				

	 			}});
			prods.setOnClickListener(new OnClickListener() {
		     	   
	 			public void onClick(View arg0) {
	 				
	 				 Intent prod = new Intent(Category.this, Products.class);
		       		  startActivity(prod);
	 				

	 			}});
			cats.setOnClickListener(new OnClickListener() {
		     	   
	 			public void onClick(View arg0) {
	 				
	 				 Intent cat = new Intent(Category.this, Categories.class);
		       		  startActivity(cat);
	 				

	 			}});
			home.setOnClickListener(new OnClickListener() {
		     	   
	 			public void onClick(View arg0) {
	 				
	 				 Intent hom = new Intent(Category.this, Home.class);
		       		  startActivity(hom);
	 				

	 			}});
			
			
			
		//prods=(GridView)foindViewById(R.id.prods);
		//leftnav=(ListView)findViewById(R.id.leftnav);
		//rightnav=(ListView)findViewById(R.id.rightnav);
		
		//prodad=new adapters.ProductsGridAdapter(this) ;
		//prods.setAdapter(prodad);
		
		//leftad= new adapters.HomeLeftAdapter(this);
		//leftnav.setAdapter(leftad);
		
		//rightad= new adapters.ProductsRightAdapter(this);
		//rightnav.setAdapter(rightad);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
