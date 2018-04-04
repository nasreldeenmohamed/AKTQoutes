package com.optimizeware.aktqoutes;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class InputData {

	static Context context;

	static ArrayList<String> allQuotes = new ArrayList<String>();
	static ArrayList<Integer> allFavoraties = new ArrayList<Integer>();
	static ArrayList<String> allFavoraties_string = new ArrayList<String>();
	static String DataFile = "data";
	static String FavoriteFile = "favorite.txt";

	private InputData() {

	}

	public static void readAllData() {

		try {
			InputStream json = context.getAssets().open(DataFile + ".txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(json));
			String str;

			while ((str = in.readLine()) != null) {
				allQuotes.add(str);
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void readFavorite() {
		try {
			// File file = new File(context.getFilesDir(), FavoriteFile);
			allFavoraties.clear();
			InputStream is = context.openFileInput(FavoriteFile);

			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str;
			if ((str = br.readLine()) != null) {
				String[] parts = str.split("-");
				for (int i = 0; i < parts.length; i++)
					allFavoraties.add(Integer.parseInt(parts[i]));

			}

			br.close();
			is.close();

		} catch (IOException e) {
			File file = new File(context.getFilesDir(), FavoriteFile);
			if (file.exists())
				file.delete();
		}
	}

	public static void Save() {

		// File file = new File("FavoriteFile"+".txt");
		// boolean deleted = file.delete();

		try {
			File file = new File(context.getFilesDir(), FavoriteFile);
			if (file.exists())
				file.delete();
			FileOutputStream fos = context.openFileOutput(FavoriteFile,
					Context.MODE_APPEND);
			// fos = new FileOutputStream(FavoriteFile, true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (int i = 0; i < allFavoraties.size(); i++) {
				bw.write(String.valueOf(allFavoraties.get(i)));

				bw.write("-");
			}
			bw.close();
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<String> getAllQoutes() {
		return allQuotes;
	}

	public void setAllQoutes(ArrayList<String> allQoutes) {
		allQuotes = allQoutes;
	}

	public static ArrayList<String> getAllFavoraties() {
		allFavoraties_string = new ArrayList<String>();
		for (int i = 0; i < allFavoraties.size(); i++) {
			allFavoraties_string.add(allQuotes.get(allFavoraties.get(i)));
		}
		return allFavoraties_string;
	}

	public void setAllFavoraties(ArrayList<Integer> allFavoraties) {
		InputData.allFavoraties = allFavoraties;
	}

	public static void AddFavoraiteQoute(int quote_id) {

		allFavoraties.add(quote_id);

		FileOutputStream fos;
		try {
			fos = context.openFileOutput(FavoriteFile, Context.MODE_APPEND);
			OutputStreamWriter outputstream = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(outputstream);

			bw.write(String.valueOf(quote_id));
			bw.write("-");

			// bw.newLine();

			bw.close();
			outputstream.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// fos = new FileOutputStream(FavoriteFile, true);

		// Save();
	}

	public static void RemoveFavoraiteQoute(int quote_id) {
		// allFavoraties_string.remove(allFavoraties.indexOf((Integer)quote_id));
		allFavoraties.remove((Integer) quote_id);
		Save();
		getAllFavoraties();

	}

	public void SearchFavoraiteQoute() {

	}

	public static boolean isExistFavoraiteQuote(int quote_id) {
		if (allFavoraties.size() == 0)
			return false;

		return allFavoraties.contains(quote_id);
	}

}
