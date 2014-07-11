
package pkg_directroute;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pkg_directroute package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GenerateServiceRequestResponse_QNAME = new QName("http://PKG_DirectRoute/", "generate_Service_RequestResponse");
    private final static QName _GenerateServiceRequest_QNAME = new QName("http://PKG_DirectRoute/", "generate_Service_Request");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pkg_directroute
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GenerateServiceRequestResponse }
     * 
     */
    public GenerateServiceRequestResponse createGenerateServiceRequestResponse() {
        return new GenerateServiceRequestResponse();
    }

    /**
     * Create an instance of {@link GenerateServiceRequest }
     * 
     */
    public GenerateServiceRequest createGenerateServiceRequest() {
        return new GenerateServiceRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateServiceRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://PKG_DirectRoute/", name = "generate_Service_RequestResponse")
    public JAXBElement<GenerateServiceRequestResponse> createGenerateServiceRequestResponse(GenerateServiceRequestResponse value) {
        return new JAXBElement<GenerateServiceRequestResponse>(_GenerateServiceRequestResponse_QNAME, GenerateServiceRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateServiceRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://PKG_DirectRoute/", name = "generate_Service_Request")
    public JAXBElement<GenerateServiceRequest> createGenerateServiceRequest(GenerateServiceRequest value) {
        return new JAXBElement<GenerateServiceRequest>(_GenerateServiceRequest_QNAME, GenerateServiceRequest.class, null, value);
    }

}
