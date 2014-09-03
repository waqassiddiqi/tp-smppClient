package smppclient.consumer.task;

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

import smppclient.consumer.Consumer;
import smppclient.consumer.handler.IRequestHandler;
import smppclient.consumer.handler.IRequestHandler.RequestType;
import smppclient.consumer.handler.LogMessageHandler;
import smppclient.consumer.handler.RequestHandler;
import smppclient.consumer.provgw.util.RequestBuilder;

public class PullMessagesTask extends TimerTask {
	
	private Logger log = Logger.getLogger(getClass().getName());
	
	private static String endPoint;
	private static ExecutorService mRequestPool;
	private static int mMaxClients = 10;
	private static boolean autoPrivision = true;
	
	static {
		ResourceBundle myResources = ResourceBundle.getBundle("client");
		
		try {
			endPoint = myResources.getString("request.mgpoolws.endpoint");					
			mMaxClients = Integer.parseInt(myResources.getString("provgw.max_connections"));
			autoPrivision = Integer.parseInt(myResources.getString("provgw.auto_provision")) == 1 ? true : false;
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
			
			/***
			 * Test
			 */
			/*
			StringArray arr = new StringArray();
			arr.setMSISDN("923202191532");
			arr.setXms("add waqas11");
			arr.setCorrelationID("id");
			
			List<StringArray> responseList = new ArrayList<StringArray>();
			responseList.add(arr);
			*/
			
			String requestXml = "";
			IRequestHandler handler = null;
			RequestType requestType = RequestType.OTHER;
			
			for(StringArray response : responseList) {
				
				if(!validateParam(response))
					continue;
				
				if(response.getXms().trim().toLowerCase().startsWith("unsub")) {
					
					requestXml = RequestBuilder.build(
							RequestBuilder.REQUEST_FUNCTION_UNSUB, "SMS", 
								new String[] { "msisdn", response.getMSISDN() });
					
					requestType = RequestType.UNSUB;
					
				} else if(response.getXms().trim().toLowerCase().startsWith("sub")) {
					
					requestXml = RequestBuilder.build(
							RequestBuilder.REQUEST_FUNCTION_SUB, "SMS", 
								new String[] { "msisdn", response.getMSISDN() });
					
					requestType = RequestType.SUB;
					
				} else if(response.getXms().trim().toLowerCase().startsWith("add")) {
					
					String skypeId = response.getXms().substring(
							response.getXms().trim().indexOf(" "));
					
					requestXml = RequestBuilder.build(
							RequestBuilder.REQUEST_FUNCTION_ADD_SKYPE_ID, "SMS", 
								new String[] { "msisdn", response.getMSISDN(), "skypeid", skypeId.trim() });
					
					if(autoPrivision)
						requestType = RequestType.SUB;
					
				} else if(response.getXms().trim().toLowerCase().startsWith("del")) {
					
					String skypeId = response.getXms().substring(
							response.getXms().trim().indexOf(" "));
					
					requestXml = RequestBuilder.build(
							RequestBuilder.REQUEST_FUNCTION_REMOVE_SKYPE_ID, "SMS", 
								new String[] { "msisdn", response.getMSISDN(), "skypeid", skypeId.trim() });
					
					if(autoPrivision)
						requestType = RequestType.SUB;
					
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
				
				handler = new RequestHandler(response.getMSISDN(), response.getCorrelationID(), 
						requestType, requestXml);
				
				mRequestPool.execute(handler);
			}
			
		} catch (Exception e) {
			log.error("PullMessageTask failed: " + e.getMessage(), e);
		}
	}
	
	private boolean validateParam(StringArray stringArray) {
		if(stringArray == null)
			return false;
		
		if(stringArray.getMSISDN() == null)
			return false;
		
		if(stringArray.getMSISDN().trim().length() == 0)
			return false;
		
		return true;
	}
}