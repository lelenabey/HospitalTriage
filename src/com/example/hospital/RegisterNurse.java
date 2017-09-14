package com.example.hospital;



import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterNurse extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_nurse);
		
		Intent intent = getIntent();
		TextView newID = (TextView) findViewById(R.id.idnum);
		newID.setText(" " + (String)intent.getSerializableExtra("newID"));
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
	
	public void regNurse(View view) throws IOException{
		String ID = ((TextView) findViewById(R.id.idnum)).
				getText().toString().trim();
		String Fname = ((EditText) findViewById(R.id.fname)).
				getText().toString();
		String Lname = ((EditText) findViewById(R.id.lname)).
				getText().toString();
		String Pass = ((EditText) findViewById(R.id.pass)).
				getText().toString();
		String Repass = ((EditText) findViewById(R.id.repass)).
				getText().toString();
		AlertDialog alertDialog;
		alertDialog = new AlertDialog.Builder(this).create();
		if (Fname.equals("") || Lname.equals("") || Pass.equals("")
				|| Repass.equals("")){
			alertDialog.setTitle("Empty Field(s)");
			alertDialog.setMessage("One or more of the required fields" +
					" are empty.");
			alertDialog.show();	
		}
		else if(!Pass.equals(Repass)){
			alertDialog.setTitle("Error");
			alertDialog.setMessage("The passwords do not match");
			alertDialog.show();
		}
		else{
			File passwdFile = new File
					(this.getApplicationContext().getFilesDir(), "passwords.txt");
			if (!passwdFile.exists()){
				passwdFile.createNewFile();
			}
			FileManager f = new FileManager(passwdFile);
			f.saveToFile((ID + "," + Pass + "," + Fname + ","
					+ Lname  + "\n"));
			Intent intent = new Intent(this, Actions.class);
			intent.putExtra("ID", ID);
			intent.putExtra("Name", Fname + " " + Lname);
			startActivity(intent);
			
		}
	}
		
}
	