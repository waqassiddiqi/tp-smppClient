package smppclient.consumer.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import org.apache.log4j.Logger;

import pkg_directroute.DirectRoute;
import pkg_directroute.DirectRouteService;
import smppclient.consumer.Consumer;
import smppclient.consumer.handler.IRequestHandler.RequestType;
import smppclient.consumer.handler.LogMessageHandler;
import smppclient.consumer.util.NumberUtil;

public class DirectRouteRequestTask {
	private static String endPoint = "";
	private static HashMap<String, String> serviceIdsMap;
	
	private static Logger log = Logger.getLogger(DirectRouteRequestTask.class.getName());
	
	static {
		ResourceBundle myResources = ResourceBundle.getBundle("client");
		endPoint = myResources.getString("request.directroute.endpoint");
		serviceIdsMap = new HashMap<String, String>();
		
		List<String> keys = Collections.list(myResources.getKeys() );
		
		for(String key : keys) {
			if(key.startsWith("consumer.service.")) {
				serviceIdsMap.put(key, myResources.getString(key));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void sendRequest(String msisdn, RequestType requestType) {
		
		if(requestType == RequestType.OTHER)
			return;
		
		msisdn = NumberUtil.toLocal(msisdn);
		
		log.info("Making SIP request of: " + requestType + " for:" + msisdn);
		
		try {
			DirectRouteService service = new DirectRouteService();
			DirectRoute port = service.getDirectRoutePort();

			BindingProvider bindingProvider = (BindingProvider) port;
			bindingProvider.getRequestContext().put("javax.xml.ws.service.endpoint.address", endPoint);

			Binding binding = bindingProvider.getBinding();
			List<Handler> handlerChain = binding.getHandlerChain();
			handlerChain.add(new LogMessageHandler());
			binding.setHandlerChain(handlerChain);

			log.info("Making request to: "
					+ bindingProvider.getRequestContext().get(
							"javax.xml.ws.service.endpoint.address"));

			String text = "";
			String serviceId = "";
			if(requestType == RequestType.SUB) {
				text = "sub";
				serviceId = serviceIdsMap.get("consumer.service.sub");
			} else if(requestType == RequestType.UNSUB) {
				serviceId = serviceIdsMap.get("consumer.service.unsub");
				text = "unsub";
			}
			
			log.info("Response from server: " + port.generateServiceRequest(Consumer.aspUsername, 
					Consumer.aspPassword, serviceId, msisdn, text));
			
		} catch (Exception e) {
			log.error("Sending direct router request failed: " + e.getMessage(), e);
		}
	}
}