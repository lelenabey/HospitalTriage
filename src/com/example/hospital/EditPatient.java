package com.example.hospital;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

import PII.SymptomVitalSign;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class EditPatient extends Activity implements ListPage {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_patient);
		
		Intent intent = getIntent();
		int pos = Integer.parseInt((String)intent.
				getSerializableExtra("pos"));
		String HCnum = (String)intent.getSerializableExtra("HCnum");
		File svsFile = new File
				(this.getApplicationContext().getFilesDir(), "svs.txt");
		FileManager m = new FileManager(svsFile);
		try {
			String[] data = m.readToPos(pos).split(",");
			EditText temp = (EditText)findViewById(R.id.Temperature);
			temp.setText(data[0]);
			EditText dias = (EditText)findViewById(R.id.Diastolic);
			dias.setText(data[2]);
			EditText sys = (EditText)findViewById(R.id.Systolic);
			sys.setText(data[1]);
			EditText HR = (EditText)findViewById(R.id.HeartRate);
			HR.setText(data[3]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File patient = new File
				(this.getApplicationContext().getFilesDir(), HCnum + ".txt");
		FileManager m1 = new FileManager(patient);
		m1.createFileIfNotExist();
		
		try {
			createListView(R.id.listView2, this, getPatientData(m1.readFromFile()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
	
	public void savePatient(View view) throws FileNotFoundException{
		Intent intent = getIntent();
		int pos = Integer.parseInt((String)intent.
				getSerializableExtra("pos"));
		String HCnum = (String)intent.getSerializableExtra("HCnum");
		
		String dias = ((TextView) findViewById(R.id.Diastolic)).
				getText().toString().trim();
		String sys = ((TextView) findViewById(R.id.Systolic)).
				getText().toString().trim();
		String heartRate = ((TextView) findViewById(R.id.HeartRate)).
				getText().toString().trim();
		String temp = ((TextView) findViewById(R.id.Temperature)).
				getText().toString().trim();
		
		File svsFile = new File
				(this.getApplicationContext().getFilesDir(), "svs.txt");
		FileManager m = new FileManager(svsFile);
		
		SymptomVitalSign s = new SymptomVitalSign(Double.parseDouble(temp), 
				Double.parseDouble(sys), Double.parseDouble(dias), Double.parseDouble(heartRate),
				new Date().toString());
		
		String modifiedLine = s.toString() + "\n";
		System.out.println(HCnum);
		File temp1 = new File
				(this.getApplicationContext().getFilesDir(), "temp.txt");
		File patientFile = new File
				(this.getApplicationContext().getFilesDir(), HCnum + ".txt");
		m.modifyFile(modifiedLine, pos, patientFile, temp1);
		temp1.renameTo(svsFile);
		
		File patient = new File
				(this.getApplicationContext().getFilesDir(), HCnum + ".txt");
		FileManager m1 = new FileManager(patient);
		m1.createFileIfNotExist();
		
		try {
			createListView(R.id.listView2, this, getPatientData(m1.readFromFile()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void createListView(int id, Activity page, String[] items) {
		ListView lv = (ListView)findViewById(id);
		ArrayAdapter<String> simpleAdpt = new ArrayAdapter<String>(page,
	              android.R.layout.simple_list_item_1, android.R.id.text1, items);
		
	    lv.setAdapter(simpleAdpt);		
	}
	
	public String[] getPatientData(ArrayList<String> data){
		String[] all = new String[data.size()];
		for (int x = 0; x < data.size(); x++){
			all[x] = "Temperature: " + (data.get(x).split(","))[0] + "\n" 
					+ "Diastolic: " + (data.get(x).split(","))[1] + "\n" 
					+ "Systolic: " + (data.get(x).split(","))[2] + "\n" 
					+ "Heart Rate: " + (data.get(x).split(","))[3] + "\n" ;
		}
		return all;
	}
}
