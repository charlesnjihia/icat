package utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class InitialInput {
	//ObjectSupplier sup;
	database.TableSuppliersHandler sups;
	database.TableProductsHandler prodhandle;
	database.TableProdsCatsHandler prodcat;
	database.TableGeoHandler geocode;
	database.TableAgentsHandler agee;
	database.TablePhotosHandler fotoz;
	database.TableUpdatesHandler updater;
	database.TableCustomersHandler customer;
	
	public InitialInput(Context context){
	sups=new database.TableSuppliersHandler(context);
	sups.open();
	prodhandle=new database.TableProductsHandler(context);
	//prodhandle.open();
	prodcat=new database.TableProdsCatsHandler(context);
	prodcat.open();
	geocode=new database.TableGeoHandler(context);
	geocode.open();
	agee=new database.TableAgentsHandler(context) ;
	agee.open();
	fotoz=new database.TablePhotosHandler(context);
	fotoz.open();
	updater=new database.TableUpdatesHandler(context);
	//updater.open();
	customer=new database.TableCustomersHandler(context);
	customer.open();
	}
	
	public void addSuppliers(JSONArray suppliers){
		objects.ObjectSupplier sup=new objects.ObjectSupplier();
		objects.ObjectUpdate update=new objects.ObjectUpdate();
		Log.i("Supplier start",""+suppliers.length());
	
		int length=suppliers.length();
		
		for(int i=0;i<length;i++){
			try {
				JSONObject supp=suppliers.getJSONObject(i);
				Log.i("object gotten with lenth",""+supp.length());
				String id=supp.getString("id");
				Long supId=Long.parseLong(id);
				String name=supp.getString("name");
				String logo=supp.getString("coded_logo");
				String cats=supp.getString("categories");
				
				Log.i("all values gotten","all values gotten");
				
				sup.set_id(supId);
				sup.set_name(name);
				sup.set_logo(logo);
				sup.set_supcats(cats);
				Log.i("Object Instantiated","Object Instantiated");
				
				sups.addSupplier(sup);
				
				Log.i("Supplier added",""+sup.get_name());
				
				//Log.i("all values gotten","all values gotten");
				/*
				sup.set_id(Long.parseLong(supp.getString("id")));
				Log.i("id gotten","id gotten");
				sup.set_name(supp.getString("name"));
				Log.i("name gotten","name gotten");
				//sup.set_logo(supp.getString("coded_logo"));
				Log.i("logo gotten","logo gotten");
				sup.set_supcats(supp.getString("categories"));
				Log.i("cats","cats gotten");
			//	sups.addSupplier(sup);
				*/
				//Log.i("Supplier added",""+sup.get_name());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		sups.close();
		if(length>0){
		update.setTableName("Suppliers");
		update.setLastId(sup.get_id());
		updater.open();
		updater.updateTable(update);
		updater.close();
		}
		
}
	
	public void updateSuppliers(JSONArray suppliers){
		objects.ObjectSupplier sup=new objects.ObjectSupplier();
		
		Log.i("Supplier start",""+suppliers.length());
	
		int length=suppliers.length();
		
		for(int i=0;i<length;i++){
			try {
				JSONObject supp=suppliers.getJSONObject(i);
				Log.i("object gotten with lenth",""+supp.length());
				String id=supp.getString("id");
				Long supId=Long.parseLong(id);
				String name=supp.getString("name");
				String logo=supp.getString("coded_logo");
				String cats=supp.getString("categories");
				
				Log.i("all values gotten","all values gotten");
				
				sup.set_id(supId);
				sup.set_name(name);
				sup.set_logo(logo);
				sup.set_supcats(cats);
				Log.i("Object Instantiated","Object Instantiated");
				
				sups.updateSupplier(sup);
				
				Log.i("Supplier updated",""+sup.get_name());
				
				//Log.i("all values gotten","all values gotten");
				/*
				sup.set_id(Long.parseLong(supp.getString("id")));
				Log.i("id gotten","id gotten");
				sup.set_name(supp.getString("name"));
				Log.i("name gotten","name gotten");
				//sup.set_logo(supp.getString("coded_logo"));
				Log.i("logo gotten","logo gotten");
				sup.set_supcats(supp.getString("categories"));
				Log.i("cats","cats gotten");
			//	sups.addSupplier(sup);
				*/
				//Log.i("Supplier added",""+sup.get_name());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		sups.close();
		updater.updateTableDate("Suppliers");
		
}
	
	
	
	
public void addProducts(JSONArray products){
	prodhandle.open();
	objects.ProductsObject prod=new objects.ProductsObject();
	objects.ObjectUpdate update=new objects.ObjectUpdate();
	int length=products.length();
	for(int i=0;i<length;i++){
		
		try {
			JSONObject prodd=products.getJSONObject(i);
			//Log.i("object gotten with lenth",""+supp.length());
			/*String id=supp.getString("id");
			Long supId=Long.parseLong(id);
			String name=supp.getString("name");
			String logo=supp.getString("coded_logo");
			String cats=supp.getString("categories");
			
			Log.i("all values gotten","all values gotten");*/
			
			prod.set_id(prodd.getLong("id"));
			prod.set_prodname(prodd.getString("prod_name"));
			prod.set_ownerid(prodd.getLong("owner_id"));
			prod.set_desc(prodd.getString("prod_desc"));
			prod.set_price(prodd.getLong("iprocure_price"));
			prod.set_deno(prodd.getString("price_deno"));
			prod.set_prodphoto(prodd.getString("coded_photo"));
			prod.set_prodcat(prodd.getLong("prod_cat"));
			prod.set_subcat(prodd.getLong("prod_subcat"));
			prod.set_availTime(prodd.getLong("aval_time"));
			prod.set_availUnits(prodd.getString("dur_units"));
			prod.set_minOrder(prodd.getLong("min_order"));
			prod.set_orderUnits(prodd.getString("order_units"));
			
		    long k=	prodhandle.createProduct(prod);
						
			Log.i("product added",""+prod.get_prodname());
			//prodhandle.close();
			//Log.i("all values gotten","all values gotten");
			/*
			sup.set_id(Long.parseLong(supp.getString("id")));
			Log.i("id gotten","id gotten");
			sup.set_name(supp.getString("name"));
			Log.i("name gotten","name gotten");
			//sup.set_logo(supp.getString("coded_logo"));
			Log.i("logo gotten","logo gotten");
			sup.set_supcats(supp.getString("categories"));
			Log.i("cats","cats gotten");
		//	sups.addSupplier(sup);
			*/
			//Log.i("Supplier added",""+sup.get_name());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	prodhandle.close();
	if(length>0){
	update.setTableName("Products");
	update.setLastId(prod.get_id());
	updater.open();
	updater.updateTable(update);
	updater.close();
	}
}


public void updateProducts(JSONArray products){
	prodhandle.open();
	objects.ProductsObject prod=new objects.ProductsObject();
	
	int length=products.length();
	for(int i=0;i<length;i++){
		
		try {
			JSONObject prodd=products.getJSONObject(i);
			//Log.i("object gotten with lenth",""+supp.length());
			/*String id=supp.getString("id");
			Long supId=Long.parseLong(id);
			String name=supp.getString("name");
			String logo=supp.getString("coded_logo");
			String cats=supp.getString("categories");
			
			Log.i("all values gotten","all values gotten");*/
			
			prod.set_id(prodd.getLong("id"));
			prod.set_prodname(prodd.getString("prod_name"));
			prod.set_ownerid(prodd.getLong("owner_id"));
			prod.set_desc(prodd.getString("prod_desc"));
			prod.set_price(prodd.getLong("iprocure_price"));
			prod.set_deno(prodd.getString("price_deno"));
			prod.set_prodphoto(prodd.getString("coded_photo"));
			prod.set_prodcat(prodd.getLong("prod_cat"));
			prod.set_subcat(prodd.getLong("prod_subcat"));
			prod.set_availTime(prodd.getLong("aval_time"));
			prod.set_availUnits(prodd.getString("dur_units"));
			prod.set_minOrder(prodd.getLong("min_order"));
			prod.set_orderUnits(prodd.getString("order_units"));
			
		   prodhandle.updateProduct(prod);
						
			Log.i("product updated",""+prod.get_prodname());
			//prodhandle.close();
			//Log.i("all values gotten","all values gotten");
			/*
			sup.set_id(Long.parseLong(supp.getString("id")));
			Log.i("id gotten","id gotten");
			sup.set_name(supp.getString("name"));
			Log.i("name gotten","name gotten");
			//sup.set_logo(supp.getString("coded_logo"));
			Log.i("logo gotten","logo gotten");
			sup.set_supcats(supp.getString("categories"));
			Log.i("cats","cats gotten");
		//	sups.addSupplier(sup);
			*/
			//Log.i("Supplier added",""+sup.get_name());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	prodhandle.close();
	updater.updateTableDate("Products");
	
}





public void addCats(JSONArray cats){
	objects.ObjectProductCats cat=new objects.ObjectProductCats();
	objects.ObjectUpdate update=new objects.ObjectUpdate();
	

	int length=cats.length();
	
	for(int i=0;i<length;i++){
		try {
			JSONObject catt=cats.getJSONObject(i);
			
			
			cat.set_id(catt.getLong("id"));
			cat.set_name(catt.getString("cat_name"));
			cat.set_photo(catt.getString("coded_logo"));
			cat.setLight(catt.getString("light_code"));
			cat.setDark(catt.getString("dark_code"));
			
			
			prodcat.createCategories(cat);			
			Log.i("Category added",""+cat.get_name());
			
			//Log.i("all values gotten","all values gotten");
			/*
			sup.set_id(Long.parseLong(supp.getString("id")));
			Log.i("id gotten","id gotten");
			sup.set_name(supp.getString("name"));
			Log.i("name gotten","name gotten");
			//sup.set_logo(supp.getString("coded_logo"));
			Log.i("logo gotten","logo gotten");
			sup.set_supcats(supp.getString("categories"));
			Log.i("cats","cats gotten");
		//	sups.addSupplier(sup);
			*/
			//Log.i("Supplier added",""+sup.get_name());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	if(length>0){
	update.setTableName("Categories");
	update.setLastId(cat.get_id());
	updater.open();
	updater.updateTable(update);
	updater.close();
	}
	
}
public void updateCats(JSONArray cats){
	objects.ObjectProductCats cat=new objects.ObjectProductCats();
	
	

	int length=cats.length();
	
	for(int i=0;i<length;i++){
		try {
			JSONObject catt=cats.getJSONObject(i);
			
			
			cat.set_id(catt.getLong("id"));
			cat.set_name(catt.getString("cat_name"));
			cat.set_photo(catt.getString("coded_logo"));
			
			
			
			prodcat.updateCategories(cat);			
			Log.i("Category updated",""+cat.get_name());
			
			//Log.i("all values gotten","all values gotten");
			/*
			sup.set_id(Long.parseLong(supp.getString("id")));
			Log.i("id gotten","id gotten");
			sup.set_name(supp.getString("name"));
			Log.i("name gotten","name gotten");
			//sup.set_logo(supp.getString("coded_logo"));
			Log.i("logo gotten","logo gotten");
			sup.set_supcats(supp.getString("categories"));
			Log.i("cats","cats gotten");
		//	sups.addSupplier(sup);
			*/
			//Log.i("Supplier added",""+sup.get_name());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	updater.updateTableDate("Categories");
	
}


public void addSubcats(JSONArray subcats){
	objects.ObjectSubCat subcat=new objects.ObjectSubCat();
	objects.ObjectUpdate update=new objects.ObjectUpdate();
	int length=subcats.length();
	
	for(int i=0;i<length;i++){
		try {
			JSONObject catt=subcats.getJSONObject(i);
				
			subcat.set_subid(catt.getLong("id"));
			subcat.set_catId(catt.getLong("cat_id"));
			subcat.set_subName(catt.getString("sub_name"));
						
			prodcat.createSubCategories(subcat);
			
			Log.i("SubCategory added",""+subcat.get_subName());
			
			//Log.i("all values gotten","all values gotten");
			/*
			sup.set_id(Long.parseLong(supp.getString("id")));
			Log.i("id gotten","id gotten");
			sup.set_name(supp.getString("name"));
			Log.i("name gotten","name gotten");
			//sup.set_logo(supp.getString("coded_logo"));
			Log.i("logo gotten","logo gotten");
			sup.set_supcats(supp.getString("categories"));
			Log.i("cats","cats gotten");
		//	sups.addSupplier(sup);
			*/
			//Log.i("Supplier added",""+sup.get_name());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	if(length>0){
	update.setTableName("Subcats");
	update.setLastId(subcat.get_subid());
	updater.open();
	updater.updateTable(update);
	updater.close();
	}
	
	
	
}

public void updateSubcats(JSONArray subcats){
	objects.ObjectSubCat subcat=new objects.ObjectSubCat();
	
	int length=subcats.length();
	
	for(int i=0;i<length;i++){
		try {
			JSONObject catt=subcats.getJSONObject(i);
				
			subcat.set_subid(catt.getLong("id"));
			subcat.set_catId(catt.getLong("cat_id"));
			subcat.set_subName(catt.getString("sub_name"));
						
			prodcat.updateSubCategories(subcat);
			
			Log.i("SubCategory updated",""+subcat.get_subName());
			
			//Log.i("all values gotten","all values gotten");
			/*
			sup.set_id(Long.parseLong(supp.getString("id")));
			Log.i("id gotten","id gotten");
			sup.set_name(supp.getString("name"));
			Log.i("name gotten","name gotten");
			//sup.set_logo(supp.getString("coded_logo"));
			Log.i("logo gotten","logo gotten");
			sup.set_supcats(supp.getString("categories"));
			Log.i("cats","cats gotten");
		//	sups.addSupplier(sup);
			*/
			//Log.i("Supplier added",""+sup.get_name());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	
	updater.updateTableDate("Subcats");
	
	
}
	

public void addGeocodes(JSONArray geocodes){
	objects.ObjectGeocodes geo=new objects.ObjectGeocodes();
	
	int length=geocodes.length();
	
	for(int i=0;i<length;i++){
		try {
			JSONObject gee=geocodes.getJSONObject(i);
				
			
			geo.set_code(gee.getLong("iprocureid"));
			geo.set_name(gee.getString("name"));
			
						
			geocode.addGeoCode(geo);
			
			Log.i("Geocode added",""+geo.get_name());
			
			//Log.i("all values gotten","all values gotten");
			/*
			sup.set_id(Long.parseLong(supp.getString("id")));
			Log.i("id gotten","id gotten");
			sup.set_name(supp.getString("name"));
			Log.i("name gotten","name gotten");
			//sup.set_logo(supp.getString("coded_logo"));
			Log.i("logo gotten","logo gotten");
			sup.set_supcats(supp.getString("categories"));
			Log.i("cats","cats gotten");
		//	sups.addSupplier(sup);
			*/
			//Log.i("Supplier added",""+sup.get_name());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	geocode.close();
}

public void addClients(JSONArray clients){
	objects.ObjectCustomers cl=new objects.ObjectCustomers();
	
	int length=clients.length();
	
	for(int i=0;i<length;i++){
		try {
			JSONObject client=clients.getJSONObject(i);
				
			
			cl.set_id(9);
			cl.set_name(client.getString("fname"));
			cl.set_surname(client.getString("lname"));
			cl.set_email(client.getString("email"));
			cl.set_mobile(client.getString("phone"));
			cl.set_location(client.getString("loc"));
			cl.set_issent(1);						
			customer.addCustomer(cl);
			
			Log.i("Customer added",""+cl.get_name());
			
			//Log.i("all values gotten","all values gotten");
			/*
			sup.set_id(Long.parseLong(supp.getString("id")));
			Log.i("id gotten","id gotten");
			sup.set_name(supp.getString("name"));
			Log.i("name gotten","name gotten");
			//sup.set_logo(supp.getString("coded_logo"));
			Log.i("logo gotten","logo gotten");
			sup.set_supcats(supp.getString("categories"));
			Log.i("cats","cats gotten");
		//	sups.addSupplier(sup);
			*/
			//Log.i("Supplier added",""+sup.get_name());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	customer.close();
}






public void addAgents(JSONArray agents){
	objects.ObjectAgents agent=new objects.ObjectAgents();
	
	int length=agents.length();
	
	for(int i=0;i<length;i++){
		try {
			JSONObject ag=agents.getJSONObject(i);
				
			
			
			agent.set_id(ag.getLong("id"));
			agent.set_name(ag.getString("name"));
			agent.set_pass(ag.getString("password"));
			
			
						
			agee.addAgent(agent);
			
			Log.i("Agent added",""+agent.get_name());
			
			//Log.i("all values gotten","all values gotten");
			/*
			sup.set_id(Long.parseLong(supp.getString("id")));
			Log.i("id gotten","id gotten");
			sup.set_name(supp.getString("name"));
			Log.i("name gotten","name gotten");
			//sup.set_logo(supp.getString("coded_logo"));
			Log.i("logo gotten","logo gotten");
			sup.set_supcats(supp.getString("categories"));
			Log.i("cats","cats gotten");
		//	sups.addSupplier(sup);
			*/
			//Log.i("Supplier added",""+sup.get_name());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	agee.close();
}

public void updateAgents(JSONArray agents){
	objects.ObjectAgents agent=new objects.ObjectAgents();
	
	int length=agents.length();
	
	for(int i=0;i<length;i++){
		try {
			JSONObject ag=agents.getJSONObject(i);
				
			
			
			agent.set_id(ag.getLong("id"));
			agent.set_name(ag.getString("name"));
			agent.set_pass(ag.getString("password"));
			
			
						
			agee.updateAgent(agent);
			
			Log.i("Agent added",""+agent.get_name());
			
			//Log.i("all values gotten","all values gotten");
			/*
			sup.set_id(Long.parseLong(supp.getString("id")));
			Log.i("id gotten","id gotten");
			sup.set_name(supp.getString("name"));
			Log.i("name gotten","name gotten");
			//sup.set_logo(supp.getString("coded_logo"));
			Log.i("logo gotten","logo gotten");
			sup.set_supcats(supp.getString("categories"));
			Log.i("cats","cats gotten");
		//	sups.addSupplier(sup);
			*/
			//Log.i("Supplier added",""+sup.get_name());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	agee.close();
	updater.updateTableDate("Agents");
}


public void addPhotos(JSONArray photos){
	objects.ObjectPhotos photo=new objects.ObjectPhotos();
	objects.ObjectUpdate update=new objects.ObjectUpdate();
	int length=photos.length();
	
	for(int i=0;i<length;i++){
		try {
			JSONObject foto=photos.getJSONObject(i);
				
			
			photo.set_id(foto.getLong("prod_id"));
			photo.set_photo(foto.getString("coded_photo"));
			
			
			
						
			fotoz.addPhoto(photo);
			
			Log.i("Photo added","For Product  "+photo.get_id());
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	agee.close();
	
	if(length>0){
	update.setTableName("Images");
	update.setLastId(photo.get_id());
	updater.open();
	updater.updateTable(update);
	updater.close();
	}

}	
	
	
	
}