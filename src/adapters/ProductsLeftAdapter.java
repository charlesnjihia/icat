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

public class ProductsLeftAdapter extends BaseAdapter {
    private Context mContext;    
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
   // private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private LayoutInflater inflater;
    
    String[] titles={"HOME","PRODUCTS","CARTS","CATEGORIES","SUPPLIERS","OFFERS","PARTNERS","LOAN"};
    public Integer[] mThumbIds = {
    		 R.drawable.home,
              R.drawable.can_dark,R.drawable.cart, R.drawable.cat,
              R.drawable.sup, R.drawable.offer,
              R.drawable.partner,R.drawable.loan
              
      };

    public  ProductsLeftAdapter(Context c) {
        mContext = c;
       inflater =  (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return titles.length;
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
    	
    	
    	
		 View view=inflater.inflate(R.layout.products_leftitem, parent, false);;
		 ImageView icon=(ImageView)view.findViewById(R.id.listimg);
		 icon.setImageResource(mThumbIds[position]);
	      TextView catName = (TextView) view.findViewById(R.id.listtitle);
		catName.setText(titles[position]);
		
		return view;
    }

    // references to our images
    
    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
    
}



