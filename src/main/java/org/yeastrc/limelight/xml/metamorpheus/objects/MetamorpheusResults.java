package org.yeastrc.limelight.xml.metamorpheus.objects;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public class MetamorpheusResults {

	private Map<MetamorpheusReportedPeptide, Collection<MetamorpheusPSM>> peptidePSMMap;

	private Map<String, String> proteinsIdSequenceMap;
	private Map<String, MetamorpheusProtein> proteinsSequenceProteinMap;

	private Map<String, BigDecimal> staticMods;
	private String version;
	private String searchDatabase;

	public String getSearchDatabase() {
		return searchDatabase;
	}

	public void setSearchDatabase(String searchDatabase) {
		this.searchDatabase = searchDatabase;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<MetamorpheusReportedPeptide, Collection<MetamorpheusPSM>> getPeptidePSMMap() {
		return peptidePSMMap;
	}

	public void setPeptidePSMMap(Map<MetamorpheusReportedPeptide, Collection<MetamorpheusPSM>> peptidePSMMap) {
		this.peptidePSMMap = peptidePSMMap;
	}

	public Map<String, String> getProteinsIdSequenceMap() {
		return proteinsIdSequenceMap;
	}

	public void setProteinsIdSequenceMap(Map<String, String> proteinsIdSequenceMap) {
		this.proteinsIdSequenceMap = proteinsIdSequenceMap;
	}

	public Map<String, MetamorpheusProtein> getProteinsSequenceProteinMap() {
		return proteinsSequenceProteinMap;
	}

	public void setProteinsSequenceProteinMap(Map<String, MetamorpheusProtein> proteinsSequenceProteinMap) {
		this.proteinsSequenceProteinMap = proteinsSequenceProteinMap;
	}

	public Map<String, BigDecimal> getStaticMods() {
		return staticMods;
	}

	public void setStaticMods(Map<String, BigDecimal> staticMods) {
		this.staticMods = staticMods;
	}
}
