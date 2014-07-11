
package org.me.mgpoolbulk;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.mgpoolbulk package. 
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

    private final static QName _GetServiceMsgsResponse_QNAME = new QName("http://MGPoolBulk.me.org/", "GetServiceMsgsResponse");
    private final static QName _GetServiceMsgs_QNAME = new QName("http://MGPoolBulk.me.org/", "GetServiceMsgs");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.mgpoolbulk
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetServiceMsgsResponse }
     * 
     */
    public GetServiceMsgsResponse createGetServiceMsgsResponse() {
        return new GetServiceMsgsResponse();
    }

    /**
     * Create an instance of {@link GetServiceMsgs }
     * 
     */
    public GetServiceMsgs createGetServiceMsgs() {
        return new GetServiceMsgs();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiceMsgsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MGPoolBulk.me.org/", name = "GetServiceMsgsResponse")
    public JAXBElement<GetServiceMsgsResponse> createGetServiceMsgsResponse(GetServiceMsgsResponse value) {
        return new JAXBElement<GetServiceMsgsResponse>(_GetServiceMsgsResponse_QNAME, GetServiceMsgsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiceMsgs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MGPoolBulk.me.org/", name = "GetServiceMsgs")
    public JAXBElement<GetServiceMsgs> createGetServiceMsgs(GetServiceMsgs value) {
        return new JAXBElement<GetServiceMsgs>(_GetServiceMsgs_QNAME, GetServiceMsgs.class, null, value);
    }

}
