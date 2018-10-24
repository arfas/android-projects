package com.example.railway_reserv;

import DBHelper.DBHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminRegActivity extends Activity {

	Button register, cancel;
	EditText id, name, pwd, rpwd, phno, mail;
	DBHelper bdh;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_reg);
		register = (Button) findViewById(R.id.register);
		cancel = (Button) findViewById(R.id.cancel);
		// id=(EditText) findViewById(R.id.id);
		name = (EditText) findViewById(R.id.ename);
		pwd = (EditText) findViewById(R.id.pwd);
		rpwd = (EditText) findViewById(R.id.rpwd);
		phno = (EditText) findViewById(R.id.phno);
		mail = (EditText) findViewById(R.id.mail);
		bdh = new DBHelper(this);
		db = bdh.getWritableDatabase();
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (/* !TextUtils.isEmpty(id.getText().toString())&& */!TextUtils.isEmpty(name.getText().toString())
						&& !TextUtils.isEmpty(phno.getText().toString())
						&& !TextUtils.isEmpty(mail.getText().toString())) {
					if (pwd.getText().toString().equals(rpwd.getText().toString())) {
						if (phno.getText().toString().length() == 10) {
							if (mail.getText().toString().contains("@") && mail.getText().toString().contains(".")) {
								ContentValues cv = new ContentValues();
								// cv.put("pid", id.getText().toString());
								cv.put("pname", name.getText().toString());
								cv.put("ppwd", pwd.getText().toString());
								cv.put("pphno", phno.getText().toString());
								cv.put("pmail", mail.getText().toString());
								db.insert("Admin", null, cv);
								Intent i = new Intent(AdminRegActivity.this, AdminLoginActivity.class);
								startActivity(i);
							} else {
								Toast.makeText(AdminRegActivity.this, "youe mail id is not valid ", Toast.LENGTH_LONG)
										.show();

							}
						} else {
							Toast.makeText(AdminRegActivity.this, "your mobile numbet should be 10 digits ",
									Toast.LENGTH_LONG).show();

						}
					} else {
						Toast.makeText(AdminRegActivity.this, "conform password and password are not matching ",
								Toast.LENGTH_LONG).show();

					}
				} else {
					Toast.makeText(AdminRegActivity.this, "your field should not be empty ", Toast.LENGTH_LONG).show();

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