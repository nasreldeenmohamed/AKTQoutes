package com.optimizeware.aktqoutes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.startapp.android.publish.adsCommon.StartAppAd;

import java.util.ArrayList;

public class QoutesListActivity extends Activity {

	ToggleButton qouts_btn, fav_btn;
	// List view
	private ListView lv;
	// Listview Adapter
	ArrayAdapter<String> adapterQoutes, adapterFavor;
	String newString = "";
	String FILE_NAME = "data";
	boolean isQouat = false;
	int pos_in_list = 0;
	boolean fav = false;
	public static ArrayList<String> allQuotes, AllFavoraites;

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (fav_btn.isChecked()) {
			AllFavoraites = InputData.getAllFavoraties();
			adapterFavor = new ArrayAdapter<String>(InputData.context,
					R.layout.list_item, R.id.product_name, AllFavoraites);
			lv.setAdapter(adapterFavor);
		}
         
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_qoutes_list);

		qouts_btn = (ToggleButton) findViewById(R.id.toggleButtonQoutes);
		fav_btn = (ToggleButton) findViewById(R.id.toggleButtonFavoraites);

		lv = (ListView) findViewById(R.id.listQoutes);
		// qouts_btn.setTextOn("Quotes");
		// qouts_btn.setTextOff("Quotes");

		// fav_btn.setTextOn("Favoraites");
		// fav_btn.setTextOff("Favoraites");

		Bundle b = getIntent().getExtras();
		fav = b.getBoolean("fav");

		allQuotes = new ArrayList<String>();
		AllFavoraites = new ArrayList<String>();

		allQuotes = InputData.allQuotes;
		adapterQoutes = new ArrayAdapter<String>(InputData.context,
				R.layout.list_item, R.id.product_name, allQuotes);

		fav_btn.setChecked(false);

		if (fav)
			fav_btn.performClick();
		else if (!fav) { // qouts_btn.performClick();
			lv.setAdapter(adapterQoutes);
			qouts_btn.setChecked(true);
		}
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long id) {
				pos_in_list = pos;
				Intent intent = new Intent(QoutesListActivity.this,
						QuoteActivity.class);
				String position = String.valueOf(pos);
				// Log.e("position", position);
				intent.putExtra("pos", position);
				intent.putExtra("fav", fav_btn.isChecked());
				startActivity(intent);
			}
		});
		isQouat = true;
		// qouts_btn.performClick();

		// Adding items to listview

		qouts_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				isQouat = true;
				fav_btn.setChecked(false);
				qouts_btn.setChecked(true);

				lv.setAdapter(adapterQoutes);

			}
		});

		fav_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				isQouat = false;
				qouts_btn.setChecked(false);
				fav_btn.setChecked(true);

				AllFavoraites = InputData.getAllFavoraties();

				adapterFavor = new ArrayAdapter<String>(InputData.context,
						R.layout.list_item, R.id.product_name, AllFavoraites);

				lv.setAdapter(adapterFavor);

			}
		});

	}

	@Override
	public void onBackPressed() {
		StartAppAd.onBackPressed(this);
		super.onBackPressed();
	}
}
