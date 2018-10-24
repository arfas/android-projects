package com.example.railway_reserv;

import DBHelper.DBHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ProfileUpdate extends Activity {

	Button update, cancel;
	EditText npwd, rpwd, nmail, nphno;
	SharedPreferences sp;
	DBHelper bdh;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_update);
		sp = (SharedPreferences) getSharedPreferences("bda", Context.MODE_PRIVATE);
		update = (Button) findViewById(R.id.bnupdate);
		cancel = (Button) findViewById(R.id.bncancel);
		npwd = (EditText) findViewById(R.id.npwd);
		rpwd = (EditText) findViewById(R.id.rpwd);
		nmail = (EditText) findViewById(R.id.nmail);
		nphno = (EditText) findViewById(R.id.nphno);

		bdh = new DBHelper(ProfileUpdate.this);
		db = bdh.getWritableDatabase();

		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// if condition here
				String[] sel = { sp.getInt("donation_id", 0) + "" };
				ContentValues cv = new ContentValues();
				cv.put("bmail", nmail.getText().toString());
				cv.put("bphno", nphno.getText().toString());
				cv.put("bpwd", npwd.getText().toString());

				db.update("UserReg", cv, "donation_id=?", sel);
				startActivity(new Intent(ProfileUpdate.this, ProfilePage.class));
				finish();

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
