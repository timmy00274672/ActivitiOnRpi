package com.diplab.activitionrpi.test.signal;

import java.util.HashMap;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.cmd.SignalEventReceivedCmd;
import org.activiti.engine.impl.context.Context;

public class ThrowSignalJavaDelegate implements JavaDelegate {
	private static int count = 0;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		HashMap<String, Object> processVariables = new HashMap<String, Object>();
		processVariables.put("args", String.valueOf(count++));

		new SignalEventReceivedCmd("test_signal", null, processVariables, null)
				.execute(Context.getCommandContext());

	}
}
