package com.example.railway_reserv;


import DBHelper.DBHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegActivity extends Activity {
	Button sign, clear;

	EditText n, e, p, pwd, cpwd;
	SQLiteDatabase dbh;
	DBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_reg);
		n = (EditText) findViewById(R.id.susername);
		e = (EditText) findViewById(R.id.sEmail);
		p = (EditText) findViewById(R.id.sphone);
		pwd = (EditText) findViewById(R.id.spwd);
		cpwd = (EditText) findViewById(R.id.scpwd);

		sign = (Button) findViewById(R.id.btnSIGN);
		clear = (Button) findViewById(R.id.btnCLEAR);
		db = new DBHelper(this);

		dbh = db.getWritableDatabase();
		sign.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String username, email, phone, password, cpwd1;
				username = n.getText().toString().trim();
				email = e.getText().toString().trim();
				phone = p.getText().toString().trim();
				password = pwd.getText().toString().trim();
				cpwd1 = cpwd.getText().toString().trim();
				if (!password.equals(cpwd1)) {
					cpwd.setError("Password not matching");
				} else if (e.getText().toString().contains("@") && e.getText().toString().contains(".")) {

					SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("username", username);
					editor.putString("phone", phone);
					editor.putString("password", password);
					editor.commit();

					ContentValues cv = new ContentValues();
					cv.put("buser", username);
					cv.put("bmail", email);
					cv.put("bphno", phone);
					cv.put("bpwd", password);
					dbh.insert("UserReg", null, cv);

					Toast.makeText(getApplicationContext(), "- SUCCESS -", Toast.LENGTH_LONG).show();
					Intent i = new Intent(UserRegActivity.this, UserLoginActivity.class);
					startActivity(i);
					finish();
				} else {
					e.setError("Enter Valid Email ID");
				}

			}
		});

		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				n.setText("");
				e.setText("");
				p.setText("");
				pwd.setText("");
				cpwd.setText("");

			}
		});

	}

}
