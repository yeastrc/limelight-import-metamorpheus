package org.yeastrc.limelight.xml.metamorpheus.objects;

import java.io.File;
import java.util.List;

public class ConversionParameters {

    public ConversionParameters(File mzidFile, String outputFilePath, ConversionProgramInfo conversionProgramInfo, List<File> tomlFiles) {
        this.mzidFile = mzidFile;
        this.outputFilePath = outputFilePath;
        this.conversionProgramInfo = conversionProgramInfo;
        this.tomlFiles = tomlFiles;
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

    public List<File> getTomlFiles() {
        return tomlFiles;
    }

    private File mzidFile;
    private String outputFilePath;
    private ConversionProgramInfo conversionProgramInfo;
    private List<File> tomlFiles;

}
