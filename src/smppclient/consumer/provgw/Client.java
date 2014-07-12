package smppclient.consumer.provgw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Client {
	private static String provGwIP = "127.0.0.1";
	private static int provGwPort = 8081;
	private Logger log = Logger.getLogger(getClass().getName());
	
	static {
		ResourceBundle myResources = ResourceBundle.getBundle("client");
		try {
			provGwPort = Integer.parseInt(myResources.getString("provgw.port"));
			provGwIP = myResources.getString("provgw.ip");
		} catch (Exception e) { }
	}
	
	public String sendRequest(String request) {
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		try {
			
			clientSocket = new Socket(provGwIP, provGwPort);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			out.println(request);
			
			return in.readLine();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {						
			try {
				out.close();
				in.close();
				clientSocket.close();
			} catch (IOException e) {
				log.error("sendRequest(): failed to clear resources", e);
			}			
		}
		
		return null;
	}
}
