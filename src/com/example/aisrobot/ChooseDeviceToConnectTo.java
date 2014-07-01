package com.example.aisrobot;

import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class ChooseDeviceToConnectTo extends Activity {

	@Override
	public void onBackPressed() {

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_device_to_connect_to);


		ListView list = (ListView)findViewById(R.id.list);

		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		final Set<BluetoothDevice> paired = mBluetoothAdapter.getBondedDevices();

		ArrayList<String> s = new ArrayList<String>();
		final ArrayList<String> ads = new ArrayList<String>();

		for(BluetoothDevice d:paired) {
			ads.add(d.getAddress());
			s.add(d.getName() + "\n" + d.getAddress());
		}

		list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s));
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				MainActivity.btHandler = new BluetoothHandler(view.getContext(), ads.get((int) id));
				finish();
			}
		});
	}
}