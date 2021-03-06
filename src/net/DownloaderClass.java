package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DownloaderClass {
	
	
	database.TableUpdatesHandler updater;
	static utilities.InitialInput input;
	static int downloadVal=0;
	static String tableName;
	public DownloaderClass(Context context){
		
		input=new utilities.InitialInput(context);
		updater=new database.TableUpdatesHandler(context); 		
		
		}
	
	public void performDownload(){
		updater.open();
		updater.updateTableDate();	
		
		downloadStuff(downloadVal) ; 			
		
	}
	
	
		
		 
		  
		  public static void downloadStuff(int downloadVal)  {
			  Log.i("Trying to perform download","Download");
			   GetIcatData task = new GetIcatData();
		// task.execute("http://192.168.9.102:80/icat/index.php/fetch-all/"+downloadVal);
		   // task.execute("http://iprocureafrica.co/iCatalog/index.php/fetch-all");
		        }
		  
		  private static class GetIcatData extends AsyncTask<String, Void, String> {
	            @Override
	            protected String doInBackground(String... urls) {
	            	String response=POST(urls[0],"charles");
	            	
	            		return	response;
	            }
	            
	            public static String POST(String url, String person){
	    	        InputStream inputStream = null;
	    	        String result= "";
	    	        try {
	    	 
	    	            // 1. create HttpClient
	    	            HttpClient httpclient = new DefaultHttpClient();
	    	 
	    	            // 2. make POST request to the given URL
	    	            HttpPost httpPost = new HttpPost(url);
	    	 
	    	            String json = "";
	    	 
	    	            // 3. build jsonObject
	    	            JSONObject jsonObject = new JSONObject();
	    	            jsonObject.accumulate("name", person);
	    	           
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
	            
	            @Override
	            protected void onPostExecute(String result) {
	             // textView.setText(result);
	            	Log.i("result",""+result.length());
	            	Log.i("result",""+result);
	            	
	            	try {
						JSONObject	fetchtres = new JSONObject(result);
						JSONArray supplierz = fetchtres.getJSONArray("suppliers");
						JSONArray products = fetchtres.getJSONArray("products");
						JSONArray categories= fetchtres.getJSONArray("cats");
						JSONArray subcats= fetchtres.getJSONArray("subcats");
						JSONArray geocodes= fetchtres.getJSONArray("geocodes");
						JSONArray agents= fetchtres.getJSONArray("agents");
						JSONArray photos= fetchtres.getJSONArray("photos");
						
						int prodLength=products.length();
						int imagesLength= photos.length();
						Log.i("image lenght",""+imagesLength);
						input.addSuppliers(supplierz);
						input.addProducts(products);
						input.addCats(categories);
						input.addSubcats(subcats);
						input.addGeocodes(geocodes);
						input.addAgents(agents);
						input.addPhotos(photos);
						
						if(prodLength==5||imagesLength==5){
							downloadVal++;
							downloadStuff(downloadVal) ;
							}
						
						
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	            	
	            	
	            	
	            	
	            	
	            }
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
	
	
	
	


