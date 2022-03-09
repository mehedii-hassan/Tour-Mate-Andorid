package activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tourmate.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.etSignUpUserName)
    AppCompatEditText etSignInUserName;

    @BindView(R.id.etSignInUserPassword)
    AppCompatEditText etSignInUserPassword;

    @BindView(R.id.tvSignInForgotPass)
    AppCompatTextView tvSignInForgotPass;

    @BindView(R.id.btnLoginSignIn)
    AppCompatButton btnLoginSignIn;

    @BindView(R.id.tvSignUpSIActivity)
    AppCompatTextView tvSignUpSignInActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        btnLoginSignIn.setOnClickListener(this);
        tvSignUpSignInActivity.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginSignIn:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;

            case R.id.tvSignUpSIActivity:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
        }
    }
}