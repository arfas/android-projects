package com.example.Airlines;

import DBHelper.DBHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

public class LoginA extends Activity {

	EditText username, password, name, pwd, rpwd, phno, mail;
	String usernam, email, phone, passwor, cpwd1;
	Button sign, register;
	DBHelper bdh;
	SQLiteDatabase db;
	ScrollView sc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logina);
		sign = (Button) findViewById(R.id.blsign);
		register = (Button) findViewById(R.id.blregister);
		name = (EditText) findViewById(R.id.ename);
		pwd = (EditText) findViewById(R.id.pwd);
		rpwd = (EditText) findViewById(R.id.rpwd);
		phno = (EditText) findViewById(R.id.phno);
		mail = (EditText) findViewById(R.id.mail);

		username = (EditText) findViewById(R.id.eluser);
		password = (EditText) findViewById(R.id.elpass);

		sc = (ScrollView) findViewById(R.id.scrollView);

		bdh = new DBHelper(this);
		db = bdh.getWritableDatabase();
		sign.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String[] cols = { "pname", "ppwd" };
				System.out.println(cols);
				if (!TextUtils.isEmpty(username.getText().toString())
						&& !TextUtils.isEmpty(password.getText().toString())) {
					String[] selected = { username.getText().toString(), password.getText().toString() };

					Cursor cur = db.query("User", cols, "pname=? and ppwd=?", selected, null, null, null);

					if (cur.moveToFirst()) {
						do {
							Intent i = new Intent(LoginA.this, MainActivity.class);
							startActivity(i);
							finish();

						} while (cur.moveToNext());
					} else {
						Toast.makeText(LoginA.this, "your are not a valid user", Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(LoginA.this, "Field should not be empty", Toast.LENGTH_LONG).show();

				}
			}
		});

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				usernam = name.getText().toString().trim();
				email = mail.getText().toString().trim();
				phone = phno.getText().toString().trim();
				passwor = pwd.getText().toString().trim();
				cpwd1 = rpwd.getText().toString().trim();
				if (!passwor.equals(cpwd1)) {
					rpwd.setError("Password not matching");
				} else if (mail.getText().toString().contains("@") && mail.getText().toString().contains(".")) {

					ContentValues cv = new ContentValues();
					cv.put("pname", usernam);
					cv.put("ppwd", passwor);
					cv.put("pmail", email);
					cv.put("pphno", phone);
					db.insert("User", null, cv);
					System.out.println(cv);
					
					
					sc.setVisibility(View.GONE);
					sign.setBackgroundColor(Color.BLUE);
					sign.setText("Login Now");
					
					
					Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();

				} else {
					mail.setError("Enter Valid Email ID");
				}
			}
		});

	}
}
