package com.example.railway_reserv;

import java.util.Date;
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

public class FeedBackPage extends Activity {
	EditText ed;
	Button submit, cancel;
	DBHelper bdh;
	SQLiteDatabase db;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_back_page);
		ed = (EditText) findViewById(R.id.editText1);
		submit = (Button) findViewById(R.id.button1);
		sp = (SharedPreferences) getSharedPreferences("bda", Context.MODE_PRIVATE);

		cancel = (Button) findViewById(R.id.button2);
		bdh = new DBHelper(this);
		db = bdh.getWritableDatabase();
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentValues cv = new ContentValues();
				cv.put("feedback", ed.getText().toString());
				cv.put("fdate", new Date() + "");
				cv.put("fgid", sp.getInt("donation_id", 0));
				db.insert("bfeedback", null, cv);
				startActivity(new Intent(FeedBackPage.this, ProfilePage.class));
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
