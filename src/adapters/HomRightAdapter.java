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

public class HomRightAdapter extends BaseAdapter {
    private Context mContext;    
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
   // private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private LayoutInflater inflater;
    
    String[] titles={"Shops","Cart","Categories","Users","Offers","Points"};
   public Integer[] mThumbIds = {
            R.drawable.newcat1, R.drawable.newcat2,
            R.drawable.newcat3, R.drawable.newcat4,
            R.drawable.newcat5,R.drawable.newcat6,
            R.drawable.newcat7
            
            
    };

    public  HomRightAdapter(Context c) {
        mContext = c;
       inflater =  (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return  mThumbIds.length;
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
    	
    	
    	
		 View view=inflater.inflate(R.layout.products_rightitem, parent, false);
		 ImageView icon=(ImageView)view.findViewById(R.id.image);
		 icon.setImageResource(mThumbIds[position]);
		// TextView catName = (TextView) view.findViewById(R.id.txtCategory);
		// catName.setText(links[position]);
		
		return view;
    }

    // references to our images
    
    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
    
}



