package com.example.faida;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class CheckOut extends Activity{
	
	ListView cart;
	adapters.CheckOutAdapter chkadapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart_check);
		
		cart=(ListView)findViewById(R.id.cart);
		chkadapter=new adapters.CheckOutAdapter(this);
		cart.setAdapter(chkadapter);
		
	}

}
