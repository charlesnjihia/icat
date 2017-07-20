package com.example.faida;

import android.os.Bundle;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NetListener extends BroadcastReceiver {
	@Override
    public void onReceive(Context context, Intent intent) {


         /* ConnectivityManager connectivityManager = (ConnectivityManager) 
                                       context.getSystemService(Context.CONNECTIVITY_SERVICE );
          NetworkInfo activeNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);*/
		net.CheckNetStatus statechk=new net.CheckNetStatus(context);		
          boolean isConnected = statechk.chkstatus(); 
          if (isConnected) { 
        	  
        	  
      		    net.SendLoanClients lReq=new net.SendLoanClients(context)	;
				lReq.getUnsentClients();
        	  /*
        	  NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        	  mBuilder.setSmallIcon(R.drawable.cart);
              mBuilder.setContentTitle("Notification Alert, Click Me!");
              mBuilder.setContentText("Hi, This is Android Notification Detail!");
             

              NotificationManager mNotificationManager =
              (NotificationManager)  context.getSystemService(Context.NOTIFICATION_SERVICE);

             // notificationID allows you to update the notification later on. 
              mNotificationManager.notify(10,mBuilder.getNotification());//.notify(notificationID, mBuilder.build());
          */
          
          }
          else{
        	  Log.i("NET", "not connected" +isConnected);
          }
        }

}
