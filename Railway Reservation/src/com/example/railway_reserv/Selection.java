package com.example.railway_reserv;

import java.util.ArrayList;

import DBHelper.DBHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Selection extends Activity {

	DBHelper bdh;
	SQLiteDatabase db;
	ArrayAdapter<String> reqadapter;
	Spinner bloodrequ, donarrequ;
	int pos = 0, posi = 1;
	Button vd, vf, donarinfo, NewTrain, logout;
	ArrayList<String> name, phno, bg, gender, age, district, status, ldod;
	ArrayList<String> name1, phno1, bg1, gender1, age1, district1, status1, ldod1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection);
		NewTrain = (Button) findViewById(R.id.btnNewxtrain);

		bloodrequ = (Spinner) findViewById(R.id.brequests);
		donarrequ = (Spinner) findViewById(R.id.drequests);
		vd = (Button) findViewById(R.id.viewdon);
		vf = (Button) findViewById(R.id.viewfeed);
		donarinfo = (Button) findViewById(R.id.donar);
		logout = (Button) findViewById(R.id.btnmlogout);
		bdh = new DBHelper(this);
		db = bdh.getWritableDatabase();

		name = new ArrayList<String>();
		phno = new ArrayList<String>();
		bg = new ArrayList<String>();
		gender = new ArrayList<String>();
		age = new ArrayList<String>();
		district = new ArrayList<String>();

		status = new ArrayList<String>();
		ldod = new ArrayList<String>();

		name1 = new ArrayList<String>();
		phno1 = new ArrayList<String>();
		bg1 = new ArrayList<String>();
		gender1 = new ArrayList<String>();
		age1 = new ArrayList<String>();
		district1 = new ArrayList<String>();

		status1 = new ArrayList<String>();
		ldod1 = new ArrayList<String>();

		String[] cols = { "bname", "bphno", "bbg", "bgender", "bage", "bdistrict", "bstatus", "blod" };
		Cursor cur = db.query("Request", cols, null, null, null, null, null);
		if (cur.moveToFirst()) {
			do {
				name.add(cur.getString(0));
				phno.add(cur.getString(1));
				bg.add(cur.getString(2));
				gender.add(cur.getString(3));
				age.add(cur.getInt(4) + "");
				district.add(cur.getString(5));

				status.add(cur.getString(6));
				ldod.add(cur.getString(7));

			} while (cur.moveToNext());
		}
		cur.close();

		String[] cols1 = { "bname", "bphno", "bbg", "bgender", "bage", "bdistrict", "bstatus", "blod" };
		Cursor cur1 = db.query("Donar", cols1, null, null, null, null, null);
		if (cur1.moveToFirst()) {
			do {
				name1.add(cur1.getString(0));
				phno1.add(cur1.getString(1));
				bg1.add(cur1.getString(2));
				gender1.add(cur1.getString(3));
				age1.add(cur1.getInt(4) + "");
				district1.add(cur1.getString(5));

				status1.add(cur1.getString(6));
				ldod1.add(cur1.getString(7));

			} while (cur1.moveToNext());
		}
		cur1.close();

		if (name.size() != 0) {
			reqadapter = new ArrayAdapter<String>(Selection.this, android.R.layout.simple_dropdown_item_1line, name);
			bloodrequ.setAdapter(reqadapter);
			bloodrequ.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub

					pos = position;

				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub

				}
			});

			vd.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					AlertDialog.Builder alert = new AlertDialog.Builder(Selection.this);
					alert.setTitle("Info Request");
					alert.setMessage("Name : " + name.get(pos).toString() + "\nPhone : " + phno.get(pos).toString()
							+ "\nBloodGroup : " + bg.get(pos).toString() + "\nGender : " + gender.get(pos).toString()
							+ "\nDistrict : " + district.get(pos).toString());
					alert.setPositiveButton("Accept", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
					alert.setNegativeButton("Reject", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
					alert.setNeutralButton("Check Status", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(Selection.this, "Status is : " + status.get(pos).toString(),
									Toast.LENGTH_LONG).show();
						}
					});
					alert.create();
					alert.show();
				}
			});

		}

		if (name1.size() != 0) {
			reqadapter = new ArrayAdapter<String>(Selection.this, android.R.layout.simple_dropdown_item_1line, name1);
			donarrequ.setAdapter(reqadapter);
			donarrequ.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub

					posi = position;

				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub

				}
			});

			donarinfo.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					AlertDialog.Builder alert = new AlertDialog.Builder(Selection.this);
					alert.setTitle("Info about CANCEL");
					alert.setMessage("Name : " + name1.get(posi).toString() + "\nPhone : " + phno1.get(posi).toString()
							+ "" + "\nBloodGroup : " + bg1.get(posi).toString() + "\nGender : "
							+ gender1.get(posi).toString() + "\nDistrict : " + district1.get(posi).toString()
							+ "\nLast Donation Date : " + ldod1.get(posi).toString());
					alert.setPositiveButton("Accept", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
					alert.setNegativeButton("Reject", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
					alert.setNeutralButton("Check Status", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(Selection.this, "Status is : " + status1.get(pos).toString(),
									Toast.LENGTH_LONG).show();
						}
					});
					alert.create();
					alert.show();
				}
			});

		}

		NewTrain.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Selection.this, NewTrainmm.class));
			}
		});

		vf.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Selection.this, ViewFeedBack.class));
			}
		});
		logout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Selection.this, Home.class));
			}
		});
	}

}
