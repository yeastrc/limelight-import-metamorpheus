MetaMorpheus to limelight XML Converter
=======================================

Use this program to convert the results of a MetaMorpheus analysis to
limelight XML suitable for import into the limelight web application.

How To Run
-------------
1. Download the [latest release](https://github.com/yeastrc/limelight-import-metamorpheus/releases).
2. Run the program ``java -jar metamorpheus2LimelightXML.jar`` with no arguments to see the possible parameters. Requires Java 8.

Notes
------------
This program uses the mzIdentML (.mzID) file output by MetaMorpheus. Please be sure that output option is not disabled when running MetaMorpheus.
This file is typically found in the "Individual File Results" directory in the directory corresponding to the final task of your MetaMorpheus search.
For example: ```C:\my_searches\yeastIsGreat\Task2-SearchTask\Individual File Results```

Command Line Instructions
-------------------------

```
java -jar metamorpheus2LimelightXML.jar [-hvV] -m=<mzidFile> -o=<outFile>
                                        [-t=<tomlFiles>]...

Description:

Convert the results of a MetaMorpheus analysis to a Limelight XML file suitable
for import into Limelight.

More info at: https://github.com/yeastrc/limelight-import-metamorpheus

Options:
  -m, --mzid=<mzidFile>      Full path to the location of the mzIdentML file (.mzid).
  -o, --out-file=<outFile>   Full path to use for the Limelight XML output file
                               (including file name).
  -t, --toml=<tomlFiles>     [Optional] Specify one or more toml config files to
                               include in the limelight XML file. Specify a -t for
                               each toml.
  -v, --verbose              If this parameter is present, error messages will
                               include a full stacktrace. Helpful for debugging.
  -h, --help                 Show this help message and exit.
  -V, --version              Print version information and exit.
```
