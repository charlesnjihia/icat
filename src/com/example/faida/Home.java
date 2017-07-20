package com.example.faida;

import java.util.Timer;
import java.util.TimerTask;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class Home extends Activity {
	ListView leftnav,rightnav;
	//GridView prods;
//	adapters.ProductsGridAdapter prodad;
	TextView prodname,offertitle,offerinfor;
	adapters.HomeLeftAdapter leftad;
	adapters.HomRightAdapter rightad;
	private ImageSwitcher offerimg;
	 ImageView play,pause;
	 database.TableUpdatesHandler chkupdates;
	public Integer[] mThumbIds = {
            R.drawable.sample1, R.drawable.sample2,
            R.drawable.sample3, R.drawable.sample4,
            R.drawable.sample6, R.drawable.sample5            
    };
	SharedData sharedData;
  int x=1;
  int y=0;
  float initialX;
 
  TimerTask mTimerTask;
	final Handler handler = new Handler();
	Timer timer = new Timer();	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		
		
		
		prodname=(TextView)findViewById(R.id.prodname);
		offertitle=(TextView)findViewById(R.id.offertitle);
		offerinfor=(TextView)findViewById(R.id.offerdesc);
		
		
		//prods=(GridView)findViewById(R.id.prods);
		leftnav=(ListView)findViewById(R.id.leftnav);
		rightnav=(ListView)findViewById(R.id.rightnav);
		
		play=(ImageView)findViewById(R.id.play);
		//pause=(ImageView)findViewById(R.id.pause);
		offerimg=(ImageSwitcher)findViewById(R.id.offerimg);
		
		offerimg.setFactory(new ViewFactory() {
			   
			@SuppressWarnings("deprecation")
			public View makeView() {
			      ImageView myView = new ImageView(getApplicationContext());
			      myView.setScaleType(ImageView.ScaleType.FIT_XY);
			      myView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.
			      FILL_PARENT,LayoutParams.FILL_PARENT));
			      return myView;
			   }});
			
		offerimg.setImageResource(R.drawable.sample1);
		
		doTimerTask();
		
		//prodad=new adapters.ProductsGridAdapter(this) ;
		//prods.setAdapter(prodad);
		
		leftad= new adapters.HomeLeftAdapter(this);
		leftnav.setAdapter(leftad);
		//leftnav added to offer navigation
		
		rightad= new adapters.HomRightAdapter(this);
		rightnav.setAdapter(rightad);
		
		
		play.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				if(y==0){
 				doTimerTask();
 				}else{
 					stopTask();
 				}
 				

 			}});
				
		
		
		leftnav.setOnItemClickListener(new OnItemClickListener() {
	 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
	 	    	
	 	    	if(position==1){
	       		  Intent prods = new Intent(Home.this, Products.class);
	       		  startActivity(prods);
	 	    	}
	 	    	if(position==2){
		       		  Intent cats = new Intent(Home.this, SavedCarts.class);
		       		  startActivity(cats);
		 	    	}
	 	    	if(position==3){
		       		  Intent cats = new Intent(Home.this, Categories.class);
		       		  startActivity(cats);
		 	    	}
	 	    	if(position==4){
		       		  Intent sups = new Intent(Home.this, Suppliers.class);
		       		  startActivity(sups);
		 	    	}
	 	    	if(position==5){
		       		  Intent offers = new Intent(Home.this, Products.class);
		       		  startActivity(offers);
		 	    	}
	 	    	if(position==6){
		       		  Intent part = new Intent(Home.this, Partners.class);
		       		  startActivity(part);
		 	    	}
	 	    	if(position==7){
		       		  Intent app = new Intent(Home.this, LoanAppPart1.class);
		       		  startActivity(app);
		 	    	}
	       		  
	   		}});
		
	
	chkupdates=new database.TableUpdatesHandler(Home.this);	
	chkupdates.open();	
	int appstatus=chkupdates.getAppStatus();
	chkupdates.close();
	
	if(appstatus==0){
		net.CheckNetStatus netstatus=new net.CheckNetStatus(Home.this);
		boolean status=netstatus.chkstatus();
		Log.i("Status"," "+status);	
		if(status==true){
			
			net.DownloaderClass downl= new net.DownloaderClass(this);
			downl.performDownload();
			
			//Log.i("The application is new"," download can be performed");	
		}else{
		Log.i("The application is new"," download cant be performed due to connectivity");
		}
	}else{
		Log.i("The application is old"," app was installed way loooong");
	}
		
		
		
	}
	public void doTimerTask(){
		
		mTimerTask = new TimerTask() {       
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {       
                        try {
                     	   if (x== 5 ) {
                     		   x=0;
                               
                            }
                     	   Animation in = AnimationUtils.loadAnimation(Home.this,
                  			      R.anim.slide_in_right);
                  			      Animation out = AnimationUtils.loadAnimation(Home.this,
                  			      R.anim.slide_out_left);
                  			      
                  			offerimg.setInAnimation(in);
                  			offerimg.setOutAnimation(out);
                  			
                     	   offerimg.setImageResource(mThumbIds[x]);
                     	  x++;
                        	
                        //	Log.i("Schedule working","after evry 2 seconds................");
                          // PerformBackgroundTask performBackgroundTask = new PerformBackgroundTask();
                            // PerformBackgroundTask this class is the class that extends AsynchTask 
                           // performBackgroundTask.execute();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(mTimerTask, 0, 5000); 
       play.setImageResource(R.drawable.pause);
       y=1;
		
	}
	public void stopTask(){
		 
 	   if(mTimerTask!=null){
 	     // hTextView.setText("Timer canceled: " + nCounter);

 	      //Log.d("TIMER", "timer canceled");
 	      mTimerTask.cancel();
 	     play.setImageResource(R.drawable.play); 
 	     y=0;
 	   }
 	 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	 public boolean onTouchEvent(MotionEvent event) {
	  // TODO Auto-generated method stub
	  switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	            initialX = event.getX();
	            break;
	        case MotionEvent.ACTION_UP:
	            float finalX = event.getX();
	            if (initialX > finalX)
	            {
	            if(x<5){
	            	Animation in = AnimationUtils.loadAnimation(Home.this,
            			      R.anim.slide_in_right);
            			      Animation out = AnimationUtils.loadAnimation(Home.this,
            			      R.anim.slide_out_left);
            			      
            			    /*  Animation in = AnimationUtils.loadAnimation(Home.this,
                    			      R.anim.flip_left_in);
                    			      Animation out = AnimationUtils.loadAnimation(Home.this,
                    			      R.anim.flip_right_out);*/
            			      
            			      
            			offerimg.setInAnimation(in);
            			offerimg.setOutAnimation(out);
	                 x++;
            			      
	                offerimg.setImageResource(mThumbIds[x]);
	            }
	            } 
	            else
	            {           
	            if(x>0){
	            	 Animation in = AnimationUtils.loadAnimation(Home.this,
             			      R.anim.slide_in_left);
             			      Animation out = AnimationUtils.loadAnimation(Home.this,
             			      R.anim.slide_out_right);
	            	 x--;
	            	 offerimg.setInAnimation(in);
           			offerimg.setOutAnimation(out);
	                 offerimg.setImageResource(mThumbIds[x]);
	            }
	            }
	            break;
	        }
	        return false;
	 }
	
	public boolean chkstatus(){
		ConnectivityManager connectivityManager = (ConnectivityManager) 
	    this.getSystemService(Context.CONNECTIVITY_SERVICE );
		NetworkInfo activeNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean isConnected = activeNetInfo != null && activeNetInfo.isConnectedOrConnecting();  
        return isConnected;
}
	
	 
}
