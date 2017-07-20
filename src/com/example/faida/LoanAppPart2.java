package com.example.faida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import net.CheckNetStatus;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class LoanAppPart2 extends Activity  {
	Spinner init,owners;
	EditText loan,income,costs;
	TextView regdate;
	  String clLoan,clMar,clInit,clOwners,clIncome,clCosts,clRegdate;
	ImageView cal;
	RelativeLayout apply;
	SharedData data= new SharedData();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client_infor2);
		
		loan=(EditText)findViewById(R.id.loan);
		//mar=(EditText)findViewById(R.id.status);
		init=(Spinner)findViewById(R.id.initialf);
		owners=(Spinner)findViewById(R.id.owners);
		income=(EditText)findViewById(R.id.income);
		costs=(EditText)findViewById(R.id.costs);
		regdate=(TextView)findViewById(R.id.regdate);
		apply=(RelativeLayout)findViewById(R.id.apply);
		cal=(ImageView)findViewById(R.id.cal);
		
		apply.setOnClickListener(new OnClickListener() {
	     	   
			public void onClick(View arg0) {
				Boolean isVerified=verifyInput();
				if(isVerified){
					
					net.CheckNetStatus net=new net.CheckNetStatus(LoanAppPart2.this);
					
					boolean isConn=net.chkstatus();
					if(isConn){
					//	net.SendLoanClients lReq=new net.SendLoanClients(LoanAppPart2.this)	;
						//lReq.getUnsentClients();
						
						
						new HttpAsyncTask().execute("http://192.168.1.112:80/icat/index.php/addloanee");
							//net.SendLoanClients lReq=new net.SendLoanClients(LoanAppPart2.this)	;
							
						//	
							
							
							
							//Toast.makeText(getApplicationContext(),"Loan Application Sent !!", 
							//		   Toast.LENGTH_LONG).show();	
							
				
					}else{
						saveClient(false);
						Toast.makeText(getApplicationContext(),"No Internet Connection,Make connection in order to send", 
								   Toast.LENGTH_LONG).show();	
						
						
						
						
					}
					
				// Intent app2 = new Intent(LoanAppPt art2.this, LoanAppPart1.class);
				// det.putExtlcra("prodId",prodId);
	       		 // startActivity(app2);
				}else{
					showErr();
					//Toast.makeText(getApplicationContext(),"Please provided all the required details", 
						//	   Toast.LENGTH_LONG).show();	
				}
				

			}});
		
		cal.setOnClickListener(new OnClickListener() {
	     	   
 			public void onClick(View arg0) {
 				
 				 //start of the dialog
 				final Dialog d =new Dialog(LoanAppPart2.this, R.style.CustomDialog);
 				
 				LayoutInflater inflater =LoanAppPart2.this.getLayoutInflater(); 
				 final View input=inflater.inflate(R.layout.calendar, null);
 				
 				d.setContentView(input);
 				
 				//
 				CalendarView cal=(CalendarView)input.findViewById(R.id.cal);
 				cal.setOnDateChangeListener(new OnDateChangeListener() {
 					
 					@Override
 					public void onSelectedDayChange(CalendarView view, int year, int month,
 							int dayOfMonth) {
 						// TODO Auto-generated method stub
 						month+=1;
 						regdate.setText(year+"-"+month+"-"+dayOfMonth);
 						d.cancel();
 						
 					}
 				});
 			
 				// show it
 				d.getWindow().setLayout(600, 400);
 				d.setCanceledOnTouchOutside(true);
 				d.show(); 

 				}});
		
		
		
	}
	
	public boolean verifyInput(){
		clLoan=loan.getText().toString();
		clInit= String.valueOf(init.getSelectedItem());
		//clInit=init.getText().toString();
		clOwners= String.valueOf(owners.getSelectedItem());
		//clOwners=owners.getText().toString();
		clIncome=income.getText().toString();
		clCosts=costs.getText().toString();
		clRegdate=regdate.getText().toString();
		
		if(clIncome.length()<1||clCosts.length()<1||clRegdate.length()<1||clLoan.length()<1){
			return false;
		}else if(!(clInit.equalsIgnoreCase("loan")||clInit.equalsIgnoreCase("savings")||clInit.equalsIgnoreCase("daily income"))){
			
		}else if(!(clOwners.equalsIgnoreCase("solely owned")||clOwners.equalsIgnoreCase("jointly owned"))){
			
		}
				
		objects.ObjectLoanClients client=new objects.ObjectLoanClients();
		Float clLoane=Float.parseFloat(clLoan);
		//int clOwnerse=Integer.parseInt(clOwners);
		Float clIncomee=Float.parseFloat(clIncome);
		Float clCostse=Float.parseFloat(clCosts);
		
	 //  client=data.getClient();
		/*
		client.setClLoan(Float.parseFloat(clLoan));
		client.setClInit(clInit);
		client.setClOwners(Integer.parseInt(clOwners));
		client.setClIncome(Float.parseFloat(clIncome));
		client.setClCosts(Float.parseFloat(clCosts));
		client.setClRegdate(clRegdate);
		
		*/
		data.setInforPart2(LoanAppPart2.this, clLoane, clInit, clOwners, clIncomee,clCostse,clRegdate,"Joan");
		//data.setClient(client);
		/*
		 * 
		 * 
		 */
		return true;
		
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
	    	  Log.d("sent", ""+result);
	    	  
	    	  if(result.length()>0){
	    		  showDia(result);
	    		  saveClient(true);
	    		  //Toast.makeText(LoanAppPart2.this, result, Toast.LENGTH_LONG).show();
	    	  }
	    	  
	        //Toast.makeText(context, "Data Sent!", Toast.LENGTH_LONG).show();
	   }
	}
	

