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

package com.epam.ta.reportportal.core.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.ta.reportportal.database.dao.LaunchRepository;
import com.epam.ta.reportportal.database.dao.ProjectSettingsRepository;
import com.epam.ta.reportportal.database.dao.TestItemRepository;
import com.epam.ta.reportportal.database.entity.Launch;
import com.epam.ta.reportportal.database.entity.ProjectSettings;
import com.epam.ta.reportportal.database.entity.item.TestItem;

/**
 * Default implementation of {@link StatisticsFacade}
 * 
 * @author Dzianis Shlychkou
 * @author Andrei_Ramanchuk
 */
@Service
public class StatisticsFacadeImpl implements StatisticsFacade {

	@Autowired
	private TestItemRepository testItemRepository;

	@Autowired
	private LaunchRepository launchRepository;

	@Autowired
	private ProjectSettingsRepository settingsRepository;

	@Override
	public TestItem updateExecutionStatistics(final TestItem testItem) {
		testItemRepository.updateExecutionStatistics(testItem);
		launchRepository.updateExecutionStatistics(testItem);
		return testItemRepository.findOne(testItem.getId());
	}

	@Override
	public TestItem updateIssueStatistics(final TestItem testItem) {
		Launch launch = launchRepository.findOne(testItem.getLaunchRef());
		ProjectSettings prjSettings = settingsRepository.findOne(launch.getProjectRef());
		testItemRepository.updateIssueStatistics(testItem, prjSettings);
		launchRepository.updateIssueStatistics(testItem, prjSettings);
		return testItemRepository.findOne(testItem.getId());
	}

	@Override
	public TestItem resetIssueStatistics(final TestItem testItem) {
		Launch launch = launchRepository.findOne(testItem.getLaunchRef());
		ProjectSettings prjSettings = settingsRepository.findOne(launch.getProjectRef());
		testItemRepository.resetIssueStatistics(testItem, prjSettings);
		launchRepository.resetIssueStatistics(testItem, prjSettings);
		return testItemRepository.findOne(testItem.getId());
	}

	@Override
	public TestItem resetExecutionStatistics(TestItem testItem) {
		testItemRepository.resetExecutionStatistics(testItem);
		launchRepository.resetExecutionStatistics(testItem);
		return testItemRepository.findOne(testItem.getId());
	}

	@Override
	public TestItem deleteIssueStatistics(TestItem testItem) {
		testItemRepository.deleteIssueStatistics(testItem);
		launchRepository.deleteIssueStatistics(testItem);
		return testItemRepository.findOne(testItem.getId());
	}

	@Override
	public TestItem deleteExecutionStatistics(TestItem testItem) {
		testItemRepository.deleteExecutionStatistics(testItem);
		launchRepository.deleteExecutionStatistics(testItem);
		return testItemRepository.findOne(testItem.getId());
	}

	@Override
	public void updateParentStatusFromStatistics(TestItem item) {
		item.setStatus(StatisticsHelper.getStatusFromStatistics(item.getStatistics()));
		testItemRepository.save(item);
		if (null != item.getParent()) {
			TestItem parent = testItemRepository.findOne(item.getParent());
			updateParentStatusFromStatistics(parent);
		}
	}

	@Override
	public void updateLaunchFromStatistics(Launch launch) {
		launch.setStatus(StatisticsHelper.getStatusFromStatistics(launch.getStatistics()));
		launchRepository.save(launch);
	}
}