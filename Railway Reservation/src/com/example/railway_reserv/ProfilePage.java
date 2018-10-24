package com.example.railway_reserv;

import java.util.ArrayList;

import DBHelper.DBHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ProfilePage extends Activity {

	Button b1, b2, b3, b4, donar, viewm, refreshm;
	TextView t;
	DBHelper bdh;
	SQLiteDatabase db;
	ListView lv;
	ArrayList<String> fb, fd;
	ArrayAdapter<String> reqadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_page);
		b1 = (Button) findViewById(R.id.b1);
		b2 = (Button) findViewById(R.id.b2);
		b3 = (Button) findViewById(R.id.b3);
		b4 = (Button) findViewById(R.id.b4);
		refreshm = (Button) findViewById(R.id.btnrefreshtrain);
		viewm = (Button) findViewById(R.id.btnviewtrains);
		donar = (Button) findViewById(R.id.bd);
		t = (TextView) findViewById(R.id.textrain);
		lv = (ListView) findViewById(R.id.gridView1);
		t.setText("");
		bdh = new DBHelper(this);
		db = bdh.getWritableDatabase();
		refreshm.setVisibility(View.GONE);

		viewm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				fb = new ArrayList<String>();
				fd = new ArrayList<String>();

				String[] cols = { "TrainName", "tcode" };
				Cursor cur = db.query("NewTrain", cols, null, null, null, null, null);
				if (cur.moveToFirst()) {
					do {

						fb.add(cur.getString(0));
						fd.add(cur.getString(1));

					} while (cur.moveToNext());
				}
				cur.close();
				if (fb.size() != 0) {
					reqadapter = new ArrayAdapter<String>(ProfilePage.this, android.R.layout.simple_list_item_1, fb);
				}
				lv.setAdapter(reqadapter);
				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

						StringBuffer result = new StringBuffer();
						result.append(" Name :  ").append(fb.get(position).toString());
						result.append("\n Phone : ").append(fd.get(position).toString());

						t.setText(result);

						refreshm.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {

								b1.setVisibility(View.VISIBLE);
								b2.setVisibility(View.VISIBLE);
								b3.setVisibility(View.VISIBLE);
								b4.setVisibility(View.VISIBLE);
								viewm.setVisibility(View.VISIBLE);
								donar.setVisibility(View.VISIBLE);
								lv.setVisibility(View.GONE);

								refreshm.setVisibility(View.GONE);
								t.setText(" ");

							}
						});

					}
				});

				b1.setVisibility(View.GONE);
				b2.setVisibility(View.GONE);
				b3.setVisibility(View.GONE);
				b4.setVisibility(View.GONE);
				viewm.setVisibility(View.GONE);
				donar.setVisibility(View.GONE);
				lv.setVisibility(View.VISIBLE);

				refreshm.setVisibility(View.VISIBLE);

			}

		});
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ProfilePage.this, UserRequest.class);
				startActivity(i);

			}
		});

		donar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ProfilePage.this, UserCancel.class);
				startActivity(i);
			}
		});

		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "PROFILE CHANGES", 5).show();
				Intent i = new Intent(ProfilePage.this, ProfileUpdate.class);
				startActivity(i);
			}
		});
		b3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ProfilePage.this, FeedBackPage.class);
				startActivity(i);
			}
		});
		b4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ProfilePage.this, Home.class);
				startActivity(i);
				finish();
			}
		});

	}

}
