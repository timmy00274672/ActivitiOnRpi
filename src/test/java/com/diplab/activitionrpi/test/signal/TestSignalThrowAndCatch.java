package com.diplab.activitionrpi.test.signal;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;

public class TestSignalThrowAndCatch implements JavaDelegate {
	public static void main(String[] args) throws InterruptedException {
		ProcessEngineConfigurationImpl config = new StandaloneInMemProcessEngineConfiguration();
		config.setJobExecutorActivate(true);

		final ProcessEngine processEngine = config.buildProcessEngine();
		processEngine.getRepositoryService().createDeployment()
				.disableSchemaValidation().disableBpmnValidation()
				.addClasspathResource("bpmn/signalReceive.bpmn")
				.addClasspathResource("bpmn/throwTestSignal.bpmn").deploy();

		RuntimeService runtimeService = processEngine.getRuntimeService();
		runtimeService.startProcessInstanceByKey("signalReceive");
		runtimeService.startProcessInstanceByKey("throwTestSignal");

	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(execution.getId() + execution.getVariables());
		// execution.getVariables().entrySet().stream()
		// .forEach(e -> System.out.println(e));
	}
}
