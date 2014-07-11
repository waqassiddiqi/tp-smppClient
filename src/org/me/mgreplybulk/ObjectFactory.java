
package org.me.mgreplybulk;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.mgreplybulk package. 
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

    private final static QName _SendMessage_QNAME = new QName("http://MGReplyBulk.me.org/", "sendMessage");
    private final static QName _SendNotificationResponse_QNAME = new QName("http://MGReplyBulk.me.org/", "sendNotificationResponse");
    private final static QName _SendMT_QNAME = new QName("http://MGReplyBulk.me.org/", "sendMT");
    private final static QName _SendMessageResponse_QNAME = new QName("http://MGReplyBulk.me.org/", "sendMessageResponse");
    private final static QName _SendNotification_QNAME = new QName("http://MGReplyBulk.me.org/", "sendNotification");
    private final static QName _SendMTResponse_QNAME = new QName("http://MGReplyBulk.me.org/", "sendMTResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.mgreplybulk
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendNotificationResponse }
     * 
     */
    public SendNotificationResponse createSendNotificationResponse() {
        return new SendNotificationResponse();
    }

    /**
     * Create an instance of {@link SendMessage }
     * 
     */
    public SendMessage createSendMessage() {
        return new SendMessage();
    }

    /**
     * Create an instance of {@link SendMessageResponse }
     * 
     */
    public SendMessageResponse createSendMessageResponse() {
        return new SendMessageResponse();
    }

    /**
     * Create an instance of {@link SendMT }
     * 
     */
    public SendMT createSendMT() {
        return new SendMT();
    }

    /**
     * Create an instance of {@link SendMTResponse }
     * 
     */
    public SendMTResponse createSendMTResponse() {
        return new SendMTResponse();
    }

    /**
     * Create an instance of {@link SendNotification }
     * 
     */
    public SendNotification createSendNotification() {
        return new SendNotification();
    }

    /**
     * Create an instance of {@link SendMessageStruct }
     * 
     */
    public SendMessageStruct createSendMessageStruct() {
        return new SendMessageStruct();
    }

    /**
     * Create an instance of {@link SendMTStruct }
     * 
     */
    public SendMTStruct createSendMTStruct() {
        return new SendMTStruct();
    }

    /**
     * Create an instance of {@link SendNotificationStruct }
     * 
     */
    public SendNotificationStruct createSendNotificationStruct() {
        return new SendNotificationStruct();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MGReplyBulk.me.org/", name = "sendMessage")
    public JAXBElement<SendMessage> createSendMessage(SendMessage value) {
        return new JAXBElement<SendMessage>(_SendMessage_QNAME, SendMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendNotificationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MGReplyBulk.me.org/", name = "sendNotificationResponse")
    public JAXBElement<SendNotificationResponse> createSendNotificationResponse(SendNotificationResponse value) {
        return new JAXBElement<SendNotificationResponse>(_SendNotificationResponse_QNAME, SendNotificationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MGReplyBulk.me.org/", name = "sendMT")
    public JAXBElement<SendMT> createSendMT(SendMT value) {
        return new JAXBElement<SendMT>(_SendMT_QNAME, SendMT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MGReplyBulk.me.org/", name = "sendMessageResponse")
    public JAXBElement<SendMessageResponse> createSendMessageResponse(SendMessageResponse value) {
        return new JAXBElement<SendMessageResponse>(_SendMessageResponse_QNAME, SendMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendNotification }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MGReplyBulk.me.org/", name = "sendNotification")
    public JAXBElement<SendNotification> createSendNotification(SendNotification value) {
        return new JAXBElement<SendNotification>(_SendNotification_QNAME, SendNotification.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMTResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://MGReplyBulk.me.org/", name = "sendMTResponse")
    public JAXBElement<SendMTResponse> createSendMTResponse(SendMTResponse value) {
        return new JAXBElement<SendMTResponse>(_SendMTResponse_QNAME, SendMTResponse.class, null, value);
    }

}
