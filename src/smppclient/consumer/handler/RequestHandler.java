package smppclient.consumer.handler;

import java.io.StringReader;
import java.util.ResourceBundle;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import smppclient.consumer.PushMessageTask;
import smppclient.consumer.provgw.Client;

public class RequestHandler implements IRequestHandler {
	private String mRequestXML;
	private String mCorrelationId;
	private String mMsisdn;
	private Logger log = Logger.getLogger(getClass().getName());

	private static String systemErrorText = "";
	private static String invalidCommand = "";

	static {
		ResourceBundle myResources = ResourceBundle.getBundle("client");
		systemErrorText = myResources.getString("response.sms_on_error");
		invalidCommand = myResources.getString("response.invalid_keyword");
	}

	public RequestHandler(String msisdn, String correlationId, String requestXML) {
		this.mRequestXML = requestXML;
		this.mCorrelationId = correlationId;
		this.mMsisdn = msisdn;

		this.log.info("Invoking RequestHandler for: " + correlationId);
	}

	public void run() {
		this.log.info("Outgoing request >> " + this.mRequestXML);

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		InputSource source = null;
		String response = "";
		String smsResponseText = "";
		try {
			response = new Client().sendRequest(this.mRequestXML);
			
			this.log.info("Incoming response << " + response);

			response = "<root>" + response.replace("|", "") + "</root>";
			
			source = new InputSource(new StringReader(response));
			NodeList textMessages = (NodeList) xpath.evaluate("//Response/resultMessage/message/text()", source, XPathConstants.NODESET);

			for (int i = 0; i < textMessages.getLength(); i++) {
				
				smsResponseText = textMessages.item(i).getNodeValue();
				
				if ((smsResponseText != null) && (smsResponseText.trim().length() > 0))
					new PushMessageTask().sendSmsResponse(this.mMsisdn, this.mCorrelationId, smsResponseText);
				else
					new PushMessageTask().sendSmsResponse(this.mMsisdn, this.mCorrelationId, invalidCommand);
			}
			
			
			
		} catch (Exception e) {
			new PushMessageTask().sendSmsResponse(this.mMsisdn, this.mCorrelationId, systemErrorText);
			this.log.error(e.getMessage(), e);
		}
	}
}