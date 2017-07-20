package com.example.faida;

import java.util.List;

import objects.ObjectProductCats;

import database.TableProdsCatsHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class SubCats extends Activity {
	ListView subs;
	ImageView catImg;
	TextView catName;
	adapters.SubcatsAdapter subcats;
    int catId;
    database.TableProdsCatsHandler catHandler;
    List<objects.ObjectSubCat> catSubs;
    objects.ObjectProductCats cat;
    LinearLayout bg,bgg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subcats);
		catId=getIntent().getIntExtra("catId",0);
		
		objects.ObjectProductCats cat=new objects.ObjectProductCats();
		
		catHandler=new database.TableProdsCatsHandler(this);
		catHandler.open();
		catSubs=catHandler.getSubCats(catId);
		cat=catHandler.getCat(catId);
		catHandler.close();		
		
		
		catImg=(ImageView)findViewById(R.id.bgimage);		
		catName=(TextView)findViewById(R.id.catname);
		catName.setText(cat.get_name().toUpperCase());
		bg=(LinearLayout)findViewById(R.id.bg1);
		bgg=(LinearLayout)findViewById(R.id.bg2);
		
		String color=cat.getLight();
		color=color.replace(" ", "");
		
		bg.setBackgroundColor(Color.parseColor(color));
		bgg.setBackgroundColor(Color.parseColor(color));
		
		String fotoString=cat.get_photo();
	    byte[] photoName= Base64.decode(fotoString, Base64.DEFAULT);
		Bitmap catFoto= BitmapFactory.decodeByteArray( photoName, 
		                0,photoName.length);
		catImg.setImageBitmap(catFoto);
		subs=(ListView)findViewById(R.id.subs);
		subcats=new adapters.SubcatsAdapter(this,catId) ;
		subs.setAdapter(subcats);
		
		
		
		subs.setOnItemClickListener(new OnItemClickListener() {
	 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
	 	    	
	 	    	
	       		  Intent cat = new Intent(SubCats.this,Products.class);
	       		  startActivity(cat);
	 	    	
	       		  
	   		}});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
