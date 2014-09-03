package smppclient.consumer.task;

import java.util.List;
import java.util.ResourceBundle;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import org.apache.log4j.Logger;
import org.me.mgreply.MGReplyWS;
import org.me.mgreply.MGReplyWSService;

import smppclient.consumer.Consumer;
import smppclient.consumer.handler.LogMessageHandler;

public class PushMessageTask {
	private static String endPoint = "";
	private static String failedChargingText = "";

	private static Logger log = Logger.getLogger(PushMessageTask.class.getName());

	static {
		ResourceBundle myResources = ResourceBundle.getBundle("client");
		endPoint = myResources.getString("request.msgreplyws.endpoint");
		failedChargingText = myResources.getString("response.failed_charging");
	}

	@SuppressWarnings("rawtypes")
	public void sendSmsResponse(String msisdn, String correlationId, String text) {
		log.info("Sending text messge: " + text + " to:" + msisdn);
		try {
			MGReplyWSService service = new MGReplyWSService();
			MGReplyWS port = service.getMGReplyWSPort();

			BindingProvider bindingProvider = (BindingProvider) port;
			bindingProvider.getRequestContext().put("javax.xml.ws.service.endpoint.address", endPoint);

			Binding binding = bindingProvider.getBinding();
			List<Handler> handlerChain = binding.getHandlerChain();
			handlerChain.add(new LogMessageHandler());
			binding.setHandlerChain(handlerChain);

			log.info("Making request to: "
					+ bindingProvider.getRequestContext().get(
							"javax.xml.ws.service.endpoint.address"));

			String response = port.sendMessage(Consumer.aspUsername,
					Consumer.aspPassword, correlationId, text,
					Consumer.senderId, 1, failedChargingText);

			log.info("Response from server: " + response);
		} catch (Exception e) {
			log.error("Sending msg failed: " + e.getMessage(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public String sendSms(String msisdn, String text, String serviceId) {
		log.info("Sending text messge: " + text + " to:" + msisdn);
		String response = "<Response></Response>";
		
		try {
			MGReplyWSService service = new MGReplyWSService();
			MGReplyWS port = service.getMGReplyWSPort();

			BindingProvider bindingProvider = (BindingProvider) port;
			bindingProvider.getRequestContext().put("javax.xml.ws.service.endpoint.address", endPoint);

			Binding binding = bindingProvider.getBinding();
			List<Handler> handlerChain = binding.getHandlerChain();
			handlerChain.add(new LogMessageHandler());
			binding.setHandlerChain(handlerChain);

			log.info("Making request to: "
					+ bindingProvider.getRequestContext().get(
							"javax.xml.ws.service.endpoint.address"));
			
			response = port.sendMT(Consumer.aspUsername, Consumer.aspPassword, 
					text, Consumer.senderId, msisdn, serviceId, 1);

			log.info("Response from server: " + response);
		} catch (Exception e) {
			log.error("Sending msg failed: " + e.getMessage(), e);
		}
		
		return response;
	}
}