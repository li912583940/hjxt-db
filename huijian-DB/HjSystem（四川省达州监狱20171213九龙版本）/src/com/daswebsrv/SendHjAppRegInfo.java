
package com.daswebsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sRegisterInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sRegisterInfo"
})
@XmlRootElement(name = "sendHjAppRegInfo")
public class SendHjAppRegInfo {

    @XmlElement(required = true)
    protected String sRegisterInfo;

    /**
     * Gets the value of the sRegisterInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSRegisterInfo() {
        return sRegisterInfo;
    }

    /**
     * Sets the value of the sRegisterInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSRegisterInfo(String value) {
        this.sRegisterInfo = value;
    }

}
