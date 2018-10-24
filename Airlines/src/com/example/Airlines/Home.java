package com.example.Airlines;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Home extends Fragment {

	Button book, mybook, details, deals;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.home, container, false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		book = (Button) getActivity().findViewById(R.id.bbook);
		mybook = (Button) getActivity().findViewById(R.id.bmyBook);
		details = (Button) getActivity().findViewById(R.id.bdetails);
		deals = (Button) getActivity().findViewById(R.id.bdeals);

		book.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(getActivity().getApplicationContext(), Book.class));

			}
		});
		mybook.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity().getApplicationContext(), MyBooking.class));

			}
		});
		details.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity().getApplicationContext(), TermsAndConditions.class));
				Toast.makeText(getActivity(), "Flight Updates", Toast.LENGTH_LONG).show();
			}
		});
		deals.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment newFragment = new Home();
				FragmentManager fm = getFragmentManager();
				newFragment = new Deals();
				fm.beginTransaction().replace(R.id.content_frame, newFragment).commit();

			}
		});
	}

}
