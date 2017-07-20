package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.faida.Categories;
import com.example.faida.SavedCarts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SendCart {
	static List<objects.ObjectCart> cartItems;
	static objects.ObjectCustomers client;
	static Context context;
	static String payMethod,transNum,cartId;
	database.TableSavedCartsHandler svcart;
	
	
public SendCart(Context context){
		
		this.context=context;
		svcart=new database.TableSavedCartsHandler(context);		
		
		}
public void makePurchase(List<objects.ObjectCart> cartItems,objects.ObjectCustomers client,String paymethod, String trans,String cartId){
	this.cartItems=cartItems;
	this.client=client;
	this.payMethod=paymethod;
	this.transNum=trans;
	this.cartId=cartId;
	//new HttpAsyncTask().execute("http://192.168.0.110:80/icat/index.php/order-cart");
	new HttpAsyncTask().execute("http://iprocureafrica.co/iCatalog/index.php/order-cart");
}




public static String POST(String url){
    InputStream inputStream = null;
    String result= "";
    try {

        // 1. create HttpClient
        HttpClient httpclient = new DefaultHttpClient();

        // 2. make POST request to the given URL
        HttpPost httpPost = new HttpPost(url);

        String json = "";
        JSONArray carts= new JSONArray();
        
        for(int i=0;i<cartItems.size();i++){
        	Log.i("entered loop","entered loop");
        	objects.ObjectCart crt=new objects.ObjectCart();
        	crt=cartItems.get(i);
        	Log.i("gotten object","gotten object");
        	JSONObject cart= new JSONObject();
        	cart.put("prodId", crt.get_prodId());
        	Log.i("gotten prodId","gotten prodId");
        	cart.put("quantity", crt.get_quantity());
        	Log.i("gotten quantity","gotten qantity");
        	cart.put("units", crt.get_units());
        	Log.i("gotten units","gotten units");
        
        	carts.put(cart);

            
        	Log.i("added cart","added cart");       	
        }

        // 3. build jsonObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("clientName",client.get_name()+" "+client.get_surname() );
        jsonObject.accumulate("clientTel", client.get_mobile());
        jsonObject.accumulate("payMethod",payMethod);
        jsonObject.accumulate("transNum",transNum);
        jsonObject.accumulate("agentName","Bernard");
        jsonObject.accumulate("amount",0);            
        Log.i("ADDED ALL","added all");
        jsonObject.put("cart",carts);
        Log.i("added cart array","added cart array");
        
        // 4. convert JSONObject to JSON to String
        json = jsonObject.toString();

        // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
        // ObjectMapper mapper = new ObjectMapper();
        // json = mapper.writeValueAsString(person); 

        // 5. set json to StringEntity
        StringEntity se = new StringEntity(json);

        // 6. set httpPost Entity
        httpPost.setEntity(se);

        // 7. Set some headers to inform server about the type of the content   
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        // 8. Execute POST request to the given URL
        HttpResponse httpResponse = httpclient.execute(httpPost);

        // 9. receive response as inputStream
        inputStream = httpResponse.getEntity().getContent();

        // 10. convert inputstream to string
        if(inputStream != null)
            result = convertInputStreamToString(inputStream);
        else
            result = "Did not work!";

    } catch (Exception e) {
        Log.d("InputStream", e.getLocalizedMessage());
    }

    // 11. return result
    
    return result;
}
private static String convertInputStreamToString(InputStream inputStream) throws IOException{
    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
    String line = "";
    String result = "";
    while((line = bufferedReader.readLine()) != null)
        result += line;

    inputStream.close();
    return result;

}   

private class HttpAsyncTask extends AsyncTask<String, Void, String> {

protected String doInBackground(String... urls) {
String res;
    
   res=POST(urls[0]);
    return res;
}
// onPostExecute displays the results of the AsyncTask.
@Override
protected void onPostExecute(String result) {
Log.i("res----",""+result);
	
	if(result.equalsIgnoreCase("success")){
		Log.i("cartid is this ",""+cartId);
		svcart.open();
		svcart.updatePurchase(cartId);
		svcart.close();
		
		Toast toast = Toast.makeText(context, "Cart Sent", Toast.LENGTH_SHORT);
		toast.show();
		Intent cartDetails= new Intent(context, com.example.faida.Cart.class);
		cartDetails.putExtra("cartid", cartId);
		cartDetails.putExtra("clientid", client.get_id());
 		context.startActivity(cartDetails);
		
		//new AlertDialog.Builder(Cart.this).setTitle("Request Sent").setMessage("The Cart Purchase have been successfully delivered").setNeutralButton("OK", null).show();
	}else{
		Toast toast = Toast.makeText(context, "Error Sending. Try again!", Toast.LENGTH_SHORT);
		toast.show();
		
	}
}
}
}


