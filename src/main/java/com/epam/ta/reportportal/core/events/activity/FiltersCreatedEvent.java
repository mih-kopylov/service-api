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

import java.time.LocalDateTime;

/**
 * @author pavel_bortnik
 */
public class FiltersCreatedEvent implements ActivityEvent {

	private UserFilter userFilter;

	public FiltersCreatedEvent(UserFilter userFilter) {
		this.userFilter = userFilter;
	}

	@Override
	public Activity toActivity() {
		;
		Activity activity = new Activity();
		activity.setCreatedAt(LocalDateTime.now());
		activity.setEntity(Activity.Entity.FILTER);
		activity.setAction(ActivityAction.CREATE_FILTER.getValue());
		activity.setProjectId(userFilter.getProject().getId());
		return activity;
	}
}
