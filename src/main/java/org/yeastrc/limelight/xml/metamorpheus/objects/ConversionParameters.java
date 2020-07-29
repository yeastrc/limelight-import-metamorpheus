package org.yeastrc.limelight.xml.metamorpheus.objects;

import java.io.File;
import java.util.List;

public class ConversionParameters {
    @Override
    public String toString() {
        return "ConversionParameters{" +
                "mzidFile=" + mzidFile +
                ", outputFilePath='" + outputFilePath + '\'' +
                ", conversionProgramInfo=" + conversionProgramInfo +
                ", tomlFiles=" + tomlFiles +
                ", isOpenMod=" + isOpenMod +
                '}';
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

    public ConversionParameters(File mzidFile, String outputFilePath, ConversionProgramInfo conversionProgramInfo, List<File> tomlFiles, boolean isOpenMod) {
        this.mzidFile = mzidFile;
        this.outputFilePath = outputFilePath;
        this.conversionProgramInfo = conversionProgramInfo;
        this.tomlFiles = tomlFiles;
        this.isOpenMod = isOpenMod;
    }

    public boolean isOpenMod() {
        return isOpenMod;
    }

    private File mzidFile;
    private String outputFilePath;
    private ConversionProgramInfo conversionProgramInfo;
    private List<File> tomlFiles;
    boolean isOpenMod;

}
