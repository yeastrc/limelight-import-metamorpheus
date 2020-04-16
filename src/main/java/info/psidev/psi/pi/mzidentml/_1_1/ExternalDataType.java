//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.16 at 11:07:07 AM PDT 
//


package info.psidev.psi.pi.mzidentml._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Data external to the XML instance document. The location of the data file is given in the location attribute. 
 * 
 * <p>Java class for ExternalDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExternalDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.1.1}IdentifiableType">
 *       &lt;sequence>
 *         &lt;element name="ExternalFormatDocumentation" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="FileFormat" type="{http://psidev.info/psi/pi/mzIdentML/1.1.1}FileFormatType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="location" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExternalDataType", propOrder = {
    "externalFormatDocumentation",
    "fileFormat"
})
@XmlSeeAlso({
    SearchDatabaseType.class,
    SourceFileType.class,
    SpectraDataType.class
})
public class ExternalDataType
    extends IdentifiableType
{

    @XmlElement(name = "ExternalFormatDocumentation")
    @XmlSchemaType(name = "anyURI")
    protected String externalFormatDocumentation;
    @XmlElement(name = "FileFormat")
    protected FileFormatType fileFormat;
    @XmlAttribute(name = "location", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String location;

    /**
     * Gets the value of the externalFormatDocumentation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalFormatDocumentation() {
        return externalFormatDocumentation;
    }

    /**
     * Sets the value of the externalFormatDocumentation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalFormatDocumentation(String value) {
        this.externalFormatDocumentation = value;
    }

    /**
     * Gets the value of the fileFormat property.
     * 
     * @return
     *     possible object is
     *     {@link FileFormatType }
     *     
     */
    public FileFormatType getFileFormat() {
        return fileFormat;
    }

    /**
     * Sets the value of the fileFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileFormatType }
     *     
     */
    public void setFileFormat(FileFormatType value) {
        this.fileFormat = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

}
