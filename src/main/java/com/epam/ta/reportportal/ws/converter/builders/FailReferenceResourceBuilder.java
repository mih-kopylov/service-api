/*
 * Copyright 2016 EPAM Systems
 * 
 * 
 * This file is part of EPAM Report Portal.
 * https://github.com/epam/ReportPortal
 * 
 * Report Portal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Report Portal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Report Portal.  If not, see <http://www.gnu.org/licenses/>.
 */ 
 
/**
 * 
 */
package com.epam.ta.reportportal.ws.converter.builders;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.epam.ta.reportportal.database.entity.item.FailReferenceResource;

/**
 * Builder for {@link FailReferenceResource} persistence layer object
 * 
 * @author Andrei_Ramanchuk
 *
 */
@Service
@Scope("prototype")
public class FailReferenceResourceBuilder extends Builder<FailReferenceResource>{

	@Override
	protected FailReferenceResource initObject() {
		return new FailReferenceResource();
	}
	
	public FailReferenceResourceBuilder addTestItemRef(String testItemRef) {
		getObject().setTestItemRef(testItemRef);
		return this;
	}
	
	public FailReferenceResourceBuilder addLaunchRef(String launchRef) {
		getObject().setLaunchRef(launchRef);
		return this;
	}
}