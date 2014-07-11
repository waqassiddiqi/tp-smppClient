
package org.me.mgreplybulk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendNotificationStruct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendNotificationStruct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CorrelationID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="xms" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="repServicID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendNotificationStruct", propOrder = {
    "correlationID",
    "xms",
    "from",
    "repServicID"
})
public class SendNotificationStruct {

    @XmlElement(name = "CorrelationID", required = true)
    protected String correlationID;
    @XmlElement(required = true)
    protected String xms;
    @XmlElement(required = true)
    protected String from;
    @XmlElement(required = true)
    protected String repServicID;

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
     * Gets the value of the repServicID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepServicID() {
        return repServicID;
    }

    /**
     * Sets the value of the repServicID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepServicID(String value) {
        this.repServicID = value;
    }

}
