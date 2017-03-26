package com.example.prora.moduleexchange;

import android.util.Log;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by prora on 3/26/2017.
 */

public class Producer implements Runnable {

	private static final String TAG = Producer.class.getSimpleName();
	private BlockingQueue bq = null;

	public Producer(BlockingQueue queue) {
		this.setBlockingQueue(queue);
	}

	@Override
	public void run() {
		Random rand = new Random();
		int res = 0;
		try {
			res = Addition(rand.nextInt(100), rand.nextInt(50));
			Log.d(TAG, "Produced: " + res);
			bq.put(res);
			Thread.sleep(1000);
			res = Addition(rand.nextInt(100), rand.nextInt(50));
			Log.d(TAG, "Produced: " + res);
			bq.put(res);
			Thread.sleep(1000);
			res = Addition(rand.nextInt(100), rand.nextInt(50));
			Log.d(TAG, "Produced: " + res);
			bq.put(res);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setBlockingQueue(BlockingQueue bq) {
		this.bq = bq;
	}

	public int Addition(int x, int y) {
		int result = 0;
		result = x + y;
		return result;
	}
}
