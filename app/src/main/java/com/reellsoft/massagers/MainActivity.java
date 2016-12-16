package com.reellsoft.massagers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Vibrator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends Activity {

    ImageButton lcFeetButton;
    ImageButton lcArmButton;
    ImageButton lcLegButton;
    ImageButton lcBodyButton;
    ImageButton lcStopButton;
    ImageView lcCurrentImage;
    RelativeLayout mMainLayout;
    AdView mAdView;
    Vibrator lcVibrator;
    final Context lcContext = this;
    InterstitialAd mInterstitialAd;
    Handler handler;
    Runnable myRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        AdView adView = (AdView)this.findViewById(R.id.adView);
//        adView.loadAd(new AdRequest.Builder()
//                .addTestDevice(com.google.ads.AdRequest.TEST_EMULATOR)
//                .build());
        lcFeetButton = (ImageButton) findViewById(R.id.btnFeet);
        lcFeetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StartFootMassage();
            }
        });

        lcArmButton = (ImageButton) findViewById(R.id.btnArm);
        lcArmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StartArmMassage();
            }
        });

        lcBodyButton = (ImageButton) findViewById(R.id.btnBody);
        lcBodyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StartBodyMassage();
            }
        });

        lcLegButton = (ImageButton) findViewById(R.id.btnLeg);
        lcLegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StartLegMassage();
            }
        });

        lcStopButton = (ImageButton) findViewById(R.id.btnStop);
        lcStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StopMassage();
            }
        });
        lcVibrator = (Vibrator) lcContext.getSystemService(Context.VIBRATOR_SERVICE);
        lcCurrentImage = (ImageView)findViewById(R.id.imgSelected);

        mInterstitialAd = new InterstitialAd(lcContext);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                handler = new Handler() ;

                handler.postDelayed( myRun = new Runnable() {
                    @Override
                    public void run() {
                        loadReklam();
                        handler.postDelayed( this, 40 * 1000 );
                    }
                }, 40 * 1000 );
            }
            @Override
            public void onAdLoaded() {
                handler.removeCallbacks(myRun);
                mInterstitialAd.show();
            }

        });
        mInterstitialAd.setAdUnitId("ca-app-pub-4788682465554858/1455339721");
// Create ad request
        handler = new Handler() ;

        handler.postDelayed( myRun = new Runnable() {
            @Override
            public void run() {
                loadReklam();
                handler.postDelayed( this, 5 * 1000 );
            }
        }, 5 * 1000 );
        AdView adView = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
    }
    private void loadReklam(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }
    private void StartFootMassage(){
        lcFeetButton.setImageResource(R.drawable.feetbuttonselected);
        lcStopButton.setImageResource(R.drawable.stopbutton);
        lcArmButton.setImageResource(R.drawable.armbutton);
        lcBodyButton.setImageResource(R.drawable.bodybutton);
        lcLegButton.setImageResource(R.drawable.legbutton);
        lcCurrentImage.setImageResource(R.drawable.feet);
        CreateMassage(700,900,200,150,1210);
    }

    private void StartArmMassage(){
        lcFeetButton.setImageResource(R.drawable.feetbutton);
        lcStopButton.setImageResource(R.drawable.stopbutton);
        lcArmButton.setImageResource(R.drawable.armbuttonselected);
        lcBodyButton.setImageResource(R.drawable.bodybutton);
        lcLegButton.setImageResource(R.drawable.legbutton);
        lcCurrentImage.setImageResource(R.drawable.arm);
        CreateMassage(300,300,300,300,900);
    }

    private void StartBodyMassage(){
        lcFeetButton.setImageResource(R.drawable.feetbutton);
        lcStopButton.setImageResource(R.drawable.stopbutton);
        lcArmButton.setImageResource(R.drawable.armbutton);
        lcBodyButton.setImageResource(R.drawable.bodybuttonselected);
        lcLegButton.setImageResource(R.drawable.legbutton);
        lcCurrentImage.setImageResource(R.drawable.body);
        CreateMassage(300,800,250,600,1400);
    }

    private void StartLegMassage(){
        lcFeetButton.setImageResource(R.drawable.feetbutton);
        lcStopButton.setImageResource(R.drawable.stopbutton);
        lcArmButton.setImageResource(R.drawable.armbutton);
        lcBodyButton.setImageResource(R.drawable.bodybutton);
        lcLegButton.setImageResource(R.drawable.legbuttonselected);
        lcCurrentImage.setImageResource(R.drawable.leg);
        CreateMassage(200,500,200,500,1000);
    }

    private void StopMassage(){
        lcFeetButton.setImageResource(R.drawable.feetbutton);
        lcStopButton.setImageResource(R.drawable.stopbuttonselected);
        lcArmButton.setImageResource(R.drawable.armbutton);
        lcBodyButton.setImageResource(R.drawable.bodybutton);
        lcLegButton.setImageResource(R.drawable.legbutton);
        lcCurrentImage.setImageResource(R.drawable.stop);
        lcVibrator.cancel();
    }

    private void CreateMassage(int argDot, int argDash, int argShortGap, int argMediumGap, int argLongGap){
        // This example will cause the phone to vibrate "SOS" in Morse Code
        // In Morse Code, "s" = "dot-dot-dot", "o" = "dash-dash-dash"
        // There are pauses to separate dots/dashes, letters, and words
        // The following numbers represent millisecond lengths
        int lcDot = argDot;      // Length of a Morse Code "dot" in milliseconds
        int lcDash = argDash;     // Length of a Morse Code "dash" in milliseconds
        int lcShortGap = argShortGap;    // Length of Gap Between dots/dashes
        int lcMediumGap = argMediumGap;   // Length of Gap Between Letters
        int lcLongGap = argLongGap;    // Length of Gap Between Words
        long[] lcPattern = {
                0,  // Start immediately
                lcDot, lcShortGap, lcDot, lcShortGap, lcDot,    // s
                lcMediumGap,
                lcDash, lcShortGap, lcDash, lcShortGap, lcDash, // o
                lcMediumGap,
                lcDot, lcShortGap, lcDot, lcShortGap, lcDot,    // s
                lcLongGap
        };
        // Only perform this pattern one time (-1 means "do not repeat")
        lcVibrator.vibrate(lcPattern, 11);

    }

    @Override
    public void onBackPressed() {
       lcVibrator.cancel();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
