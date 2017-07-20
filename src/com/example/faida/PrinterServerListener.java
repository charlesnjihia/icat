package com.example.faida;

import java.net.Socket;

//import java.net.Socket;

public interface PrinterServerListener {

	void onConnect(Socket s);
    //public void onConnect(Socket socket);
}
