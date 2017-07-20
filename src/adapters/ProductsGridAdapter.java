package adapters;

//import com.example.cata.R;


import java.util.List;

import com.example.faida.Categories;
import com.example.faida.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;

public class ProductsGridAdapter extends BaseAdapter {
    private Context mContext;    
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
   // private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private LayoutInflater inflater;
    List<objects.ProductsObject> prods;
    int numProds;
    
    String[] titles={"Shops","Cart","Categories","Users","Offers","Points"};
 public Integer[] mThumbIds = {
            R.drawable.sample1, R.drawable.sample2,
            R.drawable.sample3, R.drawable.sample4,
            R.drawable.sample6, R.drawable.sample5
            
    };

    public  ProductsGridAdapter(Context c,List<objects.ProductsObject> prods) {
        mContext = c;
       inflater =  (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       this.prods=prods;
       numProds=prods.size();
    }

    public int getCount() {
        return numProds;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return 5;
    }
    @Override
    public int getItemViewType(int position) {
    	
    	return 1;
    	
    }
  
    
   
    // create a new ImageView for each item referenced by the Adapter
   
	public View getView(int position, View convertView, ViewGroup parent) {
    	
		objects.ProductsObject prod=(objects.ProductsObject) prods.get(position);
		//long id=prod.get_id();
	//	Log.i("Product id",""+id);
		
		String photo=prod.get_prodphoto();		
		 byte[] photoName= Base64.decode(photo, Base64.DEFAULT);
		 String deno=prod.get_deno();
		 String price=""+prod.get_price();
		 String prodname=prod.get_prodname();
    	
		 View view=inflater.inflate(R.layout.products_item, parent, false);;
		 ImageView icon=(ImageView)view.findViewById(R.id.image);
		// icon.setImageResource(mThumbIds[position]);
		 
		 
		 icon.setImageBitmap(BitmapFactory.decodeByteArray( photoName, 
	                0,photoName.length)); 
		 
		 
		 
		// ImageView add=(ImageView)view.findViewById(R.id.adder);
		 
	     TextView prodName = (TextView) view.findViewById(R.id.prodname);
		 prodName.setText(prodname);
		 TextView prodPrice = (TextView) view.findViewById(R.id.prodprice);
	 prodPrice.setText(deno+" "+price);
		 
		 
		/* add.setOnClickListener(new OnClickListener() {
	     	   
	 			public void onClick(View arg0) {
	 				
	 				 Intent det = new Intent( mContext, Categories.class);
	 				 mContext.startActivity(det);
	 				

	 			}}); */
		
		return view;
    }

    // references to our images
    
    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
    
}



