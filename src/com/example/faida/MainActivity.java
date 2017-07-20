package com.example.faida;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView subs;
	adapters.SubcatsAdapter subcats;
	
	EditText name,age,gender,occ,loc,loan,mar,ed,dep,init,owners,income,costs,regdate,id,phone;
  String clName,clAge,clGender,clOcc,clLoc,clLoan,clMar,clEd,clDep,clInit,clOwners,clIncome,clCosts,clRegdate, clId,clPhone;
	Button register;
  
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client_infor);
		
		name=(EditText)findViewById(R.id.name);
		id=(EditText)findViewById(R.id.id);
		phone=(EditText)findViewById(R.id.fone);
		age=(EditText)findViewById(R.id.age);
		gender=(EditText)findViewById(R.id.gender);
		occ=(EditText)findViewById(R.id.occupation);
		loc=(EditText)findViewById(R.id.location);
		loan=(EditText)findViewById(R.id.amount);
		mar=(EditText)findViewById(R.id.status);
		ed=(EditText)findViewById(R.id.education);
		dep=(EditText)findViewById(R.id.dep);
		init=(EditText)findViewById(R.id.initial);
		owners=(EditText)findViewById(R.id.owners);
		income=(EditText)findViewById(R.id.income);
		costs=(EditText)findViewById(R.id.costs);
		regdate=(EditText)findViewById(R.id.regdate);
		register=(Button)findViewById(R.id.reg);
		//subs=(ListView)findViewById(R.id.subs);
		//subcats=new adapters.SubcatsAdapter(this,1) ;
		//subs.setAdapter(subcats);
				
		Boolean isVerified=verifyInput();
		if(isVerified){
			
			net.CheckNetStatus stat=new net.CheckNetStatus(this);
			Boolean isConnected=stat.chkstatus();
			
			if(isConnected){
				sendClientData();
			
				
				
			}
			else{
				
			}
			
			
			
			
			
		}else{
			
		}
		
	}

  public void sendClientData(){
		objects.ObjectLoanClients client=new objects.ObjectLoanClients(); 
		client.setClName(clName);
		client.setClAge(Integer.parseInt(clAge));
		client.setClCosts(Float.parseFloat(clCosts));
		client.setClDep(Integer.parseInt(clDep));
		client.setClEd(clEd);
		client.setClGender(clGender);
		client.setClId(clId);
		client.setClIncome(Float.parseFloat(clIncome));
		client.setClInit(clInit);
		client.setClLoan(Float.parseFloat(clLoan));
		client.setClLoc(clLoc);
		client.setClMar(clMar);
		client.setClOcc(clOcc);
		//client.setClOwners(Integer.parseInt(clOwners));
		client.setClPhone(clPhone);
		client.setClRegdate(clRegdate);
		
		
		
		
  }
  
  
  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public boolean verifyInput(){
		clName=name.getText().toString();
		clId=id.getText().toString();
		clPhone=phone.getText().toString();
		clAge=age.getText().toString();
		clGender=gender.getText().toString();
		clOcc=occ.getText().toString();
		clLoc=loc.getText().toString();
		clLoan=loan.getText().toString();
		clMar=mar.getText().toString();
		clEd=ed.getText().toString();
		clDep=dep.getText().toString();
		clInit=init.getText().toString();
		clOwners=owners.getText().toString();
		clIncome=income.getText().toString();
		clCosts=costs.getText().toString();
		clRegdate=regdate.getText().toString();
		
	if(clName.length()<1||clAge.length()<1||clGender.length()<1||clLoc.length()<1||clLoan.length()<1){
		return false;
	}else if(clMar.length()<1||clEd.length()<1||clDep.length()<1||clInit.length()<1||clOwners.length()<1){
		return false;
	}else if(clIncome.length()<1||clCosts.length()<1||clRegdate.length()<1||clId.length()<1||clPhone.length()<1){
		return false;
	}
		
	return true;	
	}
	

}
