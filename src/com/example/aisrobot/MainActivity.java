package com.example.aisrobot;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static BluetoothHandler btHandler;
	private Button forward,backward,right,left,stop,execute,clear,recon;
	private TextView console;
	private ArrayList<String> stack;
	private Context context;
	public static String ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = getApplicationContext();
		startActivity(new Intent(context, ChooseDeviceToConnectTo.class));
		init();
		handleListeners();


	}

	private void init() {
		forward = (Button)findViewById(R.id.forward);
		recon = (Button)findViewById(R.id.reconnect);
		backward = (Button)findViewById(R.id.backward);
		right = (Button)findViewById(R.id.right);
		left = (Button)findViewById(R.id.left);
		stop = (Button)findViewById(R.id.stop);
		execute = (Button)findViewById(R.id.execute);
		console = (TextView)findViewById(R.id.console);
		console.setMovementMethod(new ScrollingMovementMethod());
		clear = (Button)findViewById(R.id.clear);
		stack = new ArrayList<String>();
	}

	private void handleListeners() {
		forward.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stack.add("Forwards");
				updateConsole();

			}
		});

		backward.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stack.add("Backwards");
				updateConsole();

			}
		});

		left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				stack.add("Left");
				updateConsole();
			}
		});

		right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				stack.add("Right");
				updateConsole();
			}
		});

		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				stack.add("Stop");
				updateConsole();
			}
		});

		execute.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				executeActions();

			}

		});

		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				stack.clear();
				updateConsole();

			}
		});

		recon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btHandler = null;
				startActivity(new Intent(context, ChooseDeviceToConnectTo.class));


			}
		});
	}

	private void updateConsole() {
		String data = "";
		for(String x:stack)
			data+="\n"+x;

		console.setText(data);

	}

	private void executeActions() {

		for(String item:stack) {

			String code = "1";

			if(item.equals("Forwards")) {
				code = "3";
			}else if(item.equals("Backwards")) {
				code = "2";
			}else if(item.equals("Left")) {
				code = "5";
			}else if(item.equals("Right")) {
				code = "4";
			}

			btHandler.write(code);

			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				Toast.makeText(getApplicationContext(), "Cant Sleep Now Killing Self", Toast.LENGTH_SHORT).show();
				System.exit(-1);
			}
		}

		btHandler.write("1");
		clear.performClick();
		stack.clear();
	}

	protected void onDestroy() {
		super.onDestroy();
		btHandler = null;
	}
}
