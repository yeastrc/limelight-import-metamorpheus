package org.yeastrc.limelight.xml.metamorpheus.reader;

import info.psidev.psi.pi.mzidentml._1.*;
import org.yeastrc.limelight.xml.metamorpheus.objects.MetamorpheusPSM;
import org.yeastrc.limelight.xml.metamorpheus.objects.MetamorpheusProtein;
import org.yeastrc.limelight.xml.metamorpheus.objects.MetamorpheusReportedPeptide;
import org.yeastrc.limelight.xml.metamorpheus.objects.MetamorpheusResults;
import org.yeastrc.limelight.xml.metamorpheus.utils.ReportedPeptideUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class MetamorpheusResultsReader {

    public static MetamorpheusResults getResults(File mzidFile) throws Exception {

        MzIdentMLType mzIdentML = getMzIdentML(mzidFile);

        String version = mzIdentML.getAnalysisSoftwareList().getAnalysisSoftware().get(0).getVersion();
        System.out.println("\tMetaMorpheus version: " + version);

        Map<String, BigDecimal> staticMods = getStaticMods(mzIdentML);
        System.out.println("\tFound " + staticMods.size() + " static mods.");

        // A map of proteins parsed from the mzIdentML, keyed by DBSequence.id
        Map<String, MetamorpheusProtein> proteinMap = getProteins(mzIdentML);
        System.out.println("\tFound " + proteinMap.size() + " protein identifications.");

        // A map of peptides parsed from the mzIdentML, keyed by Peptide.id in that file
        Map<String, MetamorpheusReportedPeptide> reportedPeptideMap = getPeptides(mzIdentML);
        System.out.println("\tFound " + reportedPeptideMap.size() + " distinct peptide ids.");

        Map<MetamorpheusReportedPeptide, Collection<MetamorpheusPSM>> psmPeptideMap = getPSMPeptideMap(mzIdentML, reportedPeptideMap);


        return null;
    }

    private static Map<MetamorpheusReportedPeptide, Collection<MetamorpheusPSM>> getPSMPeptideMap(MzIdentMLType mzIdentML,
                                                                                                  Map<String, MetamorpheusReportedPeptide> reportedPeptideMap) throws Exception {

        Map<MetamorpheusReportedPeptide, Collection<MetamorpheusPSM>> psmPeptideMap = new HashMap<>();
        SpectrumIdentificationListType spectrumIdentificationList = getSpectrumIdentificationList(mzIdentML);


        return psmPeptideMap;
    }

    private static SpectrumIdentificationListType getSpectrumIdentificationList(MzIdentMLType mzIdentML) throws Exception {

        DataCollectionType dataCollection = mzIdentML.getDataCollection();
        if(dataCollection == null) {
            throw new Exception("Could not find DataCollection element.");
        }

        AnalysisDataType analysisData = dataCollection.getAnalysisData();
        if(analysisData == null) {
            throw new Exception("Could not find AnalysisData element.");
        }

        SpectrumIdentificationListType spectrumIdentificationList = analysisData.getSpectrumIdentificationList().get(0);    // assume only one spectrum identification list
        if(spectrumIdentificationList == null) {
            throw new Exception("Could not find SpectrumIdentificationList element.");
        }

        return spectrumIdentificationList;
    }

    private static Map<String, MetamorpheusReportedPeptide> getPeptides(MzIdentMLType mzIdentML) throws Exception {
        Map<String, MetamorpheusReportedPeptide> peptideMap = new HashMap<>();
        Map<String, Collection<String>> pepEvidenceMap = getPeptideEvidenceMap(mzIdentML);

        SequenceCollectionType sequenceCollection = getSequenceCollection(mzIdentML);
        for(PeptideType peptide : sequenceCollection.getPeptide()) {
            MetamorpheusReportedPeptide metamorpheusReportedPeptide = getReportedPeptide(peptide, pepEvidenceMap);
            peptideMap.put(peptide.getId(), metamorpheusReportedPeptide);
        }

        return peptideMap;
    }

    private static MetamorpheusReportedPeptide getReportedPeptide(PeptideType peptide, Map<String, Collection<String>> pepEvidenceMap) {
        MetamorpheusReportedPeptide reportedPeptide = new MetamorpheusReportedPeptide();
        Map<Integer, BigDecimal> mods = getDynamicMods(peptide);

        reportedPeptide.setNakedPeptide(peptide.getPeptideSequence());
        reportedPeptide.setMods(mods);
        reportedPeptide.setReportedPeptideString(ReportedPeptideUtils.getReportedPeptideString(peptide.getPeptideSequence(), mods));
        reportedPeptide.setProteinMatches(pepEvidenceMap.get(peptide.getId()));

        return reportedPeptide;
    }

    private static Map<Integer, BigDecimal> getDynamicMods(PeptideType peptide) {
        Map<Integer, BigDecimal> mods = new HashMap<>();

        for(ModificationType mod : peptide.getModification()) {
            mods.put(mod.getLocation(), BigDecimal.valueOf(mod.getMonoisotopicMassDelta()));
        }

        return mods;
    }

    private static Map<String, Collection<String>> getPeptideEvidenceMap(MzIdentMLType mzIdentML) throws Exception {
        Map<String, Collection<String>> pepEvidenceMap = new HashMap<>();

        SequenceCollectionType sequenceCollection = getSequenceCollection(mzIdentML);
        for(PeptideEvidenceType peptideEvidence : sequenceCollection.getPeptideEvidence()) {
            String pepRef = peptideEvidence.getPeptideRef();
            String protRef = peptideEvidence.getDBSequenceRef();

            if(!pepEvidenceMap.containsKey(pepRef)) {
                pepEvidenceMap.put(pepRef, new HashSet<>());
            }

            pepEvidenceMap.get(pepRef).add(protRef);
        }

        return pepEvidenceMap;
    }

    private static SequenceCollectionType getSequenceCollection(MzIdentMLType mzIdentML) throws Exception {
        SequenceCollectionType sequenceCollection = mzIdentML.getSequenceCollection();
        if( sequenceCollection == null ) {
            throw new Exception("Did not find SequenceCollection in .mzid file.");
        }

        return sequenceCollection;
    }


    private static Map<String, MetamorpheusProtein> getProteins(MzIdentMLType mzIdentML) throws Exception {
        Map<String, MetamorpheusProtein> proteinMap = new HashMap<>();

        SequenceCollectionType sequenceCollection = getSequenceCollection(mzIdentML);

        for(DBSequenceType dbSequence : sequenceCollection.getDBSequence()) {
            String id = dbSequence.getId();
            String name = dbSequence.getName();
            String accession = dbSequence.getAccession();
            String sequence = dbSequence.getSeq();

            MetamorpheusProtein.Annotation anno = new MetamorpheusProtein.Annotation();
            anno.setDescription( name );
            anno.setName( accession );

            MetamorpheusProtein metaProtein = new MetamorpheusProtein(sequence);
            metaProtein.getAnnotations().add(anno);

            proteinMap.put(id, metaProtein);
        }

        return proteinMap;
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
