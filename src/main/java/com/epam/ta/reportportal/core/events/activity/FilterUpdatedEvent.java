/*
 * Copyright 2017 EPAM Systems
 *
 *
 * This file is part of EPAM Report Portal.
 * https://github.com/reportportal/service-api
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

package com.epam.ta.reportportal.core.events.activity;

import com.epam.ta.reportportal.core.events.ActivityEvent;
import com.epam.ta.reportportal.entity.Activity;
import com.epam.ta.reportportal.entity.filter.UserFilter;

/**
 * @author Pavel Bortnik
 */
public class FilterUpdatedEvent extends AroundEvent<UserFilter> implements ActivityEvent {
	public FilterUpdatedEvent(UserFilter before, UserFilter after) {
		super(before, after);
	}

	@Override
	public Activity toActivity() {
		Activity activity = new Activity();
		return null;
	}

	public static class FilterActivityDetails {
	}

	//	private final String updatedBy;
	//
	//	public FilterUpdatedEvent(UserFilter before, UserFilter after, String updatedBy) {
	//		super(before, after);
	//		this.updatedBy = updatedBy;
	//	}
	//
	//	public String getUpdatedBy() {
	//		return updatedBy;
	//	}
}
