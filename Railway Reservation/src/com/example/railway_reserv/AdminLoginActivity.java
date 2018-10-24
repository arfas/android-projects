package com.example.railway_reserv;

import DBHelper.DBHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends Activity {

	Button login, cancel, Reg;
	EditText name, pwd;
	DBHelper bdh;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_login);
		Reg = (Button) findViewById(R.id.btnAdminRegm);
		login = (Button) findViewById(R.id.btnAdminLogin);
		cancel = (Button) findViewById(R.id.btnAdminCancel);
		name = (EditText) findViewById(R.id.lAdminphone);
		pwd = (EditText) findViewById(R.id.lAdminpwd);
		bdh = new DBHelper(this);
		db = bdh.getWritableDatabase();
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] cols = { "pname", "ppwd" };
				if (!TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(pwd.getText().toString())) {
					String[] selected = { name.getText().toString(), pwd.getText().toString() };
					Cursor cur = db.query("Admin", cols, "pname=? and ppwd=?", selected, null, null, null);
					if (cur.moveToFirst()) {
						do {
							Intent i = new Intent(AdminLoginActivity.this, Selection.class);
							startActivity(i);
							finish();
						} while (cur.moveToNext());
					} else {
						Toast.makeText(AdminLoginActivity.this, "your are not a valid user", Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(AdminLoginActivity.this, "Field should not be empty", Toast.LENGTH_LONG).show();

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
		Reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(AdminLoginActivity.this, AdminRegActivity.class);
				startActivity(i);
			}
		});

	}
}
