package com.optimizeware.aktqoutes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.startapp.android.publish.adsCommon.StartAppAd;

public class Dialog {
	String title, message;
	Context context;

	public Dialog(String title, String message, Context context) {
		this.title = title;
		this.message = message;
		this.context = context;
	}

	void show() {
		final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setButton("نعم", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				StartAppAd.onBackPressed(context);
				System.exit(1);
			}
		});
		
		alertDialog.setButton2("تقييم", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		
		alertDialog.setButton3("لا", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				alertDialog.dismiss();
			}
		});
		alertDialog.show();
	}
}
