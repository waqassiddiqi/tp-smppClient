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
	public static String serviceShortCode;
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
			this.mRepeatInterval = Integer.parseInt(myResources.getString("consumer.interval"));
			aspUsername = myResources.getString("consumer.asp.username");
			aspPassword = myResources.getString("consumer.asp.password");
			serviceShortCode = myResources.getString("consumer.short_code");			
			senderId = myResources.getString("consumer.sender_id");
			
		} catch(Exception ex) {
			log.error("Unable to initialize: " + ex.getMessage(), ex);
			
			System.exit(-1);
		}
		
		this.log.info("Consumer initialized");
	}

	public void start() {
		mTimer = new Timer();
		mTimer.scheduleAtFixedRate(new PullMessagesTask(), 1000, this.mRepeatInterval);
	}

	public static void main(String[] args) {
		new Consumer().start();
	}
}