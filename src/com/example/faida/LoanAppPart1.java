package com.example.faida;
import objects.ObjectLoanClients;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoanAppPart1 extends Activity {
	Spinner gender,mar,loc;
	EditText fname,mname,lname,age,occ,ed,dep,id,phone,email;
	String clfName,clmName,cllName,clAge,clGender,clOcc,clLoc,clEd,clDep, clId,clPhone,clEmail,clMar;
	RelativeLayout next;
	SharedData data= new SharedData();
	ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client_infor1);
		
		fname=(EditText)findViewById(R.id.fname);
		mname=(EditText)findViewById(R.id.mname);
		lname=(EditText)findViewById(R.id.lname);		
		id=(EditText)findViewById(R.id.id);
		phone=(EditText)findViewById(R.id.fone);
		age=(EditText)findViewById(R.id.age);
		gender=(Spinner)findViewById(R.id.gender);
		occ=(EditText)findViewById(R.id.occupation);
		loc=(Spinner)findViewById(R.id.location);
		//email=(EditText)findViewById(R.id.email);
		ed=(EditText)findViewById(R.id.education);
		dep=(EditText)findViewById(R.id.dep);		
		mar=(Spinner)findViewById(R.id.married);
		back=(ImageView)findViewById(R.id.lyb);
	
		
		
		
		
		
		next=(RelativeLayout) findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {
	     	   
			public void onClick(View arg0) {
				Boolean isVerified=verifyInput();
				if(isVerified){					
				 Intent app2 = new Intent(LoanAppPart1.this, LoanAppPart2.class);
				// det.putExtra("prodId",prodId);
	       		  startActivity(app2);
				}else{
					
					showDia();
					//Toast.makeText(getApplicationContext(),"Please provided all the required details", 
						//	   Toast.LENGTH_LONG).show();	
				}
				

			}});
		
		back.setOnClickListener(new OnClickListener() {
	     	   
			public void onClick(View arg0) {
				
				onBackPressed();
				

			}});
		
	}
	public boolean verifyInput(){
		clfName=fname.getText().toString();
		clmName=mname.getText().toString();
		cllName=lname.getText().toString();
		clId=id.getText().toString();
		clPhone=phone.getText().toString();
		clAge=age.getText().toString();
		clGender= String.valueOf(gender.getSelectedItem());
		clLoc=String.valueOf(loc.getSelectedItem());//loc.getText().toString();
		clOcc=occ.getText().toString();
		//clEmail=email.getText().toString();
		clDep=dep.getText().toString();
		clEd=ed.getText().toString();
		clMar=String.valueOf(mar.getSelectedItem());
		/*
		 *clLoan=loan.getText().toString();
		 *clMar=mar.getText().toString();
		clInit=init.getText().toString();
		clOwners=owners.getText().toString();
		clIncome=income.getText().toString();
		clCosts=costs.getText().toString();
		clRegdate=regdate.getText().toString();*/
		
	if(clfName.length()<1||clAge.length()<1||clLoc.length()<1){
		return false;
	}else if(clmName.length()<1||clEd.length()<1||clDep.length()<1||cllName.length()<1){
		return false;
	}else if(clId.length()<1||clPhone.length()<1){
		return false;
	}else if(!(clGender.equalsIgnoreCase("male")||clGender.equalsIgnoreCase("female"))){
		return false;
	}
	else if(!(clMar.equalsIgnoreCase("single")||clMar.equalsIgnoreCase("married"))){
		return false;
	}
	objects.ObjectLoanClients client=new objects.ObjectLoanClients();

	int clAgee=Integer.parseInt(clAge);
	 int clDepp=Integer.parseInt(clDep);
	
	/*client.setCllNat me(clfName);
	client.setClmName(clmName);
	client.setCllName(cllName);
	client.setClAge(Integer.parseInt(clAge));
	client.setClLoc(clLoc);
	client.setClEmail(clEmail);
	client.setClEd(clEd);
	client.setClDep(Integer.parseInt(clDep));
	client.setClId(clId);
	client.setClPhone(clPhone);*/
	data.setInforPart1(LoanAppPart1.this, clfName, clmName, cllName, clId, clPhone, clAgee, clGender, clOcc, clLoc,"", clEd, clDepp,clMar);
	//data.setClient(client);
	return true;	
	}
	
	public void showDia(){
		
		
		 //start of the dialog
		Dialog d =new Dialog(LoanAppPart1.this, R.style.CustomDialog);
		
		LayoutInflater inflater =LoanAppPart1.this.getLayoutInflater(); 
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
