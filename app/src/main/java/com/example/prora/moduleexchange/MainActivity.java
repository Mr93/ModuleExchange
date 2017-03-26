package com.example.prora.moduleexchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BlockingQueue bq = new ArrayBlockingQueue(1000);

		Producer producer = new Producer(bq);
		//Consumer consumer = new Consumer(bq);

		new Thread(producer).start();
		//new Thread(consumer).start();

		//Thread.sleep(4000);
	}
}
