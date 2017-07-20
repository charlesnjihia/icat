package com.example.faida;

import java.util.List;
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class ProductPreview extends Activity {
	database.TableProductsHandler prodshandler;
	database.TableProdsCatsHandler cathandler;
	database.TableSavedCartsHandler svCart;
	database.TableCartHandler cartt;
	ListView leftnav,rightnav;
	TextView prodname,subcat,cat,price;
//	adapters.ProductsGridAdapter prodad;
	adapters.HomeLeftAdapter leftad;
	adapters.ProductsRightAdapter rightad;
	RelativeLayout prev,next;
	ImageView infor,prods,cats,home, back,catimage,cart;
	private ImageSwitcher prodimg;
	int customerid=-2;
	SharedData data= new SharedData();
	float prodCost;
	int x=2,prodId,prodPos;
	  float initialY;
	List<objects.ProductsObject> allProds;
	objects.ObjectProductCats cate;
	objects.ObjectSubCat sub;
	 public Integer[] mThumbIds = {
	            R.drawable.sample1, R.drawable.sample2,
	            R.drawable.sample3, R.drawable.sample4,
	            R.drawable.sample6, R.drawable.sample5
	            
	    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prod_preview);
		 prodId=getIntent().getIntExtra("prodId",0);
		 objects.ProductsObject prodDetails =new objects.ProductsObject();
		 prodshandler=new database.TableProductsHandler(this);
		 prodshandler.open();
	     allProds=prodshandler.getAllProducts();
	     prodDetails=prodshandler.getProduct(prodId);
	     prodshandler.close();
		
		setProductPosition(prodId);
		cathandler=new database.TableProdsCatsHandler(this);
		cathandler.open();
		
		cate=cathandler.getCat((int)prodDetails.get_prodcat());
		sub=cathandler.getSubCat((int)prodDetails.get_subcat());
		cathandler.close();
		
		svCart=new database.TableSavedCartsHandler(this);
		cartt=new database.TableCartHandler(this);
		
		prodCost=prodDetails.get_price();
		String color=cate.getLight();
		color=color.replace(" ", "");
		prodname=(TextView)findViewById(R.id.prodname);
		prodname.setText(prodDetails.get_prodname());
		prodname.setTextColor(Color.parseColor(color));
		
		
		price=(TextView)findViewById(R.id.price);
		price.setText(prodDetails.get_deno()+" "+prodDetails.get_price());
		price.setBackgroundColor(Color.parseColor(color));
		
		subcat=(TextView)findViewById(R.id.sub);
		subcat.setText(cate.get_name().toUpperCase());
		cat=(TextView)findViewById(R.id.cat);
		cat.setText(sub.get_subName().toUpperCase());
		
		infor=(ImageView)findViewById(R.id.infor);
		prods=(ImageView)findViewById(R.id.prods);
		cats=(ImageView)findViewById(R.id.cats);
		home=(ImageView)findViewById(R.id.home);
		catimage=(ImageView)findViewById(R.id.catimage);
		cart=(ImageView)findViewById(R.id.order);
		back=(ImageView)findViewById(R.id.back);
		prev=(RelativeLayout)findViewById(R.id.prev);
		next=(RelativeLayout)findViewById(R.id.next);
		prodimg=(ImageSwitcher)findViewById(R.id.prodimg);
		
		String photo=prodDetails.get_prodphoto();		
		 final byte[] photoName= Base64.decode(photo, Base64.DEFAULT);
	   //  prodView=new ImageView(getApplicationContext()); 
	       
		 Bitmap catprod= toGrayscale(BitmapFactory.decodeByteArray( photoName, 
	                0,photoName.length));
		  catimage.setImageBitmap(catprod);
		
		//prodimg.setImageResource(R.drawable.ic_launcher);
		prodimg.setFactory(new ViewFactory() {
		   public View makeView() {
		      ImageView prodView = new ImageView(getApplicationContext());
		     prodView.setImageBitmap(BitmapFactory.decodeByteArray( photoName, 
		                0,photoName.length));   
		      prodView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		      prodView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.
		      FILL_PARENT,LayoutParams.FILL_PARENT));
		      return prodView;
		   }});
		
		//prodimg.setOnTouchListener((OnTouchListener) this);
		
		//prodimg.setImageResource(R.drawable.sample4);
		
		
		
		/*
		prev.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				
 								 
 				 if(x>=1){
 					x-=1; 
 				 }
 				
 				prodimg.setImageResource(mThumbIds[x]);

 			}});
		next.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				
 				 if(x<=4){
  					x+=1; 
  				 }
  				prodimg.setImageResource(mThumbIds[x]);

 			}});
		*/
		
		infor.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				
 				 Intent det = new Intent(ProductPreview.this, ProductDetails.class);
 				 det.putExtra("prodId",prodId);
	       		  startActivity(det);
 				

 			}});
		
		back.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				
 				onBackPressed();
 				

 			}});
		cart.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				
 				order(prodId,prodCost);	
 				

 			}});
		
				
		
		
		prods.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				
 				 Intent prod = new Intent(ProductPreview.this, Products.class);
	       		  startActivity(prod);
 				

 			}});
		cats.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				
 				 Intent cat = new Intent(ProductPreview.this, Categories.class);
	       		  startActivity(cat);
 				

 			}});
		home.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				
 				 Intent hom = new Intent(ProductPreview.this, Home.class);
	       		  startActivity(hom);
 				

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
	
