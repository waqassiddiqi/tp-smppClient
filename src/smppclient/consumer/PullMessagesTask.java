package smppclient.consumer;

import java.util.List;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import org.apache.log4j.Logger;
import org.me.mgpool.MGPoolWS;
import org.me.mgpool.MGPoolWSService;
import org.me.mgpool.StringArray;

import smppclient.consumer.handler.IRequestHandler;
import smppclient.consumer.handler.LogMessageHandler;
import smppclient.consumer.handler.SubscriptionHandler;
import smppclient.consumer.handler.SubscriptionHandler.SubscriptionAction;
import smppclient.consumer.model.Subscriber;

public class PullMessagesTask extends TimerTask {
	
	private static String endPoint;
	private static ExecutorService mRequestPool;
	
	static {
		ResourceBundle myResources = ResourceBundle.getBundle("client");
		endPoint = myResources.getString("request.mgpoolws.endpoint");
		
		mRequestPool = Executors.newFixedThreadPool(10);
	}
	
	private Logger log = Logger.getLogger(getClass().getName());
	
	@SuppressWarnings("rawtypes")
	@Override
	public void run() {
		
		try {
			MGPoolWSService service = new MGPoolWSService();
			MGPoolWS port = service.getMGPoolWSPort();
			
			BindingProvider bindingProvider = (BindingProvider) port;
			bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
			
			Binding binding = bindingProvider.getBinding();
		    List<Handler> handlerChain = binding.getHandlerChain();
		    handlerChain.add(new LogMessageHandler());
		    binding.setHandlerChain(handlerChain);
			
			log.info("Making request to: " + bindingProvider.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
			
			List<StringArray> responseList = port.getServiceMsgs(Consumer.aspUsername, Consumer.aspPassword, 
					Consumer.serviceId);
			
			log.info("Messages pulled from server: " + responseList.size());
			
			IRequestHandler handler = null;
			
			for(StringArray response : responseList) {
				if(response.getXms().toLowerCase().contains("unsub")) {
					handler = new SubscriptionHandler(
							new Subscriber(response.getMSISDN(), 1, 1), 
							SubscriptionAction.UNSUB, response.getCorrelationID());
					
					mRequestPool.execute(handler);
					
				} else if(response.getXms().toLowerCase().contains("sub")) {
					handler = new SubscriptionHandler(new Subscriber(response.getMSISDN(), 1, 1), 
							SubscriptionAction.SUB, response.getCorrelationID());
					
					mRequestPool.execute(handler);
				}
			}
		} catch (Exception e) {
			log.error("PullMessageTask failed: " + e.getMessage(), e);
		}
	}
}