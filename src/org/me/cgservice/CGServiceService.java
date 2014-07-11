
package org.me.cgservice;

import java.net.MalformedURLException;
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
@WebServiceClient(name = "CGServiceService", targetNamespace = "http://CGService.me.org/", wsdlLocation = "classpath:wsdl/TP-CHARGE-WS_1.wsdl")
public class CGServiceService
    extends Service
{

    private final static URL CGSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException CGSERVICESERVICE_EXCEPTION;
    private final static QName CGSERVICESERVICE_QNAME = new QName("http://CGService.me.org/", "CGServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Workspaces/TP/SmppClient/src/TP-CHARGE-WS_1.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CGSERVICESERVICE_WSDL_LOCATION = url;
        CGSERVICESERVICE_EXCEPTION = e;
    }

    public CGServiceService() {
        super(__getWsdlLocation(), CGSERVICESERVICE_QNAME);
    }

    public CGServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CGSERVICESERVICE_QNAME, features);
    }

    public CGServiceService(URL wsdlLocation) {
        super(wsdlLocation, CGSERVICESERVICE_QNAME);
    }

    public CGServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CGSERVICESERVICE_QNAME, features);
    }

    public CGServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CGServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CGService
     */
    @WebEndpoint(name = "CGServicePort")
    public CGService getCGServicePort() {
        return super.getPort(new QName("http://CGService.me.org/", "CGServicePort"), CGService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CGService
     */
    @WebEndpoint(name = "CGServicePort")
    public CGService getCGServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://CGService.me.org/", "CGServicePort"), CGService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CGSERVICESERVICE_EXCEPTION!= null) {
            throw CGSERVICESERVICE_EXCEPTION;
        }
        return CGSERVICESERVICE_WSDL_LOCATION;
    }

}
