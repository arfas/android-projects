package com.example.railway_reserv;

import DBHelper.DBHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewTrainmm extends Activity {

	Button insertm, cancel;
	EditText inserttrain, Codem;
	SharedPreferences sp;
	DBHelper bdh;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_train);

		insertm = (Button) findViewById(R.id.btnSubmittrain);
		cancel = (Button) findViewById(R.id.btnCancelTrain);

		inserttrain = (EditText) findViewById(R.id.eNewTrain);
		Codem = (EditText) findViewById(R.id.eNewCode);
		bdh = new DBHelper(this);
		db = bdh.getWritableDatabase();
		insertm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (inserttrain.getText().toString().equals("")) {
					inserttrain.setError("Enter Train Name");

				} else if (Codem.getText().toString().equals("")) {
					Codem.setError("Enter Train Code");

				} else {

					ContentValues cv = new ContentValues();

					// cv.put("pid", id.getText().toString());
					cv.put("TrainName", inserttrain.getText().toString());
					cv.put("tcode", Codem.getText().toString());
					db.insert("NewTrain", null, cv);

					Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
				}

			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});

	}

}
