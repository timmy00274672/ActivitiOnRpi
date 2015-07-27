package com.diplab.activitionrpi.test.signal;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;

public class TestSignalStartEvent {
	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl config = new StandaloneInMemProcessEngineConfiguration();
		config.setJobExecutorActivate(true);

		final ProcessEngine processEngine = config.buildProcessEngine();
		processEngine.getRepositoryService().createDeployment()
				.disableSchemaValidation().disableBpmnValidation()
				.addClasspathResource("bpmn/signalStartEvent.bpmn")
				.addClasspathResource("bpmn/throwTestSignal.bpmn").deploy();

		 RuntimeService runtimeService = processEngine.getRuntimeService();
		 runtimeService.startProcessInstanceByKey("throwTestSignal");
		 
	}
}
