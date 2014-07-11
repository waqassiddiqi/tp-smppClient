package org.me.mgreply;

import java.util.List;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import smppclient.consumer.handler.LogMessageHandler;

public class Main {
	public static void main(String[] args) {
		MGReplyWSService service = new MGReplyWSService();
		MGReplyWS port = service.getMGReplyWSPort();
		
		BindingProvider bindingProvider = (BindingProvider) port;
		//bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
		
		Binding binding = bindingProvider.getBinding();
	    List<Handler> handlerChain = binding.getHandlerChain();
	    handlerChain.add(new LogMessageHandler());
	    binding.setHandlerChain(handlerChain);
	    
	    System.out.println(bindingProvider.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
	    
	}

}
