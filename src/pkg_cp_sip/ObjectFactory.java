
package pkg_cp_sip;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pkg_cp_sip package. 
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

    private final static QName _Provisioning_QNAME = new QName("http://PKG_CP_SIP/", "Provisioning");
    private final static QName _ProvisioningResponse_QNAME = new QName("http://PKG_CP_SIP/", "ProvisioningResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pkg_cp_sip
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Provisioning }
     * 
     */
    public Provisioning createProvisioning() {
        return new Provisioning();
    }

    /**
     * Create an instance of {@link ProvisioningResponse }
     * 
     */
    public ProvisioningResponse createProvisioningResponse() {
        return new ProvisioningResponse();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Provisioning }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://PKG_CP_SIP/", name = "Provisioning")
    public JAXBElement<Provisioning> createProvisioning(Provisioning value) {
        return new JAXBElement<Provisioning>(_Provisioning_QNAME, Provisioning.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProvisioningResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://PKG_CP_SIP/", name = "ProvisioningResponse")
    public JAXBElement<ProvisioningResponse> createProvisioningResponse(ProvisioningResponse value) {
        return new JAXBElement<ProvisioningResponse>(_ProvisioningResponse_QNAME, ProvisioningResponse.class, null, value);
    }

}
