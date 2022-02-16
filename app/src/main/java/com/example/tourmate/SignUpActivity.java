package com.example.tourmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.etSignUpUserName)
    AppCompatEditText etSignUpUserName;

    @BindView(R.id.etSignUpEmailAddress)
    AppCompatEditText etSignUpEmailAddress;

    @BindView(R.id.etUserPassSignUp)
    AppCompatEditText etUserPassSignUp;

    @BindView(R.id.etConfirmPassword)
    AppCompatEditText etConfirmPassword;

    @BindView(R.id.btnSignUp)
    AppCompatButton btnSignUp;

    @BindView(R.id.tvSignInSUActivity)
    AppCompatTextView tvSignInSUActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        btnSignUp.setOnClickListener(this);
        tvSignInSUActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSignUp:
            case R.id.tvSignInSUActivity:
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                break;
        }

    }
}