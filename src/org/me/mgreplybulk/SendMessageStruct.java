
package org.me.mgreplybulk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendMessageStruct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendMessageStruct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CorrelationID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="xms" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="failedChargingXms" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendMessageStruct", propOrder = {
    "correlationID",
    "xms",
    "from",
    "type",
    "failedChargingXms"
})
public class SendMessageStruct {

    @XmlElement(name = "CorrelationID", required = true)
    protected String correlationID;
    @XmlElement(required = true)
    protected String xms;
    @XmlElement(required = true)
    protected String from;
    @XmlElement(name = "Type")
    protected int type;
    @XmlElement(required = true)
    protected String failedChargingXms;

    /**
     * Gets the value of the correlationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelationID() {
        return correlationID;
    }

    /**
     * Sets the value of the correlationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelationID(String value) {
        this.correlationID = value;
    }

    /**
     * Gets the value of the xms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXms() {
        return xms;
    }

    /**
     * Sets the value of the xms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXms(String value) {
        this.xms = value;
    }

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Gets the value of the type property.
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

    /**
     * Gets the value of the failedChargingXms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailedChargingXms() {
        return failedChargingXms;
    }

    /**
     * Sets the value of the failedChargingXms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailedChargingXms(String value) {
        this.failedChargingXms = value;
    }

}
