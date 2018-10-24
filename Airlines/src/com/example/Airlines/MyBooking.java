package com.example.Airlines;

import java.util.ArrayList;
import DBHelper.DBHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyBooking extends Activity {

	ListView lv;
	ArrayAdapter<String> re;
	ArrayList<String> fb, fd, ff, ft;
	DBHelper bdh;
	SQLiteDatabase db;
	TextView t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mybooking);

		lv = (ListView) findViewById(R.id.gridVi1);

		t = (TextView) findViewById(R.id.textretrieve);
		bdh = new DBHelper(this);
		db = bdh.getWritableDatabase();
		fb = new ArrayList<String>();
		fd = new ArrayList<String>();
		ff = new ArrayList<String>();
		ft = new ArrayList<String>();

		String[] cols = { "bphone", "bfrom", "bto", "bdate" };

		Cursor cur = db.query("Book", cols, null, null, null, null, null);
		if (cur.moveToFirst()) {
			do {

				fb.add(cur.getString(0));
				fd.add(cur.getString(1));
				ff.add(cur.getString(2));
				ft.add(cur.getString(3));

			} while (cur.moveToNext());
		}
		cur.close();
		if (fb.size() != 0) {
			re = new ArrayAdapter<String>(MyBooking.this, android.R.layout.simple_list_item_1, fb);
		}
		lv.setAdapter(re);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub

				StringBuffer result = new StringBuffer();
				result.append(" You Have Booked Tickets ");
				result.append("\n Phone : ").append(fb.get(position).toString());
				result.append("\n From : ").append(fd.get(position).toString());
				result.append("\n To : ").append(ff.get(position).toString());
				result.append("\n On Date : ").append(ft.get(position).toString());
				t.setText(result);
				t.setTextColor(Color.GREEN);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_booking, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_setting) {
			Intent i = new Intent(MyBooking.this, LoginA.class);
			startActivity(i);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
