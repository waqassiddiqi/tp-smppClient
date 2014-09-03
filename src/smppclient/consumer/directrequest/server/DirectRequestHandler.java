package smppclient.consumer.directrequest.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

import smppclient.consumer.task.PushMessageTask;

public class DirectRequestHandler implements Runnable {
	private Socket mClientSocket;
	private boolean mClientConnected = false;
	private Logger log = Logger.getLogger(getClass().getName());
	
	public DirectRequestHandler(Socket clientSocket) {
		this.mClientSocket = clientSocket;
		this.mClientConnected = true;
	}
	
	@Override
	public void run() {
		log.info("New incoming request");
        
		try {
			while (this.mClientConnected) {
				if (this.mClientSocket.isOutputShutdown()) {
					this.mClientConnected = false;
				} else {
					BufferedReader inFromClient = new BufferedReader(new InputStreamReader(this.mClientSocket.getInputStream()));
					DataOutputStream outToClient = new DataOutputStream(this.mClientSocket.getOutputStream());

					String xml = inFromClient.readLine();

					log.info("<< incoming request: " + xml);
					
					String response = processRequest(xml);
					
					outToClient.writeBytes(response);
					
					log.info(">> outgoing response: " + response);
						
					this.mClientConnected = false;
					this.mClientSocket.close();
				}
			}
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
		} finally {
			try {
				if(this.mClientSocket != null && this.mClientSocket.isClosed() == false)
					this.mClientSocket.close();
			} catch (IOException e) {
				log.error("Failed to free socket resource", e);
			}
		}
	}
	
	private String processRequest(String xml) throws XPathExpressionException {
		XPathFactory xpathFactory = XPathFactory.newInstance();
	    XPath xpath = xpathFactory.newXPath();
	    String msisdn = "";
	    String text = "";
	    String serviceId = "";
	    
		InputSource source = new InputSource(new StringReader(xml));
		msisdn = xpath.evaluate("/methodCall/msisdn", source);
		
		source = new InputSource(new StringReader(xml));
		text = xpath.evaluate("/methodCall/text", source);
		
		source = new InputSource(new StringReader(xml));
		serviceId = xpath.evaluate("/methodCall/serviceId", source);
		
		return new PushMessageTask().sendSms(msisdn, text, serviceId);
	}
}