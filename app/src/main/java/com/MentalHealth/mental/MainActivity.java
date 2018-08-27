package com.MentalHealth.mental;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.MentalHealth.mental.home.view.MainFragment;
import com.google.firebase.iid.FirebaseInstanceId;

//import com.appsee.Appsee;
//import com.crashlytics.android.Crashlytics;
////import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Fabric.with(this, new Crashlytics());
//        Appsee.start(getString(R.string.com_appsee_apikey));
        setContentView(R.layout.activity_main);
        //AIzaSyDopNvyH-wgUYE1suNa6Ag6AvlJBXGIwkQ
        MainFragment mFragment = new MainFragment();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e( "Refreshed token" , refreshedToken);
//            if (noti_live != null) {
//                mFragment.setDefaultTab(7);
//                SharedPreference.checkToken(MainActivity.this, idUser);
//                SharedPreference.saveAccountHasLogin(MainActivity.this, mUserModel);
//            }
        mFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, mFragment)
                .commit();
    }



}
