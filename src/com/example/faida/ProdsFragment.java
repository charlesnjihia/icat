package com.example.faida;


import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import com.example.faida.R;



@SuppressLint("NewApi")
public class ProdsFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
	adapters.ProductsGridAdapter prodad;
	
	database.TableProductsHandler prodshandler;
	int limit,start,supcat;
	int catId,supId,subId;

	static int fragPosition;
	List<objects.ProductsObject> frgProds;
	
	static ProdsFragment init(int pos) {
		ProdsFragment  truitonFrag = new ProdsFragment ();
		
		fragPosition=pos*6;
		//Log.i("Fragment Position",""+fragPosition);
		 // Supply val input as an argument.
		// Bundle args = new Bundle();
		// args.putInt("val", pos);
		 //truitonFrag.setArguments(args);
		 return truitonFrag;
		 }
		 
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
	        Bundle savedInstanceState) {
		
		prodshandler= new database.TableProductsHandler(getActivity());
        prodshandler.open();
		frgProds=prodshandler.getFragProducts(fragPosition);
		prodshandler.close();
		
		 View view = inflater.inflate(R.layout.prods_fragment, 
	              container, false);
		 prodad=new adapters.ProductsGridAdapter(view.getContext(),frgProds) ;
		 GridView prods=(GridView)view.findViewById(R.id.prods);
		 prods.setAdapter(prodad);
		 prods.setOnItemClickListener(new OnItemClickListener() {
		 	    public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
		 	    	 objects.ProductsObject prod=frgProds.get(position);
		 	    	int prodId=(int)prod.get_id();
			       		  Intent prods = new Intent(getActivity(), ProductPreview.class);
			       		 prods.putExtra("prodId",prodId);
			       		  startActivity(prods);
		 	    	}});
		 
		 return view;
		
	
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
