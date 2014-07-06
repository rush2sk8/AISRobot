package com.example.aisrobot;

import android.app.Activity;
import android.os.Bundle;

public class Advanced extends Activity {

	private BluetoothHandler btHandler;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advanced);

	}



	@Override
	public void onBackPressed() {
		super.onBackPressed();
		btHandler = null;
		finish();
	}
}
