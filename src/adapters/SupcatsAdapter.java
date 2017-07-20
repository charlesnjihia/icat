package adapters;

//import com.example.cata.R;


import java.util.ArrayList;
import java.util.List;

import objects.ObjectSupplier;

import com.example.faida.R;


import android.content.Context;
import android.content.res.Configuration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;

public class SupcatsAdapter extends BaseAdapter {
    private Context mContext;    
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
   // private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private LayoutInflater inflater;
    
    database.TableSuppliersHandler supplierHandler;
    database.TableProdsCatsHandler catHandler;
    List<objects.ObjectProductCats> supCats=new ArrayList<objects.ObjectProductCats>();
   // List<objects.ObjectProductCats> supCats;
    objects.ObjectSupplier supplier=new objects.ObjectSupplier();
    
    String[] titles={"Shops","Cart","Categories","Users","Offers","Points"};
   /* public Integer[] mThumbIds = {
            R.drawable.store1, R.drawable.cart_l,
            R.drawable.cat, R.drawable.uzer,
            R.drawable.offer4, R.drawable.offer3
            
    };*/

    public SupcatsAdapter(Context c,int supId) {
        mContext = c;
       inflater =  (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       supplierHandler=new database.TableSuppliersHandler(mContext);
       catHandler=new database.TableProdsCatsHandler(mContext);
       catHandler.open();
		supplierHandler.open();
		supplier=supplierHandler.getSupplier(supId);
	//	supCats=catHandler.getSupplierCats(supId);  ////
		supplierHandler.close();	
		
		objects.ObjectProductCats cate=new objects.ObjectProductCats();
		
		String  supcats=supplier.get_supcats();
		 String[] supcatss=supcats.split(",");
	      // Log.i("size of cats",""+supcatss.length);
	       for(int i=0;i<supcatss.length;i++){
	    	 //  objects.ObjectProductCats cate=new objects.ObjectProductCats();
	    	 int x=Integer.parseInt(supcatss[i]);
	    	// Log.i("x is",""+x);
	    	 cate=catHandler.getCat(x);
	    	 Log.i("Categories about to be added",""+cate.get_name());  
	    	supCats.add(cate);
	    	  
	    	 Log.i("Categories added",""+cate.get_name());   
	       }     
       
       
    }

    public int getCount() {
        return supCats.size();
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
    	
     objects.ObjectProductCats supcat=supCats.get(position);
    	
    	
		 View view=inflater.inflate(R.layout.subcats_listitem, parent, false);;
		TextView subName = (TextView) view.findViewById(R.id.subcat);
		subName.setText(supcat.get_name());
		
		return view;
    }

    // references to our images
    
    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
    
}



