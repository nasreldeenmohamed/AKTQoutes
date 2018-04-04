package com.optimizeware.aktqoutes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;

import com.startapp.android.publish.adsCommon.AutoInterstitialPreferences;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;

public class MainActivity extends Activity {
	private Handler handler;
	private ProgressBar progressBar;
	Button share_btn, rate_btn, qoutes_btn, fav_btn;
	Context con;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		StartAppSDK.init(this, "203113186", true);
        StartAppAd.enableAutoInterstitial();
        StartAppAd.setAutoInterstitialPreferences(
                new AutoInterstitialPreferences()
                        .setSecondsBetweenAds(60)
        );

		setContentView(R.layout.activity_main);
		con = this;
		final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
		// handler = new Handler();
		// progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		// startActivity(new Intent(MainActivity.this,
		// QoutesListActivity.class));

		// Thread background = new Thread() {
		// public void run() {
		//
		// try {

		InputData.context = con;
		InputData.readAllData();
		InputData.readFavorite();

		qoutes_btn = (Button) findViewById(R.id.imageViewQoutesHome);
		fav_btn = (Button) findViewById(R.id.imageViewFavorateHome);
		rate_btn = (Button) findViewById(R.id.imageViewRateHome);
		share_btn = (Button) findViewById(R.id.imageViewShareHome);

		qoutes_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				arg0.startAnimation(animAlpha);
				//qoutes_btn.setBackgroundColor(getResources().getColor(R.color.halfTransparent));
				Intent i = new Intent(con, QoutesListActivity.class);
				i.putExtra("fav", false);
				startActivity(i);
			//	qoutes_btn.setBackgroundColor(Color.TRANSPARENT);
				
			}
		});

		fav_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				arg0.startAnimation(animAlpha);
				Intent i = new Intent(con, QoutesListActivity.class);
				i.putExtra("fav", true);
				startActivity(i);
			}
		});

		rate_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				arg0.startAnimation(animAlpha);

			}
		});

		share_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				arg0.startAnimation(animAlpha);

			}
		});

		// Thread will sleep for 5 seconds

		// After 5 seconds redirect to another intent

		// Remove activity
		// finish();
		//
		// } catch (Exception e) {
		//
		// }
		// }
		// };

		// start thread
		// background.start();

	}

	@Override
	public void onBackPressed() {
		Dialog d = new Dialog("خروج", "هل تريد الخروج من التطبيق؟",con);
		d.show();
	
	}
	
	
	/*
	 * public void startProgress(View view) { new Thread(new Task()).start(); }
	 * 
	 * class Task implements Runnable {
	 * 
	 * @Override public void run() { for (int i = 0; i <= 20; i++) { final int
	 * value = i; try { Thread.sleep(1000);
	 * 
	 * } catch (InterruptedException e) { e.printStackTrace(); }
	 * handler.post(new Runnable() {
	 * 
	 * @Override public void run() { progressBar.setProgress(value);
	 * 
	 * } }); } } }
	 */
}
