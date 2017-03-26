package com.example.prora.moduleexchange;

import java.io.File;

/**
 * Created by prora on 3/26/2017.
 */

public class Task extends Thread {
	private TaskInfo taskInfo;

	public Task(TaskInfo taskInfo) {
		this.taskInfo = taskInfo;
	}

	public TaskInfo getTaskInfo() {
		return taskInfo;
	}
}
