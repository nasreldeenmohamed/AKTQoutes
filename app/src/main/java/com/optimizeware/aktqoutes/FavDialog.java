package com.optimizeware.aktqoutes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class FavDialog {
	String title , message ;
	int Qoute_id; 
	Context context ; 
	boolean yes=false;
	
	public FavDialog(String title , String message, int id,  Context context) {
		this.title = title ;
		this.message = message;
		this.Qoute_id = id;
		this.context = context ; 
	}
	
	@SuppressWarnings("deprecation")
	void show()
	{
		final AlertDialog alertDialog = new AlertDialog.Builder(
				context).create();
		alertDialog.setTitle(title);
		alertDialog
				.setMessage(message);
		//alertDialog.setIcon(id);
		alertDialog.setButton("Yes",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						yes=true;
						// Remove favoraite
						InputData.RemoveFavoraiteQoute(Qoute_id);
						Toast.makeText(context, "Qoute Removed from Favoraites Successfully!", Toast.LENGTH_SHORT).show();
					}
				});
		
		alertDialog.setButton2("No",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						alertDialog.dismiss();
					}
				});
		
		
		alertDialog.show();
		
		
	}
}
