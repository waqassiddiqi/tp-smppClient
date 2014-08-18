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
import org.me.mgpoolbulk.MGPoolBulkWS;
import org.me.mgpoolbulk.MGPoolBulkWSService;
import org.me.mgpoolbulk.StringArray;

import smppclient.consumer.handler.IRequestHandler;
import smppclient.consumer.handler.LogMessageHandler;
import smppclient.consumer.handler.RequestHandler;
import smppclient.consumer.provgw.util.RequestBuilder;

public class PullMessagesTask extends TimerTask {
	
	private Logger log = Logger.getLogger(getClass().getName());
	
	private static String endPoint;
	private static ExecutorService mRequestPool;
	private static int mMaxClients = 10;
	
	static {
		ResourceBundle myResources = ResourceBundle.getBundle("client");
		
		try {
			endPoint = myResources.getString("request.mgpoolws.endpoint");					
			mMaxClients = Integer.parseInt(myResources.getString("provgw.max_connections"));						
		} catch (Exception e) {}
	}
	
	public PullMessagesTask() {
		if(mRequestPool == null) {
			mRequestPool = Executors.newFixedThreadPool(mMaxClients);
		}
	}	
	
	@SuppressWarnings("rawtypes")
	@Override
	public void run() {
		
		try {
			MGPoolBulkWSService service = new MGPoolBulkWSService();
			MGPoolBulkWS port = service.getMGPoolBulkWSPort();
			
			BindingProvider bindingProvider = (BindingProvider) port;
			bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
			
			Binding binding = bindingProvider.getBinding();
		    List<Handler> handlerChain = binding.getHandlerChain();
		    handlerChain.add(new LogMessageHandler());
		    binding.setHandlerChain(handlerChain);
			
			log.info("Making request to: " + bindingProvider.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
			
			List<StringArray> responseList = port.getServiceMsgs(Consumer.aspUsername, 
					Consumer.aspPassword, Consumer.serviceShortCode);
			
			
			log.info("Messages pulled from server: " + responseList.size());
			
			String requestXml = "";
			IRequestHandler handler = null;
			
			for(StringArray response : responseList) {
								
				if( !validateParam(response.getMSISDN()) )
					continue;
				
				if(response.getXms().trim().toLowerCase().startsWith("unsub")) {
					
					requestXml = RequestBuilder.build(
							RequestBuilder.REQUEST_FUNCTION_UNSUB, "SMS", 
								new String[] { "msisdn", response.getMSISDN() });
					
				} else if(response.getXms().trim().toLowerCase().startsWith("sub")) {
					
					requestXml = RequestBuilder.build(
							RequestBuilder.REQUEST_FUNCTION_SUB, "SMS", 
								new String[] { "msisdn", response.getMSISDN() });
					
				} else if(response.getXms().trim().toLowerCase().startsWith("add")) {
					
					String skypeId = response.getXms().substring(
							response.getXms().trim().indexOf(" "));
					
					requestXml = RequestBuilder.build(
							RequestBuilder.REQUEST_FUNCTION_ADD_SKYPE_ID, "SMS", 
								new String[] { "msisdn", response.getMSISDN(), "skypeid", skypeId.trim() });
					
				} else if(response.getXms().trim().toLowerCase().startsWith("del")) {
					
					String skypeId = response.getXms().substring(
							response.getXms().trim().indexOf(" "));
					
					requestXml = RequestBuilder.build(
							RequestBuilder.REQUEST_FUNCTION_REMOVE_SKYPE_ID, "SMS", 
								new String[] { "msisdn", response.getMSISDN(), "skypeid", skypeId.trim() });
					
				} else if(response.getXms().trim().toLowerCase().startsWith("list")) {
					
					requestXml = RequestBuilder.build(
							RequestBuilder.REQUEST_FUNCTION_QUERY_BUDDY_LIST, "SMS", 
								new String[] { "msisdn", response.getMSISDN() });
					
				}  else if(response.getXms().trim().toLowerCase().startsWith("status")) {
					
					requestXml = RequestBuilder.build(
							RequestBuilder.REQUEST_FUNCTION_STATUS, "SMS", 
								new String[] { "msisdn", response.getMSISDN() });
					
				} else if(response.getXms().trim().toLowerCase().startsWith("help")) {
					
					requestXml = RequestBuilder.build(
							RequestBuilder.REQUEST_FUNCTION_HELP, "SMS", 
								new String[] { "msisdn", response.getMSISDN() });
				}
				
				handler = new RequestHandler(response.getMSISDN(), response.getCorrelationID(), requestXml);
				mRequestPool.execute(handler);
			}
			
		} catch (Exception e) {
			log.error("PullMessageTask failed: " + e.getMessage(), e);
		}
	}
	
	private boolean validateParam(Object o) {
		if(o == null)
			return false;
		
		if(o.toString().trim().length() == 0)
			return false;
		
		return true;
	}
}