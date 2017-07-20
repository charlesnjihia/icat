package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import objects.ObjectLoanClients;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.faida.SharedData;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class SendLoanClients {
						static List<objects.ObjectLoanClients> clients;
						database.TableUpdatesHandler updater;	
					    objects.ObjectLoanClients clientReq;
						SharedData data= new SharedData();
						static Context context;
						int x;
	
public void getUnsentClients(){
	
	database.TableLoaneesHandler handler=new database.TableLoaneesHandler(context);
	handler.open();
	clients=handler.getUnsentLoanees();
	handler.close();
	
	new HttpAsyncTask().execute("http://192.168.1.112:80/icat/index.php/addloanees");
}
	
	
	
public SendLoanClients(Context context){
		
		this.context=context;
		updater=new database.TableUpdatesHandler(context); 		
		
		}


/*public static  objects.ObjectLoanClients getReqObject(){
	
	return clientReq;
}*/


public void sendClientReq(){
	
	
	
	
	//new HttpAsyncTask().execute("http://192.168.1.112:80/icat/index.php/addloanee");
	//new HttpAsyncTask().execute("http://iprocureafrica.co/iCatalog/index.php/addloanee");
	// task.execute("http://192.168.0.102:80/icat/index.php/fetch-all/"+downloadVal);
}

private class HttpAsyncTask extends AsyncTask<String, Void, String> {
    protected String doInBackground(String... urls) {

       /* person = new Person();
        person.setName(etName.getText().toString());
        person.setCountry(etCountry.getText().toString());
        person.setTwitter(etTwitter.getText().toString());*/

        return POST(urls[0]);
    }
    // onPostExecute displays the results of the AsyncTask.
    protected void onPostExecute(String result) {
    	 // Log.d("sent", ""+result);
    	  if(result.equalsIgnoreCase("success")){
    		  
    		  database.TableLoaneesHandler handler=new database.TableLoaneesHandler(context);
    			handler.open();
    			handler.updateSent();
    			handler.close();
    		  
    		  
    	  }
        //Toast.makeText(context, "Data Sent!", Toast.LENGTH_LONG).show();
   }
}



public static String POST(String url){
    InputStream inputStream = null;
    String result = "";
    try {
    //	SharedData data= new SharedData();
    //	objects.ObjectLoanClients clientReq=data.getClient(context);
//
        // 1. create HttpClient
        HttpClient httpclient = new DefaultHttpClient();

        // 2. make POST request to the given URL
        HttpPost httpPost = new HttpPost(url);
        
        JSONArray jsonArray = new JSONArray();

        String json = "";
        // getReqObject();
        // 3. build jsonObject
        
        for(int i=0;i<clients.size();i++){        	
        objects.ObjectLoanClients clientReq=clients.get(i);
        	
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("fname", clientReq.getClName());
        jsonObject.accumulate("mname", clientReq.getClmName());
        jsonObject.accumulate("lname", clientReq.getCllName());
        jsonObject.accumulate("age", clientReq.getClAge());
        jsonObject.accumulate("gender", clientReq.getClGender());
        jsonObject.accumulate("occupation", clientReq.getClOcc());
        jsonObject.accumulate("location", clientReq.getClLoc());
        jsonObject.accumulate("education", clientReq.getClEd());        
        jsonObject.accumulate("dependants", clientReq.getClDep());
        jsonObject.accumulate("id", clientReq.getClId());
        jsonObject.accumulate("phone", clientReq.getClPhone());
        jsonObject.accumulate("email", clientReq.getClEmail()); 
        jsonObject.accumulate("mstatus", clientReq.getClMar()); 
        jsonObject.accumulate("loan", clientReq.getClLoan());
        jsonObject.accumulate("initialfund", clientReq.getClInit());
        jsonObject.accumulate("owners", clientReq.getClOwners());
        jsonObject.accumulate("income", clientReq.getClIncome());
        jsonObject.accumulate("costs", clientReq.getClCosts());
        jsonObject.accumulate("regdate", clientReq.getClRegdate());
        jsonObject.accumulate("lofficer", clientReq.getClLOfficer());
        jsonArray.put(jsonObject);
        }
       
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loanees", jsonArray);
        // 4. convert JSONObject to JSON to String
        json = jsonObject.toString();
        
        Log.i("Json object string",""+json);

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

}
