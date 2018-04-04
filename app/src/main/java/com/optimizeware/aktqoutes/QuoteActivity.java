package com.optimizeware.aktqoutes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.startapp.android.publish.adsCommon.StartAppAd;


//import android.renderscript.AllocationAdapter;

public class QuoteActivity extends Activity {

    Button share_btn, Copy_btn, Fav_btn, next_btn, Prev_btn;
    TextView qoute_tv;
    String Qoute;
    String Postion = "";
    int QoutePosition = 0;
    Context context = this;
    private boolean isFavor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quote);

        initializeComponent();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Postion = null;
            } else {
                Postion = extras.getString("pos");
                isFavor = extras.getBoolean("fav");
            }
        } else {
            Toast.makeText(QuoteActivity.this, "حدث خطأ ما، اختر المقولة مرة اخرى",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        QoutePosition = Integer.parseInt(Postion);
        if (isFavor)
            Qoute = InputData.allFavoraties_string.get(QoutePosition)
                    .toString();
        else
            Qoute = InputData.allQuotes.get(QoutePosition).toString();

        qoute_tv.setText(Qoute);
        if (!isFavor) {// lw hya gaya 3n taree2 el all quotes y2ma hatb2a
            // mawgooda aw msh mawgoda fe el fav
            if (InputData.isExistFavoraiteQuote(QoutePosition)) {
                Fav_btn.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        getResources().getDrawable(
                                R.drawable.ic_action_important), null, null);
            } else
                Fav_btn.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        getResources().getDrawable(
                                R.drawable.ic_action_not_important), null, null);

        } else // kda ya3ny hya asln gaia 3n tare2 tab el favorite
        {
            Fav_btn.setCompoundDrawablesWithIntrinsicBounds(null,
                    getResources().getDrawable(R.drawable.ic_action_important),
                    null, null);

        }

        qoute_tv.setOnTouchListener(new OnSwipeTouchListener() {
            @Override
            public void onSwipeRight() {
                prev_fun();
                // Toast.makeText(QuoteActivity.this, "right to left",
                // Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeLeft() {
                next_fun();
                // Toast.makeText(QuoteActivity.this, "left to right",
                // Toast.LENGTH_SHORT).show();
            }
        });

        next_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                next_fun();
            }
        });

        Prev_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                prev_fun();
            }
        });

        share_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                shareText(Qoute);
            }
        });

        Copy_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                copyToClipboard(Qoute);
                StartAppAd.showAd(QuoteActivity.this);
                Toast.makeText(getApplicationContext(),
                        "تم نسخ المقولة..", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        Fav_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // isFavor=
                // InputData.isExistFavoraiteQuote(Integer.parseInt(Postion));
                if (!isFavor) {// lw hya gaya 3n taree2 el all quotes y2ma
                    // hatb2a mawgooda aw msh mawgoda fe el fav
                    if (InputData.isExistFavoraiteQuote(QoutePosition)) {

                        show("المفضلات",
                                "هذه المقولة مسجلة في المفضلات، "
                                        + "هل تريد حذفها منها؟",
                                QoutePosition, false);
                        /*
                         * InputData.RemoveFavoraiteQoute(QoutePosition);
                         * Fav_btn.setCompoundDrawablesWithIntrinsicBounds(
                         * null, getResources().getDrawable(
                         * R.drawable.ic_action_not_important), null, null);
                         */
                    } else {
                        Fav_btn.setCompoundDrawablesWithIntrinsicBounds(
                                null,
                                getResources().getDrawable(
                                        R.drawable.ic_action_important), null,
                                null);

                        InputData.AddFavoraiteQoute(QoutePosition);
                    }
                } else // kda ya3ny hya asln gaia 3n tare2 tab el favorite
                {

                    /*
                     * InputData.RemoveFavoraiteQoute(InputData.allFavoraties
                     * .get(QoutePosition));
                     *
                     * Fav_btn.setCompoundDrawablesWithIntrinsicBounds( null,
                     * getResources().getDrawable(
                     * R.drawable.ic_action_not_important), null, null);
                     */

                    show("المفضلات",
                            "هذه المقولة مسجلة في المفضلات، "
                                    + "هل تريد حذفها منها؟",
                            InputData.allFavoraties.get(QoutePosition), true);
                }
            }
        });
    }

    void prev_fun() {
        QoutePosition = QoutePosition - 1;

        if (isFavor) {
            if (InputData.allFavoraties_string.size() == 0) {
                Toast.makeText(QuoteActivity.this,
                        "لا يوجد مقولات مفضلة", Toast.LENGTH_SHORT)
                        .show();
                finish();

            }
            if (QoutePosition < 0)
                QoutePosition = InputData.allFavoraties_string.size() - 1;
            Qoute = InputData.allFavoraties_string.get(QoutePosition)
                    .toString();
            Fav_btn.setCompoundDrawablesWithIntrinsicBounds(null,
                    getResources().getDrawable(R.drawable.ic_action_important),
                    null, null);

        } else {
            if (QoutePosition < 0)
                QoutePosition = InputData.allQuotes.size() - 1;
            Qoute = InputData.allQuotes.get(QoutePosition).toString();
            if (InputData.isExistFavoraiteQuote(QoutePosition)) {
                Fav_btn.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        getResources().getDrawable(
                                R.drawable.ic_action_important), null, null);
            } else
                Fav_btn.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        getResources().getDrawable(
                                R.drawable.ic_action_not_important), null, null);

        }

        qoute_tv.setText(Qoute);
    }

    void next_fun() {
        QoutePosition = QoutePosition + 1;

        if (isFavor) {
            if (InputData.allFavoraties_string.size() == 0) {
                Toast.makeText(QuoteActivity.this,
                        "لا يوجد مقولات مفضلة", Toast.LENGTH_SHORT)
                        .show();
                finish();
            } else {

                if (QoutePosition >= InputData.allFavoraties_string.size())
                    QoutePosition = 0;
                Qoute = InputData.allFavoraties_string.get(QoutePosition)
                        .toString();
                Fav_btn.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        getResources().getDrawable(
                                R.drawable.ic_action_important), null, null);
            }

        } else {
            if (QoutePosition >= InputData.allQuotes.size())
                QoutePosition = 0;
            Qoute = InputData.allQuotes.get(QoutePosition).toString();
            if (InputData.isExistFavoraiteQuote(QoutePosition)) {
                Fav_btn.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        getResources().getDrawable(
                                R.drawable.ic_action_important), null, null);
            } else
                Fav_btn.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        getResources().getDrawable(
                                R.drawable.ic_action_not_important), null, null);

        }
        qoute_tv.setText(Qoute);

    }

    void show(String title, String message, final int id, final boolean fav) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        // alertDialog.setIcon(id);
        alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StartAppAd.showAd(QuoteActivity.this);
                // Remove favoraite
                InputData.RemoveFavoraiteQoute(id);
                Toast.makeText(context,"تم حذف المقولة من المفضلات..", Toast.LENGTH_SHORT).show();
                Fav_btn.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        getResources().getDrawable(R.drawable.ic_action_not_important), null, null);
                if (fav)
                    next_btn.performClick();

            }
        });

        alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    private void initializeComponent() {
        share_btn = (Button) findViewById(R.id.buttonShare);
        Copy_btn = (Button) findViewById(R.id.buttonCopy);
        Fav_btn = (Button) findViewById(R.id.buttonMakeFavoraite);
        next_btn = (Button) findViewById(R.id.buttonNext);
        Prev_btn = (Button) findViewById(R.id.buttonPrev);

        qoute_tv = (TextView) findViewById(R.id.TextView_Qoute);

    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    void copyToClipboard(String txt) {
        // int sdk = android.os.Build.VERSION.SDK_INT;
        // if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboard.setText(txt); //
        // } else {
        // android.content.ClipboardManager clipboard =
        // (android.content.ClipboardManager)
        // getSystemService(CLIPBOARD_SERVICE);
        // android.content.ClipData clip = android.content.ClipData
        // .newPlainText("text label", txt);
        // clipboard.setPrimaryClip(clip); //
        // }
    }


    void shareText(String txt) {
        Intent localIntent = new Intent(Intent.ACTION_SEND);
        // localIntent.setAction("android.intent.action.SEND");
        localIntent.setType("text/plain");
        // localIntent.putExtra(Intent.EXTRA_SUBJECT, txt);
        localIntent.putExtra(Intent.EXTRA_TEXT, txt);

        startActivity(Intent.createChooser(localIntent, "Share it!"));
        StartAppAd.showAd(this);
    }

    /*
     * void shareScreenshot() { View rootView =
     * gameActivity.findViewById(android.R.id.content).getRootView();
     * rootView.setDrawingCacheEnabled(true); Bitmap screen =
     * rootView.getDrawingCache(); Intent share = new
     * Intent(Intent.ACTION_SEND); share.setType("image/jpeg");
     * ByteArrayOutputStream bytes = new ByteArrayOutputStream();
     * screen.compress(Bitmap.CompressFormat.JPEG, 100, bytes); File f = new
     * File(Environment.getExternalStorageDirectory() + File.separator +
     * "temporary_file.jpg"); try { f.createNewFile(); FileOutputStream fo = new
     * FileOutputStream(f); fo.write(bytes.toByteArray()); fo.close(); } catch
     * (IOException e) { e.printStackTrace(); }
     * share.putExtra(Intent.EXTRA_STREAM,
     * Uri.parse("file:///sdcard/temporary_file.jpg"));
     * context.startActivity(Intent.createChooser(share,
     * resources.getString(R.string.AskUrFriend))); }
     */
    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }
}
