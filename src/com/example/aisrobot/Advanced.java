package com.example.aisrobot;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Advanced extends Activity {

	private BluetoothHandler btHandler;
	private EditText field;
	private Button execute;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advanced);
		btHandler = MainActivity.btHandler;
		field = (EditText)findViewById(R.id.editText1);
		execute = (Button)findViewById(R.id.exec);
		
		
		
		
		
		execute.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String[] things = field.getText().toString().split("\n");
					ArrayList<String> toExecute = new ArrayList<String>();
					for(String add:things) {
						toExecute.add(add);
				
					}
					System.out.println(toExecute);
				}catch(Exception e) {
					Toast.makeText(getApplicationContext(), "Fix Errors Before Proceeding", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}



	@Override
	public void onBackPressed() {
		super.onBackPressed();
		btHandler = null;
		finish();
	}
}
