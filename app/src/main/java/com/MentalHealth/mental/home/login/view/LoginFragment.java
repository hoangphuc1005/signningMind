package com.MentalHealth.mental.home.login.view;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.MentalHealth.mental.MainActivity;
import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.home.login.model.LoginModel;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends BaseFragment {
    private EditText tvUserName;
    private EditText tvUserPassWord;
    private Button btnLogin;
    public static final String MY_PREFERENCE = "Account";
    public static final String USERNAME = "userNameKey";
    SharedPreferences sharedpreferences;
    public static final String PASSWORD = "passWordKey";
    private SOService mService;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        actionView();
        moveView();
    }

    private void moveView() {
        sharedpreferences = getContext().getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
    }

    private void actionView() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    checkUserPass(tvUserName.getText().toString().trim(),
                            tvUserPassWord.getText().toString().trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void initView() {
        tvUserName = (EditText) findViewById(R.id.tvAccount);
        tvUserPassWord = (EditText) findViewById(R.id.tvPassWord);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        mService = ApiUtils.getSOService();
    }

    private void checkUserPass(String userName, String passWord) {
        if (userName.isEmpty() && passWord.isEmpty()) {
            showErrorText("Bạn chưa nhập email và mật khẩu, bạn hãy nhập email và mật khẩu để có thể trải nghiệm App một cách tốt nhất");
        } else if (userName.isEmpty()) {
            showErrorText("Bạn chưa nhập email, bạn hãy nhập email để có thể trải nghiệm App một cách tốt nhất");
        } else if (passWord.isEmpty()) {
            showErrorText("Bạn chưa nhập mật khẩu, bạn hãy nhập mật khẩu để có thể trải nghiệm App một cách tốt nhất");
        }
        if (!userName.isEmpty() && !passWord.isEmpty()) {
            if (userName.equals("thanhtn") && passWord.equals("123456")) {
                savedAccount(userName, passWord);
                checkLogin(userName,passWord,"1234567");
            } else if (!passWord.equals("123456")) {
                showErrorText("Bạn đã nhập sai mật khẩu , bạn hãy nhập lại mật khẩu để tiếp tục ");
            } else if (userName.equals("thanhtn")) {
                showErrorText("Bạn đã nhập sai eamil , bạn hãy nhập lại email để tiếp tục ");
            }

        }
    }

    private  void checkLogin(String userName, String passWord,String deviceID){
        mService.loginApp(userName,passWord,deviceID).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response!=null){
                    if(response.body().getMessage().equals("success")){
                        nextView();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
    }

    private void nextView() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void showErrorText(String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private void savedAccount(String userName, String passWord) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USERNAME, userName.trim());
        editor.putString(PASSWORD, passWord.trim());
        editor.commit();
    }
}