public  String POST(String url){
    InputStream inputStream = null;
    String result = "";
    try {
    	SharedData data= new SharedData();
    	objects.ObjectLoanClients clientReq=data.getClient(LoanAppPart2.this);

        // 1. create HttpClient
        HttpClient httpclient = new DefaultHttpClient();

        // 2. make POST request to the given URL
        HttpPost httpPost = new HttpPost(url);

        String json = "";
        // getReqObject();
        // 3. build jsonObject
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
       // jsonObject.accumulate("email", clientReq.getClEmail()); 
        jsonObject.accumulate("mstatus", clientReq.getClMar()); 
        jsonObject.accumulate("loan", clientReq.getClLoan());
        jsonObject.accumulate("initialfund", clientReq.getClInit());
        jsonObject.accumulate("owners", clientReq.getClOwners());
        jsonObject.accumulate("income", clientReq.getClIncome());
        jsonObject.accumulate("costs", clientReq.getClCosts());
        jsonObject.accumulate("regdate", clientReq.getClRegdate());
        jsonObject.accumulate("lofficer", clientReq.getClLOfficer());
        
        
       

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
public void saveClient( boolean sent){	
	SharedData data= new SharedData();
	objects.ObjectLoanClients clientReq=data.getClient(LoanAppPart2.this);
	
	database.TableLoaneesHandler handler=new	database.TableLoaneesHandler(LoanAppPart2.this);
	handler.open();
	handler.addLoanee(clientReq, sent);
	handler.close();
	
}

public void showDia(String id){
	
	
	 //start of the dialog
	Dialog d =new Dialog(LoanAppPart2.this, R.style.CustomDialog);
	
	LayoutInflater inflater =LoanAppPart2.this.getLayoutInflater(); 
	 final View input=inflater.inflate(R.layout.app_sent, null);
	 d .setContentView(input);
	
	//d.setContentView(R.layout.app_sent);
	
	// LayoutInflater inflater =LoanAppPart2.this.getLayoutInflater(); 
	//	 final View input=inflater.inflate(R.layout.gallery, null);
	//	 d .setContentView(input);
	
	final TextView conf=(TextView) input.findViewById(R.id.conf);
	conf.setText("APPLICATION NO: "+id);

	// show it
	d.getWindow().setLayout(600, 250);
	d.show(); 

	
}

public void showErr(){
	
	
	 //start of the dialog
	Dialog d =new Dialog(LoanAppPart2.this, R.style.CustomDialog);
	
	LayoutInflater inflater =LoanAppPart2.this.getLayoutInflater(); 
	 final View input=inflater.inflate(R.layout.no_input, null);
	 d .setContentView(input);
	
	//d.setContentView(R.layout.app_sent);
	
	// LayoutInflater inflater =LoanAppPart2.this.getLayoutInflater(); 
	//	 final View input=inflater.inflate(R.layout.gallery, null);
	//	 d .setContentView(input);
	
	//final TextView conf=(TextView) input.findViewById(R.id.conf);
	//conf.setText("APPLICATION NO: "+id);

	// show it
	d.getWindow().setLayout(600, 250);
	d.setCanceledOnTouchOutside(true);
	d.show(); 

	
}

}
