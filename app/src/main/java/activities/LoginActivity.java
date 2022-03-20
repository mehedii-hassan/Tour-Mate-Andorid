package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tourmate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.etSignUpUserEmail)
    AppCompatEditText etSignUpUserEmail;

    @BindView(R.id.etSignInUserPassword)
    AppCompatEditText etUPassword;

    @BindView(R.id.tvSignInForgotPass)
    AppCompatTextView tvSignInForgotPass;

    @BindView(R.id.btnLoginSignIn)
    AppCompatButton btnLoginSignIn;

    @BindView(R.id.tvSignUpLoginActivity)
    AppCompatTextView tvSignUpLoginActivity;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        btnLoginSignIn.setOnClickListener(this);
        tvSignUpLoginActivity.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginSignIn:
                userLogin();
                break;

            case R.id.tvSignUpLoginActivity:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
        }
    }

    private void userLogin() {
        //String userName = etSignUpUserName.getText().toString().trim().toLowerCase();
        String email = etSignUpUserEmail.getText().toString().trim();
        String password = etUPassword.getText().toString().trim();


        //checking the validity of the email
        if (email.isEmpty()) {
            //etSignUpUserEmail.setError("Enter your email address");
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show();
            etSignUpUserEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //etSignUpUserEmail.setError("Enter a valid email address");
            Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
            etSignUpUserEmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            //Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
             etUPassword.setError("Enter your password");
            etUPassword.requestFocus();
        }

        if (password.length() < 6) {
            Toast.makeText(this, "wrong password", Toast.LENGTH_SHORT).show();
            //etSignInUserPassword.setError("wrong password");
            etUPassword.requestFocus();

        }


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    etSignUpUserEmail.setText("");
                    etUPassword.setText("");
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}