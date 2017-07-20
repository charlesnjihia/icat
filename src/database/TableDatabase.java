package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TableDatabase extends SQLiteOpenHelper {

  public static final String TABLE_PRODUCTS = "products";
  public static final String PROD_ID = "prod_id";
  public static final String OWNER_ID = "owner_id";
  public static final String PROD_NAME = "prod_name";
  public static final String PROD_CATEGORY = "prod_category";
  public static final String PROD_SUB_CATEGORY = "prod_sub_category";
  public static final String PROD_DESCRIPTION = "prod_description";
  public static final String PROD_PHOTO = "prod_photo";
  public static final String PROD_MORE_PHOTOS = "prod_more_photos";
  public static final String PRICE = "prod_price";
  public static final String PRICE_DENO = "deno";
  public static final String AVAIL_TIME = "avail_time";
  public static final String AVAIL_UNITS = "avail_units";
  public static final String MIN_ORDER = "min_order";
  public static final String ORDER_UNITS= "order_units";
  
  public static final String TABLE_IMAGES = "product_images";
  public static final String ID = "_id";
  public static final String PROD = "prod_id";
  public static final String IMAGE = "image";
  
  
  public static final String TABLE_UPDATES = "updates";
  public static final String TABLE_NAME = "table_name";
  public static final String LAST_ID = "last_id";
  public static final String LAST_DATE = "last_date";
  
  
   
  
  private static final String DATABASE_NAME = "icatalog.db";
  private static final int DATABASE_VERSION =1;
  
  
  
  public static final String TABLE_CUSTOMERS = "customer_infor";
 // public static final String ID = "id";
  public static final String NAME = "fname";
  public static final String MNAME = "mname";
  public static final String SURNAME = "lname";
  public static final String PHONE = "phone";
  public static final String EMAIL = "email";
  public static final String LOCATION= "location";
  public static final String SENT ="cust_sent";
  
  public static final String TABLE_SVCARTS = "saved_carts";
 // public static final String ID = "id";
  public static final String CART_ID = "cart_id";
  public static final String CUSTOMER_ID = "customer_id";
  public static final String ISSENT= "sent";
  public static final String DATE = "date";
  
  public static final String TABLE_CART = "cart";
 // public static final String ID = "id";
 // public static final String CART_ID = "cart_id";
  public static final String PRODUCT_ID = "product_id";
  public static final String QUANTITY = "quantity";
  public static final String UNITS = "units";
  
  public static final String TABLE_AGENTS = "agents";
  //public static final String ID = "id";
  public static final String AG_ID = "agent_id";
  public static final String AG_NAME = "name";
  public static final String AG_CODE = "code";
  
  
  public static final String TABLE_PROD_CATEGORIES = "prod_categories";
  //public static final String ID = "id";
  public static final String CATEGORY_ID = "cat_id";
  public static final String CAT_NAME = "cat_name";
  public static final String CAT_PHOTO = "cat_photo";
  public static final String LIGHT_CODE = "light_code";
  public static final String DARK_CODE = "dark_code";
  
  
  public static final String TABLE_PROD_SUB_CATS = "prod_subcategories";
  //public static final String ID = "id";
   public static final String CAT_ID = "sec_id";
   public static final String SUBCAT_ID = "subsec_id";
  public static final String SUB_NAME = "sub_name";
  
  
  public static final String TABLE_SUPPLIERS = "suppliers";
 // public static final String ID = "id";
  public static final String SUP_ID = "sup_id";
  public static final String SUP_NAME = "name";
  
  public static final String SUP_LOGO = "sup_logo";
  public static final String SUP_CATS = "sup_cats";
 // public static final String EMAIL = "email";
 // public static final String CAT_ID="cat_id";
  //public static final String PHONE = "phone";
 
  public static final String TABLE_SUP_CATS = "supplier_cats";   
  public static final String TABLE_GEO_CODES = "geo_ncodes";
  public static final String GEO_NAME = "geo_name";
  public static final String IPRO_ID = "ipro_id";
  
  
  public static final String TABLE_APP_STATUS = "appstatus";   
  public static final String INST_STRING = "installed";
  public static final String INST_STATUS = "ststus";
  
  
  public static final String TABLE_LOANCLIENTS = "loanclients";   
  public static final String FNAME = "fname";
  public static final String SNAME = "mname";
  public static final String LNAME = "lname";
  public static final String IDNO = "idno";
  public static final String AGE = "age";
  public static final String GENDER = "gender";
  public static final String FONE = "phone";
  public static final String EMAILL = "email";
  public static final String LOCATIONN= "location";
  public static final String OCCUPATION= "occupation";
  public static final String EDUCATION = "education";
  public static final String DEPENDANTS = "dependants";
  public static final String MARSTATUS = "marstatus";
  public static final String LOAN= "loan";
  public static final String INCOME= "income";
  public static final String COSTS= "costs";
  public static final String INITFUND = "initfund";
  public static final String OWNERS= "owners";
  public static final String REGDATE= "regdate";
  public static final String LOFFICER = "lofficer";
  public static final String INFSENT= "sent";
  
  public static final String COLOR = "colorcode";
  public static final String TABLE_COLORS = "colors";
  

  
  
 // /Database app status creation sql statement
  private static final String DATABASE_COLOR = "create table "
      + TABLE_COLORS  + "(" + ID
      + " integer primary key autoincrement, " +CAT_ID +" integer not null," +COLOR
      + " text );";
  
  
  //Database loan applicants creation sql statement
  private static final String DATABASE_LOANCLIENTS = "create table "
      + TABLE_LOANCLIENTS  + "(" + ID
      + " integer primary key autoincrement, " + FNAME +" text," +SNAME
      +" text, " + LNAME +" text, " + IDNO +" text, " + AGE +" integer,  " + GENDER
      +" text, " + FONE  +" text, " + EMAILL  +" text, " + LOCATIONN
      +" text, " + OCCUPATION  +" text, " + EDUCATION +" text, " + DEPENDANTS
      +" integer, "+ MARSTATUS +" text, " + LOAN +" float, " + INCOME
      +" float, " + COSTS +" float, " + INITFUND  +" text, " + OWNERS
      +" text, " + REGDATE +" text, " + LOFFICER +" text, "+ INFSENT+" integer not null);";
   
  
  
  
  
  //Database app status creation sql statement
  private static final String DATABASE_APP_STATUS = "create table "
      + TABLE_APP_STATUS  + "(" + ID
      + " integer primary key autoincrement, " +INST_STRING +" text," +INST_STATUS
      + " integer not null );";
   
  
  
 
//Database creation sql statement
 private static final String DATABASE_GEO_CODES = "create table "
     + TABLE_GEO_CODES  + "(" + ID
     + " integer primary key autoincrement, " +IPRO_ID+" integer not null," +GEO_NAME
     + " text );";
  
  
  
  // Database creation sql statement
  private static final String DATABASE_SUPPLIERS = "create table "
      + TABLE_SUPPLIERS + "(" + ID
      + " integer primary key autoincrement, " +SUP_ID+" integer not null," +SUP_NAME
      + " text not null,"+SUP_LOGO+" text,"+SUP_CATS+" text);";
 
  
 
  // Database creation sql statement
  private static final String DATABASE_SUBCATEGORY = "create table "
      + TABLE_PROD_SUB_CATS + "(" + ID
      + " integer primary key autoincrement, "+CAT_ID     
      + " integer not null,"+SUBCAT_ID
      + " integer not null,"+ SUB_NAME
      + " text not null);";

 
  
  
 

  // Database creation sql statement
  private static final String DATABASE_CATEGORY = "create table "
      + TABLE_PROD_CATEGORIES + "(" + ID
      + " integer primary key autoincrement, "+ CATEGORY_ID +" integer not null," + CAT_NAME
      + " text not null,"+CAT_PHOTO+" text,"+LIGHT_CODE+" text,"+DARK_CODE+" text);";
  
  
 

  // Database for table agents creation sql statement
  private static final String DATABASE_AGENTS= "create table "
      + TABLE_AGENTS + "(" + ID
      + " integer primary key autoincrement, " + AG_ID +
      " integer not null,"+  AG_NAME
      + " text not null,"+AG_CODE+" text not null);";
 
//Database  table updates creation sql statement
 private static final String DATABASE_UPDATES= "create table "
     + TABLE_UPDATES + "(" + ID
     + " integer primary key autoincrement, " + TABLE_NAME +
     " text not null,"+  LAST_ID
     + " integer not null,"+ LAST_DATE +" text);";

  
 

  // Database creation sql statement
  private static final String DATABASE_CART = "create table "
      + TABLE_CART + "(" + ID
      + " integer primary key autoincrement, " + CART_ID
      + " text not null,"+PRODUCT_ID +" integer not null,"+ QUANTITY
      +" integer not null,"+UNITS+" text not null);";
  
  

  // Database creation sql statement
  private static final String DATABASE_SVCART = "create table "
      + TABLE_SVCARTS + "(" + ID
      + " integer primary key autoincrement, " + CART_ID
      + " text not null,"+CUSTOMER_ID +" integer not null,"+
      DATE+" text not null,"+ISSENT+" integer not null);";
  

  // Database creation sql statement
  private static final String DATABASE_CUSTOMERS = "create table "
      + TABLE_CUSTOMERS + "(" + ID
      + " integer primary key autoincrement, " + NAME
      + " text not null,"+ SURNAME +" text,"+
      PHONE+" text not null,"+EMAIL+" text, "+ LOCATION +" text, "+SENT+" integer not null default 0);";

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_PRODUCTS + "(" +ID+" integer primary key autoincrement, " +PROD_ID
      + " integer not null, "+ OWNER_ID
      + " integer not null," + PROD_NAME
      + " text not null,"+PROD_CATEGORY+" integer not null,"+
      PROD_SUB_CATEGORY +" integer not null,"+PROD_DESCRIPTION
      +" text not null,"+ PROD_PHOTO +" text,"+
      PRICE+" float not null,"+PRICE_DENO+" text,"+AVAIL_TIME+" integer,"+AVAIL_UNITS+" text,"+MIN_ORDER+" integer,"+ORDER_UNITS+" text);";
  
  private static final String OTHER_IMAGES = "create table "
	      + TABLE_IMAGES + "(" + ID
	      + " integer primary key autoincrement, "+ PROD
	      + " integer not null," +IMAGE+" text );";

  public TableDatabase(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
	  
	  Log.w(TableDatabase.class.getName(),"Ndio hio database inatengenezwa ....kare gani");
	  	database.execSQL(DATABASE_CUSTOMERS);	  
	  	database.execSQL(DATABASE_CREATE);   
	    database.execSQL(DATABASE_SVCART);
	    database.execSQL(DATABASE_CART);
	    database.execSQL(DATABASE_AGENTS);
	    database.execSQL(DATABASE_SUPPLIERS);
	    database.execSQL(DATABASE_CATEGORY);
	    database.execSQL(DATABASE_SUBCATEGORY);
	    database.execSQL(OTHER_IMAGES);
	    database.execSQL(DATABASE_GEO_CODES);
	    database.execSQL( DATABASE_UPDATES);
		database.execSQL(DATABASE_APP_STATUS);
		database.execSQL(DATABASE_COLOR);
		database.execSQL(DATABASE_LOANCLIENTS);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	  
	  
	  
	  Log.w(TableDatabase.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which wont  destroy all old data ....kare gani");
	//  db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
	  onCreate(db);
  /*  Log.w(TableDatabase.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS );
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SVCARTS);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENTS);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPPLIERS);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROD_CATEGORIES);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROD_SUB_CATS);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_GEO_CODES);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_UPDATES);
    onCreate(db);*/
  }

} 