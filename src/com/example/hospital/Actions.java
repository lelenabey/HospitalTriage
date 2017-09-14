package com.example.hospital;

import PII.Patient;
import PII.SymptomVitalSign;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class Actions extends Activity implements ListPage {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nurse_actions);
		File patientFile = new File
				(this.getApplicationContext().getFilesDir(), "patients.txt");
		if (!patientFile.exists()){
			try {
				patientFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File svsFile = new File
				(this.getApplicationContext().getFilesDir(), "svs.txt");
		if (!svsFile.exists()){
			try {
				svsFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		Intent intent = getIntent();
		setTitle((String)intent.getSerializableExtra("Name"));
		
		TabHost tabs=(TabHost)findViewById(R.id.tabhost); 
		tabs.setup();
		
		TabHost.TabSpec tab1 = tabs.newTabSpec("tab1"); 
		tab1.setIndicator("Add Patient");
		tab1.setContent(R.id.addPatient);
		datepicker();
		
		TabHost.TabSpec tab2 = tabs.newTabSpec("tab2");
		tab2.setIndicator("List Patients"); 
		tab2.setContent(R.id.updatePatient); 
		
		
		tabs.addTab(tab1); 
		tabs.addTab(tab2); 
		//tabs.setCurrentTab(0);
		
		try {
			ArrayList<Patient> allPatients = Patient.getAllPatients(patientFile);
			createListView(R.id.listView, this, getPatientNames(allPatients));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
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
	
	@SuppressLint("NewApi")
	public void datepicker() {
	 
		DatePicker date = (DatePicker) findViewById(R.id.date_picker);
		
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		date.init(year, month, day, null);
	 
	}

	@Override
	public void createListView(int id, Activity page, String[] items) {
		ListView lv = (ListView)findViewById(id);
		ArrayAdapter<String> simpleAdpt = new ArrayAdapter<String>(page,
	              android.R.layout.simple_list_item_1, android.R.id.text1, items);
		
	    lv.setAdapter(simpleAdpt);
	    lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                
            	String line =    ((TextView) view.findViewById(view.getId())).getText().toString();
            	String HC = (line.split("\n"))[1];
            	String HCnum = HC.replaceAll("Health Card Number: ", "");
            	Intent intent = new Intent(getApplicationContext(), EditPatient.class);
            	intent.putExtra("pos", String.valueOf(position));
            	intent.putExtra("HCnum", HCnum);
            	startActivity(intent);
                
            }
        });
		
	}
	
	public void addPatient(View view) throws FileNotFoundException, IOException, ParseException{
		DatePicker date = (DatePicker) findViewById(R.id.date_picker);
		String patientName = ((TextView) findViewById(R.id.patientName)).
				getText().toString().trim();
		String healthCardNumber = ((TextView) findViewById(R.id.healthCardNumber)).
				getText().toString().trim();
		String dobYear = String.valueOf(date.getYear());
		String dobMonth = String.valueOf(date.getMonth());
		String dobDay = String.valueOf(date.getDayOfMonth());
		String dias = ((TextView) findViewById(R.id.Diastolic)).
				getText().toString().trim();
		String sys = ((TextView) findViewById(R.id.Systolic)).
				getText().toString().trim();
		String heartRate = ((TextView) findViewById(R.id.HeartRate)).
				getText().toString().trim();
		String temp = ((TextView) findViewById(R.id.Temperature)).
				getText().toString().trim();
		String symp = ((TextView) findViewById(R.id.SymptomDescription)).
				getText().toString().trim();
		
		Patient p = new Patient(patientName,dobMonth+"/"+dobDay+"/"+dobYear,Long.parseLong(healthCardNumber), new Date().toString());
		SymptomVitalSign s = new SymptomVitalSign(Double.parseDouble(temp), 
				Double.parseDouble(sys), Double.parseDouble(dias), Double.parseDouble(heartRate),
				new Date().toString());
		s.setSymptomDescription(symp);
		
		File patientFile = new File
				(this.getApplicationContext().getFilesDir(), "patients.txt");
		
		File svsFile = new File
				(this.getApplicationContext().getFilesDir(), "svs.txt");
		
		//ArrayList<SymptomVitalSign> svs = SymptomVitalSign.getAllSVS(svsFile);
		//System.out.println(svs);
		//SymptomVitalSign.updateToFile(svsFile, svs);
		FileManager m = new FileManager(svsFile);
		m.saveToFile(s.toString());
		p.savePatientToFile(patientFile);
		ArrayList<Patient> allPatients = Patient.getAllPatients(patientFile);
		createListView(R.id.listView, this, getPatientNames(allPatients));
		AddSuccessfull();
		
	}
	
	public String[] getPatientNames(ArrayList<Patient> allPatients){
		String[] all = new String[allPatients.size()];
		for (int x = 0; x < allPatients.size(); x++){
			all[x] = "Name: " + allPatients.get(x).getName() + "\n" 
					+ "Health Card Number: " + allPatients.get(x).getHealthCardNumber() + "\n" 
					+ "Arrival Time: "+ allPatients.get(x).getArrivalTime()+ "\n" 
					+ "Birthday: " + allPatients.get(x).getBirthday() + "\n" ;
		}
		return all;
	}
	
	public void AddSuccessfull(){
		AlertDialog alertDialog;
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Patient Added");
		alertDialog.setMessage("Patient has been added. View in List" +
				" Patient.");
		alertDialog.show();
	}
	
}
