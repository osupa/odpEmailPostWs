package com.parssoft.documentmanager.aws.utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.regions.Region;
//import com.amazonaws.services.s3.model.Region;
//import com.amazonaws.services.s3.model.Region;
import com.parssoft.documentmanager.utils.EmailPostUtils;

/**
 * This is our generic AWS region handling class.
 * 
 * Created on Jul 18, 2014, 9:14:52 PM
 *
 * @author Ade
 *
 * Copyright(c) 2014 ParsSoft.  All Rights Reserved.
 * This software is the proprietary information of ParsSoft.
 *
 */

public final class RegionConfiguration {

	private static Region region = null;
	
	public static final Region getRegion() {
		if (region == null) {
			String regionName = EmailPostUtils.regionName;

			if (regionName.equals(Regions.US_EAST_1.name())) {
				region = Region.getRegion(Regions.US_EAST_1);
			} else if (regionName.equals(Regions.US_WEST_1.name())) {
				region = Region.getRegion(Regions.US_WEST_1);
			} else if (regionName.equals(Regions.US_WEST_2.name())) {
				region = Region.getRegion(Regions.US_WEST_2);
			}
		}
		return region;
	}
}
