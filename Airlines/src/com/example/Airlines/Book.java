package com.example.Airlines;

import DBHelper.DBHelper;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.*;

public class Book extends Activity {

	TextView dob;
	DBHelper bdh;
	SQLiteDatabase db;
	Spinner fromm, too;
	EditText phone;
	Button datem, bookm;
	String[] frm = { "Select From List", "Hyderabad", "Vijaywada", "Warangal", "Delhi", "J&K", "Nepal" };
	String[] t = { "Select From List", "Hyderabad", "Vijaywada", "Warangal", "Delhi", "J&K", "Nepal" };
	String myselectedbgrp, myselectedbgrp2, Body, Des;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book);

		dob = (TextView) findViewById(R.id.textew3);
		fromm = (Spinner) findViewById(R.id.fromSpinner);
		too = (Spinner) findViewById(R.id.toSpinner);
		phone = (EditText) findViewById(R.id.edphonebook);
		datem = (Button) findViewById(R.id.bdate);
		bookm = (Button) findViewById(R.id.bBook12);

		bdh = new DBHelper(this);
		db = bdh.getWritableDatabase();
		ArrayAdapter<String> mybgroups = new ArrayAdapter<String>(Book.this,
				android.R.layout.simple_dropdown_item_1line, frm);
		fromm.setAdapter(mybgroups);
		ArrayAdapter<String> mybgroups2 = new ArrayAdapter<String>(Book.this,
				android.R.layout.simple_dropdown_item_1line, t);
		too.setAdapter(mybgroups2);

		fromm.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				myselectedbgrp = frm[position];
				Toast.makeText(getApplicationContext(), myselectedbgrp, 5).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		too.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				myselectedbgrp2 = t[position];
				Toast.makeText(getApplicationContext(), myselectedbgrp2, 5).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		datem.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_PICKER_ID);
			}
		});
		bookm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String p, fm, t1, d;

				p = phone.getText().toString();
				fm = myselectedbgrp.toString();
				t1 = myselectedbgrp2.toString();
				d = dob.getText().toString();

				ContentValues cv = new ContentValues();
				cv.put("bphone", p);
				cv.put("bfrom", fm);
				cv.put("bto", t1);
				cv.put("bdate", d);
				System.out.println(cv);
				db.insert("Book", null, cv);
				Toast.makeText(getApplicationContext(), "Successful Booking", Toast.LENGTH_LONG).show();

				String result = new String("Ticket Booking Done on");

				result = result + ("\n Phone : " + p);
				result = result + ("\n From : " + fm);
				result = result + ("\n To  : " + t1);
				result = result + ("\n Date of Journey : " + d);
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				String[] recipients = new String[] { "adi.kuppannagari@gmail.com", "akhilreddy.kanuganti@gmail.com",
						"kalyankarharish@gmail.com" };

				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);

				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Ticket Booking");
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, result);
				emailIntent.setType("text/plain");
				startActivity(Intent.createChooser(emailIntent, "Select Mail..."));

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {

			Intent i = new Intent(Book.this, LoginA.class);
			startActivity(i);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private int year;
	private int month;
	private int day;

	static final int DATE_PICKER_ID = 1111;

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_ID:

			// open datepicker dialog.
			// set date picker for current date
			// add pickerListener listner to date picker
			return new DatePickerDialog(this, pickerListener, year, month, day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		@Override
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// Show selected date
			dob.setText(
					new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));

		}
	};
}
