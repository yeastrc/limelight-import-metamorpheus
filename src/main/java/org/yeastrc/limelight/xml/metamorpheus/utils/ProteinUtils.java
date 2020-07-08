package org.yeastrc.limelight.xml.metamorpheus.utils;

public class ProteinUtils {

    public static boolean isIdDecoy(String proteinId) {
        return proteinId.startsWith("DBS_DECOY_");
    }

    public static boolean isNameDecoy(String proteinName) {
        return proteinName.startsWith("DECOY_");
    }

}
