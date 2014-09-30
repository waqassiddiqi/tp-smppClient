package smppclient.consumer.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import org.apache.log4j.Logger;

import pkg_cp_sip.CPSIPWebService;
import pkg_cp_sip.CPSIPWebServiceService;
import pkg_cp_sip.StringArray;
import smppclient.consumer.Consumer;
import smppclient.consumer.handler.IRequestHandler.RequestType;
import smppclient.consumer.handler.LogMessageHandler;
import smppclient.consumer.util.NumberUtil;

public class CPSIPRequestTask {
	private static String endPoint = "";
	private static String cpId = "";
	private static int expiryDays = 7;
	private static HashMap<String, String> serviceIdsMap;
	
	private static Logger log = Logger.getLogger(CPSIPRequestTask.class.getName());
	
	static {
		ResourceBundle myResources = ResourceBundle.getBundle("client");
		endPoint = myResources.getString("request.cpsip.endpoint");
		expiryDays = Integer.parseInt(myResources.getString("request.cpsip.expiry_days"));
		cpId = myResources.getString("request.cpsip.cpid");
		
		serviceIdsMap = new HashMap<String, String>();
		
		List<String> keys = Collections.list(myResources.getKeys() );
		
		for(String key : keys) {
			if(key.startsWith("consumer.service.")) {
				serviceIdsMap.put(key, myResources.getString(key));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void sendRequest(String correlationId, String msisdn, RequestType requestType, String resultCode) {
		
		if(requestType == RequestType.OTHER)
			return;
		
		msisdn = NumberUtil.toLocal(msisdn);
		
		log.info("Making CIP request of: " + requestType + " for:" + msisdn + " with result code : " + resultCode);
		
		
		try {
			CPSIPWebServiceService service = new CPSIPWebServiceService();
			CPSIPWebService port = service.getCPSIPWebServicePort();
			
			BindingProvider bindingProvider = (BindingProvider) port;
			bindingProvider.getRequestContext().put("javax.xml.ws.service.endpoint.address", endPoint);
			
			Binding binding = bindingProvider.getBinding();
			List<Handler> handlerChain = binding.getHandlerChain();
			handlerChain.add(new LogMessageHandler());
			binding.setHandlerChain(handlerChain);

			log.info("Making request to: " + bindingProvider.getRequestContext().get("javax.xml.ws.service.endpoint.address"));
			
			String serviceId = "";		
			
			StringArray arr = new StringArray();
			arr.getItem().add(correlationId);
			arr.getItem().add(msisdn);
			
			if(resultCode.equals("0"))
				arr.getItem().add("1");
			else
				arr.getItem().add("2");
			
			Calendar calExpiry = Calendar.getInstance();
			calExpiry.add(Calendar.DAY_OF_YEAR, expiryDays);
			
			arr.getItem().add(new SimpleDateFormat("dd-MM-yyyy").format(calExpiry.getTime()));
			
			if(requestType == RequestType.SUB) {
				serviceId = serviceIdsMap.get("consumer.service.sub");
				
				arr.getItem().add("1");
				
			} else if(requestType == RequestType.UNSUB) {
				serviceId = serviceIdsMap.get("consumer.service.unsub");
				
				arr.getItem().add("2");
			}
			
			List<StringArray> transData = new ArrayList<StringArray>();
			transData.add(arr);
			
			log.info("Response from server: " + 
					port.provisioning(Consumer.aspUsername, Consumer.aspPassword, serviceId, transData, cpId));
		} catch (Exception e) {
			log.error("Sending direct router request failed: " + e.getMessage(), e);
		}
	}
}