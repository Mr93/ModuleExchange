package com.example.prora.moduleexchange;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by prora on 3/26/2017.
 */

public class TaskManager extends Thread {
	BlockingQueue<Task> downloadingTask, pendingTask, errorTask;
	private static TaskManager instance;
	private static int MAX_TASK = 8;

	private TaskManager() {
		setUpQueues();
	}

	private void setUpQueues() {
		getDownloadingQueue();
		getPendingQueue();
		getErrorQueue();
		updateFromPendingToDownloading();
	}

	//get queue from DB
	private void getDownloadingQueue() {
		downloadingTask = new LinkedBlockingQueue<>();
	}

	private void getPendingQueue() {
		pendingTask = new LinkedBlockingQueue<>();
	}

	private void getErrorQueue() {
		errorTask = new LinkedBlockingQueue<>();
	}

	private void updateFromPendingToDownloading() {
		int offset = MAX_TASK - downloadingTask.size();
		for (int i = 0; i < offset; i++) {
			downloadingTask.offer(pendingTask.poll());
		}
	}

	public TaskManager getInstance() {
		if (instance == null) {
			instance = new TaskManager();
		}
		return instance;
	}

	@Override
	public void run() {

	}

	public void addTask(TaskInfo taskInfo) {
		Task task = new Task(taskInfo);
		if (checkContain(taskInfo, errorTask)) {
			errorTask.remove(task);
			if (downloadingTask.size() >= MAX_TASK) {
				pendingTask.add(task);
			} else {
				downloadingTask.add(task);
			}
		} else if (!checkContain(taskInfo, downloadingTask) && !checkContain(taskInfo, pendingTask)) {
			if (downloadingTask.size() >= MAX_TASK) {
				pendingTask.add(task);
			} else {
				downloadingTask.add(task);
			}
		}
	}

	public boolean checkContain(TaskInfo taskInfo, Queue<Task> tasks) {
		for (Task task : tasks) {
			if (task.getTaskInfo().isDownloadingTask == taskInfo.isDownloadingTask &&
					task.getTaskInfo().name.equalsIgnoreCase(taskInfo.name) &&
					task.getTaskInfo().url.equalsIgnoreCase(taskInfo.url)) {
				return true;
			}
		}
		return false;
	}
}
