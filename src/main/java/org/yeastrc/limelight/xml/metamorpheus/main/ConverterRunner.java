/*
 * Original author: Michael Riffle <mriffle .at. uw.edu>
 *                  
 * Copyright 2018 University of Washington - Seattle, WA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.yeastrc.limelight.xml.metamorpheus.main;

import org.yeastrc.limelight.xml.metamorpheus.builder.XMLBuilder;
import org.yeastrc.limelight.xml.metamorpheus.objects.*;
import org.yeastrc.limelight.xml.metamorpheus.reader.*;

public class ConverterRunner {

	// conveniently get a new instance of this class
	public static ConverterRunner createInstance() { return new ConverterRunner(); }
	
	
	public void convertToLimelightXML(ConversionParameters conversionParameters ) throws Throwable {

		System.err.println( "\nLoading MetaMorpheus results into memory...");
		MetamorpheusResults results = MetamorpheusResultsReader.getResults( conversionParameters.getMzidFile() );
		//System.err.println( " Found " + results.getPeptidePSMMap().keySet().size() + " distinct peptides..." );

//		System.err.print( "\nWriting out XML..." );
//		(new XMLBuilder()).buildAndSaveXML(conversionParameters, results, tdAnalysis);
//		System.err.println( " Done." );

	}
}
