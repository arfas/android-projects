package com.example.railway_reserv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Home extends Activity {
	Button sign, login, AdminLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		sign = (Button) findViewById(R.id.btnsign);
		login = (Button) findViewById(R.id.btnlogin);
		AdminLogin = (Button) findViewById(R.id.btnAdmin);

		// OnClickListener to perform Task
		sign.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Moving from One Activity to another
				Intent i = new Intent(Home.this, UserRegActivity.class);
				startActivity(i);

				// Message On screen by using TOAST
				Toast.makeText(getApplicationContext(), "- Fill Details -", Toast.LENGTH_LONG).show();

			}
		});
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Moving from One Activity to another
				Intent i = new Intent(Home.this, UserLoginActivity.class);
				startActivity(i);
				// Message On screen by using TOAST
				Toast.makeText(getApplicationContext(), "- Fill Details -", Toast.LENGTH_LONG).show();

			}
		});
		AdminLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Moving from One Activity to another
				Intent i = new Intent(Home.this, AdminLoginActivity.class);
				startActivity(i);
				// Message On screen by using TOAST
				Toast.makeText(getApplicationContext(), "- Fill Details -", Toast.LENGTH_LONG).show();

			}
		});
	}

}
