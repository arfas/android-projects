package com.example.railway_reserv;

import java.util.ArrayList;

import DBHelper.DBHelper;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ViewFeedBack extends Activity {
	DBHelper bdh;
	SQLiteDatabase db;
	ListView lv;
	ArrayList<String> fb, fd;
	ArrayAdapter<String> reqadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_feedback);
		lv = (ListView) findViewById(R.id.listView1);
		bdh = new DBHelper(this);
		db = bdh.getWritableDatabase();
		fb = new ArrayList<String>();
		fd = new ArrayList<String>();

		String[] cols = { "feedback", "fdate" };
		Cursor cur = db.query("bfeedback", cols, null, null, null, null, null);
		if (cur.moveToFirst()) {
			do {

				fb.add(cur.getString(0));
				fd.add(cur.getString(1));

			} while (cur.moveToNext());
		}
		cur.close();
		if (fb.size() != 0) {
			reqadapter = new ArrayAdapter<String>(ViewFeedBack.this, android.R.layout.simple_list_item_1, fb);
		}
		lv.setAdapter(reqadapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Given Feed Back on " + fd.get(position).toString(),
						Toast.LENGTH_LONG).show();
			}
		});
	}
}
