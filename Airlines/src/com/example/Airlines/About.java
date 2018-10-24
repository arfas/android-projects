package com.example.Airlines;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class About extends Activity {

	TextView t;
	LinearLayout l;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);

		t = (TextView) findViewById(R.id.texw3);
		l = (LinearLayout) findViewById(R.id.linearrrrrrr);
		l.setVisibility(View.GONE);
		t.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				l.setVisibility(View.VISIBLE);
			}
		});

	}
}
