
package org.me.cgservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.cgservice package. 
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

    private final static QName _CommitResponse_QNAME = new QName("http://CGService.me.org/", "commitResponse");
    private final static QName _ReserveResponse_QNAME = new QName("http://CGService.me.org/", "reserveResponse");
    private final static QName _Reserve_QNAME = new QName("http://CGService.me.org/", "reserve");
    private final static QName _RevokeResponse_QNAME = new QName("http://CGService.me.org/", "revokeResponse");
    private final static QName _RefundResponse_QNAME = new QName("http://CGService.me.org/", "refundResponse");
    private final static QName _Commit_QNAME = new QName("http://CGService.me.org/", "commit");
    private final static QName _Refund_QNAME = new QName("http://CGService.me.org/", "refund");
    private final static QName _Revoke_QNAME = new QName("http://CGService.me.org/", "revoke");
    private final static QName _Charge_QNAME = new QName("http://CGService.me.org/", "charge");
    private final static QName _ChargeResponse_QNAME = new QName("http://CGService.me.org/", "chargeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.cgservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Revoke }
     * 
     */
    public Revoke createRevoke() {
        return new Revoke();
    }

    /**
     * Create an instance of {@link ChargeResponse }
     * 
     */
    public ChargeResponse createChargeResponse() {
        return new ChargeResponse();
    }

    /**
     * Create an instance of {@link Charge }
     * 
     */
    public Charge createCharge() {
        return new Charge();
    }

    /**
     * Create an instance of {@link RefundResponse }
     * 
     */
    public RefundResponse createRefundResponse() {
        return new RefundResponse();
    }

    /**
     * Create an instance of {@link Commit }
     * 
     */
    public Commit createCommit() {
        return new Commit();
    }

    /**
     * Create an instance of {@link Refund }
     * 
     */
    public Refund createRefund() {
        return new Refund();
    }

    /**
     * Create an instance of {@link Reserve }
     * 
     */
    public Reserve createReserve() {
        return new Reserve();
    }

    /**
     * Create an instance of {@link RevokeResponse }
     * 
     */
    public RevokeResponse createRevokeResponse() {
        return new RevokeResponse();
    }

    /**
     * Create an instance of {@link CommitResponse }
     * 
     */
    public CommitResponse createCommitResponse() {
        return new CommitResponse();
    }

    /**
     * Create an instance of {@link ReserveResponse }
     * 
     */
    public ReserveResponse createReserveResponse() {
        return new ReserveResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CGService.me.org/", name = "commitResponse")
    public JAXBElement<CommitResponse> createCommitResponse(CommitResponse value) {
        return new JAXBElement<CommitResponse>(_CommitResponse_QNAME, CommitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CGService.me.org/", name = "reserveResponse")
    public JAXBElement<ReserveResponse> createReserveResponse(ReserveResponse value) {
        return new JAXBElement<ReserveResponse>(_ReserveResponse_QNAME, ReserveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Reserve }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CGService.me.org/", name = "reserve")
    public JAXBElement<Reserve> createReserve(Reserve value) {
        return new JAXBElement<Reserve>(_Reserve_QNAME, Reserve.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RevokeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CGService.me.org/", name = "revokeResponse")
    public JAXBElement<RevokeResponse> createRevokeResponse(RevokeResponse value) {
        return new JAXBElement<RevokeResponse>(_RevokeResponse_QNAME, RevokeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefundResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CGService.me.org/", name = "refundResponse")
    public JAXBElement<RefundResponse> createRefundResponse(RefundResponse value) {
        return new JAXBElement<RefundResponse>(_RefundResponse_QNAME, RefundResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Commit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CGService.me.org/", name = "commit")
    public JAXBElement<Commit> createCommit(Commit value) {
        return new JAXBElement<Commit>(_Commit_QNAME, Commit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Refund }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CGService.me.org/", name = "refund")
    public JAXBElement<Refund> createRefund(Refund value) {
        return new JAXBElement<Refund>(_Refund_QNAME, Refund.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Revoke }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CGService.me.org/", name = "revoke")
    public JAXBElement<Revoke> createRevoke(Revoke value) {
        return new JAXBElement<Revoke>(_Revoke_QNAME, Revoke.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Charge }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CGService.me.org/", name = "charge")
    public JAXBElement<Charge> createCharge(Charge value) {
        return new JAXBElement<Charge>(_Charge_QNAME, Charge.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CGService.me.org/", name = "chargeResponse")
    public JAXBElement<ChargeResponse> createChargeResponse(ChargeResponse value) {
        return new JAXBElement<ChargeResponse>(_ChargeResponse_QNAME, ChargeResponse.class, null, value);
    }

}
