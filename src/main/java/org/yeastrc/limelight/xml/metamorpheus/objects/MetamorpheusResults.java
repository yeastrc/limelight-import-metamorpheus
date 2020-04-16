package org.yeastrc.limelight.xml.metamorpheus.objects;

import java.util.Collection;
import java.util.Map;

public class MetamorpheusResults {

	private Map<MetamorpheusReportedPeptide, Collection<MetamorpheusPSM>> peptidePSMMap;
	private Map<String, MetamorpheusProtein> proteinsMap;
	private Collection<String> staticMods;
	private String version;

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

	public Map<String, MetamorpheusProtein> getProteinsMap() {
		return proteinsMap;
	}

	public void setProteinsMap(Map<String, MetamorpheusProtein> proteinsMap) {
		this.proteinsMap = proteinsMap;
	}

	public Collection<String> getStaticMods() {
		return staticMods;
	}

	public void setStaticMods(Collection<String> staticMods) {
		this.staticMods = staticMods;
	}
}
