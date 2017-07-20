package com.example.faida;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

import com.datecs.api.printer.Printer;
import com.datecs.api.printer.PrinterInformation;
import com.datecs.api.printer.ProtocolAdapter;

import database.TableSavedCartsHandler;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Cart extends Activity{
	
	
	 // Debug
    private static final String LOG_TAG = "PrinterSample"; 
    private static final boolean DEBUG = true;
    
    // Request to get the bluetooth device
    private static final int REQUEST_GET_DEVICE = 0; 
    
    // Request to get the bluetooth device
    private static final int DEFAULT_NETWORK_PORT = 9100; 
        
	// The listener for all printer events
	private final ProtocolAdapter.ChannelListener mChannelListener = new ProtocolAdapter.ChannelListener() {
        @Override
        public void onReadEncryptedCard() {
            //toast(getString(R.string.msg_read_encrypted_card));
        }
        
        @Override
        public void onReadCard() {
            //readMagstripe();            
        }
        
        @Override
        public void onReadBarcode() {
            //readBarcode(0);
        }
        
        @Override
        public void onPaperReady(boolean state) {
            if (state) {
               // toast(getString(R.string.msg_paper_ready));
            } else {
               // toast(getString(R.string.msg_no_paper));
            }
        }
        
        @Override
        public void onOverHeated(boolean state) {
            if (state) {
              //  toast(getString(R.string.msg_overheated));
            }
        }
               
        @Override
        public void onLowBattery(boolean state) {
            if (state) {
                //toast(getString(R.string.msg_low_battery));
            }
        }
    };
	
	
	database.TableCartHandler cart;
	database.TableSavedCartsHandler svcart;
	database.TableCustomersHandler cust;
	database.TableAgentsHandler ag;
	
	objects.ObjectCustomers client;
	 static List<objects.ObjectCart> cartItems;
	
	long clientId;
	static String cartId,payMethod,custName,custTel, transNum,agentCode,agentName;
	
	static float sumAmount=0;
	float itemSum;
	
	
	ListView cartlist;
	adapters.CartAdapter cartad;
	ImageView back;	
	TextView clientName,numProds,sumTotal,payDisp;
	Button print,buy;
	String transacNum,PayMethod;
	int status;
	
	//printer variables
	private Printer mPrinter;
	private ProtocolAdapter mProtocolAdapter;
	private PrinterInformation mPrinterInfo;
	private BluetoothSocket mBluetoothSocket;
	private PrinterServer mPrinterServer;
	private Socket mPrinterSocket;
	private boolean mRestart=false;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart);			
		cartId=getIntent().getStringExtra("cartid");
        clientId=getIntent().getLongExtra("clientid",0);
        client=new objects.ObjectCustomers();
        objects.ProductsObject prod=new objects.ProductsObject();
        
        
        cust=new database.TableCustomersHandler(this);
        cust.open();
        client=cust.getCustomers(clientId);
        cust.close();        
        
        cart=new database.TableCartHandler(this);
        cart.open();
        cartItems=cart.getCartProds(cartId);
        long y=cart.getCartProdsCount(cartId) ;
        cart.close();
        
        for(int x=0;x<cartItems.size();x++){        	
            objects.ObjectCart crt=new objects.ObjectCart();
             crt=cartItems.get(x);
             cart.open();
             prod=cart.getProdDetails(crt.get_prodId()) ;
             cart.close();
             crt.set_name(prod.get_prodname());
             crt.set_price(prod.get_price());
            
            }
        svcart=new database.TableSavedCartsHandler(this);
        svcart.open();
        status=svcart.getStatus(cartId);
        svcart.close();
        
        
        
        
        for(int x=0;x<cartItems.size();x++){        	
            objects.ObjectCart crt=new objects.ObjectCart();
             crt=cartItems.get(x);
             itemSum=crt.get_quantity()*crt.get_price();
             sumAmount+=itemSum;             
            }
        
        int itemsnum=cartItems.size();
        
         //Log.i("The number of cart items is",""+itemsnum);

       //  Log.i("The number of cart items is from count is",""+y);
         
         
        
        
         clientName=(TextView)findViewById(R.id.client);
         numProds=(TextView)findViewById(R.id.numprods);
         sumTotal=(TextView)findViewById(R.id.total);
         payDisp=(TextView)findViewById(R.id.paytab);
         
         if(status==1){
        	 payDisp.setText("Paid"); 
        	 payDisp.setTextColor(Color.parseColor("#ff0000"));
         }
         
         clientName.setText(client.get_name()+" "+client.get_surname());
         numProds.setText(""+itemsnum+" PRODUCTS");
         sumTotal.setText(""+sumAmount+" Sh");
         sumAmount=0;
         
         
         
        
		
		 cartad=new adapters.CartAdapter(this,cartItems);
		 cartlist=(ListView)findViewById(R.id.cart);
		 cartlist.setAdapter(cartad);
		 back=(ImageView)findViewById(R.id.back);
		 back.setOnClickListener(new OnClickListener() {
	     	 
			 
			 public void onClick(View arg0) {
				 Intent carts= new Intent(Cart.this, SavedCarts.class);
					
			 		startActivity(carts);
					

				}});
	
		 
		 findViewById(R.id.print).setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					if(mRestart ==true){
						//printText(cartItems); 
						//doConnection();
						 waitForConnection();
					}else{
						//mRestart =true;
						//doConnection();
						 waitForConnection();
						//printText(cartItems); 
					}
				              								
				}        	
	        });
		 findViewById(R.id.pay).setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					
					if(status==1){
						Toast toast = Toast.makeText(Cart.this, "Cart already Sent!!", Toast.LENGTH_SHORT);
						toast.show();
						
					}else{
					
				net.CheckNetStatus chekker=new net.CheckNetStatus(Cart.this);	
				boolean isConnected=chekker.chkstatus();
				
				if(isConnected){
					
					/*
					
					//Log.i("Try to send out","Try to send out");
					net.SendCart catSend=new net.SendCart(Cart.this);
					catSend.makePurchase(cartItems, client);
					*/
					
					// retrieve display dimensions
					Rect displayRectangle = new Rect();
					Window window =Cart.this.getWindow();
					window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
					
			    //createdialog
					int width=(int)(displayRectangle.width() * 0.8f);
					int height =(int)(displayRectangle.height() * 0.5f);
				//
					//start of the dialog
					final Dialog d =new Dialog(Cart.this, R.style.CustomDialog);
					
					LayoutInflater inflater =Cart.this.getLayoutInflater(); 
					 final View input=inflater.inflate(R.layout.pay_method, null);
					 d .setContentView(input);
					
					//d.setContentView(R.layout.app_sent);
					
					// LayoutInflater inflater =LoanAppPart2.this.getLayoutInflater(); 
					//	 final View input=inflater.inflate(R.layout.gallery, null);
					//	 d .setContentView(input);
					
					//final TextView conf=(TextView) input.findViewById(R.id.title);
				//	conf.setText("Connect To Printer First");
					 final RadioGroup	paymethod = (RadioGroup)input.findViewById(R.id.paymethod);
					final EditText trans=(EditText) input.findViewById(R.id.trans);
					final TextView ok=(TextView) input.findViewById(R.id.ok);
					final TextView cancel=(TextView) input.findViewById(R.id.cancel);
					final TextView cheker=(TextView) input.findViewById(R.id.chekker);
					//final RadioButton selectedOption;
					ok.setOnClickListener(new OnClickListener() {
				     	   
			 			public void onClick(View arg0) {
			 				String transNum=trans.getText().toString();	
			 				Log.i("Reached here","reached here");
			 				int selected = paymethod.getCheckedRadioButtonId();
			 				Log.i("after here","after here");
			 				RadioButton selectedOption = (RadioButton)input.findViewById(selected);
			 				
			 				String option=selectedOption.getText().toString();
			 				payMethod=option;
			 		        if(option.equalsIgnoreCase("MPESA")&&transNum.length()<1){
			 		        	
			 		        	cheker.setText("Add the Transaction Number!");
			 		        	
			 		        }
			 		        else {if(option.equalsIgnoreCase("CASH")){
			 		        	transacNum="1234";
			 		        	
			 		        	
			 		        }else{
			 		        
			 		        	
			 		        	transacNum=transNum;
			 		        	
			 		        }
			 		       net.SendCart catSend=new net.SendCart(Cart.this);
							catSend.makePurchase(cartItems, client,option,transacNum,cartId);
							d.cancel();
		 		        }

			 				}});
					
					cancel.setOnClickListener(new OnClickListener() {
				     	   
			 			public void onClick(View arg0) {
			 				
			 				 
			 				d.cancel();

			 				}});
					
					
					
		             
					// show it
					d.getWindow().setLayout(width, height);
					//d.setCanceledOnTouchOutside(true);
					d.show(); 
					
				}else{
					
					reqConnection();
					
				}
					
				              								
				}
				}
	        });
	}
	
	private void error(final String text, boolean resetConnection) {        
        if (resetConnection) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {        
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();  
                    
                }           
            });
                
           // waitForConnection();
        }
    }
	
	private void doJob(final Runnable job, final int resId) {
        // Start the job from main thread
        runOnUiThread(new Runnable() {            
            @Override
            public void run() {
                // Progress dialog available due job execution
                final ProgressDialog dialog = new ProgressDialog(Cart.this);
                dialog.setTitle(getString(R.string.title_please_wait));
                dialog.setMessage(getString(resId));
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                
                Thread t = new Thread(new Runnable() {            
                    @Override
                    public void run() {                
                        try {
                            job.run();
                        } finally {
                            dialog.dismiss();
                        }
                    }
                });
                t.start();   
            }
        });                     
    }
	
	 @Override
		protected void onDestroy() {
	        super.onDestroy();
	        mRestart = false;               
	        closeActiveConnection();
		}	
			    
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == REQUEST_GET_DEVICE) {
	            if (resultCode == DeviceListActivity.RESULT_OK) {   
	            	String address = data.getStringExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
	            	//address = "192.168.11.136:9100";
	            	if (BluetoothAdapter.checkBluetoothAddress(address)) {
	            		establishBluetoothConnection(address);
	            	} else {
	            		establishNetworkConnection(address);
	            	}
	            } else if (resultCode == RESULT_CANCELED) {
	                
	            } else {
	                finish();
	            }
	        }
	    }
    
	
	 private void establishBluetoothConnection(final String address) {
	    	closePrinterServer();
	        
	        doJob(new Runnable() {           
	            @Override
	            public void run() {      
	                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();                
	                BluetoothDevice device = adapter.getRemoteDevice(address);                    
	                UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	                InputStream in = null;
	                OutputStream out = null;
	                
	                adapter.cancelDiscovery();
	                
	                try {
	                    if (DEBUG) Log.d(LOG_TAG, "Connect to " + device.getName());
	                    mBluetoothSocket = device.createRfcommSocketToServiceRecord(uuid);
	                    mBluetoothSocket.connect();
	                    in = mBluetoothSocket.getInputStream();
	                    out = mBluetoothSocket.getOutputStream();                                        
	                } catch (IOException e) {    
	                	e.printStackTrace();
	                    error(getString(R.string.msg_failed_to_connect) + ". " +  e.getMessage(), mRestart);
	                    return;
	                }                                  
	                
	                try {
	                    initPrinter(in, out);
	                } catch (IOException e) {
	                	e.printStackTrace();
	                    error(getString(R.string.msg_failed_to_init) + ". " +  e.getMessage(), mRestart);
	                    return;
	                }
	            }
	        }, R.string.msg_connecting); 
	    }
	    
	    private void establishNetworkConnection(final String address) {
	    	closePrinterServer();
	        
	        doJob(new Runnable() {           
	            @Override
	            public void run() {            	
	            	Socket s = null;
	            	try {
	            		String[] url = address.split(":");
	            		int port = DEFAULT_NETWORK_PORT;
	            		
	            		try {
	            			if (url.length > 1)  {
	            				port = Integer.parseInt(url[1]);
	            			}
	            		} catch (NumberFormatException e) { }
	            		
	            		s = new Socket(url[0], port);
	            		s.setKeepAlive(true);
	                    s.setTcpNoDelay(true);
		            } catch (UnknownHostException e) {
		            	error(getString(R.string.msg_failed_to_connect) + ". " +  e.getMessage(), mRestart);
	                    return;
		            } catch (IOException e) {
		            	error(getString(R.string.msg_failed_to_connect) + ". " +  e.getMessage(), mRestart);
	                    return;
		            }            
	            	
	                InputStream in = null;
	                OutputStream out = null;
	                
	                try {
	                    if (DEBUG) Log.d(LOG_TAG, "Connect to " + address);
	                    mPrinterSocket = s;                    
	                    in = mPrinterSocket.getInputStream();
	                    out = mPrinterSocket.getOutputStream();                                        
	                } catch (IOException e) {                    
	                    error(getString(R.string.msg_failed_to_connect) + ". " +  e.getMessage(), mRestart);
	                    return;
	                }                                  
	                
	                try {
	                    initPrinter(in, out);
	                } catch (IOException e) {
	                    error(getString(R.string.msg_failed_to_init) + ". " +  e.getMessage(), mRestart);
	                    return;
	                }
	            }
	        }, R.string.msg_connecting); 
	    }
	    
	
	
	
	protected void initPrinter(InputStream inputStream, OutputStream outputStream) throws IOException {
        mProtocolAdapter = new ProtocolAdapter(inputStream, outputStream);
       
        if (mProtocolAdapter.isProtocolEnabled()) {
            final ProtocolAdapter.Channel channel = mProtocolAdapter.getChannel(ProtocolAdapter.CHANNEL_PRINTER);
            channel.setListener(mChannelListener);
            // Create new event pulling thread
            new Thread(new Runnable() {                
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                        try {
                            channel.pullEvent();
                        } catch (IOException e) {
                        	e.printStackTrace();
                            error(e.getMessage(), mRestart);
                            break;
                        }
                    }
                }
            }).start();
            mPrinter = new Printer(channel.getInputStream(), channel.getOutputStream());
        } else {
            mPrinter = new Printer(mProtocolAdapter.getRawInputStream(), mProtocolAdapter.getRawOutputStream());
        }
        
        mPrinterInfo = mPrinter.getInformation();
        
        runOnUiThread(new Runnable() {          
            public void run() {
            	mRestart=true;
            	printText(cartItems);  
            	//closePrinterConnection();
              //  ((ImageView)findViewById(R.id.icon)).setImageResource(R.drawable.icon);
              //  ((TextView)findViewById(R.id.name)).setText(mPrinterInfo.getName());
            }
        });
	
	}
    
	
	
	private synchronized void closeBlutoothConnection() {        
        // Close Bluetooth connection 
        BluetoothSocket s = mBluetoothSocket;
        mBluetoothSocket = null;
        if (s != null) {
            if (DEBUG) Log.d(LOG_TAG, "Close Blutooth socket");
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        
    }
    
    private synchronized void closeNetworkConnection() {
        // Close network connection
        Socket s = mPrinterSocket;
        mPrinterSocket = null;
        if (s != null) {
            if (DEBUG) Log.d(LOG_TAG, "Close Network socket");
            try {
                s.shutdownInput();
                s.shutdownOutput();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }            
        }
    }
    
    private synchronized void closePrinterServer() {
    	closeNetworkConnection();
    	
        // Close network server
        PrinterServer ps = mPrinterServer;
        mPrinterServer = null;
        if (ps != null) {
            if (DEBUG) Log.d(LOG_TAG, "Close Network server");
            try {
                ps.close();
            } catch (IOException e) {                
                e.printStackTrace();
            }            
        }     
    }
    
    private synchronized void closePrinterConnection() {
        if (mPrinter != null) {
            mPrinter.release();
        }
        
       if (mProtocolAdapter != null) {
            mProtocolAdapter.release();
        }
       
    }
	
	
	
	private synchronized void closeActiveConnection() {
        closePrinterConnection();
        closeBlutoothConnection();
        closeNetworkConnection();  
        closePrinterServer();
    }

	
	
	
	
	
    public synchronized void waitForConnection() {
        closeActiveConnection();
        
        // Show dialog to select a Bluetooth device. 
        startActivityForResult(new Intent(this, DeviceListActivity.class), REQUEST_GET_DEVICE);
        
        // Start server to listen for network connection.
        try {
            mPrinterServer = new PrinterServer(new PrinterServerListener() {                
                public void onConnect(Socket socket) {
                    if (DEBUG) Log.d(LOG_TAG, "Accept connection from " + socket.getRemoteSocketAddress().toString());
                    
                    // Close Bluetooth selection dialog
                    finishActivity(REQUEST_GET_DEVICE);                    
                    
                    mPrinterSocket = socket;
                    try {
                        InputStream in = socket.getInputStream();
                        OutputStream out = socket.getOutputStream();
                        initPrinter(in, out);
                    } catch (IOException e) {   
                    	e.printStackTrace();
                        error(getString(R.string.msg_failed_to_init) + ". " + e.getMessage(), mRestart);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
public void reqConnection(){
		
		// retrieve display dimensions
			Rect displayRectangle = new Rect();
			Window window =Cart.this.getWindow();
			window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
			
	    //createdialog
			int width=(int)(displayRectangle.width() * 0.8f);
			int height =(int)(displayRectangle.height() * 0.3f);
		//
			//start of the dialog
			final Dialog d =new Dialog(Cart.this, R.style.CustomDialog);
			
			LayoutInflater inflater =Cart.this.getLayoutInflater(); 
			 final View input=inflater.inflate(R.layout.no_net, null);
			 d .setContentView(input);
			
			//d.setContentView(R.layout.app_sent);
			
			// LayoutInflater inflater =LoanAppPart2.this.getLayoutInflater(); 
			//	 final View input=inflater.inflate(R.layout.gallery, null);
			//	 d .setContentView(input);
			
		//	final TextView conf=(TextView) input.findViewById(R.id.title);
		//	conf.setText("Connect To Printer First");
			
			//final TextView ok=(TextView) input.findViewById(R.id.ok);
			//final TextView cancel=(TextView) input.findViewById(R.id.cancel);
			/*
			 ok.setOnClickListener(new OnClickListener() {
		     	   
	 			public void onClick(View arg0) {
	 				d.cancel();
	 				//mRestart = true;
				    waitForConnection();
	 				

	 				}});
			
			cancel.setOnClickListener(new OnClickListener() {
		     	   
	 			public void onClick(View arg0) {
	 				
	 				 
	 				d.cancel();

	 				}});
			
			*/
			
             
			// show it
			d.getWindow().setLayout(width, height);
			d.setCanceledOnTouchOutside(true);
			d.show(); 
	}
	
	
	public void doConnection(){
		
		// retrieve display dimensions
			Rect displayRectangle = new Rect();
			Window window =Cart.this.getWindow();
			window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
			
	    //createdialog
			int width=(int)(displayRectangle.width() * 0.8f);
			int height =(int)(displayRectangle.height() * 0.3f);
		//
			//start of the dialog
			final Dialog d =new Dialog(Cart.this, R.style.CustomDialog);
			
			LayoutInflater inflater =Cart.this.getLayoutInflater(); 
			 final View input=inflater.inflate(R.layout.connect, null);
			 d .setContentView(input);
			
			//d.setContentView(R.layout.app_sent);
			
			// LayoutInflater inflater =LoanAppPart2.this.getLayoutInflater(); 
			//	 final View input=inflater.inflate(R.layout.gallery, null);
			//	 d .setContentView(input);
			
			final TextView conf=(TextView) input.findViewById(R.id.title);
			conf.setText("Connect To Printer First");
			
			final TextView ok=(TextView) input.findViewById(R.id.ok);
			final TextView cancel=(TextView) input.findViewById(R.id.cancel);
			ok.setOnClickListener(new OnClickListener() {
		     	   
	 			public void onClick(View arg0) {
	 				d.cancel();
	 				//mRestart = true;
				    waitForConnection();
	 				

	 				}});
			
			cancel.setOnClickListener(new OnClickListener() {
		     	   
	 			public void onClick(View arg0) {
	 				
	 				 
	 				d.cancel();

	 				}});
			
			
			
             
			// show it
			d.getWindow().setLayout(width, height);
			//d.setCanceledOnTouchOutside(true);
			d.show(); 
	}
	
	private void printText(final List<objects.ObjectCart> cartProducts) {
	    doJob(new Runnable() {           
            @Override
            public void run() {
        		StringBuffer sb = new StringBuffer();
        		sb.append("{reset}{center}{w}{h}Faida Receipt");
                sb.append("{br}");
                sb.append("{br}");
                float total=0;
                   int j=1;
                
                for	(int i=0;i<cartProducts.size();i++){
                	objects.ObjectCart crt=new objects.ObjectCart();
                    crt=cartItems.get(i);
                    float amount=crt.get_quantity()*crt.get_price();
                    total+=amount;
                sb.append("{reset}"+j+". "+crt.get_name()+"{br}");                
                sb.append("{reset}{right}{h}Sh "+amount+" {br}");
                j++;
                
                }
                
                
                
                
                sb.append("{br}");
                sb.append("{reset}{right}{w}{h}TOTAL:Sh {/w}"+total+" {br}");            
                sb.append("{br}");
                sb.append("{reset}{center}{s}Thank You!{br}");
                
            	try {   
            	    if (DEBUG) Log.d(LOG_TAG, "Print Text");
            		mPrinter.reset();            		
                    mPrinter.printTaggedText(sb.toString());                    
                    mPrinter.feedPaper(110); 
                    mPrinter.flush();    
                    
            	} catch (IOException e) {
            		e.printStackTrace();
            	    error(getString(R.string.msg_failed_to_print_text) + ". " + e.getMessage(), mRestart);    		
            	
            	
            	}
            	closePrinterConnection();
            }
	    }, R.string.msg_printing_text);
	    
	}


}
