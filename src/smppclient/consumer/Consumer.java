package smppclient.consumer;

import java.util.ResourceBundle;
import java.util.Timer;

import org.apache.log4j.Logger;

public class Consumer {
	private Logger log = Logger.getLogger(getClass().getName());
	private Timer mTimer;
	private long mRepeatInterval = 10000;
	
	public static String aspUsername;
	public static String aspPassword;
	public static String serviceId;
	public static String subMsg;
	public static String unSubMsg;
	public static String failedCharingMsg;
	public static String senderId;
	
	public Consumer() {
		readConfig();
	}
	
	public void readConfig() {
		this.log.info("Initializing Consumer...");
		
		try {
			ResourceBundle myResources = ResourceBundle.getBundle("client");
			this.mRepeatInterval = Integer.parseInt(myResources.getString("request.interval"));
			aspUsername = myResources.getString("request.asp.username");
			aspUsername = myResources.getString("request.asp.password");
			serviceId = myResources.getString("request.serviceid");
			
			subMsg = myResources.getString("message.sub");
			unSubMsg = myResources.getString("message.unsub");
			failedCharingMsg = myResources.getString("message.failedcharging");
			senderId = myResources.getString("message.senderid");
			
		} catch(Exception ex) {
			log.error("Unable to initialize: " + ex.getMessage(), ex);
			
			System.exit(-1);
		}
		
		this.log.info("Consumer initialized");
	}

	public void start() {
		mTimer = new Timer();
		mTimer.scheduleAtFixedRate(new PullMessagesTask(), 1000, this.mRepeatInterval);
		
		for(;;) { }
	}

	public static void main(String[] args) {
		new Consumer().start();
	}
}