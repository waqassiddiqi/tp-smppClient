
package org.me.mgpool;

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
@WebServiceClient(name = "MGPoolWSService", targetNamespace = "http://MGPool.me.org/", wsdlLocation = "classpath:wsdl/MGPoolWS_1.wsdl")
public class MGPoolWSService
    extends Service
{

    private final static URL MGPOOLWSSERVICE_WSDL_LOCATION;
    private final static WebServiceException MGPOOLWSSERVICE_EXCEPTION;
    private final static QName MGPOOLWSSERVICE_QNAME = new QName("http://MGPool.me.org/", "MGPoolWSService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = MGPoolWSService.class.getClassLoader().getResource("wsdl/MGPoolWS_1.wsdl");
        } catch (Exception ex) {
            e = new WebServiceException(ex);
        }
        MGPOOLWSSERVICE_WSDL_LOCATION = url;
        MGPOOLWSSERVICE_EXCEPTION = e;
    }

    public MGPoolWSService() {
        super(__getWsdlLocation(), MGPOOLWSSERVICE_QNAME);
    }

    public MGPoolWSService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MGPOOLWSSERVICE_QNAME, features);
    }

    public MGPoolWSService(URL wsdlLocation) {
        super(wsdlLocation, MGPOOLWSSERVICE_QNAME);
    }

    public MGPoolWSService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MGPOOLWSSERVICE_QNAME, features);
    }

    public MGPoolWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MGPoolWSService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MGPoolWS
     */
    @WebEndpoint(name = "MGPoolWSPort")
    public MGPoolWS getMGPoolWSPort() {
        return super.getPort(new QName("http://MGPool.me.org/", "MGPoolWSPort"), MGPoolWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MGPoolWS
     */
    @WebEndpoint(name = "MGPoolWSPort")
    public MGPoolWS getMGPoolWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://MGPool.me.org/", "MGPoolWSPort"), MGPoolWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MGPOOLWSSERVICE_EXCEPTION!= null) {
            throw MGPOOLWSSERVICE_EXCEPTION;
        }
        return MGPOOLWSSERVICE_WSDL_LOCATION;
    }

}