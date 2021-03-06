
package org.me.mgreply;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MGReplyWSService", targetNamespace = "http://MGReply.me.org/", wsdlLocation = "classpath:wsdl/MGReplyWS_1.wsdl")
public class MGReplyWSService
    extends Service
{

    private final static URL MGREPLYWSSERVICE_WSDL_LOCATION;
    private final static WebServiceException MGREPLYWSSERVICE_EXCEPTION;
    private final static QName MGREPLYWSSERVICE_QNAME = new QName("http://MGReply.me.org/", "MGReplyWSService");

    static {
    	URL url = null;
        WebServiceException e = null;
        try {
            url = MGReplyWSService.class.getClassLoader().getResource("wsdl/MGReplyWS_1.wsdl");
        } catch (Exception ex) {
            e = new WebServiceException(ex);
        }
        
        MGREPLYWSSERVICE_WSDL_LOCATION = url;
        MGREPLYWSSERVICE_EXCEPTION = e;
    }

    public MGReplyWSService() {
        super(__getWsdlLocation(), MGREPLYWSSERVICE_QNAME);
    }

    public MGReplyWSService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MGREPLYWSSERVICE_QNAME, features);
    }

    public MGReplyWSService(URL wsdlLocation) {
        super(wsdlLocation, MGREPLYWSSERVICE_QNAME);
    }

    public MGReplyWSService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MGREPLYWSSERVICE_QNAME, features);
    }

    public MGReplyWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MGReplyWSService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MGReplyWS
     */
    @WebEndpoint(name = "MGReplyWSPort")
    public MGReplyWS getMGReplyWSPort() {
        return super.getPort(new QName("http://MGReply.me.org/", "MGReplyWSPort"), MGReplyWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MGReplyWS
     */
    @WebEndpoint(name = "MGReplyWSPort")
    public MGReplyWS getMGReplyWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://MGReply.me.org/", "MGReplyWSPort"), MGReplyWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MGREPLYWSSERVICE_EXCEPTION!= null) {
            throw MGREPLYWSSERVICE_EXCEPTION;
        }
        return MGREPLYWSSERVICE_WSDL_LOCATION;
    }

}
