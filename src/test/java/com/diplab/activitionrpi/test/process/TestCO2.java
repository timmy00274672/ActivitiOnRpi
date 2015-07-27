package com.diplab.activitionrpi.test.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;

public class TestCO2 {
	public static void main(String[] args) {
		ProcessEngineConfigurationImpl config = new StandaloneInMemProcessEngineConfiguration();
		// config.setJobExecutor(true);

		final ProcessEngine processEngine = config.buildProcessEngine();
		processEngine.getRepositoryService().createDeployment()
				.disableSchemaValidation().disableBpmnValidation()
				.addClasspathResource("bpmn/rpi_co2.bpmn").deploy();

		processEngine.getRuntimeService()
				.startProcessInstanceByKey("myProcess");

	}
}
