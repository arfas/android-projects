package com.example.railway_reserv;

import DBHelper.DBHelper;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UserLoginActivity extends Activity {

	EditText p, pwd1;
	Button login, cancel;
	DBHelper bdh;
	SQLiteDatabase db;

	public final String DEFAULT = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_login);
		p = (EditText) findViewById(R.id.lphone);
		pwd1 = (EditText) findViewById(R.id.lpwd);
		login = (Button) findViewById(R.id.btnLogin);
		cancel = (Button) findViewById(R.id.btnCancel);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String phone = p.getText().toString().trim();
				String password = pwd1.getText().toString().trim();

				SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
				String susername = sp.getString("username", DEFAULT);
				String sphone = sp.getString("phone", DEFAULT);
				String spassword = sp.getString("password", DEFAULT);
				if (phone.equals(sphone) && password.equals(spassword)) {
					Intent i = new Intent(UserLoginActivity.this, ProfilePage.class);

					i.putExtra("username", susername);
					startActivity(i);
					finish();
				} else {
					p.setError("INCORRECT");
					pwd1.setError("INCORRECT");
				}

			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}
}