package com.example.faida;

import java.util.List;

import objects.ObjectLoanClients;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedData {
	
	
	
List<objects.ObjectCart> cartObjects;
objects.ObjectLoanClients client;
	
	public String getAgentCode(Context context){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		String agentCode = sharedPreferences.getString("agentcode", "code");
          return agentCode;

	}
	public void setAgentCode(String key, String value,Context context) {
		key="agentcode";
		    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		        Editor editor = sharedPreferences.edit();
		        editor.putString(key, value);
		         editor.commit();
	  }	
	
	public String getCart(Context context){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		String agentCode = sharedPreferences.getString("cart", "cart");
          return agentCode;

	}
	public void setCart(String key, String value,Context context) {
		key="cart";
	    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
	        Editor editor = sharedPreferences.edit();
	        editor.putString(key, value);
	         editor.commit();
  }
	public int getCategory(Context context){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		int agentCode = sharedPreferences.getInt("categoryId", 0);
          return agentCode;

	}
	public void setCategory(String key, int value,Context context) {
		key="categoryId";
		    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		        Editor editor = sharedPreferences.edit();
		        editor.putInt(key, value);
		         editor.commit();
	  }	
	
	public int getSubcategory(Context context){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		int agentCode = sharedPreferences.getInt("subcategoryId", 0);
          return agentCode;

	}
	public void setSubcategory(String key, int value,Context context) {
		key="subcategoryId";
		    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		        Editor editor = sharedPreferences.edit();
		        editor.putInt(key, value);
		         editor.commit();
	  }	
	
	public int getSupplier(Context context){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		int agentCode = sharedPreferences.getInt("supplierId", 0);
          return agentCode;

	}
	public void setSupplier(String key, int value,Context context) {
		key="supplierId";
		    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		        Editor editor = sharedPreferences.edit();
		        editor.putInt(key, value);
		         editor.commit();
	  }
	
	public long getCustId(Context context){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		Long customerId = sharedPreferences.getLong("customer", -2);
          return customerId;

	}
	public void nullifyCustId(String key, int value,Context context) {
		key="customer";
	    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
	        Editor editor = sharedPreferences.edit();
	        editor.putLong(key, -2);
	        editor.commit();
  }
	
	public void setCustId(String key, Long value,Context context) {
		key="customer";
	    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
	        Editor editor = sharedPreferences.edit();
	        editor.putLong(key, value);
	        editor.commit();
  }
	
	public void setInforPart1(Context context,String fname,String mname,String lname,String id,String phone,int age,String gender,String occ,String loc,String email,String ed,int dep,String mar){
		//this.client=client;
		//key="subcategoryId";
	    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
	        Editor editor = sharedPreferences.edit();
	        editor.putString("fname", fname);
	        editor.putString("mname", mname);
	        editor.putString("lname", lname);
	        editor.putString("id", id);
	        editor.putString("phone", phone);
	        editor.putInt("age", age);
	        editor.putString("gender", gender);
	        editor.putString("occ", occ);
	        editor.putString("loc", loc);
	        editor.putString("email", email);
	        editor.putString("ed", ed);
	        editor.putString("mstatus", mar);
	        editor.putInt("dep", dep);
	        editor.commit();
	}
	
	public void setInforPart2(Context context,Float loan,String init, String owners,Float income,Float costs,String reg, String officer){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        editor.putFloat("loan", loan);
        editor.putString("init", init);
        editor.putString("owners", owners);
        editor.putFloat("income", income);
        editor.putFloat("costs", costs);
        editor.putString("reg", reg);
        editor.putString("lofficer", officer);
        editor.commit();
		
	}
	
	
	
	 public objects.ObjectLoanClients getClient(Context context)
	 {
		 objects.ObjectLoanClients client=new objects.ObjectLoanClients();
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		 client.setClName(sharedPreferences.getString("fname", "0"));
		 client.setClmName(sharedPreferences.getString("mname", "0"));
		 client.setCllName(sharedPreferences.getString("lname", "0"));
		 client.setClId(sharedPreferences.getString("id", "0"));
		 client.setClPhone(sharedPreferences.getString("phone", "0"));
		 client.setClAge(sharedPreferences.getInt("age", 0));
		 client.setClGender(sharedPreferences.getString("gender", "0"));
		 client.setClOcc(sharedPreferences.getString("occ", "0"));
		 client.setClLoc(sharedPreferences.getString("loc", "0"));
		 client.setClEmail(sharedPreferences.getString("email", "0"));
		 client.setClEd(sharedPreferences.getString("ed", "0"));
		 client.setClMar(sharedPreferences.getString("mstatus", "0"));
		 client.setClDep(sharedPreferences.getInt("dep", 0));
		 client.setClLoan(sharedPreferences.getFloat("loan", 0));
		 client.setClInit(sharedPreferences.getString("init", "0"));
		 client.setClOwners(sharedPreferences.getString("owners", "0"));
		 client.setClIncome(sharedPreferences.getFloat("income", 0));
		 client.setClCosts(sharedPreferences.getFloat("costs", 0));
		 client.setClRegdate(sharedPreferences.getString("reg", "0"));
		 client.setClLOfficer(sharedPreferences.getString("lofficer", "0"));
		 return client;
	 }

}
