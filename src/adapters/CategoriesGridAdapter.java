package adapters;

//import com.example.cata.R;


import java.util.List;

import com.example.faida.R;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.widget.TextView;

public class CategoriesGridAdapter extends BaseAdapter {
    private Context mContext;    
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
   // private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private LayoutInflater inflater;
    int i=1;
    
    String[] titles={"Shops","Cart","Categories","Users","Offers","Points"};
 public Integer[] mThumbIds = {
            R.drawable.newcat1, R.drawable.newcat2,
            R.drawable.newcat3, R.drawable.newcat4,
            R.drawable.newcat5, R.drawable.newcat6,
            R.drawable.newcat7
    };
 
 database.TableProdsCatsHandler catHandler;
 List<objects.ObjectProductCats> cats;
 
 public int[] colors={0xffEE6461,0xffF08D54,0xff3CB2A2,0xff3B607D,0xffE8D548,0xff6DB6C9,0xffF08D54,0xff3CB2A2,0xff3B607D};

    public  CategoriesGridAdapter(Context c) {
        mContext = c;
       inflater =  (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       
       catHandler=new database.TableProdsCatsHandler(mContext);
       catHandler.open();
       cats=catHandler.getAllCats();
       catHandler.close();
    }

    public int getCount() {
        return cats.size();
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
    	
		
    	objects.ObjectProductCats cat=cats.get(position);
    	String catname=cat.get_name();
    	
		 View view=inflater.inflate(R.layout.categories_griditem, parent, false);
		 RelativeLayout bg=(RelativeLayout)view.findViewById(R.id.bg);
		 
		 String color=cat.getLight();
		 color=color.replace(" ", "");
		
		 
		bg.setBackgroundColor(Color.parseColor(color));
		 
		
		 ImageView icon=(ImageView)view.findViewById(R.id.caticon);
		 if(position<=6){
	     icon.setImageResource(mThumbIds[position]);
		 }else{
			 icon.setImageResource(mThumbIds[0]);
		 }
	   TextView catName = (TextView) view.findViewById(R.id.title);
	   catName.setText(catname.toUpperCase());
		 //prodName.setText(titles[position]);
		// TextView prodPrice = (TextView) view.findViewById(R.id.prodprice);
		// prodPrice.setText(titles[position]);
	   i++;
		return view;
		
    }

    // references to our images
    /*
    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
    */
}



