//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.16 at 11:07:07 AM PDT 
//


package info.psidev.psi.pi.mzidentml._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The collection of input and output data sets of the analyses.
 * 			
 * 
 * <p>Java class for DataCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Inputs" type="{http://psidev.info/psi/pi/mzIdentML/1.1.1}InputsType"/>
 *         &lt;element name="AnalysisData" type="{http://psidev.info/psi/pi/mzIdentML/1.1.1}AnalysisDataType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataCollectionType", propOrder = {
    "inputs",
    "analysisData"
})
public class DataCollectionType {

    @XmlElement(name = "Inputs", required = true)
    protected InputsType inputs;
    @XmlElement(name = "AnalysisData", required = true)
    protected AnalysisDataType analysisData;

    /**
     * Gets the value of the inputs property.
     * 
     * @return
     *     possible object is
     *     {@link InputsType }
     *     
     */
    public InputsType getInputs() {
        return inputs;
    }

    /**
     * Sets the value of the inputs property.
     * 
     * @param value
     *     allowed object is
     *     {@link InputsType }
     *     
     */
    public void setInputs(InputsType value) {
        this.inputs = value;
    }

    /**
     * Gets the value of the analysisData property.
     * 
     * @return
     *     possible object is
     *     {@link AnalysisDataType }
     *     
     */
    public AnalysisDataType getAnalysisData() {
        return analysisData;
    }

    /**
     * Sets the value of the analysisData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnalysisDataType }
     *     
     */
    public void setAnalysisData(AnalysisDataType value) {
        this.analysisData = value;
    }

}
