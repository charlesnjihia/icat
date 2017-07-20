package adapters;

//import com.example.cata.R;


import java.util.List;

import com.example.faida.R;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.widget.TextView;

public class SubcatsAdapter extends BaseAdapter {
    private Context mContext;    
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
   // private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private LayoutInflater inflater;
    
    database.TableProdsCatsHandler catHandler;
    List<objects.ObjectSubCat> catSubs;
    objects.ObjectProductCats cat;
    
    String[] titles={"Shops","Cart","Categories","Users","Offers","Points"};
   /* public Integer[] mThumbIds = {
            R.drawable.store1, R.drawable.cart_l,
            R.drawable.cat, R.drawable.uzer,
            R.drawable.offer4, R.drawable.offer3
            
    };*/

    public SubcatsAdapter(Context c,int catId) {
        mContext = c;
       inflater =  (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       catHandler=new database.TableProdsCatsHandler(mContext);
		catHandler.open();
		catSubs=catHandler.getSubCats(catId);
		cat=catHandler.getCat(catId);
		catHandler.close();		
       
       
    }

    public int getCount() {
        return catSubs.size();
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
    	String color=cat.getLight();
    	 color=color.replace(" ", "");
    	objects.ObjectSubCat sub=catSubs.get(position);
    	String subname=sub.get_subName();
    	
		 View view=inflater.inflate(R.layout.subcats_listitem, parent, false);
		 RelativeLayout bg=(RelativeLayout)view.findViewById(R.id.bgg);
		 bg.setBackgroundColor(Color.parseColor(color));
		TextView subName = (TextView) view.findViewById(R.id.subcat);
		subName.setText(subname.toUpperCase());
		
		return view;
    }

    // references to our images
    
    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
    
}



