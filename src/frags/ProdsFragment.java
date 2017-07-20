package frags;
import java.util.List;
import com.example.faida.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

@SuppressLint("NewApi")
public class ProdsFragment extends Fragment  {
	adapters.ProductsGridAdapter prodad;
	List<objects.ProductsObject> frgProds;
	
	static ProdsFragment  init(int val) {
		ProdsFragment  truitonFrag = new ProdsFragment ();
		 // Supply val input as an argument.
		 Bundle args = new Bundle();
		 args.putInt("val", val);
		 truitonFrag.setArguments(args);
		 return truitonFrag;
		 }
		 
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
	        Bundle savedInstanceState) {
		 View view = inflater.inflate(R.layout.prods_fragment, 
	              container, false);
		 prodad=new adapters.ProductsGridAdapter(view.getContext(), frgProds) ;
		 GridView prods=(GridView)view.findViewById(R.id.prods);
		 prods.setAdapter(prodad);
		 
		
		 
		 
		 return view;
		
	
	}
	
	

}
