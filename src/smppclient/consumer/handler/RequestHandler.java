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
import smppclient.consumer.provgw.Client;

public class RequestHandler implements IRequestHandler {

	private String mRequestXML;
	private String correlationId;
	private Logger log = Logger.getLogger(getClass().getName());
	
	private static String endPoint;
	
	static {
		ResourceBundle myResources = ResourceBundle.getBundle("client");
		endPoint = myResources.getString("request.msgreplyws.endpoint");
	}
	
	public RequestHandler(String requestXML, String correlationId) {
		this.mRequestXML = requestXML;
		
		log.info("Invoking RequestHandler for: " + correlationId);
	}
	
	@Override
	public void run() {
		log.info("Outgoing request >> " + mRequestXML);
		
		String response = new Client().sendRequest(mRequestXML);
		
		log.info("Incoming response << " + response);
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