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

package org.yeastrc.limelight.xml.metamorpheus.annotation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.yeastrc.limelight.limelight_import.api.xml_dto.FilterDirectionType;
import org.yeastrc.limelight.limelight_import.api.xml_dto.FilterablePsmAnnotationType;


public class PSMAnnotationTypes {

	// metamorpheus scores
	public static final String ANNOTATION_TYPE_QVALUE = "q-value";
	public static final String ANNOTATION_TYPE_SCORE = "score";
	public static final String ANNOTATION_TYPE_MASS_DIFF = "mass diff.";
	public static final String ANNOTATION_TYPE_RANK = "rank";

	public static List<FilterablePsmAnnotationType> getFilterablePsmAnnotationTypes() {
		List<FilterablePsmAnnotationType> types = new ArrayList<FilterablePsmAnnotationType>();

		{
			FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
			type.setName( ANNOTATION_TYPE_QVALUE );
			type.setDescription( "Q-value" );
			type.setFilterDirection( FilterDirectionType.BELOW );
			type.setDefaultFilterValue(new BigDecimal("0.01"));

			types.add( type );
		}

		{
			FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
			type.setName( ANNOTATION_TYPE_SCORE );
			type.setDescription( "Metamorpheus score" );
			type.setFilterDirection( FilterDirectionType.ABOVE );

			types.add( type );
		}

		{
			FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
			type.setName( ANNOTATION_TYPE_MASS_DIFF );
			type.setDescription( "Observed m/z - expected m/z" );
			type.setFilterDirection( FilterDirectionType.BELOW );

			types.add( type );
		}

		{
			FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
			type.setName( ANNOTATION_TYPE_RANK );
			type.setDescription( "Rank of this PSM for this scan" );
			type.setFilterDirection( FilterDirectionType.BELOW );
			type.setDefaultFilterValue(new BigDecimal("1").setScale(0, RoundingMode.HALF_UP));

			types.add( type );
		}

		return types;
	}
	
	
}