protected void order(long prod,float prodCost) {
		
		long custId=data.getCustId(this);
		//Log.i("shared cust Id",""+custId);
		
		if(custId==-2)
		{	
			Intent regcust=new Intent(ProductPreview.this,AddToCart.class);
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
				Window window = ProductPreview.this.getWindow();
				window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
				
		    //createdialog
				int width=(int)(displayRectangle.width() * 0.7f);
				int height =(int)(displayRectangle.height() * 0.6f);
				
				 //start of the dialog
				final Dialog d =new Dialog(ProductPreview.this, R.style.CustomDialog);
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
	
	public void setProductPosition( int prodId){
		for(int i=0;i<allProds.size();i++){
			objects.ProductsObject prod=allProds.get(i);
			if(prod.get_id()==prodId){
				prodPos=i;
			}
			
			
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
		Window window = ProductPreview.this.getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
		
    //createdialog
		int width=(int)(displayRectangle.width() * 0.7f);
		int height =(int)(displayRectangle.height() * 0.6f);
		
		 //start of the dialog
		final Dialog d =new Dialog(ProductPreview.this, R.style.CustomDialog);
		d.setContentView(R.layout.check_out);
		RelativeLayout toprods,check;
		toprods=(RelativeLayout)d.findViewById(R.id.back);
		check=(RelativeLayout)d.findViewById(R.id.check);
		
		  toprods.setOnClickListener(new OnClickListener() {
	     	   
	 			public void onClick(View arg0) {
	 				
	 				Intent prods = new Intent(ProductPreview.this, Products.class);
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
				data.nullifyCustId("customer",-2,ProductPreview.this);
				
				
				Intent savedCarts = new Intent(ProductPreview.this, SavedCarts.class);
				

				//intent
				startActivity(savedCarts);	
	 				
	 				
	 				
	 				
	 				
	 				

	 			}});
		// show it
			d.getWindow().setLayout(width, height);
			d.show(); 
		
	//end prompt
		
		
		
		
	}
	
	 public void next(View view){
		 if(prodPos<(allProds.size()-1)){
		 prodPos=prodPos+1;
	      
		 objects.ProductsObject prod=allProds.get(prodPos);
		 
		 prodname.setText(prod.get_prodname());
		price.setText(prod.get_deno()+" "+prod.get_price());
		prodId=(int)prod.get_id();
		int catId=(int)prod.get_prodcat();
		int subId=(int)prod.get_subcat();
		cathandler.open();		
		cate=cathandler.getCat(catId);
		sub=cathandler.getSubCat(subId);
		cathandler.close();
		
		String color=cate.getLight();
		color=color.replace(" ", "");		
		prodname.setTextColor(Color.parseColor(color));
		price.setBackgroundColor(Color.parseColor(color));
		subcat.setText(cate.get_name().toUpperCase());
		cat.setText(sub.get_subName().toUpperCase());
		
		String photo=prod.get_prodphoto();		
		 final byte[] photoName= Base64.decode(photo, Base64.DEFAULT);
		
		Bitmap foto= BitmapFactory.decodeByteArray( photoName, 
	                0,photoName.length);
		 
		 Animation in = AnimationUtils.loadAnimation(this,
			      R.anim.slide_out_top);
			      Animation out = AnimationUtils.loadAnimation(this,
			      R.anim.slide_in_bottom);
	     
	      
	      prodimg.setInAnimation(out);
	      prodimg.setOutAnimation(in);
	      prodimg.setImageDrawable(new BitmapDrawable(foto));
	      
	      Bitmap catprod= toGrayscale(foto);
		  catimage.setImageBitmap(catprod);
	      
	    
	     
	    //  prodimg.setImageResource(R.drawable.ic_launcher);
	      }
	   }
	 public void previous(View view){
		 if(prodPos>0){
	      prodPos=prodPos-1;	      
		 objects.ProductsObject prod=allProds.get(prodPos);
		 
		 prodname.setText(prod.get_prodname());
		price.setText(prod.get_deno()+" "+prod.get_price());
		prodId=(int)prod.get_id();
		int catId=(int)prod.get_prodcat();
		int subId=(int)prod.get_subcat();
		
		cathandler.open();		
		cate=cathandler.getCat(catId);
		sub=cathandler.getSubCat(subId);
		cathandler.close();
		
		String color=cate.getLight();
		Log.i("clop",""+color);
		color=color.replace(" ", "");		
		prodname.setTextColor(Color.parseColor(color));
		price.setBackgroundColor(Color.parseColor(color));
		
		subcat.setText(cate.get_name().toUpperCase());
		cat.setText(sub.get_subName().toUpperCase());
		 String photo=prod.get_prodphoto();		
		 final byte[] photoName= Base64.decode(photo, Base64.DEFAULT);
		
		Bitmap foto= BitmapFactory.decodeByteArray( photoName, 
	                0,photoName.length);
		
		
		 
		 Animation in = AnimationUtils.loadAnimation(this,
			      R.anim.slide_in_top);
			      Animation out = AnimationUtils.loadAnimation(this,
			      R.anim.slide_out_bottom);
	     
	      prodimg.setInAnimation(in);
	      prodimg.setOutAnimation(out);
	      
	      prodimg.setImageDrawable(new BitmapDrawable(foto));
	      
	      Bitmap catprod= toGrayscale(foto);
		  catimage.setImageBitmap(catprod);
	    //  changeImage(prodPos);
	      }
	   }
	 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public Bitmap toGrayscale(Bitmap bmpOriginal)
	{        
	    int width, height;
	    height = bmpOriginal.getHeight();
	    width = bmpOriginal.getWidth();    

	    Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
	    Canvas c = new Canvas(bmpGrayscale);
	    Paint paint = new Paint();
	    ColorMatrix cm = new ColorMatrix();
	    cm.setSaturation(0);
	    ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
	    paint.setColorFilter(f);
	    c.drawBitmap(bmpOriginal, 0, 0, paint);
	    return bmpGrayscale;
	}
	
	//@Override
	 public boolean onTouchEvent(MotionEvent event) {
	  // TODO Auto-generated method stub
	  switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	            initialY = event.getY();
	            break;
	        case MotionEvent.ACTION_UP:
	            float finalY = event.getY();
	            if (initialY> finalY)
	            {
	            next(prodimg);
	            } 
	            else
	            {
	            previous(prodimg);	
	            }
	            break;
	        }
	        return false;
	 }

}
