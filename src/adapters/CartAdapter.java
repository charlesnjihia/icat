package adapters;

import java.util.List;

import com.example.faida.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CartAdapter extends BaseAdapter {
	 private LayoutInflater inflater;
	  private Context mContext;    
	  private final List<objects.ObjectCart> cartProds;
	 
	public  CartAdapter(Context c,List<objects.ObjectCart> cartProducts) {
		this.cartProds = cartProducts;
		mContext=c;
		inflater =  (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cartProds.size();
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
		objects.ObjectCart cartProd=cartProds.get(position);
		// TODO Auto-generated method stub
		 View view=inflater.inflate(R.layout.cart_listitem, parent, false);
		 TextView name=(TextView)view.findViewById(R.id.name);
		 name.setText(cartProd.get_name());
		 TextView quantity = (TextView)view.findViewById(R.id.qty);
		 quantity.setText(""+cartProd.get_quantity());
		 TextView wholeCost = (TextView) view.findViewById(R.id.amount);
		 wholeCost.setText(""+cartProd.get_quantity()*cartProd.get_price());
		return view;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
