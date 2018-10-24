package com.example.railway_reserv;

import DBHelper.DBHelper;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class UserRequest extends Activity {
	Button submit, cancel, pickldod;
	EditText name, phno, age, setldod;
	TextView gender;
	RadioGroup gender1;
	RadioButton selectedgender;
	Spinner bloodgrp, place, status;
	String[] groups = { "Select your Blood Group", "A+ve", "A-ve", "B+ve", "B-ve", "AB+ve", "AB-ve", "O+ve", "O-ve" };
	String[] district = { "Select Your District", "Adilabad", "Anatapur", "Chittoor", "East Godaveri", "Guntur",
			"Hyderadad", "Kadapa", "Karimnagar", "Khammam", "Krishna", "Kurnool", "Mahaboobnagar", "Medak", "Nalgonda",
			"Nellore", "Nizamabad", "Prakasam", "Rangareddy", "Srikakulam", "Vijayanagaram", "Vishakapatnam",
			"Warangal", "West Godavari" };

	String[] status1 = { "Select Status", "Required", "Not Required" };
	DBHelper brdb;
	SharedPreferences sp;
	String myselectedgroup, myselecteddistrict, myselectedunits;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_request);
		submit = (Button) findViewById(R.id.submit);
		cancel = (Button) findViewById(R.id.cancel);
		sp = (SharedPreferences) getSharedPreferences("bda", Context.MODE_PRIVATE);
		pickldod = (Button) findViewById(R.id.blod);
		setldod = (EditText) findViewById(R.id.ldod);
		pickldod.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				showDialog(DATE_PICKER_ID);
			}
		});
		name = (EditText) findViewById(R.id.name);
		phno = (EditText) findViewById(R.id.phno);
		age = (EditText) findViewById(R.id.age);
		gender = (TextView) findViewById(R.id.gender);
		gender1 = (RadioGroup) findViewById(R.id.gender1);
		// Spinners
		bloodgrp = (Spinner) findViewById(R.id.bloodgrp);
		place = (Spinner) findViewById(R.id.place);
		status = (Spinner) findViewById(R.id.status);

		brdb = new DBHelper(this);
		db = brdb.getWritableDatabase();
		ArrayAdapter<String> mybloodgroup = new ArrayAdapter<String>(UserRequest.this,
				android.R.layout.simple_dropdown_item_1line, groups);
		bloodgrp.setAdapter(mybloodgroup);
		ArrayAdapter<String> mybloodstatus = new ArrayAdapter<String>(UserRequest.this,
				android.R.layout.simple_dropdown_item_1line, status1);
		status.setAdapter(mybloodstatus);

		gender1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				selectedgender = (RadioButton) findViewById(checkedId);
			}
		});
		bloodgrp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				myselectedgroup = groups[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		ArrayAdapter<String> mydistrict = new ArrayAdapter<String>(UserRequest.this,
				android.R.layout.simple_dropdown_item_1line, district);
		place.setAdapter(mydistrict);
		place.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				myselecteddistrict = district[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// Buttons
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(phno.getText().toString())
						&& !TextUtils.isEmpty(gender.getText().toString())
						&& !TextUtils.isEmpty(age.getText().toString())) {
					ContentValues cv = new ContentValues();
					cv.put("request_id", sp.getInt("request_id", 0));
					cv.put("bname", name.getText().toString());
					cv.put("bphno", phno.getText().toString());
					cv.put("bgender", selectedgender.getText().toString());
					cv.put("bage", Integer.parseInt(age.getText().toString()));
					cv.put("bbg", myselectedgroup);
					cv.put("bdistrict", myselecteddistrict);

					cv.put("bstatus", status.getSelectedItem().toString());
					cv.put("blod", setldod.getText().toString());
					db.insert("Request", null, cv);

					finish();
					Toast.makeText(UserRequest.this, "Successfully Requested for Booking ", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(UserRequest.this, "Your field should not be empty", Toast.LENGTH_LONG).show();
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
			setldod.setText(
					new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));

		}
	};

}
