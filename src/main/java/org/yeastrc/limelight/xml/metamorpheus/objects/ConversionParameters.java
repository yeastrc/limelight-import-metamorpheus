package org.yeastrc.limelight.xml.metamorpheus.objects;

import java.io.File;

public class ConversionParameters {

    public ConversionParameters(File mzidFile, String outputFilePath, ConversionProgramInfo conversionProgramInfo) {
        this.mzidFile = mzidFile;
        this.outputFilePath = outputFilePath;
        this.conversionProgramInfo = conversionProgramInfo;
    }

    public File getMzidFile() {
        return mzidFile;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public ConversionProgramInfo getConversionProgramInfo() {
        return conversionProgramInfo;
    }

    private File mzidFile;
    private String outputFilePath;
    private ConversionProgramInfo conversionProgramInfo;

}
