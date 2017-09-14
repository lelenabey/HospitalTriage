package com.example.hospital;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		File passwdFile = new File
				(this.getApplicationContext().getFilesDir(), "passwords.txt");
		if (!passwdFile.exists()){
			try {
				passwdFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
	
	public void register(View view) {
		Intent intent = new Intent(this, RegisterNurse.class);
		int idCount = 99990000;
		File passwdFile = new File
				(this.getApplicationContext().getFilesDir(), "passwords.txt");
		FileManager f = new FileManager(passwdFile);
		try{
		ArrayList<String> contents = f.readFromFile();
		//System.out.println(contents);
		if (contents.size() > 0){
			String lastID = (contents.get(contents.size()-1).split(","))[0];
			idCount = (Integer.parseInt(lastID)) + 1;
		}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		String newID = String.valueOf(idCount);
		intent.putExtra("newID", newID);
		startActivity(intent);
	}
	
	public void login(View view) throws IOException {
		Intent intent = new Intent(this, Actions.class);
		EditText getID = (EditText) findViewById(R.id.iden);
		EditText getPassword = (EditText) findViewById(R.id.password);
		String ID = getID .getText().toString();
		String password = getPassword.getText().toString();
		boolean check = false; // user exists
		
		File passwdFile = new File
				(this.getApplicationContext().getFilesDir(), "passwords.txt");
		FileManager n = new FileManager(passwdFile);
		LoginManager m = new LoginManager(n.readFromFile());
		check = m.check(ID, password);
		
		if(check){
			intent.putExtra("ID", ID);
			intent.putExtra("Name", m.getN(ID, password));
			startActivity(intent);
		}
		else{
			loginError();
		}
	}
	
	public void loginError(){
		AlertDialog alertDialog;
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Error");
		alertDialog.setMessage("Wrong username and/or password.");
		alertDialog.show();
	}

}
