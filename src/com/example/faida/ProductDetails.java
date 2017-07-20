package com.example.faida;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.widget.CursorAdapter;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ProductDetails extends Activity {
	database.TableProductsHandler prodshandler;
	database.TablePhotosHandler fotoHandler;
	database.TableProdsCatsHandler cathandler;
	database.TableSavedCartsHandler svCart;
	database.TableCartHandler cartt;
	//database.TableCustomersHandler customers;
	ListView leftnav,rightnav;
	TextView prodname,proddet,minorder,price,subcat,cat, dao;
	RelativeLayout cart,body;
	int catt;
	int customerid=-2; 
	SharedData data= new SharedData();
	ImageView back,order,imgs,idea,bgimage,chart;
	objects.ObjectProductCats cate;
	objects.ObjectSubCat sub;
	Integer[] imageIDs = {
			R.drawable.sample1,
			R.drawable.sample2,
			R.drawable.sample3,
			R.drawable.sample4,
			R.drawable.sample5,
			R.drawable.sample6,
			R.drawable.sample1
			};
	
	int prodId;
	float prodCost;
	List<objects.ObjectPhotos> otherImages;
	objects.ProductsObject prodDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prod_details);
		prodId=getIntent().getIntExtra("prodId",0);
		
		 prodDetails =new objects.ProductsObject();
		prodshandler=new database.TableProductsHandler(this);
		    prodshandler.open();
	        prodDetails=prodshandler.getProduct(prodId);
	       prodshandler.close();
	       
	       fotoHandler=new database.TablePhotosHandler(this);
	       fotoHandler.open();
	       otherImages=fotoHandler.getOthers(prodId);
	       fotoHandler.close();
		
	       cathandler=new database.TableProdsCatsHandler(this);
			cathandler.open();			
			cate=cathandler.getCat((int)prodDetails.get_prodcat());
			sub=cathandler.getSubCat((int)prodDetails.get_subcat());
			cathandler.close();
			
			svCart=new database.TableSavedCartsHandler(this);
			cartt=new database.TableCartHandler(this);
			
			//customers=new database.TableCustomersHandler(this);
			
			String color=cate.getLight();
			color=color.replace(" ", "");
			
			catt=(int)prodDetails.get_prodcat();
		
		prodname=(TextView)findViewById(R.id.prodname);
		prodname.setText(prodDetails.get_prodname());
		prodname.setTextColor(Color.parseColor(color));
		
		proddet=(TextView)findViewById(R.id.desc);
		proddet.setText(prodDetails.get_proddesc());
		minorder=(TextView)findViewById(R.id.min);
		minorder.setText("Min. Order: "+prodDetails.get_minOrder());
		price=(TextView)findViewById(R.id.price);
		price.setText(prodDetails.get_deno()+" "+prodDetails.get_price());
		prodCost=prodDetails.get_price();
		subcat=(TextView)findViewById(R.id.sub);
		subcat.setText(cate.get_name().toUpperCase());
		cat=(TextView)findViewById(R.id.cat);
		cat.setText(sub.get_subName().toUpperCase());
		dao=(TextView)findViewById(R.id.dao);
		
		body=(RelativeLayout)findViewById(R.id.body);	
		
		
		body.setBackgroundColor(Color.parseColor(color));

		cart=(RelativeLayout)findViewById(R.id.cart);		
		back=(ImageView)findViewById(R.id.back);
		imgs=(ImageView)findViewById(R.id.fotos);
		idea=(ImageView)findViewById(R.id.idea);
		chart=(ImageView)findViewById(R.id.chart);
		dao.setTextColor(Color.parseColor(color));
		if(catt==1){
		idea.setImageResource(R.drawable.loan1);
		chart.setImageResource(R.drawable.cartadd1);
		}
		if(catt==2){
			idea.setImageResource(R.drawable.loan2);
			chart.setImageResource(R.drawable.cartadd2);
			
			}
		if(catt==3){
			idea.setImageResource(R.drawable.loan3);
			chart.setImageResource(R.drawable.cartadd3);
			}
		if(catt==4){
			idea.setImageResource(R.drawable.loan4);
			chart.setImageResource(R.drawable.cartadd4);
			}
		if(catt==5){
			idea.setImageResource(R.drawable.loan5);
			chart.setImageResource(R.drawable.cartadd5);
			}
		if(catt==6){
			idea.setImageResource(R.drawable.loan6);
			chart.setImageResource(R.drawable.cartadd6);
			}
		if(catt==7){
			idea.setImageResource(R.drawable.loan7);
			chart.setImageResource(R.drawable.cartadd7);
			}
		bgimage=(ImageView)findViewById(R.id.bgimage);
		
		String photo=prodDetails.get_prodphoto();		
		 final byte[] photoName= Base64.decode(photo, Base64.DEFAULT);
		 bgimage.setImageBitmap(BitmapFactory.decodeByteArray( photoName, 
	                0,photoName.length));   
		 
		 
		 
		
		back.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				
 				onBackPressed();
 				
 				/* Intent det = new Intent(ProductDetails.this, ProductPreview.class);
 				 det.putExtra("prodId",prodId);
	       		  startActivity(det);*/
 				

 			}});
		
		
		imgs.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 			// retrieve display dimensions
 				Rect displayRectangle = new Rect();
 				Window window = ProductDetails.this.getWindow();
 				window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
 				
 		    //createdialog
 				int width=(int)(displayRectangle.width() * 0.8f);
 				int height =(int)(displayRectangle.height() * 0.7f);
 				
 				Dialog d =new Dialog(ProductDetails.this, R.style.CustomDialog);
 				
 				 LayoutInflater inflater =ProductDetails.this.getLayoutInflater(); 
 				 final View input=inflater.inflate(R.layout.gallery, null);
 				 d .setContentView(input);
 				//d.setContentView(R.layout.gallery);
 				
 				final ImageView curprod=(ImageView) input.findViewById(R.id.prev);
				 //curprod.setImageResource(R.drawable.sample1);
 				curprod.setImageBitmap(BitmapFactory.decodeByteArray( photoName, 
 		                0,photoName.length));  
				 
				@SuppressWarnings("deprecation")
				final Gallery gallery = (Gallery) input.findViewById(R.id.prodfotos);
				gallery.setAdapter(new ImageAdapter(ProductDetails.this));
				gallery.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View v, int position,long id)
			{
						 byte[] photoName=null;
						 if(position<otherImages.size()){
						objects.ObjectPhotos foto=otherImages.get(position);			
						String photo=foto.get_photo();		
						 photoName= Base64.decode(photo, Base64.DEFAULT);
						 }else{
							 String photo=prodDetails.get_prodphoto();		
							  photoName= Base64.decode(photo, Base64.DEFAULT); 
							 
						 }
						 curprod.setImageBitmap(BitmapFactory.decodeByteArray( photoName, 
					                0,photoName.length));
					
					//	curprod.setImageResource(imageIDs[position]);
				}
				});
 				
 				
 				d.getWindow().setLayout(width, height);
 				d.setCanceledOnTouchOutside(true);
 				d.show(); 
 			
 				// show it
 				//alertDialog.show(); 

 				}}); 

		cart.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				
 				order(prodId,prodCost);

 				}});
		
		idea.setOnClickListener(new OnClickListener() {		
			
	       
 			public void onClick(View arg0) {
 				
 				
 				 Intent loan = new Intent(ProductDetails.this, LoanAppPart1.class);
 				// det.putExtra("prodId",prodId);
	       		  startActivity(loan);
 				
 				
 		
 				}});
		
		

		//prods=(GridView)findViewById(R.id.prods);
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
	public class ImageAdapter extends BaseAdapter {
		private Context context;
		private int itemBackground;
		public ImageAdapter(Context c)
		{
			context = c;
			// sets a grey background; wraps around the images
			TypedArray a =obtainStyledAttributes(R.styleable.MyGallery);
			itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
			a.recycle();
		}
		// returns the number of images
		public int getCount() {
			return otherImages.size()+1;
		}
		// returns the ID of an item
		public Object getItem(int position) {
			return position;
		}
		// returns the ID of an item
		public long getItemId(int position) {
			return position;
		}
		
		
		// returns an ImageView view
		@SuppressWarnings("deprecation")
		public View getView(int position, View convertView, ViewGroup parent) {
			 byte[] photoName = null;
			if(position < otherImages.size()){
			objects.ObjectPhotos foto=otherImages.get(position);			
			String photo=foto.get_photo();		
			 photoName= Base64.decode(photo, Base64.DEFAULT);
		   //  prodView=new ImageView(getApplicationContext()); 
		       
			// Bitmap image= BitmapFactory.decodeByteArray( photoName, 
		          //      0,photoName.length);	
			}else{
				String photo=prodDetails.get_prodphoto();		
				  photoName= Base64.decode(photo, Base64.DEFAULT);
				 
			}
			
			ImageView imageView = new ImageView(context);
			imageView.setImageBitmap(BitmapFactory.decodeByteArray( photoName,0,photoName.length));
			imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));
			imageView.setBackgroundResource(itemBackground);
			return imageView;
		}
	}
	
	protected void order(long prod,float prodCost) {
		
		long custId=data.getCustId(this);
		//Log.i("shared cust Id",""+custId);
		
		if(custId==-2)
		{	
			Intent regcust=new Intent(ProductDetails.this,AddToCart.class);
			regcust.putExtra("product", prod);
			regcust.putExtra("price", prodCost);
			startActivity(regcust);
		/*	
		// retrieve display dimensions
				Rect displayRectangle = new Rect();
				Window window = ProductDetails.this.getWindow();
				window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
				
		    //createdialog
				int width=(int)(displayRectangle.width() * 0.7f);
				int height =(int)(displayRectangle.height() * 0.6f);
				
				 //start of the dialog
				Dialog d =new Dialog(ProductDetails.this, R.style.CustomDialog);
				d.setContentView(R.layout.select_user);
				AutoCompleteTextView cname=(AutoCompleteTextView)d.findViewById(R.id.name);
				
				SearchCustomer customerAdpter=new SearchCustomer(this);
			       cname.setAdapter(customerAdpter);
			       cname.setThreshold(2);
			       cname.setOnItemClickListener(customerAdpter);
			
				// show it
				d.getWindow().setLayout(width, height);
				d.show(); 
    */
			
			
		}
		else{
			customerid=(int) custId;
				
		// retrieve display dimensions
				Rect displayRectangle = new Rect();
				Window window = ProductDetails.this.getWindow();
				window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
				
		    //createdialog
				int width=(int)(displayRectangle.width() * 0.7f);
				int height =(int)(displayRectangle.height() * 0.6f);
				
				 //start of the dialog
				final Dialog d =new Dialog(ProductDetails.this, R.style.CustomDialog);
				d.setContentView(R.layout.add_to_cart);
				final EditText qty=(EditText)d.findViewById(R.id.qty);
				final TextView chekker=(TextView)d.findViewById(R.id.disp);
				Button ok=(Button)d.findViewById(R.id.ok);
				Button cancel=(Button)d.findViewById(R.id.cancel);
				
				cancel.setOnClickListener(new OnClickListener() {
			     	   
		 			public void onClick(View arg0) {
		 				d.cancel();
		 				
		 			}});
				
				ok.setOnClickListener(new OnClickListener() {
			     	   
		 			public void onClick(View arg0) {
		 			String quant=qty.getText().toString();
		 			
		 			if(quant.length()<1){
		 				
		 				chekker.setText("Indicate Product Quantity!!");
		 				
		 			}else{
		 				
		 			int quantity=Integer.parseInt(quant);
		 				addToCart(quantity);		 				
		 				d.cancel();
		 			}
		 			
		 			
		 			
		 				
		 			}});
				
			
				// show it
				d.getWindow().setLayout(width, height);
				d.show(); 
    
		}
		
	}
	
	public void addToCart(int quantity){
		
		final String cartId=data.getCart(this);
		final Long custId=data.getCustId(this);
		
		objects.ObjectCart cartOb=new objects.ObjectCart();
		cartOb.set_cartId(cartId);
		cartOb.set_prodId(prodId);
		cartOb.set_quantity(quantity);
		cartOb.set_units("items");
		cartt.open();
		cartt.addtoCart(cartOb);
		cartt.close();
		//prompt user proceed
		
		// retrieve display dimensions
		Rect displayRectangle = new Rect();
		Window window = ProductDetails.this.getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
		
    //createdialog
		int width=(int)(displayRectangle.width() * 0.7f);
		int height =(int)(displayRectangle.height() * 0.6f);
		
		 //start of the dialog
		final Dialog d =new Dialog(ProductDetails.this, R.style.CustomDialog);
		d.setContentView(R.layout.check_out);
		RelativeLayout toprods,check;
		toprods=(RelativeLayout)d.findViewById(R.id.back);
		check=(RelativeLayout)d.findViewById(R.id.check);
		
		  toprods.setOnClickListener(new OnClickListener() {
	     	   
	 			public void onClick(View arg0) {
	 				
	 				Intent prods = new Intent(ProductDetails.this, Products.class);
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
				data.nullifyCustId("customer",-2,ProductDetails.this);
				
				
				Intent savedCarts = new Intent(ProductDetails.this, SavedCarts.class);
				

				//intent
				startActivity(savedCarts);	
	 				
	 				
	 				
	 				
	 				
	 				

	 			}});
		// show it
			d.getWindow().setLayout(width, height);
			d.show(); 
		
	//end prompt
		
		
		
		
	}
	
	
}
