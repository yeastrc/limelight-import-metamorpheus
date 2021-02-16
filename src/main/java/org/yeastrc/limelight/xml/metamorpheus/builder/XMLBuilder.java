package org.yeastrc.limelight.xml.metamorpheus.builder;

import org.yeastrc.limelight.limelight_import.api.xml_dto.*;
import org.yeastrc.limelight.limelight_import.api.xml_dto.SearchProgram.PsmAnnotationTypes;
import org.yeastrc.limelight.limelight_import.create_import_file_from_java_objects.main.CreateImportFileFromJavaObjectsMain;
import org.yeastrc.limelight.xml.metamorpheus.annotation.PSMAnnotationTypeSortOrder;
import org.yeastrc.limelight.xml.metamorpheus.annotation.PSMAnnotationTypes;
import org.yeastrc.limelight.xml.metamorpheus.annotation.PSMDefaultVisibleAnnotationTypes;
import org.yeastrc.limelight.xml.metamorpheus.constants.Constants;
import org.yeastrc.limelight.xml.metamorpheus.objects.*;
import org.yeastrc.limelight.xml.metamorpheus.utils.ReportedPeptideUtils;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.Map;

public class XMLBuilder {

	public void buildAndSaveXML(ConversionParameters conversionParameters,
								MetamorpheusResults results)
    throws Exception {

		LimelightInput limelightInputRoot = new LimelightInput();

		limelightInputRoot.setFastaFilename( results.getSearchDatabase() );

		// add in the conversion program (this program) information
		ConversionProgramBuilder.createInstance().buildConversionProgramSection( limelightInputRoot, conversionParameters);

		SearchProgramInfo searchProgramInfo = new SearchProgramInfo();
		limelightInputRoot.setSearchProgramInfo( searchProgramInfo );

		SearchPrograms searchPrograms = new SearchPrograms();
		searchProgramInfo.setSearchPrograms( searchPrograms );

		{
			SearchProgram searchProgram = new SearchProgram();
			searchPrograms.getSearchProgram().add( searchProgram );

			searchProgram.setName( Constants.PROGRAM_NAME );
			searchProgram.setDisplayName( Constants.PROGRAM_NAME );
			searchProgram.setVersion(results.getVersion());


			//
			// Define the annotation types present in tide data
			//
			PsmAnnotationTypes psmAnnotationTypes = new PsmAnnotationTypes();
			searchProgram.setPsmAnnotationTypes( psmAnnotationTypes );

			FilterablePsmAnnotationTypes filterablePsmAnnotationTypes = new FilterablePsmAnnotationTypes();
			psmAnnotationTypes.setFilterablePsmAnnotationTypes( filterablePsmAnnotationTypes );

			for( FilterablePsmAnnotationType annoType : PSMAnnotationTypes.getFilterablePsmAnnotationTypes() ) {
				filterablePsmAnnotationTypes.getFilterablePsmAnnotationType().add( annoType );
			}
		}

		//
		// Define which annotation types are visible by default
		//
		DefaultVisibleAnnotations xmlDefaultVisibleAnnotations = new DefaultVisibleAnnotations();
		searchProgramInfo.setDefaultVisibleAnnotations( xmlDefaultVisibleAnnotations );

		VisiblePsmAnnotations xmlVisiblePsmAnnotations = new VisiblePsmAnnotations();
		xmlDefaultVisibleAnnotations.setVisiblePsmAnnotations( xmlVisiblePsmAnnotations );

		for( SearchAnnotation sa : PSMDefaultVisibleAnnotationTypes.getDefaultVisibleAnnotationTypes() ) {
			xmlVisiblePsmAnnotations.getSearchAnnotation().add( sa );
		}

		//
		// Define the default display order in proxl
		//
		AnnotationSortOrder xmlAnnotationSortOrder = new AnnotationSortOrder();
		searchProgramInfo.setAnnotationSortOrder( xmlAnnotationSortOrder );

		PsmAnnotationSortOrder xmlPsmAnnotationSortOrder = new PsmAnnotationSortOrder();
		xmlAnnotationSortOrder.setPsmAnnotationSortOrder( xmlPsmAnnotationSortOrder );

		for( SearchAnnotation xmlSearchAnnotation : PSMAnnotationTypeSortOrder.getPSMAnnotationTypeSortOrder() ) {
			xmlPsmAnnotationSortOrder.getSearchAnnotation().add( xmlSearchAnnotation );
		}

		//
		// Define the static mods
		//
		Map<String, BigDecimal> staticMods = results.getStaticMods();
		if(staticMods.size() > 0) {

			StaticModifications smods = new StaticModifications();
			limelightInputRoot.setStaticModifications( smods );

			for( String residue : staticMods.keySet() ) {

				StaticModification xmlSmod = new StaticModification();
				xmlSmod.setAminoAcid( residue );
				xmlSmod.setMassChange( staticMods.get(residue) );

				smods.getStaticModification().add( xmlSmod );
			}
		}

		//
		// Build MatchedProteins section and get map of protein names to MatchedProtein ids
		//
		MatchedProteinsBuilder.getInstance().buildMatchedProteins(
				limelightInputRoot,
				results.getProteinsSequenceProteinMap()
		);


		//
		// Define the peptide and PSM data
		//
		ReportedPeptides reportedPeptides = new ReportedPeptides();
		limelightInputRoot.setReportedPeptides( reportedPeptides );

		// iterate over each distinct reported peptide
		for( MetamorpheusReportedPeptide metamorpheusReportedPeptide : results.getPeptidePSMMap().keySet() ) {

			// skip this if it only contains decoys
			if(!peptideHasProteins(metamorpheusReportedPeptide, results)) {
				continue;
			}

			String reportedPeptideString = metamorpheusReportedPeptide.getReportedPeptideString();

			ReportedPeptide xmlReportedPeptide = new ReportedPeptide();
			reportedPeptides.getReportedPeptide().add( xmlReportedPeptide );

			xmlReportedPeptide.setReportedPeptideString( reportedPeptideString );
			xmlReportedPeptide.setSequence( metamorpheusReportedPeptide.getNakedPeptide() );

			MatchedProteinsForPeptide xProteinsForPeptide = new MatchedProteinsForPeptide();
			xmlReportedPeptide.setMatchedProteinsForPeptide( xProteinsForPeptide );

			// add in protein inference info
			int proteinCount = 0;
			for( String proteinId : metamorpheusReportedPeptide.getProteinMatches() ) {

				if(results.getProteinsIdSequenceMap().containsKey( proteinId ) ) {
					proteinCount++;

					MetamorpheusProtein matchedProtein = results.getProteinsSequenceProteinMap().get(results.getProteinsIdSequenceMap().get(proteinId));
					int matchedProteinId = matchedProtein.getUniqueId();

					MatchedProteinForPeptide xProteinForPeptide = new MatchedProteinForPeptide();
					xProteinsForPeptide.getMatchedProteinForPeptide().add(xProteinForPeptide);

					xProteinForPeptide.setId(BigInteger.valueOf(matchedProteinId));
				}
			}

			if( proteinCount == 0) {
				throw new Exception("Could not find a protein for peptide: " + metamorpheusReportedPeptide);
			}

			// add in the mods for this peptide
			if( metamorpheusReportedPeptide.getMods() != null && metamorpheusReportedPeptide.getMods().keySet().size() > 0 ) {

				PeptideModifications xmlModifications = new PeptideModifications();
				xmlReportedPeptide.setPeptideModifications( xmlModifications );

				for( int position : metamorpheusReportedPeptide.getMods().keySet() ) {

					PeptideModification xmlModification = new PeptideModification();
					xmlModifications.getPeptideModification().add( xmlModification );

					xmlModification.setMass( metamorpheusReportedPeptide.getMods().get( position ) );

					if( position == 0) {

						xmlModification.setIsNTerminal( true );

					} else if( position == metamorpheusReportedPeptide.getNakedPeptide().length() + 1 ) {

						xmlModification.setIsCTerminal( true );

					} else {
						xmlModification.setPosition( BigInteger.valueOf( position ) );
					}
				}
			}


			// add in the PSMs and annotations
			Psms xmlPsms = new Psms();
			xmlReportedPeptide.setPsms( xmlPsms );

			// iterate over all PSMs for this reported peptide

			for( MetamorpheusPSM psm : results.getPeptidePSMMap().get(metamorpheusReportedPeptide) ) {

				Psm xmlPsm = new Psm();
				xmlPsms.getPsm().add( xmlPsm );

				xmlPsm.setScanNumber( new BigInteger( String.valueOf( psm.getScanNumber() ) ) );
				xmlPsm.setPrecursorCharge( new BigInteger( String.valueOf( psm.getCharge() ) ) );
				xmlPsm.setPrecursorMZ(psm.getObservedMoverZ());
				xmlPsm.setPrecursorRetentionTime(psm.getRetentionTime());

				// add in the filterable PSM annotations (e.g., score)
				FilterablePsmAnnotations xmlFilterablePsmAnnotations = new FilterablePsmAnnotations();
				xmlPsm.setFilterablePsmAnnotations( xmlFilterablePsmAnnotations );

				if(conversionParameters.isOpenMod() && psm.getMassDiff() != null) {
					PsmOpenModification xmlPsmOpenModification = new PsmOpenModification();
					xmlPsm.setPsmOpenModification(xmlPsmOpenModification);
					xmlPsmOpenModification.setMass(psm.getMassDiff());
				}

				// handle psm scores

				{
					FilterablePsmAnnotation xmlFilterablePsmAnnotation = new FilterablePsmAnnotation();
					xmlFilterablePsmAnnotations.getFilterablePsmAnnotation().add( xmlFilterablePsmAnnotation );

					xmlFilterablePsmAnnotation.setAnnotationName( PSMAnnotationTypes.ANNOTATION_TYPE_SCORE );
					xmlFilterablePsmAnnotation.setSearchProgram( Constants.PROGRAM_NAME );
					xmlFilterablePsmAnnotation.setValue( psm.getScore().setScale(4, RoundingMode.HALF_UP));
				}

				{
					FilterablePsmAnnotation xmlFilterablePsmAnnotation = new FilterablePsmAnnotation();
					xmlFilterablePsmAnnotations.getFilterablePsmAnnotation().add( xmlFilterablePsmAnnotation );

					xmlFilterablePsmAnnotation.setAnnotationName( PSMAnnotationTypes.ANNOTATION_TYPE_QVALUE );
					xmlFilterablePsmAnnotation.setSearchProgram( Constants.PROGRAM_NAME );
					xmlFilterablePsmAnnotation.setValue( psm.getqValue().round(new MathContext(4, RoundingMode.HALF_UP)));
				}

				{
					FilterablePsmAnnotation xmlFilterablePsmAnnotation = new FilterablePsmAnnotation();
					xmlFilterablePsmAnnotations.getFilterablePsmAnnotation().add( xmlFilterablePsmAnnotation );

					xmlFilterablePsmAnnotation.setAnnotationName( PSMAnnotationTypes.ANNOTATION_TYPE_MASS_DIFF );
					xmlFilterablePsmAnnotation.setSearchProgram( Constants.PROGRAM_NAME );
					xmlFilterablePsmAnnotation.setValue( psm.getMassDiff());
				}

				{
					FilterablePsmAnnotation xmlFilterablePsmAnnotation = new FilterablePsmAnnotation();
					xmlFilterablePsmAnnotations.getFilterablePsmAnnotation().add( xmlFilterablePsmAnnotation );

					xmlFilterablePsmAnnotation.setAnnotationName( PSMAnnotationTypes.ANNOTATION_TYPE_RANK );
					xmlFilterablePsmAnnotation.setSearchProgram( Constants.PROGRAM_NAME );
					xmlFilterablePsmAnnotation.setValue( psm.getRank());
				}

			}// end iterating over psms for a reported peptide

		}//end iterating over reported peptides


		if(conversionParameters.getTomlFiles() != null && conversionParameters.getTomlFiles().size() > 0) {
			// add in the config files
			ConfigurationFiles xmlConfigurationFiles = new ConfigurationFiles();
			limelightInputRoot.setConfigurationFiles( xmlConfigurationFiles );

			for(File tomlFile : conversionParameters.getTomlFiles() ) {
				ConfigurationFile xmlConfigurationFile = new ConfigurationFile();
				xmlConfigurationFiles.getConfigurationFile().add(xmlConfigurationFile);

				xmlConfigurationFile.setSearchProgram(Constants.PROGRAM_NAME);
				xmlConfigurationFile.setFileName(tomlFile.getName());
				xmlConfigurationFile.setFileContent(Files.readAllBytes(FileSystems.getDefault().getPath(tomlFile.getAbsolutePath())));
			}
		}

		//make the xml file
		CreateImportFileFromJavaObjectsMain.getInstance().createImportFileFromJavaObjectsMain( new File(conversionParameters.getOutputFilePath() ), limelightInputRoot);

	}

	private boolean peptideHasProteins(MetamorpheusReportedPeptide metamorpheusReportedPeptide, MetamorpheusResults results) {

		for( String proteinId : metamorpheusReportedPeptide.getProteinMatches() ) {
			if(results.getProteinsIdSequenceMap().containsKey( proteinId ) ) {
				return true;
			}
		}

		return false;
	}
	
}
