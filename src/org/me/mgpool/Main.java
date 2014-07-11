package org.me.mgpool;

import javax.xml.ws.BindingProvider;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MGPoolWSService service = new MGPoolWSService();
		MGPoolWS port = service.getMGPoolWSPort();
		
		BindingProvider bindingProvider = (BindingProvider) port;
		System.out.println(bindingProvider.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
		
		port.getServiceMsgs("", "", "");
	}

}
