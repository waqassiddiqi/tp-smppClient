package smppclient.consumer.handler;

import java.util.List;
import java.util.ResourceBundle;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import org.apache.log4j.Logger;
import org.me.mgreply.MGReplyWS;
import org.me.mgreply.MGReplyWSService;

import smppclient.consumer.Consumer;
import smppclient.consumer.db.SubscriberDAO;
import smppclient.consumer.model.Subscriber;

public class SubscriptionHandler implements IRequestHandler {

	public enum SubscriptionAction { SUB, UNSUB };
	
	private SubscriptionAction action;
	private Subscriber sub;
	private String correlationId;
	private Logger log = Logger.getLogger(getClass().getName());
	
	private static String endPoint;
	
	static {
		ResourceBundle myResources = ResourceBundle.getBundle("client");
		endPoint = myResources.getString("request.msgreplyws.endpoint");
	}
	
	public SubscriptionHandler(Subscriber sub, SubscriptionAction action, String correlationId) {
		this.sub = sub;
		this.action = action;
		this.correlationId = correlationId;
		
		if(action == SubscriptionAction.SUB)
			log.info("Invoking SubscriptionHandler for new subscription");
		else if(action == SubscriptionAction.UNSUB)
			log.info("Invoking SubscriptionHandler to unsubscribe");
	}
	
	@Override
	public void run() {
		
		switch(this.action) {
			case SUB:
				log.info("Adding new subscriber: " + sub.toString());
				
				if(new SubscriberDAO().addSubscriber(sub)) {
					log.info("Subcription successful: " + sub.toString());
				} else {
					log.info("Subcription failed: " + sub.toString());
				}
				
				sendSmsResponse(Consumer.subMsg, sub.toString());
				
				break;
				
			case UNSUB:
				log.info("Unsubscribing existing subscriber: " + sub.toString());
				
				if(new SubscriberDAO().removeSubscriber(sub)) {
					log.info("Successfuly unsubscribed: " + sub.toString());
				} else {
					log.info("Removing subscriber failed: " + sub.toString());
				}
				
				sendSmsResponse(Consumer.unSubMsg, sub.toString());
				
				break;
		}
	}
	
	private void sendSmsResponse(String text, String msisdn) {
		
		log.info("Sending text messge: " + text + " to:" + msisdn);
		
		try {
			MGReplyWSService service = new MGReplyWSService();
			MGReplyWS port = service.getMGReplyWSPort();
			
			BindingProvider bindingProvider = (BindingProvider) port;
			bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
			
			Binding binding = bindingProvider.getBinding();
		    List<Handler> handlerChain = binding.getHandlerChain();
		    handlerChain.add(new LogMessageHandler());
		    binding.setHandlerChain(handlerChain);
		    
		    log.info("Making request to: " + bindingProvider.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
		    
		    String response = port.sendMessage(Consumer.aspUsername, Consumer.aspPassword, 
		    		this.correlationId, text, Consumer.senderId, 1, Consumer.failedCharingMsg);
		    
		    log.info("Response from server: " + response);
		} catch (Exception e) {
			log.error("Sending msg failed: " + e.getMessage(), e);
		}
	}
}