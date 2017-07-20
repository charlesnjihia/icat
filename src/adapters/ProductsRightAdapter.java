package adapters;

//import com.example.cata.R;


import com.example.faida.R;

import android.content.Context;
import android.content.res.Configuration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;

public class ProductsRightAdapter extends BaseAdapter {
    private Context mContext;    
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
   // private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private LayoutInflater inflater;
    
    String[] titles={"Shops","Cart","Categories","Users","Offers","Points"};
    public Integer[] mThumbIds = {
            R.drawable.trac, R.drawable.chaff,
            R.drawable.water, R.drawable.light,
            R.drawable.sun,
            R.drawable.cart,R.drawable.home,
            
            
    };

    public  ProductsRightAdapter(Context c) {
        mContext = c;
       inflater =  (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
    	 return  10;
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
    	
    	
    	
		 View view=inflater.inflate(R.layout.products_rightitem, parent, false);;
		 ImageView icon=(ImageView)view.findViewById(R.id.image);
		 //icon.setImageResource(mThumbIds[position]);
		return view;
    }

    // references to our images
    
    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
    
}



