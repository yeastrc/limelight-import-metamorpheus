package org.yeastrc.limelight.xml.metamorpheus.builder;

import java.io.File;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.yeastrc.limelight.limelight_import.api.xml_dto.LimelightInput;
import org.yeastrc.limelight.limelight_import.api.xml_dto.MatchedProtein;
import org.yeastrc.limelight.limelight_import.api.xml_dto.MatchedProteinLabel;
import org.yeastrc.limelight.limelight_import.api.xml_dto.MatchedProteins;
import org.yeastrc.limelight.xml.metamorpheus.objects.MetamorpheusProtein;
import org.yeastrc.limelight.xml.metamorpheus.objects.MetamorpheusReportedPeptide;
import org.yeastrc.proteomics.fasta.FASTAEntry;
import org.yeastrc.proteomics.fasta.FASTAFileParser;
import org.yeastrc.proteomics.fasta.FASTAFileParserFactory;
import org.yeastrc.proteomics.fasta.FASTAHeader;

/**
 * Build the MatchedProteins section of the limelight XML docs. This is done by finding all proteins in the FASTA
 * file that contains any of the peptide sequences found in the experiment. 
 * 
 * This is generalized enough to be usable by any pipeline
 * 
 * @author mriffle
 *
 */
public class MatchedProteinsBuilder {

	public static MatchedProteinsBuilder getInstance() { return new MatchedProteinsBuilder(); }


	public void buildMatchedProteins(LimelightInput limelightInputRoot, Map<String, MetamorpheusProtein> proteinSequenceProteinMap) throws Exception {
		
		System.err.print( " Matching peptides to proteins..." );

		MatchedProteins xmlMatchedProteins = new MatchedProteins();
		limelightInputRoot.setMatchedProteins( xmlMatchedProteins );

		for( String sequence : proteinSequenceProteinMap.keySet() ) {

			MetamorpheusProtein protein = proteinSequenceProteinMap.get(sequence);

			MatchedProtein xmlProtein = new MatchedProtein();
			xmlMatchedProteins.getMatchedProtein().add( xmlProtein );

			xmlProtein.setSequence( protein.getSequence() );
			xmlProtein.setId( BigInteger.valueOf(protein.getUniqueId()));

			for( MetamorpheusProtein.Annotation anno : protein.getAnnotations() ) {
				MatchedProteinLabel xmlMatchedProteinLabel = new MatchedProteinLabel();
				xmlProtein.getMatchedProteinLabel().add( xmlMatchedProteinLabel );

				xmlMatchedProteinLabel.setName( anno.getName() );

				if( anno.getDescription() != null )
					xmlMatchedProteinLabel.setDescription( anno.getDescription() );
			}
		}
	}
	
}
