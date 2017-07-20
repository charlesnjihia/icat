package adapters;

//import com.example.cata.R;


import java.util.List;

import com.example.faida.R;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;

public class SuppliersGridAdapter extends BaseAdapter {
    private Context mContext;    
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
   // private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private LayoutInflater inflater;
    
    String[] titles={"Shops","Cart","Categories","Users","Offers","Points"};
 public Integer[] mThumbIds = {
            R.drawable.sample1, R.drawable.sample2,
            R.drawable.sample3, R.drawable.sample4,
            R.drawable.sample6, R.drawable.sample5
            
    };
 database.TableSuppliersHandler supsHandler;
	List<objects.ObjectSupplier> suppliers;


    public  SuppliersGridAdapter(Context c) {
        mContext = c;
       inflater =  (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       supsHandler=new database.TableSuppliersHandler(mContext);
		supsHandler.open();
		suppliers=supsHandler.getSuppliers();
		supsHandler.close();
    }

    public int getCount() {
        return suppliers.size();
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
    	
    	objects.ObjectSupplier sup=suppliers.get(position);
    	
		 View view=inflater.inflate(R.layout.suppliers_griditem, parent, false);;
		 ImageView icon=(ImageView)view.findViewById(R.id.supicon);
		 String iconString=sup.get_logo();
		 byte[] photoName= Base64.decode(iconString, Base64.DEFAULT);
			Bitmap supFoto= BitmapFactory.decodeByteArray( photoName, 
			                0,photoName.length);
			icon.setImageBitmap(supFoto);
		 
		 
		// icon.setImageResource(mThumbIds[position]);
	     //TextView prodName = (TextView) view.findViewById(R.id.prodname);
		 //prodName.setText(titles[position]);
		// TextView prodPrice = (TextView) view.findViewById(R.id.prodprice);
		// prodPrice.setText(titles[position]);
		
		return view;
    }

    // references to our images
    
    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
    
}



