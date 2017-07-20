package com.example.faida;

import java.util.List;

import database.TableProdsCatsHandler;
import database.TableSuppliersHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class Supplier extends Activity {
	ListView subs;
	adapters.SupcatsAdapter subcats;
	int supId;
	TextView supName;
	ImageView supLogo;
	database.TableProdsCatsHandler catsHandler;
	List<objects.ObjectSupCat> supcats;
	database.TableSuppliersHandler supplierHandler;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.supplier);
		supId=getIntent().getIntExtra("supId",0);
		
		
		/*catsHandler=new database.TableProdsCatsHandler(this);
		catsHandler.open();
		supcats=catsHandler.getSupplierCats(supId);
		catsHandler.close();*/
		
		objects.ObjectSupplier sup=new objects.ObjectSupplier();
		
		supplierHandler=new database.TableSuppliersHandler(this);
		supplierHandler.open();
		sup=supplierHandler.getSupplier(supId);
		supplierHandler.close();
		
		subs=(ListView)findViewById(R.id.subs);
		subcats=new adapters.SupcatsAdapter(this,supId) ;
		subs.setAdapter(subcats);
		
		supName=(TextView)findViewById(R.id.supname);
		supName.setText(sup.get_name());
		
		supLogo=(ImageView)findViewById(R.id.bgimage);
		
		String fotoString=sup.get_logo();
	    byte[] photoName= Base64.decode(fotoString, Base64.DEFAULT);
		Bitmap supFoto= BitmapFactory.decodeByteArray( photoName, 
		                0,photoName.length);
		supLogo.setImageBitmap(supFoto);
		
		subs.setOnItemClickListener(new OnItemClickListener() {
	 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
	 	    	
	 	    	
	       		  Intent cat = new Intent(Supplier.this,Products.class);
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
