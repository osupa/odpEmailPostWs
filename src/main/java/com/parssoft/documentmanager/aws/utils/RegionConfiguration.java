package com.parssoft.documentmanager.aws.utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.regions.Region;
//import com.amazonaws.services.s3.model.Region;
//import com.amazonaws.services.s3.model.Region;
import com.parssoft.documentmanager.utils.EmailPostUtils;
import static com.parssoft.documentmanager.utils.EmailPostUtils.REGION_EAST_1_URL_DOMAIN_ADDRESS;
import static com.parssoft.documentmanager.utils.EmailPostUtils.REGION_WEST_1_URL_DOMAIN_ADDRESS;
import static com.parssoft.documentmanager.utils.EmailPostUtils.REGION_WEST_2_URL_DOMAIN_ADDRESS;
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
	private static String regionDomainName = null;

	public static final Region getRegion() {
		if (region == null) {
			String regionName = EmailPostUtils.regionName;

			if (regionName.equals(Regions.US_EAST_1.name())) {
				region = Region.getRegion(Regions.US_EAST_1);
				regionDomainName = REGION_EAST_1_URL_DOMAIN_ADDRESS;
			} else if (regionName.equals(Regions.US_WEST_1.name())) {
				region = Region.getRegion(Regions.US_WEST_1);
				regionDomainName = REGION_WEST_1_URL_DOMAIN_ADDRESS;
			} else if (regionName.equals(Regions.US_WEST_2.name())) {
				region = Region.getRegion(Regions.US_WEST_2);
				regionDomainName = REGION_WEST_2_URL_DOMAIN_ADDRESS;
			}
		}
		return region;
	}

	public static final String getRegionDomainAddress() {
		if (region == null) {
			getRegion();
		}
		return regionDomainName;
	}
}
