package org.yeastrc.limelight.xml.metamorpheus.reader;

import info.psidev.psi.pi.mzidentml._1_1.*;
import org.yeastrc.limelight.xml.metamorpheus.objects.MetamorpheusProtein;
import org.yeastrc.limelight.xml.metamorpheus.objects.MetamorpheusReportedPeptide;
import org.yeastrc.limelight.xml.metamorpheus.objects.MetamorpheusResults;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class MetamorpheusResultsReader {

    public static MetamorpheusResults getResults(File mzidFile) throws JAXBException {

        MzIdentMLType mzIdentML = getMzIdentML(mzidFile);

        String version = mzIdentML.getAnalysisSoftwareList().getAnalysisSoftware().get(0).getVersion();
        Map<String, BigDecimal> staticMods = getStaticMods(mzIdentML);

        // A map of proteins parsed from the mzIdentML, keyed by DBSequence.id
        Map<String, MetamorpheusProtein> proteinMap = new HashMap<>();

        // A map of peptides parsed from the mzIdentML, keyed by Peptide.id in that file
        Map<String, MetamorpheusReportedPeptide> reportedPeptideMap = new HashMap<>();

        System.out.println(version);

        return null;
    }

    private static Map<String, BigDecimal> getStaticMods(MzIdentMLType mzIdentML) {
        Map<String, BigDecimal> staticMods = new HashMap<>();

        AnalysisProtocolCollectionType analysisProtocol = mzIdentML.getAnalysisProtocolCollection();
        if(analysisProtocol != null) {
            for( SpectrumIdentificationProtocolType sipt : analysisProtocol.getSpectrumIdentificationProtocol() ) {
                ModificationParamsType modificationParams = sipt.getModificationParams();
                if(modificationParams != null) {
                    for(SearchModificationType searchModification : modificationParams.getSearchModification()) {
                        if(searchModification.isFixedMod()) {
                            BigDecimal massShift = BigDecimal.valueOf( searchModification.getMassDelta() );

                            for(String residue : searchModification.getResidues()) {
                                staticMods.put(residue, massShift);
                            }
                        }
                    }
                }
            }
        }

        return staticMods;
    }

    private static MzIdentMLType getMzIdentML(File mzidFile) throws JAXBException {

        MzIdentMLType mzIdentML = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MzIdentMLType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            mzIdentML = (MzIdentMLType)jaxbUnmarshaller.unmarshal( mzidFile );
        } catch (JAXBException e) {
            System.err.println("Error processing mzIdentML file: " + mzidFile.getAbsolutePath());
            throw e;
        }


        return mzIdentML;
    }
}
