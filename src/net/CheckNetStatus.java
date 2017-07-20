package net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetStatus {
	
	private Context context;
	public CheckNetStatus(Context context){
		this.context=context;
	}
	
	public boolean chkstatus(){
			ConnectivityManager connectivityManager = (ConnectivityManager) 
		    context.getSystemService(Context.CONNECTIVITY_SERVICE );
			NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();//.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			boolean isConnected = activeNetInfo != null && activeNetInfo.isConnectedOrConnecting();  
            return isConnected;
            
           /* ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;  */
            
	}
/*if (isConnected) { 
/*
NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
mBuilder.setSmallIcon(R.drawable.cart);
mBuilder.setContentTitle("Notification Alert, Click Me!");
mBuilder.setContentText("Hi, This is Android Notification Detail!");


NotificationManager mNotificationManager =
(NotificationManager)  context.getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on. 
mNotificationManager.notify(10,mBuilder.getNotification());//.notify(notificationID, mBuilder.build());
*

}
else{
Log.i("NET", "not connected" +isConnected);
}*/

}
