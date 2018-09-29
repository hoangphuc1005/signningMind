package com.MentalHealth.mental;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.MentalHealth.mental.constant.Constants;
import com.MentalHealth.mental.diary.view.ChatWithMonsterFragment;
import com.MentalHealth.mental.gamemini.view.MiniGameAnswerFragment;
import com.MentalHealth.mental.home.login.model.LoginModel;
import com.MentalHealth.mental.home.view.MainFragment;
import com.MentalHealth.mental.infonew.view.InfoNewDetailFragment;
import com.MentalHealth.mental.monthinfo.view.DayDetailSlideFragment;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;
import com.MentalHealth.mental.servicefcm.NotificationUtils;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.MentalHealth.mental.home.view.MainFragment.USER_ID;

//import com.appsee.Appsee;
//import com.crashlytics.android.Crashlytics;
////import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private String id, type, resume;
    private SOService mService;
    SharedPreferences sharedpreferences;
    public static final String MY_PREFERENCE = "Account";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = this.getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        int count = sharedpreferences.getInt("Count", 0);
        if (count > 0) {
            count--;
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt("Count", count);
            editor.apply();
        }
//        Fabric.with(this, new Crashlytics());
//        Appsee.start(getString(R.string.com_appsee_apikey));

        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        mService = ApiUtils.getSOService();
        if (bundle != null) {
            id = bundle.getString(Constants.ID);
            type = bundle.getString(Constants.TYPE);
            if (id != null && type != null) {
                switch (type) {
                    case "0":
                        moveFragment(new InfoNewDetailFragment());
                        break;
                    case "1":
                        moveFragment(new DayDetailSlideFragment());
                        break;
                    case "2":
                        moveFragment(new ChatWithMonsterFragment());
                        break;
                    case "3":
                        moveFragment(new MiniGameAnswerFragment());
                        break;

                }
            }
        } else {
            MainFragment mFragment = new MainFragment();
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Log.e("Refreshed token", refreshedToken);
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

        //AIzaSyDopNvyH-wgUYE1suNa6Ag6AvlJBXGIwkQ

    }

    private void moveFragment(Fragment fragment) {
//                mFragment.setArguments(getIntent().getExtras()); //old
        Bundle args = new Bundle();

        args.putString("id", id);
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        final String userID = sharedpreferences.getString(USER_ID, "");
        if (!userID.isEmpty()) {
            if (!NotificationUtils.isAppIsInBackground(this)) {
                mService.loginTime(userID, "1").enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        Log.e("response", response.raw().message());
                        if (response.raw().code() == 400) {
                            mService.loginTime(userID, "0").enqueue(new Callback<LoginModel>() {
                                @Override
                                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                                    if (response.raw().code() == 200) {
                                        mService.loginTime(userID, "1");
                                    }
                                }

                                @Override
                                public void onFailure(Call<LoginModel> call, Throwable t) {

                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {

                    }
                });
            } else {
                mService.loginTime(userID, "0").enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        Log.e("response", response.raw().message());
                        if (response.raw().code() == 400) {
                            mService.loginTime(userID, "1").enqueue(new Callback<LoginModel>() {
                                @Override
                                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                                    if (response.raw().code() == 200) {
                                        mService.loginTime(userID, "0");
                                    }
                                }

                                @Override
                                public void onFailure(Call<LoginModel> call, Throwable t) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {

                    }
                });
            }
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        sharedpreferences = this.getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        final String userID = sharedpreferences.getString(USER_ID, "");
        if (!userID.isEmpty()) {
            if (!NotificationUtils.isAppIsInBackground(this)) {
                mService.loginTime(userID, "1").enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        Log.e("response", response.raw().message());
                        if (response.raw().code() == 400) {
                            mService.loginTime(userID, "0").enqueue(new Callback<LoginModel>() {
                                @Override
                                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                                    if (response.raw().code() == 200)
                                        mService.loginTime(userID, "1");
                                }

                                @Override
                                public void onFailure(Call<LoginModel> call, Throwable t) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {

                    }
                });
            } else {
                mService.loginTime(userID, "0").enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        Log.e("response", response.raw().message());
                        if (response.raw().code() == 400) {
                            mService.loginTime(userID, "1").enqueue(new Callback<LoginModel>() {
                                @Override
                                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                                    if (response.raw().code() == 200)
                                        mService.loginTime(userID, "0");
                                }

                                @Override
                                public void onFailure(Call<LoginModel> call, Throwable t) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {

                    }
                });
            }
        }
    }
}
