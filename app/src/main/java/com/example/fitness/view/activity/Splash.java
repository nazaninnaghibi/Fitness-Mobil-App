package com.example.fitness.view.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fitness.R;

import es.dmoral.toasty.Toasty;

public class Splash extends AppCompatActivity {
    ConnectionCheck connectionCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        connectionCheck = new ConnectionCheck();
        registerReceiver(connectionCheck, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        registerReceiver(connectionCheck, new IntentFilter("android.net.android.net.wifi.WIFI_STATE_CHANGE"));
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(connectionCheck);
        super.onStop();
    }

    void MethoidCheckIntent() {
        StringRequest stringRequest = new StringRequest(0, "https://salmandhealth.ir/", response -> {

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }, error -> Toasty.error(getApplicationContext(), R.string.internet, Toast.LENGTH_SHORT, true).show());

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }

    class ConnectionCheck extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            boolean checkinfo = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (checkinfo) {
                MethoidCheckIntent();
            } else {
                startActivity(new Intent(getApplicationContext(), NoInternet.class));
                finish();
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}