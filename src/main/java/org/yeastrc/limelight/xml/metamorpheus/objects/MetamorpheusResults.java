package org.yeastrc.limelight.xml.metamorpheus.objects;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public class MetamorpheusResults {

	private Map<MetamorpheusReportedPeptide, Collection<MetamorpheusPSM>> peptidePSMMap;
	private Map<String, MetamorpheusProtein> proteinsMap;
	private Map<String, BigDecimal> staticMods;
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

	public Map<String, BigDecimal> getStaticMods() {
		return staticMods;
	}

	public void setStaticMods(Map<String, BigDecimal> staticMods) {
		this.staticMods = staticMods;
	}
}
