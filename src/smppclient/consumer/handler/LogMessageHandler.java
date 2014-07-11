package smppclient.consumer.handler;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

public class LogMessageHandler implements SOAPHandler<SOAPMessageContext> {
	private Logger log = Logger.getLogger(getClass().getName());
	
	@Override
	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		SOAPMessage msg = context.getMessage();
		try {
			
			boolean isOutboundMessage= (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			
			if(isOutboundMessage) {
				log.info(">>> Request");
			} else {
				log.info("<<< Reply");
			}
			
			log.info(getMsgAsString(msg));
			
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}
	
	private String getMsgAsString(SOAPMessage message) {
		String msg = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			message.writeTo(baos);
			msg = baos.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}