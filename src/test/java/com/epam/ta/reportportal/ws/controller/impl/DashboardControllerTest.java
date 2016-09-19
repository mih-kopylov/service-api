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
package com.epam.ta.reportportal.ws.controller.impl;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.ta.reportportal.ws.model.dashboard.DashboardResource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.epam.ta.reportportal.auth.AuthConstants;
import com.epam.ta.reportportal.ws.BaseMvcTest;
import com.epam.ta.reportportal.ws.model.dashboard.CreateDashboardRQ;
import com.epam.ta.reportportal.ws.model.dashboard.UpdateDashboardRQ;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

/**
 * @author Dzmitry_Kavalets
 *
 */
public class DashboardControllerTest extends BaseMvcTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void createDashboardPositive() throws Exception {
		CreateDashboardRQ createDashboardRQ = new CreateDashboardRQ();
		createDashboardRQ.setName("dashboard");
		this.mvcMock
				.perform(post(PROJECT_BASE_URL + "/dashboard").principal(authentication())
						.content(objectMapper.writeValueAsBytes(createDashboardRQ)).contentType(APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void getAllDashboardsPositive() throws Exception {
		this.mvcMock.perform(get(PROJECT_BASE_URL + "/dashboard").principal(authentication())).andExpect(status().is(200));
	}

	@Test
	public void getDashboardPositive() throws Exception {
		this.mvcMock.perform(get(PROJECT_BASE_URL + "/dashboard/520e1f3818127ca383464342").principal(authentication()))
				.andExpect(status().is(200));
	}

	@Test
	public void updateDashboardPositive() throws Exception {
		final UpdateDashboardRQ rq = new UpdateDashboardRQ();
		rq.setName("name");
		rq.setAddWidget(
				new DashboardResource.WidgetObjectModel("613e1f3818127ca356339f38", new ArrayList<Integer>(), new ArrayList<Integer>()));
		rq.setShare(true);
		this.mvcMock.perform(put(PROJECT_BASE_URL + "/dashboard/520e1f3818127ca383464342").principal(authentication())
				.content(objectMapper.writeValueAsBytes(rq)).contentType(APPLICATION_JSON)).andExpect(status().is(200));
	}

	@Test
	public void deleteDashboardPositive() throws Exception {
		this.mvcMock.perform(delete(PROJECT_BASE_URL + "/dashboard/520e1f3818127ca383464342").principal(authentication()))
				.andExpect(status().is(200));
	}

	@Test
	public void getSharedDashboardsNamesPositive() throws Exception {
		this.mvcMock.perform(get(PROJECT_BASE_URL + "/dashboard/shared").principal(authentication())).andExpect(status().is(200));
	}

	@Override
	protected Authentication authentication() {
		return AuthConstants.ADMINISTRATOR;
	}
}