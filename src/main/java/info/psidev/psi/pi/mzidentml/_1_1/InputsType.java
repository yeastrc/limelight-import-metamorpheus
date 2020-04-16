//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.16 at 11:07:07 AM PDT 
//


package info.psidev.psi.pi.mzidentml._1_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The inputs to the analyses including the databases searched, the spectral data and the source file converted to mzIdentML. 
 * 
 * <p>Java class for InputsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InputsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SourceFile" type="{http://psidev.info/psi/pi/mzIdentML/1.1.1}SourceFileType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SearchDatabase" type="{http://psidev.info/psi/pi/mzIdentML/1.1.1}SearchDatabaseType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SpectraData" type="{http://psidev.info/psi/pi/mzIdentML/1.1.1}SpectraDataType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputsType", propOrder = {
    "sourceFile",
    "searchDatabase",
    "spectraData"
})
public class InputsType {

    @XmlElement(name = "SourceFile")
    protected List<SourceFileType> sourceFile;
    @XmlElement(name = "SearchDatabase")
    protected List<SearchDatabaseType> searchDatabase;
    @XmlElement(name = "SpectraData", required = true)
    protected List<SpectraDataType> spectraData;

    /**
     * Gets the value of the sourceFile property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sourceFile property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSourceFile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SourceFileType }
     * 
     * 
     */
    public List<SourceFileType> getSourceFile() {
        if (sourceFile == null) {
            sourceFile = new ArrayList<SourceFileType>();
        }
        return this.sourceFile;
    }

    /**
     * Gets the value of the searchDatabase property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchDatabase property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchDatabase().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchDatabaseType }
     * 
     * 
     */
    public List<SearchDatabaseType> getSearchDatabase() {
        if (searchDatabase == null) {
            searchDatabase = new ArrayList<SearchDatabaseType>();
        }
        return this.searchDatabase;
    }

    /**
     * Gets the value of the spectraData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spectraData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpectraData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpectraDataType }
     * 
     * 
     */
    public List<SpectraDataType> getSpectraData() {
        if (spectraData == null) {
            spectraData = new ArrayList<SpectraDataType>();
        }
        return this.spectraData;
    }

}