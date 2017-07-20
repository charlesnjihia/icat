package adapters;

import java.util.List;

import com.example.faida.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SavedAdapter extends BaseAdapter {
	 private LayoutInflater inflater;
	  private Context mContext;   
	  List<objects.ObjectSavedCart> savedCarts;
	 
	 
	public  SavedAdapter(Context c, List<objects.ObjectSavedCart> savedCarts) {
		
		mContext=c;
		this.savedCarts = savedCarts;
		inflater =  (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return savedCarts.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
*/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		objects.ObjectSavedCart cart=savedCarts.get(position);
		// TODO Auto-generated method stub
		 View view=inflater.inflate(R.layout.saved_listitem, parent, false);
		 TextView name=(TextView)view.findViewById(R.id.name);
		 name.setText(cart.get_name());
		 TextView itemsNum = (TextView)view.findViewById(R.id.items);
			itemsNum.setText(""+cart.get_Items()+" items");
		return view;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
